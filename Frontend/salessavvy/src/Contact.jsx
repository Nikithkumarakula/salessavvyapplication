// Contact.jsx
import React from "react";

export function Contact() {
  return (
    <div className="page-container">
        
      <h2>Contact Us</h2>
      <p>
        Have questions? Get in touch with us!
      </p>
      <ul>
        <li>Email: support@salessavvy.com</li>
        <li>Phone: +1 234 567 8900</li>
        <li>Address: 123 SalesSavvy Street, New York, NY</li>
      </ul>

      <a href="/customerhome" className="back-button">
            ← Home page
          </a>
      
    </div>
  );
}
export default Contact;