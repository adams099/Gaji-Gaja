<template>
  <div>

    <body :class="{ dark: isDark }">
      <Sidebar @TestEmit="NyobaEmit" @TestEmit2="NyobaEmits"></Sidebar>
      <PageOne v-if="pa1"></PageOne>
      <CompanyComp2 v-if="pa2"></CompanyComp2>
      <AllUser v-if="pa3"></AllUser>
      <Approval v-if="pa4"></Approval>
      <ProfileComp v-if="pa5"></ProfileComp>
      <EmployeeVue v-if="pa6"></EmployeeVue>
      <ApprovalEmpl v-if="pa7"></ApprovalEmpl>
    </body>
  </div>
</template>

<script>
import Sidebar from "../components/SideBar.vue";
import PageOne from "../components/PageOne.vue";
import CompanyComp2 from "../components/CompanyComp2.vue";
import AllUser from "../components/AllUserComp.vue";
import ProfileComp from "../components/ProfileComp.vue";
import Approval from "../components/ApprovalComp.vue"
import EmployeeVue from '@/components/EmployeeComp.vue';
import ApprovalEmpl from "@/components/ApprovalEmpl.vue";

export default {
  name: "SidebarS",
  components: {
    Sidebar,
    PageOne,
    CompanyComp2,
    AllUser,
    ProfileComp,
    Approval,
    EmployeeVue,
    ApprovalEmpl,
},
  data() {
    return {
      isDark: false,
      pa1: true,
      pa2: false,
      pa3: false,
      pa4: false,
      pa5: false,
      pa6: false,
      pa7: false,
    };
  },
  methods: {
    NyobaEmit() {
      this.isDark = !this.isDark;
    },
    logAfterTenMinutes(n) {
      const now = new Date();
      const future = new Date(n);
      console.log(future);
      setTimeout(() => {
        // console.log("test");
        this.$router.push("/auth");
        this.$session.destroy();
      }, future - now);
    },
    NyobaEmits(n) {
      if (n == 1) {
        this.pa1 = true;
        this.pa2 = false;
        this.pa3 = false;
        this.pa4 = false;
        this.pa4 = false;
        this.pa5 = false;
        this.pa6 = false;
        this.pa7 = false;
      } else if (n == 2) {
        this.pa1 = false;
        this.pa2 = false;
        this.pa3 = true;
        this.pa4 = false;
        this.pa5 = false;
        this.pa6 = false;
        this.pa7 = false;
      } else if (n == 3) {
        this.pa1 = false;
        this.pa2 = true;
        this.pa3 = false;
        this.pa4 = false;
        this.pa5 = false;
        this.pa6 = false;
        this.pa7 = false;
      } else if (n == 4) {
        this.pa1 = false;
        this.pa2 = false;
        this.pa3 = false;
        this.pa4 = true;
        this.pa5 = false;
        this.pa6 = false;
        this.pa7 = false;
      } else if (n== 5) {
        this.pa1 = false;
        this.pa2 = false;
        this.pa3 = false;
        this.pa4 = false;
        this.pa5 = true;
        this.pa6 = false;
        this.pa7 = false;
      } else if ( n == 6){
        this.pa1 = false;
        this.pa2 = false;
        this.pa3 = false;
        this.pa4 = false;
        this.pa5 = false;
        this.pa6 = true;
        this.pa7 = false;
      } else {
        this.pa1 = false;
        this.pa2 = false;
        this.pa3 = false;
        this.pa4 = false;
        this.pa5 = false;
        this.pa6 = false;
        this.pa7 = true;
      }
    },
  },
  created() {
    this.logAfterTenMinutes(this.$session.get("jwt").data.substr(0, 23));
    // console.log(this.$session.get("jwt").data.substr(0, 23));
    // console.log(
    //   this.$session
    //     .get("jwt")
    //     .data.substr(23, this.$session.get("jwt").data.length)
    // );
    // this.$session.start();
    // if (!this.$session.has("jwt")) {
    //   this.$router.push("/auth");
    //   // this.$session.destroy();
    // }
  },
};
</script>

<style>
/* Google Font Import - Poppins */
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}

:root {
  /* ===== Colors ===== */
  --body-color: #e4e9f7;
  --sidebar-color: #fff;
  --primary-color: #695cfe;
  --primary-color-light: #f6f5ff;
  --toggle-color: #ddd;
  --text-color: #707070;

  /* ====== Transition ====== */
  --tran-03: all 0.2s ease;
  --tran-03: all 0.3s ease;
  --tran-04: all 0.3s ease;
  --tran-05: all 0.3s ease;
}

ol,
ul {
  padding-left: 0rem;
}

body {
  min-height: 100vh;
  background-color: var(--body-color);
  transition: var(--tran-05);
}

::selection {
  background-color: var(--primary-color);
  color: #fff;
}

body.dark {
  --body-color: #18191a;
  --sidebar-color: #242526;
  --primary-color: #3a3b3c;
  --primary-color-light: #3a3b3c;
  --toggle-color: #fff;
  --text-color: #ccc;
}

