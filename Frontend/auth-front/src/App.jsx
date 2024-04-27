import React from 'react'
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar/Navbar";
import CreateCapsule from './components/CreateCapsule/CreateCapsule';
import Contact from './components/Contact/Contact';
import PrivateCapsules from './components/PrivateCapsules/PrivateCapsules';
import MyCapsules from './components/MyCapsules/MyCapsules';
import Home from './components/Home/Home';
import Login from "./components/Login/Login";

function App() {
  return (
    <Router>
      <Navbar/>
      <main className="main-content">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/create-capsule" element={<CreateCapsule />} />
          <Route path="/private-capsules" element={<PrivateCapsules />} />
          <Route path="/my-capsules" element={<MyCapsules />} />
          <Route path="/login" element={<Login/>}/>
        </Routes>
      </main>
      {}
    </Router>
  );
}

export default App;
