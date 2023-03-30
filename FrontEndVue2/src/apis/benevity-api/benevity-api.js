/**
 * Singleton class used to access the Benevity API
 *
 * @class BenevityApi
 * @typedef {BenevityApi}
 */

import { isEmptyObject } from "@/helpers/index.js";

export default class BenevityApi {
  static #instance;
  #baseUrl;
  #clientId;
  #clientSecret;
  #grantType;
  #adapter;
  #companyId;
  #apiKey;

  constructor(config) {
    if (BenevityApi.#instance) {
      BenevityApi.#instance.#initialize(config);
      return BenevityApi.#instance;
    }

    BenevityApi.#instance = this;

    if (isEmptyObject(config)) {
      throw new Error("BenevityApi config is not defined or an empty object.");
    }

    BenevityApi.#instance.#initialize(config);
  }

  /*
  This actually is used, just not on the this object. VSCode ts syntax highlighting can't detec that it's used.
  */
  #initialize(config) {
    if (!isEmptyObject(config)) {
      Object.assign(BenevityApi.#instance, config);
    }
  }

  set baseUrl(value) {
    this.#baseUrl = value;
  }

  set clientId(value) {
    this.#clientId = value;
  }

  set clientSecret(value) {
    this.#clientSecret = value;
  }

  set grantType(value) {
    this.#grantType = value;
  }

  set adapter(value) {
    this.#adapter = value;
  }

  set companyId(value) {
    this.#companyId = value;
  }

  set apiKey(value) {
    this.#apiKey = value;
  }
}
