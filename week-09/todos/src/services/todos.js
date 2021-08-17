const url = "http://localhost:8080/api/todos";
const forbidden = "403 Forbidden";

export async function findAll() {

  const token = localStorage.getItem('jwt_token');
  if (!token) {
    return Promise.reject(forbidden);
  }

  const init = {
    headers: {
      "Authorization": `Bearer ${token}`
    }
  };

  return fetch(url, init)
    .then(r => {
      if (r.status === 200) {
        return r.json();
      }
      return Promise.reject("Not 200 OK");
    });
};

export async function findById(id) {
  
  const token = localStorage.getItem('jwt_token');
  if (!token) {
    return Promise.reject(forbidden);
  }

  const init = {
    headers: {
      "Authorization": `Bearer ${token}`
    }
  };

  return fetch(`${url}/${id}`, init)
    .then(r => {
      if (r.status === 200) {
        return r.json();
      }
      return Promise.reject("Not 200 OK");
    });
}

export async function save(todo) {

  const token = localStorage.getItem('jwt_token');
    if (!token) {
      return Promise.reject(forbidden);
    }

    const init = {
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
        "Authorization": `Bearer ${token}`
      },
      body: JSON.stringify(todo)
    }

    if (todo.id === 0) {
      init.method = "POST";
      await fetch(url, init)
        .then(r => {
          if (r.status === 201) {
            return r.json();
          }
          return Promise.reject("Not 201 Created");
        });
    } else {
      init.method = "PUT";
      await fetch(`${url}/${todo.id}`, init)
        .then(r => {
          if (r.status !== 204) {
            return Promise.reject("Not 204 No Content");
          }
        });
    }
}

export async function deleteById(id) {

  const token = localStorage.getItem('jwt_token');
  if (!token) {
    return Promise.reject(forbidden);
  }
  
  const init = {
    method: "DELETE",
    headers: {
      "Authorization": `Bearer ${token}`
    }
  };

  await fetch(`${url}/${id}`, init)
    .then(r => {
      if (r.status !== 204) {
        return Promise.reject("Not 204 No Content");
      }
    });
}