<template>
  <div class="body h-100 d-flex align-items-center">
    <img src="../assets/background.png" alt="" />
    <div>
      <!-- <img src="../assets/gaja.jpg" style="width: 50vh" /> -->
    </div>
    <div class="col-lg-4 ml-auto login mr-5">
      <div class="card register shadow-lg p-5 d-flex justify-content-end">
        <h2 class="head" v-bind:class="{ 'mobile-text': showForgotPassword }" v-if="!showForgotPassword">
          SIGN IN
        </h2>
        <h2 class="head forgot-text" v-bind:class="{ 'mobile-text': !showForgotPassword }" v-else>
          FORGOT PASSWORD
        </h2>

        <form action="" @submit.prevent="loginFunc" v-if="!showForgotPassword" class="login">
          <!-- Email -->
          <div>
            <div class="form-input">
              <input type="email" id="email" name="email" class="form-control mb-3 mt-4" placeholder="Email" required
                v-model="userLogin.email" />
              <span v-if="error.email" class="validation-message">Email harus diisi!</span>
              <i class="fas fa-envelope input-icon mt-4"></i>
            </div>

            <!-- Password -->
            <div class="form-input">
              <input :type="showPassword ? 'text' : 'password'" id="password" name="password"
                class="form-control mb-3 password-input" placeholder="Password" required v-model="userLogin.pass" />
              <span v-if="error.password" class="validation-message">Password harus diisi!</span>
              <i class="fas fass" :class="showPassword ? 'fa-eye' : 'fa-eye-slash'" @click="togglePasswordVisibility"></i>
            </div>

            <vue-recaptcha v-if="attempts === 0" sitekey="6LcCOh8mAAAAAFa-Vv0emVYbkzEYYQOiBT9-YTfV"
              @verify="onVerify"></vue-recaptcha>
            <div class="forgot-password" @click="toggleForgotPassword">
              Forgot Password
            </div>
          </div>

          <div class="d-flex flex-row justify-content-end">
            <button type="submit" tag="button" class="btn btn-primary mt-3">
              Log In
            </button>
          </div>
        </form>

        <!-- FORM OTP -->
        <div class="form-2">
          <form @submit.prevent="checkOtp" v-if="sfp" @click="hidenForgotPassword">
            <form @submit.prevent="sendOtp" class="form-input flex-row">
              <input type="email" id="otp-email" name="password" v-model="otp.email" class="form-control mb-1 otp-email"
                placeholder="Enter Email" required v-if="!showOtp" autocomplete="off" />
              <button v-if="loading" class="sent btn btn-primary ml-3 p-2 pr-4" disabled>
                {{ remainingTime }}
              </button>
              <button v-else class="sent btn btn-primary ml-3 p-2">Sent</button>
            </form>
            <div>
              <input type="text" id="otp-email" v-model="otp.otp" name="password" required
                class="form-control mb-1 otp-email mb-4" placeholder="Enter OTP" v-if="!showOtp" autocomplete="off" />
            </div>
            <div>
              <button type="submit" tag="button" class="btn btn-primary mb-4">
                Verify OTP
              </button>
            </div>
            <div class="back-button">
              <div class="btn-back" v-on:click="BackButton(1)">
                <i class="fas fa-arrow-left"></i> Back
              </div>
            </div>
          </form>

          <!-- CHANGE PASSWORD -->
          <form @submit.prevent="changePass" v-if="scpass" @click="hidenForgotPassword">
            <!-- <div class="form-input flex-row">
              <input type="password" id="new-password" v-model="gantipw.pw1" name="new-password"
                class="form-control mb-1 new-password" placeholder="Enter New Password" v-if="!showOtp" />
            </div> -->
            <div class="form-input flex-row">
              <input id="new-password" v-model="gantipw.pw1" name="new-password" class="form-control mb-1 new-password"
                placeholder="Enter New Password" v-if="!showOtp" :type="inputType1" />
              <span class="password-toggle" @click="togglePasswordVisibilityS('pw1')">
                <i class="fas otp-1" :class="showPasswordS.pw1 ? 'fa-eye' : 'fa-eye-slash'"></i>
              </span>
            </div>

            <!-- <div>
              <input type="password" id="confirm-password" name="confirm-password" v-model="gantipw.pw2"
                class="form-control mb-1 confirm-password mb-4" placeholder="Confirm New Password" v-if="!showOtp" />
            </div> -->
            <div>
              <input id="confirm-password" v-model="gantipw.pw2" name="confirm-password"
                class="form-control mb-1 confirm-password mb-4" placeholder="Confirm New Password" v-if="!showOtp"
                :type="inputType2" />
              <span class="password-toggle" @click="togglePasswordVisibilityS('pw2')">
                <i class="fas otp-2" :class="showPasswordS.pw2 ? 'fa-eye' : 'fa-eye-slash'"></i>
              </span>
            </div>
            <div>
              <button type="submit" tag="button" class="btn btn-primary mb-4" v-if="scpass">
                Change Password
              </button>
            </div>
            <div class="back-button">
              <div class="btn-back" style="cursor: pointer" v-on:click="BackButton(2)">
                <i class="fas fa-arrow-left"></i> Back
              </div>
            </div>
          </form>
        </div>
      </div>
      <h6 class="cr text-center">Copyright @2023 | BO Admin</h6>
    </div>
  </div>
