"use strict";
const common_vendor = require("../../common/vendor.js");
const common_assets = require("../../common/assets.js");
const stores_user = require("../../stores/user.js");
const api_base = require("../../api/base.js");
const api_article = require("../../api/article.js");
if (!Array) {
  const _easycom_uni_search_bar2 = common_vendor.resolveComponent("uni-search-bar");
  const _easycom_m_card2 = common_vendor.resolveComponent("m-card");
  const _easycom_uni_load_more2 = common_vendor.resolveComponent("uni-load-more");
  (_easycom_uni_search_bar2 + _easycom_m_card2 + _easycom_uni_load_more2)();
}
const _easycom_uni_search_bar = () => "../../uni_modules/uni-search-bar/components/uni-search-bar/uni-search-bar.js";
const _easycom_m_card = () => "../../components/global/m-card2.js";
const _easycom_uni_load_more = () => "../../uni_modules/uni-load-more/components/uni-load-more/uni-load-more.js";
if (!Math) {
  (_easycom_uni_search_bar + _easycom_m_card + _easycom_uni_load_more)();
}
const _sfc_main = {
  __name: "index",
  setup(__props) {
    stores_user.useUserStore();
    const back2log = () => {
      common_vendor.index.redirectTo({
        url: "/pages/login"
      });
    };
    const safeTop = common_vendor.ref(0);
    const triggered = common_vendor.ref(false);
    const loadMoreStatus = common_vendor.ref("more");
    const loadMore = async () => {
      if (loadMoreStatus.value == "noMore") {
        return false;
      }
      common_vendor.index.__f__("log", "at pages/index/index.vue:28", "------>上拉加载更多");
      loadMoreStatus.value = "loading";
      queryParam.value.pageNo++;
      let res = await api_article.articleApi.list(queryParam.value);
      setTimeout(() => {
        articleList.value = articleList.value.concat(res.data.data.rows);
        if (articleList.value.length < res.data.data.total) {
          loadMoreStatus.value = "more";
        } else {
          loadMoreStatus.value = "noMore";
        }
      }, 1e3);
    };
    async function refreshFeed() {
      common_vendor.index.__f__("log", "at pages/index/index.vue:43", "---->下拉刷新");
      triggered.value = true;
      await search();
      triggered.value = false;
    }
    const categoryList = common_vendor.ref([
      { id: 0, paramName: "全部", paramValue: "" }
      //categoryId为空
    ]);
    const categoryIndex = common_vendor.ref(0);
    const valueChange = (e, categoryId) => {
      categoryIndex.value = e;
      queryParam.value.categoryId = categoryId;
      search();
    };
    const queryParam = common_vendor.ref({
      pageNo: 1,
      pageSize: 5,
      title: ""
    });
    common_vendor.ref({});
    const articleList = common_vendor.ref([]);
    common_vendor.onLoad(async () => {
      let { safeArea } = common_vendor.index.getSystemInfoSync();
      safeTop.value = safeArea.top + 10;
      let res = await api_base.baseApi.getParamListByBaseName("articleType");
      categoryList.value = categoryList.value.concat(res.data.data);
      await search();
    });
    const search = async () => {
      queryParam.value.pageNo = 1;
      common_vendor.index.__f__("log", "at pages/index/index.vue:86", "query:", queryParam.value);
      let res = await api_article.articleApi.list(queryParam.value);
      common_vendor.index.__f__("log", "at pages/index/index.vue:88", res.data.data);
      articleList.value = res.data.data.rows;
    };
    const clearSearch = () => {
      queryParam.value.title = "";
      search();
    };
    const viewDetail = (id) => {
      common_vendor.index.navigateTo({
        url: "/pages/articles/article?id=" + id
      });
    };
    return (_ctx, _cache) => {
      return common_vendor.e({
        a: common_vendor.o(search),
        b: common_vendor.o(clearSearch),
        c: common_vendor.o(($event) => queryParam.value.title = $event),
        d: common_vendor.p({
          placeholder: "搜索...",
          radius: "100",
          clearButton: "auto",
          cancelButton: "none",
          modelValue: queryParam.value.title
        }),
        e: common_vendor.f(categoryList.value, (item, index, i0) => {
          return common_vendor.e({
            a: common_vendor.t(item.paramName),
            b: index == categoryIndex.value
          }, index == categoryIndex.value ? {} : {}, {
            c: index == categoryIndex.value ? 1 : "",
            d: common_vendor.o(($event) => valueChange(index, item.paramValue), item.id),
            e: item.id
          });
        }),
        f: safeTop.value + "px",
        g: common_vendor.f(articleList.value, (item, value, i0) => {
          return {
            a: common_vendor.o(($event) => viewDetail(item.id), item.id),
            b: item.id,
            c: "b5677eb8-1-" + i0,
            d: common_vendor.p({
              localdata: item
            })
          };
        }),
        h: articleList.value.length == 0
      }, articleList.value.length == 0 ? {
        i: common_assets._imports_0$2
      } : {
        j: common_vendor.p({
          status: loadMoreStatus.value
        })
      }, {
        k: "calc(100vh - " + (safeTop.value + 110) + "px)",
        l: triggered.value,
        m: common_vendor.o(refreshFeed),
        n: common_vendor.o(loadMore),
        o: common_vendor.o(back2log)
      });
    };
  }
};
wx.createPage(_sfc_main);
//# sourceMappingURL=../../../.sourcemap/mp-weixin/pages/index/index.js.map
