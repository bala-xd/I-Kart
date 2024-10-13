import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { login_uri, cart_uri } from '../Config';

function Register() {
    const [user, setUser] = useState({
        "name": "",
        "email": "",
        "password": "",
        "dob": "",
        "address": "",
        "phone": ""
    });
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const res = await axios.post(`${login_uri}/register`, user);
            await axios.post(`${cart_uri}/new-cart/${res.data.userId}`);
            console.log(res);
            navigate('/profile'); 
        } catch (err) {
            console.log(err);
            setError(err.response.data);
        }
    };

    function handleChange(e) {
        const { name, value } = e.target;
        setUser({ ...user, [name]: value });
    }

    return (
        <div className='container'>
            <h2>Register</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-container">
                    <div className="left">
                        <input
                            type="text"
                            placeholder="Name"
                            name="name"
                            onChange={handleChange}
                            required
                        />
                        <input
                            type="email"
                            placeholder="Email"
                            name="email"
                            onChange={handleChange}
                            required
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
                        />
                        <input
                            type="text"
                            placeholder="Address"
                            name="address"
                            onChange={handleChange}
                            required
                        />
                        <input
                            type="text"
                            placeholder="Phone"
                            name="phone"
                            onChange={handleChange}
                            required
                        />
                    </div>
                </div>
                {error && <span className='err'>{error}</span>}
                <button type="submit">Register</button>
            </form>
        </div>
    );
}

export default Register;
