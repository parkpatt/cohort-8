import { useState } from 'react';
import initialHeroes from '../data/heroes';
import initialAbilities from '../data/abilities';
import HeroForm from './HeroForm';
import ConfirmDelete from './ConfirmDelete';
import HeroRow from './HeroRow';

const DEFAULT_HERO = { 
  id: 0, 
  alias: "", 
  fullName: "", 
  faction: "" 
};

function ViewHeroes() {

  const [heroes, setHeroes] = useState(initialHeroes);
  const [currentView, setCurrentView] = useState("list");
  const [currentHero, setCurrentHero] = useState(DEFAULT_HERO);

  const onAdd = () => {
    setCurrentHero(DEFAULT_HERO);
    setCurrentView("form");
  };

  const onEdit = (evt) => {
    const hero = heroes.find(h => h.id === Number(evt.target.value));
    if (hero) {
      setCurrentHero(hero);
      setCurrentView("form");
    }
  };

  const onSave = (hero) => {
    const heroToSave = { ...hero };
    if (hero.id === 0) {
      heroToSave.id = heroes.length === 0 ? 1 : Math.max(...heroes.map(h => h.id)) + 1;
      setHeroes([...heroes, heroToSave]);
    } else {
      const nextHeroes = [...heroes];
      const indexToUpdate = nextHeroes.findIndex(h => h.id === hero.id);
      nextHeroes[indexToUpdate] = hero;
      setHeroes(nextHeroes);
    }
    setCurrentView("list");
  };

  const onDelete = (evt) => {    
    const hero = heroes.find(h => h.id === Number(evt.target.value));
    if (hero) {
      setCurrentHero(hero);
      setCurrentView("confirmDelete");
    }
  };

  const onDeleteConfirmed = () => { 
    const nextHeroes = [...heroes].filter(h => h.id !== currentHero.id);
    setHeroes(nextHeroes);
    setCurrentView("list");
  };

  if (currentView === "form") {
    return <HeroForm currentHero={currentHero} saveHero={onSave} cancel={() => setCurrentView("list")} />
  } else if (currentView === "confirmDelete") {
    return <ConfirmDelete currentHero={currentHero} confirm={onDeleteConfirmed} cancel={() => setCurrentView("list")} />
  }

  return <div className="container">
    <div className="row align-items-center">
      <h2 className="col">Heroes</h2>
      <div className="col  align-self-end">
        <button type="button" className="btn btn-primary" onClick={onAdd}>Add</button>
      </div>
    </div>
    {heroes.map(h => <HeroRow key={h.id} hero={h} onEdit={onEdit} onDelete={onDelete} />)}
  </div>
}

export default ViewHeroes;