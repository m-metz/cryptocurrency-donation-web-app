import { Buffer } from "buffer";

import "isomorphic-fetch";

import { isEmptyObject } from "@/helpers";

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
  #clientId;
  #clientSecret;
  #grantType;
  #scopes;
  #adapter;
  #companyId;
  #apiKey;
  #viteProxyUrl;

  // OAuth token tracking
  #tokenExpiryTime;
  #accessToken;

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
  This actually is used, just not on the this object. VSCode ts syntax highlighting can't detect that it's used.
  */
  #initialize(config) {
    if (!isEmptyObject(config)) {
      Object.assign(this, config);
      if (typeof this.#viteProxyUrl === "string") {
        this.baseUrl = this.#viteProxyUrl;
      }
    }
  }

  refreshApiAuthentication() {
    // Set variables
    var currentTime = Date.now();

    // Get a new token if current one expired, if no token exists, or if no expiryTime exists
    if (
      currentTime >= this.#tokenExpiryTime ||
      !this.#accessToken ||
      !this.#tokenExpiryTime
    ) {
      let tokenUrl = this.#baseUrl + "/oauth2/token";

      let options = {
        method: "POST",
        credentials: "include",
        headers: {
          authorization:
            "Basic " +
            Buffer.from(`${this.#clientId}:${this.#clientSecret}`).toString(
              "base64"
            ),
          "content-type": "application/x-www-form-urlencoded",
        },
        body: new URLSearchParams({
          grant_type: this.#grantType,
          scope: this.#scopes,
        }),
      };
      fetch(tokenUrl, options)
        .then(function (response) {
          if (!response.ok) {
            throw new Error("Could not get OAuth token");
          }
          return response.json();
        })
        .then(function (json) {
          console.log("parsed json", json);
        });
      // pm.sendRequest(options, function (err, response) {
      //   this.#accessToken = response.json().access_token;

      //   // Set the ExpiresInTime variable to the time given in the response if it exists
      //   if (response.json().expires_in) {
      //     this.#tokenExpiryTime =
      //       currentTime + response.json().expires_in * 0.9 * 1000;
      //   }
      // });
    }
  }

  get baseUrl() {
    return this.baseUrl;
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

  set viteProxyUrl(value) {
    this.#viteProxyUrl = value;
  }
}
