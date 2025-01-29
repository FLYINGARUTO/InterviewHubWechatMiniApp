import request from "@/utils/request";

export default{
	getParamListByBaseName(baseName){
				return request({
					url: `/base/${baseName}`
				})
	}
}