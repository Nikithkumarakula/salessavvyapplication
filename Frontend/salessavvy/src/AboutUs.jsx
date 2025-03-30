// AboutUs.jsx
import React from "react";
import './assets/styles.css';
import './assets/footersection.css';

// Export a function called AboutUs
export function AboutUs() {
  // Return a div with a class of page-container
  return (
    
    <div className="page-container">


      
      <h2>About Us</h2>
     
      <p>
        Welcome to SalesSavvy! We are your one-stop shop for all your needs.
        Our mission is to provide quality products with seamless shopping
        experiences. Founded in 2025, we strive to bring the best deals and
        customer service to our shoppers.
      </p>
      <p>
        Whether you're looking for electronics, clothing, or home essentials,
        we've got you covered.
      </p>

      <a href="/customerhome" className="back-button">
            ← Home page
          </a>
    </div>
  );
}
export default AboutUs;