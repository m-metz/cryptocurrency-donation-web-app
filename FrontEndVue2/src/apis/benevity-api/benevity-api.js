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
   * Provides full details of a cause, like their full description etc. that a
   * search would shorten.
   *
   * https://developer.benevity.org/guides/causes/get-cause-details.html
   *
   * @param {string} id A causes id, it may be alphanumeric and have -.
   * @returns {Promise}
   */
  async causes(id) {
    return fetch(this.#baseUrl + "/causes/" + id)
      .then(function (response) {
        if (!response.ok) {
          console.log(response.text());
          throw new Error(`Could not get cause details by id=${id}`);
        }
        return response.json();
      })
      .catch(function (ex) {
        console.error(ex);
      });
  }

  /**
   * Search for causes on the benevity API by passing in a query string.
   *
   * https://developer.benevity.org/guides/causes/search-cause.html
   *
   * You can also use "id:123-456789" format to look up a specific id, or
   * '"123-456789"' to get a specific cause, and the cause id's children.
   * @param {string} [query="*"]
   * @returns {Promise}
   */
  async searchCauses(query = "*") {
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
