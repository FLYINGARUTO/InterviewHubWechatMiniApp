import request from "@/utils/request";

export default{
	getCountByUsername(username){
				return request({
					url: `/user/count/${username}`
				})
	},
	getVerificationCode(email,type){
		return request({
			url: `/user/verification/${email}/${type}`
		})
	},
	checkCodeValid(email,code){
		return request({
			url:`/user/checkCode/${code}/${email}`
		})
	},
	register(user){
		return request({
			url: `/user/register`,
			method:"POST",
			data: user
		})
	}
}