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
			url:`/article/follow/${authorId}`
		})
	},
	unfollow(authorId){
		return request({
			url:`/article/unfollow/${authorId}`,

		})
	}
}