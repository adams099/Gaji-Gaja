<template>
  <div class="body h-100 d-flex align-items-center">
    <div>
      <!-- <img src="../assets/gaja.jpg" style="width: 50vh" /> -->
    </div>
    <div class="col-lg-4 col-md-6 col-sm-8 mx-auto login">
      <div class="card register shadow-lg p-5  d-flex justify-content-center">
        <h2 class="head" style="color: rgb(88, 84, 84);" v-if="!showForgotPassword && !showOtp && showPass">SIGN IN</h2>
        <h2 class="head forgot-text" style="color: rgb(88, 84, 84);"
          v-else-if="showForgotPassword && showPass && !showOtp">FORGOT
          PASSWORD</h2>

        <h2 class="head forgot-label" v-if="showForgotPassword && showPass && !showOtp">Enter the email address
          associated</h2>
        <!-- <h2 class="head forgot-label" v-if="showForgotPassword">with your account</h2> -->
        <form action="" @submit.prevent="loginFunc">

          <!-- EMAIL -->
          <div>
            <div class="form-input" v-if="!showForgotPassword">
              <input type="email" id="email" name="email" class="form-control mb-1" placeholder="Email" required
                v-model="userLogin.email" />
              <span v-if="error.email" class="validation-message">Email harus diisi!</span>
              <i class="fas fa-envelope input-icon"></i>
            </div>

            <div class="form-input" v-if="!showForgotPassword">
              <input type="password" id="password" name="password" class="form-control mb-4" placeholder="Password"
                required v-model="userLogin.pass" />
              <span v-if="error.password" class="validation-message">Password harus diisi!</span>
              <i class="fas fa-lock input-icon"></i>
            </div>

            <div v-if="showForgotPassword" @click="hidenForgotPassword">
              <div class="form-input flex-row">
                <input type="email" id="otp-email" name="password" class="form-control mb-1 otp-email"
                  placeholder="Enter Email" style="flex-grow: 1" v-if="!showOtp" />
                <div class="sent btn btn-primary ml-3 mb-2 p-2 pr-4" @click="togleOtp" v-if="!showOtp">Sent</div>
                <i class="fas fa-paper-plane input-icon" style="color: white; padding-left: 7px;"></i>
              </div>
            </div>


            <div class="forgot-password mb-3" @click="toggleForgotPassword" v-if="!showForgotPassword">Forgot Password
            </div>
            <!-- <div class="forgot-password mb-3" @click="togleOtp">OTP</div> -->
          </div>


          <div class="d-flex flex-row justify-content-end">
            <button type="submit" tag="button" class="btn btn-primary mb-1" v-if="!showForgotPassword">
              Log In
            </button>
          </div>
          <!-- <p>Add User<a href="/Register"> Here!</a></p> -->
        </form>

        <!-- otp -->
        <div class="container" v-show="showOtp" v-if="showOtp">
          <header>
            <i class="bx bxs-check-shield"></i>
          </header>
          <!-- <h4 class="text-center mb-3">Enter Your Email</h4> -->
          <h4 v-if="showOtp && !showPass" class="text-center mb-3">Enter OTP Code</h4>
          <h4 v-else-if="showPass" class="text-center mb-3">Change Password</h4>
          <h4 v-else>Enter Password</h4>
          <form action="#">
            <div class="row mb-5">
              <div class="col">
                <input type="text" class="form-control" placeholder="Input OTP" v-if="!showPass">
              </div>
              <div v-if="showPass">
                <div class="passx col mb-4 ">
                  <input type="password" class="form-control" placeholder="New Password" style="width: 100%;">
                </div>
                <div class="col">
                  <input type="password" class="form-control" placeholder="Confirm Password">
                </div>
              </div>
            </div>
            <button class="button-otp" @click="togleShowPass" v-if="!showPass">Verify OTP</button>
            <button class="button-otp" @click="togleShowPass" v-if="showPass">Change password</button>
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
      error: {
        email: false,
        password: false,
      },
      loginError: false,
      showForgotPassword: false,
      showOtp: false,
      showPass: false,
    };
  },

  methods: {
    toggleForgotPassword() {
      this.showForgotPassword = true;
    },
    togleOtp() {
      this.showOtp = true;
    },

    hidenForgotPassword() {
      this.showForgotPassword = true;
    },
    togleShowPass() {
      this.showPass = true;
    },


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
            // if (
            //   e.response.data.message.includes(
            //     "Incorrect result size: Expected 1, actual 0"
            //   )
            // ) {
            //   this.loginError = true;
            // }
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