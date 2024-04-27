import React from "react";
import styles from "./MyCapsules.module.css";
import { ScrollableFeed } from "../ScrollableFeed/ScrollableFeed";

export const MyCapsules = () => {
  return (
    <>
      <div className={styles.mainLoginContainer}>
        <h2 className={styles.header}> Previously created capsules</h2>

        <ScrollableFeed requestType={"myCapsules"}></ScrollableFeed>
      </div>
    </>
  );
};
