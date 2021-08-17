import { useState, useEffect } from 'react';
import HeroForm from './HeroForm';
import ConfirmDelete from './ConfirmDelete';
import HeroRow from './HeroRow';
import HeroCard from './HeroCard';

const url = "http://localhost:8080/api/hero";

function ViewHeroes() {

  const [heroes, setHeroes] = useState([]);
  const [currentView, setCurrentView] = useState("list");
  const [heroId, setCurrentHero] = useState(0);
  const [heroToDelete, setHeroToDelete] = useState();

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

  const onAdd = () => {
    setCurrentHero();
    setCurrentView("form");
  };

  const onEdit = (evt) => {
    const hero = heroes.find(h => h.id === parseInt(evt.target.value, 10));
    if (hero) {
      setCurrentHero(hero.id);
      setCurrentView("form");
    }
  };

  const onSave = async (hero) => {
   
    const init = {
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify(hero)
    };

    if (hero.id === 0) {  
      init.method =  "POST";
      const response = await fetch(url, init);
      if (response.status !== 201) {
        return Promise.reject(new Error("POST failed."));
      }
      fetchAll();
    } else {
      init.method = "PUT";
      const response = await fetch(`${url}/${hero.id}`, init);
      if (response.status !== 204) {
        return Promise.reject(new Error("PUT failed."));
      }
      fetchAll();
    }
    setCurrentView("list");
  };

  const onDelete = (evt) => {    
    const hero = heroes.find(h => h.id === parseInt(evt.target.value, 10));
    if (hero) {
      setHeroToDelete(hero);
      setCurrentView("confirmDelete");
    }
  };

  const onDeleteConfirmed = async () => { 
    const init = { method: "DELETE" };
    const response = await fetch(`${url}/${heroToDelete.id}`, init);
    if (response.status !== 204) {
      return Promise.reject(new Error("DELETE failed."));
    }
    fetchAll();
    setCurrentView("list");
  };

  if (currentView === "form") {
    return <HeroForm heroId={heroId} saveHero={onSave} cancel={() => setCurrentView("list")} />
  } else if (currentView === "confirmDelete") {
    return <ConfirmDelete hero={heroToDelete} confirm={onDeleteConfirmed} cancel={() => setCurrentView("list")} />
  }

  return (<>
    <div className="container">
      <div className="row align-items-center">
        <h2 className="col">Heroes</h2>
        <div className="col  align-self-end">
          <button type="button" className="btn btn-primary" onClick={onAdd}>Add</button>
        </div>
      </div>
      {heroes.map(h => <HeroRow key={h.id} hero={h} onEdit={onEdit} onDelete={onDelete} />)}
    </div>
    <div className="card-columns">
      {heroes.map(h => <HeroCard key={h.id} hero={h} onEdit={onEdit} onDelete={onDelete} />)}
    </div>
  </>)
}

export default ViewHeroes;