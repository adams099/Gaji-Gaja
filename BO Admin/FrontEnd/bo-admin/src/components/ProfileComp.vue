<template>
  <section class="home h-100">
    <div class="text text-center color-text">
      {{ isFormVisible ? "Change Password Page" : "Profile" }}
    </div>

    <!-- CHANGE PASSWORD -->
    <div v-show="isFormVisible" class="col-md-11 mt-5">
      <form class="change-password shadow-lg col-md-6  " @submit.prevent="saveChanges">
        <h6 class="change-text text-center mb-4">Change Password</h6>
        <!-- old password -->
        <div class="form-group d-flex flex-column align-items-center">
          <div class="form-group col-md-11 ">
            <label for="old-password">Old Password</label>
            <div class="row">
              <input :type="showPassword ? 'text' : 'password'" class="form-control" id="old-password" required
                placeholder="Old Password" autocomplete="off" v-model="currentPass">
              <i class="fas fass " :class="showPassword ? 'fa-eye ' : 'fa-eye-slash'" @click="toggleShowPassword(1)"></i>
            </div>
          </div>

          <!-- new password -->
          <div class="form-group col-md-11">
            <label for="new-password">New Password</label>
            <div class="row">
              <input :type="showNewPassword ? 'text' : 'password'" class="form-control " id="new-password" required
                placeholder="New Password" autocomplete="off" v-model="newPass">
              <i class="fas fass " :class="showNewPassword ? 'fa-eye ' : 'fa-eye-slash'"
                @click="toggleShowPassword(2)"></i>
            </div>
          </div>
          <!-- confirm password -->
          <div class="form-group col-md-11">
            <label for="confirm-password">Confirm Password</label>
            <div class="row">
              <input :type="showConfirmPassword ? 'text' : 'password'" class="form-control " id="confirm-password"
                required placeholder="Confirm Password" autocomplete="off" v-model="confPass">
              <i class="fas fass " :class="showConfirmPassword ? 'fa-eye ' : 'fa-eye-slash'"
                @click="toggleShowPassword(3)"></i>
            </div>
          </div>
          <div class="col align-items-center d-flex flex-column">
            <button class="btn submit btn-primary mt-4 row" type="submit">Save</button>
            <button class="btn btn-outline-secondary submit mt-3 row" v-if="isCancle" @click="isFormVisible = false">
              Cancel
            </button>
          </div>
        </div>
      </form>
    </div>
    <div class="row">
      <!-- change password -->

      <div class="col-lg-4 ml-5 mt-4">
        <!-- CARD 1 -->
        <div class="card mb-1 shadow-lg" v-show="!isFormVisible">
          <div class="text-center">
            <img class="rounded-circle img-fluid" src="../assets/suzume.jpg" alt="" style="width: 150px; height: 150px" />

            <h5 class="my-3">{{ name | toUpperCase }}</h5>
            <p class="text-muted mb-2">
              {{ userData.roleId === 1 ? "SuperAdmin" : "Admin" }}
            </p>
            <div class="d-flex justify-content-center mb-2">
              <div class="ml-3">
                <button type="button" id="show-btn" class="btn btn-outline-primary ms-1 mt-2 btn-change py-3 "
                  @click="toggleFormVisibility">
                  Change Passwd
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- CARD 2 -->
      <div class="col-lg-6 mt-4 card-dua shadow-lg ml-4" v-show="!isFormVisible">
        <div class="mb-4">
          <div class="table">
            <table style="width: 100%">
              <tr>
                <th>Name</th>
                <td>{{ userData.name | toUpperCase }}</td>
              </tr>
              <!-- <hr /> -->
              <tr>
                <th>Email</th>
                <td>{{ userData.email }}</td>
                <hr />
              </tr>
              <!-- <hr /> -->
              <tr>
                <th>Phone Number</th>
                <td>{{ userData.phone ? userData.phone : "No hp kosong" }}</td>
              </tr>
              <!-- <hr /> -->
              <tr>
                <th>Created At</th>
                <td>{{ userData.created }}</td>
              </tr>
            </table>
            <hr />
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import userService from "@/services/userService";
import ChangePaswd from "@/components/ChangePaswdComp.vue";
export default {
  name: "ProfileComp",
  comments: {
    ChangePaswd,
  },

  data() {
    return {
      name: null,
      userData: {
        id: null,
        email: null,
        phone: null,
        pass: null,
        update: null,
      },
      currentPass: null,
      newPass: null,
      confPass: null,
      isFormVisible: false,
      isCancle: true,
      title: null,
      showPassword: false,
      showNewPassword: false,
      showConfirmPassword: false,
      item: null
    };
  },

  filters: {
    toUpperCase: function (value) {
      if (!value) return "";
      return value.toString().toUpperCase();
    },
  },

  methods: {
    toggleFormVisibility() {
      this.isFormVisible = !this.isFormVisible;
    },
    Logout() {
      this.$session.destroy();
      this.$router.replace("/auth");
    },

    // SHOW / HIDE PASSWORD
    toggleShowPassword(item) {
      if (item == 1) {
        this.showPassword = !this.showPassword
      } else if (item == 2) {
        this.showNewPassword = !this.showNewPassword
      } else {
        this.showConfirmPassword = !this.showConfirmPassword
      }
    },

    saveChanges() {
      const regex = /^(?=.*[a-zA-Z])(?=.*\d).+$/;
      var data = this.userData;
      data.pass = this.currentPass;
      console.log(regex.test(this.newPass));

      if (this.newPass.length >= 8) {
        if (regex.test(this.newPass)) {
          //post data
          userService
            .login(data)
            .then((response) => {
              if (response.status === 200) {
                if (this.newPass == this.confPass) {
                  data.pass = this.newPass;
                  userService
                    .register(data)
                    .then((response) => {
                      if (response.status == 201) {
                        // console.log(response.data);
                        this.$toast.success("Success change password!", {
                          position: "top-right",
                          timeout: 2500,
                        });
                        this.Logout();
                      }
                    })
                    .catch(() => {
                      this.$toast.error("Error", {
                        position: "top-right",
                        timeout: 2500,
                      });
                    });
                } else {
                  this.$toast.warning(
                    "New password and confirmation password do not match!",
                    {
                      position: "top-right",
                      timeout: 2500,
                    }
                  );
                }
              }
            })
            .catch((e) => {
              try {
                e.response.status == 404;
                this.$toast.warning(
                  "Incorrect old password. Please try again!",
                  {
                    position: "top-right",
                    timeout: 2500,
                  }
                );
              } catch (error) {
                this.$toast.error("Error", {
                  position: "top-right",
                  timeout: 2500,
                });
              }
            });
        } else {
          //warning combination
          this.$toast.warning(
            "The password must consist of a combination of letters and numbers!",
            {
              position: "top-right",
              timeout: 2500,
            }
          );
        }
      } else {
        // warning character
        this.$toast.warning("Your password is must more than 8 character!", {
          position: "top-right",
          timeout: 2500,
        });
      }
    },

    // GET USE
    getUser() {
      let data = this.userData;
      data.email = this.$session.get("email");

      userService
        .findEmail(data)
        .then((response) => {
          if (response.status === 200) {
            let oldName = response.data.name;
            let first = oldName.split(" ")[0];
            let second = oldName.split(" ")[1];
            if (second == null || second == undefined) {
              this.name = first;
            } else {
              this.name = first + " " + second;
            }

            console.log("success get user");
            console.log(response.status);
            this.userData = response.data;
            if (response.data.update == null) {
              this.isFormVisible = !this.isFormVisible;
              this.isCancle = false;
            }
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

<style scoped>
.card {
  border-radius: 20px;
  background-color: white;
  padding: 20px;
}

.card-dua {
  background-color: white;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
}

.btn-outline-primary:hover {
  background-color: #695cfe;
}

.btn-outline-primary {
  padding: 5px 20px;
  border-radius: 15px;
}

.table {
  margin-top: 40px;
  margin-bottom: 0;
  margin-left: 10px;
}

img:hover {
  cursor: pointer;
  transform: scale(1.2);
  transition: transform 400ms;
  border-radius: 1px;
}

.primary {
  background-color: #695cfe;
  border: none;
}

.primary:hover {
  background-color: #5549da;
}

/* change password */
.change-password {
  background-color: white;
  padding: 50px 50px;
  border-radius: 25px;
  margin: auto;
  align-content: center;
  right: 0px;
}

.submit {
  color: white;
  width: 100%;
  border-radius: 10px;
}

.btn-primary {
  background-color: #695cfe;
  height: 50px;
  border-radius: 15px;
}

.btn-primary:hover {
  background-color: #5549da;
}

.change-text {
  font-size: large;
}

.btn-outline-secondary:hover {
  color: white;

}

.btn-outline-secondary {
  color: grey;
  height: 50px;
  border-radius: 15px;
}

i,
.fass {
  position: absolute;
  font-size: px;
  color: #999;
  right: 20px;
  margin-top: 17px;
}

input,
.form-control {
  height: 50px;
  border-radius: 10px;
}
</style>
