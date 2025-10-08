import request from "@/utils/request";

export default {
	publish(article) {
		return request({
			url: '/article/publish',
			method: 'POST',
			data: article

		})
	},
	list(queryParam) {
		console.log(queryParam)
		return request({
			url: '/article/list',
			method: 'POST',
			data: queryParam
		})
	},
	getArticle(id){
		return request({
			url: `/article/${id}`
		})
	},
	follow(authorId){
		return request({
			url:`/interaction/follow/${authorId}`
		})
	},
	unfollow(authorId){
		return request({
			url:`/interaction/unfollow/${authorId}`,

		})
	},
	like(params){
		return request({
			url:`/interaction/like`,
			method:"POST",
			data: params
		})
	},
	unlike(params){
		return request({
			url:`/interaction/unlike`,
			method:"DELETE",
			data: params
	
		})
	},
	star(params){
		return request({
			url:`/interaction/star`,
			method:"POST",
			data: params
		})
	},
	unstar(params){
		return request({
			url:`/interaction/unstar`,
			method:"DELETE",
			data: params
	
		})
	},
	comment(params){
		return request({
			url:"/article/comment/new",
			method:"POST",
			data:params
		})
	},
	getInteractionData(articleId,userId){
		return request({
			url:`/interaction/article/${articleId}/${userId}`
		})
	},
	getComments(params){
		return request({
			url:"/article/comment/list",
			method:"POST",
			data:params
		})
	},
	getUserStatistics(userId){
		return request({
			url:`/interaction/user-statistics/${userId}`
		})
	}
}