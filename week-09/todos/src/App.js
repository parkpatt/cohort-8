<<<<<<< HEAD
import { useEffect, useState } from 'react';
=======
import { useState, useEffect } from 'react';
>>>>>>> 6806cda2ce7ae8e8cd48f2da913a5245a34bbbe2
import { BrowserRouter as Router, Redirect, Route, Switch } from 'react-router-dom';
import About from './components/About';
import Login from './components/Login';
import LoginContext from './contexts/LoginContext';
import Nav from './components/Nav';
import ToDoForm from './components/ToDoForm';
import ToDoList from './components/ToDoList';
import jwtDecode from 'jwt-decode';

function App() {

  const [user, setUser] = useState(null);
  const [initialized, setInitialized] = useState(false);

  const onAuthenticated = (token) => {
    const payload = jwtDecode(token);
    setUser(payload.sub);
    localStorage.setItem('jwt_token', token);
  }

  useEffect(() => {
    const token = localStorage.getItem('jwt_token');
    if (token) {
      onAuthenticated(token);
    }
<<<<<<< HEAD
  }, [onAuthenticated])

=======
    setInitialized(true);
  }, [])
>>>>>>> 6806cda2ce7ae8e8cd48f2da913a5245a34bbbe2

  const logout = () => {
    setUser(null);
    localStorage.removeItem('jwt_token');
  }

  const auth = {
    user,
    onAuthenticated,
    logout
  }

  if (!initialized) {
    return null;
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
