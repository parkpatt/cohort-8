import { useContext } from 'react';
import { Link } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';

function NavBar() {

  const auth = useContext(LoginContext);

  return <nav className="navbar navbar-expand-lg navbar-light">
    <Link to={"/"} className="navbar-brand">Heroes</Link>
    <div className="collapse navbar-collapse">
      <ul className="navbar-nav mr-auto">
        <li className="nav-item active">
          {auth.user 
              ? <div>
                  Hello, {auth.user}
                  <button className="btn btn-light" onClick={() => auth.logout()}>Log out</button>
                </div> 
              : <Link to="/login">Login</Link>}
        </li>
      </ul>
    </div>
  </nav>
}

export default NavBar;