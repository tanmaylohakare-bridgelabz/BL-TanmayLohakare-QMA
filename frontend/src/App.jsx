import React, { useState, useEffect } from 'react';
import './App.scss';
import * as api from './services/api';

const categoryUnits = {
    LENGTH: ['INCHES', 'FEET', 'YARD', 'CENTIMETER'],
    VOLUME: ['GALLON', 'LITRE', 'MILLILITER'],
    WEIGHT: ['KILOGRAM', 'GRAM', 'TONNE'],
    TEMPERATURE: ['FAHRENHEIT', 'CELSIUS']
};

const formatStr = str => str.charAt(0) + str.slice(1).toLowerCase();

function App() {
  const [token, setToken] = useState(null);
  
  const [category, setCategory] = useState('LENGTH');
  const [action, setAction] = useState('convert');
  
  const [val1, setVal1] = useState(1);
  const [unit1, setUnit1] = useState('INCHES');
  
  const [val2, setVal2] = useState(1);
  const [unit2, setUnit2] = useState('FEET');
  
  const [targetUnit, setTargetUnit] = useState('INCHES');
  const [result, setResult] = useState('1.000');
  const [isError, setIsError] = useState(false);

  useEffect(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const tokenFromUrl = urlParams.get('token');

    if (tokenFromUrl) {
      localStorage.setItem('jwt_token', tokenFromUrl);
      window.history.replaceState({}, document.title, window.location.pathname);
      setToken(tokenFromUrl);
    } else {
      setToken(localStorage.getItem('jwt_token'));
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('jwt_token');
    setToken(null);
  };

  useEffect(() => {
    // When category changes, reset units
    const units = categoryUnits[category];
    setUnit1(units[0]);
    setUnit2(units.length > 1 ? units[1] : units[0]);
    setTargetUnit(units.length > 1 ? units[1] : units[0]);
    
    if (category === 'TEMPERATURE' && (action === 'add' || action === 'subtract')) {
      setAction('convert');
    }
  }, [category]);

  useEffect(() => {
    calculate();
  }, [category, action, val1, unit1, val2, unit2, targetUnit, token]);

  const calculate = async () => {
    if (!token) return;

    try {
      let res;
      if (action === 'convert') {
        res = await api.convertQuantity({ category, value1: val1, unit1, targetUnit });
        setResult(parseFloat(res.data).toFixed(3));
      } else if (action === 'compare') {
        res = await api.compareQuantities({ category, value1: val1, unit1, value2: val2, unit2 });
        setResult(res.data ? "Equal" : "Not Equal");
      } else if (action === 'add') {
        res = await api.addQuantities({ category, value1: val1, unit1, value2: val2, unit2, targetUnit });
        setResult(parseFloat(res.data).toFixed(3));
      } else if (action === 'subtract') {
        res = await api.subtractQuantities({ category, value1: val1, unit1, value2: val2, unit2, targetUnit });
        setResult(parseFloat(res.data).toFixed(3));
      }
      setIsError(false);
    } catch (err) {
      if (err.response && (err.response.status === 401 || err.response.status === 403)) {
        handleLogout();
      } else {
        setResult("Error");
        setIsError(true);
      }
    }
  };

  if (!token) {
    return (
      <div className="app-root">
        <header className="top-banner">
          <h1>Welcome To Quantity Measurement</h1>
        </header>
        <div className="main-container">
          <section className="auth-box">
            <h2>Sign In Required</h2>
            <p>Please sign in with your Google account to use the tool.</p>
            <a href="http://localhost:8080/oauth2/authorization/google" className="btn google-btn">
                <svg viewBox="0 0 24 24" width="20" height="20" xmlns="http://www.w3.org/2000/svg">
                    <g transform="matrix(1, 0, 0, 1, 27.009001, -39.238998)">
                        <path fill="#4285F4" d="M -3.264 51.509 C -3.264 50.719 -3.334 49.969 -3.454 49.239 L -14.754 49.239 L -14.754 53.749 L -8.284 53.749 C -8.574 55.229 -9.424 56.479 -10.684 57.329 L -10.684 60.329 L -6.824 60.329 C -4.564 58.239 -3.264 55.159 -3.264 51.509 Z"/>
                        <path fill="#34A853" d="M -14.754 63.239 C -11.514 63.239 -8.804 62.159 -6.824 60.329 L -10.684 57.329 C -11.764 58.049 -13.134 58.489 -14.754 58.489 C -17.884 58.489 -20.534 56.369 -21.484 53.529 L -25.464 53.529 L -25.464 56.619 C -23.494 60.539 -19.444 63.239 -14.754 63.239 Z"/>
                        <path fill="#FBBC05" d="M -21.484 53.529 C -21.734 52.809 -21.864 52.039 -21.864 51.239 C -21.864 50.439 -21.724 49.669 -21.484 48.949 L -21.484 45.859 L -25.464 45.859 C -26.284 47.479 -26.754 49.299 -26.754 51.239 C -26.754 53.179 -26.284 54.999 -25.464 56.619 L -21.484 53.529 Z"/>
                        <path fill="#EA4335" d="M -14.754 43.989 C -12.984 43.989 -11.404 44.599 -10.154 45.789 L -6.734 42.369 C -8.804 40.429 -11.514 39.239 -14.754 39.239 C -19.444 39.239 -23.494 41.939 -25.464 45.859 L -21.484 48.949 C -20.534 46.109 -17.884 43.989 -14.754 43.989 Z"/>
                    </g>
                </svg>
                Sign in with Google
            </a>
          </section>
        </div>
      </div>
    );
  }

  const units = categoryUnits[category];
  const showVal2 = action !== 'convert';
  const showOp = action === 'add' || action === 'subtract';
  const showTargetUnit = action !== 'compare';

  return (
    <div className="app-root">
      <header className="top-banner">
        <h1>Welcome To Quantity Measurement</h1>
        <button onClick={handleLogout} className="logout-btn">Log Out</button>
      </header>

      <div className="main-container">
        
        <div className="section-title">CHOOSE TYPE</div>
        <div className="category-cards">
          {[
            { id: 'LENGTH', icon: '📐', label: 'Length' },
            { id: 'WEIGHT', icon: '⚖️', label: 'Weight' },
            { id: 'TEMPERATURE', icon: '🌡️', label: 'Temperature' },
            { id: 'VOLUME', icon: '🥛', label: 'Volume' }
          ].map(c => (
            <div 
              key={c.id}
              className={`card cat-card ${category === c.id ? 'active' : ''}`}
              onClick={() => setCategory(c.id)}
            >
              <div className="icon">{c.icon}</div>
              <span>{c.label}</span>
            </div>
          ))}
        </div>

        <div className="section-title">CHOOSE ACTION</div>
        <div className="action-tabs">
          {[
            { id: 'compare', label: 'Comparison' },
            { id: 'convert', label: 'Conversion' },
            { id: 'add', label: 'Arithmetic' }
          ].map(a => (
            <button 
              key={a.id}
              className={`action-tab ${action === a.id || (a.id === 'add' && action === 'subtract') ? 'active' : ''}`}
              onClick={() => {
                if (category === 'TEMPERATURE' && a.id === 'add') {
                  alert("Arithmetic operations are not supported for Temperature.");
                  return;
                }
                setAction(a.id);
              }}
            >
              {a.label}
            </button>
          ))}
        </div>

        <div className="calculation-area">
          <div className="input-block">
            <label className="val-label">VALUE 1</label>
            <div className="card input-card">
              <input type="number" value={val1} onChange={e => setVal1(e.target.value)} />
              <select className="unit-dropdown" value={unit1} onChange={e => setUnit1(e.target.value)}>
                {units.map(u => <option key={u} value={u}>{formatStr(u)}</option>)}
              </select>
            </div>
          </div>

          {showOp && (
            <div className="operator-block">
              <div className="card op-card" onClick={() => setAction(action === 'add' ? 'subtract' : 'add')}>
                {action === 'add' ? '+' : '-'}
              </div>
            </div>
          )}

          {showVal2 && (
            <div className="input-block">
              <label className="val-label">VALUE 2</label>
              <div className="card input-card">
                <input type="number" value={val2} onChange={e => setVal2(e.target.value)} />
                <select className="unit-dropdown" value={unit2} onChange={e => setUnit2(e.target.value)}>
                  {units.map(u => <option key={u} value={u}>{formatStr(u)}</option>)}
                </select>
              </div>
            </div>
          )}
        </div>

        <div className="result-block">
          <label className="val-label">RESULT</label>
          <div className={`card result-card ${isError ? 'error' : ''}`}>
            <div className="result-value">{result}</div>
            {showTargetUnit && (
              <select className="unit-dropdown" value={targetUnit} onChange={e => setTargetUnit(e.target.value)}>
                {units.map(u => <option key={u} value={u}>{formatStr(u)}</option>)}
              </select>
            )}
          </div>
        </div>

      </div>
    </div>
  );
}

export default App;
