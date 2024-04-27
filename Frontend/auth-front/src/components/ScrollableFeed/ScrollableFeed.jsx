import React from "react";
import { useState } from "react";
import styles from "./ScrollableFeed.module.css";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect } from "react";

export const ScrollableFeed = ({ requestType }) => {
  const [publicCapsules, setPublicCapsules] = useState([]);

  const fetchData = async () => {
    if (requestType === "publicCapsules") {
      // const response = await getVideos();
      // setPublicCapsules(response);
      // console.log(response);
    } else if (requestType === "myCapsules") {
      // const response = await getVideos();
      // setPublicCapsules(response);
      // console.log(response);
    } else if (requestType === "myPrivateCapsules") {
      // const response = await getVideos();
      // setPublicCapsules(response);
      // console.log(response);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const unlocked = {
    display: "none",
  };

  return (
    <div className={styles.feed}>
      <Link key="id" to="#" className={styles.capsuleCard}>
        <div style={1 ? null : { display: "none" }}>
          <img
            className={styles.icon}
            src="src\assets\icons\very-good-hourglass.svg"
          ></img>
        </div>
        <h2 className={styles.title}> Titlu</h2>
        <p className={styles.description}>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugit dolorum
          ipsa,
        </p>{" "}
      </Link>

      {/* {publicCapsules.length ? (
        publicCapsules.map((capsule) => (
          <Link
            key={capsule.id}
            to={`/${capsule.id}`}
            className={styles.capsuleCard}
          >
           <div style={1 ? null : { display: "none" }}>
          <img
            className={styles.icon}
            src="src\assets\icons\very-good-hourglass.svg"
          ></img>
        </div>
            <h2 className={styles.title}> {capsule.title}</h2>
            <p className={styles.description}>{capsule.description}</p>
          </Link>
        ))
      ) : (
        <div> </div>
      )} */}
    </div>
  );
};
