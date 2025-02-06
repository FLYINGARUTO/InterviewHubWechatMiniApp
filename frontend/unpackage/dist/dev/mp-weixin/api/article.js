"use strict";
const utils_request = require("../utils/request.js");
const articleApi = {
  publish(article) {
    return utils_request.request({
      url: "/article/publish",
      method: "POST",
      data: article
    });
  }
};
exports.articleApi = articleApi;
//# sourceMappingURL=../../.sourcemap/mp-weixin/api/article.js.map
