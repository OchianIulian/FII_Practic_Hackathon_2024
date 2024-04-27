import React, { useState } from 'react';
import './PrivateCapsules.css'; 
import img from '../../assets/friends.jpg';

const PrivateCapsule = () => {
  const [inputData, setInputData] = useState({
    code: '',
    id: ''
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setInputData(prevData => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleSubmit = () => {
    console.log(inputData);
    alert('Data submitted: ' + JSON.stringify(inputData));
  };

  return (
    <div className="private-capsule-container">
      <div className="image-container">
        <img src={img} alt="Private Capsule" />
      </div>
      <div className="form-container">
        <p>Are you prepared to reveal <span className="highlight">some memories</span>?</p>
        <input
          type="text"
          name="code"
          value={inputData.code}
          onChange={handleInputChange}
          placeholder="Enter code here..."
        />
        <input
          type="text"
          name="id"
          value={inputData.id}
          onChange={handleInputChange}
          placeholder="Enter ID here..."
        />
        <button onClick={handleSubmit}>Submit</button>
      </div>
    </div>
  );
};

export default PrivateCapsule;
