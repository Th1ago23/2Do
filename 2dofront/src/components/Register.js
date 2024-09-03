import React, { useState } from 'react';
import axios from 'axios';
import './Register.css';

function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!username || !password || !name || !email || !phone) {
      setError('All fields are required');
      return;
    }

    if (password.length < 6) {
      setError('Password must be at least 6 characters long');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/users/register', { username, password, name, email, phone });

      if (response.status === 200) {
        console.log('Registration successful:', response.data);
        window.location.href = '/login';
      } else {
        if (response.data.message === 'Email already exists') {
          setError('Email já está em uso!!');
          return;
        } else {
          setError('Registro falhou, por favor tente novamente.');
        }
      }
    } catch (err) {
        setError('Falha no registro. Verifique a conexão ou tente novamente.');     
    }
  };

  return (
    <div className="register-container">
      <form className="register-form" onSubmit={handleSubmit}>
        {error && <div className="error-message">{error}</div>}
        
        <h1>Register</h1> 
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
        <input
          type="text"
          placeholder="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="text"
          placeholder="Phone"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
        />
        <a href="/login">Already have an account? Login here</a>
        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default Register;
