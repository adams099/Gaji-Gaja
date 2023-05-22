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

  findEmail(data) {
    return http.post("/user/findEmail", data);
  }

  getAll() {
    return http.get("/user/getAll");
  }

  getUserById() {
    return http.get("/user/id/{id}");
  }

  potp(data){
    return http.post("/user/otp",data)
  }

  cotp(data){
    return http.post("/user/cekotp",data)
  }

  semail(data){
    return http.post("/user/semail",data)
  }
}

export default new User();
