import { ethers } from "ethers";
import "isomorphic-fetch";

import { isEmptyObject } from "@/helpers/index.js";

/**
 * @typedef {import("./config").config} config
 */

/**
 * Singleton class used to access the Benevity API
 *
 * @class CryptocurrencyDonationWebAppApi
 * @typedef {CryptocurrencyDonationWebAppApi}
 */
export default class CryptocurrencyDonationWebAppApi {
  static #instance;

  #baseUrl;
  #toCryptoAddress;

  /**
   * Creates an instance of CryptocurrencyDonationWebAppApi.
   *
   * @constructor
   * @param {config} config
   */
  constructor(config) {
    if (CryptocurrencyDonationWebAppApi.#instance) {
      CryptocurrencyDonationWebAppApi.#instance.#initialize(config);
      return CryptocurrencyDonationWebAppApi.#instance;
    }

    CryptocurrencyDonationWebAppApi.#instance = this;

    if (isEmptyObject(config)) {
      throw new Error(
        "CryptocurrencyDonationWebAppApi config is not defined or is an empty object."
      );
    }

    CryptocurrencyDonationWebAppApi.#instance.#initialize(config);
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
      this.#toCryptoAddress = config.toCryptoAddress;
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

  /**
   * Description placeholder
   * @async
   * @param {Object} params
   * @param {string} params.nonProfitId - Benevity's non-profit ID.
   * @param {string} [params.donorUserId = ""] - The userID (user attribute)
   *  on Benevity's users. Might be used to look up users in the future, but
   *  for now it is not needed. Non-nullable, so we set it to "".
   * @param {string} [params.cryptocurrencyTxId = null] - We look up
   *  cryptocurrency by From address for now, but we may make the TxID the new
   *  mandatory way to look up transactions. Nullable field.
   * @param {TransactionResponse} txResponse
   * @param {FormData} params.formData - Form data from src/views/NonProfitView.vue
   * @returns {Promise}
   */
  async cryptocurrencyDonationCreateDonation({
    nonProfitId,
    donorUserId = "",
    txResponse = null,
    formData,
  }) {
    if (txResponse.to !== this.#toCryptoAddress) {
      throw Error(
        `You did not send to the correct address. Expected ${
          this.#toCryptoAddress
        }, you sent to ${txResponse.to}`
      );
    }
    //Create an object from the form data entries
    const formDataObject = Object.fromEntries(formData.entries());

    /*
    initialCryptoAmount is not used, but needs to be destructured so that
    taxReceipt holds the correct properties and doesn't have extra.

    That being said, the backend might ignore the extra properties.
    */
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const { initialCryptoAmount, ...taxReceipt } = formDataObject;

    const cryptocurrencyDonation = {
      nonProfitId,
      donorUserId,
      cryptocurrencyTxId: txResponse.hash,
      fromCryptoAddress: txResponse.from,
      toCryptoAddress: txResponse.to,
      initialCryptoAmount: ethers.formatEther(txResponse.value),
      receipted: true,
      taxReceipt,
    };

    return fetch(this.#baseUrl + "/cryptocurrencydonation/createDonation", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(cryptocurrencyDonation),
    }).then(
      async function (response) {
        if (!response.ok) {
          const responseText = await response.text();
          throw new Error(
            `Could not create cryptocurrencydonation. ${responseText}`
          );
        }
        return response.text();
      },
      function (err) {
        /*
       Log full error object to console for debugging purposes.
       */
        console.error(err);
        throw new Error(`Could not create cryptocurrencydonation. (${err})`);
      }
    );
  }

  get toCryptoAddress() {
    return this.#toCryptoAddress;
  }
}
