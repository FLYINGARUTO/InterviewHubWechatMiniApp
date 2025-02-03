"use strict";
const common_vendor = require("../common/vendor.js");
common_vendor.index.addInterceptor("request", {
  invoke(args) {
    common_vendor.index.__f__("log", "at utils/intercept.js:3", "------invoke----args---> ", args);
  }
});
//# sourceMappingURL=../../.sourcemap/mp-weixin/utils/intercept.js.map
