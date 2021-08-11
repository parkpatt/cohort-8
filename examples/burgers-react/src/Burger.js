import InAndOut from './InAndOut';

function Burger({ burger }) {

    return <div className="burger-row">
        <div>{burger.name}</div>
        <div>{burger.calories}</div>
        <InAndOut />
    </div>;
}

export default Burger;