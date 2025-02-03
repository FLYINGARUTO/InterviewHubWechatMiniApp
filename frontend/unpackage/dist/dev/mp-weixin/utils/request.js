"use strict";
const common_vendor = require("../common/vendor.js");
const config_config = require("../config/config.js");
const request = (option) => {
  return new Promise((resolve, reject) => {
    common_vendor.index.request({
      url: config_config.config.apiBaseUrl + option.url,
      // Combine base URL with endpoint
      data: option.data || {},
      // Use provided data or an empty object
      header: option.header || {},
      // Use provided headers or an empty object
      method: option.method || "GET",
      // Default HTTP method is GET
      // Callback for successful request
      success: (res) => {
        var _a;
        if (res.data.code == 2e4) {
          resolve(res);
        } else {
          let errInfo = ((_a = res.data) == null ? void 0 : _a.message) || "unknown error";
          common_vendor.index.showToast({
            icon: "error",
            // Display an error icon
            duration: 3e3,
            // Show the toast for 3 seconds
            title: errInfo
            // Show the error message
          });
          setTimeout(() => {
            reject(new Error(errInfo));
          }, 3e3);
        }
      },
      // Callback for failed request
      fail: (err) => {
        let errInfo = "";
        if (err.errMsg.includes("timeout")) {
          errInfo = "request timeout, please try again";
        } else if (err.errMsg.includes("abort")) {
          errInfo = "request data wrong, please try again";
        } else {
          errInfo = "Internet request wrong";
        }
        common_vendor.index.showToast({
          icon: "none",
          // Display a neutral icon
          title: errInfo,
          // Show the error message
          duration: 3e3
          // Show the toast for 3 seconds
        });
        reject(new Error(errInfo));
      }
    });
  });
};
exports.request = request;
//# sourceMappingURL=../../.sourcemap/mp-weixin/utils/request.js.map
