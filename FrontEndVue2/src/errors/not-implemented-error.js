import ExtendableError from "./extendable-error";

export default class NotImplementedError extends ExtendableError {
  /**
   * Creates an instance of NotImplementedError.
   * @constructor
   * @param {string} [notImplementedName=""] Used in default error message if
   *    you don't pass in a message string after this param, else keep it as
   *    "".
   * @param {string} [message=""] The error message for the error object.
   * @param {...} errorParams params to pass to the Error class.
   */
  constructor(notImplementedName = "", message = "", ...errorParams) {
    if (notImplementedName !== "" && !errorParams[0]) {
      message = notImplementedName + " is not implemented.";
    }

    super(message, ...errorParams);

    this.name = "NotImplementedError";
  }
}
