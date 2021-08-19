import { useState, useEffect } from 'react';
import { Link, useParams, useHistory } from 'react-router-dom';
import { fetchHero, saveHero } from '../api/heroApi';
import { fetchAll as fetchAbilities } from '../api/abilityApi';
import ErrorSummary from './ErrorSummary';

const DEFAULT_HERO = { 
  id: 0, 
  alias: "", 
  fullName: "", 
  faction: "" ,
  abilities: []
};

function HeroForm() {

  const [hero, setHero] = useState(DEFAULT_HERO);
  const [abilities, setAbilities] = useState([]);
  const [errors, setErrors] = useState([]);

  const { heroId } = useParams();
  const history = useHistory();

  useEffect(() => {
    fetchAbilities()
      .then(json => setAbilities(json))
      .catch(console.error);
  }, []);

  useEffect(() => {
    if (heroId !== undefined) {
      fetchHero(heroId)
        .then(json => setHero(json))
        .catch(console.error);
    }
  }, [heroId]);

  const onChange = (evt) => {
    const nextHero = {...hero};
    if (evt.target.name === "abilities") {
      if (evt.target.checked) {
        const ability = abilities.find(a => a.id === Number(evt.target.value));
        if (ability) {
          nextHero.abilities.push(ability);
        }
      } else {
        nextHero.abilities = nextHero.abilities.filter(a => a.id !== Number(evt.target.value));
      }
    } else {
      nextHero[evt.target.name] = evt.target.value;
    }
    setHero(nextHero);
  };

  const onSubmit = (evt) => {
    evt.preventDefault();

    saveHero(hero)
      .then(() => history.push("/"))
      .catch(err => {
        setErrors(err);
      });
  };

  return <div className="container">
    <div className="row align-items-center">
      <h2 className="col">{hero.id === 0 ? "Add a Hero" : `Edit ${hero.alias}`}</h2>
    </div>
    <form onSubmit={onSubmit}>
      <div className="mb-3">
        <label className="form-label" htmlFor="alias">Alias</label>
        <input type="text" className="form-control" id="alias" name="alias" value={hero.alias} onChange={onChange} required />
      </div>
      <div className="mb-3">
        <label className="form-label" htmlFor="fullName">Name</label>
        <input type="text" className="form-control" id="fullName" name="fullName" value={hero.fullName} onChange={onChange} />
      </div>
      <div className="mb-3">
        <label className="form-label" htmlFor="faction">Faction</label>
        <input type="text" className="form-control" id="faction" name="faction" value={hero.faction} onChange={onChange} />
      </div>
      <div className="mb-3">
        <h4 className="mb-3">Abilities</h4>
        {abilities.map(a => 
          <div className="form-check form-switch" key={a.id}>            
            <input type="checkbox" className="form-check-input" name="abilities" id={`${a.id}-${a.name}`} value={a.id} 
              checked={hero.abilities.find(ha => ha.id === a.id) != null} onChange={onChange} />
            <label className="form-check-label" htmlFor={`${a.id}-${a.name}`}>{a.name}</label>
          </div>
        )}
      </div>
      <div className="mb-3">
        <button type="submit" className="btn btn-primary me-1">Save</button>
        <Link to="/" className="btn btn-secondary mx-1">Cancel</Link>
      </div>
    </form>
    <ErrorSummary errors={errors} />
  </div>
}

export default HeroForm;