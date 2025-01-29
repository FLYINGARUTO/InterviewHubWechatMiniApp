<template>
	<uni-notice-bar scrollable="false" text="currently in test mode" show-icon="true"></uni-notice-bar>
	<view class="outer">

		<view style="margin: 10px 10px;">
			<uni-forms :model="userForm" ref="formRef" label-width="82px" :rules="formRules">
				<uni-forms-item name="username" label="用户名" required>
					<uni-easyinput v-model="userForm.username"></uni-easyinput>
				</uni-forms-item>
				<uni-forms-item name="password" label="密码" required>
					<uni-easyinput type="password" v-model="userForm.password"></uni-easyinput>
				</uni-forms-item>
				<uni-forms-item name="password2" label="确认密码" required>
					<uni-easyinput type="password" v-model="userForm.password2"></uni-easyinput>
				</uni-forms-item>
				<uni-forms-item name="sex" label="性别" required>
					<uni-data-checkbox v-model="userForm.sex" :localdata="sexs"></uni-data-checkbox>
				</uni-forms-item>
				<uni-forms-item name="phone" label="电话" required>
					<uni-easyinput v-model="userForm.phone"></uni-easyinput>
				</uni-forms-item>
				<uni-forms-item name="captcha" label="验证码" required>
					<view style="display: flex; align-content: space-between;">
						<view style="width: 50%;">
							<uni-easyinput v-model="userForm.captcha"></uni-easyinput>
						</view>
						<view style="width: 48%;">
							<button type="default" size="mini"
								style="width: 99%;height: 35px;line-height: 35px">获取验证码</button>
						</view>
					</view>
				</uni-forms-item>

			</uni-forms>
			<button type="primary"  @tap="submitForm1">注册</button>
		</view>
	</view>
</template>

<script setup>
	import {
		reactive,
		ref
	} from 'vue';
	const userForm = reactive({
		username: "",
		password: "",
		password2: "",
		sex: "",
		phone: "",
		captcha: "",
	})
	const formRef = ref()
	const sexs = [{
		text: "男",
		value: 1
	}, {
		text: "女",
		value: 2
	}]
	
	const submitForm1=()=>{
		formRef.value.validate().then(res=>{
			console.log("1")
		}).catch(err=>{
			console.log("2")
		})
	}
	const formRules = {

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
						if (value == "admin") {
							reject(new Error("Illegal username!"))
						}
						else{
							resolve()
						}
					})
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
		password2: {
			rules: [{
				required: true,
				errorMessage: "You cannot leave it empty."
			}, {
				minLength: 6,
				maxLength: 30,
				errorMessage: "The length must range from {minLength} to {maxLength}"
			}, {
				validateFunction: function(rule, value, data, callback) {

					if (value !== userForm.password)
						callback("inputs not consistent")
					else
						callback()
				}
			}]
		},
		sex: {
			rules: [{
				required: true,
				errorMessage: "You cannot leave it empty."
			}, ]
		},
		phone: {
			rules: [{
				required: true,
				errorMessage: "You cannot leave it empty."
			}, {
				minLength: 6,
				maxLength: 14,
				errorMessage: "The length must range from {minLength} to {maxLength}"
			}, ]
		},
		captcha: {
			rules: [{
				required: true,
				errorMessage: "You cannot leave it empty."
			}, {
				minLength: 6,
				maxLength: 6,
				errorMessage: "The length must exactly be {maxLength}"
			}]
		}
	}
</script>

<style>
	.outer {
		padding: 10px;
	}
</style>