"use strict";
const common_vendor = require("../common/vendor.js");
const api_user = require("../api/user.js");
const utils_storage = require("../utils/storage.js");
const useUserStore = common_vendor.defineStore("user", () => {
  const user = common_vendor.ref({});
  const token = common_vendor.ref("");
  function login(username, password) {
    return new Promise((resolve, reject) => {
      api_user.userApi.login({
        username,
        password
      }).then(async (res) => {
        token.value = res.data.data;
        const userInfo = await getUserInfo(res.data.data);
        user.value = userInfo.data;
        utils_storage.storage.setStorageToken(res.data.data);
        resolve(res.data);
      }).catch((err) => reject(err));
    });
  }
  function getUserInfo(token2) {
    return new Promise((resolve, reject) => {
      api_user.userApi.getUserInfo(token2).then((res) => {
        user.value = res.data;
        resolve(res.data);
      }).catch((err) => reject(err));
    });
  }
  function logout() {
    return new Promise((resolve, reject) => {
      api_user.userApi.logout().then((res) => {
        user.value = {};
        token.value = "";
        utils_storage.storage.removeStorageToken();
        resolve(res.data);
      }).catch((err) => {
        reject(err);
      });
    });
  }
  return {
    user,
    token,
    login,
    logout,
    getUserInfo
  };
});
exports.useUserStore = useUserStore;
//# sourceMappingURL=../../.sourcemap/mp-weixin/stores/user.js.map
