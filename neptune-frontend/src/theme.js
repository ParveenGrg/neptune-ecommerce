// src/theme.js
import { createTheme } from '@mui/material/styles';

const darkTheme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#90caf9', // soft blue
    },
    secondary: {
      main: '#f48fb1', // soft pink
    },
    background: {
      default: '#121212', // dark background
      paper: '#1d1d1d', // slightly lighter for contrast
    },
    text: {
      primary: '#ffffff',
      secondary: '#bbbbbb',
    },
  },
  typography: {
    fontFamily: 'Inter, sans-serif',
  },
});

export default darkTheme;