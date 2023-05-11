import Home from "./pages/Home.vue";
import Login from "./pages/LoginPage.vue";
// import AllUser from "./pages/AllUser.vue";
// import Company from "./pages/Company.vue";

export default [
  {
    name: "login",
    path: "/auth",
    component: Login,
  },

  {
    name: "home",
    path: "/",
    component: Home,
    // children: [
    //   {
    //     name: "allUser",
    //     path: "/allUser",
    //     component: AllUser,
    //   },
    //   {
    //     name: "allUser",
    //     path: "/allUser",
    //     component: AllUser,
    //   },
    //   {

    //     name: "company",
    //     path: "/company",
    //     component: Company,
    //   },
    // ]
  },

];
