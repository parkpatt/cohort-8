import { useState, useEffect } from 'react';
import { Link, useParams, useHistory } from 'react-router-dom';
import { fetchHero } from '../api/heroApi';

const heroUrl = "http://localhost:8080/api/hero/";

function ConfirmDelete() {

  const [hero, setHero] = useState({ alias: '' });
  const { heroId } = useParams();
  const history = useHistory();

  useEffect(() => {
    if (heroId !== undefined) {
      fetchHero(heroId)
        .then(json => setHero(json))
        .catch(console.error);
    }
  }, [heroId]);

  const confirm = async () => { 
    const init = { method: "DELETE" };
    const response = await fetch(`${heroUrl}/${heroId}`, init);
    if (response.status !== 204) {
      return Promise.reject(new Error("DELETE failed."));
    }
    history.push("/");
  };

  return <div className="container">
    <div className="row align-items-center">
      <h2 className="col">{`Delete ${hero.alias}?`}</h2>
    </div>
    <div className="alert alert-danger">Are you sure? {`${hero.alias}`} will be permanently deleted.</div>
    <div className="mb-3">
      <button type="button" className="btn btn-danger mx-3" onClick={confirm}>Delete</button>
      <Link to="/" className="btn btn-secondary mx-1">Cancel</Link>
    </div>
  </div>
}

export default ConfirmDelete;