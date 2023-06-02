import http from "../http-common";

class Employee {
  getAll() {
    return http.get("/employee/getAll");
  }

  addEmploye() {
    return http.post("/employee/save");
  }

  getEmployeeById(id) {
    return http.get(`/employee/id/${id}`);
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
