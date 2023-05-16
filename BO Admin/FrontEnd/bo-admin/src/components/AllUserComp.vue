<template>
  <section class="home">
    <div class="text text-center color-text">All User</div>
    <div class="table">
      <div class="d-flex flex-row justify-content-between mb-2">
        <h5 class="color-text">Daftar User</h5>
        <div>
          <div class="container mr-3">
            <!-------------------------- START ADD USER ---------------->
            <div>
              <b-button id="show-btn" @click="showModal" class="btn-primary">Add</b-button>
              <b-modal ref="add-modal" hide-footer title="Add User Admin">
                <div class="d-block">
                  <div class="form">
                    <div class="form-group">
                      <label for="name">Name</label>
                      <input type="text" class="form-control" id="name" aria-describedby="emailHelp"
                        placeholder="Enter Name" required v-model="inputData.name" />
                      <span class="valid" v-if="error.name">Nama harus diisi!</span>
                    </div>
                    <div class="form-group">
                      <label for="username">Username</label>
                      <input type="text" class="form-control" id="username" aria-describedby="emailHelp"
                        placeholder="Enter username" required v-model="inputData.username" />
                      <span class="valid" v-if="error.usernameada">Username sudah ada</span>
                      <span class="valid" v-if="error.username">Username harus diisi!</span>
                    </div>
                    <div class="form-group">
                      <label for="email">Email</label>
                      <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                        placeholder="Enter email" required v-model="inputData.email" />
                      <span class="valid" v-if="error.emailada">Email sudah ada</span>
                      <span class="valid" v-if="error.email">Email harus diisi!</span>
                    </div>
                    <div class="form-group">
                      <label for="pass">Password</label>
                      <input type="password" class="form-control" id="pass" aria-describedby="emailHelp"
                        placeholder="Enter password" required v-model="inputData.pass" />
                      <span class="valid" v-if="error.pass">Password harus diisi!</span>
                    </div>
                  </div>
                </div>

                <div class="dflex justify-content-center">
                  <b-button variant="primary" block @click="addUser">Update</b-button>
                  <b-button variant="danger" block @click="toggleModal">Cancel</b-button>
                </div>
              </b-modal>
            </div>
            <!------------------------ END ADD USER ------------------------->

            <!---------------------- START UPDATE -------------------------->
            <div>
              <b-modal ref="update-modal" hide-footer title="Update User Data">
                <div class="d-block">
                  <div class="form">
                    <div class="form-group">
                      <label for="name">Name</label>
                      <input type="text" class="form-control" id="name" placeholder="Enter Name" />
                    </div>
                    <div class="form-group">
                      <label for="username">Username</label>
                      <input type="text" class="form-control" id="username" placeholder="Enter username" />
                    </div>
                    <div class="form-group">
                      <label for="email">Email</label>
                      <input type="email" class="form-control" id="email" placeholder="Enter email" />
                    </div>
                    <div class="form-group">
                      <label for="pass">Password</label>
                      <input type="password" class="form-control" id="pass" placeholder="Enter password" />
                    </div>
                  </div>
                </div>

                <div class="dflex justify-content-center">
                  <b-button variant="primary" block @click="showModal">Update</b-button>
                  <b-button variant="danger" block @click="toggleModal">Cancel</b-button>
                </div>
              </b-modal>
            </div>
            <!---------------------- END UPDATE -------------------------->
            <div>
                                <b-modal v-model="showModalStatus">
                                    <b-modal v-model="showModalStatus" hide-footer>
                                        <div>
                                            <h6> Status Confirmation</h6>
                                            <!-- <p>Are you sure you want to perform this action?</p> -->
                                            <b-col>
                                                <div class="initombol">
                                                    <button @click="accept" class="btn btn-success mr-3">Accept</button>
                                                    <button @click="reject" class="btn btn-danger mr-3">Reject</button>
                                                    <button @click="deadactive"
                                                        class="btn btn-primary mr-3">Deadactive</button>
                                                </div>
                                            </b-col>
                                            <!-- <div class="back-button ">
                                            <button @click="showModalStatus = false"
                                                class="btn btn-secondary mt-3 ml-3 shadow">Back</button>
                                        </div> -->
                                        </div>
                                    </b-modal>
                                </b-modal>
                            </div>
          </div>
        </div>
      </div>
      <!---------------------- START USER TABLE -------------------------->
      <table class="table ">
        <thead class="text-center">
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Nama</th>
            <th scope="col">Username</th>
            <th scope="col">Email</th>
            <th scope="col ">Status</th>
            <th scope="col ">Action</th>
          </tr>
        </thead>
        <tbody class="shadow">
          <tr v-for="(item, index) in showData" :key="index" class="baris text-center bg-white ">
            <td scope="row" class="text-center">{{ item.id }}</td>
            <td> {{ item?.name }}</td>
            <td> {{ item?.username }} </td>
            <td> {{ item?.email }}</td>
            <!-- <td class="pp color-text" :style="{ color: 'green' }"> In Review</td> -->
            <button type="button" class="status blue" v-if="item.statId == 1">In Review</button>
                        <button type="button" class="status green" v-else-if="item.statId == 2">Active</button>
                        <button type="button" class="status red" v-else-if="item.statId == 3">Rejected</button>
                        <button type="button" class="status salmon" v-else>Deactive</button>
            <td class="text-center">
              <button type="button" class="btn btn-primary" @click="toggleModal">Update</button>
              <button type="button" class="btn btn-delete text-white "
                                @click="showModalStatuss(item)">Status</button>
            </td>
          </tr>
        </tbody>

        <!-- <tbody v-else>
          <tr class="msg-tr text-center">
            <td colspan="5" class="msg-null text-center">
              <h3 class="color-text">Saat ini Tidak Ada Data User !</h3>
            </td>
          </tr>
        </tbody> -->
        <!---------------------- START USER TABLE -------------------------->
      </table>
      <div class="row d-flex justify-content-center next">
        <button v-if="this.indexing > 1" type="button" @click="LimitData(1)" class="btn btn-success"
          style="margin-left: 5px;">Previous</button>
        <button class="ml-4 mr-4 font-italic mt-2 btn">{{ indexing }}</button>
        <button v-if="this.indexing < this.maxdata" type="button" @click="LimitData(2)"
          class="btn btn-success">Next</button>
      </div>
    </div>
  </section>
