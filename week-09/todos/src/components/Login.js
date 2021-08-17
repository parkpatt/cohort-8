import { useState, useContext } from 'react';
import { useHistory } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';

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
    auth.login(credentials.username, credentials.password);
    history.push("/");
  }

  return (<div className="row">
    <form className="col s12" onSubmit={onSubmit}>
      <div className="row">
        <div className="input-field col s12">
          <input id="username" type="text" name="username" value={credentials.username} onChange={onChange} />
          <label htmlFor="username">Description</label>
        </div>
      </div>
      <div className="row">
        <div className="input-field col s12">
          <input id="password" type="password" name="password" value={credentials.password} onChange={onChange} />
          <label htmlFor="password">Password</label>
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