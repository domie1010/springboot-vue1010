<template>
	<div class="app-contain" :style='{"padding":"0 10%","margin":"0","overflow":"hidden","color":"#666","alignItems":"flex-start","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%","fontSize":"14px","justifyContent":"space-between"}'>
		<div class="bread_view">
			<el-breadcrumb separator="/" class="breadcrumb">
				<el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_view" v-if="centerType">
			<el-button class="back_btn" @click="backClick" type="primary">返回</el-button>
		</div>
		<el-form :inline="true" :model="searchQuery" class="list_search">
			<div class="search_view">
				<div class="search_label">
					用户账号：
				</div>
				<div class="search_box">
					<el-input class="search_inp" v-model="searchQuery.yonghuzhanghao" placeholder="用户账号"
						clearable>
					</el-input>
				</div>
			</div>
			<div class="search_btn_view">
				<el-button class="search_btn" type="primary" @click="searchClick">搜索</el-button>
				<el-button class="add_btn" type="success" v-if="btnAuth('yonghu','新增')" @click="addClick">新增</el-button>
			</div>
		</el-form>
		<div class="list_bottom">
			<div class="data_box">
				<div class="data_view">
					<div class="data_item" v-for="(item,index) in list" :key="index" @click.stop="detailClick(item.id)" >
						<div class="data_content">
						</div>
					</div>
				</div>
				<el-pagination
					background 
					:layout="layouts.join(',')"
					:total="total" 
					:page-size="listQuery.limit"
					prev-text="<"
					next-text=">"
					:hide-on-single-page="false"
					:style='{"border":"0px solid #eee","padding":"4px 0","margin":"10px 0 20px","whiteSpace":"nowrap","color":"#333","textAlign":"center","flexWrap":"wrap","background":"none","display":"flex","width":"100%","fontWeight":"500","justifyContent":"center"}'
					@size-change="sizeChange"
					@current-change="currentChange" 
					@prev-click="prevClick"
					@next-click="nextClick"  />
			</div>
		</div>
		<el-dialog v-model="preViewVisible" :title="'查看大图'" width="60%" destroy-on-close>
			<img :src="preViewUrl" style="width: 100%;" alt="">
		</el-dialog>
	</div>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	const route = useRoute()
	//基础信息
	const tableName = 'yonghu'
	const formName = '用户'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	const list = ref([])
	const listQuery = ref({
		page: 1,
		limit: Number(8)
	})
	const total = ref(0)
	const listLoading = ref(false)
	//权限验证
	const btnAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isAuth(e,a)
		}
	}
	const addClick = () => {
		router.push('/index/yonghuAdd')
	}
	//判断是否从个人中心跳转
	const centerType = ref(false)
	//返回
	const backClick = () => {
		router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
	}
	const init = () => {
		if(route.query.centerType){
			centerType.value = true
		}
		getList()
	}
	//搜索
	const searchQuery = ref({})
	//下拉列表
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	//分页
	const layouts = ref(["prev","pager","next"])
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	const prevClick = () => {
		listQuery.value.page = listQuery.value.page - 1
		getList()
	}
	const nextClick = () => {
		listQuery.value.page = listQuery.value.page + 1
		getList()
	}
	//分页
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		if(searchQuery.value.yonghuzhanghao&&searchQuery.value.yonghuzhanghao!=''){
			params.yonghuzhanghao = '%' + searchQuery.value.yonghuzhanghao + '%'
		}
		context?.$http({
			url: `${tableName}/${centerType.value?'page':'list'}`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
		})
	}
	const detailClick = (id) => {
		router.push('yonghuDetail?id=' + id + (centerType.value?'&&centerType=1':''))
	}
	//下载文件
	const download = (file) =>{
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		const a = document.createElement('a');
		a.style.display = 'none';
		a.setAttribute('target', '_blank');
		file && a.setAttribute('download', file);
		a.href = context?.$config.url + file;
		document.body.appendChild(a);
		a.click();
		document.body.removeChild(a);
	}
	// 查看大图
	const preViewUrl = ref('')
	const preViewVisible = ref(false)
	const preViewClick = (url) =>{
		preViewUrl.value = url
		preViewVisible.value = true
	}
	init()
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
	// 分类盒子
	.category_view {
		padding: 20px;
		margin: 0 40px 0 0;
		background: #fff;
		display: flex;
		width: 200px;
		border-color: #ddd #f2f2f2 #eee;
		border-width: 4px 2px 3px;
		justify-content: center;
		border-style: solid;
		flex-wrap: wrap;
		// 分类item
		.category {
			cursor: pointer;
			padding: 10px 20px;
			margin: 0px 0px 10px 0;
			color: #333;
			border-color: #ededed;
			box-sizing: border-box;
			border-radius: 4px;
			background: none;
			width: 100%;
			border-width: 1px;
			border-style: solid;
			text-align: left;
			min-width: 60%;
		}
		// item-悬浮
		.category:hover {
			color: #fff;
			background: #0d6392;
			border-color: #0d6392;
			border-width: 1px;
			opacity: 1;
			border-style: solid;
		}
		// item-选中
		.categoryActive {
			color: #fff;
			background: #0d6392;
			border-color: #0d6392;
			border-width: 1px;
			opacity: 1;
			border-style: solid;
		}
	}

	//搜索
	.list_search {
		border: 0px solid #ddd;
		padding: 0;
		margin: 20px auto;
		background: none;
		display: flex;
		width: 100%;
		justify-content: center;
		align-items: center;
		.search_view {
			margin: 0 10px 0 0;
			display: flex;
			align-items: center;
			.search_label {
				margin: 0 10px 0 0;
				color: #666;
				font-weight: 500;
				display: inline-block;
				font-size: 14px;
				line-height: 40px;
				height: 40px;
			}
			.search_box {
				display: flex;
				width: calc(100% - 100px);
				// 输入框
				:deep(.search_inp) {
					border: 1px solid #eee;
					box-shadow: 0 0 0px rgba(85, 255, 255, 0.5);
					padding: 0 10px;
					outline: 0px solid #efefef;
					background: #fff;
					width: 100%;
					outline-offset: 0px;
					line-height: 36px;
					box-sizing: border-box;
					min-width: 200px;
					height: 36px;
					//去掉默认样式
					.el-input__wrapper{
						border: none;
						box-shadow: none;
						background: none;
						border-radius: 0;
						height: 100%;
						padding: 0;
					}
					.is-focus {
						box-shadow: none !important;
					}
				}
			}
		}
		.search_btn_view {
			margin: 20px 0;
			display: flex;
			// 搜索按钮
			.search_btn {
				border: 0;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 20px;
				outline: none;
				margin: 0 10px 0 0;
				color: #fff;
				background: #0d6392;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 搜索按钮-悬浮
			.search_btn:hover {
				opacity: 0.8;
			}
			// 新增按钮
			.add_btn {
				border: 0;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 20px;
				margin: 0 10px 0 0;
				outline: none;
				color: #333;
				background: #0058ab30;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 新增按钮-悬浮
			.add_btn:hover {
				opacity: 0.8;
			}
		}
	}

	// 数据盒子
	.list_bottom {
		padding: 0px;
		background: #fff;
		flex: 1;
		display: flex;
		width: calc(100% - 240px);
		align-items: flex-start;
		flex-wrap: wrap;
		//列表
		.data_box {
			padding: 0;
			background: none;
			width: 100%;
			.data_view {
				background: none;
				display: flex;
				width: 100%;
				flex-wrap: wrap;
				.data_item:nth-of-type(2n - 1) {
					border: 1px solid #eee;
					padding: 10px;
					margin: 0 0 20px;
					display: flex;
					width: calc(100% / 2 - 20px);
					justify-content: space-between;
					position: relative;
					flex-wrap: wrap;
					// 图片盒子
					.data_img_box {
						width: 50%;
						position: relative;
						height: 100%;
						// 图片
						.data_img {
							object-fit: cover;
							width: 100%;
							height: 100%;
						}
					}
					// 内容盒子
					.data_content {
						padding: 20px;
						width: 50%;
						transition: all 0s;
						height: auto;
						// 价格
						.data_price {
							color: #f00;
							text-align: right;
						}
						// 标题1
						.data_title1 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题2
						.data_title2 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题3
						.data_title3 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题4
						.data_title4 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题5
						.data_title5 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 底部栏
						.data_operate_box {
							display: flex;
							justify-content: center;
							align-items: center;
							// 点赞数
							.data_like {
								margin: 0 10px 0 0;
								color: #0266b5;
								display: flex;
								font-size: 16px;
								align-items: center;
								.like_icon {
									margin: 0 4px 0 0;
									color: inherit;
								}
								.like_num {
									color: inherit;
								}
							}
							// 收藏数
							.data_collect {
								margin: 0 10px 0 0;
								color: #ee7810;
								display: flex;
								font-size: 16px;
								align-items: center;
								.el-icon {
									margin: 0 4px 0 0;
									color: inherit;
								}
								.collect_num {
									color: inherit;
								}
							}
							// 点击数
							.data_clickNum {
								margin: 0 10px 0 0;
								color: inherit;
								display: flex;
								font-size: 16px;
								align-items: center;
								.el-icon {
									margin: 0 4px 0 0;
									color: inherit;
								}
								.clickNum_num {
									color: inherit;
								}
							}
						}
					}
				}
				.data_item:nth-of-type(2n) {
					border: 1px solid #eee;
					padding: 10px;
					margin: 0 10px 20px;
					display: flex;
					width: calc(100% / 2 - 20px);
					justify-content: space-between;
					position: relative;
					flex-wrap: wrap;
					// 图片盒子
					.data_img_box {
						width: 50%;
						position: relative;
						height: 100%;
						order: 2;
						// 图片
						.data_img {
							object-fit: cover;
							width: 100%;
							height: 100%;
						}
					}
					// 内容盒子
					.data_content {
						padding: 20px;
						width: 50%;
						transition: all 0s;
						height: auto;
						// 价格
						.data_price {
							color: #f00;
						}
						// 标题1
						.data_title1 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题2
						.data_title2 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题3
						.data_title3 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题4
						.data_title4 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题5
						.data_title5 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 底部栏
						.data_operate_box {
							display: flex;
							justify-content: center;
							align-items: center;
							// 点赞数
							.data_like {
								margin: 0 10px 0 0;
								color: #0266b5;
								display: flex;
								font-size: 16px;
								align-items: center;
								.like_icon {
									margin: 0 4px 0 0;
									color: inherit;
								}
								.like_num {
									color: inherit;
								}
							}
							// 收藏数
							.data_collect {
								margin: 0 10px 0 0;
								color: #ee7810;
								display: flex;
								font-size: 16px;
								align-items: center;
								.el-icon {
									margin: 0 4px 0 0;
									color: inherit;
								}
								.collect_num {
									color: inherit;
								}
							}
							// 点击数
							.data_clickNum {
								margin: 0 10px 0 0;
								color: inherit;
								display: flex;
								font-size: 16px;
								align-items: center;
								.el-icon {
									margin: 0 4px 0 0;
									color: inherit;
								}
								.clickNum_num {
									color: inherit;
								}
							}
						}
					}
				}
				.data_item:nth-of-type(2n - 1):hover {
					cursor: pointer;
					border: 1px solid #0058ab20;
					color: #0058ab;
					background: #0058ab06;
					// 图片盒子
					.data_img_box {
						// 图片
						.data_img {
						}
					}
					// 内容盒子
					.data_content {
						// 价格
						.data_price {
						}
						// 标题1
						.data_title1 {
						}
						// 标题2
						.data_title2 {
						}
						// 标题3
						.data_title3 {
						}
						// 标题4
						.data_title4 {
						}
						// 标题5
						.data_title5 {
						}
						// 底部栏
						.data_operate_box {
							color: inherit;
							// 点赞数
							.data_like {
								color: inherit;
								.like_icon {
								}
								.like_num {
								}
							}
							// 收藏数
							.data_collect {
								color: inherit;
								.el-icon {
								}
								.collect_num {
								}
							}
							// 点击数
							.data_clickNum {
								.el-icon {
								}
								.clickNum_num {
								}
							}
						}
					}
				}
				.data_item:nth-of-type(2n):hover {
					cursor: pointer;
					border: 1px solid #0058ab20;
					color: #0058ab;
					background: #0058ab06;
					// 图片盒子
					.data_img_box {
						// 图片
						.data_img {
						}
					}
					// 内容盒子
					.data_content {
						// 价格
						.data_price {
						}
						// 标题1
						.data_title1 {
						}
						// 标题2
						.data_title2 {
						}
						// 标题3
						.data_title3 {
						}
						// 标题4
						.data_title4 {
						}
						// 标题5
						.data_title5 {
						}
						// 底部栏
						.data_operate_box {
							color: inherit;
							// 点赞数
							.data_like {
								color: inherit;
								.like_icon {
								}
								.like_num {
								}
							}
							// 收藏数
							.data_collect {
								color: inherit;
								.el-icon {
								}
								.collect_num {
								}
							}
							// 点击数
							.data_clickNum {
								.el-icon {
								}
								.clickNum_num {
								}
							}
						}
					}
				}
			}
		}
	}

	// animation
	.animation_box {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	.animation_box:hover {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, -10px, 0px);
		-webkit-perspective: 1000px;
		perspective: 1000px;
		transition: 0.3s;
	}
	.animation_box img {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	.animation_box img:hover {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		-webkit-perspective: 1000px;
		perspective: 1000px;
		transition: 0.3s;
	}
	// animation

	// 分页器
	.el-pagination {
		// 总页码
		:deep(.el-pagination__total) {
			margin: 0 10px 0 0;
			color: #666;
			font-weight: 400;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 24px;
			height: 24px;
		}
		// 上一页
		:deep(.btn-prev) {
			border: none;
			border-radius: 2px;
			padding: 0;
			margin: 0 2px;
			color: #666;
			background: #f4f4f5;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 24px;
			min-width: 24px;
			height: 24px;
		}
		// 下一页
		:deep(.btn-next) {
			border: none;
			border-radius: 2px;
			padding: 0;
			margin: 0 2px;
			color: #666;
			background: #f4f4f5;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 24px;
			min-width: 24px;
			height: 24px;
		}
		// 上一页禁用
		:deep(.btn-prev:disabled) {
			border: none;
			cursor: not-allowed;
			padding: 0;
			margin: 0 2px;
			color: #C0C4CC;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 24px;
			border-radius: 2px;
			background: #f4f4f5;
			width: 24px;
			height: 24px;
		}
		// 下一页禁用
		:deep(.btn-next:disabled) {
			border: none;
			cursor: not-allowed;
			padding: 0;
			margin: 0 2px;
			color: #C0C4CC;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 24px;
			border-radius: 2px;
			background: #f4f4f5;
			width: 24px;
			height: 24px;
		}
		// 页码
		:deep(.el-pager) {
			padding: 0;
			margin: 0;
			display: inline-block;
			vertical-align: top;
			// 数字
			.number {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 24px;
				border-radius: 2px;
				background: #f4f4f5;
				text-align: center;
				min-width: 24px;
				height: 24px;
			}
			// 数字悬浮
			.number:hover {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 24px;
				border-radius: 2px;
				background: #0d6392;
				text-align: center;
				min-width: 24px;
				height: 24px;
			}
			// 选中
			.number.is-active {
				cursor: default;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 24px;
				border-radius: 2px;
				background: #0d6392;
				text-align: center;
				min-width: 24px;
				height: 24px;
			}
		}
		// sizes
		:deep(.el-pagination__sizes) {
			box-shadow: none;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 24px;
			height: 24px;
			.el-select {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0;
				color: #606266;
				display: inline-block;
				font-size: 13px;
				line-height: 24px;
				border-radius: 0px;
				outline: 0;
				background: #fff;
				width: 100%;
				text-align: center;
				height: 24px;
				//去掉默认样式
				.select-trigger{
					height: 100%;
					.el-input{
						height: 100%;
						.el-input__wrapper{
							border: none;
							box-shadow: none;
							background: none;
							border-radius: 0;
							height: 100%;
							padding: 0;
						}
						.is-focus {
							box-shadow: none !important;
						}
					}
				}
			}
		}
		// 跳页
		:deep(.el-pagination__jump) {
			margin: 0 0 0 24px;
			color: #606266;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
			// 输入框
			.el-input {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 3px;
				margin: 0 6px;
				color: #606266;
				display: inline-block;
				font-size: 14px;
				line-height: 24px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 38px;
				text-align: center;
				height: 24px;
				//去掉默认样式
				.el-input__wrapper{
					border: none;
					box-shadow: none;
					background: none;
					border-radius: 0;
					height: 100%;
					padding: 0;
				}
				.is-focus {
					box-shadow: none !important;
				}
			}
		}
	}
	
	// 热门信息盒子
	.hot_view {
		border: 0px solid #ddd;
		padding: 0;
		margin: 30px auto;
		background: none;
		width: 100%;
		// 标题
		.hot_title {
			margin: 20px auto;
			color: #666;
			background: none;
			font-weight: 500;
			width: 600px;
			font-size: 24px;
			text-align: center;
		}

		.hot_list {
			padding: 0 0 0 2%;
			margin: 30px 0 0;
			display: flex;
			width: 100%;
			flex-wrap: wrap;
			// item
			.hot {
				cursor: pointer;
				border: 1px solid #eee;
				padding: 10px 10px;
				margin: 0 2% 20px 0;
				background: #fff;
				width: 31.3%;
				box-sizing: border-box;
				//图片盒子
				.hot_img_view {
					margin: 0 0 2px;
					width: 48%;
					float: left;
					height: 100px;
					// 图片
					.hot_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				// 内容盒子
				.hot_content {
					width: 48%;
					float: right;
					// 名称
					.hot_text {
					padding: 0 16px;
					margin: 0 0 0px;
					overflow: hidden;
					color: #333;
					white-space: nowrap;
					background: none;
					font-size: 14px;
					line-height: 30px;
					text-overflow: ellipsis;
					text-align: left;
					height: 30px;
					}
				}
			}
		}
	}
</style>