import http from "../http-common";

class Approval {

  saveApprov(data) {
    return http.post("/approv/save", data);
  }
}

export default new Approval();
