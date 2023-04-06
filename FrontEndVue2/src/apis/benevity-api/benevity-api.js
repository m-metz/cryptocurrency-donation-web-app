import "isomorphic-fetch";

import { isEmptyObject } from "@/helpers/index.js";

/**
 * Singleton class used to access the Benevity API
 *
 * @class BenevityApi
 * @typedef {BenevityApi}
 */
export default class BenevityApi {
  static #instance;

  // API authentication configuration
  #baseUrl;
  // #useMocks;

  constructor(config) {
    if (BenevityApi.#instance) {
      BenevityApi.#instance.#initialize(config);
      return BenevityApi.#instance;
    }

    BenevityApi.#instance = this;

    if (isEmptyObject(config)) {
      throw new Error(
        "BenevityApi config is not defined or is an empty object."
      );
    }

    BenevityApi.#instance.#initialize(config);
  }

  /*
  This actually is used, just not on the `this` object. VSCode ts syntax highlighting can't detect that it's used.
  */
  #initialize(config) {
    if (!isEmptyObject(config)) {
      this.#baseUrl = config.baseUrl;
    }
  }
}
