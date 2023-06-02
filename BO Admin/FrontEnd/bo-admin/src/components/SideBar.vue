<template>
  <nav :class="{ close: isClose }" class="sidebar">
    <header>
      <div class="image-text">
        <span class="image">
          <img class="img" src="../assets/bo-admin.png" alt="" />
        </span>

        <div class="text logo-text d-flex flex-column mb-2">
          <span class="name">BO ADMIN</span>
          <span class="profession" style="font-size: 12px">Gaji Gaja</span>
        </div>
      </div>

      <i class="bx bx-chevron-right toggle" v-on:click="SidebarClose"
        ><i class="fa-solid fa-chevron-right"></i
      ></i>
    </header>

    <div class="menu-bar">
      <div class="menu">
        <div class="div">
          <p class="name-account text-capitalize text-center" v-if="!isClose">
            Hi, {{ name }}
          </p>
        </div>
        <ul class="menu-links mt-5">
          <!--------------- DASHBOARD --------------->
          <a v-if="sidebarItem" class="side-btn" v-show="firstLogin">
            <li class="">
              <a @click="NyobaEmis(1)" :class="{ active: pa1 }">
                <b-icon
                  icon="house"
                  class="rounded-circle p-1"
                  variant="light"
                  style="
                    width: 30px;
                    height: 30px;
                    margin-left: 10px;
                    background-color: #695cfe;
                  "
                ></b-icon>
                <span
                  class="link-container"
                  style="margin-left: 5px"
                  v-if="!isClose"
                  >Dashboard</span
                >
              </a>
            </li>
          </a>

          <!--------------- ALL USER --------------->
          <a v-if="!sidebarItem" class="side-btn" v-show="firstLogin">
            <li class="">
              <a @click="NyobaEmis(2)" :class="{ active: pa2 }">
                <b-icon
                  icon="person-plus"
                  class="rounded-circle p-1"
                  variant="light"
                  style="
                    width: 30px;
                    height: 30px;
                    margin-left: 10px;
                    background-color: #695cfe;
                  "
                ></b-icon>
                <span
                  class="link-container"
                  style="margin-left: 5px"
                  v-if="!isClose"
                  >All User</span
                >
              </a>
            </li>
          </a>

          <!--------------- COMPANY --------------->
          <a
            v-if="sidebarItem || !sidebarItem"
            class="side-btn"
            v-show="firstLogin"
          >
            <li class="">
              <a @click="NyobaEmis(banjir)" :class="{ active: pa3 }">
                <b-icon
                  icon="building"
                  class="rounded-circle p-1"
                  variant="light"
                  style="
                    width: 30px;
                    height: 30px;
                    margin-left: 10px;
                    background-color: #695cfe;
                  "
                ></b-icon>
                <span
                  class="link-container"
                  style="margin-left: 5px"
                  v-if="!isClose"
                  >{{ ahok }}</span
                >
              </a>
            </li>
          </a>

          <!--------------- APPROVAL --------------->
          <a v-if="sidebarItem" class="side-btn" v-show="firstLogin">
            <li class="">
              <a @click="NyobaEmis(ngapainsih)" :class="{ active: pa4 }">
                <b-icon
                  icon="check2-circle"
                  class="rounded-circle p-1"
                  variant="light"
                  style="
                    width: 30px;
                    height: 30px;
                    margin-left: 10px;
                    background-color: #695cfe;
                  "
                ></b-icon>
                <span
                  class="link-container"
                  style="margin-left: 5px"
                  v-if="!isClose"
                  >Approval</span
                >
              </a>
            </li>
          </a>

          <!--------------- PROFIL --------------->
          <a class="side-btn">
            <li class="">
              <a @click="NyobaEmis(5)" :class="{ active: pa5 }">
                <b-icon
                  icon="person"
                  class="rounded-circle p-1"
                  variant="light"
                  style="
                    width: 30px;
                    height: 30px;
                    margin-left: 10px;
                    background-color: #695cfe;
                  "
                ></b-icon>
                <span
                  class="link-container"
                  style="margin-left: 5px"
                  v-if="!isClose"
                  >Profile</span
                >
              </a>
            </li>
          </a>
        </ul>
      </div>

      <div class="bottom-content">
        <li class="">
          <a @click="Logout">
            <a>
              <b-icon
                icon="door-closed"
                class="rounded-circle p-1"
                variant="light"
                style="
                  width: 30px;
                  height: 30px;
                  margin-left: 10px;
                  background-color: #695cfe;
                "
              ></b-icon>
              <span class="text nav-text" style="margin-left: 5px">Logout</span>
            </a>
          </a>
        </li>

        <li class="mode">
          <div class="sun-moon">
            <b-icon
              v-if="textMode == 'Dark Mode'"
              icon="sun"
              class="rounded-circle p-1 mt-2"
              variant="light"
              style="
                width: 30px;
                height: 30px;
                margin-left: 10px;
                background-color: #695cfe;
              "
            ></b-icon>
            <b-icon
              v-else
              icon="moon"
              class="rounded-circle p-1 mt-2"
              variant="light"
              style="
                width: 30px;
                height: 30px;
                margin-left: 10px;
                background-color: #695cfe;
              "
            ></b-icon>
            <i class="bx bx-sun icon sun"></i>
          </div>
          <span class="mode-text text">{{ textMode }}</span>

          <div class="toggle-switch" v-on:click="ModeLandD" v-if="!isClose">
            <span class="switch"></span>
          </div>
        </li>
      </div>
    </div>
    <div class="footer"></div>
  </nav>
