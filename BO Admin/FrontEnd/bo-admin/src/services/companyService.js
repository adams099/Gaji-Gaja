import http from "../http-common";

class Company {
  getAll() {
    return http.get("/company/getAll");
  }

  getCompanyById() {
    return http.get("/company/id/{id}");
  }

  addCompany() {
    return http.post("/company/save");
  }
}

export default new Company();
