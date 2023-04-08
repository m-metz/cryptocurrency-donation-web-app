import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  base: import.meta.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "non-profits",
      component: () => import("@/views/NonProfitsView.vue"),
    },
    {
      path: "/non-profit/:id",
      name: "non-profit",
      component: () => import("@/views/NonProfitView.vue"),
    },
    {
      path: "/cryptocurrency-donations",
      name: "cryptocurrency-donations",
      // route level code-splitting
      // this generates a separate chunk (...View.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("@/views/CryptocurrencyDonationsView.vue"),
    },
  ],
});

function hasQueryParams(route) {
  return !!Object.keys(route.query).length;
}

/*
Keep query params if to route does not have any.
*/
router.beforeEach((to, from, next) => {
  if (!hasQueryParams(to) && hasQueryParams(from)) {
    next({ name: to.name, params: to.params, query: from.query });
  } else {
    next();
  }
});

export default router;
