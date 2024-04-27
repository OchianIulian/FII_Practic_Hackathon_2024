import styles from "./ScrollableFeed.module.css";
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHourglassHalf } from "@fortawesome/free-solid-svg-icons";

export const ScrollableFeed = ({ requestType }) => {
  const [capsules, setCapsules] = useState([]);

  // Fetch capsules data from backend based on requestType
  const fetchData = async () => {
    try {
      const response = await fetch(
        `https://your-api-url.com/api/capsules/${requestType}`
      );
      const data = await response.json();
      setCapsules(data);
    } catch (error) {
      console.error("Failed to fetch capsules:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [requestType]);

  if (!capsules.length) {
    return <div className={styles.feed}>Loading capsules...</div>;
  }

  const unlocked = {
    display: "none",
  };

  return (
    <div className={styles.feed}>
      {capsules.map((capsule) => (
        <Link
          key={capsule.id}
          to={{
            pathname: `/unlocked/${capsule.id}`,
            state: {
              title: capsule.title,
              description: capsule.description,
            },
          }}
          className={styles.capsuleCard}
        >
          <div className={styles.cardContent}>
            <div style={1 ? null : { display: "none" }}>
              <img
                className={styles.icon}
                src="src\assets\icons\very-good-hourglass.svg"
              ></img>
            </div>
            <h2 className={styles.title}>{capsule.title}</h2>
            <p className={styles.description}>{capsule.description}</p>
          </div>
        </Link>
      ))}
    </div>
  );
};

export default ScrollableFeed;
