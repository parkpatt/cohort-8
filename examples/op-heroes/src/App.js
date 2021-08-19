import { useState, useEffect } from 'react';
import { BrowserRouter as Router, Switch, Route, Redirect } from 'react-router-dom';
import { refresh } from './api/authApi';
import ConfirmDelete from './components/ConfirmDelete';
import HeroForm from './components/HeroForm';
import NavBar from './components/NavBar';
import ViewHeroes from './components/ViewHeroes';
import LoginContext from './contexts/LoginContext';
import Login from './components/Login';
import Register from './components/Register';

const wait = 1000 * 60 * 7;

const parseToken = (token) => {
  const tokenTokens = token.split('.');
  try {
    const jwtBody = tokenTokens[1];
    return JSON.parse(atob(jwtBody));
  } catch (err) {
    console.log(err)
    console.log("Failed to parse token.")
  }
}

function App() {

  const [user, setUser] = useState(null);
  const [initialized, setInitialized] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('jwt_token');
    if (token) {
      auth.onAuthenticated(token);
    }
    setInitialized(true);
  }, []);

  const auth = {
    user,
    onAuthenticated: (token) => {
      const payload = parseToken(token);
      setUser(payload.sub);
      localStorage.setItem('jwt_token', token);
      setTimeout(refreshToken, wait);
    },
    logout: () => {
      setUser(null);
      localStorage.removeItem('jwt_token');
    }
  };

  const refreshToken = () => {
    refresh()
      .then(token => {
        localStorage.setItem('jwt_token', token);
        setTimeout(refreshToken, wait);
      })
      .catch(console.error);
  };

  if (!initialized) {
    return null;
  }

  return (
    <LoginContext.Provider value={auth}>
      <Router>
        <NavBar />
        <Switch>
          <Route path={["/add", "/edit/:heroId"]}>
            {user 
              ? <HeroForm />
              : <Redirect to="/login" />}
          </Route>
          <Route path="/delete/:heroId">
            {user 
              ? <ConfirmDelete />
              : <Redirect to="/login" />}
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/">
            <ViewHeroes />
          </Route>
        </Switch>
      </Router>
    </LoginContext.Provider>
  );
}

export default App;
