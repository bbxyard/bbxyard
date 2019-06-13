/* 用于定义JS统一方法 */
/* 
  如：
  // 判断是否为空
  // '', {}, [], 0, null, undefined, false
  export function isEmpty(obj) {
    if (Array.isArray(obj)) {
      return obj.length === 0
    } else if (isObject(obj)) {
      return Object.keys(obj).length === 0
    } else {
      return !obj
    }
  }
*/
