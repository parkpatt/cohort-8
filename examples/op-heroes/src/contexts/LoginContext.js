import { createContext } from "react";

/* track user state 
{
  user: { username, password },
  login: funciton() { },
  logout: function() { }
}
*/
const LoginContext = createContext();

export default LoginContext;