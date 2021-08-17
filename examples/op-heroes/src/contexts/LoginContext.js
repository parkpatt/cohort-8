import { createContext } from "react";

// track user state
/*
  {
    user : {username, password},
    login: function (){},
    logout: function(){}
  }
*/
const LoginContext = createContext();

export default LoginContext;