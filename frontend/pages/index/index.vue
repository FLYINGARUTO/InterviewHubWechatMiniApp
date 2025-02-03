<script setup>
	import {useUserStore} from "../../stores/user.js"
	const useUser = useUserStore()
const back2log=()=>{

	uni.redirectTo({
		url: '/pages/login'
	})
}
</script>

<template>
	<view style="text-align: center;">
		首页
		{{useUser.user.nickname}}
		<button @click="back2log">Login Page</button>
	</view>
</template>


<style>

</style>
