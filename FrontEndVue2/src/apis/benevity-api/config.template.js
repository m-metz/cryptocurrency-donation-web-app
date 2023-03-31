/*
Copy config.template.js to config.js and fill in the required configuration parameters.
*/

export default {
  baseUrl: "https://api.benevity-staging.org",
  /*
  Required.
  
  Benevity should give you this clientId. It is essentially a username. It allows you to get OAuth tokens from their OAuth endpoint.
  */
  clientId: "0oaaqon7apsE1vBd54x7",
  /*
  Required.
  
  Benevity should give you this secret (and should *never* be checked into git).
  It allows you to get OAuth tokens from their OAuth endpoint.
  */
  clientSecret: "",
  grantType: "client_credentials",
  /*
  Optional.

  "benevity/api" is the default scope if undefined. More scopes could be added in the future.
  */
  // scopes: ["benevity/api"],
  /*
  Required.

  For Benevity's legacy XML API. This typically doesn't change as almost all requests go to this adapter.
  */
  adapter: "Adapter.General",
  /*
  Required.

  A company id for the company integrating with benevity.

  For their legacy XML API: /Adapter.General/[company ID]/[operation]
  */
  companyId: "3L15RCQVSY",
  /*
  Optional. If access to the Legacy XML API is needed.

  This is a secret key from Benevity (and should *never* be checked into git).
  It will be used to give you access to their legacy XML API.
  */
  apiKey: "",

  /*
  If set to a relative path string, proxy benevityApi requests throught the Vite server in dev or
  preview mode. Else send directly to the Benevity API from the browser.
  */
  viteProxyUrl: "/benevityApi",
};
