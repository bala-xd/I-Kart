import React, { useEffect, useState } from 'react';
import axios from 'axios'; // Don't forget to import axios
import { product_uri, cart_uri } from '../../Config';
import './ProductListing.css';
import { jwtDecode } from 'jwt-decode';
import auth from '../../modules/Auth';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function ProductListing() {
    const [products, setProducts] = useState([]);

    const notify = (productName) => toast.success(`${productName} is added to your cart!`, {
        position: "top-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeButton: false,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
        });

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

    async function addProduct(prod_id, productName) {
        var token = auth.getToken();

        if (token.length>0) {
            var user = jwtDecode(token);
            try {
                await axios.post(`${cart_uri}/add-item/${user.id}/${prod_id}/1`);
                notify(productName);
            } catch(err) {
                console.log(err);
            }
        } else {
            window.location.href='/login';
        }
    }

    return (
        <div className='products'>
            <ToastContainer/>
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
                            <button onClick={()=>addProduct(p.id,p.name)}>Add to Cart</button>
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
