import React from "react";
import styles from "./Login.module.css";
import { ScrollableFeed } from "../ScrollableFeed/ScrollableFeed";

function Login() {
  return (
    <div className={styles.mainLoginContainer}>
      <div className={styles.upperContainer}>
        <div className={styles.leftContainer}>
          <h1 className={styles.formSigninHeading}>
            We help you share &nbsp;
            <span>your memories</span>
            &nbsp; with your beloved ones!
          </h1>
          <h4>
            Join us and transform your experiences<br></br> into a timeless
            story.
          </h4>
          <table className={styles.tableButtons}>
            <tbody>
              <tr>
                <td>
                  <a
                    className={styles.links}
                    href="http://localhost:8080/oauth2/authorization/google"
                  >
                    <button>GitHub</button>
                  </a>
                </td>
                <td>
                  <a
                    className={styles.links}
                    href="http://localhost:8080/oauth2/authorization/google"
                  >
                    <button>Gmail</button>
                  </a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <img
          className={styles.rightImage}
          alt="Happy friends looking at a laptop"
          src="src\assets\page images\login-people.jpg"
        ></img>
      </div>
      <ScrollableFeed requestType={"publicCapsules"}></ScrollableFeed>
    </div>
  );
}

export default Login;
