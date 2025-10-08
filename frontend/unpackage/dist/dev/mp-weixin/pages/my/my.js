"use strict";
const common_vendor = require("../../common/vendor.js");
const stores_user = require("../../stores/user.js");
const api_article = require("../../api/article.js");
if (!Array) {
  const _easycom_uni_icons2 = common_vendor.resolveComponent("uni-icons");
  _easycom_uni_icons2();
}
const _easycom_uni_icons = () => "../../uni_modules/uni-icons/components/uni-icons/uni-icons.js";
if (!Math) {
  _easycom_uni_icons();
}
const _sfc_main = {
  __name: "my",
  setup(__props) {
    const userStore = stores_user.useUserStore();
    const safeTop = common_vendor.ref(0);
    const userStatistics = common_vendor.ref({});
    common_vendor.onLoad(async () => {
      let { safeArea } = common_vendor.index.getSystemInfoSync();
      safeTop.value = safeArea.top;
      let res1 = await api_article.articleApi.getUserStatistics(userStore.user.id);
      userStatistics.value = res1.data.data;
      common_vendor.index.__f__("log", "at pages/my/my.vue:132", "互动数据: ", res1.data.data);
    });
    return (_ctx, _cache) => {
      return {
        a: safeTop.value + "px",
        b: common_vendor.unref(userStore).user.avatarUrl,
        c: common_vendor.t(common_vendor.unref(userStore).user.nickname),
        d: common_vendor.t(common_vendor.unref(userStore).user.level),
        e: common_vendor.p({
          type: "gear",
          size: "32",
          color: "white"
        }),
        f: common_vendor.t(userStatistics.value.fansCount),
        g: common_vendor.t(userStatistics.value.followingCount),
        h: common_vendor.t(userStatistics.value.likeCount),
        i: common_vendor.t(userStatistics.value.starCount),
        j: common_vendor.p({
          type: "flag",
          color: "#404040",
          size: "28"
        }),
        k: common_vendor.p({
          type: "star",
          color: "#404040",
          size: "28"
        }),
        l: common_vendor.p({
          type: "hand-up",
          color: "#404040",
          size: "28"
        }),
        m: common_vendor.p({
          type: "bars",
          color: "#404040",
          size: "28"
        }),
        n: common_vendor.p({
          type: "font",
          color: "#404040",
          size: "28"
        }),
        o: common_vendor.p({
          type: "vip",
          color: "#404040",
          size: "28"
        }),
        p: common_vendor.p({
          type: "auth",
          color: "#404040",
          size: "28"
        }),
        q: common_vendor.p({
          type: "phone",
          color: "#404040",
          size: "28"
        }),
        r: common_vendor.p({
          type: "chatboxes",
          color: "#404040",
          size: "28"
        }),
        s: common_vendor.p({
          type: "locked",
          color: "#404040",
          size: "28"
        }),
        t: common_vendor.p({
          type: "minus",
          color: "#404040",
          size: "28"
        }),
        v: common_vendor.p({
          type: "tune",
          color: "#404040",
          size: "28"
        }),
        w: "calc(100vh - " + safeTop.value + "px)"
      };
    };
  }
};
wx.createPage(_sfc_main);
//# sourceMappingURL=../../../.sourcemap/mp-weixin/pages/my/my.js.map
