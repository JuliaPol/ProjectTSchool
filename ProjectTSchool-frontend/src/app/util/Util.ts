export class Util {
  public static myIndexOf(o, list, objectField: string, listField) {
    if (!o) {
      return;
    }
    for (let i = 0; i < list.length; i++) {
      if (objectField === null) {
        if (list[i][listField] === o) {
          return i;
        }
      } else {
        if (list[i][listField] === o[objectField]) {
          return i;
        }
      }

    }
    return -1;
  }
}
