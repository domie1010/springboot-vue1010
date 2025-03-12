
<template>
	<div>
		<div class="app-contain">
			<div class="list_search_view">
				<div class="btn_view">
					<el-button type="primary" :disabled="selRows.length==1?false:true" @click="editClick" v-if=" btnAuth('config','修改')">修改</el-button>
				</div>
			</div>
			<br>
			<el-table
				v-loading="listLoading"
				border 
				:stripe='true'
				@selection-change="handleSelectionChange" 
				ref="table"
				v-if="btnAuth('config','查看')"
				:data="list"
				@row-click="listChange">
				<el-table-column :resizable='true' align="left" header-align="left" type="selection" width="55" />
				<el-table-column label="序号" width="70" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">{{ scope.$index + 1}}</template>
				</el-table-column>
				<el-table-column
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="left"
					 prop="name"
					label="名称">
					<template #default="scope">
						{{scope.row.name}}
					</template>
				</el-table-column>
				<el-table-column label="值" width="120" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						<div v-if="scope.row.value">
							<el-image v-if="scope.row.value.substring(0,4)=='http'" preview-teleported
								:preview-src-list="[scope.row.value.split(',')[0]]"
								:src="scope.row.value.split(',')[0]" style="width:100px;height:100px"></el-image>
							<el-image v-else preview-teleported
								:preview-src-list="[$config.url+scope.row.value.split(',')[0]]"
								:src="$config.url+scope.row.value.split(',')[0]" style="width:100px;height:100px">
							</el-image>
						</div>
						<div v-else>无图片</div>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination 
				background
				:layout="layouts.join(',')"
				:total="total" 
				:page-size="listQuery.limit"
				prev-text="上一页"
				next-text="下一页"
				:hide-on-single-page="false"
				:style='{"border":"1px solid #e1e1e1","padding":"10px 0","margin":"30px 0 0","whiteSpace":"nowrap","overflow":"hidden","color":"#333","textAlign":"center","background":"#eff3f9","width":"100%","clear":"both","fontWeight":"500"}'
				@size-change="sizeChange"
				@current-change="currentChange" 
				@prev-click="prevClick"
				@next-click="nextClick"  />
		</div>
		<formModel ref="formRef" @formModelChange="formModelChange"></formModel>
	</div>
