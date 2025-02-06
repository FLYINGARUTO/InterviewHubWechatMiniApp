"use strict";
const common_vendor = require("../common/vendor.js");
const utils_storage = require("./storage.js");
common_vendor.index.addInterceptor("request", {
  //invoke是在触发前执行
  invoke(args) {
    args.header = {
      "Authorization": utils_storage.storage.getStorageToken()
    };
  }
});
//# sourceMappingURL=../../.sourcemap/mp-weixin/utils/intercept.js.map
