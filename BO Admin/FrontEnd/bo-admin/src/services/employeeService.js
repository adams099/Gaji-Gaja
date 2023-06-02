import http from "../http-common";

class Employee {
  getAll() {
    return http.get("/employee/getAll");
  }

  upload(data) {
    return http.post("/employee/save", data);
  }

  getEmployeeById(id) {
    return http.get(`/employee/id/${id}`);
  }

  addEmployee() {
    return http.post("/employee/save");
  }

  getStatusCount(status) {
    return http.get(`/employee/count/${status}`);
  }

  getLength() {
    return http.get(`/employee/length`);
  }

  uploadImage(image, employeeId) {
    let formData = new FormData();
    formData.append("image", image);

    return http.post(`/att/upload/${employeeId}`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }
}

export default new Employee();