</template>
<script setup>
	import axios from 'axios'
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
		watch,
	} from 'vue'
	import {
		useRoute,
		useRouter
	} from 'vue-router'
	import {
		ElMessageBox
	} from 'element-plus'
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	import formModel from './formModel.vue'
	
	//基础信息
	const tableName = 'config'
	const formName = '轮播图'
	const route = useRoute()
	//基础信息
	onMounted(()=>{
	})
	//列表数据
	const list = ref(null)
	const table = ref(null)
	const listQuery = ref({
		page: 1,
		limit: 20,
		sort: 'id',
		order: 'desc'
	})
	const searchQuery = ref({})
	const selRows = ref([])
	const listLoading = ref(false)
	const listChange = (row) =>{
		nextTick(()=>{
			table.value.clearSelection()
			table.value.toggleRowSelection(row)
		})
	}
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		params['sort'] = 'id'
		params['order'] = 'desc'
        params['name'] = '%swiper%'
		context?.$http({
			url: `${tableName}/page`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
		})
	}
	//删
	const delClick = (id) => {
		let ids = ref([])
		if (id) {
			ids.value = [id]
		} else {
			if (selRows.value.length) {
				for (let x in selRows.value) {
					ids.value.push(selRows.value[x].id)
				}
			} else {
				return false
			}
		}
		ElMessageBox.confirm(`是否删除选中${formName}`, '提示', {
			confirmButtonText: '是',
			cancelButtonText: '否',
			type: 'warning',
		}).then(() => {
			context?.$http({
				url: `${tableName}/delete`,
				method: 'post',
				data: ids.value
			}).then(res => {
				context?.$toolUtil.message('删除成功', 'success',()=>{
					getList()
				})
			})
		})
	}
	//多选
	const handleSelectionChange = (e) => {
		selRows.value = e
	}
	//列表数据
	//分页
	const total = ref(0)
	const layouts = ref(["total","prev","pager","next","sizes","jumper"])
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
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	//搜索
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	//表单
	const formRef = ref(null)
	const formModelChange=()=>{
		searchClick()
	}
	const addClick = ()=>{
		formRef.value.init()
	}
	const editClick = ()=>{
		if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'edit')
		}
	}
	
	const infoClick = (id=null)=>{
		if(id){
			formRef.value.init(id,'info')
		}
		else if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'info')
		}
	}
	// 表单
	// 预览文件
	const preClick = (file) =>{
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		window.open(context?.$config.url + file)
		// const a = document.createElement('a');
		// a.style.display = 'none';
		// a.setAttribute('target', '_blank');
		// file && a.setAttribute('download', file);
		// a.href = context?.$config.url + file;
		// document.body.appendChild(a);
		// a.click();
		// document.body.removeChild(a);
	}
	// 下载文件
	const download = (file) => {
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
			headers: {
				token: context?.$toolUtil.storageGet('Token')
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


	//初始化
	const init = () => {
		getList()
	}
	init()
</script>
<style lang="scss" scoped>
	
	// 操作盒子
	.list_search_view {
		margin: 20px auto;
		display: flex;
		width: 100%;
		align-items: center;
		flex-wrap: wrap;
		// 搜索盒子
		.search_form {
			border: 0px solid #eee;
			border-radius: 0px;
			padding: 0;
			margin: 0;
			display: flex;
			width: auto;
			align-items: center;
			order: 2;
			// 子盒子
			.search_view {
				padding: 0;
				margin: 0 0 0 0;
				display: flex;
				align-items: center;
				// 搜索label
				.search_label {
					margin: 0 5px 0;
					color: #666;
					background: none;
					font-weight: 500;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					text-align: right;
					height: 40px;
				}
				// 搜索item
				.search_box {
					padding: 0;
					display: inline-block;
					width: auto;
				}
			}
			// 搜索按钮盒子
			.search_btn_view {
				width: 20%;
				display: flex;
				padding: 0 20px;
				// 搜索按钮
				.search_btn {
					border: 3px ridge rgba(93,83,181,1);
					cursor: pointer;
					border-radius: 4px;
					padding: 0 20px;
					margin: 0 0 0 10px;
					color: #fff;
					background: linear-gradient(180deg, rgba(191,187,233,1) 0%, rgba(139,133,203,1) 50%, rgba(111,100,203,1) 51%, rgba(93,83,181,1) 100%);
					width: auto;
					font-size: 14px;
					min-width: 110px;
					height: 40px;
				}
				// 搜索按钮-悬浮
				.search_btn:hover {
					opacity: 1;
				}
			}
		}
		//头部按钮盒子
		.btn_view {
			margin: 0 20px 0 0;
			display: flex;
			width: auto;
			flex-wrap: wrap;
			order: 1;
			// 其他
			:deep(.el-button--default){
				border: 1px solid #b9c8d2;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 20px;
				margin: 0 5px 0 0;
				color: #fa7e1a;
				background: url(http://codegen.caihongy.cn/20230210/7d53b0e6bd6b44dc87a1dcd33f7af58f.gif);
				font-weight: 600;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 其他-悬浮
			:deep(.el-button--default:hover){
				opacity: 0.8;
			}
			// 新增
			:deep(.el-button--success){
				border: 1px solid #b9c8d2;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 20px;
				margin: 0 5px 0 0;
				color: #4968b4;
				background: url(http://codegen.caihongy.cn/20230210/7d53b0e6bd6b44dc87a1dcd33f7af58f.gif);
				font-weight: 600;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 新增-悬浮
			:deep(.el-button--success:hover){
				opacity: 0.8;
			}
			// 修改
			:deep(.el-button--primary){
				border: 1px solid #b9c8d2;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 20px;
				margin: 0 5px 0 0;
				color: #2791f6;
				background: url(http://codegen.caihongy.cn/20230210/7d53b0e6bd6b44dc87a1dcd33f7af58f.gif);
				font-weight: 600;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 修改-悬浮
			:deep(.el-button--primary:hover){
				opacity: 0.8;
			}
			// 详情
			:deep(.el-button--info){
				border: 1px solid #b9c8d2;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 20px;
				margin: 0 5px 0 0;
				color: #42b228;
				background: url(http://codegen.caihongy.cn/20230210/7d53b0e6bd6b44dc87a1dcd33f7af58f.gif);
				font-weight: 600;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 详情-悬浮
			:deep(.el-button--info:hover){
				opacity: 0.8;
			}
			// 删除
			:deep(.el-button--danger){
				border: 1px solid #b9c8d2;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 20px;
				margin: 0 5px 0 0;
				color: #d63d55;
				background: url(http://codegen.caihongy.cn/20230210/7d53b0e6bd6b44dc87a1dcd33f7af58f.gif);
				font-weight: 600;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 删除-悬浮
			:deep(.el-button--danger:hover){
				opacity: 0.8;
			}
			// 统计
			:deep(.el-button--warning){
				border: 1px solid #b9c8d2;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 20px;
				margin: 0 5px 0 0;
				color: #16c1b6;
				background: url(http://codegen.caihongy.cn/20230210/7d53b0e6bd6b44dc87a1dcd33f7af58f.gif);
				font-weight: 600;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
			// 统计-悬浮
			:deep(.el-button--warning:hover){
				opacity: 0.8;
			}
		}
	}
	// 表格样式
	.el-table {
		border-radius: 0px;
		padding: 0;
		box-shadow: 0px 16px 12px -9px #bbb;
		margin: 20px 0 0;
		background: url(http://codegen.caihongy.cn/20230215/073b458fe9794c978b11ee977f3ace9c.gif);
		width: 100%;
		font-size: 13px;
		border-color: #92a3cb;
		border-width: 4px 1px 2px 1px;
		border-style: solid;
		:deep(.el-table__header-wrapper) {
			thead {
				color: inherit;
				font-weight: 500;
				width: 100%;
				tr {
					background: #f8f8f8;
					th {
						padding: 5px 0;
						background: rgba(145,145,145,.1);
						border-color: #92a3cb30;
						border-width: 0 1px 1px 0;
						border-style: solid;
						text-align: left;
						.cell {
							padding: 0 5px;
							word-wrap: normal;
							color: inherit;
							white-space: normal;
							font-weight: bold;
							display: inline-block;
							vertical-align: middle;
							font-size: inherit;
							line-height: 24px;
							text-overflow: ellipsis;
							word-break: break-all;
							width: 100%;
							position: relative;
						}
					}
				}
			}
		}
		:deep(.el-table__body-wrapper) {
			tbody {
				width: 100%;
				tr {
					background: rgba(255,255,255,.5);
					td {
						padding: 5px 0;
						color: #555;
						background: rgba(255,255,255,.5);
						border-color: #92a3cb30;
						border-width: 0 1px 1px 0;
						border-style: solid;
						text-align: left;
						.cell {
							padding: 0 10px;
							overflow: hidden;
							word-break: break-all;
							white-space: normal;
							line-height: 24px;
							text-overflow: ellipsis;
							// 编辑
							.el-button--primary {
								border: 1px solid #d7e0ea;
								cursor: pointer;
								border-radius: 4px;
								padding: 4px 4px 4px 20px;
								box-shadow: 0px 4px 2px -2px #eee;
								margin: 0 6px 6px 0;
								color: #466c95;
								background: url(http://codegen.caihongy.cn/20230220/9220697c21454c7f81dd6cc302220480.png) no-repeat 4px 8px,#eaeef2;
								width: auto;
								font-size: 13px;
								height: 32px;
							}
							// 编辑-悬浮
							.el-button--primary:hover {
							}
							// 详情
							.el-button--info {
								border: 1px solid #d7e1eb;
								cursor: pointer;
								border-radius: 4px;
								padding: 4px 4px 4px 20px;
								box-shadow: 0px 4px 2px -2px #eee;
								margin: 0 6px 6px 0;
								color: #1b89ef;
								background: url(http://codegen.caihongy.cn/20230220/b0c049479a6d42828661d85d56848f78.png) no-repeat 4px 8px,#ebf4fd;
								width: auto;
								font-size: 13px;
								height: 32px;
							}
							// 详情-悬浮
							.el-button--info:hover {
							}
							// 删除
							.el-button--danger {
								border: 1px solid #edd9db;
								cursor: pointer;
								border-radius: 4px;
								padding: 4px 4px 4px 20px;
								box-shadow: 0px 4px 2px -2px #eee;
								margin: 0 6px 6px 0;
								color: #eb4a5f;
								background: url(http://codegen.caihongy.cn/20230220/afa1aa4f7bd9496e8301ffa4a0f01e20.png) no-repeat 4px 8px,#fbf2f2;
								width: auto;
								font-size: 13px;
								height: 32px;
							}
							// 删除-悬浮
							.el-button--danger:hover {
							}
							// 跨表
							.el-button--success {
								border: 1px solid #c9f6f5;
								cursor: pointer;
								border-radius: 4px;
								padding: 4px 4px 4px 20px;
								box-shadow: 0px 4px 2px -2px #eee;
								margin: 0 6px 6px 0;
								color: #333;
								background: url(http://clfile.zggen.cn/20240410/3833536c5d7c465c95cc97a90d6c0f08.png) no-repeat 2px center / 18px,#e4f9f8;
								width: auto;
								font-size: 13px;
								height: 32px;
							}
							// 跨表-悬浮
							.el-button--success:hover {
							}
							// 操作
							.el-button--warning {
								border: 1px solid #f6e4c9;
								cursor: pointer;
								border-radius: 4px;
								padding: 4px 4px 4px 20px;
								box-shadow: 0px 4px 2px -2px #eee;
								margin: 0 6px 6px 0;
								color: #333;
								background: url(http://clfile.zggen.cn/20240410/5be10e36b4204cbab778a9800c99ccba.png) no-repeat 4px center / 12px,#fef7ed;
								width: auto;
								font-size: 13px;
								height: 32px;
							}
							// 操作-悬浮
							.el-button--warning:hover {
							}
						}
					}
				}
				tr.el-table__row--striped {
					td {
						background: rgba(238,238,238,.5);
					}
				}
				tr:hover {
					td {
						padding: 5px 0;
						color: #666;
						background: none;
						border-color: #92a3cb30;
						border-width: 0 1px 1px 0;
						border-style: solid;
						text-align: left;
					}
				}
			}
		}
	}
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
			line-height: 28px;
			height: 28px;
		}
		// 上一页
		:deep(.btn-prev) {
			border: 1px solid #eee;
			border-radius: 0px;
			padding: 0 5px;
			margin: 0 5px;
			color: #666;
			background: #fff;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 26px;
			min-width: 35px;
			height: 26px;
		}
		// 下一页
		:deep(.btn-next) {
			border: 1px solid #eee;
			border-radius: 0px;
			padding: 0 5px;
			margin: 0 5px;
			color: #666;
			background: #fff;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 26px;
			min-width: 35px;
			height: 26px;
		}
		// 上一页禁用
		:deep(.btn-prev:disabled) {
			border: 1px solid #eee;
			cursor: not-allowed;
			border-radius: 0px;
			padding: 0 5px;
			margin: 0 5px;
			color: #666;
			background: #fff;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 26px;
			height: 26px;
		}
		// 下一页禁用
		:deep(.btn-next:disabled) {
			border: 1px solid #eee;
			cursor: not-allowed;
			border-radius: 0px;
			padding: 0 5px;
			margin: 0 5px;
			color: #666;
			background: #fff;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 26px;
			height: 26px;
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
				border: 1px solid #eff3f9;
				padding: 0 4px;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 26px;
				border-radius: 0px;
				background: none;
				text-align: center;
				min-width: 30px;
				height: 26px;
			}
			// 数字悬浮
			.number:hover {
				cursor: pointer;
				border: 1px solid #e6e6e6;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 26px;
				border-radius: 0px;
				background: #8d6ec8;
				text-align: center;
				min-width: 30px;
				height: 26px;
			}
			// 选中
			.number.is-active {
				cursor: default;
				border: 1px solid #e6e6e6;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 26px;
				border-radius: 0px;
				background: #8d6ec8;
				text-align: center;
				min-width: 30px;
				height: 26px;
			}
		}
		// sizes
		:deep(.el-pagination__sizes) {
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
			.el-select {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0;
				color: #606266;
				display: inline-block;
				font-size: 13px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
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
				color: #606266;
				display: inline-block;
				font-size: 14px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: auto;
				text-align: center;
				height: 28px;
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
</style>
