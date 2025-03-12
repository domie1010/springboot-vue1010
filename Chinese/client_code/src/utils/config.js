const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '帖子',
					icon: 'icon-common1',
					child:[
						{
							name:'帖子',
							url:'/index/tieziList'
						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: "中文社区交流平台"
        } 
    }
}
export default config
