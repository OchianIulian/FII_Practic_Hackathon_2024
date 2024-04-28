import React from "react";
import { NavLink } from "react-router-dom";
import "./Navbar.css";
import searchIcon from "../../assets/search.png";
import logoIcon from "../../assets/iconTimeless.png";


const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-section">
        <img src={logoIcon} alt="Timeless Memories Logo" className="logo" />
      </div>
      <div className="navbar-section search-container">
        <input type="text" placeholder="Search" className="search-input" />
        <img src={searchIcon} alt="Search" className="search-icon" />
      </div>
      <div className="navbar-section nav-links">
        <NavLink to="/private-capsules">Private Capsules</NavLink>
        <NavLink to="/my-capsules">My Capsules</NavLink>
        <NavLink to="/create-capsule">Create Capsule</NavLink>
        <NavLink to="/contact">Contact Us</NavLink>
        <NavLink to="/">Home</NavLink>
      </div>
    </nav>
  );
};

export default Navbar;
