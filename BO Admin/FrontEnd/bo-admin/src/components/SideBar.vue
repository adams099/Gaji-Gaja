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
        <!-- <li class="search-box">
                        <i class='bx bx-search icon'></i>
                        <input type="text" placeholder="Search...">
                    </li> -->

        <ul class="menu-links">
          <!--------------- DASHBOARD --------------->
          <router-link :to="`/home`" class="side-btn" active-class="active" exact>
            <li class="">
              <a>
                <b-icon icon="house" class="rounded-circle p-1" variant="light" style="
                    width: 30px;
                    height: 30px;
                    margin-left: 10px;
                    background-color: #695cfe;
                  "></b-icon>
                <span class="link-container" style="margin-left: 5px">Dashboard</span>
              </a>
            </li>
          </router-link>

          <!--------------- ALL USER --------------->
          <router-link to="/allUser" class="side-btn" active-class="active" exact>
            <li class="">
              <a>
                <b-icon icon="person" class="rounded-circle p-1" variant="light" style="
                    width: 30px;
                    height: 30px;
                    margin-left: 10px;
                    background-color: #695cfe;
                  "></b-icon>
                <span class="link-container" style="margin-left: 5px">All User</span>
              </a>
            </li>
          </router-link>

          <!--------------- COMPANY --------------->
          <router-link to="/company" class="side-btn" active-class="active" exact>
            <li class="">
              <a>
                <b-icon icon="building" class="rounded-circle p-1" variant="light" style="
                  width: 30px;
                  height: 30px;
                  margin-left: 10px;
                  background-color: #695cfe;
                "></b-icon>
                <span class="link-container" style="margin-left: 5px">Company</span>
              </a>
            </li>
          </router-link>

        </ul>
      </div>

      <div class="bottom-content">
        <li class="">
          <router-link :to="`/`">
            <a>
              <b-icon icon="door-closed" class="rounded-circle p-1" variant="light" style="
                  width: 30px;
                  height: 30px;
                  margin-left: 10px;
                  background-color: #695cfe;
                "></b-icon>
              <span class="text nav-text" style="margin-left: 5px">Logout</span>
            </a>
          </router-link>
        </li>

        <li class="mode">
          <div class="sun-moon">
            <b-icon icon="moon" class="rounded-circle p-1 mt-2" variant="light" style="
                width: 30px;
                height: 30px;
                margin-left: 10px;
                /* background-color: #695cfe; */
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
    };
  },
  methods: {
    SidebarClose() {
      this.isClose = !this.isClose;
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

a:active {
  text-decoration: none;
}

a:link {
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
</style>
