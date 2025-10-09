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
				token.value = res.data.data
				await getUserInfo(res.data.data)
				

				storage.setStorageToken(res.data.data)
				
				resolve(res.data)
			}).catch(err => reject(err))
		})



	}

	function getUserInfo(token) {
		return new Promise((resolve, reject) => {
			userApi.getUserInfo(token).then(res => {
				user.value = res.data.data
				resolve(res.data.data)
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
	
	function refresh(newUser,newToken){
		user.value=newUser
		token.value=newToken
		storage.setStorageToken(newToken)
	}

	return {
		user,
		token,
		login,
		logout,
		getUserInfo,
		refresh
	}
})