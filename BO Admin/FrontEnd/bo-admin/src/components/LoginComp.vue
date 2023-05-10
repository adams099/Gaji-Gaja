<template>
  <div class="bg-primary h-100 d-flex align-items-center">
    <div>
      <!-- <img src="../assets/gaja.jpg" style="width: 50vh" /> -->
    </div>
    <div class="col-lg-4 col-md-6 col-sm-8 mx-auto">
      <div class="card register p-5 rounded bg-light d-flex justify-content-center">
        <h2 class="head">Sign In</h2>
        <form @submit.prevent="login">
          <div class="form-input">
            <input type="email" id="email_login" name="email" class="form-control mb-4" placeholder="Email" required
              v-model="userLogin.email" />
            <input type="password" id="password_login" name="password" class="form-control mb-2" placeholder="Password"
              required v-model="userLogin.password" />
          </div>
          <div class="d-flex flex-row justify-content-end">
            <button to="/home" type="submit" tag="button" class="btn btn-primary mb-2">
              Log In
            </button>
          </div>
          <p>Add User<a href="/Register"> Here!</a></p>
        </form>
      </div>
    </div>
  </div>
</template>
    
<script>
import userService from '@/services/userService';
export default {
  name: "LoginComp",
  components: {},

  data() {
    return {
      userLogin: {
        email: "",
        password: ""
      },
      loginValid: false,
      emailValid: false
    }
  },

  methods: {
    login() {
      console.log(this.userLogin);
      userService.login(this.userLogin).then((response) => {
        if (response.status == 200) {
          this.$router.push("/home")
        }
      }).catch((e) => {
        if (e.response.status === 500) {
          this.loginValid
        }
      })
    }
  }
}
</script>
    
<style scoped>
.input-group {
  position: relative;
}

.head {
  display: flex;
  justify-content: center;
}

input {
  border: solid 1.5px rgb(119, 7, 224);
  border-radius: 1rem;
  background: none;
  padding: 1rem;
  font-size: 1rem;
  color: #f5f5f5;
  transition: 150ms cubic-bezier(0.5, 0, 0.2, 0);
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
}

.form-input {
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 2rem;
}

input:focus~label {
  transform: translateY(-50%) scale(0.8);
  background-color: red;
  padding: 0 0.2em;
  color: #695cfe;
}
</style>