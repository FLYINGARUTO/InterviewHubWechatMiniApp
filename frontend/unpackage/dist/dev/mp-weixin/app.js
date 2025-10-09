"use strict";
Object.defineProperty(exports, Symbol.toStringTag, { value: "Module" });
const common_vendor = require("./common/vendor.js");
const stores_user = require("./stores/user.js");
const utils_storage = require("./utils/storage.js");
require("./utils/intercept.js");
if (!Math) {
  "./pages/login.js";
  "./pages/reg/reg.js";
  "./pages/reg/reg-success.js";
  "./pages/index/index.js";
  "./pages/my/my.js";
  "./pages/articles/publish.js";
  "./pages/articles/publish-success.js";
  "./components/global/m-card.js";
  "./pages/articles/article.js";
  "./pages/my/my-fav.js";
}
const _sfc_main = {
  //uniapp的生命周期
  onLaunch: async function() {
    let token = utils_storage.storage.getStorageToken();
    if (token) {
      let useUser = stores_user.useUserStore();
      await useUser.getUserInfo(token);
      common_vendor.index.__f__("log", "at App.vue:16", "You are logged in automatically~");
      common_vendor.index.showToast({
        title: "You are logged in automatically~"
      });
      setTimeout(() => {
        common_vendor.index.switchTab({
          url: "/pages/index/index"
        });
      });
    }
  },
  onShow: function() {
    common_vendor.index.__f__("log", "at App.vue:30", "App Show");
  },
  onHide: function() {
    common_vendor.index.__f__("log", "at App.vue:33", "App Hide");
  }
};
function createApp() {
  const app = common_vendor.createSSRApp(_sfc_main);
  app.use(common_vendor.createPinia());
  return {
    app,
    Pinia: common_vendor.Pinia
  };
}
createApp().app.mount("#app");
exports.createApp = createApp;
//# sourceMappingURL=../.sourcemap/mp-weixin/app.js.map
