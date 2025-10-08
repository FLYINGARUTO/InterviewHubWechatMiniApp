<template>
	<view class="my">
		<view  :style="{height: safeTop+'px'}">
			
		</view>
		<view style="padding: 30px 20px 20px" :style="{height: 'calc(100vh - ' + (safeTop) + 'px)'}">
			<view style="display: flex;justify-content: space-between; align-items: center;">
				<view style="display: flex;">
					<button type="default" style="padding: 0; width: 56px; height: 56px; border-radius: 28px;" size="mini">
						<image :src="userStore.user.avatarUrl" mode="widthFix" style="width: 56px;" ></image>
					</button>
					<view style="display: flex; flex-direction: column; align-items: center; margin-left: 10px;">
						<text style="font-size: 22px;color: white; margin-bottom: 5px;">{{userStore.user.nickname}}</text>
						<text style="width:40px; text-align: center; background-color: white; font-size: 12px;border-radius: 10px;"
							>{{userStore.user.level}}</text>
					</view>
				</view>
				<view>
					<uni-icons type="gear" size="32" color="white"></uni-icons>
				</view>
			</view>
			<view class="statistics">
				<view class="statistics-item">
					<text style="font-weight: bold; font-size: 18px;">
						{{ userStatistics.fansCount }}
					</text>
					<text style="font-size: 14px;">
						粉丝
					</text>
				</view>
				<view class="statistics-item">
					<text style="font-weight: bold; font-size: 18px;">
						{{ userStatistics.followingCount }}
					</text>
					<text style="font-size: 14px;">
						关注
					</text>
				</view>
				<view class="statistics-item">
					<text style="font-weight: bold; font-size: 18px;">
						{{ userStatistics.likeCount }}
					</text>
					<text style="font-size: 14px;">
						获赞
					</text>
				</view>
				<view class="statistics-item">
					<text style="font-weight: bold;font-size: 18px;">
						{{ userStatistics.starCount }}
					</text>
					<text style="font-size: 14px;">
						收藏
					</text>
				</view>
			</view>
			<view class="opr-list">
				<view class="item">
					<uni-icons type="flag" color="#404040" size="28"></uni-icons>
					<text>浏览历史</text>
				</view>
				<view class="item">
					<uni-icons type="star" color="#404040" size="28"></uni-icons>
					<text>收藏夹</text>
				</view>
				<view class="item">
					<uni-icons type="hand-up" color="#404040" size="28"></uni-icons>
					<text>赞过</text>
				</view>
				<view class="item">
					<uni-icons type="bars" color="#404040" size="28"></uni-icons>
					<text>我的分享</text>
				</view>
				
			</view>
			
			<view class="opr-list">
				<view class="item">
					<uni-icons type="font" color="#404040" size="28"></uni-icons>
					<text>字体设置</text>
				</view>
				<view class="item">
					<uni-icons type="vip" color="#404040" size="28"></uni-icons>
					<text>升级会员</text>
				</view>
				<view class="item">
					<uni-icons type="auth" color="#404040" size="28"></uni-icons>
					<text>安全中心</text>
				</view>
				<view class="item">
					<uni-icons type="phone" color="#404040" size="28"></uni-icons>
					<text>联系我们</text>
				</view>
				<view class="item">
					<uni-icons type="chatboxes" color="#404040" size="28"></uni-icons>
					<text>交流群</text>
				</view>
				<view class="item">
					<uni-icons type="locked" color="#404040" size="28"></uni-icons>
					<text>修改密码</text>
				</view>
				<view class="item">
					<uni-icons type="minus" color="#404040" size="28"></uni-icons>
					<text>退出登陆</text>
				</view>
				<view class="item">
					<uni-icons type="tune" color="#404040" size="28"></uni-icons>
					<text>更多设置</text>
				</view>
				
			</view>
			
		</view>
	</view>
</template>

<script setup>
import { ref } from 'vue'
import { onLoad } from '@dcloudio/uni-app'
import { useUserStore } from '../../stores/user'
import { storeToRefs } from 'pinia'
import  articleApi  from '../../api/article.js'
const userStore = useUserStore()

const safeTop=ref(0)
const userStatistics=ref({})
onLoad(async()=>{
	let {safeArea} = uni.getSystemInfoSync()
	safeTop.value=safeArea.top;
	
	let res1= await articleApi.getUserStatistics(userStore.user.id)
	userStatistics.value=res1.data.data
	console.log("互动数据: ",res1.data.data)
})
</script>

<style lang="scss">
.my{
	background: linear-gradient(to top, #fafafa 45% , #d2302c 80% , #d2302c 100% );
	.statistics{

		color: white;
		padding: 20px 20px 0;
		display: flex;
		justify-content: space-between;
		margin-bottom: 30px;
		.statistics-item{
			display: flex;
			flex-direction: column;
			align-items: center;
		}
		// margin-top: 50px;
		// background-color: white;
		// color: #5d5d5d;
		// padding: 20px 20px 0;
		// border-radius: 20px;
		// display: flex;
		// justify-content: space-between;
	}
	.opr-list{
		padding: 20px 20px 0;
		display: flex;
		justify-content: space-between;
		background-color: white;
		color: #5d5d5d;
		border-radius: 20px;
		flex-wrap: wrap;
		margin-bottom: 20px;
		.item{
			width: 23%;
			display: flex;
			flex-direction: column;
			align-items: center;
			padding-bottom: 20px;
			text{
				font-size: 14px;
				color: #404040;
			}
		}
	}
	
}
</style>
