import "../css/HeroCard.css";

function HeroCard({ hero }) {
  return <div className="card p-3">
    <img className="card-img-top" src={hero.imgUrl} alt={hero.alias} />
    <div className="card-body">
      <div className="card-title">{hero.alias}</div>
      <p className="card-text">Faction: {hero.faction}</p>
    </div>
  </div>
}

export default HeroCard;