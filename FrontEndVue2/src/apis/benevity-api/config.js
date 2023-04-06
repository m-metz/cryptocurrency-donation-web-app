/*
Copy config.template.js to config.js and fill in the required configuration parameters.
*/

/**
 * @typedef {Object} config
 * @property {string} baseUrl The url to connect to our Benevity API Proxy.
 * @property {number} useMockForUnimplemented If true, use mock JSON data (if
 *  available) if the API has not been implemented yet. If false, raise an
 *  error if the API is not implemented.
 */

/**
 * @type {config} object
 */
export default {
  baseUrl: "http://localhost:8080",
  useMockForUnimplemented: true,
};
