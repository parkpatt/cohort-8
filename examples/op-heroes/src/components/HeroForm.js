import { useState, useEffect } from 'react';

const DEFAULT_HERO = { 
  id: 0, 
  alias: "", 
  fullName: "", 
  faction: "" ,
  abilities: []
};

function HeroForm({ heroId, saveHero, cancel }) {

  const [hero, setHero] = useState(DEFAULT_HERO);
  const [abilities, setAbilities] = useState([]);

  useEffect(() => {
    const fetchAbilities = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/ability");
        if (response.status !== 200) {
          return Promise.reject(new Error("Fetch abilities failed."));
        }
        const json = await response.json();
        setAbilities(json);
      } catch (error) {
        console.log(error);
      }
    };
    fetchAbilities();
  }, []);

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
    saveHero(hero);
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
        <button type="button" className="btn btn-secondary mx-1" onClick={cancel}>Cancel</button>
      </div>
    </form>
  </div>
}

export default HeroForm;