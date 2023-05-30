import http from "../http-common";

class Company {
  getAll() {
    return http.get("/company/getAll");
  }

  upload(data) {
    return http.post("/company/save", data);
  }

  getCompanyById(id) {
    return http.get(`/company/id/${id}`);
  }

  addCompany() {
    return http.post("/company/save");
  }

  getStatusCount(status) {
    return http.get(`/company/count/${status}`);
  }

  getLength() {
    return http.get(`/company/length`);
  }

  uploadImage(image, companyId) {
    let formData = new FormData();
    formData.append("image", image);

    return http.post(`/att/upload/${companyId}`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }
}

export default new Company();
