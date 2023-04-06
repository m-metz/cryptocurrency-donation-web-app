export default class ExtendableError extends Error {
  /**
   * Creates an instance of NotImplementedError.
   * @constructor
   * @abstract
   * @param {string} [notImplementedName=""] Used in default error message if
   *    you don't pass in a message string after this param, else keep it as
   *    "".
   * @param {string} [message=""] The error message for the error object.
   * @param {...} errorParams params to pass to the Error class.
   */
  constructor(...errorParams) {
    super(...errorParams);

    if (this.constructor == ExtendableError) {
      throw new Error("Abstract classes can't be instantiated.");
    }

    // Maintains proper stack trace for where our error was thrown (only available on V8)
    if (Error.captureStackTrace) {
      Error.captureStackTrace(this, ExtendableError);
    }
  }
}
