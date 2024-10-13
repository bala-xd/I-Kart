import { Link, useNavigate } from 'react-router-dom';
import './Header.css';
import { useEffect, useState } from 'react';
import { login_uri } from '../../Config';
import axios from 'axios';

function Header() {
    const [user, setUser] = useState();

    useEffect(()=>{
        let token = localStorage.getItem("auth");
        token = JSON.parse(token)?.data;
        if (token) setUser(token);
    }, [])

    function logout() {
        axios.put(`${login_uri}/logout`, user)
        .catch((err)=>console.log(err));
        localStorage.removeItem("auth");
        setUser({});
        window.location.reload();
    }

    const navigate = useNavigate();
    return (
        <div className='menu'>
            <h2 className='title' onClick={()=>navigate('/')}>I-Kart</h2>
            <nav>
                <ul>
                    <li><Link to="/">Home</Link></li>
                    <li><Link to="/profile">Profile</Link></li>
                    <li><Link to="/product">Product</Link></li>
                    <li><Link to="/cart">Cart</Link></li>
                    { user ? 
                        <li onClick={logout}><Link to="/">Logout</Link></li>
                        :
                        <li><Link to="/login">Login</Link></li>
                    }
                    <li><Link to="/register">Register</Link></li>
                </ul>
            </nav>
        </div>
    );
}

export default Header;