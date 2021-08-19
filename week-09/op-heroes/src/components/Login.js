import { useState, useContext } from 'react';
import { Link, useHistory } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';

function Login() {

  const [user, setUser] = useState({ username: '', password: '' });

  const auth = useContext(LoginContext);
  const history = useHistory();

  const onChange = (evt) => {
    const nextUser = {...user};
    nextUser[evt.target.name] = evt.target.value;
    setUser(nextUser);
  };

  const onSubmit = (evt) => {
    evt.preventDefault();
    auth.login(user.username, user.password);
    history.push("/");
  };

  return <form onSubmit={onSubmit}>      
    <div className="mb-3">
      <label className="form-label" htmlFor="username">Username</label>
      <input type="text" className="form-control" id="username" name="username" value={user.username} onChange={onChange} />
    </div>
    <div className="mb-3">
      <label className="form-label" htmlFor="password">Password</label>
      <input type="password" className="form-control" id="password" name="password" value={user.password} onChange={onChange} />
    </div>
    <div className="mb-3">
      <button type="submit" className="btn btn-primary me-1">Submit</button>
      <Link to="/" className="btn btn-secondary mx-1">Cancel</Link>
    </div>
  </form>
}

export default Login;