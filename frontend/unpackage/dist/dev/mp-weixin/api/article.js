"use strict";
const common_vendor = require("../common/vendor.js");
const utils_request = require("../utils/request.js");
const articleApi = {
  publish(article) {
    return utils_request.request({
      url: "/article/publish",
      method: "POST",
      data: article
    });
  },
  list(queryParam) {
    common_vendor.index.__f__("log", "at api/article.js:13", queryParam);
    return utils_request.request({
      url: "/article/list",
      method: "POST",
      data: queryParam
    });
  },
  getArticle(id) {
    return utils_request.request({
      url: `/article/${id}`
    });
  },
  follow(authorId) {
    return utils_request.request({
      url: `/article/follow/${authorId}`
    });
  },
  unfollow(authorId) {
    return utils_request.request({
      url: `/article/unfollow/${authorId}`
    });
  }
};
exports.articleApi = articleApi;
//# sourceMappingURL=../../.sourcemap/mp-weixin/api/article.js.map
