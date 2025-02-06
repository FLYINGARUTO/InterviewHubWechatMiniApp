<script>
	import {
		useUserStore
	} from './stores/user';
	import storage from './utils/storage';
	export default {
		onLaunch: async function() {
			// when there is valid token in the storage
			// auto login
			let token = storage.getStorageToken()
			if (token) {
				let useUser = useUserStore()
				//store the user object 
				await useUser.getUserInfo(token)
				console.log("You are logged in automatically~")
				uni.showToast({
					title: "You are logged in automatically~"
				})
				setTimeout(() => {
					uni.switchTab({
						url: "/pages/index/index"
					}), 2000
				})

			}

		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	@import '@/uni_modules/uni-scss/index.scss';
	/* #ifndef APP-NVUE */
	@import '@/static/customicons.css';

	// // 设置整个项目的背景色
	// page {
	// 	background-color: #f5f5f5;
	// }

	/* #endif */
	.example-info {
		font-size: 14px;
		color: #333;
		padding: 10px;
	}

	.round-button {
		border-radius: 23px;
	}
</style>