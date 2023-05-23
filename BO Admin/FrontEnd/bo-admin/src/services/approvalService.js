import http from "../http-common";

class Approval {
  saveApprov(data) {
    return http.post("/approv/save", data);
  }
  getAll() {
    return http.get("/approv/getAll");
  }
}

export default new Approval();
