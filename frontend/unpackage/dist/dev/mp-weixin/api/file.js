"use strict";
const common_vendor = require("../common/vendor.js");
const config_config = require("../config/config.js");
const fileApi = {
  uploadFile(tempfile) {
    return new Promise((resolve, reject) => {
      common_vendor.index.uploadFile({
        url: `${config_config.config.apiBaseUrl}/file/upload`,
        filePath: tempfile,
        name: "file",
        formData: {},
        success: (uploadFileRes) => {
          common_vendor.index.__f__("log", "at api/file.js:12", uploadFileRes.data);
          resolve(JSON.parse(uploadFileRes.data));
        },
        fail(err) {
          common_vendor.index.__f__("log", "at api/file.js:16", "file upload failed", err);
        }
      });
    });
  }
};
exports.fileApi = fileApi;
//# sourceMappingURL=../../.sourcemap/mp-weixin/api/file.js.map
