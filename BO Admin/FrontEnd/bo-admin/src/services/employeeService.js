import http from "../http-common";

class Employee {
  getAll() {
    return http.get("/employee/getAll");
  }

  addEmployee() {
    return http.post("/employee/save");
  }

  getEmployeeById(id) {
    return http.get(`/employee/id/${id}`);
  }

  upload(data) {
    return http.post("/employee/save", data);
  }

  uploadImage(image, employeeId) {
    let formData = new FormData();
    formData.append("imgDoc", image);

    return http.post(`/att/upload/${employeeId}`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }
}

export default new Employee();
