<template>
	<div>
		<div class="forget_view">
			<el-form :model="forgetForm" class="forget_box">
				<div class="forget_title">中文社区交流平台{{pageType==1?'找回密码':pageType==2?'输入密保':'重置密码'}}</div>
				<div class="tab_line">
					<div class="line"></div>
					<div class="num_line">
						<div class="line_number" :class="pageType==1?'line_number1':'',pageType>1?'line_number2':''"><div class="number" v-if="pageType<2">1</div><el-icon v-else><Check /></el-icon></div>
						<div class="line_number" :class="pageType==2?'line_number1':'',pageType>2?'line_number2':''"><div class="number" v-if="pageType<3">2</div><el-icon v-else><Check /></el-icon></div>
						<div class="line_number" :class="pageType==3?'line_number1':''"><div class="number">3</div></div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const pageType = ref(1)
	const forgetForm = ref({})
	const userForm = ref({})
	//返回登录
	const close = () => {
		context?.$router.push({
			path: "/login"
		});
	}
</script>

<style lang="scss" scoped>
	.forget_view {
		background-repeat: no-repeat;
		flex-direction: column;
		background-size: cover;
		background: url(http://clfile.zggen.cn/20231229/e2095d3a74784da2bdff07be79797146.jpg);
		display: flex;
		min-height: 100vh;
		justify-content: center;
		align-items: center;
		position: relative;
		background-position: center center;
		// 表单盒子
		.forget_box {
			border-radius: 20px;
			padding: 30px 20px 40px;
			box-shadow: 0px 26px 36px -20px #083f5d;
			margin: 20px 0;
			flex-direction: column;
			background: #fff;
			display: flex;
			width: 600px;
			min-height: 600px;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			// 标题
			.forget_title {
				padding: 0px;
				margin: 0 0 20px;
				color: #0d6392;
				width: 100%;
				font-size: 18px;
				text-align: center;
			}
			// tab
			// 盒子
			.tab_line{
				padding: 30px 0;
				margin: 0 auto;
				flex-direction: column;
				display: flex;
				width: 80%;
				align-items: center;
				// 中间线
				.line{
				background: #ccc;
				width: 80%;
				height: 2px;
				}
				// item盒子
				.num_line{
					margin: -20px 0 0;
					display: flex;
					width: 80%;
					justify-content: space-between;
					align-items: center;
					height: 40px;
					// 默认样式
					.line_number{
						border: 2px solid #ddd;
						border-radius: 50%;
						color: #aaa;
						background: #eee;
						display: flex;
						width: 40px;
						font-size: 18px;
						justify-content: center;
						align-items: center;
						height: 40px;
					}
					// 选中样式
					.line_number1{
						color: rgba(64, 158, 255, 1);
						font-size: 22px;
						border-color: rgba(64, 158, 255, 1);
					}
					// 完成样式
					.line_number2{
						color: rgba(0, 170, 0, 1.0);
						border-color: rgba(0, 170, 0, 1.0);
					}
				}
			}
			// item
			.list_item {
				margin: 10px auto 20px;
				display: flex;
				width: 80%;
				justify-content: flex-start;
				align-items: center;
				// label
				.item_label {
					width: 120px;
					font-size: 14px;
					box-sizing: border-box;
					text-align: right;
				}
				// 输入框
				:deep(.list_inp) {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 10px;
					width: 300px;
					line-height: 36px;
					box-sizing: border-box;
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
				//下拉框样式
				:deep(.list_sel) {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 10px;
					background: #fff;
					width: 300px;
					line-height: 36px;
					box-sizing: border-box;
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
			// 按钮盒子
			.list_btn {
				margin: 20px 0 0;
				display: flex;
				width: 100%;
				justify-content: center;
				align-items: center;
				flex-wrap: wrap;
				// 获取密保
				:deep(.el-button--success) {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 30px;
					margin: 0 0 0 90px;
					color: #fff;
					background: linear-gradient(158deg, rgba(75,149,218,1) 49%, rgba(0,88,171,1) 100%),#0058ab;
					width: auto;
					font-size: 14px;
					min-width: 300px;
					height: 36px;
				}
				// 获取密保悬浮
				:deep(.el-button--success:hover) {
				}
				// 确认密保
				:deep(.el-button--primary) {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 30px;
					margin: 0 0 0 90px;
					color: #fff;
					background: linear-gradient(158deg, rgba(75,149,218,1) 49%, rgba(0,88,171,1) 100%),#0058ab;
					width: auto;
					font-size: 14px;
					min-width: 300px;
					height: 36px;
				}
				// 确认密保悬浮
				:deep(.el-button--primary:hover) {
				}
				// 重置密码
				:deep(.el-button--warning) {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 30px;
					margin: 0 0 0 90px;
					color: #fff;
					background: linear-gradient(158deg, rgba(75,149,218,1) 49%, rgba(0,88,171,1) 100%),#0058ab;
					width: auto;
					font-size: 14px;
					min-width: 300px;
					height: 36px;
				}
				// 重置密码悬浮
				:deep(.el-button--warning:hover) {
				}
				.r-login {
					cursor: pointer;
					padding: 20px 10% 0;
					color: #999;
					width: 90%;
					font-size: 14px;
					text-align: right;
				}
			}
		}
	}
</style>
