import React, { useEffect, useState } from 'react';
import {
  TextField,
  Button,
  Box,
  Typography,
  Stack
} from '@mui/material';

function ProductForm({ onSubmit, initialData, isEditing, cancelEdit }) {
  const [product, setProduct] = useState({ name: '', price: '', description: '' });
  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (initialData) {
      setProduct({
        id: initialData.id,
        name: initialData.name || '',
        price: initialData.price?.toString() || '',
        description: initialData.description || '',
      });
    } else {
      setProduct({ name: '', price: '', description: '' });
    }
  }, [initialData]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prev) => ({ ...prev, [name]: value }));
  };

  const validate = () => {
    const newErrors = {};
    if (!product.name.trim()) newErrors.name = 'Name is required.';
    if (!product.price.trim()) {
      newErrors.price = 'Price is required.';
    } else if (isNaN(product.price) || Number(product.price) <= 0) {
      newErrors.price = 'Price must be a positive number.';
    }
    if (!product.description.trim()) newErrors.description = 'Description is required.';
    return newErrors;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const validationErrors = validate();
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }
    onSubmit({
      id: initialData?.id,
      ...product,
      price: parseFloat(product.price),
    });
    setProduct({ name: '', price: '', description: '' });
    setErrors({});
  };

  return (
    <Box component="form" onSubmit={handleSubmit} sx={{ mb: 4 }}>
      <Typography variant="h6" gutterBottom>
        {isEditing ? 'Edit Product' : 'Add Product'}
      </Typography>

      <Stack spacing={2}>
        <TextField
          label="Name"
          name="name"
          value={product.name}
          onChange={handleChange}
          error={!!errors.name}
          helperText={errors.name}
          fullWidth
        />

        <TextField
          label="Price"
          name="price"
          value={product.price}
          onChange={handleChange}
          error={!!errors.price}
          helperText={errors.price}
          fullWidth
        />

        <TextField
          label="Description"
          name="description"
          value={product.description}
          onChange={handleChange}
          error={!!errors.description}
          helperText={errors.description}
          fullWidth
          multiline
          rows={4}
        />

        <Stack direction="row" spacing={2}>
          <Button variant="contained" color="primary" type="submit">
            {isEditing ? 'Update' : 'Add'} Product
          </Button>
          {isEditing && (
            <Button variant="outlined" color="secondary" onClick={cancelEdit}>
              Cancel
            </Button>
          )}
        </Stack>
      </Stack>
    </Box>
  );
}

export default ProductForm;