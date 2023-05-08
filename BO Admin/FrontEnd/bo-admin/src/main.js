import Vue from "vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import App from "./App.vue";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faPhone } from "@fortawesome/free-solid-svg-icons";

library.add(faPhone);

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// import vue router
import VueRouter from "vue-router";
import Routes from "./routes";

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueRouter);

const router = new VueRouter({
  routes: Routes,
  mode: "history",
});

// router.replace({path: '/'})

new Vue({
  render: (h) => h(App),
  router: router,
}).$mount("#app");
