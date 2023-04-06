/*
Copy config.template.js to config.js and fill in the required configuration parameters.
*/

export default {
  /*
  Required.

  The url to connect to our Benevity API Proxy.
  */
  baseUrl: "http://localhost:8080",

  /*
  Required.

  If true, use mock JSON data (if available) if the API has not been
  implemented yet.

  If false, raise an error if the API is not implemented.
  */
  useMockForUnimplemented: true,
};
