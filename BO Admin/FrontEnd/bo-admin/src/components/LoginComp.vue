<template>
  <div class="body h-100 d-flex align-items-center">
    <div>
      <!-- <img src="../assets/gaja.jpg" style="width: 50vh" /> -->
    </div>
    <div class="col-lg-4 col-md-6 col-sm-8 mx-auto login">
      <div class="card register shadow-lg p-5  d-flex justify-content-center">
        <h2 class="head" style="color: rgb(88, 84, 84);" v-if="!showForgotPassword && !showOtp && showPass">SIGN IN</h2>
        <h2 class="head forgot-text" style="color: rgb(88, 84, 84);" v-else-if="showForgotPassword && !showOtp">FORGOT
          PASSWORD</h2>
        <!-- <h2 class="head forgot-label">Enter the email address
          associated</h2>
        <h2 class="head forgot-label">with your account</h2> -->

        <form action="" @submit.prevent="loginFunc" v-if="!showForgotPassword">
          <!-- Email -->
          <div>
            <div class="form-input">
              <input type="email" id="email" name="email" class="form-control mb-1" placeholder="Email" required
                v-model="userLogin.email" />
              <span v-if="error.email" class="validation-message">Email harus diisi!</span>
              <i class="fas fa-envelope input-icon"></i>
            </div>

            <!-- Password -->
            <div class="form-input">
              <input type="password" id="password" name="password" class="form-control mb-4" placeholder="Password"
                required v-model="userLogin.pass" />
              <span v-if="error.password" class="validation-message">Password harus diisi!</span>
              <i class="fas fa-lock input-icon"></i>
            </div>

            <div class="forgot-password mb-3" @click="toggleForgotPassword">Forgot Password
            </div>
          </div>


          <div class="d-flex flex-row justify-content-end">
            <button type="submit" tag="button" class="btn btn-primary mb-1">
              Log In
            </button>
          </div>
        </form>

        <!-- FORM OTP -->
        <div class="form-2">
          <form @submit.prevent="checkOtp" v-if="sfp" @click="hidenForgotPassword">
            <form @submit.prevent="sendOtp" class="form-input flex-row">
              <input type="email" id="otp-email" name="password" v-model="otp.email" class="form-control mb-1 otp-email"
                placeholder="Enter Email" required style="flex-grow: 1" v-if="!showOtp" />
              <button v-if="loading" class="sent btn btn-primary ml-3 p-2 pr-4" disabled
                style="margin-bottom: 0.35rem;">{{ remainingTime }}</button>
              <button v-else class="sent btn btn-primary ml-3 p-2" style="margin-bottom: 0.35rem;">Sent</button>
            </form>
            <div>
              <input type="text" id="otp-email" v-model="otp.otp" name="password" required
                class="form-control mb-1 otp-email mb-4" placeholder="Enter OTP" style="flex-grow: 1" v-if="!showOtp" />
            </div>
            <div>
              <button type="submit" tag="button" class="btn btn-primary mb-1">
                Verify OTP
              </button>
            </div>
            <p style="cursor: pointer;" v-on:click="BackButton(1)">Back</p>
          </form>

          <!-- CHANGE PASSWORD -->
          <form @submit.prevent="changePass" v-if="scpass" @click="hidenForgotPassword">
            <div class="form-input flex-row">
              <input type="password" id="new-password" v-model="gantipw.pw1" name="new-password"
                class="form-control mb-1 new-password" placeholder="Enter New Password" style="flex-grow: 1"
                v-if="!showOtp" />
            </div>
            <div>
              <input type="password" id="confirm-password" name="confirm-password" v-model="gantipw.pw2"
                class="form-control mb-1 confirm-password mb-4" placeholder="Confirm New Password" style="flex-grow: 1"
                v-if="!showOtp" />
            </div>
            <div>
              <button type="submit" tag="button" class="btn btn-primary mb-1" v-if="scpass">
                Change Password
              </button>
            </div>
            <p style="cursor: pointer;" v-on:click="BackButton(2)">Back</p>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
    
<script>
import userService from "@/services/userService";
export default {
  name: "LoginComp",
  components: {

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
      dataemail: {}
    };
  },

  methods: {
    toggleForgotPassword() {
      this.showForgotPassword = true;
      this.sfp = true
    },
    togleOtp() {
      this.showOtp = true;
    },
    sendOtp() {
      this.startLoading()
      let data = this.otp
      data.otp = null
      userService
        .potp(data)
        .then((response) => {
          if (response.status === 200) {
            console.log("succes");
            this.$toast.success('Check Your Email', {
              position: 'top-right',
              timeout: 2500,
            });
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    checkOtp() {
      let data = this.otp
      userService
        .cotp(data)
        .then((response) => {
          if (response.status === 200) {
            console.log(response.data);
            this.togleShowPass()
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    async changePass() {
      if (this.gantipw.pw1 != this.gantipw.pw2) {
        this.$toast.error('Password Not Same', {
          position: 'top-right',
          timeout: 2500,
        });
      } else {
        let data = this.otp
        data.otp = null
        await userService
          .findEmail(data)
          .then((response) => {
            if (response.status === 200) {
              this.dataemail = response.data
            }
          })
          .catch((e) => {
            console.log(e);
          });
        this.dataemail.pass = this.gantipw.pw1
        let data2 = this.dataemail
        userService
          .register(data2)
          .then((response) => {
            if (response.status === 201) {
              this.$toast.success('Ganti Password Berhasil', {
                position: 'top-right',
                timeout: 2500,
              });
              setTimeout(() => {
                window.location.reload();
              }, 2000);
            }
          })
          .catch((e) => {
            console.log(e);
          });
      }
    },
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
    stopLoading(timer) {
      this.loading = false;
      clearInterval(timer);
    },
    hidenForgotPassword() {
      this.showForgotPassword = true;
    },
    BackButton(he) {
      if (he == 1) {
        this.showForgotPassword = false
        this.sfp = false
      } else {
        this.scpass = !this.scpass;
        this.sfp = !this.sfp
      }
    },
    togleShowPass() {
      this.scpass = true;
      this.sfp = !this.sfp
    },


    // LOGIN FUNCTION
    loginFunc() {
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
            if (response.status === 200) {
              this.$session.start();

              this.$session.set("jwt", response.data);
              this.$session.set("email", data.email);
              this.$router.push("/");
            }
          })
          .catch((e) => {
            try {
              e.response.status == 404
              this.$toast.warning('Incorrect email or password. Please try again!', {
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
      }
    },
  },
};
</script>
    
<style scoped>
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

.form-input {
  display: flex;
  align-items: end;
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


/* otp */
</style>