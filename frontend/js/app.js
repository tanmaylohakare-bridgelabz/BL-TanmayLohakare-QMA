// UC19: ES9 Features, Async/Await, AJAX, DOM Manipulation, Conditional Logic

// Define Unit Mapping
const categoryUnits = {
    LENGTH: ['INCHES', 'FEET', 'YARD', 'CENTIMETER'],
    VOLUME: ['GALLON', 'LITRE', 'MILLILITER'],
    WEIGHT: ['KILOGRAM', 'GRAM', 'TONNE'],
    TEMPERATURE: ['FAHRENHEIT', 'CELSIUS']
};

// State variables
let currentToken = null;
let currentMode = 'convert';

// DOM Elements
const authSection = document.getElementById('auth-section');
const appSection = document.getElementById('app-section');
const logoutBtn = document.getElementById('logout-btn');
const categorySelect = document.getElementById('category');
const tabs = document.querySelectorAll('.tab');
const forms = document.querySelectorAll('.app-form');
const unitSelects = document.querySelectorAll('.unit-select');
const resultBox = document.getElementById('result-box');
const resultText = document.getElementById('result-text');
const loader = document.querySelector('.loader');

// Authentication Handling
const checkAuth = () => {
    // Check URL for token (after Google Redirect)
    const urlParams = new URLSearchParams(window.location.search);
    const tokenFromUrl = urlParams.get('token');

    if (tokenFromUrl) {
        localStorage.setItem('jwt_token', tokenFromUrl);
        // Clean URL
        window.history.replaceState({}, document.title, window.location.pathname);
    }

    currentToken = localStorage.getItem('jwt_token');

    if (currentToken) {
        authSection.classList.remove('active');
        appSection.classList.remove('hidden');
        appSection.classList.add('active');
        populateUnits();
    } else {
        appSection.classList.remove('active');
        appSection.classList.add('hidden');
        authSection.classList.add('active');
    }
};

const handleLogout = () => {
    localStorage.removeItem('jwt_token');
    currentToken = null;
    checkAuth();
};

logoutBtn.addEventListener('click', handleLogout);

// UI Logic
const populateUnits = () => {
    const selectedCategory = categorySelect.value;
    const units = categoryUnits[selectedCategory];
    
    unitSelects.forEach(select => {
        select.innerHTML = '';
        units.forEach(unit => {
            const option = document.createElement('option');
            option.value = unit;
            option.textContent = unit.charAt(0) + unit.slice(1).toLowerCase();
            select.appendChild(option);
        });
    });
};

categorySelect.addEventListener('change', () => {
    populateUnits();
    hideResult();
});

// Tab Switching
tabs.forEach(tab => {
    tab.addEventListener('click', (e) => {
        // Update active tab styling
        tabs.forEach(t => t.classList.remove('active'));
        e.target.classList.add('active');

        // Show corresponding form
        currentMode = e.target.getAttribute('data-target');
        forms.forEach(f => f.classList.remove('active'));
        
        let targetForm;
        if (currentMode === 'convert') targetForm = document.getElementById('convert-form');
        else if (currentMode === 'compare') targetForm = document.getElementById('compare-form');
        else {
            targetForm = document.getElementById('math-form');
            document.getElementById('math-operator').textContent = currentMode === 'add' ? '+' : '-';
        }
        targetForm.classList.add('active');
        hideResult();
    });
});

const showResult = (text, isError = false) => {
    loader.classList.add('hidden');
    resultText.classList.remove('hidden');
    resultBox.classList.remove('hidden');
    resultText.textContent = text;
    if (isError) {
        resultBox.classList.add('error');
    } else {
        resultBox.classList.remove('error');
    }
};

const hideResult = () => {
    resultBox.classList.add('hidden');
};

const showLoader = () => {
    resultBox.classList.remove('hidden');
    resultBox.classList.remove('error');
    resultText.classList.add('hidden');
    loader.classList.remove('hidden');
};

// API Handling with Promises & Async/Await
const apiCall = async (endpoint, payload) => {
    try {
        const response = await fetch(`http://localhost:8080/api/quantity/${endpoint}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${currentToken}`
            },
            body: JSON.stringify(payload)
        });

        if (response.status === 401 || response.status === 403) {
            handleLogout();
            throw new Error('Session expired. Please log in again.');
        }

        const data = await response.json();
        
        if (!response.ok) {
            throw new Error(data.message || 'An error occurred during calculation.');
        }

        return data;
    } catch (error) {
        throw error;
    }
};

// Form Submissions
document.getElementById('convert-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    showLoader();
    
    const payload = {
        category: categorySelect.value,
        value1: parseFloat(document.getElementById('conv-val').value),
        unit1: document.getElementById('conv-unit1').value,
        targetUnit: document.getElementById('conv-unit2').value
    };

    try {
        const res = await apiCall('convert', payload);
        showResult(`${payload.value1} ${payload.unit1} = ${res.data.toFixed(4)} ${payload.targetUnit}`);
    } catch (err) {
        showResult(err.message, true);
    }
});

document.getElementById('compare-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    showLoader();
    
    const payload = {
        category: categorySelect.value,
        value1: parseFloat(document.getElementById('comp-val1').value),
        unit1: document.getElementById('comp-unit1').value,
        value2: parseFloat(document.getElementById('comp-val2').value),
        unit2: document.getElementById('comp-unit2').value
    };

    try {
        const res = await apiCall('compare', payload);
        const isEqual = res.data === true;
        showResult(isEqual ? 'Equal' : 'Not Equal');
    } catch (err) {
        showResult(err.message, true);
    }
});

document.getElementById('math-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    showLoader();
    
    const payload = {
        category: categorySelect.value,
        value1: parseFloat(document.getElementById('math-val1').value),
        unit1: document.getElementById('math-unit1').value,
        value2: parseFloat(document.getElementById('math-val2').value),
        unit2: document.getElementById('math-unit2').value,
        targetUnit: document.getElementById('math-target').value
    };

    try {
        const res = await apiCall(currentMode, payload);
        const operator = currentMode === 'add' ? '+' : '-';
        showResult(`${payload.value1} ${payload.unit1} ${operator} ${payload.value2} ${payload.unit2} = ${res.data.toFixed(4)} ${payload.targetUnit}`);
    } catch (err) {
        showResult(err.message, true);
    }
});

// Initialize
document.addEventListener('DOMContentLoaded', checkAuth);
