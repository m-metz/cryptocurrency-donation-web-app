import Vue from "vue";
import { createPinia, PiniaVuePlugin } from "pinia";

import App from "./App.vue";
import router from "./router";
import benevityApi from "@/apis/benevity-api";
import "@fortawesome/fontawesome-free/css/all.min.css";
/*
Bootstrap has to be customized before mdb loads, so don't import it in main.scss
*/
import "./assets/bootstrap-customized.scss";
import "mdbvue/lib/css/mdb.min.css";
import "./assets/main.scss";

Vue.use(PiniaVuePlugin);

new Vue({
  router,
  pinia: createPinia(),
  render: (h) => h(App),
}).$mount("#app");

benevityApi.searchCauses("*").then((causes) => {
  console.log(causes);
});

benevityApi
  .adapterGeneralGetUserProfile("TestUserCA")
  .then((getUserProfile) => {
    console.log(getUserProfile);
  });
