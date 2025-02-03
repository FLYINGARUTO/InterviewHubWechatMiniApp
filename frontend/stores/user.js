import {
	defineStore
} from 'pinia'
import {
	ref
} from "vue"
import userApi from "../api/user.js"
import storage from "../utils/storage.js"
export const useUserStore = defineStore("user", () => {
	const user = ref({})
	const token = ref("")

	function login(username, password) {
		return new Promise((resolve, reject) => {
			userApi.login({
				username,
				password
			}).then(async res => {
				//console.log("userApi.login response: ", res)
				token.value = res.data.data
				//// ✅ 先等待 getUserInfo 解析完 Promise，再赋值给 user.value
				const userInfo = await getUserInfo(res.data.data)
				user.value=userInfo.data
				//console.log("after login, user:",user.value)
				//console.log(user.value.username)
				storage.setStorageToken(res.data.data)
				
				resolve(res.data)
			}).catch(err => reject(err))
		})



	}

	function getUserInfo(token) {
		return new Promise((resolve, reject) => {
			userApi.getUserInfo(token).then(res => {
				user.value = res.data
				resolve(res.data)
			}).catch(err => reject(err))
		})
	}

	function logout() {
		return new Promise((resolve, reject) => {
			userApi.logout().then(res => {
				//empty all data
				user.value = {}
				token.value = ""
				storage.removeStorageToken()
				resolve(res.data)
			}).catch(err => {
				reject(err)
			})
		})
	}


	return {
		user,
		token,
		login,
		logout,
		getUserInfo
	}
})