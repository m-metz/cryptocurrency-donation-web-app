/**
 * This provides access to the Benevity's API.
 *
 * Note that it is proxied through our own server, so it may not have the exact
 * same endpoints as Benevity's API.
 */

import config from "./config";

import BenevityApi from "./benevity-api";

export default new BenevityApi(config);
