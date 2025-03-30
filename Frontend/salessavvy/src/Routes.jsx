import React from "react";
import { Routes, Route } from "react-router-dom";
import LoginPage from "./LoginPage";
import RegistrationPage from "./RegistrationPage";
import CustomerHomePage from "./CustomerHomePage";
import CartPage from "./CartPage";
import OrderPage from "./OrderPage";
import AdminLogin from "./AdminLogin"; 
import AdminDashboard from "./AdminDashboard";
import AboutUs from "./AboutUs";
import Contact from "./Contact";
import TermsOfService from "./TermsOfService";
import PrivacyPolicy from "./PrivacyPolicy";

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<LoginPage />} />
      <Route path="/register" element={<RegistrationPage />} />
      <Route path="/customerhome" element={<CustomerHomePage />} />
      <Route path="/UserCartPage" element={<CartPage />} />
      <Route path="/orders" element={<OrderPage />} />
      <Route path="/admin" element={<AdminLogin />} />
      <Route path="/admindashboard" element={<AdminDashboard />} />
      
      <Route path="/aboutus" element={<AboutUs />} />
      <Route path="/contact" element={<Contact />} />
      <Route path="/termsOfservice" element={<TermsOfService />} />
      <Route path="/privacypolicy" element={<PrivacyPolicy />} />

    </Routes>
  );
};

export default AppRoutes;
