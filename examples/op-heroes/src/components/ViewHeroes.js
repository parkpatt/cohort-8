import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import HeroRow from './HeroRow';
import HeroCard from './HeroCard';
import { fetchAll } from '../api/heroApi';

function ViewHeroes() {

  const [heroes, setHeroes] = useState([]);

  useEffect(() => {
    fetchAll()
      .then(setHeroes)
      .catch(console.error);
  }, []);

  return (<>
    <div className="container">
      <div className="row align-items-center">
        <h2 className="col">Heroes</h2>
        <div className="col  align-self-end">
          <Link to="/add" className="btn btn-primary">Add</Link>
        </div>
      </div>
      {heroes.map(h => <HeroRow key={h.id} hero={h} />)}
    </div>
    <div className="card-columns">
      {heroes.map(h => <HeroCard key={h.id} hero={h} />)}
    </div>
  </>)
}

export default ViewHeroes;