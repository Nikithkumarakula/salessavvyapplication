import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import useravatar from './useravatar.png';
import './assets/styles.css';
import './profiledrop.css';

export function ProfileDropdown({ username }) {
  const [isOpen, setIsOpen] = useState(false);
  const [userData, setUserData] = useState(null);
  const [showProfileFrame, setShowProfileFrame] = useState(false);
  const navigate = useNavigate();

  // Toggle dropdown
  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  // Fetch user details from backend
  const handleProfileClick = async () => {
    if (!username) return;

    try {
      const response = await fetch(`http://localhost:9090/api/users/${username}`, {
        method: "GET",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const data = await response.json();
      console.log("User Details:", data);

      // Normalize data keys to match frontend expectations
      setUserData({
        userId: data.USERID,
        username: data.USERNAME,
        email: data.EMAIL,
      });
      setShowProfileFrame(true);
    } catch (error) {
      console.error("Error fetching user details:", error);
    }
  };

  // Handle logout
  const handleLogout = async () => {
    try {
      const response = await fetch('http://localhost:9090/api/auth/logout', {
        method: 'POST',
        credentials: 'include',
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        console.log('User successfully logged out');
        setUserData(null);
        setShowProfileFrame(false);
        navigate('/');
      } else {
        console.error('Failed to log out');
      }
    } catch (error) {
      console.error('Error during logout:', error);
    }
  };

  // Navigate to orders page
  const handleOrdersClick = () => {
    navigate('/orders');
  };

  // Close profile frame
  const closeProfileFrame = () => {
    setShowProfileFrame(false);
  };

  return (
    <div>
      {/* Profile Dropdown */}
      <div className="profile-dropdown">
        <button className="profile-button" onClick={toggleDropdown}>
          <img
            src={useravatar}
            alt="User Avatar"
            className="user-avatar"
            onError={(e) => { e.target.src = 'fallback-logo.png'; }}
          />
          <span className="username">{username || 'Guest'}</span>
        </button>

        {/* Dropdown Menu */}
        {isOpen && (
          <div className="dropdown-menu">
            <button onClick={handleProfileClick}>Profile</button>
            <button onClick={handleOrdersClick}>Orders</button>
            <button onClick={handleLogout}>Logout</button>
          </div>
        )}
      </div>

      {/* Profile Frame (Right Side) */}
      {showProfileFrame && userData && (
        <div className="profile-frame">
          <button className="close-button" onClick={closeProfileFrame}>
            &times;
          </button>
          <h3>User Profile</h3>
          <p><strong>USERID:</strong> {userData.userId}</p>
          <p><strong>USERNAME:</strong> {userData.username}</p>
          <p><strong>EMAIL:</strong> {userData.email}</p>
        </div>
      )}
    </div>
  );
}



