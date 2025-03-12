<template>
	<div>
		<div class="login_view">
			<div class="outTitle_view">
				<div class="outTilte">中文社区交流平台登录</div>
			</div>
			<el-form :model="loginForm" class="login_form">
				<div class="list_item" v-if="loginType==1">
					<div class="list_label">
						账号：
					</div>
					<input class="list_inp" v-model="loginForm.username" placeholder="请输入账号" />
				</div>
				<div class="list_item" v-if="loginType==1">
					<div class="list_label">
						密码：
					</div>
					<input class="list_inp" v-model="loginForm.password" type="password" placeholder="请输入密码" @keydown.enter.native="handleLogin"  />
				</div>
				<div class="list_type" v-if="userList.length>1">
					<div class="list_label">
						用户类型：
					</div>
				  <el-select v-model="loginForm.role" placeholder="请选择用户类型">
				    <el-option v-for="(item,index) in userList" :label="item.roleName" :value="item.roleName"></el-option>
				  </el-select>
				</div>
				<div class="remember_view" v-if="loginType==1">
					<el-checkbox v-model="rememberPassword" label="记住密码" size="large" :true-label="true"
						:false-label="false" />
				</div>
				<div class="btn_view">
					<el-button class="login" v-if="loginType==1" type="success" @click="handleLogin">登录</el-button>
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
		onMounted,
	} from "vue";
	const userList = ref([])
	const menus = ref([])
	const loginForm = ref({
		role: '',
		username: '',
		password: ''
	})
	const tableName = ref('')
	const loginType = ref(1)
	//是否记住密码
	const rememberPassword = ref(true)
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const handleLogin = () => {
		if (!loginForm.value.username) {
			context?.$toolUtil.message('请输入用户名', 'error')
			
			return;
		}
		if (!loginForm.value.password) {
			context?.$toolUtil.message('请输入密码', 'error')
			
			return;
		}
		if (userList.value.length > 1) {
			if (!loginForm.value.role) {
				context?.$toolUtil.message('请选择角色', 'error')
				verifySlider.value.reset()
				return;
			}
			for (let i = 0; i < menus.value.length; i++) {
				if (menus.value[i].roleName == loginForm.value.role) {
					tableName.value = menus.value[i].tableName;
				}
			}
		} else {
			tableName.value = userList.value[0].tableName;
			loginForm.value.role = userList.value[0].roleName;
		}
		login()
	}
	const login = () => {
		context?.$http({
			url: `${tableName.value}/login?username=${loginForm.value.username}&password=${loginForm.value.password}`,
			method: 'post'
		}).then(res => {
			//是否保存当前账号密码至缓存
			if (rememberPassword.value) {
				let loginForm1 = JSON.parse(JSON.stringify(loginForm.value))
				delete loginForm1.code
				context?.$toolUtil.storageSet("loginForm", JSON.stringify(loginForm1));
			} else {
				context?.$toolUtil.storageRemove("loginForm")
			}
			context?.$toolUtil.storageSet("Token", res.data.token);
			context?.$toolUtil.storageSet("role", loginForm.value.role);
			context?.$toolUtil.storageSet("sessionTable", tableName.value);
			context?.$toolUtil.storageSet("adminName", loginForm.value.username);
			context?.$router.push('/')
		}, err => {
		})
	}
	//获取菜单
	const getMenu=()=> {
      let params = {
        page: 1,
        limit: 1,
        sort: 'id',
      }

      context?.$http({
        url: "menu/list",
        method: "get",
        params: params
      }).then(res => {
          menus.value = JSON.parse(res.data.data.list[0].menujson)
          for (let i = 0; i < menus.value.length; i++) {
            if (menus.value[i].hasBackLogin=='是') {
              userList.value.push(menus.value[i])
            }
          }
			//获取缓存是否有保存的账号密码
			let form = context?.$toolUtil.storageGet('loginForm')
			if (form) {
				
			}else {
				loginForm.value.role = userList.value[0].roleName
			}
          context?.$toolUtil.storageSet("menus", JSON.stringify(menus.value));
      })
    }
	//初始化
	const init = () => {
		getMenu();
		//获取缓存是否有保存的账号密码
		let form = context?.$toolUtil.storageGet('loginForm')
		if (form) {
			loginForm.value = JSON.parse(form)
		}
	}
	onMounted(()=>{
		init()
		
	})
</script>

