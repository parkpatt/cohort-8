import './App.css';
import Heading from './Heading';
import Numbers from './Numbers';
import Movies from './Movies';

function App() {
  return (
    <div>
      <h1>Hi</h1>
      <Heading />
      <Heading message={"Kittens"} />
      <Heading message={"Puppies"} />
      <Numbers />
      <Numbers min={11} max={20} />
      <Movies />
    </div>
  );
}

export default App;
