const url = "http://localhost:8080/api/ability";

const abilityApi = {
  fetchAll: async (onSuccess) => {
    try {
      const response = await fetch(url);
      if (response.status !== 200) {
        return Promise.reject(new Error("Fetch abilities failed."));
      }
      const json = await response.json();
      typeof onSuccess === "function" && onSuccess(json);
    } catch (error) {
      console.log(error);
    }
  }
}

export default abilityApi;