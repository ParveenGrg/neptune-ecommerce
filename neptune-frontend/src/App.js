import React, { useEffect, useState } from 'react';
import ProductForm from './components/ProductForm';
import ProductList from './components/ProductList';
import axios from 'axios';
import Navbar from './components/Navbar';
import ExclusiveSlider from './components/ExclusiveSlider';

function App() {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);

  const fetchProducts = async () => {
    try {
      const response = await axios.get('http://localhost:8080/products');
      setProducts(response.data);
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  // Triggered when a product is selected for editing
  const handleEdit = (product) => {
    setSelectedProduct(product);
  };

  // Called after save (add or update)
  const handleSave = () => {
    fetchProducts();          // Refresh the product list
    setSelectedProduct(null); // Clear form after save
  };

  return (
    <div className="container">

      <div>
        <Navbar />
        <ExclusiveSlider />
      </div>
      <ProductForm
        selectedProduct={selectedProduct}
        onSave={handleSave}
      />
      <ProductList products={products} />
    </div>
  );
}

export default App;