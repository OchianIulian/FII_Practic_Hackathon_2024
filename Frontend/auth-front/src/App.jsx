import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import Login from "./components/Login/Login";
import { Route, Routes } from "react-router-dom";
import { MyCapsules } from "./components/MyCapsules/MyCapsules";
import { Home } from "./components/Home/Home";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      {/* <Navbar></Navbar> */}
      <Routes>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/mycapsules" element={<MyCapsules />}></Route>
        <Route path="/home" element={<Home />}></Route>
      </Routes>
    </>
  );
}

export default App;
