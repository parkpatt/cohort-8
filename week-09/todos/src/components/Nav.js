import { useContext } from 'react';
import { Link } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';

function Nav() {

  const auth = useContext(LoginContext);

  return <nav>
    <div className="nav-wrapper">
      <Link to="/" className="brand-logo">To Do List</Link>
      <ul id="nav-mobile" className="right hide-on-med-and-down">
        <li>
          <Link to="/add">Add Item</Link>
        </li>
        <li>
          <Link to="/about">About</Link>
        </li>
        {!auth.user && <>
          <li>
            <Link to="/login">Login</Link>
          </li>
          <li>
            <Link to="/register">Register</Link>
          </li>
        </>}
        {auth.user && <>
          <li>
            Hello,&nbsp;
            {auth.user}
            <button className="btn-flat" onClick={auth.logout}>Logout</button>
          </li>
        </>}
      </ul>
    </div>
  </nav>
}

export default Nav;