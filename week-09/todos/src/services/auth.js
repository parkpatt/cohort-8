<<<<<<< HEAD
const url = "http://localhost:5000"

export async function authenticate(credentials){
=======
const url = "http://localhost:5000";

export async function authenticate(credentials) {
>>>>>>> 6806cda2ce7ae8e8cd48f2da913a5245a34bbbe2
  const init = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json"
    },
    body: JSON.stringify(credentials)
<<<<<<< HEAD
  }
  return fetch(`${url}/authenticate`, init)
    .then(response => {
      if (response.status === 200){
        return response.json();
      } else if(response.status === 403){
        return null;
      }
      return Promise.reject("Something went wrong.");
=======
  };

  return fetch(`${url}/authenticate`, init)
    .then(response => {
      if (response.status === 200) {
        return response.json();
      } else if (response.status === 403) {
        return null;
      }
      return Promise.reject("Something went wrong :(");
>>>>>>> 6806cda2ce7ae8e8cd48f2da913a5245a34bbbe2
    });
}