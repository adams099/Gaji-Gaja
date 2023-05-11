import http from "../http-common";

class User {
  login(data) {
    return http.post("/user/login", data);
  }
  register(data) {
    return http.post("/user/register", data);
  }

  getAll() {
    return http.get("/getAll");
  }

  getUserById() {
    return http.get("/{id}");
  }
}

export default new User();
