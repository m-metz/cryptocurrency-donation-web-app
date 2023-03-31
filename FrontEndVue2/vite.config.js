import { fileURLToPath, URL } from "node:url";

import { defineConfig } from "vite";
import legacy from "@vitejs/plugin-legacy";
import vue2 from "@vitejs/plugin-vue2";
import vue2Jsx from "@vitejs/plugin-vue2-jsx";
import benevityApiConfig from "./src/apis/benevity-api/config";

let proxy = null;
if (typeof benevityApiConfig.viteProxyUrl === "string") {
  const regExp = new RegExp("^\\" + benevityApiConfig.viteProxyUrl + "");

  proxy = {
    [benevityApiConfig.viteProxyUrl]: {
      target: benevityApiConfig.baseUrl,
      changeOrigin: true,
      rewrite: (path) => path.replace(regExp, ""),
    },
  };

  benevityApiConfig.baseUrl = benevityApiConfig.viteProxyUrl;
}

// https://vitejs.dev/config/
export default defineConfig({
  css: {
    devSourcemap: true,
  },
  plugins: [
    vue2(),
    vue2Jsx(),
    legacy({
      targets: ["ie >= 11"],
      additionalLegacyPolyfills: ["regenerator-runtime/runtime"],
    }),
  ],
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url)),
    },
  },
  server: {
    // cors: { origin: benevityApiConfig.baseUrl },
    proxy: proxy,
  },
});
