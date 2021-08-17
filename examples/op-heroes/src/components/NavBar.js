import { Link, useContext } from "react-router-dom";

function NavBar(){

  return (
    <nav>
      <Link to='/login'>Login</Link>
    </nav>
  )
}

export default NavBar;