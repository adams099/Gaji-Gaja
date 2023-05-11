import http from "../http-common";

class User {
  login(data) {
    return http.post("/user/loginV2", data);
  }
  register(data) {
    return http.post("/user/register", data);
  }

  getAll() {
    return http.get("/user/getAll");
  }

  getUserById() {
    return http.get("/user/id/{id}");
  }
}

export default new User();
