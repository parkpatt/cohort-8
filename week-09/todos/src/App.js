import { useState } from 'react';
import { BrowserRouter as Router, Redirect, Route, Switch } from 'react-router-dom';
import About from './components/About';
import Login from './components/Login';
import LoginContext from './contexts/LoginContext';
import Nav from './components/Nav';
import ToDoForm from './components/ToDoForm';
import ToDoList from './components/ToDoList';

function App() {

  const [user, setUser] = useState(null);

  const login = (username, password) => {
    setUser({
      username,
      password
    });
  }

  const logout = () => {
    setUser(null);
  }

  const auth = {
    user: user ? {...user} : null,
    login,
    logout
  }

  return (
    <LoginContext.Provider value={auth}>
      <Router>
        <Nav />
        <Switch>
          <Route path={["/add", "/edit/:id"]}>
            {auth.user 
              ? <ToDoForm />
              : <Redirect to="/login" />}
          </Route>
          <Route path="/about">
            <About />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/">
            {auth.user 
              ? <ToDoList />
              : <Redirect to="/login" />}
          </Route>
        </Switch>
      </Router>
    </LoginContext.Provider>
  );
}

export default App;
