import React from "react";

import "./App.css";

const App = () => {
  const socialSignIn = (socialType) => {
    return `http://j8b304.p.ssafy.io:8080/oauth2/authorization/google`;
  };

  return (
    <div className="container">
      <div className="login" onClick={(e) => {
          e.preventDefault();
          window.location.href = socialSignIn("google");
        }}>
        Login
      </div>
      <div className="logout">Logout</div>
    </div>
  );
};

export default App;