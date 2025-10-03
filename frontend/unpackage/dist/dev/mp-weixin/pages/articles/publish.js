"use strict";
const common_vendor = require("../../common/vendor.js");
const api_base = require("../../api/base.js");
const api_article = require("../../api/article.js");
if (!Array) {
  const _easycom_uni_easyinput2 = common_vendor.resolveComponent("uni-easyinput");
  const _easycom_uni_forms_item2 = common_vendor.resolveComponent("uni-forms-item");
  const _easycom_uni_data_select2 = common_vendor.resolveComponent("uni-data-select");
  const _easycom_uni_forms2 = common_vendor.resolveComponent("uni-forms");
  (_easycom_uni_easyinput2 + _easycom_uni_forms_item2 + _easycom_uni_data_select2 + _easycom_uni_forms2)();
}
const _easycom_uni_easyinput = () => "../../uni_modules/uni-easyinput/components/uni-easyinput/uni-easyinput.js";
const _easycom_uni_forms_item = () => "../../uni_modules/uni-forms/components/uni-forms-item/uni-forms-item.js";
const _easycom_uni_data_select = () => "../../uni_modules/uni-data-select/components/uni-data-select/uni-data-select.js";
const _easycom_uni_forms = () => "../../uni_modules/uni-forms/components/uni-forms/uni-forms.js";
if (!Math) {
  (_easycom_uni_easyinput + _easycom_uni_forms_item + _easycom_uni_data_select + _easycom_uni_forms)();
}
const _sfc_main = {
  __name: "publish",
  setup(__props) {
    const pubFormRef = common_vendor.ref();
    const pubForm = common_vendor.ref({});
    const pubFormRule = common_vendor.ref({
      title: {
        rules: [{
          required: true,
          errorMessage: "please type the title "
        }, {
          minLength: 5,
          maxLength: 50,
          errorMessage: "length of title must range from {minLength} to {maxLength}"
        }]
      },
      categoryId: {
        rules: [{
          required: true,
          errorMessage: "please select the category "
        }]
      },
      details: {
        rules: [{
          required: true,
          errorMessage: "description cannot left empty "
        }]
      }
    });
    const category = common_vendor.ref([]);
    common_vendor.onLoad(async () => {
      let res = await api_base.baseApi.getParamListByBaseName("articleType");
      category.value = res.data.data.map((item) => {
        return {
          value: item.paramValue,
          text: item.paramName
        };
      });
    });
    const publish = () => {
      pubFormRef.value.validate().then(() => {
        common_vendor.index.__f__("log", "at pages/articles/publish.vue:68", "form is valid : ", pubForm.value);
        api_article.articleApi.publish(pubForm.value).then((res) => {
          common_vendor.index.__f__("log", "at pages/articles/publish.vue:70", "published: ", res.data);
          pubForm.value = {};
          pubFormRef.value.clearValidate();
          clearDetail();
          common_vendor.index.redirectTo({
            url: "/pages/articles/publish-success"
          });
        });
      }).catch(() => {
        common_vendor.index.__f__("log", "at pages/articles/publish.vue:83", "form is invalid : ", pubForm.value);
      });
    };
    const detailInput = (e) => {
      pubForm.value.details = e.detail.html;
    };
    const clearDetail = () => {
      common_vendor.index.createSelectorQuery().select("#myEditor").context((res) => {
        res.context.setContents({ html: "" });
      }).exec();
    };
    return (_ctx, _cache) => {
      return {
        a: common_vendor.o(($event) => pubForm.value.title = $event),
        b: common_vendor.p({
          placeholder: "title",
          modelValue: pubForm.value.title
        }),
        c: common_vendor.p({
          name: "title",
          required: ""
        }),
        d: common_vendor.o(($event) => pubForm.value.categoryId = $event),
        e: common_vendor.p({
          localdata: category.value,
          modelValue: pubForm.value.categoryId
        }),
        f: common_vendor.p({
          name: "categoryId",
          required: ""
        }),
        g: common_vendor.o(detailInput),
        h: common_vendor.p({
          name: "details",
          required: ""
        }),
        i: common_vendor.o(publish),
        j: common_vendor.sr(pubFormRef, "4a34467e-0", {
          "k": "pubFormRef"
        }),
        k: common_vendor.p({
          rules: pubFormRule.value,
          model: pubForm.value
        })
      };
    };
  }
};
wx.createPage(_sfc_main);
//# sourceMappingURL=../../../.sourcemap/mp-weixin/pages/articles/publish.js.map
