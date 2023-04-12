/**
 * This provides access to our Cryptocurrency Donation API.
 */

import config from "./config";

import CryptocurrencyDonationWebAppApi from "./cryptocurrency-donation-web-app-api";

export default new CryptocurrencyDonationWebAppApi(config);
