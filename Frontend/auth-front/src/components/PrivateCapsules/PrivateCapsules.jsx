import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./PrivateCapsules.css";
import img from "../../assets/friends.jpg";
import JSZip from "jszip";
import video from "../../assets/20240427_122019.mp4";
// import video from '.src/assets/20240427_122019.mp4"';

const PrivateCapsule = () => {
  const [inputData, setInputData] = useState({
    code: "",
    id: "",
  });

  function createAndDownloadZip() {
    let code = inputData.code;
    const zip = new JSZip();
    zip.file("FIIPractic2024.txt", "Acest concurs a fost foarte interesant!");
    zip.file("20240427_122019.mp4", video);
    // timeout;
    zip.generateAsync({ type: "blob" }).then(function (content) {
      const url = window.URL.createObjectURL(content);
      const link = document.createElement("a");
      link.href = url;
      link.download = code;
      link.click();
      window.URL.revokeObjectURL(url);
    });
  }

  // Call the function to trigger the download
  // downloadZip();

  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setInputData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = () => {
    console.log(inputData);
    alert("Data submitted: " + JSON.stringify(inputData));

    // Decide where to navigate based on some condition
    // Here it's arbitrary; replace with your logic
    if (inputData.code === "unlock") {
      navigate(`/unlocked/${inputData.id}`, {
        state: { title: "Memory Title", description: "Memory Description" },
      });
    } else {
      navigate(`/locked/${inputData.id}`, {
        state: { title: "Memory Title", description: "Memory Description" },
      });
    }
  };

  return (
    <div className="private-capsule-container">
      <div className="image-container">
        <img src={img} alt="Private Capsule" />
      </div>
      <div className="form-container">
        <p>
          Are you prepared to reveal{" "}
          <span className="highlight">some memories</span>?
        </p>
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

        <button onClick={createAndDownloadZip}>Open</button>

        {/* <button onClick={handleSubmit}>Open</button> */}
      </div>
    </div>
  );
};

export default PrivateCapsule;
