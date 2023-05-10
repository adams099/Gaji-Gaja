import Home from "./pages/Home.vue";
import Login from "./pages/LoginPage.vue";
import AllUser from "./pages/AllUser.vue";

export default [
  {
    name: "login",
    path: "/",
    component: Login,
  },

  {
    name: "home",
    path: "/home",
    component: Home,
  },
  {
    name: "allUser",
    path: "/allUser",
    component: AllUser,
  },
];
