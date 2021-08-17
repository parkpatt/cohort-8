
import { useState, useEffect } from 'react';
import { useHistory, useParams, Link } from 'react-router-dom';

function ConfirmDelete() {

  const [hero, setHero] = useState({alias: ''});
  const {heroId} = useParams();
  const history = useHistory();
  const url = "http://localhost:8080/api/hero";

  useEffect(() => {
    const fetchHero = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/hero/${heroId}`);
        if (response.status !== 200) {
          return Promise.reject(new Error(`Fetch hero failed, heroId: ${heroId}.`));
        }
        const json = await response.json();
        setHero(json);
        console.log(json);
      } catch (error) {
        console.log(error);
      }
    };
    if (heroId !== undefined) {
      fetchHero();
    }
  }, [heroId]);

  const confirm = async () => { 
    const init = { method: "DELETE" };
    const response = await fetch(`${url}/${hero.id}`, init);
    if (response.status !== 204) {
      return Promise.reject(new Error("DELETE failed."));
    }
    history.push('/');
  };


  return <div className="container">
    <div className="row align-items-center">
      <h2 className="col">{`Delete ${hero.alias}?`}</h2>
    </div>
    <div className="alert alert-danger">Are you sure? {`${hero.alias}`} will be permanently deleted.</div>
    <div className="mb-3">
      <button type="button" className="btn btn-danger mx-3" onClick={confirm}>Delete</button>
      <Link to='/' className="btn btn-secondary mx-3">Cancel</Link>
    </div>
  </div>
}

export default ConfirmDelete;