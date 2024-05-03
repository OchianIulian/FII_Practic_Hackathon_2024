import styles from "./ScrollableFeed.module.css";
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHourglassHalf } from "@fortawesome/free-solid-svg-icons";
import fetchCapsules from "../../../getcapsulesmock";

export const ScrollableFeed = ({ requestType }) => {
  const [capsules, setCapsules] = useState([]);

  // Fetch capsules data from backend based on requestType
  // const fetchData = async () => {
  //   try {
  //     const response = await fetch(
  //       `https://your-api-url.com/api/capsules/${requestType}`
  //     );
  //     const data = await response.json();
  //     setCapsules(data);
  //   } catch (error) {
  //     console.error("Failed to fetch capsules:", error);
  //   }
  // };

  // useEffect(() => {
  //   fetchData();
  // }, [requestType]);

  const fetchDataMock = async () => {
    const response = await fetchCapsules();
    setCapsules(response);
    console.log(response);
  };

  useEffect(() => {
    fetchDataMock();
  }, []);

  if (!capsules.length) {
    return <div className={styles.feed}>Loading capsules...</div>;
  }

  const unlocked = {
    display: "hidden",
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
          style={{
            backgroundImage: `url(${capsule.image})`,
          }}
        >
          <div className={styles.cardContent}>
            <div style={capsule.unlocked ? { display: "none " } : null}>
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
