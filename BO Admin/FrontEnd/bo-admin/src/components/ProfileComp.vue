<template>
  <section class="home">
    <div class="text text-center color-text">Profile</div>
    <div class="row">
      <div class="col-lg-4 ml-4 mt-4">
        <div class="card mb-4">
          <div class="card-body text-center">
            <img class="rounded-circle img-fluid" src="../assets/profile.jpg" alt="" style="width: 150px" />
            <h5 class="my-3">John Smith</h5>
            <p class="text-muted mb-2">Superadmin</p>
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
                          placeholder="Input Your Old Password" required />
                      </div>

                      <div class="form-group">
                        <label for="newpass">New Password</label>
                        <input type="password" class="form-control" id="newpass" aria-describedby="emailHelp"
                          placeholder="Input Your New Password" required />
                      </div>

                      <div class="form-group">
                        <label for="confnewpass">Confirmation</label>
                        <input type="password" class="form-control" id="confnewpass" aria-describedby="emailHelp"
                          placeholder="Input Your New Password" required />
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
              <tr v-for="item in getUser" :key="item">
                <th>Name</th>
                <td>{{ item.name }}</td>
              </tr>
              <hr />
              <tr v-for="item in getUser" :key="item">
                <th>Email</th>
                <td>{{ item.email }}</td>
                <hr />
              </tr>
              <hr />
              <tr v-for="item in getUser" :key="item">
                <th>Username</th>
                <td>{{ item.username }}</td>
              </tr>
              <hr />
              <tr v-for="item in getUser" :key="item">
                <th>Created By</th>
                <td>{{ item.createdby }}</td>
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
        name: "",
        email: "",
        username: "",
        createdby: "",
      },
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

    saveChanges() { },

    getUser() {
      userService
        .findByUsername()
        .then((response) => {
          this.userData = response.data;
          console.log(this.getUser);
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

<style scoped>
.btn-outline-primary:hover {
  background-color: #695CFE;
}
</style>