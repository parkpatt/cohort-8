import { useState, useContext } from 'react';
import { Link, useHistory } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';
import { authenticate } from '../api/authApi';
import ErrorSummary from './ErrorSummary';

function Login() {

  const [credentials, setCredentials] = useState({ username: '', password: '' });
  const [errors, setErrors] = useState([]);
  const auth = useContext(LoginContext);
  const history = useHistory();

  const onChange = (evt) => {
    const nextCredentials = {...credentials};
    nextCredentials[evt.target.name] = evt.target.value;
    setCredentials(nextCredentials);
  };

  const onSubmit = (evt) => {
    evt.preventDefault();

    authenticate(credentials)
      .then(body => {
        if (body === null) {
          setErrors(["Username/password are not correct."])
        } else {
          const { jwt_token } = body;
          auth.onAuthenticated(jwt_token);
          history.push("/");
        }
      })
      .catch(console.error);
  };

  return <form onSubmit={onSubmit}>      
    <div className="mb-3">
      <label className="form-label" htmlFor="username">Username</label>
      <input type="text" className="form-control" id="username" name="username" 
          required value={credentials.username} onChange={onChange} />
    </div>
    <div className="mb-3">
      <label className="form-label" htmlFor="password">Password</label>
      <input type="password" className="form-control" id="password" name="password" 
          required value={credentials.password} onChange={onChange} />
    </div>
    <div className="mb-3">
      <button type="submit" className="btn btn-primary me-1">Submit</button>
      <Link to="/" className="btn btn-secondary mx-1">Cancel</Link>
    </div>
    <ErrorSummary errors={errors} />
  </form>
}

export default Login;