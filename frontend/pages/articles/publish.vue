<template>
	<view style="padding: 20px;">
		<uni-forms ref="pubFormRef" :rules="pubFormRule" :model="pubForm">
			<uni-forms-item name="title" required="">
				<uni-easyinput placeholder="title" v-model="pubForm.title"></uni-easyinput>
			</uni-forms-item>
			<uni-forms-item name="categoryId" required="">
				<uni-data-select v-model="pubForm.categoryId" :localdata="category" ></uni-data-select>
			</uni-forms-item>
			<uni-forms-item name="details" required="">
				<editor placeholder="share your stories..." class="detailsEditor" @input="detailInput" id="myEditor" ref="editorRef"></editor>
			</uni-forms-item>
			<button type="primary" @click="publish">publish</button>
		</uni-forms>
	</view>
</template>

<script setup>
	import {
		onLoad
	} from "@dcloudio/uni-app"
	import {
		ref
	} from 'vue'
	import baseApi from '@/api/base.js'
	const pubFormRef = ref()
	const pubForm = ref({})
	const pubFormRule = ref({
		title: {
			rules: [{
				required : true,
				errorMessage: 'please type the title '
			},{
				minLength: 5,
				maxLength: 50,
				errorMessage: 'length of title must range from {minLength} to {maxLength}'
			}]
		},
		categoryId: {
			rules: [{
				required : true,
				errorMessage: 'please select the category '
			}]
		},
		details: {
			rules: [{
				required : true,
				errorMessage: 'description cannot left empty '
			}]
		},
	})
	const category = ref([])
	onLoad(() => {
		baseApi.getParamListByBaseName('articleType').then(res => {
			//console.log('-----articleType-----:', res.data.data)
			category.value = res.data.data.map(item => {
				return {
					value: item.paramValue,
					text: item.paramName
				}
			})

		})


	})
	import articleApi from '@/api/article.js'
	const publish = () =>{
		pubFormRef.value.validate().then(() => {
			console.log("form is valid : ",pubForm.value)
			articleApi.publish(pubForm.value).then(res=>{
				console.log('published: ',res.data)
				pubForm.value={}
				pubFormRef.value.clearValidate()//clear the first two inputs
				clearDetail()//clear the editor
				uni.showToast({
					title:"post success"
				})
			})

			}
		).catch(()=>{
			console.log("form is invalid : ",pubForm.value)
		})
	}
	//editor的listener
	const detailInput = (e)=>{
		pubForm.value.details= e.detail.html
	}
	//clear the editor
	const clearDetail=()=>{
		uni.createSelectorQuery().select("#myEditor").context(res=>{
			res.context.setContents({ html: '' })
		}).exec()
	}
</script>

<style>
	.detailsEditor {
		border-radius: 5px;
		padding: 10px;
		border: 1px solid #e5e5e5;
		height: 100vh-35;
	}
</style>