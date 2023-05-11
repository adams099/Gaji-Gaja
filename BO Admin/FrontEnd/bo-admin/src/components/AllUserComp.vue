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

              <b-modal ref="my-modal" hide-footer title="Add User Admin">
                <div class="d-block">
                  <div class="form">
                    <div class="form-group">
                      <label for="name">Name</label>
                      <input type="text" class="form-control" id="name" aria-describedby="emailHelp"
                        placeholder="Enter Name" />
                    </div>
                    <div class="form-group">
                      <label for="username">Username</label>
                      <input type="text" class="form-control" id="username" aria-describedby="emailHelp"
                        placeholder="Enter username" />
                    </div>
                    <div class="form-group">
                      <label for="email">Email</label>
                      <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                        placeholder="Enter email" />
                    </div>
                    <div class="form-group">
                      <label for="pass">Password</label>
                      <input type="password" class="form-control" id="pass" aria-describedby="emailHelp"
                        placeholder="Enter password" />
                    </div>
                  </div>
                </div>

                <div class="dflex justify-content-center">
                  <b-button variant="primary" block @click="toggleModal">Submit</b-button>
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
                      <input type="text" class="form-control" id="name" aria-describedby="emailHelp"
                        placeholder="Enter Name" />
                    </div>
                    <div class="form-group">
                      <label for="username">Username</label>
                      <input type="text" class="form-control" id="username" aria-describedby="emailHelp"
                        placeholder="Enter username" />
                    </div>
                    <div class="form-group">
                      <label for="email">Email</label>
                      <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                        placeholder="Enter email" />
                    </div>
                    <div class="form-group">
                      <label for="pass">Password</label>
                      <input type="password" class="form-control" id="pass" aria-describedby="emailHelp"
                        placeholder="Enter password" />
                    </div>
                  </div>
                </div>

                <div class="dflex justify-content-center">
                  <b-button variant="primary" block @click="toggleModal">Update</b-button>
                  <b-button variant="danger" block @click="toggleModal">Cancel</b-button>
                </div>
              </b-modal>
            </div>
            <!---------------------- END UPDATE -------------------------->
          </div>
        </div>
      </div>
      <!---------------------- START USER TABLE -------------------------->
      <table class="table">
        <thead class="text-center">
          <tr>
            <th scope="col">No</th>
            <th scope="col">Nama</th>
            <th scope="col">Username</th>
            <th scope="col">Email</th>
            <th scope="col ">Handle</th>
          </tr>
        </thead>
        <tbody v-if="userData.length > 0">
          <tr v-for="(item, index) in userData" :key="index" class="baris text-center">
            <td scope="row" class="text-center">{{ index + 1 }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.username }}</td>
            <td>{{ item.email }}</td>
            <td class="text-center">
              <button type="button" class="btn btn-success" @click="toggleModal">Update</button>
              <button type="button" class="btn btn-delete">Remove</button>
            </td>
          </tr>
        </tbody>

        <tbody v-else>
          <tr class="msg-tr text-center">
            <td colspan="5" class="msg-null text-center">
              <h3 class="color-text">Saat ini Tidak Ada Data User !</h3>
            </td>
          </tr>
        </tbody>
        <!---------------------- START USER TABLE -------------------------->
      </table>
    </div>
  </section>
</template>

<script>
import userService from '@/services/userService.js';

export default {
  name: "AllUserCompS",

  data() {
    return {
      userData: []
    }
  },


  methods: {
    // MODAL BOX
    showModal() {
      this.$refs["update-modal"].show();
    },
    hideModal() {
      this.$refs["update-modal"].hide();
    },
    toggleModal() {

      this.$refs["update-modal"].toggle("#toggle-btn");
    },

    // GET USER
    getUser() {
      userService
        .getAll()
        .then((response) => {
          this.userData = response.data;
          console.log(this.userData);
        })
        .catch((e) => {
          console.log(e);
        });
    }
  },

  mounted() {
    this.getUser();
  },
};
</script>

<style scoped>
h5 {
  margin-left: 20px;
  color: gray;
}

thead {
  background-color: #695cfe;
  color: aliceblue;
}

.table {
  padding-left: 20px;
  padding-right: 20px;
}

.btn-danger {
  /* margin-left: 10px; */
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
</style>