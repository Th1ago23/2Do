import React from 'react';
import './Home.css';
import LogoGif from '../img/neon.gif'
import { useNavigate } from 'react-router-dom';

const Home = () => {
const navigate = useNavigate();
  return (
    <div className="home-container">
      <div className="logo">
        <img src={LogoGif} alt="2Do Logo" className="logo-image" />
      </div>
      <h1 className="home-title">Organize o seu dia.</h1>
      <div className="buttons-container">
        <button className="home-button register-button" onClick={() => navigate('/register')}>Registrar</button>
        <button className="home-button login-button" onClick={() => navigate('/login')}>Entrar</button>
      </div>
    </div>
  );
};

export default Home;
