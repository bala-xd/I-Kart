import React, { useEffect, useState } from 'react';
import axios from 'axios'; // Don't forget to import axios
import { product_uri, cart_uri } from '../../Config';
import './ProductListing.css';
import { jwtDecode } from 'jwt-decode';
import auth from '../../modules/Auth';

function ProductListing() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const res = await axios.get(`${product_uri}/products`); // Fix the URL
                setProducts(res.data); // Update to set the data correctly
            } catch (err) {
                console.log(err);
            }
        };

        fetchProducts();
    }, []);

    async function addProduct(prod_id) {
        var token = auth.getToken();

        if (token.length>0) {
            var user = jwtDecode(token);
            try {
                const cart = await axios.post(`${cart_uri}/add-item/${user.id}/${prod_id}/1`);
                console.log(cart);
            } catch(err) {
                console.log(err);
            }
        } else {
            window.location.href='/login';
        }
    }

    return (
        <div className='products'>
            <h1>Products</h1>
            {products && products.length > 0 ? (
                <div className='card-container'>
                    {products.map((p, i) => (
                        <div className='card' key={i}>
                            <h2>{p.name}</h2>
                            <p>{p.description}</p>
                            <p><strong>Price:</strong> Rs.{p.price.toFixed(2)} /-</p>
                            <p><strong>Description:</strong> {p.description}</p>
                            <p><strong>Stock:</strong> {p.stock}</p>
                            <p><strong>Category:</strong> {p.category}</p>
                            <button onClick={()=>addProduct(p.id)}>Add to Cart</button>
                        </div>
                    ))}
                </div>
            ) : (
                <p>No Products Available!</p>
            )}
        </div>
    );  
}  

export default ProductListing;
