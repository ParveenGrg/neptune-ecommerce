import React, { useEffect, useState } from 'react';
import axios from 'axios';
import ProductForm from './ProductForm';
import {
  Typography,
  List,
  ListItem,
  ListItemText,
  Button,
  Divider,
  Paper,
  Box,
} from '@mui/material';

function ProductList() {
  const [products, setProducts] = useState([]);
  const [editId, setEditId] = useState(null);
  const [editData, setEditData] = useState(null);

  const fetchProducts = () => {
    axios.get('http://localhost:8080/products')
      .then(res => setProducts(res.data))
      .catch(err => console.error(err));
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  const handleAdd = (product) => {
    axios.post('http://localhost:8080/products', product)
      .then(fetchProducts)
      .catch(err => console.error(err));
  };

  const handleDelete = (id) => {
    axios.delete(`http://localhost:8080/products/${id}`)
      .then(fetchProducts)
      .catch(err => console.error(err));
  };

  const handleEditInit = (product) => {
    setEditId(product.id);
    setEditData(product);
  };

  const handleUpdate = (updatedProduct) => {
    console.log('Updating product with ID:', editId);
    console.log('Updated product data:', updatedProduct);
    axios.put(`http://localhost:8080/products/${editId}`, updatedProduct)
      .then(() => {
        setEditId(null);
        setEditData(null);
        fetchProducts();
      })
      .catch(err => console.error(err));
  };

  return (
    <Box sx={{ padding: 4 }}>
      <Typography variant="h4" gutterBottom>Neptune Product Manager</Typography>

      <Paper sx={{ padding: 2, marginBottom: 4 }}>
        <ProductForm
          onSubmit={editId ? handleUpdate : handleAdd}
          initialData={editData}
          isEditing={!!editId}
          cancelEdit={() => {
            setEditId(null);
            setEditData(null);
          }}
        />
      </Paper>

      <Typography variant="h5" gutterBottom>Product List</Typography>

      <List>
        {products.map(product => (
          <React.Fragment key={product.id}>
            <ListItem
              secondaryAction={
                <>
                  <Button variant="outlined" onClick={() => handleEditInit(product)} sx={{ marginRight: 1 }}>
                    Edit
                  </Button>
                  <Button variant="outlined" color="error" onClick={() => handleDelete(product.id)}>
                    Delete
                  </Button>
                </>
              }
            >
              <ListItemText
                primary={`${product.name} - $${product.price}`}
                secondary={product.description}
              />
            </ListItem>
            <Divider />
          </React.Fragment>
        ))}
      </List>
    </Box>
  );
}

export default ProductList;