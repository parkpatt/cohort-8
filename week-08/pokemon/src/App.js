import { useState } from 'react';
import Pokedex from './components/Pokedex';
import Pokemon from './components/Pokemon';

import 'bootstrap/dist/css/bootstrap.css';

function App() {

  const [pokeUrl, setPokeUrl] = useState();
  const cancel = () => setPokeUrl(); 

  return (
    <div className="container">
      <h1>Pokémon</h1>
      {pokeUrl
        ? <Pokemon pokeUrl={pokeUrl} cancel={cancel} />
        : <Pokedex setPokeUrl={setPokeUrl} />
      }
    </div>
  );
}

export default App;
