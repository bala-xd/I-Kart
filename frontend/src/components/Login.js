import { useState } from 'react';
import axios from 'axios';
import { user_uri } from '../Config';
import auth from '../modules/Auth';
import { Link, useNavigate } from 'react-router-dom';

function Login() {
    const [user,setUser] = useState({
        "username": "",
        "password": ""
    });
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        try {
            const token = await axios.post(`${user_uri}/login`, user);
            auth.setUserToken(token.data);
            navigate('/');
            window.location.reload();
        } catch (err) {
            console.log(err);
            setError("Invalid Username or Password!");
        }
        
    };

    function handleChange(e) {
        const {name,value} = e.target;
        setUser({...user,[name]:value});
    }

    return (
        <div className='container'>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Username"
                    name="username"
                    onChange={(e) => handleChange(e)}
                    required
                />
                <input
                    type="password"
                    placeholder="Password"
                    name="password"
                    onChange={(e) => handleChange(e)}
                    required
                />
                { error && <span className='err'>{error}</span>}
                <button type="submit">Login</button>
                <span className='user-link'>New User? <Link to='/register'>Click here</Link> to Register</span>
            </form>
        </div>
    );
}

export default Login;