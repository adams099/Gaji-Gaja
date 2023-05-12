import http from "../http-common";

class User {
  login(data) {
    return http.post("/user/loginV2", data);
  }

  register(data) {
    return http.post("/user/saveV2", data);
  }

  findByEmail(data) {
    return http.post("/user/email", data);
  }

  findByUsername(data) {
    return http.post("/user/username", data);
  }

  getAll() {
    return http.get("/user/getAll");
  }

  getUserById() {
    return http.get("/user/id/{id}");
  }
}

export default new User();
