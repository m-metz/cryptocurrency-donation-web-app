/* eslint-env node */
require("@rushstack/eslint-patch/modern-module-resolution");

module.exports = {
  root: true,
  /*
  es2022 didn't seem to work here, so I manually set parserOptions.ecmaVersion instead.
  */
  // env: {
  //   es2022: true,
  // },
  extends: [
    "plugin:vue/essential",
    "eslint:recommended",
    "@vue/eslint-config-prettier",
  ],
  overrides: [
    {
      files: ["cypress/e2e/**.{cy,spec}.{js,ts,jsx,tsx}"],
      extends: ["plugin:cypress/recommended"],
    },
  ],
  parserOptions: {
    ecmaVersion: 2022,
  },
};
