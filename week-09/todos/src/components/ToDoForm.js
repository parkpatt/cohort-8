import { useState, useEffect } from 'react';
import { useHistory, useParams } from 'react-router';
import { findById, save } from '../services/todos';

const EMPTY_TODO = {
  id: 0,
  description: ''
};

function ToDoForm() {

  const [todo, setTodo] = useState(EMPTY_TODO);

  const history = useHistory();
  const { id } = useParams();
  const token = localStorage.getItem('jwt_token');
  const init = {
    headers: {
      "Authorization": `Bearer ${token}`
    }
  }

  useEffect(() => {
    if (id) {
<<<<<<< HEAD
      fetch(`${url}/${id}`, init)
        .then(r => {
          if (r.status === 200) {
            return r.json();
          }
          return Promise.reject("Not 200 OK");
        })
=======
      findById(id)
>>>>>>> 6806cda2ce7ae8e8cd48f2da913a5245a34bbbe2
        .then(setTodo)
        .catch(console.error);
    }
  }, [id, history])

  const onChange = (evt) => {
    setTodo({...todo, description: evt.target.value });
  }

  const onSubmit = (evt) => {
    evt.preventDefault();

    save(todo)
      .then(() => {
        return history.push("/");
      })
      .catch(console.error);
  }

  return <div className="row">
    <form className="col s12" onSubmit={onSubmit}>
      <div className="row">
        <div className="input-field col s12">
          <input id="description" type="text" value={todo.description} onChange={onChange} />
          <label htmlFor="description" className="active">Description</label>
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
