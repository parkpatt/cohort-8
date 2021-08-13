import { useState, useEffect } from 'react';

function Pokemon({ pokeUrl, cancel }) {

  const [pokemon, setPokemon] = useState();

  useEffect(() => {
    fetch(pokeUrl)
      .then(r => {
        if (r.status === 200) {
          return r.json();
        }
        throw new Error(`Pokemon fetch failed: ${pokeUrl}`);
      })
      .then(setPokemon)
      .catch(console.error);
  }, []);
  
  return <>
    {pokemon
      ? <>
          <h3>{pokemon.name}</h3>
          {
            Object.keys(pokemon.sprites)
              .filter(key => typeof pokemon.sprites[key] === "string")
              .map(key => <img key={key} src={pokemon.sprites[key]} />)
          }
        </>
      : <div className="alert alert-warning">Loading...</div>
    }
    <button type="button" className="btn btn-success" onClick={cancel}>Back</button>
  </>
}

export default Pokemon;