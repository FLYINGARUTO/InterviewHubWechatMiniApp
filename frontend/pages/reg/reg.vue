<template>
	<uni-notice-bar text="Currently unavailable except for developers." show-icon="true"></uni-notice-bar>
	<view style="padding: 20px; ">

		<uni-forms :model="regFormData" ref="formRef" :rules="formRules" :label-width="85">
			<uni-forms-item label="Avatar">
				<button style="width: 56px;height: 56px;padding: 0;border-radius: 28px" open-type="chooseAvatar"
					@chooseavatar="chooseavatar" size="mini">
					<image :src="avatar" style="width: 56px;height: 56px" />
				</button>
			</uni-forms-item>
			<uni-forms-item label="Nickname" name="nickname" required>
				<uni-easyinput v-model="regFormData.nickname" type="text" placeholder="nickname"
					prefix-icon="contact"></uni-easyinput>
			</uni-forms-item>
			<uni-forms-item label="Username" name="username" required>
				<uni-easyinput v-model="regFormData.username" type="text" placeholder="username"
					prefix-icon="contact"></uni-easyinput>
			</uni-forms-item>
			<uni-forms-item label="Gender" name="gender" required>
				<uni-data-checkbox v-model="regFormData.gender" :localdata="sexs"></uni-data-checkbox>
			</uni-forms-item>
			<uni-forms-item label="Password" name="password" required>
				<uni-easyinput v-model="regFormData.password" type="password" placeholder="password"
					prefix-icon="locked"></uni-easyinput>
			</uni-forms-item>
			<uni-forms-item label="Confirm Password" name="passwordRe" required>
				<uni-easyinput v-model="regFormData.passwordRe" type="password" placeholder="confirm password"
					prefix-icon="locked"></uni-easyinput>
			</uni-forms-item>

			<uni-forms-item label="Email" name="email" required>
				<uni-easyinput v-model="regFormData.email" type="text" placeholder="email"
					prefix-icon="email"></uni-easyinput>
			</uni-forms-item>
			<uni-forms-item label="Verify" name="verify" required>
				<view style="justify-content: space-between;display: flex;">
					<view style="width: 50%;">
						<uni-easyinput v-model="regFormData.verify" type="text" placeholder="verification code"
							prefix-icon="email"></uni-easyinput>
					</view>
					<view style="width: 48%;">
						<button size="mini" style="width: 98%;" :disabled="countDown>0" @tap="getCode">
							{{ countDown>0 ? `${countDown}s` : 'Send' }}
						</button>
					</view>
				</view>
			</uni-forms-item>
		</uni-forms>

	</view>
	<button style="border-radius: 5px;width: 100%;" type="primary" @tap="submitForm" size="mini">Register</button>
</template>
<script setup>
	import {
		onLoad
	} from "@dcloudio/uni-app"
	import {
		reactive,
		ref
	} from 'vue';
	import baseApi from "@/api/base.js"
	import userApi from "@/api/user.js"
	import fileApi from "@/api/file.js"
	import user from "@/api/user.js";
	const formRef = ref()
	const regFormData = reactive({
		nickname: "",
		username: "",
		password: "",
		passwordRe: "",
		avatar: "",
		email: "",
		type: "",
		gender: "",
		verify: ""
	})
	onLoad(() => {
		baseApi.getParamListByBaseName("sex").then(res => {
			console.log("baseApi request:", res);
			sexs.value = res.data.data.map(item => {
				return {
					text: item.paramName,
					value: item.paramValue
				}

			})
		})

	})
	let countDown = ref(0)
	const avatar = ref("../../static/icon/my0.png")
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
						userApi.getCountByUsername(value).then(res => {
							console.log(res)
							if (res.data.data > 0) {
								reject(new Error("Username has been taken!"))
							}
							resolve()
						})




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
						callback("inputs not consistent")
					else
						callback()
				}
			}]
		},
		gender: {
			rules: [{
				required: true,
				errorMessage: "You cannot leave it empty."
			}, ]
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
					let re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
					if (!re.test(value))
						callback("Illegal format!")
					return true
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
						userApi.checkCodeValid(regFormData.email, value).then(res => {
							console.log(res)
							if (res.data.message != null && res.data.message != "Success")
								reject(new Error(res.data.message))
							resolve()
						})

					})
				}
			}]
		}
	}


	const submitForm = () => {
		console.log("button pressed")
		console.log("Validation result:", formRef.value.validate());
		formRef.value.validate().then(res => {
			console.log("All inputs comply by the form's rule.")
			regFormData.type='1' //normal registration
			userApi.register(regFormData).then(regRes => {
				console.log("registration success")
				uni.reLaunch({
					url: "/pages/reg/reg-success"
				})
			}).catch(err => {
				console.log("fail")
			})



		})
	}
	const getCode = () => {
		userApi.getVerificationCode(regFormData.email, "reset").then(() => {
			countDown.value = 60
			let interval = setInterval(() => {
				countDown.value--
				if (countDown.value === 0) {
					clearInterval(interval)
					console.log("Cooldown Finished")
				}
			}, 1000)
		})

	}
	const chooseavatar = (e) => {

		fileApi.uploadFile(e.detail.avatarUrl).then(res => {
			console.log("upload response:", res.data)
			avatar.value = e.detail.avatarUrl
			regFormData.avatar = res.data.fileName
		}).catch(err => {
			console.error(err)
		})



	}
	const sexs = ref([{
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
	])
</script>
<style>

</style>