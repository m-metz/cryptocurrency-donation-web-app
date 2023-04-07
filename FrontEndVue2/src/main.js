import Vue from "vue";
import { createPinia, PiniaVuePlugin } from "pinia";

import App from "./App.vue";
import router from "./router";
import benevityApi from "@/apis/benevity-api";

import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap-css-only/css/bootstrap.min.css";
import "mdbvue/lib/css/mdb.min.css";
import "./assets/main.css";

Vue.use(PiniaVuePlugin);

new Vue({
  router,
  pinia: createPinia(),
  render: (h) => h(App),
}).$mount("#app");

benevityApi.searchCauses("*").then((causes) => {
  console.log(causes);
});

benevityApi.causes("124-122680572RR0001").then((cause) => {
  console.log(cause);
});
