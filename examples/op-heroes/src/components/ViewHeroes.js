import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import HeroRow from './HeroRow';
import HeroCard from './HeroCard';

const url = "http://localhost:8080/api/hero";

function ViewHeroes() {

  const [heroes, setHeroes] = useState([]);

  const fetchAll = async () => {
    try {
      const response = await fetch(url);
      if (response.status !== 200) {
        return Promise.reject(new Error("Fetch failed."));
      }
      const json = await response.json();
      setHeroes(json);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    fetchAll();
  }, []);

  return (<>
    <div className="container">
      <div className="row align-items-center">
        <h2 className="col">Heroes</h2>
        <div className="col  align-self-end">
          <Link to='/add' className="btn btn-primary">Add</Link>
          {/* <button type="button" className="btn btn-primary" onClick={onAdd}>Add</button> */}
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