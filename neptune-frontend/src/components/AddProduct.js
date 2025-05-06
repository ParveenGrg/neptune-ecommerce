import React, { useState } from 'react';
import axios from 'axios';

const AddProduct = () => {
  const [product, setProduct] = useState({
    name: '',
    description: '',
    price: ''
  });

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('https://<your-backend-url>/products', {
        ...product,
        price: parseFloat(product.price)
      });
      alert('Product added!');
      setProduct({ name: '', description: '', price: '' });
    } catch (error) {
      console.error(error);
      alert('Error adding product');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Add Product</h2>
      <input
        type="text"
        name="name"
        placeholder="Name"
        value={product.name}
        onChange={handleChange}
        required
      />
      <br />
      <textarea
        name="description"
        placeholder="Description"
        value={product.description}
        onChange={handleChange}
      ></textarea>
      <br />
      <input
        type="number"
        name="price"
        placeholder="Price"
        value={product.price}
        onChange={handleChange}
        required
      />
      <br />
      <button type="submit">Add</button>
    </form>
  );
};

export default AddProduct;
