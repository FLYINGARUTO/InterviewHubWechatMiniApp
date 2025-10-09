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
	},
	login(user){
		return request({
			url: `/user/login`,
			method:"POST",
			data: user
		})
	},
	logout(){
	
		return request({
			url: `/user/logout`,
			method:"POST",
		})
	},
	getUserInfo(token){
		return request({
			url: `/user/info/${token}`,

		})
	},
	updateAvatar(data){
		return request({
			url:'/user/avatar',
			method:"PUT",
			data: data
		})
	}
}