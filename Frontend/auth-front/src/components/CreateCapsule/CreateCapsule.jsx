import React, { useState, useRef } from 'react';
import axios from 'axios';
import "./CreateCapsule.css"
import CapsulePreview from './CapsulePreview';

const CreateCapsule = () => {
  const [capsuleData, setCapsuleData] = useState({
    title: '',
    description: '',
    canBeOpenedAt: '',
    isPrivate: true,
    coverImage: '',
    png: null,
    video: null,
    textFile: null,
  });

  const pngRef = useRef(null);
  const textFileRef = useRef(null);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCapsuleData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleFileChange = (e) => {
    const { name } = e.target;
    const file = e.target.files[0];
    setCapsuleData((prevData) => ({
      ...prevData,
      [name]: file,
    }));
  };

  const triggerFileInput = (fileInputRef) => {
    fileInputRef.current.click();
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append('title', capsuleData.title);
    formData.append('description', capsuleData.description);
    formData.append('canBeOpenedAt', capsuleData.canBeOpenedAt);
    formData.append('isPrivate', capsuleData.isPrivate);
    
    
    if (capsuleData.png) {
      formData.append('images', capsuleData.png); 
    }
    if (capsuleData.video) {
      formData.append('videos', capsuleData.video); 
    }
    if (capsuleData.textFile) {
      formData.append('textFiles', capsuleData.textFile); 
    }

    axios.post('/', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    .then(response => {
      console.log(response.data);
    })
    .catch(error => {
      console.error('There was an error!', error);
    });
  };

  const [showVideoPopup, setShowVideoPopup] = useState(false);

  const handleVideoClick = () => {
    setShowVideoPopup(true); 
  };

  const closeVideoPopup = () => {
    setShowVideoPopup(false); 
  };
  return (
    <div className="create-capsule">
      <CapsulePreview data={capsuleData} />
      <div className="capsule-form-container">
        <form className="capsule-form" onSubmit={handleSubmit}>
          <input
            type="text"
            name="title"
            placeholder="Add title"
            value={capsuleData.title}
            onChange={handleInputChange}
          />
          <textarea
            name="description"
            placeholder="Add a description..."
            value={capsuleData.description}
            onChange={handleInputChange}
          />
          <input
            type="date"
            name="dateToOpen"
            value={capsuleData.dateToOpen}
            onChange={handleInputChange}
          />
          <div id="checkboxDiv" >
          <label>
            Make private</label>
            <input id='checkbox'
              type="checkbox"
              name="isPrivate"
              checked={capsuleData.isPrivate}
              onChange={(e) => setCapsuleData((prevData) => ({ ...prevData, isPrivate: e.target.checked }))}
            />
          </div>
          <input
            type="file"
            name="coverImage"
            onChange={handleFileChange}
          />
          <button type="button" onClick={() => triggerFileInput(pngRef)}>
            Add Png to Capsule
          </button>
          <input
            type="file"
            ref={pngRef}
            name="png"
            accept="image/png"
            onChange={handleFileChange}
            style={{ display: 'none' }} 
          />
          
          <button type="button" onClick={handleVideoClick}>
          Add Video to Capsule
        </button>

          <button type="button" onClick={() => triggerFileInput(textFileRef)}>
            Add Text File to Capsule
          </button>
          <input
            type="file"
            ref={textFileRef}
            name="textFile"
            accept="text/plain"
            onChange={handleFileChange}
            style={{ display: 'none' }}
          />
          {showVideoPopup && (
          <div className="video-popup">
            <div className="video-popup-content">
              <h2>Premium Feature</h2>
              <p>Adding videos is a premium feature.<br/> Please go to payment page to access this feature.</p>
              <button onClick={closeVideoPopup}>Close</button>
            </div>
          </div>
        )}

          <button type="submit">Create Capsule</button>
        </form>
      </div>
    </div>
  );

}

export default CreateCapsule;





