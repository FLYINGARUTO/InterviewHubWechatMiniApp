"use strict";
const common_vendor = require("../../common/vendor.js");
const api_base = require("../../api/base.js");
const api_user = require("../../api/user.js");
const api_file = require("../../api/file.js");
if (!Array) {
  const _easycom_uni_notice_bar2 = common_vendor.resolveComponent("uni-notice-bar");
  const _easycom_uni_forms_item2 = common_vendor.resolveComponent("uni-forms-item");
  const _easycom_uni_easyinput2 = common_vendor.resolveComponent("uni-easyinput");
  const _easycom_uni_data_checkbox2 = common_vendor.resolveComponent("uni-data-checkbox");
  const _easycom_uni_forms2 = common_vendor.resolveComponent("uni-forms");
  (_easycom_uni_notice_bar2 + _easycom_uni_forms_item2 + _easycom_uni_easyinput2 + _easycom_uni_data_checkbox2 + _easycom_uni_forms2)();
}
const _easycom_uni_notice_bar = () => "../../uni_modules/uni-notice-bar/components/uni-notice-bar/uni-notice-bar.js";
const _easycom_uni_forms_item = () => "../../uni_modules/uni-forms/components/uni-forms-item/uni-forms-item.js";
const _easycom_uni_easyinput = () => "../../uni_modules/uni-easyinput/components/uni-easyinput/uni-easyinput.js";
const _easycom_uni_data_checkbox = () => "../../uni_modules/uni-data-checkbox/components/uni-data-checkbox/uni-data-checkbox.js";
const _easycom_uni_forms = () => "../../uni_modules/uni-forms/components/uni-forms/uni-forms.js";
if (!Math) {
  (_easycom_uni_notice_bar + _easycom_uni_forms_item + _easycom_uni_easyinput + _easycom_uni_data_checkbox + _easycom_uni_forms)();
}
const _sfc_main = {
  __name: "reg",
  setup(__props) {
    const formRef = common_vendor.ref();
    const regFormData = common_vendor.reactive({
      nickname: "",
      username: "",
      password: "",
      passwordRe: "",
      avatar: "",
      email: "",
      type: "",
      gender: "",
      verify: ""
    });
    common_vendor.onLoad(() => {
      api_base.baseApi.getParamListByBaseName("sex").then((res) => {
        common_vendor.index.__f__("log", "at pages/reg/reg.vue:80", "baseApi request:", res);
        sexs.value = res.data.data.map((item) => {
          return {
            text: item.paramName,
            value: item.paramValue
          };
        });
      });
    });
    let countDown = common_vendor.ref(0);
    const avatar = common_vendor.ref("../../static/icon/my0.png");
    const formRules = {
      nickname: {
        rules: [{
          required: true,
          errorMessage: "You cannot leave it empty."
        }, {
          minLength: 3,
          maxLength: 15,
          errorMessage: "The length must range from {minLength} to {maxLength}"
        }]
      },
      username: {
        rules: [{
          required: true,
          errorMessage: "You cannot leave it empty."
        }, {
          minLength: 3,
          maxLength: 15,
          errorMessage: "The length must range from {minLength} to {maxLength}"
        }, {
          //set aside some space for async response
          validateFunction: function(rule, value, data, callback) {
            return new Promise((resolve, reject) => {
              api_user.userApi.getCountByUsername(value).then((res) => {
                common_vendor.index.__f__("log", "at pages/reg/reg.vue:117", res);
                if (res.data.data > 0) {
                  reject(new Error("Username has been taken!"));
                }
                resolve();
              });
            });
          }
        }]
      },
      password: {
        rules: [{
          required: true,
          errorMessage: "You cannot leave it empty."
        }, {
          minLength: 6,
          maxLength: 30,
          errorMessage: "The length must range from {minLength} to {maxLength}"
        }]
      },
      passwordRe: {
        rules: [{
          required: true,
          errorMessage: "You cannot leave it empty."
        }, {
          minLength: 6,
          maxLength: 30,
          errorMessage: "The length must range from {minLength} to {maxLength}"
        }, {
          validateFunction: function(rule, value, data, callback) {
            if (value !== regFormData.password)
              callback("inputs not consistent");
            else
              callback();
          }
        }]
      },
      gender: {
        rules: [{
          required: true,
          errorMessage: "You cannot leave it empty."
        }]
      },
      email: {
        rules: [{
          required: true,
          errorMessage: "You cannot leave it empty."
        }, {
          minLength: 8,
          maxLength: 30,
          errorMessage: "The length must range from {minLength} to {maxLength}"
        }, {
          validateFunction: function(rule, value, data, callback) {
            let re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
            if (!re.test(value))
              callback("Illegal format!");
            return true;
          }
        }]
      },
      verify: {
        rules: [{
          required: true,
          errorMessage: "You cannot leave it empty."
        }, {
          minLength: 6,
          maxLength: 6,
          errorMessage: "The length must exactly be {maxLength}"
        }, {
          validateFunction: function(rule, value, data, callback) {
            return new Promise((resolve, reject) => {
              api_user.userApi.checkCodeValid(regFormData.email, value).then((res) => {
                common_vendor.index.__f__("log", "at pages/reg/reg.vue:194", res);
                if (res.data.message != null && res.data.message != "Success")
                  reject(new Error(res.data.message));
                resolve();
              });
            });
          }
        }]
      }
    };
    const submitForm = () => {
      common_vendor.index.__f__("log", "at pages/reg/reg.vue:208", "button pressed");
      common_vendor.index.__f__("log", "at pages/reg/reg.vue:209", "Validation result:", formRef.value.validate());
      formRef.value.validate().then((res) => {
        common_vendor.index.__f__("log", "at pages/reg/reg.vue:211", "All inputs comply by the form's rule.");
        regFormData.type = "1";
        api_user.userApi.register(regFormData).then((regRes) => {
          common_vendor.index.__f__("log", "at pages/reg/reg.vue:214", "registration success");
          common_vendor.index.reLaunch({
            url: "/pages/reg/reg-success"
          });
        }).catch((err) => {
          common_vendor.index.__f__("log", "at pages/reg/reg.vue:219", "fail");
        });
      });
    };
    const getCode = () => {
      api_user.userApi.getVerificationCode(regFormData.email, "reset").then(() => {
        countDown.value = 60;
        let interval = setInterval(() => {
          countDown.value--;
          if (countDown.value === 0) {
            clearInterval(interval);
            common_vendor.index.__f__("log", "at pages/reg/reg.vue:233", "Cooldown Finished");
          }
        }, 1e3);
      });
    };
    const chooseavatar = (e) => {
      api_file.fileApi.uploadFile(e.detail.avatarUrl).then((res) => {
        common_vendor.index.__f__("log", "at pages/reg/reg.vue:242", "upload response:", res.data);
        avatar.value = e.detail.avatarUrl;
        regFormData.avatar = res.data.fileName;
      }).catch((err) => {
        common_vendor.index.__f__("error", "at pages/reg/reg.vue:246", err);
      });
    };
    const sexs = common_vendor.ref([
      {
        text: "male",
        value: 1
      },
      {
        text: "female",
        value: 2
      },
      {
        text: "prefer not to say",
        value: 3
      }
    ]);
    return (_ctx, _cache) => {
      return {
        a: common_vendor.p({
          text: "Currently unavailable except for developers.",
          ["show-icon"]: "true"
        }),
        b: avatar.value,
        c: common_vendor.o(chooseavatar),
        d: common_vendor.p({
          label: "Avatar"
        }),
        e: common_vendor.o(($event) => regFormData.nickname = $event),
        f: common_vendor.p({
          type: "text",
          placeholder: "nickname",
          ["prefix-icon"]: "contact",
          modelValue: regFormData.nickname
        }),
        g: common_vendor.p({
          label: "Nickname",
          name: "nickname",
          required: true
        }),
        h: common_vendor.o(($event) => regFormData.username = $event),
        i: common_vendor.p({
          type: "text",
          placeholder: "username",
          ["prefix-icon"]: "contact",
          modelValue: regFormData.username
        }),
        j: common_vendor.p({
          label: "Username",
          name: "username",
          required: true
        }),
        k: common_vendor.o(($event) => regFormData.gender = $event),
        l: common_vendor.p({
          localdata: sexs.value,
          modelValue: regFormData.gender
        }),
        m: common_vendor.p({
          label: "Gender",
          name: "gender",
          required: true
        }),
        n: common_vendor.o(($event) => regFormData.password = $event),
        o: common_vendor.p({
          type: "password",
          placeholder: "password",
          ["prefix-icon"]: "locked",
          modelValue: regFormData.password
        }),
        p: common_vendor.p({
          label: "Password",
          name: "password",
          required: true
        }),
        q: common_vendor.o(($event) => regFormData.passwordRe = $event),
        r: common_vendor.p({
          type: "password",
          placeholder: "confirm password",
          ["prefix-icon"]: "locked",
          modelValue: regFormData.passwordRe
        }),
        s: common_vendor.p({
          label: "Confirm Password",
          name: "passwordRe",
          required: true
        }),
        t: common_vendor.o(($event) => regFormData.email = $event),
        v: common_vendor.p({
          type: "text",
          placeholder: "email",
          ["prefix-icon"]: "email",
          modelValue: regFormData.email
        }),
        w: common_vendor.p({
          label: "Email",
          name: "email",
          required: true
        }),
        x: common_vendor.o(($event) => regFormData.verify = $event),
        y: common_vendor.p({
          type: "text",
          placeholder: "verification code",
          ["prefix-icon"]: "email",
          modelValue: regFormData.verify
        }),
        z: common_vendor.t(common_vendor.unref(countDown) > 0 ? `${common_vendor.unref(countDown)}s` : "Send"),
        A: common_vendor.unref(countDown) > 0,
        B: common_vendor.o(getCode),
        C: common_vendor.p({
          label: "Verify",
          name: "verify",
          required: true
        }),
        D: common_vendor.sr(formRef, "879e3530-1", {
          "k": "formRef"
        }),
        E: common_vendor.p({
          model: regFormData,
          rules: formRules,
          ["label-width"]: 85
        }),
        F: common_vendor.o(submitForm)
      };
    };
  }
};
wx.createPage(_sfc_main);
//# sourceMappingURL=../../../.sourcemap/mp-weixin/pages/reg/reg.js.map
