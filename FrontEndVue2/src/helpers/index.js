export function isEmptyObject(obj) {
  var name;
  for (name in obj) {
    return false;
  }
  return true;
}

export function warnStackTrace(message) {
  let e = new Error();
  console.warn(message, e.stack);
}
