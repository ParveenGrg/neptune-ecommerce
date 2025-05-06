import React from 'react';
import Slider from 'react-slick';
import { Box, Typography } from '@mui/material';

const sampleImages = [
  {
    src: 'https://cdn.shopify.com/s/files/1/0070/7032/articles/trending-products_c5994df0-190d-4799-859c-5f3bdfee1751.png?v=1742951849',
    caption: 'Trending Products',
  },
  {
    src: 'https://www.godaddy.com/resources/ae/wp-content/uploads/sites/11/online-store-graphic.jpg?size=768x0',
    caption: 'Build Your Online Store',
  },
  {
    src: 'https://mma.prnewswire.com/media/465386/Product_of_the_Year_USA_2017_Winners.jpg?p=facebook',
    caption: 'Product of the Year',
  },
];

const ExclusiveSlider = () => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 600,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 4000,
  };

  return (
    <Box sx={{ maxWidth: '100%', mt: 2 }}>
      <Slider {...settings}>
        {sampleImages.map((img, index) => (
          <Box key={index} sx={{ position: 'relative' }}>
            <img
              src={img.src}
              alt={`Slide ${index}`}
              style={{
                width: '100%',
                maxHeight: '450px',
                objectFit: 'cover',
                borderRadius: '8px',
              }}
            />
            <Typography
              variant="h6"
              sx={{
                position: 'absolute',
                bottom: 20,
                left: 20,
                color: 'white',
                backgroundColor: 'rgba(0, 0, 0, 0.5)',
                padding: '8px 12px',
                borderRadius: '4px',
              }}
            >
              {img.caption}
            </Typography>
          </Box>
        ))}
      </Slider>
    </Box>
  );
};

export default ExclusiveSlider;