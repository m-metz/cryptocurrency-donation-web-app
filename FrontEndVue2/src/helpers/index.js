export function isEmptyObject(obj) {
  let name;
  for (name in obj) {
    return false;
  }
  return true;
}

export function warnStackTrace(message) {
  const e = new Error();
  console.warn(message, e.stack);
}
