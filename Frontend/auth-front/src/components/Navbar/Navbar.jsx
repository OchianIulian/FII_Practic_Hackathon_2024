import React from "react";
import { NavLink } from "react-router-dom";
import "./Navbar.css";
import search from "../../assets/search.png"

const Navbar = () => {
 return (
  <nav>
   <ul className="nav__list">
     <div className="nav-middle flex-div">
        <div className='search-box flex-div'>
            <input type="text" placeholder='Search'/>
            <img src={search} alt=""/>
        </div>
      </div>
     <li className="nav__item">
       <NavLink to="/private-capsules" className="nav__link">
         Private Capsules
       </NavLink>
     </li>
     <li className="nav__item">
       <NavLink to="/my-capsules" className="nav__link">
         My capsules
       </NavLink>
     </li>
     <li className="nav__item">
       <NavLink to="/create-capsule" className="nav__link">
         Create Capsule
       </NavLink>
     </li>
     <li className="nav__item">
       <NavLink to="/contact" className="nav__link">
         Contact Us
       </NavLink>
     </li>
     <li className="nav__item">
       <NavLink to="/" className="nav__link">
         Home
       </NavLink>
     </li>
   </ul>
   </nav>
 );
};

export default Navbar;
