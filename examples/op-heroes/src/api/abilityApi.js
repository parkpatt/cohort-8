const url = "http://localhost:8080/api/ability";

export async function fetchAll() {
  const response = await fetch(url);
  if (response.status !== 200) {
    return Promise.reject(new Error("Fetch abilities failed."));
  }
  return await response.json();
}