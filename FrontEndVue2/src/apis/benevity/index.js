/*
TODO: Move this to the backend and pass OAuth tokens or retrieved HMAC codes
back to the client to use. This way the shared secrets will be protected.

Copy config.template.js to config.js and fill in the required configuration parameters.
*/
import config from "./config";

console.log(config.baseUrl);
