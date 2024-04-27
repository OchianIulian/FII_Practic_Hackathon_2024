import React from "react";
import styles from "./Home.module.css";
import { ScrollableFeed } from "../ScrollableFeed/ScrollableFeed";

export const Home = () => {
  return (
    <>
      <div className={styles.mainLoginContainer}>
        <h2 className={styles.header}>
          {" "}
          Step into the past and relive <span>cherished memories</span> shared
          by our community
        </h2>

        <ScrollableFeed requestType={"publicCapsules"}></ScrollableFeed>
      </div>
    </>
  );
};
