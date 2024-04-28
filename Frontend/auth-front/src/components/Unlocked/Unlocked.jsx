import React from 'react';
import { useLocation } from 'react-router-dom';
import "./Unlocked.css";  

const Unlocked = () => {
  const location = useLocation();
  // Extracting capsule_id and token from location.state
  const { id, token, title, description } = location.state || { 
    id: '1', 
    token: '2', 
    title: 'Default Title', 
    description: 'Default Description' 
  };

  const handleDownload = async () => {
    try {
      // Constructing the URL dynamically based on the capsule_id and token
      const response = await fetch(`http://localhost:8080/capsule/get-capsule/${id}/${token}`, { method: 'GET' });
      if (response.ok) {
        const blob = await response.blob();
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `${title.replace(/ /g, '_')}.zip`; 
        document.body.appendChild(a);
        a.click();
        a.remove();
        window.URL.revokeObjectURL(url);
      } else {
        throw new Error('Failed to download the file');
      }
    } catch (error) {
      console.error('Download error:', error);
      alert('Error downloading file: ' + error.message);
    }
  };

  return (
    <div className="unlocked-container">
      <h1>{title}</h1>
      <p>{description}</p>
      <button onClick={handleDownload}>Download Memories</button>
      <div className="reminder">
        <p>Are you ready to open memories?</p>
      </div>
    </div>
  );
};

export default Unlocked;
