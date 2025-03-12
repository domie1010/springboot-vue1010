<template>
	<div class="app-contain" :style='{"padding":"30px 10% 30px","margin":"0px auto","alignItems":"flex-start","color":"#666","flexWrap":"wrap","display":"flex","width":"100%","fontSize":"14px","position":"relative","justifyContent":"space-between"}'>
		<div class="bread_view">
			<el-breadcrumb separator="/" class="breadcrumb">
				<el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_view">
			<el-button class="back_btn" @click="backClick" type="primary">返回</el-button>
		</div>
		<div class="detail_view">
			<div class="swiper_view">
				<mySwiper :data="bannerList" :type="3"
				:loop="false"
				:navigation="false"
				:pagination="true"
				:paginationType="4"
				:scrollbar="false"
				:slidesPerView="1"
				:spaceBetween="20"
				:autoHeight="false"
				:centeredSlides="false"
				:freeMode="false"
				:effectType="0"
				:direction="horizontal"
				:autoplay="true"
				:slidesPerColumn="1">
				<template #default="scope">
					<img :style='{"objectFit":"contain","width":"100%","height":"500px"}' :src="scope.row?$config.url + scope.row:''">
				</template>
			</mySwiper>
			</div>
			
			<div class="info_view">
				<div class="title_view">
					<div class="detail_title">
					</div>
				</div>
				<div class="info_item">
					<div class="info_label">内容分类</div>
					<div  class="info_text" >{{detail.neirongfenlei}}</div>
				</div>
				<div class="btn_view">
					<el-button v-if="centerType&&(detail.ispay=='未支付'||!detail.ispay)&&btnFrontAuth('neirongfenlei','支付')" class="approval_btn" @click="payClick">支付</el-button>
					<el-button class="edit_btn" v-if="centerType&&btnAuth('neirongfenlei','修改')" type="primary" @click="editClick">修改</el-button>
					<el-button class="del_btn" v-if="centerType&&btnAuth('neirongfenlei','删除')" type="danger" @click="delClick">删除</el-button>
				</div>
			</div>
		</div>
		<el-tabs type="border-card" v-model="activeName" class="tabs_view">
		</el-tabs>
	</div>