<style lang="scss" scoped>
	.login_view {
		background-repeat: no-repeat;
		flex-direction: column;
		background-size: 100% 100%!important;
		background: url(http://clfile.zggen.cn/20240413/7f0c2ebb13cf40f19448f505adafd523.jpg);
		display: flex;
		min-height: 100vh;
		justify-content: center;
		align-items: center;
		position: relative;
		background-position: center center;
		// 标题盒子
		.outTitle_view {
			padding: 0px;
			top: 0;
			left: 0;
			background: url(http://codegen.caihongy.cn/20230220/93da8688dea64d828ade3cf330fa6af5.png) no-repeat left bottom / auto 100%,url(http://codegen.caihongy.cn/20230220/567a2d3b7488444c893461a5b89e87e8.png) no-repeat right bottom / auto 100%,#0d3084;
			display: flex;
			width: 100%;
			line-height: 64px;
			justify-content: center;
			align-items: center;
			position: absolute;
			.outTilte {
				color: #fff;
				font-size: 26px;
			}
		}
		// 表单盒子
		.login_form {
			border: 10px outset #849acf;
			border-radius: 4px;
			padding: 40px 40px 20px 0;
			margin: 80px auto 40px;
			flex-direction: column;
			background: #fff;
			display: flex;
			width: 688px;
			justify-content: flex-start;
			flex-wrap: wrap;
		}
		// item盒子
		.list_item {
			margin: 0  auto 20px;
			display: flex;
			width: 90%;
			justify-content: flex-start;
			align-items: center;
			// label
			.list_label {
				color: #666;
				width: 110px;
				font-size: 14px;
				line-height: 36px;
				text-align: right;
			}
			// 输入框
			.list_inp {
				border: 3px ridge #eee;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				background: linear-gradient(30deg, rgba(227,231,242,1) 0%, rgba(255,255,255,1) 20%, rgba(255,255,255,1) 80%, rgba(227,231,242,1) 100%);
				width: calc(100% - 110px);
				line-height: 44px;
				height: 44px;
			}
		}
		.list_type {
			margin: 0px auto;
			display: flex;
			width: 90%;
			justify-content: flex-start;
			align-items: center;
			order: 3;
			.list_label {
				color: #666;
				background: none;
				width: 110px;
				font-size: 14px;
				line-height: 36px;
				text-align: right;
			}
			// 下拉框样式
			:deep(.el-select) {
				border: 3px ridge #eee;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				background: linear-gradient(30deg, rgba(227,231,242,1) 0%, rgba(255,255,255,1) 20%, rgba(255,255,255,1) 80%, rgba(227,231,242,1) 100%);
				width: calc(100% - 110px);
				font-size: 14px;
				line-height: 44px;
				box-sizing: border-box;
				height: 44px;
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
		// 记住密码样式
		.remember_view {
			padding: 0 0 0 110px;
			margin: 0  auto 20px;
			width: 90%;
			text-align: center;
		
			// 未选中样式
			:deep(.el-checkbox) {
				margin: 0;
				display: flex;
				width: 20%;
				justify-content: center;
				align-items: center;
				// 复选框
				.el-checkbox__inner {
					border: 1px solid #DCDFE6;
					background: #fff;
				}
				// 提示文字
				.el-checkbox__label{
					color: #999;
				}
			}
			// 选中样式
			:deep(.is-checked) {
				//复选框
				.el-checkbox__inner {
					background-color: #0d308460;
					border-color: #0d3084;
				}
				// 提示文字
				.el-checkbox__label{
					color: #0d3084;
				}
			}
		}
		// 按钮盒子
		.btn_view {
			padding: 0 0 0 110px;
			margin: 20px auto 0;
			display: flex;
			width: 90%;
			justify-content: flex-start;
			align-items: center;
			flex-wrap: wrap;
			order: 5;
			// 登录
			.login {
				border: 3px ridge rgba(93,83,181,1);
				cursor: pointer;
				border-radius: 2px;
				padding: 0 24px;
				margin: 0 10px 10px 0;
				color: #fff;
				background: linear-gradient(180deg, rgba(191,187,233,1) 0%, rgba(139,133,203,1) 50%, rgba(111,100,203,1) 51%, rgba(93,83,181,1) 100%);
				display: inline-block;
				letter-spacing: 4px;
				width: auto;
				font-size: 16px;
				height: 44px;
			}
		}
	}

</style>