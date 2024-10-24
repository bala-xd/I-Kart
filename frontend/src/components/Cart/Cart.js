import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { cart_uri } from '../../Config';
import './Cart.css';
import { FaSquarePlus, FaSquareMinus } from "react-icons/fa6";
import { RiDeleteBin2Fill } from "react-icons/ri";
import { jwtDecode } from 'jwt-decode';
import auth from '../../modules/Auth';

function Cart() {
  const [cart, setCart] = useState({});
  const [user, setUser] = useState();

  useEffect(() => {
    const token = auth.getToken();
    if (token.length > 0) {
      const user = jwtDecode(token);
      setUser(user);
      fetchCart(user?.id);
    }
  }, []);

  const fetchCart = async (userId) => {
    try {
      const response = await axios.get(`${cart_uri}/cart/${userId}`);
      setCart(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const updateCart = async (cartId) => {
    try {
      const response = await axios.get(`${cart_uri}/cart/${cartId}`);
      setCart(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  async function incrementProduct(prod_id) {
    try {
      await axios.post(`${cart_uri}/add-item/${cart.cartId}/${prod_id}/1`);
      updateCart(cart.cartId); // Re-fetch the updated cart
    } catch (err) {
      console.log(err);
    }
  }

  async function decrementProduct(prod_id) {
    try {
      await axios.post(`${cart_uri}/add-item/${cart.cartId}/${prod_id}/-1`);
      updateCart(cart.cartId); // Re-fetch the updated cart
    } catch (err) {
      console.log(err);
    }
  }

  async function removeProduct(prod_id) {
    try {
      await axios.delete(`${cart_uri}/delete-item/${cart.cartId}/${prod_id}`);
      updateCart(cart.cartId); // Re-fetch the updated cart
    } catch (err) {
      console.log(err);
    }
  }

  return (
    <div className='cart-page'>
      {cart ? 
        <div>
          <h1>Cart Details</h1>
          <div className='cart-summary'>
            <p><strong>Customer Name:</strong> {user?.sub}</p>
          </div>

          <h2>Items in Cart</h2>
          <div className='cart-items'>
            {cart.items && cart.items.length > 0 ? (
              <div>
                <ul>
                  {cart.items.map((item, index) => (
                    <li key={index} className='cart-item'>
                      <p><strong>Product Name:</strong> {item.productName}</p>
                      <p className='qty-box'>
                        <strong>Quantity:</strong> 
                        <span className='icon' onClick={() => incrementProduct(item.productId)}><FaSquarePlus/></span> 
                        {item.quantity} 
                        <span className='icon' onClick={() => decrementProduct(item.productId)}><FaSquareMinus/></span>
                        <span className='icon' onClick={() => removeProduct(item.productId)}><RiDeleteBin2Fill/></span>
                      </p>
                    </li>
                  ))}
                </ul>
                <div className='cart-total'>
                  <h3>Total Price: Rs.{cart.totalPrice.toFixed(2)} /-</h3>
                </div>
                <button>Place order</button>
              </div>
            ) : (
              <p>No items in the cart.</p>
            )}
          </div>
        </div>
      : <h2>You must login to view this page!</h2>}
    </div>
  );
};

export default Cart;