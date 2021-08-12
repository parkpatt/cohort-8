import './App.css';
import { useState } from 'react';
import Burger from './Burger';

function App() {

  const startingBurgers = [
    {
      id: 1,
      name: "California",
      calories: 380
    },
    {
      id: 2,
      name: "The Heart Attack",
      calories: 15000
    },
    {
      id: 3,
      name: "Salmon Burger",
      calories: 200
    }
  ];

  const [burgers, setBurgers] = useState(startingBurgers);

  const buttonClick = () => {
    const id = Math.random() * 1000;
    const newBurger = {
      id: id,
      name: `Veggie Burger ${id}`,
      calories: 230
    };
    
    setBurgers([...burgers, newBurger]);
  }

  return (
    <>
      <h1>Burger Menu</h1>
      <button type="button" onClick={buttonClick}>Add Burger</button>
      {burgers.map(b => <Burger key={b.id} burger={b} />)}
    </>
  );
}

export default App;
