<template>
  <section class="home">
    <div class="text text-center color-text">All User</div>
    <div class="table">
      <div class="d-flex flex-row justify-content-between mb-2">
        <h5 class="color-text">{{ roleId == 1 ? '' : 'Daftar Admin Company' }}</h5>
        <div>
          <div class="container mr-3">
            <!-------------------------- START ADD USER ---------------->
            <div>
              <template>
                <b-button id="show-btn" @click="showModalAdd" class="btn-add">
                  <i class="fa-sharp fa-regular fa-plus"></i> {{ roleId == 1 ? 'Admin' : 'Admin Company' }}
                </b-button>
              </template>

              <b-modal ref="add-modal" hide-footer title="Add Admin User">
                <div class="d-block">
                  <div class="form">
                    <div class="form-group">
                      <label for="name">Name</label>
                      <input type="text" class="form-control" id="name" placeholder="Enter Name" required
                        v-model="inputData.name" />
                      <span class="valid" v-if="error.name">Nama harus diisi!</span>
                    </div>

                    <div class="form-group">
                      <label for="phone_number">Phone Number</label>
                      <input type="text" class="form-control" id="phone_number" placeholder="Enter Phone Number" required
                        v-model="inputData.phone" />
                      <span class="valid" v-if="error.phoneAda">Phone Number sudah ada</span>
                      <span class="valid" v-if="error.phone">Phone Number diisi!</span>
                    </div>

                    <div class="form-group">
                      <label for="email">Email</label>
                      <input type="email" class="form-control" id="email" placeholder="Enter email" required
                        v-model="inputData.email" />
                      <span class="valid" v-if="error.emailada">Email sudah ada</span>
                      <span class="valid" v-if="error.email">Email harus diisi!</span>
                    </div>

                    <!-- <div class="form-group" v-if="false">
                      <label for="pass">Password</label>
                      <input type="password" class="form-control" id="pass" placeholder="Enter password" required
                        :readonly="!" v-model="inputData.pass" />
                      <span class="valid" v-if="error.pass">Password harus diisi!</span>
                    </div> -->

                  </div>
                </div>

                <div class="dflex justify-content-center">
                  <b-button variant="primary" block @click="addUser">Save</b-button>
                  <b-button variant="danger" block @click="toggleModal">Cancel</b-button>
                </div>
              </b-modal>
            </div>
            <!------------------------ END ADD USER ------------------------->


            <!-- modal detail -->
            <div>
              <b-modal ref="up-modal" hide-footer title="Add Admin User">
                <div class="d-block">

                  <div class="row d-flex flex-row justify-content-center">
                    <h6 class="text-center mr-2 text-secondary">
                      Status User Saat ini
                    </h6>
                    <h6 :class="colorStatus">{{ status }}</h6>
                  </div>

                  <div class="form">
                    <div class="form-group">
                      <label for="name">Name</label>
                      <input type="text" class="form-control" id="name" placeholder="Enter Name" required readonly
                        v-model="detail.name" />
                    </div>

                    <div class="form-group">
                      <label for="phone_number">Phone Number</label>
                      <input type="text" class="form-control" id="phone_number" placeholder="Enter Phone Number" required
                        readonly v-model="detail.phone" />
                    </div>

                    <div class="form-group">
                      <label for="email">Email</label>
                      <input type="email" class="form-control" id="email" placeholder="Enter email" required readonly
                        v-model="detail.email" />
                    </div>

                    <div class="form-group">
                      <label for="creationDate">Creation Date</label>
                      <input type="creationDate" class="form-control" id="creationDate" readonly
                        v-model="detail.created" />
                    </div>
                    <div class="form-group">
                      <label for="lastUpdated">Last Updated</label>
                      <input type="lastUpdated" class="form-control" id="lastUpdated" readonly v-model="detail.update" />
                    </div>

                    <div class="form-group">
                      <label for="lastLogin">Last Login</label>
                      <input type="lastLogin" class="form-control" id="lastLogin" readonly v-model="detail.last" />
                    </div>
                  </div>
                </div>
                <div class="dflex justify-content-center">
                  <b-button variant="warning" class="text-white" block v-if="detail.statId == 2 || detail.statId == 4"
                    @click.prevent="deactiveAlert()">
                    {{ actBtn }}
                  </b-button>
                  <b-button variant="danger" block @click="toggleModalUps">Cancel</b-button>
                </div>
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
            <th scope="col">Email</th>
            <th scope="col ">Status</th>
            <th scope="col ">Action</th>
          </tr>
        </thead>
        <tbody class="shadow">
          <tr v-for="(item, index) in showData" :key="index" class="baris text-center bg-white ">
            <td scope="row" class="text-center">{{ item.id }}</td>
            <td> {{ item?.name }}</td>
            <td> {{ item?.email }}</td>
            <!-- <td class="pp color-text" :style="{ color: 'green' }"> In Review</td> -->
            <button type="button" class="status blue" v-if="item.statId == 1">In Review</button>
            <button type="button" class="status green" v-else-if="item.statId == 2">Active</button>
            <button type="button" class="status red" v-else-if="item.statId == 3">Rejected</button>
            <button type="button" class="status red" v-else>Deactive</button>
            <td class="text-center">
              <button type="button" class="btn btn-primary" @click="toggleModalUp(item)">Detail</button>
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
      userData: {},
      roleId: this.$session.get("jwt").roleId,

      userCredential: {
        id: null,
        email: null,
        companyId: null,
      },

      inputData: {
        id: null,
        name: null,
        email: null,
        pass: null,
        roleId: null,
        statId: null,
        createdBy: null,
        approved: null,
        phone: null,
        companyId: null,
      },

      detail: {
        id: null,
        name: null,
        email: null,
        pass: null,
        roleId: null,
        statId: null,
        createdBy: null,
        approved: null,
        phone: null,
        created: null,
        update: null,
        last: null,
      },

      showData: {},

      error: {
        "name": false,
        "email": false,
        "emailada": false,
        "phoneAda": false,
        "phone": false,
        "pass": false,
      },
      semail: {
        "email": null,
        "subject": null,
        "body": null
      },

      indexing: 1,
      maxdata: null,
      dikurangin: null,
      colorStatus: null,
      status: null,
      actBtn: null,
      comId: null,
    }
  },

  // METHODS
  methods: {
    getUsers() {
      if (this.roleId === 3) {
        this.getData()
      } else {
        this.getUser();
      }
    },
    deactiveAlert() {
      let data = this.detail;

      this.$swal({
        title: "Are you sure?",
        text: "You can't revert your action",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: data.statId === 2 ? "Deactive it!" : "Reactive it!",
        cancelButtonText: "No, Cancel!",
        showCloseButton: true,
        showLoaderOnConfirm: true,
      }).then((result) => {
        if (result.value) {
          if (data.statId === 2) {
            this.deactiveFunc();
          } else {
            this.reactiveFunc();
          }
        } else {
          this.$swal({
            confirmButtonText: "Close",
            icon: "error",
            title: "Cancelled",
            text: "Your file is still intact",
          });
        }
      });
    },

    deactiveFunc() {
      let data = this.detail;
      data.statId = 4;
      userService.register(data)
        .then((response) => {
          this.$toast.success('User status has been successfully Deactive!', {
            position: 'top-right',
            timeout: 2500,
          });
          console.log(response.status);
          this.toggleModalUps();
          this.getUsers()
        })
        .catch(() => {
          this.$toast.error('Error', {
            position: 'top-right',
            timeout: 2500,
          });
        });
    },

    reactiveFunc() {
      const length = 8;
      const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
      let result = "";
      for (const property in this.error) {
        this.error[property] = false;
      }
      for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        result += characters.charAt(randomIndex);
      }

      let data = this.detail;
      data.pass = result;
      data.update = null,
        data.last = null,
        data.statId = 2;

      userService.register(data)
        .then((response) => {
          this.semail.email = data.email
          this.semail.subject = "Account Reactivated: Welcome Back!"
          this.semail.body = data.pass

          userService.asemail(this.semail).then(() => {
            this.$toast.success('User status has been successfully Active!', {
              position: 'top-right',
              timeout: 2500,
            });
          }).catch((e) => {
            console.log(e);
            try {
              e["code"] === "ERR_NETWORK";
              console.log(e["code"]);
              this.$toast.error("ERROR NETWORK CONNECTION", {
                position: "top-right",
                timeout: 2500,
              });
            } catch (error) {
              this.$toast.error('Failed to send the password to the email!', {
                position: 'top-right',
                timeout: 2500,
              });
            }
          });

          console.log(response.status)
          this.toggleModalUps();
          this.getUsers()
        })
        .catch(() => {
          this.$toast.error('Error', {
            position: 'top-right',
            timeout: 2500,
          });
        });
    },

    showToast() {
      this.$toast.success('Input submitted successfully!', {
        position: 'top-right',
        timeout: 2500,
      });
    },

    // MODAL BOX
    showModalAdd() {
      this.$refs["add-modal"].show();

      for (const property in this.error) {
        this.error[property] = false;
      }
      for (const property in this.inputData) {
        this.inputData[property] = null;
      }
    },

    toggleModalUp(data) {
      this.detail = data;

      if (data.statId == 1) {
        this.status = "In Review";
        this.colorStatus = "review";
      } else if (data.statId == 2) {
        this.status = "Active";
        this.colorStatus = "active";
        this.actBtn = "Deactive";
      } else if (data.statId == 3) {
        this.status = "Reject";
        this.colorStatus = "reject";
      } else {
        this.status = "Deactive";
        this.colorStatus = "deactive";
        this.actBtn = "Reactive";
      }

      this.toggleModalUps()
    },

    toggleModal() {
      this.$refs["add-modal"].toggle("#toggle-btn");
    },

    toggleModalUps() {
      this.$refs["up-modal"].toggle("#toggle-btn");
    },

    hideModal() {
      this.$refs["add-modal"].hide();
    },

    async getData() {
      try {
        await this.getUserCredential();
        this.getUsersByComId();
      } catch (error) {
        console.log(error);
      }
    },

    getUserCredential() {
      return new Promise((resolve, reject) => {
        let data = this.userCredential;
        data.email = this.$session.get("email");

        userService
          .findEmail(data)
          .then((response) => {
            if (response.status === 200) {
              this.comId = response.data.companyId;
              resolve(); // Menandakan bahwa getUser telah selesai dengan baik
            } else {
              reject("Error: getUser failed"); // Menandakan bahwa getUser gagal
            }
          })
          .catch((e) => {
            reject(e);
          });
      });
    },

    getUsersByComId() {
      let data = this.comId;

      userService
        .getAllByComId(data)
        .then((response) => {
          this.userData = response.data;
          var njir = this.userData.length / 7
          this.maxdata = Math.ceil(njir)
          var suiiii = this.userData.length % 7
          this.dikurangin = 7 - suiiii
          this.LimitData();
        })
        .catch((e) => {
          console.log(e);
        });
    },

    async addUser() {
      const length = 8;
      const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
      let result = "";
      for (const property in this.error) {
        this.error[property] = false;
      }

      for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        result += characters.charAt(randomIndex);
      }

      let data = this.inputData;
      data.pass = result
      data.roleId = 2;
      data.statId = 2;
      data.createdBy = this.$session.get("email");

      for (const property in data) {
        if (data[property] == null || data[property] == "") {
          this.error[property] = true;
        }
      }
      if (this.error['name'] == false && this.error['email'] == false && this.error['pass'] == false) {

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

        if (this.error['emailada'] == false) {
          // if login role 3, set role 4 & set companyId

          if (this.roleId === 3) {
            data.roleId = 4;
            data.companyId = this.comId;
          }

          userService
            .register(data)
            .then((response) => {
              if (response.status == 201) {
                this.semail.email = data.email
                this.semail.subject = "Your Credential Login"
                this.semail.body = data.pass

                userService.asemail(this.semail).then(() => {
                  this.showToast();
                }).catch((e) => {
                  console.log(e);
                  try {
                    e["code"] === "ERR_NETWORK";
                    console.log(e["code"]);
                    this.$toast.error("ERROR NETWORK CONNECTION", {
                      position: "top-right",
                      timeout: 2500,
                    });
                  } catch (error) {
                    this.$toast.error('Failed to send the password to the email!', {
                      position: 'top-right',
                      timeout: 2500,
                    });
                  }
                });

                // console.log(response.data);
                console.log("success regis user");
                this.$refs["add-modal"].toggle("#toggle-btn");
                this.getUsers()
              }
            })
            .catch((e) => {
              console.log(e);
              this.$toast.error('Error!', {
                position: 'top-right',
                timeout: 2500,
              });
            });
        }

      }

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
          // console.log(this.dikurangin);
          this.LimitData();
        })
        .catch((e) => {
          try {
            e["code"] === "ERR_NETWORK";
            console.log(e["code"]);
            this.$toast.error("ERROR NETWORK CONNECTION", {
              position: "top-right",
              timeout: 2500,
            });
          } catch (error) {
            console.log(e);
          }
        });
    },
  },


  created() {
    this.getUsers();
  },
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

.red {
  border: 2px solid red;
  color: red;
}

.green {
  border: 2px solid green;
  color: green;
}

.btn-add {
  background-color: #695cfe;
  color: white;
  border: none;
  border-radius: 10px;
  width: auto;
}

.btn-add:hover {
  background-color: #6155e4;
  color: white;
  border: none;
}

.review {
  color: rgb(88, 88, 233);
  font-weight: 600;
}

.active {
  color: green;
  font-weight: 600;
}

.reject {
  color: red;
  font-weight: 600;
}

.deactive {
  color: red;
  font-weight: 600;
}
</style>