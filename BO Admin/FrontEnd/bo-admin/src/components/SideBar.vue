<template>
  <nav :class="{ close: isClose }" class="sidebar">
    <header>
      <div class="image-text">
        <span class="image">
          <img class="img" src="../assets/bo-admin.png" alt="" />
        </span>

        <div class="text logo-text">
          <span class="name">BO ADMIN</span>
          <span class="profession">Gaji Gaja</span>
        </div>
      </div>

      <i class="bx bx-chevron-right toggle" v-on:click="SidebarClose"><b-icon icon="arrow-right-circle"
          class="rounded-circle p-1" variant="light" style="
            width: 30px;
            height: 30px;
            margin-left: 0px;
            background-color: #695cfe;
          "></b-icon></i>
    </header>

    <div class="menu-bar">
      <div class="menu">

        <ul class="menu-links">
          <!--------------- DASHBOARD --------------->
          <a class="side-btn" @click="NyobaEmis(1)" :class="{ active: pa1 }" exact>
            <li class="">
              <a>
                <b-icon icon="house" class="rounded-circle p-1" variant="light" style="
                    width: 30px;
                    height: 30px;
                    margin-left: 10px;
                    background-color: #695cfe;
                  "></b-icon>
                <span class="text link-container" style="margin-left: 5px">Dashboard</span>
              </a>
            </li>
          </a>

          <!--------------- ALL USER --------------->
          <a class="side-btn" @click="NyobaEmis(2)" :class="{ active: pa2 }" exact>
            <li class="">
              <a>
                <b-icon icon="person" class="rounded-circle p-1" variant="light" style="
                    width: 30px;
                    height: 30px;
                    margin-left: 10px;
                    background-color: #695cfe;
                  "></b-icon>
                <span class="text link-container" style="margin-left: 5px">All User</span>
              </a>
            </li>
          </a>

          <!--------------- COMPANY --------------->
          <a v-if="sidebarItem" class="side-btn" @click="NyobaEmis(3)" :class="{ active: pa3 }" exact>
            <li class="">
              <a>
                <b-icon icon="building" class="rounded-circle p-1" variant="light" style="
                    width: 30px;
                    height: 30px;
                    margin-left: 10px;
                    background-color: #695cfe;
                  "></b-icon>
                <span class="text link-container" style="margin-left: 5px">Company</span>
              </a>
            </li>
          </a>
        </ul>
      </div>

      <div class="bottom-content">
        <li class="">
          <a @click="Logout">
            <a>
              <b-icon icon="door-closed" class="rounded-circle p-1" variant="light" style="
                  width: 30px;
                  height: 30px;
                  margin-left: 10px;
                  background-color: #695cfe;
                "></b-icon>
              <span class="text nav-text" style="margin-left: 5px">Logout</span>
            </a>
          </a>
        </li>

        <li class="mode">
          <div class="sun-moon">
            <b-icon icon="moon" class="rounded-circle p-1 mt-2" variant="light" style="
                width: 30px;
                height: 30px;
                margin-left: 10px;
                background-color: #695cfe;
              "></b-icon>
            <i class="bx bx-sun icon sun"></i>
          </div>
          <span class="mode-text text">{{ textMode }}</span>

          <div class="toggle-switch" v-on:click="ModeLandD" v-if="!isClose">
            <span class="switch"></span>
          </div>
        </li>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "DetalFormAnggota",
  data() {
    return {
      isClose: false,
      textMode: "Dark Mode",
      pa1: true,
      pa2: false,
      pa3: false,
      tipeUser: null,
      sidebarItem: true,
      ntol: null
    };
  },
  methods: {
    SidebarClose() {
      this.isClose = !this.isClose;
    },
    NyobaEmis(n) {
      if (n == 1) {
        this.$emit("TestEmit2", 1);
        this.pa1 = true;
        this.pa2 = false;
        this.pa3 = false;
      } else if (n == 2) {
        this.$emit("TestEmit2", 2);
        this.pa1 = false;
        this.pa2 = true;
        this.pa3 = false;
      } else {
        this.$emit("TestEmit2", 3);
        this.pa1 = false;
        this.pa2 = false;
        this.pa3 = true;
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
    this.$session.start()
    if (!this.$session.has("jwt")) {
      this.$router.replace("/auth");
      this.$session.destroy();
    }
    if (this.$session.get('jwt').roleId == 1) {
      console.log(this.$session.get('jwt').roleId);
      this.sidebarItem = false
    }
  },
};
</script>

<style scoped>
.side-btn:focus {
  outline: none;
}

.side-btn.active {
  position: relative;
  background-color: #ffff;
  color: #695cfe;
  font-weight: 300px;
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

/* .router-link-exact-active {
    background-color: white;
}

.router-link-active {
    background-color: #695CFE;
}

.router-link-exact-active .router-link-active {
    background-color: #695CFE;
} */

.side-btn active {
  background-color: #695cfe;
}
</style>
