import request from "@/utils/request";

export default{
	publish(article){
				return request({
					url: '/article/publish',
					method: 'POST',
					data: article
					
				})
	}
}