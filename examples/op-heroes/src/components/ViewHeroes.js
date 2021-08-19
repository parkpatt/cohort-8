import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import HeroRow from './HeroRow';
import HeroCard from './HeroCard';
import { fetchAll } from '../api/heroApi';

function ViewHeroes() {

  const [heroes, setHeroes] = useState([]);
<<<<<<< HEAD

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
=======
>>>>>>> 6806cda2ce7ae8e8cd48f2da913a5245a34bbbe2

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
<<<<<<< HEAD
          <Link to='/add' className="btn btn-primary">Add</Link>
          {/* <button type="button" className="btn btn-primary" onClick={onAdd}>Add</button> */}
=======
          <Link to="/add" className="btn btn-primary">Add</Link>
>>>>>>> 6806cda2ce7ae8e8cd48f2da913a5245a34bbbe2
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