import { useState } from 'react';
import axios from 'axios';
import {useNavigate} from 'react-router-dom';
import { login_uri } from '../Config';
import Auth from '../modules/Auth';

function Login() {
    const [user,setUser] = useState({
        "email": "",
        "password": ""
    });
    const [error, setError] = useState(null);

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        try {
            console.log(`${login_uri}/login`);
            const res = await axios.put(`${login_uri}/login`, user);
            Auth.setUserToken(res);
            navigate('/'); 
            window.location.reload();
        } catch (err) {
            console.log(err);
            setError(err.response.data);
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
                    type="email"
                    placeholder="Email"
                    name="email"
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
            </form>
        </div>
    );
}

export default Login;