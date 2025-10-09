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
      url: `/interaction/follow/${authorId}`
    });
  },
  unfollow(authorId) {
    return utils_request.request({
      url: `/interaction/unfollow/${authorId}`
    });
  },
  like(params) {
    return utils_request.request({
      url: `/interaction/like`,
      method: "POST",
      data: params
    });
  },
  unlike(params) {
    return utils_request.request({
      url: `/interaction/unlike`,
      method: "DELETE",
      data: params
    });
  },
  star(params) {
    return utils_request.request({
      url: `/interaction/star`,
      method: "POST",
      data: params
    });
  },
  unstar(params) {
    return utils_request.request({
      url: `/interaction/unstar`,
      method: "DELETE",
      data: params
    });
  },
  comment(params) {
    return utils_request.request({
      url: "/article/comment/new",
      method: "POST",
      data: params
    });
  },
  getInteractionData(articleId, userId) {
    return utils_request.request({
      url: `/interaction/article/${articleId}/${userId}`
    });
  },
  getComments(params) {
    return utils_request.request({
      url: "/article/comment/list",
      method: "POST",
      data: params
    });
  },
  getUserStatistics(userId) {
    return utils_request.request({
      url: `/interaction/user-statistics/${userId}`
    });
  },
  getMyFavList(userId) {
    return utils_request.request({
      url: `/article/star-list/${userId}`
    });
  }
};
exports.articleApi = articleApi;
//# sourceMappingURL=../../.sourcemap/mp-weixin/api/article.js.map
