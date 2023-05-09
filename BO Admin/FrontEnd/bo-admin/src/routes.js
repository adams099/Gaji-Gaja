
import Home from "./pages/Home.vue";
import Login from "./pages/LoginPage.vue"

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

]