<template>
	<view style="text-align: center;">
		<view style="margin-top: 100px;">
			<image style="width: 200px;" src="/Users/flyingbuta/Desktop/Xiaomi-logo.png" mode="widthFix"></image>
		</view>
		<view style="padding: 0 30px;">
			<uni-forms :rules="rules" ref="formRef" :model="formData">
				<uni-forms-item name="username">
					<uni-easyinput placeholder="请输入用户名..." v-model="formData.username" prefix-icon="contact"></uni-easyinput>
				</uni-forms-item>
				<uni-forms-item name="password"> 
					<uni-easyinput type="password" placeholder="请输入密码..." v-model="formData.password" prefix-icon="locked"></uni-easyinput>
				</uni-forms-item>
			</uni-forms>
			<button class="round-button" type="primary" @tap="login">登录</button>
			
		</view>
		<view class="other-action">
			<text>忘记密码</text>
			<text @tap="toReg">新用户注册</text>
		</view>
		
		<view class="bottom-view"> 
			<view class="icon" style="background-color:#599ee3;">
				<uni-icons  type="qq" size="32" color="white"></uni-icons>
			</view>
			<view class="icon" style="background-color: #07c160;">
				<uni-icons type="weixin" size="32" color="white"></uni-icons>
			</view>
			<view class="icon" style="background-color: #fb666c;">
				<uni-icons type="weibo" size="32" color="white"></uni-icons>
			</view>
			 
		</view>
		
	</view>
</template>

<script setup>
import { reactive,ref } from 'vue';
import storage from '../utils/storage';
import {useUserStore} from '../stores/user.js'
const formData=reactive({
	username:"",
	password:""
})
const formRef=ref()
const rules={
	username:{
		rules:[
			{required: true,errorMessage:"不能为空！"},
			{minLength:3, maxLength:30, errorMessage:"长度需在{minLength}~{maxLength}之间"			}
		]
	},
	password:{
		rules:[
			{required:true,errorMessage:"不能为空！"},
			{minLength:5,maxLength:30, errorMessage:"长度需在{minLength}~{maxLength}之间"}
		]
	},
}
const toReg=()=>{
	uni.navigateTo({
		url:"/pages/reg/reg"
	})
}
const login=()=>{
	const store=useUserStore()
	formRef.value.validate().then( res=>{
		console.log("表单规则验证通过：",res)
		
		store.login(formData.username,formData.password).then(async res=>{
			uni.showToast({
				title:"login success"
			})
			await store.getUserInfo(storage.getStorageToken()) //获取token，解析为user对象存入pinia
			uni.switchTab({
				url:"/pages/index/index"
			})
		}).catch(err=>{
			console.log("login failed:",err)
		})
		
	
	}).catch(err=>{
		console.log("错误信息：",err)
	})
}
</script>

<style lang="scss">
	.other-action{
		padding: 0 30px;
		color: cornflowerblue;
		justify-content: space-between;
		display: flex;
	}
	.bottom-view{
		box-sizing: border-box;
		position: absolute;
		bottom: 50px;
		
		display: flex;
		
		justify-content: space-around;
		
		
		
		width: 100%;
		.icon{
			width: 46px;
			height: 46px;
			border-radius: 23px;
			display: flex;
			align-items: center;
			justify-content: center;
		}
	}
</style>
