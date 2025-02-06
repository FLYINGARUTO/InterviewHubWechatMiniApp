import storage from "./storage"
uni.addInterceptor('request',{
	//invoke是在触发前执行
	invoke(args) {
		args.header = {
			'Authorization' : storage.getStorageToken()
		}
	}
})