import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './components/common/Header';
import Login from './components/Login';
import Register from './components/Register';
import Home from './components/Home/Home'
import Profile from './components/Profile';
import Product from './components/Product/Product';
import Cart from './components/Cart/Cart';

function App() {
    return (
        <Router>
            <Header />
            <Routes>
                <Route path="/" element={<Home/>} />
                <Route path="login" element={<Login />} />
                <Route path="register" element={<Register />} /> 
                <Route path="profile" element={<Profile/>} />
                <Route path="product" element={<Product/>}/>
                <Route path="Cart" element={<Cart/>}/>
            </Routes>
        </Router>
    );
}

export default App;
