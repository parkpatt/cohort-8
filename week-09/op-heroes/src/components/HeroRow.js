import { Link } from 'react-router-dom';

function HeroRow({ hero }) {
  return <div className="row mb-2">
    <div className="col">
      {hero.alias}
    </div>
    <div className="col">
      {hero.fullName}
    </div>
    <div className="col">
      {hero.faction}
    </div>
    <div className="col">
      {hero.abilities.map(a => a.name).join(", ")}
    </div>
    <div className="col">
      <Link to={`/delete/${hero.id}`} className="btn btn-danger mx-1">Delete</Link>
      <Link to={`/edit/${hero.id}`} className="btn btn-info mx-1">Edit</Link>
    </div>
  </div>
}

export default HeroRow;