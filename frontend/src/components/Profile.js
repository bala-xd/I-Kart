import React, { useEffect, useState } from 'react';
import '../styles/Profile.css'; 
import auth from '../modules/Auth';
import { jwtDecode } from 'jwt-decode';

const Profile = () => {
  const [userData, setUserData] = useState();

  useEffect(() => {
    let token = auth.getToken();
    if (token.length>0) {
      try {
        var user = jwtDecode(token);
        setUserData(user);
      } catch (error) {
        console.log(error);
      }
    }
  }, []);
  
  return (
    <div className="profile-container">
      { userData ? (
        <div className="profile-card">
          <h2>User Profile</h2>
          <p><strong>Username:</strong> {userData.sub}</p>
          <p><strong>Email:</strong> {userData.email}</p>
          <p><strong>Date of Birth:</strong> {new Date(userData.dob).toLocaleDateString()}</p>
          <p><strong>Address:</strong> {userData.address}</p>
          <p><strong>Phone:</strong> {userData.phone}</p>
        </div>
      ) : (
        <h2>You must login to view your profile!</h2>
      )}
    </div>
  );  
};

export default Profile;