uni.addInterceptor('request',{
	invoke(args) {
		console.log('------invoke----args---> ',args)
	}
})