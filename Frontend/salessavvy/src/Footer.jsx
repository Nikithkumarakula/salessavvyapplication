// Footer.jsx
import React from 'react';
import './assets/styles.css';

export function Footer() {
  return (
    <footer className="footer">
      <div className="footer-content">
        <div className="footer-left">
          <h3 className="footer-title">SalesSavvy</h3>
          <p className="footer-tagline">Your one-stop shop for all your needs</p>
        </div>
        <div className="footer-links">
          <a href="/aboutus">About Us</a>
          <a href="/contact">Contact</a>
          <a href="/termsOfservice">Terms of Service</a>
          <a href="/privacypolicy">Privacy Policy</a>
        </div>
      </div>
      <div className="footer-bottom">
        <p>© 2025 SalesSavvy. All rights reserved.</p>
      </div>
    </footer>
  );
}
export default Footer;