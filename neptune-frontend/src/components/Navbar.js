import React from 'react';
import { AppBar, Toolbar, IconButton, Typography, InputBase, Badge, Box } from '@mui/material';
import { Search, ShoppingCart, AccountCircle, LocationOn } from '@mui/icons-material';

function Navbar() {
  return (
    <AppBar position="static" color="transparent" elevation={0}>
      <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>
        
        {/* Logo */}
        <Box sx={{ display: 'flex', alignItems: 'center' }}>
          <img src="/neptune-logo.png" alt="Neptune Logo" style={{ height: 40, marginRight: 8 }} />
          <Typography variant="h6" noWrap sx={{ fontFamily: 'Inter, sans-serif', color: '#1F2933' }}>
            Neptune
          </Typography>
        </Box>

        {/* Location */}
        <Box sx={{ display: 'flex', alignItems: 'center', mx: 2 }}>
          <LocationOn sx={{ color: '#3B82F6' }} />
          <Typography variant="body2" sx={{ ml: 1, color: '#1F2933' }}>
            Current Address
          </Typography>
        </Box>

        {/* Search */}
        <Box sx={{ flexGrow: 1, mx: 2 }}>
          <InputBase
            placeholder="Search..."
            sx={{
              padding: '6px 12px',
              backgroundColor: '#F1F3F4',
              borderRadius: 1,
              width: '100%',
              fontFamily: 'Inter, sans-serif',
              color: '#1F2933'
            }}
            startAdornment={<Search sx={{ mr: 1, color: '#3B82F6' }} />}
          />
        </Box>

        {/* Account and Cart */}
        <Box sx={{ display: 'flex', alignItems: 'center' }}>
          <IconButton>
            <AccountCircle sx={{ color: '#1F2933' }} />
          </IconButton>
          <IconButton>
            <Badge badgeContent={2} color="primary">
              <ShoppingCart sx={{ color: '#1F2933' }} />
            </Badge>
          </IconButton>
        </Box>

      </Toolbar>
    </AppBar>
  );
}

export default Navbar;