function HeroRow({ hero, onDelete, onEdit }) {
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
      <button type="button" className="btn btn-danger mx-1" value={hero.id} onClick={onDelete}>Delete</button>
      <button type="button" className="btn btn-info mx-1" value={hero.id} onClick={onEdit}>Edit</button>
    </div>
  </div>
}

export default HeroRow;