<template>
  <section class="home">
    <div class="text text-center color-text">Profile</div>
    <div class="row">
      <div class="col-lg-4 ml-4 mt-4">
        <div class="card mb-4">
          <div class="card-body text-center">
            <img class="rounded-circle img-fluid" src="../assets/profile.jpg" alt="" style="width: 150px" />
            <h5 class="my-3">{{ userData.name }}</h5>
            <p class="text-muted mb-2">{{ userData.roleId === 1 ? "SuperAdmin" : "Admin" }}</p>
            <!-- <p class="text-muted mb-4">Bay Area, San Francisco, CA</p> -->
            <div class="d-flex justify-content-center mb-2">
              <!-- <button type="button" class="btn btn-outline-primary ms-1">
                Edit
              </button> -->
              <div class="ml-3">
                <button type="button" @click="showModal" id="show-btn" class="btn btn-outline-primary ms-1">
                  Change Password
                </button>
                <b-modal ref="changepassword" hide-footer title="Change Password">
                  <div class="d-block">
                    <div class="form">
                      <div class="form-group">
                        <label for="oldpass">Old Password</label>
                        <input type="text" class="form-control" id="oldpass" aria-describedby="emailHelp"
                          placeholder="Input Your Old Password" required v-model="oldpass" />
                      </div>

                      <div class="form-group">
                        <label for="newpass">New Password</label>
                        <input type="password" class="form-control" id="newpass" aria-describedby="emailHelp"
                          placeholder="Input Your New Password" required v-model="newPass" />
                      </div>

                      <div class="form-group">
                        <label for="confnewpass">Confirmation</label>
                        <input type="password" class="form-control" id="confnewpass" aria-describedby="emailHelp"
                          placeholder="Input Your New Password" required v-model="confPass" />
                      </div>
                    </div>
                  </div>

                  <div class="dflex justify-content-center">
                    <b-button variant="primary" block @click="saveChanges">Save</b-button>
                    <b-button variant="danger" block @click="toggleModal">Cancel</b-button>
                  </div>
                </b-modal>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-lg-6 mt-4">
        <div class="card mb-4">
          <div class="card-body">
            <table style="width: 100%">
              <tr>
                <th>Name</th>
                <td>{{ userData.name }}</td>
              </tr>
              <hr />
              <tr>
                <th>Email</th>
                <td>{{ userData.email }}</td>
                <hr />
              </tr>
              <hr />
              <tr>
                <th>Username</th>
                <td>{{ userData.username }}</td>
              </tr>
              <hr />
              <tr>
                <th>Created At</th>
                <td>{{ userData.created }}</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import userService from "@/services/userService";
export default {
  name: "ProfileComp",

  data() {
    return {
      userData: {
        email: null,
        pass: null,
      },
      oldpass: null,
      newPass: null,
      confPass: null,
    };
  },

  methods: {
    //Modal Box
    showModal() {
      this.$refs["changepassword"].show();
      for (const property in this.error) {
        this.error[property] = false;
      }
    },

    hideModal() {
      this.$refs["changepassword"].hide();
    },

    toggleModal() {
      this.$refs["changepassword"].toggle();
    },

    saveChanges() {
      var data = this.userData
      data.pass = this.oldpass
      console.log(data);
      userService
        .login(data)
        .then((response) => {
          if (response.status === 200) {
            if (this.newPass == this.confPass) {
              data.pass = this.newPass
              console.log(data);
            } else {
              this.$toast.warning('New password and confirmation password do not match!', {
                position: 'top-right',
                timeout: 2500,
              });
            }
          }
        })
        .catch((e) => {
          try {
            e.response.status == 404
            this.$toast.warning('Incorrect old password. Please try again!', {
              position: 'top-right',
              timeout: 2500,
            });
          } catch (error) {
            this.$toast.error('Error', {
              position: 'top-right',
              timeout: 2500,
            });
          }
        });
    },

    getUser() {
      let data = this.userData
      data.email = this.$session.get("email")

      userService
        .findEmail(data)
        .then((response) => {
          if (response.status === 200) {
            console.log("success get user");
            console.log(response.status);
            this.userData = response.data;
            // console.log(this.userData.id);
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    this.getUser();
  },
};
</script>

<style></style>