</template>

<script>
import userService from '@/services/userService.js';

export default {
  name: "AllUserCompS",

  // DATA
  data() {
    return {
      showModalStatus: false,
      userData: {
      },
      inputData: {
        username: null,
        name: null,
        email: null,
        pass: null,
        roleId: null,
        statId: null,
        createdBy: null,
        approved: null,
      },
      updateData: {
        id: null,
        username: null,
        name: null,
        email: null,
        pass: null,
        roleId: null,
        statId: null,
        createdBy: null,
        approved: null,
      },
      showData: {
      },
      error: {
        "username": false,
        "usernameada": false,
        "name": false,
        "email": false,
        "emailada": false,
        "pass": false,
      },
      indexing: 1,
      maxdata: null,
      dikurangin: null,
    }
  },

  // METHODS
  methods: {
    accept() {
            // handle accept action
            let data = this.updateData;
            data.statId = 2;
           userService.register(data)
                .then((response) => {
                    this.$toast.success('Company status has been successfully Update!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    console.log(response.status);
                    this.showModalStatus = false;
                    this.getUser()
                })
                .catch(() => {
                    this.$toast.error('Error', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                });
        },
        reject() {
            // handle reject action
            let data = this.updateData;
            data.statId = 3;
           userService.register(data)
                .then((response) => {
                    this.$toast.success('Company status has been successfully Rejected!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    console.log(response.data);
                    this.showModalStatus = false;
                    this.getUser()
                })
                .catch(() => {
                    this.$toast.error('Error', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                });
        },
        deadactive() {
            // handle review action
            let data = this.updateData;
            data.statId = 4;
           userService.register(data)
                .then((response) => {
                    this.$toast.success('Company status has been successfully Deactive!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    console.log(response.status);
                    this.showModalStatus = false;
                    this.getUser()
                })
                .catch(() => {
                    this.$toast.error('Error', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                });
        },

    showModalStatuss(test) {
            this.showModalStatus = !this.showModalStatus
            // console.log(test);
            console.log("set data");
            for (const property in this.updateData) {
                this.updateData[property] = test[property];
            }
        },

    showToast() {
      this.$toast.success('Input submitted successfully!', {
        position: 'top-right',
        timeout: 2500,
      });
    },

    // MODAL BOX
    showModal() {
      this.$refs["add-modal"].show();
      for (const property in this.error) {
        this.error[property] = false;
      }
      for (const property in this.inputData) {
        this.inputData[property] = null;
      }
    },
    hideModal() {
      this.$refs["add-modal"].hide();
    },

    async addUser() {
      for (const property in this.error) {
        this.error[property] = false;
      }
      
      let data = this.inputData;
      data.roleId = 2;
      data.statId = 2;
      data.createdBy = this.$session.get("email");
      for (const property in data) {
        if (data[property] == null || data[property] == "") {
          this.error[property] = true;
        }
      }
      if (this.error['username'] == false && this.error['name'] == false && this.error['email'] == false && this.error['pass'] == false) {

        await userService
          .findByEmail(data)
          .then((response) => {
            if (response.status == 200) {
              this.error['emailada'] = true
            }
          })
          .catch(() => {
            console.log("valid-email");
          });

        await userService
          .findByUsername(data)
          .then((response) => {
            if (response.status == 200) {
              this.error['usernameada'] = true
            }
          })
          .catch(() => {
            console.log("valid-username");
          });

        if (this.error['usernameada'] == false && this.error['emailada'] == false) {

          userService
            .register(data)
            .then((response) => {
              if (response.status == 201) {
                // console.log(response.data);
                console.log("success regis user");
                this.$refs["add-modal"].toggle("#toggle-btn");
                this.getUser()
                this.showToast();
              }
            })
            .catch((e) => {
              console.log(e);
            });
        }

      }

    },

    toggleModal() {
      this.$refs["add-modal"].toggle("#toggle-btn");
    },

    LimitData(nigga) {
      if (nigga == 1) {
        this.indexing--
      } else if (nigga == 2) {
        this.indexing++
      }

      var inw = this.indexing - 1;
      var awal = inw * 7;
      var akhir = this.indexing * 7;
      if (this.indexing == this.maxdata && this.dikurangin < 7) {
        var testos = akhir - this.dikurangin
        this.showData = []
        for (let ih = awal; ih < testos; ih++) {
          // console.log(this.userData[ih]);
          this.showData.push(this.userData[ih])
          // console.log(this.showData);
        }

      } else {
        this.showData = []
        for (let ih = awal; ih < akhir; ih++) {
          this.showData.push(this.userData[ih])
        }
      }
      // console.log(this.showData)
    },

    // GET USER
    getUser() {
      userService
        .getAll()
        .then((response) => {
          this.userData = response.data;
          var njir = this.userData.length / 7
          this.maxdata = Math.ceil(njir)
          var suiiii = this.userData.length % 7
          this.dikurangin = 7 - suiiii
          console.log(this.dikurangin);
          this.LimitData();
        })
        .catch((e) => {
          console.log(e);
        });
    }


  },


  created() {
    this.getUser();
  }
};
</script>

<style scoped>
.initombol {
    text-align: center;
}

h5 {
  margin-left: 20px;
  color: gray;
}

thead {
  background-color: #695cfe;
  color: aliceblue;
}

.valid {
  font-size: 12px;
  color: rgb(255, 47, 47);
  font-style: italic;
}

.table {
  padding-left: 20px;
  padding-right: 20px;
}


table {
  border-collapse: separate !important;
  border-spacing: 0 !important;
}

table tr th,
table tr td {
  border-right: 1px solid #dee2e6 !important;
  border-bottom: 1px solid #dee2e6 !important;
}

table tr th:first-child,
table tr td:first-child {
  border-left: 1px solid #dee2e6 !important;
}

table tr th {
  border-top: 1px solid #dee2e6 !important;
}

/* top-left border-radius */
table tr:first-child th:first-child {
  border-top-left-radius: 0.25rem !important;
}

/* top-right border-radius */
table tr:first-child th:last-child {
  border-top-right-radius: 0.25rem !important;
}

/* bottom-left border-radius */
table tr:last-child td:first-child {
  border-bottom-left-radius: 0.25rem !important;
}

/* bottom-right border-radius */
table tr:last-child td:last-child {
  border-bottom-right-radius: 0.25rem !important;
}

.add-user {
  background-color: #695cfe;
  color: whitesmoke;
}

.show-botton {
  background-color: #695cfe;
}

.show-botton:hover {
  background-color: #695cfe;
}

.form-select {
  width: 100%;
  height: 40px;
  border: 1px solid rgb(200, 200, 200);
  border-radius: 5px;
}

.btn-primary {
  background-color: #695cfe;
}

.table {
  padding-left: 20px;
  padding-right: 20px;
}

.btn-delete {
  margin-left: 10px;
  background-color: rgb(195, 10, 10);
  color: white;
}

.baris {
  background-color: #e4e9f7;
}

.next {
  /* background-color: #695cfe; */
  position: fixed;
  bottom: 0;
  left: 700px;
  margin-bottom: 10px;
}

.status {
  margin-top: 17px;
  background-color: transparent;
  border-radius: 5px;
  font-size: 13px;
  padding: 3px;
}

.blue {
    border: 2px solid blue;
    color: blue;
}

.salmon {
    border: 2px solid salmon;
    color: salmon;
}

.red {
    border: 2px solid red;
    color: red;
}

.green {
    border: 2px solid green;
    color: green;
}

.btn-primary {
    background-color: #695cfe;
}
</style>