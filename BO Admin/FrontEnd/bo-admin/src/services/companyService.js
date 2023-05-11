import http from "../http-common";

class Company {
  getAll() {
    return http.get("/getAll");
  }

  getCompanyById() {
    return http.get("/id/{id}");
  }
}

export default new Company();
