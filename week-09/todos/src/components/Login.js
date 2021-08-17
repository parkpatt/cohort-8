import { useState, useContext } from 'react';
import { useHistory } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';
import { authenticate } from '../services/auth';

function Login() {

  const [credentials, setCredentials] = useState({ username: '', password: '' });
  const auth = useContext(LoginContext);
  const history = useHistory();

  const onChange = (evt) => {
    const nextCredentials = { ...credentials };
    nextCredentials[evt.target.name] = evt.target.value;
    setCredentials(nextCredentials);
  };

  const onSubmit = (evt) => {
    evt.preventDefault();

    authenticate(credentials)
      .then(body => {
        if (body === null) {
          // TODO: tell the user something
        } else {
          const { jwt_token } = body;
          auth.onAuthenticated(jwt_token);
          history.push("/");
        }
      })
      .catch(err => {
        console.error(err);
      })
  }

  return (<div className="row">
    <form className="col s12" onSubmit={onSubmit}>
      <div className="row">
        <div className="input-field col s12">
          <input id="username" type="text" name="username" value={credentials.username} onChange={onChange} />
          <label htmlFor="username" className="active">Description</label>
        </div>
      </div>
      <div className="row">
        <div className="input-field col s12">
          <input id="password" type="password" name="password" value={credentials.password} onChange={onChange} />
          <label htmlFor="password" className="active">Password</label>
        </div>
      </div>
      <div className="row">
        <button type="submit" className="btn waves-effect waves-light">
          Submit
          <i className="material-icons right">send</i>
        </button>
      </div>
    </form>
  </div>)
}

export default Login;