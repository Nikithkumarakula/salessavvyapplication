// TermsOfService.jsx
import React from "react";

export function TermsOfService() {
  return (
    <div className="page-container">
   
      <h2>Terms of Service</h2>
      <p>
        By using our platform, you agree to our terms and conditions. We reserve
        the right to update our policies at any time.
      </p>
      <p>
        Users must be at least 18 years old to make a purchase. Unauthorized use
        of our website may result in legal action.
      </p>

      <a href="/customerhome" className="back-button">
            ← Home page
          </a>
    </div>
  );
}
export default TermsOfService;