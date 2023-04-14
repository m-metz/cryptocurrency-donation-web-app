import { fileURLToPath, URL } from "node:url";

import { defineConfig } from "vite";
import legacy from "@vitejs/plugin-legacy";
import vue2 from "@vitejs/plugin-vue2";
import vue2Jsx from "@vitejs/plugin-vue2-jsx";
import XMLLoader from "./src/vite-plugins/xml-loader";

// https://vitejs.dev/config/
export default defineConfig({
  css: {
    devSourcemap: true,
  },
  plugins: [
    vue2(),
    vue2Jsx(),
    XMLLoader(),
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
    /*
    0.0.0.0 means listen on any network for all network interfaces.

    For security, set to "localhost" if you see vite attaching to IP addresses
    *other than* local-network IP address (192.168.x, 172.[16-255].x, 10.x).
    Otherwise disabled port-forwarding on your local subnet (router) protects
    you (on trusted home or work networks).
    */
    host: "0.0.0.0",
  },
});
