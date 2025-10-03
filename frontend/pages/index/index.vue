<script setup>
	import {useUserStore} from "../../stores/user.js"
	import {onLoad} from "@dcloudio/uni-app"
	import {ref} from "vue"
	import baseApi from "@/api/base.js"
	import articleApi from "@/api/article.js"
	//初始化useUserStore() 里面持久化存储user和token
	const useUser = useUserStore()

	//跳转回登录页
	const back2log = () => {

		uni.redirectTo({
			url: '/pages/login'
		})
	}
	//安全边距（top
	const safeTop = ref(0)
	//下拉刷新trigger
	const triggered = ref(false)
	//上拉刷新status
	const loadMoreStatus = ref("more")
	const loadMore =async ()=>{
		
		if(loadMoreStatus.value=="noMore"){
			return false
		}
		console.log("------>上拉加载更多")
		loadMoreStatus.value = "loading"
		queryParam.value.pageNo++;
		let res = await articleApi.list(queryParam.value)
		setTimeout(()=>{
			articleList.value = articleList.value.concat(res.data.data.rows)
			if(articleList.value.length < res.data.data.total){
				loadMoreStatus.value = "more"
			}else{ 
				loadMoreStatus.value = "noMore"
			}
		},1000)
		
	}
	async function refreshFeed(){
		console.log("---->下拉刷新")
		triggered.value=true
		
		
		await search()
		
		
		triggered.value=false
	}
	//categoryTab
	const categoryList=ref([
		{id:0,paramName:"全部",paramValue:""} //categoryId为空
	])
	const categoryIndex=ref(0)
	//分类tab事件
	const valueChange=(e,categoryId)=>{
		categoryIndex.value=e //这个是为了控制样式的
		queryParam.value.categoryId=categoryId
		search()
		
	}
	const queryParam = ref({
		pageNo: 1,
		pageSize: 5,
		title : ""
	})
	const articleRes=ref({})
	const articleList=ref([])
	
	onLoad(async () => {
		//获取系统的信息
		//获取系统安全显示区域
		let {safeArea} = uni.getSystemInfoSync()
		safeTop.value = safeArea.top + 10
		
		let res=await baseApi.getParamListByBaseName("articleType")
		categoryList.value=categoryList.value.concat(res.data.data)
		
		await search()
		
	})
	const search=async ()=>{
		queryParam.value.pageNo=1
		console.log("query:",queryParam.value)
		let res=await articleApi.list(queryParam.value)
		console.log(res.data.data)
		
		articleList.value=res.data.data.rows

	}
	const clearSearch=()=>{
		queryParam.value.title=""
		search()
	}
	

	const viewDetail=(id)=>{
		uni.navigateTo({
			url:'/pages/articles/article?id='+id
		})
	}
</script>

<template>
	<view>
		<view class="top" :style="{paddingTop: safeTop+'px'}">
			<view class="title">
				程序员面试宝典
			</view>
			
			<uni-search-bar v-model="queryParam.title" placeholder="搜索..." radius="100"
				clearButton="auto" cancelButton="none" @confirm="search" @clear="clearSearch">
			</uni-search-bar>
			<view class="categoryTab">
				<view class="item" :class="{active: index==categoryIndex}"
				v-for="(item,index) in categoryList" @click="valueChange(index,item.paramValue)" :key="item.id">
					<text>{{item.paramName}}</text>
					<text v-if="index==categoryIndex" style="line-height: 8px;">-</text>
				</view>
			</view>
			
		</view> 
		<scroll-view scroll-y="true" show-scrollbar="true" :style="{height: 'calc(100vh - '+(safeTop + 110)+'px)'}" 
			refresher-enabled="true" :refresher-threshold="50" :refresher-triggered="triggered" @refresherrefresh="refreshFeed"
			enable-back-to-top="true" @scrolltolower="loadMore"> 
			<m-card @tap="viewDetail(item.id)" v-for="(item,value) in articleList" :localdata="item" :key="item.id"></m-card>
			<view class="no-data" v-if="articleList.length==0">
				<image src="/static/icon/fail.png" mode="widthFix"></image>
				没有匹配的结果
			</view>
			<view style="height: 200px;" v-else>
				<uni-load-more :status="loadMoreStatus"></uni-load-more>
			</view>
		</scroll-view>
			

		<button @click="back2log">Login</button>

	</view>
</template>


<style lang="scss">
	.top {
		height: 100px;
		background-color: #d2302c;
		padding: 20px 0 10px;
		.title{
			color: white;
			font-weight: bold;
			padding-left: 15px;
		}
		.categoryTab{
			display: flex;
			padding-left: 15px;
			color: lightgray;
			.item{
				text-align: center;
				font-size: 12px;
				margin-right: 10px;
				display: flex;
				flex-direction: column;
			}
			.active{
				font-weight: bold;
				color: white;
			}
		}

	}
	.no-data{
		display: flex;
		flex-direction: column;
		align-items: center;
		font-size: 14px;
		color: gray;
		image{
			width: 60px;
		}
		margin: 100px 0 50px;
	}

</style>