</template>

<script>
import userService from "@/services/userService";

export default {
  name: "DetalFormAnggota",
  data() {
    return {
      isClose: false,
      textMode: "Dark Mode",

      pa1: true,
      pa2: false,
      pa3: false,
      pa4: false,
      pa5: false,
      ahok: "Company",
      banjir: 3,
      ngapainsih: 4,

      tipeUser: null,
      sidebarItem: true,
      ntol: null,
      firstLogin: true,
      name: null,
    };
  },
  methods: {
    SidebarClose() {
      this.isClose = !this.isClose;
    },
    NyobaEmis(n) {
      console.log(n);
      if (n == 1) {
        this.$emit("TestEmit2", 1);
        this.pa1 = true;
        this.pa2 = false;
        this.pa3 = false;
        this.pa4 = false;
        this.pa5 = false;
      } else if (n == 2) {
        this.$emit("TestEmit2", 2);
        this.pa1 = false;
        this.pa2 = true;
        this.pa3 = false;
        this.pa4 = false;
        this.pa5 = false;
      } else if (n == 3) {
        this.$emit("TestEmit2", 3);
        this.pa1 = false;
        this.pa2 = false;
        this.pa3 = true;
        this.pa4 = false;
        this.pa5 = false;
      } else if (n == 4) {
        this.$emit("TestEmit2", 4);
        this.pa1 = false;
        this.pa2 = false;
        this.pa3 = false;
        this.pa4 = true;
        this.pa5 = false;
      } else if (n == 5) {
        this.$emit("TestEmit2", 5);
        this.pa1 = false;
        this.pa2 = false;
        this.pa3 = false;
        this.pa4 = false;
        this.pa5 = true;
      } else if (n == 6) {
        this.$emit("TestEmit2", 6);
        this.pa1 = false;
        this.pa2 = false;
        this.pa3 = true;
        this.pa4 = false;
        this.pa5 = false;
      } else {
        this.$emit("TestEmit2", 7);
        this.pa1 = false;
        this.pa2 = false;
        this.pa3 = false;
        this.pa4 = true;
        this.pa5 = false;
      }
    },
    ModeLandD() {
      // this.$emit
      this.$emit("TestEmit");
      if (this.textMode == "Dark Mode") {
        this.textMode = "Light Mode";
      } else {
        this.textMode = "Dark Mode";
      }
    },
    Logout() {
      this.$router.replace("/auth");
      this.$session.destroy();
    },
  },
  created() {
    this.$session.start();
    if (!this.$session.has("jwt")) {
      this.$router.replace("/auth");
      this.$session.destroy();
    }
    if (this.$session.get("jwt").roleId == 1) {
      console.log(this.$session.get("jwt").roleId);
      this.sidebarItem = false;
    }
    if (this.$session.get("jwt").roleId == 3) {
      this.ahok = "Employee";
      this.banjir = 6;
      this.ngapainsih = 7;
    }
  },
  mounted() {
    let data = {
      email: this.$session.get("email"),
    };
    userService
      .findEmail(data)
      .then((response) => {
        let oldName = response.data.name;
        this.name = oldName.split(" ")[0];

        if (response.data.roleId == 1) {
          this.NyobaEmis(2);
        } else if (response.data.update == null) {
          this.NyobaEmis(5);
          this.firstLogin = false;
          this.$toast.warning("We Recommend You to Change Your Password", {
            position: "top-right",
            timeout: 2500,
          });
        }
      })
      .catch((e) => {
        console.log(e);
      });
  },
};
</script>

<style scoped>
.side-btn:focus {
  outline: none;
}

a {
  color: grey;
}

a:hover {
  color: white;
}

.active {
  background-color: #695cfe;
  color: white;
  font-weight: 300px;
  position: relative;
}

.side-btn {
  border: none;
  padding: 20px 0px;
  cursor: pointer;
  font-size: 15px;
  font-weight: bold;
  color: #999999;
  background-color: #695cfe;
}

a:link {
  text-decoration: none;
}

a:visited {
  text-decoration: none;
}

a:hover {
  text-decoration: none;
}

a:active {
  text-decoration: none;
}

.side-btn:hover {
  color: white;
}

.name-account {
  background-color: #695cfe;
  border-radius: 13px;
  color: white;
}

.fa-chevron-right {
  font-size: 15px;
}
</style>