</template>
<script setup>
	import axios from 'axios'
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
		nextTick,
		computed
	} from 'vue';
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const route = useRoute()
	const router = useRouter()
	//基础信息
	const tableName = 'neirongfenlei'
	const formName = '内容分类'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	//权限验证
	const btnAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isAuth(e,a)
		}
	}
	//查看权限验证
	const btnFrontAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isFrontAuth(e,a)
		}
	}
	// 返回
	const backClick = () =>{
		history.back()
	}
	// 轮播图
	const bannerList = ref([])
	// 详情
	const title = ref('')
	const detail = ref({})
    const activeName = ref('first')
	const getDetail = () => {
		context?.$http({
			url: `${tableName}/detail/${route.query.id}`,
			method: 'get'
		}).then(res => {
			detail.value = res.data.data
		})
	}
	// 下载文件
	const downClick = (file) => {
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
			headers: {
				token: context?.$toolUtil.storageGet('frontToken')
			},
			responseType: "blob"
		}).then(({
			data
		}) => {
			const binaryData = [];
			binaryData.push(data);
			const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
				type: 'application/pdf;chartset=UTF-8'
			}))
			const a = document.createElement('a')
			a.href = objectUrl
			a.download = arr
			// a.click()
			// 下面这个写法兼容火狐
			a.dispatchEvent(new MouseEvent('click', {
				bubbles: true,
				cancelable: true,
				view: window
			}))
			window.URL.revokeObjectURL(data)
		})
	}
	// 判断是否从个人中心跳转
	const centerType = ref(false)
	const init = () => {
		if(route.query.centerType){
			centerType.value = true
		}
		getDetail()
	}
	//修改
	const editClick = () => {
		router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`)
	}
	//删除
	const delClick = () => {
		ElMessageBox.confirm(`是否删除此${formName}？`, '提示', {
			confirmButtonText: '是',
			cancelButtonText: '否',
			type: 'warning',
		}).then(()=>{
			context?.$http({
				url: `${tableName}/delete`,
				method: 'post',
				data: [detail.value.id]
			}).then(res=>{
				context?.$toolUtil.message('删除成功','success',()=>{
					history.back()
				})
			})
			
		})
	}
	onMounted(()=>{
		init()
	})
</script>
<style lang="scss" scoped>
	// 返回盒子
	.back_view {
		border-radius: 0px;
		padding: 0;
		margin: 10px auto;
		background: none;
		display: block;
		width: 100%;
		text-align: right;
		// 返回按钮
		.back_btn {
			border: 1px solid #eee;
			cursor: pointer;
			border-radius: 2px;
			padding: 0 30px;
			outline: none;
			color: #666;
			background: #f9f9f9;
			width: auto;
			font-size: 14px;
			height: 32px;
		}
		// 返回按钮-悬浮
		.back_btn:hover {
		}
	}
	// 面包屑盒子
	.bread_view {
		border: 1px solid #0d639220;
		border-radius: 4px;
		padding: 12px 20px;
		margin: 10px auto 10px;
		background: #0d639210;
		width: 100%;
		position: relative;
		:deep(.breadcrumb) {
			font-size: 14px;
			line-height: 1;
			.el-breadcrumb__separator {
				margin: 0 9px;
				color: #ccc;
				font-weight: 500;
			}
			.first_breadcrumb {
				.el-breadcrumb__inner {
					color: #333;
					display: inline-block;
				}
			}
			.second_breadcrumb {
				.el-breadcrumb__inner {
					color: #999;
					display: inline-block;
				}
			}
		}
	}
	
	.detail_view{
		border-radius: 0px;
		padding: 60px 40px 20px;
		background: #0058ab06;
		display: flex;
		width: 100%;
		border-color: #0058ab20;
		border-width: 1px 1px 0;
		justify-content: space-between;
		position: relative;
		border-style: solid;
		flex-wrap: wrap;
		// 轮播图
		.swiper_view {
			padding: 0;
			margin: 30px auto;
			width: 60%;
			height: 500px;
		}
		
		// 文字区
		.info_view {
			border: 0px solid #eee;
			padding: 0 20px;
			margin: 0 0 20px;
			overflow: hidden;
			background: none;
			width: 100%;
			box-sizing: border-box;
			height: auto;
		
			.title_view {
				border: 0px solid #eee;
				padding: 0 0 0 80px;
				margin: 30px auto;
				display: flex;
				line-height: 36px;
				top: 0;
				left: 12px;
				background: url(http://clfile.zggen.cn/20231229/c16b1e8143674870b2388c54506a0997.png) no-repeat left center / 99% 100%;
				width: calc(100% - 24px);
				justify-content: space-between;
				align-items: center;
				position: absolute;
				height: 36px;
		
				.detail_title {
					color: #fff;
					font-weight: 500;
					font-size: 16px;
				}
				.follow {
					border: 0px solid #ffffff50;
					cursor: pointer;
					border-radius: 4px;
					padding: 4px 10px;
					margin: 60px 10px 0 0;
					color: #333;
					background: none;
					display: flex;
					width: auto;
					line-height: 1;
					justify-content: center;
					align-items: center;
					.iconfont {
						margin: 0 4px 0 0;
						color: #333;
					}
					.iconfontActive {
						margin: 0 4px 0 0;
						color: #f79d1c;
					}
					span {
						color: #333;
					}
					.textActive {
						color: #f79d1c;
					}
				}
				.follow:hover {
				}
				.follow:active {
					transform: scale(1);
				}
			}
		
			.info_item {
					border-radius: 0px;
					padding: 0 0 10px;
					margin: 0 0 10px;
					background: none;
					display: flex;
					border-color: #0058ab20;
					border-width: 0 0 1px;
					align-items: center;
					border-style: dashed;
		
				.info_label {
					margin: 0 12px 0 0;
					font-weight: 500;
					width: auto;
				}
				.info_text {
					color: #888;
				}
			}
			.btn_view {
				padding: 0;
				margin: 20px 0 20px;
				display: flex;
				flex-wrap: wrap;
				// 修改-按钮
				.edit_btn {
					border: none;
					padding: 0 10px;
					color: #fff;
					background: rgba(255, 85, 0, 1.0);
					line-height: 32px;
					height: 32px;
				}
				// 悬浮
				.edit_btn:hover {
				}
				// 删除-按钮
				.del_btn {
					border: none;
					padding: 0 10px;
					color: #fff;
					background: #e96578;
					line-height: 32px;
					height: 32px;
				}
				// 悬浮
				.del_btn:hover {
				}
			}
		}
	}
	

	//底部盒子
	.tabs_view {
		border-radius: 0px;
		padding: 0 0 20px;
		box-shadow: none;
		margin: 0px auto;
		background: #0058ab06;
		width: 100%;
		border-color: #0058ab20;
		border-width: 0 1px 1px;
		border-style: solid;
		:deep(.el-tabs__header) {
			background: transparent;
			border: none;
		}
		// 头部
		:deep(.el-tabs__nav-scroll) {
			border-radius: 0;
			padding: 2px 60px 0;
			margin: 0;
			background: none;
			display: flex;
			border-color: #fff;
			border-width: 0 0 0px;
			border-style: solid;
			flex-wrap: wrap;
			height: 42px;
			.el-tabs__nav {
				.el-tabs__item {
					border: 1px solid #ddd;
					padding: 0 20px;
					margin: 0 20px 0 0;
					color: #333;
					font-weight: 500;
					display: inline-block;
					font-size: 14px;
					line-height: 36px;
					border-radius: 4px;
					background: none;
					position: relative;
					list-style: none;
					text-align: center;
					min-width: 80px;
					height: 36px;
				}
				.el-tabs__item:hover {
					border: 1px solid #0d6392;
					border-radius: 4px;
					color: #fff;
					background: #0d6392;
				}
				.is-active {
					border: 1px solid #0d6392;
					border-radius: 4px;
					margin: 0 20px 0 0;
					color: #fff;
					background: #0d6392;
					line-height: 36px;
					text-align: center;
					min-width: 80px;
					height: 36px;
				}
			}
		}
		// 内容区
		:deep(.el-tabs__content) {
			border-radius: 0 0 4px 4px;
			padding: 20px 60px;
			color: #666;
			background: none;
			font-size: 14px;
			border-color: #eee;
			border-width: 0;
			border-style: solid;
			text-align: left;
		}
	}
	


</style>