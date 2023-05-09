import Vue from "vue";
import { BootstrapVue, IconsPlugin, BootstrapVueIcons } from "bootstrap-vue";
import App from "./App.vue";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "bootstrap-icons/font/bootstrap-icons";

import { library } from "@fortawesome/fontawesome-svg-core";
import { faUserSecret } from "@fortawesome/free-solid-svg-icons";

library.add(faUserSecret);

/* add font awesome icon component */
Vue.component("font-awesome-icon");

Vue.config.productionTip = false;

// import vue router
import VueRouter from "vue-router";
import Routes from "./routes";

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueRouter);
Vue.use(BootstrapVueIcons);

const router = new VueRouter({
  routes: Routes,
  mode: "history",
});

// router.replace({path: '/'})

new Vue({
  render: (h) => h(App),
  router: router,
}).$mount("#app");
