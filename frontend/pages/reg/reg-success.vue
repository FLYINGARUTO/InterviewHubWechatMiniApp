<template>
	<view class="class1">
		<image src="../../static/icon/success.png" style="margin-top: 50px;"></image>
		<text style="font-weight: bold;font-size: 20px;margin-bottom: 20px;">Registered Successfully!</text>
		<text style="font-size: 16px;margin-bottom: 10px;">You can now explore our sites~</text>
		<button style="width: 100%;" @tap="toLogin">Login Now</button>
		
	</view>
</template>

<script setup>
const toLogin=()=>{
	uni.reLaunch({
		url:"/pages/login"
	})
}
</script>

<style lang="scss">
.class1{
	display: flex;
	align-items: center;
	flex-direction: column;
	padding: 30px;
}
</style>
