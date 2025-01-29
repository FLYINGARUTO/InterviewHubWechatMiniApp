"use strict";
const utils_request = require("../utils/request.js");
const baseApi = {
  getParamListByBaseName(baseName) {
    return utils_request.request({
      url: `/base/${baseName}`
    });
  }
};
exports.baseApi = baseApi;
//# sourceMappingURL=../../.sourcemap/mp-weixin/api/base.js.map
