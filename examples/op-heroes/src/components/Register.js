import { useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import { register } from '../api/authApi';
import ErrorSummary from './ErrorSummary';

function Register() {

  const [credentials, setCredentials] = useState({ username: '', password: '', confirmPassword: '' });
  const [errors, setErrors] = useState([]);

  const history = useHistory();

  const onChange = (evt) => {
    const nextCredentials = {...credentials};
    nextCredentials[evt.target.name] = evt.target.value;
    setCredentials(nextCredentials);
  };

  const onSubmit = (evt) => {
    evt.preventDefault();

    if (credentials.password !== credentials.confirmPassword) {
      setErrors(["Passwords do not match."]);
      return;
    }

    register(credentials)
      .then(() => {
        history.push("/login");
      })
      .catch(errors => {
        setErrors(errors.messages);
      });
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
      <label className="form-label" htmlFor="password">Confirm Password</label>
      <input type="password" className="form-control" id="confirmPassword" name="confirmPassword" 
          required value={credentials.confirmPassword} onChange={onChange} />
    </div>
    <div className="mb-3">
      <button type="submit" className="btn btn-primary me-1">Submit</button>
      <Link to="/" className="btn btn-secondary mx-1">Cancel</Link>
    </div>
    <ErrorSummary errors={errors} />
  </form>
}

export default Register;