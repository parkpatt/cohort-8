import { useState, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';

const url = "http://localhost:8080/api/todos";

function ToDoList() {

  const [todos, setTodos] = useState([]);

  const history = useHistory();

  const fetchAll = () => {

    const token = localStorage.getItem('jwt_token');
    if (!token){
      history.push('/login');
    }

    const init = {
      headers: {
        "Authorization": `Bearer ${token}`
      }
    }

    return fetch(url, init)
      .then(r => {
        if (r.status === 200) {
          return r.json();
        }
        return Promise.reject("Not 200 OK");
      })
      .then(setTodos)
  };

  useEffect(() => {
    fetchAll()
      .catch(console.error)
  }, []);

  const deleteToDo = (evt) => {
    const theTodo = todos.find(t => t.id === parseInt(evt.target.value), 10);
    fetch(`${url}/${theTodo.id}`, { method: "DELETE" })
      .then(r => {
        if (r.status === 204) {
          return fetchAll();
        }
        return Promise.reject("Not 204 No Content");
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
              <i className="material-icons">delete_forever</i>
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