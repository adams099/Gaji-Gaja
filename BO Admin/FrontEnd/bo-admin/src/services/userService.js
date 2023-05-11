import http from "../http-common";

class User {
  login(data) {
    return http.post("/user/loginV2", data);
  }
  register(data) {
    return http.post("/user/saveV2", data);
  }

  getAll() {
    return http.get("/getAll");
  }

  getUserById() {
    return http.get("/{id}");
  }
}

export default new User();
