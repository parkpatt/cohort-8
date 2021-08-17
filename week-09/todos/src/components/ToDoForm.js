import { useState, useEffect } from 'react';
import { useHistory, useParams } from 'react-router';

const EMPTY_TODO = {
  id: 0,
  description: ''
};

const url = "http://localhost:8080/api/todos";

function ToDoForm() {

  const [todo, setTodo] = useState(EMPTY_TODO);

  const history = useHistory();
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      fetch(`${url}/${id}`)
        .then(r => {
          if (r.status === 200) {
            return r.json();
          }
          return Promise.reject("Not 200 OK");
        })
        .then(setTodo)
        .catch(console.error);
    }
  }, [id])

  const onChange = (evt) => {
    setTodo({...todo, description: evt.target.value });
  }

  const onSubmit = (evt) => {
    evt.preventDefault();

    const init = {
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify(todo)
    }

    if (todo.id === 0) {
      init.method = "POST";
      fetch(url, init)
        .then(r => {
          if (r.status === 201) {
            return history.push("/");
          }
          return Promise.reject("Not 201 Created");
        })
        .catch(console.error);
    } else {
      init.method = "PUT";
      fetch(`${url}/${todo.id}`, init)
        .then(r => {
          if (r.status === 204) {
            return history.push("/");
          }
          return Promise.reject("Not 204 No Content");
        })
        .catch(console.error);
    }
    history.push("/");
  }

  return <div className="row">
    <form className="col s12" onSubmit={onSubmit}>
      <div className="row">
        <div className="input-field col s12">
          <input id="description" type="text" value={todo.description} onChange={onChange} />
          <label htmlFor="description">Description</label>
        </div>
      </div>
      <div className="row">
        <button type="submit" className="btn waves-effect waves-light">
          Submit
          <i className="material-icons right">send</i>
        </button>
        <button type="button" className="btn blue-grey lighten-2" onClick={() => history.push("/")}>
          Cancel
          <i className="material-icons right">cancel</i>
        </button>
      </div>
    </form>
  </div>
}

export default ToDoForm;
