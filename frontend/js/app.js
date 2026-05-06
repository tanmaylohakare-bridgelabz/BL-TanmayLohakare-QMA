// Unit Mapping matching backend Enums
const categoryUnits = {
    LENGTH: ['INCHES', 'FEET', 'YARD', 'CENTIMETER'],
    VOLUME: ['GALLON', 'LITRE', 'MILLILITER'],
    WEIGHT: ['KILOGRAM', 'GRAM', 'TONNE'],
    TEMPERATURE: ['FAHRENHEIT', 'CELSIUS']
};

let currentToken = null;
let currentCategory = 'LENGTH';
let currentAction = 'convert'; // convert, compare, add, subtract

// DOM Elements
const authSection = document.getElementById('auth-section');
const appSection = document.getElementById('app-section');
const logoutBtn = document.getElementById('logout-btn');

const catCards = document.querySelectorAll('.cat-card');
const actionTabs = document.querySelectorAll('.action-tab');

const val1Input = document.getElementById('val1');
const unit1Select = document.getElementById('unit1');
const val2Block = document.getElementById('val2-block');
const val2Input = document.getElementById('val2');
const unit2Select = document.getElementById('unit2');
const opBlock = document.getElementById('operator-block');
const opToggle = document.getElementById('operator-toggle');

const resultVal = document.getElementById('result-val');
const targetUnitSelect = document.getElementById('target-unit');

// Authentication
const checkAuth = () => {
    const urlParams = new URLSearchParams(window.location.search);
    const tokenFromUrl = urlParams.get('token');

    if (tokenFromUrl) {
        localStorage.setItem('jwt_token', tokenFromUrl);
        window.history.replaceState({}, document.title, window.location.pathname);
    }

    currentToken = localStorage.getItem('jwt_token');

    if (currentToken) {
        authSection.classList.add('hidden');
        appSection.classList.remove('hidden');
        logoutBtn.classList.remove('hidden');
        initUI();
    } else {
        authSection.classList.remove('hidden');
        appSection.classList.add('hidden');
        logoutBtn.classList.add('hidden');
    }
};

logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('jwt_token');
    currentToken = null;
    checkAuth();
});

// UI Initialization
const initUI = () => {
    populateDropdowns();
    updateLayout();
    calculate();
};

const populateDropdowns = () => {
    const units = categoryUnits[currentCategory];
    
    // Helper to format string
    const formatStr = str => str.charAt(0) + str.slice(1).toLowerCase();

    const options = units.map(u => `<option value="${u}">${formatStr(u)}</option>`).join('');
    
    unit1Select.innerHTML = options;
    unit2Select.innerHTML = options;
    targetUnitSelect.innerHTML = options;

    if (units.length > 1) {
        // Set some default different units
        unit2Select.selectedIndex = 1;
        targetUnitSelect.selectedIndex = 1;
    }
};

// Event Listeners for UI Selection
catCards.forEach(card => {
    card.addEventListener('click', () => {
        catCards.forEach(c => c.classList.remove('active'));
        card.classList.add('active');
        currentCategory = card.getAttribute('data-cat');
        
        // Reset action to convert if temperature is selected (no arithmetic supported)
        if (currentCategory === 'TEMPERATURE' && (currentAction === 'add' || currentAction === 'subtract')) {
            document.querySelector('[data-action="convert"]').click();
        }

        populateDropdowns();
        calculate();
    });
});

actionTabs.forEach(tab => {
    tab.addEventListener('click', () => {
        const action = tab.getAttribute('data-action');
        
        // Prevent arithmetic for temperature
        if (currentCategory === 'TEMPERATURE' && (action === 'add' || action === 'subtract')) {
            alert("Arithmetic operations are not supported for Temperature.");
            return;
        }

        actionTabs.forEach(t => t.classList.remove('active'));
        tab.classList.add('active');
        currentAction = action;
        updateLayout();
        calculate();
    });
});

opToggle.addEventListener('click', () => {
    if (currentAction === 'add') {
        currentAction = 'subtract';
        opToggle.textContent = '-';
    } else if (currentAction === 'subtract') {
        currentAction = 'add';
        opToggle.textContent = '+';
    }
    // Update active tab manually if they toggle via operator
    actionTabs.forEach(t => t.classList.remove('active'));
    document.querySelector(`[data-action="${currentAction === 'add' ? 'add' : 'subtract'}"]`)?.classList.add('active');
    calculate();
});

// Layout Manager
const updateLayout = () => {
    if (currentAction === 'convert') {
        val2Block.classList.add('hidden');
        opBlock.classList.add('hidden');
        targetUnitSelect.classList.remove('hidden');
    } else if (currentAction === 'compare') {
        val2Block.classList.remove('hidden');
        opBlock.classList.add('hidden');
        targetUnitSelect.classList.add('hidden');
    } else { // add or subtract
        val2Block.classList.remove('hidden');
        opBlock.classList.remove('hidden');
        targetUnitSelect.classList.remove('hidden');
        opToggle.textContent = currentAction === 'add' ? '+' : '-';
    }
};

// Auto Calculate triggers
[val1Input, val2Input, unit1Select, unit2Select, targetUnitSelect].forEach(el => {
    el.addEventListener('input', calculate);
});

// API Caller
async function calculate() {
    if (!currentToken) return;

    let endpoint = currentAction;
    let payload = {
        category: currentCategory,
        value1: parseFloat(val1Input.value || 0),
        unit1: unit1Select.value
    };

    if (currentAction === 'convert') {
        payload.targetUnit = targetUnitSelect.value;
    } else if (currentAction === 'compare') {
        payload.value2 = parseFloat(val2Input.value || 0);
        payload.unit2 = unit2Select.value;
    } else {
        // add or subtract
        payload.value2 = parseFloat(val2Input.value || 0);
        payload.unit2 = unit2Select.value;
        payload.targetUnit = targetUnitSelect.value;
    }

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
            logoutBtn.click();
            return;
        }

        const data = await response.json();
        if (!response.ok) throw new Error(data.message || "Error");

        const card = document.querySelector('.result-card');
        card.classList.remove('error');

        if (currentAction === 'compare') {
            resultVal.textContent = data.data === true ? "Equal" : "Not Equal";
        } else {
            resultVal.textContent = parseFloat(data.data).toFixed(3);
        }

    } catch (err) {
        const card = document.querySelector('.result-card');
        card.classList.add('error');
        resultVal.textContent = "Error";
    }
}

document.addEventListener('DOMContentLoaded', checkAuth);
