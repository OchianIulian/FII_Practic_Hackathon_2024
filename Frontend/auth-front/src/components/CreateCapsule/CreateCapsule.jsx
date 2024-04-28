import React, { useState, useRef } from 'react';
import axios from 'axios';
import "./CreateCapsule.css"
import CapsulePreview from './CapsulePreview';

const CreateCapsule = () => {
  const [capsuleData, setCapsuleData] = useState({
    userId: '',
    title: '',
    description: '',
    canBeOpenedAt: '',
    isPrivate: true,
    coverImage: '',
    png: null,
    video: null,
    textFiles: [], // Initialize as an empty array
    pictures: [], // Initialize as an empty array
    videos: [], // Initialize as an empty array
  });

  const pngRef = useRef(null);
  const textFileRef = useRef(null);
  const videoRef = useRef(null);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCapsuleData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleFileChange = (e) => {
    const { name } = e.target;
    const files = e.target.files;
    setCapsuleData((prevData) => ({
      ...prevData,
      [name]: [...prevData[name], ...files],
    }));
  };

  const triggerFileInput = (fileInputRef) => {
    fileInputRef.current.click();
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = new FormData();

    formData.append('userId', 1); // Replace '1' with the variable holding the mock userId
    formData.append('title', capsuleData.title);
    formData.append('description', capsuleData.description);
    formData.append('canBeOpenedAt', capsuleData.canBeOpenedAt);
    // Convert boolean to string before appending
    formData.append('isPrivate', capsuleData.isPrivate.toString());
    
      // Append all files
  capsuleData.textFiles.forEach((file) => {
    formData.append('textFiles', file);
  });
  capsuleData.pictures.forEach((file) => {
    formData.append('pictures', file);
  });
  capsuleData.videos.forEach((file) => {
    formData.append('videos', file);
  });

    axios.post('http://localhost:8080/api/saveData', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }, withCredentials: true
    })
    .then(response => {
      console.log(response.data);
    })
    .catch(error => {
      if (error.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        console.error('Server responded with error status:', error.response.status);
        console.error('Error message from server:', error.response.data);
      } else if (error.request) {
        // The request was made but no response was received
        console.error('No response received from server:', error.request);
      } else {
        // Something happened in setting up the request that triggered an Error
        console.error('Error setting up the request:', error.message);
      }
    });
  };

  // const [showVideoPopup, setShowVideoPopup] = useState(false);

  // const handleVideoClick = () => {
  //   videoRef.current.click();
  // };

  {/*const closeVideoPopup = () => {
    setShowVideoPopup(false); 
  };*/}
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
          <input
            name="description"
            placeholder="Add a description..."
            value={capsuleData.description}
            onChange={handleInputChange}
          />
          <input
            type="text"
            name="canBeOpenedAt"
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
          {/* <input
            type="file"
            name="coverImage"
            onChange={handleFileChange}
          /> */}
          <button type="button" onClick={() => triggerFileInput(pngRef)}>
            Add Png to Capsule
          </button>
          <input
            type="file"
            ref={pngRef}
            name="pictures"
            accept="image/png"
            onChange={handleFileChange}
            style={{ display: 'none' }} 
          />

          <button type="button" onClick={() => triggerFileInput(textFileRef)}>
            Add Text File to Capsule
          </button>
          <input
            type="file"
            ref={textFileRef}
            name="textFiles"
            accept="text/plain"
            onChange={handleFileChange}
            style={{ display: 'none' }}
          />

            <button type="button" onClick={() => triggerFileInput(videoRef)}>
              Add Video to Capsule
            </button>
            <input
              type="file"
              ref={videoRef}
              name="videos"
              accept="video/*"
              onChange={handleFileChange}
              style={{ display: 'none' }}
            />
          {/*{showVideoPopup && (
          <div className="video-popup">
            <div className="video-popup-content">
              <h2>Premium Feature</h2>
              <p>Adding videos is a premium feature.<br/> Please go to payment page to access this feature.</p>
              <button onClick={closeVideoPopup}>Close</button>
            </div>
          </div>*/}
                
          <input
            type="hidden"
            name="userId"
            value={1} // Replace 'userId' with the variable holding the mock userId
          />
          <button type="submit">Create Capsule</button>
        </form>
      </div>
    </div>
  );

}

export default CreateCapsule;