</template>

    
<script>
import userService from "@/services/userService";
import { VueRecaptcha } from "vue-recaptcha";
export default {
  name: "LoginComp",
  components: {
    VueRecaptcha,
  },

  data() {
    return {
      userLogin: {
        email: "",
        pass: "",
      },
      otp: {
        email: "",
        otp: "",
      },
      gantipw: {
        pw1: "",
        pw2: "",
      },
      showPasswordS: {
        pw1: false,
        pw2: false
      },
      error: {
        email: false,
        password: false,
      },
      loginError: false,
      showForgotPassword: false,
      showOtp: false,
      showPass: false,
      scpass: false,
      sfp: false,
      loading: false,
      remainingTime: 60,
      dataemail: {},
      attempts: 3,
      showPassword: false
    };
  },
  computed: {
    inputType1() {
      return this.showPasswordS.pw1 ? "text" : "password";
      // return this.showPassword.pw2 ? "text" : "password";
    },
    inputType2() {
      return this.showPasswordS.pw2 ? "text" : "password";
    },
  },


  methods: {
    startLoading() {
      this.loading = true;
      this.remainingTime = 60;

      const timer = setInterval(() => {
        this.remainingTime--;
        if (this.remainingTime <= 0) {
          this.stopLoading(timer);
        }
      }, 1000);
    },

    togglePasswordVisibilityS(field) {
      this.showPasswordS[field] = !this.showPasswordS[field];
    },
    toggleForgotPassword() {
      this.showForgotPassword = true;
      this.sfp = true;
    },
    onVerify(response) {
      console.log("Verify: " + response);
      this.attempts++;
    },
    togleOtp() {
      this.showOtp = true;
    },
    togglePasswordVisibility() {
      this.showPassword = !this.showPassword;
    },
    sendOtp() {
      this.startLoading();
      let data = this.otp;
      data.otp = null;
      userService
        .potp(data)
        .then((response) => {
          if (response.status === 200) {
            console.log("succes");
            this.$toast.success("Check Your Email", {
              position: "top-right",
              timeout: 2500,
            });
          }
        })
        .catch((e) => {
          console.log(e);
          try {
            e["code"] === "ERR_NETWORK";
            console.log(e["code"]);
            this.$toast.error("ERROR NETWORK CONNECTION", {
              position: "top-right",
              timeout: 2500,
            });
          } catch (error) {
            this.$toast.error("Error", {
              position: "top-right",
              timeout: 2500,
            });
          }
        });
    },
    checkOtp() {
      let data = this.otp;
      userService
        .cotp(data)
        .then((response) => {
          if (response.status === 200) {
            console.log(response.data);
            this.togleShowPass();
            this.$toast.success("OTP verification successful!", {
              position: "top-right",
              timeout: 2500,
            });
          }
        })
        .catch((e) => {
          console.log(e);
          try {
            e.response.status == 404;
            this.$toast.warning("Invalid OTP. Please try again.", {
              position: "top-right",
              timeout: 2500,
            });
          } catch (error) {
            this.$toast.error("Error", {
              position: "top-right",
              timeout: 2500,
            });
          }
        });
    },
    async changePass() {
      const regex = /^(?=.*[a-zA-Z])(?=.*\d).+$/;
      // var data = this.user;
      // data.newPass = this.gantipw.pw1;
      console.log(regex.test(this.gantipw.pw1));
      if (this.gantipw.pw1 != this.gantipw.pw2) {
        // kurang
        this.$toast.error("Confirmation Password Didn't Match!", {
          position: "top-right",
          timeout: 2500,
        });
      } else {
        if (this.gantipw.pw1.length >= 8) {
          if (regex.test(this.gantipw.pw1)) {
            //post data
            let data = this.otp;
            data.otp = null;
            let posisi = null;
            await userService
              .findEmail(data)
              .then((response) => {
                if (response.status === 200) {
                  this.dataemail = response.data;
                  if (response.data.update == null) {
                    this.$toast.warning("Please Change Password at Dashboard", {
                      position: "top-right",
                      timeout: 2500,
                    });
                  } else {
                    posisi = 1;
                  }
                }
              })
              .catch((e) => {
                console.log(e);
                this.$toast.error("Error!", {
                  position: "top-right",
                  timeout: 2500,
                });
              });
            if (posisi == 1) {
              this.dataemail.pass = this.gantipw.pw1;
              let data2 = this.dataemail;
              userService
                .register(data2)
                .then((response) => {
                  if (response.status === 201) {
                    this.$toast.success("Success change password!", {
                      position: "top-right",
                      timeout: 2500,
                    });
                    setTimeout(() => {
                      window.location.reload();
                    }, 2000);
                  }
                })
                .catch((e) => {
                  console.log(e);
                  try {
                    e["code"] === "ERR_NETWORK";
                    console.log(e["code"]);
                    this.$toast.error("ERROR NETWORK CONNECTION", {
                      position: "top-right",
                      timeout: 2500,
                    });
                  } catch (error) {
                    this.$toast.error("Error", {
                      position: "top-right",
                      timeout: 2500,
                    });
                  }
                });
            }
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
          //warning character
          this.$toast.warning("The password is need more than 8 character!", {
            position: "top-right",
            timeout: 2500,
          });

        }
      }
    },

    stopLoading(timer) {
      this.loading = false;
      clearInterval(timer);
    },
    hidenForgotPassword() {
      this.showForgotPassword = true;
    },
    BackButton(he) {
      if (he == 1) {
        this.showForgotPassword = false;
        this.sfp = false;
      } else {
        this.scpass = !this.scpass;
        this.sfp = !this.sfp;
      }
    },
    togleShowPass() {
      this.scpass = true;
      this.sfp = !this.sfp;
    },


    // LOGIN FUNCTION
    loginFunc() {
      if (this.attempts > 0) {
        this.attempts--;
        this.loginError = false;
        this.error = {};
        let data = this.userLogin;
        for (const property in data) {
          if (data[property] === null || data[property] === "") {
            this.error[property] = true;
          }
        }

        if (Object.keys(this.error).length === 0) {
          userService
            .login(data)
            .then((response) => {
              if (response.data.statId == 2) {
                this.$session.start();
                this.$session.set("jwt", response.data);
                this.$session.set("email", data.email);
                this.$router.push("/");
              } else {
                this.$swal({
                  cancelButtonText: "Close",
                  showCancelButton: true,
                  showConfirmButton: false,
                  icon: "error",
                  title: "Error",
                  text: "Your account is inactive. Please contact support for assistance.",
                });
              }
            })
            .catch((e) => {
              try {
                e.response.status == 404;
                this.$toast.warning(
                  "Incorrect email or password. Please try again!",
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
        }
      } else {
        this.$toast.error("Please verify the CAPTCHA", {
          position: "top-right",
          timeout: 2500,
        });
      }
    },
  },

};

</script>
    
<style scoped lang="css">
@media (max-width: 576px) {
  .card.register {
    width: 100%;
    max-width: 350px;
  }

  .head {
    font-size: 1.5rem;
  }

  .forgot-text {
    font-size: 1.3rem;
  }

  .mobile-text {
    font-size: 1.1rem;
  }
}

@media (min-width: 576px) {

  /* Styles for medium and large screens */
  .card.register {
    width: 100%;
    max-width: 450px;
  }
}

.img-background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: -1;
}

.container {
  justify-content: flex-end;
}

.input-group {
  position: relative;
  background-color: #060000;
}

.head {
  display: flex;
  justify-content: center;
}

input {
  /* border: solid 1.5px #695cfe; */
  border-radius: 10px;
  background: none;
  padding: 1rem;
  font-size: 1rem;
  transition: 150ms cubic-bezier(0.5, 0, 0.2, 0);
  height: 50px;
}

button.sent.btn.btn-primary.ml-3.p-2 {
  height: 47px;
}

label {
  font-size: 1.5rem;
  position: absolute;
  left: 16px;
  color: #e8e8e8;
  pointer-events: none;
  transform: translateY(2rem);
  transition: 150ms cubic-bezier(1, 0, 0.2, 1);
}

button {
  background-color: #695cfe;
  padding: 0.7rem;
  border-radius: 0.7rem;
  width: 100%;
  color: white;
  font-size: 1rem;
  border: none;
}

button:hover {
  background-color: hsl(241, 80%, 60%);
}

input:focus {
  outline: none;
  border: 1.5px solid #695cfe;
  border-radius: 5px;
}

.form-input {
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 1rem;
  margin-top: 1rem;
}

input:focus~label {
  transform: translateY(-50%) scale(0.8);
  background-color: red;
  padding: 0 0.2em;
  color: #695cfe;
}

.validation-message {
  color: red;
  opacity: 0.7;
  font-size: 0.8rem;
}

.validation-message-success {
  color: green;
  opacity: 0.7;
  font-size: 0.8rem;
}

.register {
  border-radius: 30px;
}

.body {
  background-color: #343a40;
}

.form-input {
  position: relative;
}

.input-icon {
  position: absolute;
  top: 17px;
  right: 14px;
  font-size: px;
  color: #999;
}

.fass {
  position: absolute;
  top: 17px;
  right: 14px;
  font-size: px;
  color: #999;
}

.form-input {
  display: flex;
  align-items: end;
  position: relative;
}

.input-icon {
  margin-left: 10px;
  font-size: 16px;
  color: #999;
}



.forgot-password {
  font-size: 13px;
  color: #695cfe;
}

.forgot-password:hover {
  cursor: pointer;
}

.sent {
  width: 130px;
  background-color: #695cfe;
  color: white;
  border-radius: 10px;
  margin-left: 10px;
  margin-bottom: 5px;
}

.sent:hover {
  color: white;
  background-color: #584be0;
}

.forgot-text {
  font-size: 25px;
}

.forgot-label {
  font-size: 12px;
  color: rgb(165, 165, 165);
}

.passx {
  width: 300px;
}

.back-button {
  color: rgba(0, 0, 0, 0.477);
  width: 80px;
  height: 20px;
  font-size: 14px;
  font-weight: 500;
}

.fa-arrow-left {
  color: white;
  background-color: #584be0;
  border-radius: 100%;
  padding: 5px 6px;
}

.btn-back {
  cursor: pointer;
  background-color: #99999935;
  padding-left: 5px;
  padding-right: 10px;
  padding-top: 5px;
  padding-bottom: 5px;
  border-radius: 10px;
}

img {
  width: 100vw;
  height: 100vh;
  position: fixed;
}

.login {
  height: 350px;
  /* position: relative; */
  bottom: 50px;
}

.cr {
  color: white;
  font-size: 10px;
  margin-top: 30px;
  position: fixed;
  bottom: 20px;
  margin: auto;
  left: 30px;
}

.password-toggle {
  position: absolute;
  top: 50%;
  margin-left: 320px;
  /* right: 10px; */
  transform: translateY(-50%);
}

.password-toggle i {
  display: inline-block;
  vertical-align: center;
  color: #999;

}

/* otp */

.otp-2 {
  margin-top: 18px;
}

i:hover {
  cursor: pointer;
}
</style>