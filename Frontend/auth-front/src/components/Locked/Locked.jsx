import React from 'react';
import { useLocation } from 'react-router-dom';
import "./Locked.css";

const Locked = () => {
  const location = useLocation();
  const { title, description } = location.state || { title: 'Default Title', description: 'Default Description' };

  const handlePayment = () => {
    console.log("Payment initiated for: ", title);
    alert("Initiating payment...");
  };

  return (
    <div className="locked-container">
      <div className="content">
        <h1>{title}</h1>
        <p>{description}</p>
      </div>
      <div className="payment-section">
        <p>Are you ready to secure these memories?</p>
        <button onClick={handlePayment}>Pay</button>
      </div>
    </div>
  );
};

export default Locked;
