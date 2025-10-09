"use strict";
const utils_request = require("../utils/request.js");
const userApi = {
  getCountByUsername(username) {
    return utils_request.request({
      url: `/user/count/${username}`
    });
  },
  getVerificationCode(email, type) {
    return utils_request.request({
      url: `/user/verification/${email}/${type}`
    });
  },
  checkCodeValid(email, code) {
    return utils_request.request({
      url: `/user/checkCode/${code}/${email}`
    });
  },
  register(user) {
    return utils_request.request({
      url: `/user/register`,
      method: "POST",
      data: user
    });
  },
  login(user) {
    return utils_request.request({
      url: `/user/login`,
      method: "POST",
      data: user
    });
  },
  logout() {
    return utils_request.request({
      url: `/user/logout`,
      method: "POST"
    });
  },
  getUserInfo(token) {
    return utils_request.request({
      url: `/user/info/${token}`
    });
  },
  updateAvatar(data) {
    return utils_request.request({
      url: "/user/avatar",
      method: "PUT",
      data
    });
  }
};
exports.userApi = userApi;
//# sourceMappingURL=../../.sourcemap/mp-weixin/api/user.js.map
