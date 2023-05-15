import http from "../http-common";

class Company {
  getAll() {
    return http.get("/company/getAll");
  }

  upload(data) {
    return http.post("/company/save",data);
  }

  getCompanyById() {
    return http.get("/company/id/{id}");
  }
}

export default new Company();
