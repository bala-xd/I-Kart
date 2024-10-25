import { useState } from 'react';
import { user_uri } from '../Config';
import { Link } from 'react-router-dom';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import axios from 'axios';

function Register() {
    const [user, setUser] = useState({
        "name": "",
        "username": "",
        "email": "",
        "password": "",
        "dob": "",
        "address": "",
        "phone": ""
    });
    const [error, setError] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await axios.post(`${user_uri}/register`, user);
            toast.success(
                <span className='toast'>User registered Successfully!</span>, {
                position: "bottom-right",
                autoClose: 3200,
                hideProgressBar: false,
                closeButton: false,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
                });
        } catch (err) {
            console.log(err);
            setError(err?.response?.data);
        }
    };

    function handleChange(e) {
        const { name, value } = e.target;
        setUser({ ...user, [name]: value });
    }

    return (
        <div className='container'>
            <ToastContainer
            />
            <h2>Register</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-container">
                    <div className="left">
                        <input
                            type="text"
                            placeholder="Username"
                            name="username"
                            onChange={handleChange}
                            required
                        />
                        <input
                            type="email"
                            placeholder="Email"
                            name="email"
                            onChange={handleChange}
                            required
                            disabled
                        />
                        <input
                            type="password"
                            placeholder="Password"
                            name="password"
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="right">
                        <input
                            type="date"
                            placeholder="Date of Birth"
                            name="dob"
                            onChange={handleChange}
                            required
                            disabled
                        />
                        <input
                            type="text"
                            placeholder="Address"
                            name="address"
                            onChange={handleChange}
                            required
                            disabled
                        />
                        <input
                            type="text"
                            placeholder="Phone"
                            name="phone"
                            onChange={handleChange}
                            required
                            disabled
                        />
                    </div>
                </div>
                {error && <span className='err'>{error}</span>}
                <button type="submit">Register</button>
                <span className='user-link'>Already a User? <Link to='/login'>Click here</Link> to Sign In</span>
            </form>
        </div>
    );
}

export default Register;