/* ===== Sidebar ===== */
.sidebar {
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  width: 250px;
  padding: 10px 14px;
  background: var(--sidebar-color);
  transition: var(--tran-05);
  z-index: 100;
}

.sidebar.close {
  width: 88px;
}

/* ===== Reusable code - Here ===== */
.sidebar li {
  height: 50px;
  list-style: none;
  display: flex;
  align-items: center;
  margin-top: 10px;
}

.sidebar header .image,
.sidebar .icon {
  min-width: 60px;
  border-radius: 6px;
}

.sidebar .icon {
  min-width: 60px;
  border-radius: 6px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
}

.sidebar .text,
.sidebar .icon {
  color: var(--text-color);
  transition: var(--tran-03);
}

.sidebar .text {
  font-size: 17px;
  font-weight: 500;
  white-space: nowrap;
  opacity: 1;
}

.sidebar.close .text {
  opacity: 0;
}

/* =========================== */

.sidebar header {
  position: relative;
}

.sidebar header .image-text {
  display: flex;
  align-items: center;
}

.sidebar header .logo-text {
  display: flex;
  flex-direction: column;
}

header .image-text .name {
  margin-top: 2px;
  font-size: 18px;
  font-weight: 600;
}

header .image-text .profession {
  font-size: 16px;
  margin-top: -2px;
  display: block;
}

.sidebar header .image {
  display: flex;
  align-items: center;
  justify-content: center;
}

.sidebar header .image img {
  width: 40px;
  border-radius: 6px;
}

.sidebar header .toggle {
  position: absolute;
  top: 50%;
  right: -25px;
  transform: translateY(-50%) rotate(180deg);
  height: 25px;
  width: 25px;
  background-color: var(--primary-color);
  color: var(--sidebar-color);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  cursor: pointer;
  transition: var(--tran-05);
}

body.dark .sidebar header .toggle {
  color: var(--text-color);
}

.sidebar.close .toggle {
  transform: translateY(-50%) rotate(0deg);
}

.sidebar .menu {
  margin-top: 20px;
}

.sidebar li.search-box {
  border-radius: 6px;
  background-color: var(--primary-color-light);
  cursor: pointer;
  transition: var(--tran-05);
}

.sidebar li.search-box input {
  height: 100%;
  width: 100%;
  outline: none;
  border: none;
  background-color: var(--primary-color-light);
  color: var(--text-color);
  border-radius: 6px;
  font-size: 17px;
  font-weight: 500;
  transition: var(--tran-05);
}

.sidebar li a {
  list-style: none;
  height: 100%;
  background-color: transparent;
  display: flex;
  align-items: center;
  height: 100%;
  width: 100%;
  border-radius: 10px;
  text-decoration: none;
  transition: var(--tran-03);
}

.sidebar li a:hover {
  background-color: var(--primary-color);
}

.sidebar li a:hover .icon,
.sidebar li a:hover .text {
  color: var(--sidebar-color);
}

body.dark .sidebar li a:hover .icon,
body.dark .sidebar li a:hover .text {
  color: var(--text-color);
}

.sidebar .menu-bar {
  height: calc(100% - 55px);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  /* overflow-y: scroll; */
  /* background-color: #18191a; */
  /* position: fixed; */
}

.menu-bar::-webkit-scrollbar {
  display: none;
}

.sidebar .menu-bar .mode {
  border-radius: 6px;
  background-color: var(--primary-color-light);
  position: relative;
  transition: var(--tran-05);
}

.menu-bar .mode .sun-moon {
  height: 50px;
  width: 60px;
}

.mode .sun-moon i {
  position: absolute;
}

.mode .sun-moon i.sun {
  opacity: 0;
}

body.dark .mode .sun-moon i.sun {
  opacity: 1;
}

body.dark .mode .sun-moon i.moon {
  opacity: 0;
}

.menu-bar .bottom-content .toggle-switch {
  position: absolute;
  right: 0;
  height: 100%;
  min-width: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 6px;
  cursor: pointer;
}

.toggle-switch .switch {
  position: relative;
  height: 22px;
  width: 40px;
  border-radius: 25px;
  background-color: var(--toggle-color);
  transition: var(--tran-05);
}

.switch::before {
  content: "";
  position: absolute;
  height: 15px;
  width: 15px;
  border-radius: 50%;
  top: 50%;
  left: 5px;
  transform: translateY(-50%);
  background-color: var(--sidebar-color);
  transition: var(--tran-04);
}

body.dark .switch::before {
  left: 20px;
}

.home {
  position: absolute;
  top: 0;
  top: 0;
  left: 250px;
  height: 110vh;
  width: calc(100% - 250px);
  background-color: var(--body-color);
  transition: var(--tran-05);
}

.home .text {
  font-size: 30px;
  font-weight: 500;
  color: var(--text-color);
  padding: 12px 60px;
}

.home .color-text {
  color: var(--text-color);
}

.sidebar.close~.home {
  left: 88px;
  height: 100vh;
  width: calc(100% - 75px);
}

body.dark .home .text {
  color: var(--text-color);
}
</style>
