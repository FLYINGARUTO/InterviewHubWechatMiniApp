const tokenKey = "Authorization";

export default {
	setStorageToken(token) {
		uni.setStorageSync(tokenKey, "Bearer "+token)
	},

	removeStorageToken() {
		uni.removeStorageSync(tokenKey)
	},
	
	getStorageToken(){
		return uni.getStorageSync(tokenKey)
	}
}