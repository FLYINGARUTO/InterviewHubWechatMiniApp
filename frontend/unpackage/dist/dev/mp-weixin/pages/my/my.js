"use strict";
const common_vendor = require("../../common/vendor.js");
const stores_user = require("../../stores/user.js");
const api_article = require("../../api/article.js");
const api_file = require("../../api/file.js");
const api_user = require("../../api/user.js");
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
      common_vendor.index.__f__("log", "at pages/my/my.vue:133", "互动数据: ", res1.data.data);
    });
    common_vendor.onShow(async () => {
      let res1 = await api_article.articleApi.getUserStatistics(userStore.user.id);
      userStatistics.value = res1.data.data;
      common_vendor.index.__f__("log", "at pages/my/my.vue:139", "互动数据: ", res1.data.data);
    });
    const chooseavatar = async (e) => {
      let res = await api_file.fileApi.uploadFile(e.detail.avatarUrl);
      common_vendor.index.__f__("log", "at pages/my/my.vue:144", res.data);
      let updateRes = await api_user.userApi.updateAvatar({
        userId: userStore.user.id,
        avatar: res.data.fileName
      });
      common_vendor.index.__f__("log", "at pages/my/my.vue:149", "头像更新： ", updateRes.data.data);
      userStore.refresh(updateRes.data.data.user, updateRes.data.data.token);
    };
    const toMyFavList = () => {
      common_vendor.index.navigateTo({
        url: "/pages/my/my-fav?userId=" + userStore.user.id
      });
    };
    return (_ctx, _cache) => {
      return {
        a: safeTop.value + "px",
        b: common_vendor.unref(userStore).user.avatarUrl,
        c: common_vendor.o(chooseavatar),
        d: common_vendor.t(common_vendor.unref(userStore).user.nickname),
        e: common_vendor.t(common_vendor.unref(userStore).user.level),
        f: common_vendor.p({
          type: "gear",
          size: "32",
          color: "white"
        }),
        g: common_vendor.t(userStatistics.value.fansCount),
        h: common_vendor.t(userStatistics.value.followingCount),
        i: common_vendor.t(userStatistics.value.likeCount),
        j: common_vendor.t(userStatistics.value.starCount),
        k: common_vendor.p({
          type: "flag",
          color: "#404040",
          size: "28"
        }),
        l: common_vendor.p({
          type: "star",
          color: "#404040",
          size: "28"
        }),
        m: common_vendor.o(toMyFavList),
        n: common_vendor.p({
          type: "hand-up",
          color: "#404040",
          size: "28"
        }),
        o: common_vendor.p({
          type: "bars",
          color: "#404040",
          size: "28"
        }),
        p: common_vendor.p({
          type: "font",
          color: "#404040",
          size: "28"
        }),
        q: common_vendor.p({
          type: "vip",
          color: "#404040",
          size: "28"
        }),
        r: common_vendor.p({
          type: "auth",
          color: "#404040",
          size: "28"
        }),
        s: common_vendor.p({
          type: "phone",
          color: "#404040",
          size: "28"
        }),
        t: common_vendor.p({
          type: "chatboxes",
          color: "#404040",
          size: "28"
        }),
        v: common_vendor.p({
          type: "locked",
          color: "#404040",
          size: "28"
        }),
        w: common_vendor.p({
          type: "minus",
          color: "#404040",
          size: "28"
        }),
        x: common_vendor.p({
          type: "tune",
          color: "#404040",
          size: "28"
        }),
        y: "calc(100vh - " + safeTop.value + "px)"
      };
    };
  }
};
wx.createPage(_sfc_main);
//# sourceMappingURL=../../../.sourcemap/mp-weixin/pages/my/my.js.map
