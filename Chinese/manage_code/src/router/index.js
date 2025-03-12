	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import discusstiezi from '@/views/discusstiezi/list'
	import yonghu from '@/views/yonghu/list'
	import neirongfenlei from '@/views/neirongfenlei/list'
	import tiezi from '@/views/tiezi/list'
	import jubaoxinxi from '@/views/jubaoxinxi/list'
	import yonghufankui from '@/views/yonghufankui/list'
	import storeup from '@/views/storeup/list'
	import config from '@/views/config/list'
	import users from '@/views/users/list'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/discusstiezi',
			name: '帖子评论',
			component: discusstiezi
		}
		,{
			path: '/yonghu',
			name: '用户',
			component: yonghu
		}
		,{
			path: '/neirongfenlei',
			name: '内容分类',
			component: neirongfenlei
		}
		,{
			path: '/tiezi',
			name: '帖子',
			component: tiezi
		}
		,{
			path: '/jubaoxinxi',
			name: '举报信息',
			component: jubaoxinxi
		}
		,{
			path: '/yonghufankui',
			name: '用户反馈',
			component: yonghufankui
		}
		,{
			path: '/storeup',
			name: '我的收藏',
			component: storeup
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		,{
			path: '/users',
			name: '管理员',
			component: users
		}
		]
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
