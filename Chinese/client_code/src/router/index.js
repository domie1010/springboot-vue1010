import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import tieziList from '@/views/pages/tiezi/list'
import tieziDetail from '@/views/pages/tiezi/formModel'
import tieziAdd from '@/views/pages/tiezi/formAdd'
import storeupList from '@/views/pages/storeup/list'
import jubaoxinxiList from '@/views/pages/jubaoxinxi/list'
import jubaoxinxiDetail from '@/views/pages/jubaoxinxi/formModel'
import jubaoxinxiAdd from '@/views/pages/jubaoxinxi/formAdd'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import neirongfenleiList from '@/views/pages/neirongfenlei/list'
import neirongfenleiDetail from '@/views/pages/neirongfenlei/formModel'
import neirongfenleiAdd from '@/views/pages/neirongfenlei/formAdd'
import yonghufankuiList from '@/views/pages/yonghufankui/list'
import yonghufankuiDetail from '@/views/pages/yonghufankui/formModel'
import yonghufankuiAdd from '@/views/pages/yonghufankui/formAdd'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'tieziList',
			component: tieziList
		}, {
			path: 'tieziDetail',
			component: tieziDetail
		}, {
			path: 'tieziAdd',
			component: tieziAdd
		}
		, {
			path: 'storeupList',
			component: storeupList
		}
		, {
			path: 'jubaoxinxiList',
			component: jubaoxinxiList
		}, {
			path: 'jubaoxinxiDetail',
			component: jubaoxinxiDetail
		}, {
			path: 'jubaoxinxiAdd',
			component: jubaoxinxiAdd
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'neirongfenleiList',
			component: neirongfenleiList
		}, {
			path: 'neirongfenleiDetail',
			component: neirongfenleiDetail
		}, {
			path: 'neirongfenleiAdd',
			component: neirongfenleiAdd
		}
		, {
			path: 'yonghufankuiList',
			component: yonghufankuiList
		}, {
			path: 'yonghufankuiDetail',
			component: yonghufankuiDetail
		}, {
			path: 'yonghufankuiAdd',
			component: yonghufankuiAdd
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
