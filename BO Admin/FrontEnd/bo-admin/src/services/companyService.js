import http from "../http-common";

class Company {
  getAll() {
    return http.get("/company/getAll");
  }

  getCompanyById() {
    return http.get("/company/id/{id}");
  }
}

export default new Company();
