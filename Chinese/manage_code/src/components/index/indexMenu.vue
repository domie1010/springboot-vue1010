<template>
	<div>
		<el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
			<el-menu :default-openeds="[]" :unique-opened="true" default-active="0" class="menu_view"
				:collapse="collapse">
				<el-sub-menu :index="0" @click="menuHandler('')">
					<template #title>
						<i class="iconfont icon-zhuye2" v-if="collapse?true:true"></i>
						<span>首页</span>
					</template>
				</el-sub-menu>
				<el-sub-menu v-for=" (menu,index) in menuList.backMenu" :key="menu.menu" :index="index+2+''">
					<template #title>
						<i class="iconfont" :class="menu.fontClass" v-if="collapse?true:true"></i>
						<span>{{ menu.menu }}</span>
					</template>
					<el-menu-item class="menu_item_view" v-for=" (child,sort) in menu.child" :key="sort"
						:index="(index+2)+'-'+sort" @click="menuHandler(child.tableName,child.menuJump)">{{ child.menu }}
					</el-menu-item>
				</el-sub-menu>
			</el-menu>
		</el-scrollbar>
	</div>
</template>

<script setup>
	import menu from '@/utils/menu'
	import {
		ref,
		toRefs,
		getCurrentInstance,
		nextTick
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//props
	const props = defineProps({
		collapse: Boolean
	})
	const {
		collapse
	} = toRefs(props)
	//data
	const menuList = ref([])
	const role = ref('')
	const styleChange = () => {
		nextTick(() => {
			document.querySelectorAll('.el-menu-vertical-demo .el-sub-menu .el-menu').forEach(el => {
				el.removeAttribute('style')
				const icon = {
					"border": "none",
					"padding": "0",
					"margin": "10px auto 0",
					"borderRadius": "0px",
					"background": "none",
					"display": "none",
					"width": "100%"
				}
				Object.keys(icon).forEach((key) => {
					el.style[key] = icon[key]
				})
			})
		})
	}
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	const init = () => {
		const menus = menu.list()
		if (menus) {
			menuList.value = menus
		}
		role.value = context?.$toolUtil.storageGet('role')

		for (let i = 0; i < menuList.value.length; i++) {
			if (menuList.value[i].roleName == role.value) {
				menuList.value = menuList.value[i];
				break;
			}
		}
		// styleChange()
	}
	const menuHandler = (name,menuJump) => {
		if(name == 'center'){
			name = `${role.value}Center`
		}
		if(name == 'storeup'){
			name = `storeup?type=${menuJump}`
		}
		if(name == 'exampaper' && menuJump == '12'){
			name = 'exampaperlist'
		}
		if(name == 'examrecord' && menuJump == '22'){
			name = 'examfailrecord'
		}
		let router = context?.$router
		name = '/' + name
		router.push(name)
	}
	init()
</script>

<style lang="scss" scoped>
	// 总盒子
	:deep(.menu_scrollbar) {

		// 菜单盒子-展开样式
		.menu_view {
			border: 0;
			padding: 30px 0 60px;
			background: none;
			height: 100%;

			// 无二级菜单
			.el-menu-item {
				padding: 0 10px 0 30px;
				margin: 0 auto;
				color: #494848;
				font-weight: 600;
				border-color: #0d3084;
				line-height: 50px;
				transition: width 0.3s ease 0s;
				border-radius: 10px;
				box-shadow: 0px 16px 9px -9px #999;
				background: url(http://codegen.caihongy.cn/20230220/d6e8e7b5d1d64a6cbed62818675f0bfb.png) no-repeat 90% 90%,linear-gradient(120deg, rgba(121,141,190,1) 0%, rgba(255,255,255,1) 50%, rgba(121,141,190,1) 100%),#fff;
				width: 90%;
				border-width: 4px;
				border-style: double;
				height: 58px;
				.iconfont {
					margin: 0 5px 0 0;
					color: inherit;
					width: 20px;
					vertical-align: middle;
					font-size: 20px;
					text-align: center;
				}
			}

			// 无二级悬浮
			.el-menu-item:hover {
				border-radius: 10px;
				padding: 0 10px 0 30px;
				box-shadow: 0px 16px 9px -9px #999;
				margin: 0 auto;
				color: #494848;
				background: url(http://codegen.caihongy.cn/20230220/180c5bfdae3d47d7a30296a253984372.png) no-repeat 90% 90%,linear-gradient(120deg, rgba(121,141,190,1) 0%, rgba(255,255,255,1) 50%, rgba(121,141,190,1) 100%),#fff;
				width: 93%;
				border-color: #849acf;
				border-width: 4px;
				line-height: 50px;
				border-style: outset;
				height: 58px;
			}

			// 无二级选中
			.el-menu-item.is-active {
				border-radius: 10px;
				padding: 0 10px 0 30px;
				box-shadow: 0px 16px 9px -9px #999;
				margin: 0 auto;
				color: #494848;
				background: url(http://codegen.caihongy.cn/20230220/180c5bfdae3d47d7a30296a253984372.png) no-repeat 90% 90%,linear-gradient(120deg, rgba(121,141,190,1) 0%, rgba(255,255,255,1) 50%, rgba(121,141,190,1) 100%),#fff;
				width: 93%;
				border-color: #849acf;
				border-width: 4px;
				line-height: 50px;
				border-style: outset;
				height: 58px;
			}

			// 有二级盒子
			.el-sub-menu {
				cursor: pointer;
				padding: 0 0;
				margin: 0 auto 20px;
				color: #333;
				white-space: nowrap;
				background: none;
				position: relative;

				// 有二级item
				.el-sub-menu__title {
					padding: 0 10px 0 30px;
					margin: 0 auto;
					color: #494848;
					font-weight: 600;
					border-color: #0d3084;
					line-height: 50px;
					transition: width 0.3s ease 0s;
					border-radius: 10px;
					box-shadow: 0px 16px 9px -9px #999;
					background: url(http://codegen.caihongy.cn/20230220/d6e8e7b5d1d64a6cbed62818675f0bfb.png) no-repeat 90% 90%,linear-gradient(120deg, rgba(121,141,190,1) 0%, rgba(255,255,255,1) 50%, rgba(121,141,190,1) 100%),#fff;
					width: 90%;
					border-width: 4px;
					border-style: double;
					height: 58px;
					.iconfont {
						margin: 0 5px 0 0;
						color: inherit;
						width: 20px;
						vertical-align: middle;
						font-size: 20px;
						text-align: center;
					}
					.el-sub-menu__icon-arrow{
						margin: -6px 0 0 8px;
						color: inherit;
						vertical-align: middle;
						width: auto;
						font-size: 12px;
						position: absolute;
						right: 10px;
					}
				}

				// 有二级item悬浮
				.el-sub-menu__title:hover {
					border-radius: 10px;
					padding: 0 10px 0 30px;
					box-shadow: 0px 16px 9px -9px #999;
					margin: 0 auto;
					color: #494848;
					background: url(http://codegen.caihongy.cn/20230220/180c5bfdae3d47d7a30296a253984372.png) no-repeat 90% 90%,linear-gradient(120deg, rgba(121,141,190,1) 0%, rgba(255,255,255,1) 50%, rgba(121,141,190,1) 100%),#fff;
					width: 93%;
					border-color: #849acf;
					border-width: 4px;
					line-height: 50px;
					border-style: outset;
					height: 58px;
				}
			}
			//二级选中
			.is-active {
				.el-sub-menu__title {
					border-radius: 10px;
					padding: 0 10px 0 30px;
					box-shadow: 0px 16px 9px -9px #999;
					margin: 0 auto;
					color: #494848;
					background: url(http://codegen.caihongy.cn/20230220/180c5bfdae3d47d7a30296a253984372.png) no-repeat 90% 90%,linear-gradient(120deg, rgba(121,141,190,1) 0%, rgba(255,255,255,1) 50%, rgba(121,141,190,1) 100%),#fff;
					width: 93%;
					border-color: #849acf;
					border-width: 4px;
					line-height: 50px;
					border-style: outset;
					height: 58px;
				}
			}
			// 二级盒子
			.el-menu--inline {
				border: none;
				padding: 0;
				margin: 20px auto 10px;
				background: none;
				width: auto;
				box-sizing: border-box;
				// 二级菜单
				.menu_item_view {
					padding: 0 10px 0 60px;
					margin: 0 auto 10px;
					color: #333;
					display: block;
					border-color: #798dbe;
					line-height: 36px;
					border-radius: 10px;
					box-shadow: none;
					background: linear-gradient(120deg, rgba(121,141,190,1) 0%, rgba(255,255,255,1) 50%, rgba(121,141,190,1) 100%),#fff;
					width: 80%;
					border-width: 4px 1px;
					border-style: double;
					height: 44px;
				}
				// 二级悬浮
				.menu_item_view:hover {
					padding: 0 10px 0 60px;
					color: #000;
					background: linear-gradient(120deg, rgba(121,141,190,1) 0%, rgba(255,255,255,1) 50%, rgba(121,141,190,1) 100%),#fff;
					border-color: #798dbe;
					border-width: 4px 1px;
					line-height: 36px;
					border-style: outset;
					height: 44px;
				}
				// 二级选中
				.is-active.menu_item_view {
					padding: 0 10px 0 60px;
					box-shadow: none;
					color: #000;
					background: linear-gradient(120deg, rgba(121,141,190,1) 0%, rgba(255,255,255,1) 50%, rgba(121,141,190,1) 100%),#fff;
					border-color: #798dbe;
					border-width: 4px 1px;
					line-height: 36px;
					border-style: outset;
					height: 44px;
				}
			}
		}
		// 菜单盒子-关闭样式
		.el-menu--collapse {
			padding: 0 10px;
			color: #fff;
			background: red;
			height: 100%;

			// 无二级菜单
			.el-menu-item {
				padding: 0 10px;
				color: #666;
				background: #fff;
				line-height: 50px;
				height: 50px;
				.iconfont {
					margin: 0 5px 0 0;
					color: inherit;
					width: 34px;
					vertical-align: middle;
					font-size: 20px;
					text-align: center;
				}
			}

			// 无二级悬浮
			.el-menu-item:hover {
				padding: 0 10px;
				color: #ccc;
				background: gray;
				line-height: 50px;
				height: 50px;
			}

			// 无二级选中
			.el-menu-item.is-active {
				padding: 0 10px;
				color: #ccc;
				background: #123;
				line-height: 50px;
				height: 50px;
			}

			// 有二级盒子
			.el-sub-menu {
				cursor: pointer;
				padding: 0 0;
				color: #333;
				white-space: nowrap;
				background: #fff;
				position: relative;

				// 有二级item
				.el-sub-menu__title {
					padding: 0 10px;
					color: #666;
					background: #fff;
					line-height: 50px;
					height: 50px;
					.iconfont {
						margin: 0 5px 0 0;
						color: inherit;
						width: 34px;
						vertical-align: middle;
						font-size: 20px;
						text-align: center;
					}
					.el-sub-menu__icon-arrow{
						margin: -3px 0 0 8px;
						color: inherit;
						vertical-align: middle;
						font-size: 12px;
						position: static;
					}
				}

				// 有二级item悬浮
				.el-sub-menu__title:hover {
					padding: 0 10px;
					color: #ccc;
					background: gray;
					line-height: 50px;
					height: 50px;
				}
			}
			//二级选中
			.is-active {
				.el-sub-menu__title {
					padding: 0 10px;
					color: #ccc;
					background: #123;
					line-height: 50px;
					height: 50px;
				}
			}
			// 二级盒子
			.el-menu--inline {
				border: none;
				padding: 4px;
				background: #123;
				// 二级菜单
				.menu_item_view {
					padding: 0 40px;
					color: #666;
					background: goldenrod;
					line-height: 50px;
					height: 50px;
				}
				// 二级悬浮
				.menu_item_view:hover {
					padding: 0 40px;
					color: #f00;
					background: greenyellow;
					line-height: 50px;
					height: 50px;
				}
				// 二级选中
				.is-active.menu_item_view {
					padding: 0 40px;
					color: #000;
					background: burlywood;
					line-height: 50px;
					height: 50px;
				}
			}
		}
	}
</style>
<style lang="scss">
	.el-popper{
		.el-menu--popup-container {
			.el-menu--popup{
				border: none;
				padding: 4px;
				background: #123;
				// 二级菜单
				.menu_item_view {
					padding: 0 40px;
					color: #666;
					background: goldenrod;
					line-height: 50px;
					height: 50px;
				}
				// 二级悬浮
				.menu_item_view:hover {
					padding: 0 40px;
					color: #f00;
					background: greenyellow;
					line-height: 50px;
					height: 50px;
				}
				// 二级选中
				.is-active.menu_item_view {
					padding: 0 40px;
					color: #000;
					background: burlywood;
					line-height: 50px;
					height: 50px;
				}
			}
		}
	}
</style>
