import "isomorphic-fetch";

import NotImplementedError from "@/errors/not-implemented-error";
import { isEmptyObject, warnStackTrace } from "@/helpers/index.js";
import searchCauses from "./mocks/search/causes.json";

/**
 * @typedef {import("./config").config} config
 */

/**
 * Singleton class used to access the Benevity API
 *
 * @class BenevityApi
 * @typedef {BenevityApi}
 */
export default class BenevityApi {
  static #instance;

  #baseUrl;
  #useMockForUnimplemented;

  /**
   * Creates an instance of BenevityApi.
   *
   * @constructor
   * @param {config} config
   */
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

  /**
   * Initialize the config params.
   *
   * This actually is used, just not on the `this` object. VSCode ts syntax highlighting can't detect that it's used.
   *
   * @param {config} config
   */
  #initialize(config) {
    if (!isEmptyObject(config)) {
      this.#baseUrl = config.baseUrl;
      this.#useMockForUnimplemented = config.useMockForUnimplemented;
    }
  }

  /**
   *
   * @param {string} query
   */
  /*
  Unused params because the API is not implemented.
  */
  // eslint-disable-next-line no-unused-vars
  searchCauses(query = "*") {
    if (this.#useMockForUnimplemented) {
      if (query !== "*") {
        warnStackTrace(
          `searchCause is mocked yet a non-default query was passed in. query=${query}`
        );
      }
      return searchCauses;
    } else {
      /*
        Remove this.#useMockForUnimplemented when implemented.
      */
      throw NotImplementedError("searchCauses");
    }
  }
}
