/**
 * This provides access to the Benevity's API.
 *
 * Note that it is proxied through our own server, so it may not have the exact
 * same endpoints as Benevity's API.
 */

/*
Copy config.template.js to config.js and fill in the required configuration parameters.
*/
import config from "./config";

import BenevityApi from "./benevity-api";

export default new BenevityApi(config);
