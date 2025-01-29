"use strict";
const common_vendor = require("../common/vendor.js");
const common_assets = require("../common/assets.js");
if (!Array) {
  const _easycom_uni_easyinput2 = common_vendor.resolveComponent("uni-easyinput");
  const _easycom_uni_forms_item2 = common_vendor.resolveComponent("uni-forms-item");
  const _easycom_uni_forms2 = common_vendor.resolveComponent("uni-forms");
  const _easycom_uni_icons2 = common_vendor.resolveComponent("uni-icons");
  (_easycom_uni_easyinput2 + _easycom_uni_forms_item2 + _easycom_uni_forms2 + _easycom_uni_icons2)();
}
const _easycom_uni_easyinput = () => "../uni_modules/uni-easyinput/components/uni-easyinput/uni-easyinput.js";
const _easycom_uni_forms_item = () => "../uni_modules/uni-forms/components/uni-forms-item/uni-forms-item.js";
const _easycom_uni_forms = () => "../uni_modules/uni-forms/components/uni-forms/uni-forms.js";
const _easycom_uni_icons = () => "../uni_modules/uni-icons/components/uni-icons/uni-icons.js";
if (!Math) {
  (_easycom_uni_easyinput + _easycom_uni_forms_item + _easycom_uni_forms + _easycom_uni_icons)();
}
const _sfc_main = {
  __name: "login",
  setup(__props) {
    const formData = common_vendor.reactive({
      username: "",
      password: ""
    });
    const formRef = common_vendor.ref();
    const rules = {
      username: {
        rules: [
          { required: true, errorMessage: "不能为空！" },
          { minLength: 3, maxLength: 30, errorMessage: "长度需在{minLength}~{maxLength}之间" }
        ]
      },
      password: {
        rules: [
          { required: true, errorMessage: "不能为空！" },
          { minLength: 5, maxLength: 30, errorMessage: "长度需在{minLength}~{maxLength}之间" }
        ]
      }
    };
    const toReg = () => {
      common_vendor.index.navigateTo({
        url: "/pages/reg/reg"
      });
    };
    const login = () => {
      formRef.value.validate().then((res) => {
        common_vendor.index.__f__("log", "at pages/login.vue:67", "验证通过：", res);
      }).catch((err) => {
        common_vendor.index.__f__("log", "at pages/login.vue:69", "错误信息：", err);
      });
    };
    return (_ctx, _cache) => {
      return {
        a: common_assets._imports_0,
        b: common_vendor.o(($event) => formData.username = $event),
        c: common_vendor.p({
          placeholder: "请输入用户名...",
          ["prefix-icon"]: "contact",
          modelValue: formData.username
        }),
        d: common_vendor.p({
          name: "username"
        }),
        e: common_vendor.o(($event) => formData.password = $event),
        f: common_vendor.p({
          type: "password",
          placeholder: "请输入密码...",
          ["prefix-icon"]: "locked",
          modelValue: formData.password
        }),
        g: common_vendor.p({
          name: "password"
        }),
        h: common_vendor.sr(formRef, "08fc6c10-0", {
          "k": "formRef"
        }),
        i: common_vendor.p({
          rules,
          model: formData
        }),
        j: common_vendor.o(login),
        k: common_vendor.o(toReg),
        l: common_vendor.p({
          type: "qq",
          size: "32",
          color: "white"
        }),
        m: common_vendor.p({
          type: "weixin",
          size: "32",
          color: "white"
        }),
        n: common_vendor.p({
          type: "weibo",
          size: "32",
          color: "white"
        })
      };
    };
  }
};
wx.createPage(_sfc_main);
//# sourceMappingURL=../../.sourcemap/mp-weixin/pages/login.js.map
