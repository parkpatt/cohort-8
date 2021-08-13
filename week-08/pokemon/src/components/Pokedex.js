import { useState, useEffect } from "react";

const url = "https://pokeapi.co/api/v2/pokemon";

function Pokedex({ setPokeUrl }) {

  const [pokeResult, setPokeResult] = useState();

  useEffect(() => {
    fetch(url)
      .then(r => {
        if (r.status === 200) {
          return r.json();
        }
        throw new Error("Fetch failed.")
      })
      .then(json => setPokeResult(json))
      .catch(err => console.error(err));
  }, []);

  return <>
    <h3>Pokédex</h3>
    {pokeResult 
      ? <table>
          <thead>
            <tr>
              <th>
                Name
              </th>
            </tr>
          </thead>
          <tbody>
            {pokeResult.results.map(p => <tr key={p.id}>
              <td>{p.name}</td>
              <td>
                <button className="btn btn-info" onClick={() => setPokeUrl(p.url)}>View</button>
              </td>
            </tr>)}
          </tbody>
        </table>
      : <div className="alert alert-warning">No Pokemon found</div>}
  </>;
}

export default Pokedex;