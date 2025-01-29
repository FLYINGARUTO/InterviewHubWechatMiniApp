import config from "../config/config";

export default{
	uploadFile(tempfile){
		return new Promise((resolve,reject)=>{
			uni.uploadFile({
				url:`${config.apiBaseUrl}/file/upload`,
				filePath:tempfile,
				name:'file',
				formData:{},
				success:(uploadFileRes)=>{
					console.log(uploadFileRes.data)
					resolve(JSON.parse(uploadFileRes.data))
				},
				fail(err) {
					console.log("file upload failed",err)
				}
			})
			
		})
	}
}