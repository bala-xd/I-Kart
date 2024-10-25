import React, { useEffect, useState } from 'react';
import { product_uri, cart_uri } from '../../Config';
import './ProductListing.css';
import { jwtDecode } from 'jwt-decode';
import auth from '../../modules/Auth';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import api from '../../modules/Auth/api';

function ProductListing() {
    const [products, setProducts] = useState([]);

    const notify = (productName) => toast.success(
        <span className='toast'>{productName} is added to your cart!</span>, {
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
                const res = await api.get(`${product_uri}/products`);
                setProducts(res.data);
            } catch (err) {
                console.log(err);
            }
        };

        fetchProducts();
    }, []);

    const addProduct = async (prod_id, productName) => {
        const token = auth.getToken();

        if (token.length>0) {
            const user = jwtDecode(token);
            try {
                await api.post(`${cart_uri}/add-item/${user.id}/${prod_id}/1`);
                notify(productName);
            } catch (err) {
                console.log(err);
            }
        } else {
            window.location.href = '/login';
        }
    };

    return (
        <div className='products'>
            <ToastContainer />
            <h1>Products</h1>
            {products && products.length > 0 ? (
                <div className='card-container'>
                    {products.map((p, i) => (
                        <div className='card' key={i}>
                            <h2>{p.name}</h2>
                            <p>{p.description}</p>
                            <p><strong>Price:</strong> Rs.{p.price.toFixed(2)} /-</p>
                            <p><strong>Stock:</strong> {p.stock}</p>
                            <p><strong>Category:</strong> {p.category}</p>
                            <button onClick={() => addProduct(p.id, p.name)}>Add to Cart</button>
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
