"use strict";
const common_vendor = require("../common/vendor.js");
const tokenKey = "Authorization";
const storage = {
  setStorageToken(token) {
    common_vendor.index.setStorageSync(tokenKey, "Bearer " + token);
  },
  removeStorageToken() {
    common_vendor.index.removeStorageSync(tokenKey);
  },
  getStorageToken() {
    return common_vendor.index.getStorageSync(tokenKey);
  }
};
exports.storage = storage;
//# sourceMappingURL=../../.sourcemap/mp-weixin/utils/storage.js.map
