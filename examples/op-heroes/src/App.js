import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'; 
import HeroForm from './components/HeroForm';
import ViewHeroes from './components/ViewHeroes';
import ConfirmDelete from './components/ConfirmDelete';
import LoginContext from './contexts/LoginContext';
import NavBar from './components/NavBar';
import Login from './components/Login';

function App() {

  const auth = {

    login: function(username, password){

    },
    logout: function(){

    }
  };

  return (
    <LoginContext.Provider value={auth}>
      <Router>
        <NavBar />
        <Switch>
          <Route path={['/add', '/edit/:HeroId']}>
            <HeroForm />
          </Route>
          <Route path='/delete/:heroId'>
            <ConfirmDelete />
          </Route>
          <Route path='/login'>
            <Login />
          </Route>
          <Route path='/'>
            <ViewHeroes />
          </Route>
        </Switch>
      </Router>
    </LoginContext.Provider>
  );
}

export default App;
