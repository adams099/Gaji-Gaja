import Vue from "vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import App from "./App.vue";
import VueRouter from "vue-router";
import Routes from "./routes.js";
import VueSession from "vue-session";
import VueBootstrapModal from "vue-bootstrap-modal";

import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";

const options = {};

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "@fortawesome/fontawesome-free/css/all.css";

Vue.config.productionTip = false;

Vue.use(Toast, options);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueRouter);
Vue.use(VueSession);
Vue.use(VueBootstrapModal);

Vue.use(Toast, {
  // registration props here
});

const router = new VueRouter({
  routes: Routes,
  mode: "history",
});

// router.replace({ path: '/auth' })

new Vue({
  render: (h) => h(App),
  router: router,
}).$mount("#app");
