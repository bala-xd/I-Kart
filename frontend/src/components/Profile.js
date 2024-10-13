import React, { useEffect, useState } from 'react';
import '../styles/Profile.css'; 

const Profile = () => {
  const [userData, setUserData] = useState();

  useEffect(()=>{
    var user = localStorage.getItem("auth");
    user = JSON.parse(user)?.data;
    console.log(user)
    setUserData(user);
  },[])

  return (
    <div className="profile-container">
      { userData ? 
      <div className="profile-card">
        <h2>User Profile</h2>
        <p><strong>Username:</strong> {userData.name}</p>
        <p><strong>User ID:</strong> {userData.userId}</p>
        <p><strong>Email:</strong> {userData.email}</p>
        <p><strong>Date of Birth:</strong> {new Date(userData.dob).toLocaleDateString()}</p>
        <p><strong>Address:</strong> {userData.address}</p>
        <p><strong>Phone:</strong> {userData.phone}</p>
      </div> :
      <h2>You must login to view your profile!</h2>}
    </div>
  );
};

export default Profile;