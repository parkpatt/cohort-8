import { useState } from 'react';
import { BrowserRouter as Router, Switch, Route, Redirect } from 'react-router-dom';
import ConfirmDelete from './components/ConfirmDelete';
import HeroForm from './components/HeroForm';
import NavBar from './components/NavBar';
import ViewHeroes from './components/ViewHeroes';
import LoginContext from './contexts/LoginContext';
import Login from './components/Login';

function App() {

  const [user, setUser] = useState(null);

  const auth = {
    user: user ? {...user} : null,
    login: function(username, password) {
      setUser({
        username,
        password
      });
    },
    logout: function() {
      setUser(null);
    }
  };

  return (
    <LoginContext.Provider value={auth}>
      <Router>
        <NavBar />
        <Switch>
          <Route path={["/add", "/edit/:heroId"]}>
            <HeroForm />
          </Route>
          <Route path="/delete/:heroId">
            {user 
              ? <ConfirmDelete />
              : <Redirect to="/login" />}
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
