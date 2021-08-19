const url = "http://localhost:8080/api/hero/";
const forbidden = "403 Forbidden";

export async function fetchAll() {

  const response = await fetch(url);
  if (response.status !== 200) {
    return Promise.reject(new Error("Fetch failed."));
  }
  return await response.json();
}

export async function fetchHero(heroId) {

  const token = localStorage.getItem('jwt_token');
  if (!token) {
    return Promise.reject(forbidden);
  }

  const init = {
    headers: {
      "Authorization": `Bearer ${token}`
    }
  };

  const response = await fetch(`${url}/${heroId}`, init);
  if (response.status !== 200) {
    return Promise.reject(new Error(`Fetch hero failed, heroId: ${heroId}.`));
  }
  return await response.json();
};

export async function saveHero(hero) {

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
    body: JSON.stringify(hero)
  }

  if (hero.id === 0) {  
    init.method =  "POST";
    const response = await fetch(url, init);
    if (response.status !== 201) {
      return Promise.reject(new Error("POST failed."));
    }
    return await response.json();
  } else {
    init.method = "PUT";
    const response = await fetch(`${url}/${hero.id}`, init);
    if (response.status !== 204) {
      return Promise.reject(new Error("PUT failed."));
    }
  }
}

export async function deleteById(heroId) {
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

  const response = await fetch(`${url}/${heroId}`, init);
  if (response.status !== 204) {
    return Promise.reject("Not 204 No Content");
  }
}