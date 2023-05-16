<template>
  <div class="body h-100 d-flex align-items-center">
    <div>
      <!-- <img src="../assets/gaja.jpg" style="width: 50vh" /> -->
    </div>
    <div class="col-lg-4 col-md-6 col-sm-8 mx-auto login">
      <div class="card register shadow-lg p-5  d-flex justify-content-center">
        <h2 class="head" style="color: rgb(88, 84, 84);">SIGN IN</h2>
        <form action="" @submit.prevent="loginFunc">
          <div class="form-input">
            <input type="email" id="email" name="email" class="form-control mb-1" placeholder="Email" required
              v-model="userLogin.email" />
            <span v-if="error.email" class="validation-message">Email harus diisi!</span>
          </div>

          <div class="form-input">
            <input type="password" id="password" name="password" class="form-control mb-4" placeholder="Password" required
              v-model="userLogin.pass" />
            <span v-if="error.password" class="validation-message">Password harus diisi!</span>
          </div>

          <!-- <div class="form-input">
            <span v-if="loginError" class="validation-message">
              Email / Password is invalid!</span>
          </div> -->

          <div class="d-flex flex-row justify-content-end">
            <button to="/home" type="submit" tag="button" class="btn btn-primary mb-1">
              Log In
            </button>
          </div>
          <!-- <p>Add User<a href="/Register"> Here!</a></p> -->
        </form>
      </div>
    </div>
  </div>
</template>
    
<script>
import userService from "@/services/userService";
export default {
  name: "LoginComp",
  components: {},

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
    };
  },

  methods: {
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
</style>