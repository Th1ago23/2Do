import React, { useState } from 'react';
import axios from 'axios';
import './Login.css';
import 'typeface-jetbrains-mono';
import 'typeface-poppins'


function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/users/login', { username, password });
      if (response.status === "Login sucsessful") {

        console.log('Login successful:', response.data);
        window.location.href = '/home';
      }

      if (response.status === 401) {
        setError('Invalid username or password');
        return;
      }
    
    } catch (err) {
      setError('Login failed');
    }
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleSubmit}>
        {error && <div className="error-message">{error}</div>}

        
        <h1>Log in</h1>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <a href="/register">Don't have an account? Register here </a> 
        <button type="submit">Log in</button>
      </form>
    </div>
  );
}

export default Login;
