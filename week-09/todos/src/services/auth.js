const url = "http://localhost:5000"

export async function authenticate(credentials){
  const init = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json"
    },
    body: JSON.stringify(credentials)
  }
  return fetch(`${url}/authenticate`, init)
    .then(response => {
      if (response.status === 200){
        return response.json();
      } else if(response.status === 403){
        return null;
      }
      return Promise.reject("Something went wrong.");
    });
}