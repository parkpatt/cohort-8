import { useState, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';
import { findAll, deleteById } from '../services/todos';

function ToDoList() {

  const [todos, setTodos] = useState([]);

  const history = useHistory();

  useEffect(() => {
    findAll()
      .then(setTodos)
      .catch(console.error);
  }, [history]);

  const deleteToDo = (evt) => {
    deleteById(parseInt(evt.target.value, 10))
      .then(() => {
        findAll()
          .then(setTodos);
      })
      .catch(console.error);
  }

  return <>
    <table className="striped">
      <thead>
        <tr>
          <th>Description</th>
        </tr>
      </thead>
      <tbody>
        {todos.map(t => <tr key={t.id}>
          <td>{t.description}</td>
          <td>
            <button className="red darken-2 btn" value={t.id} onClick={deleteToDo}>
              <i className="material-icons" value={t.id}>delete_forever</i>
            </button>
          </td>
          <td>
            <Link to={`/edit/${t.id}`} className="light-blue lighten-2 btn">
              <i className="material-icons">edit</i>
            </Link>
          </td>
        </tr>)}
      </tbody>
    </table>
    <Link to="/add" className="waves-effect waves-light btn">
      <i className="material-icons">add</i>
    </Link>
  </>
}

export default ToDoList;