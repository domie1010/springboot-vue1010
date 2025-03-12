<template>
	<div class="home_view">
		<div class="projectTitle">欢迎使用 {{projectName}}</div>
		<div class="count_list">
			<el-collapse-transition v-if="btnAuth('tiezi','首页总数')">
				<el-card v-show="countTypeList.closetieziCountType" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								帖子
							</div>
							<div class="card_head_right">
								<el-icon @click="countTypeClick('hiddentieziCountType')" class="showIcons"
									:class="countTypeList.hiddentieziCountType?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="countTypeClick('closetieziCountType')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
							
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddentieziCountType">
							<div class="count_title">帖子总数</div>
							<div class="count_num">{{tieziCount}}</div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('jubaoxinxi','首页总数')">
				<el-card v-show="countTypeList.closejubaoxinxiCountType" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								举报信息
							</div>
							<div class="card_head_right">
								<el-icon @click="countTypeClick('hiddenjubaoxinxiCountType')" class="showIcons"
									:class="countTypeList.hiddenjubaoxinxiCountType?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="countTypeClick('closejubaoxinxiCountType')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
							
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddenjubaoxinxiCountType">
							<div class="count_title">举报信息总数</div>
							<div class="count_num">{{jubaoxinxiCount}}</div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('yonghu','首页总数')">
				<el-card v-show="countTypeList.closeyonghuCountType" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								用户
							</div>
							<div class="card_head_right">
								<el-icon @click="countTypeClick('hiddenyonghuCountType')" class="showIcons"
									:class="countTypeList.hiddenyonghuCountType?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="countTypeClick('closeyonghuCountType')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
							
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddenyonghuCountType">
							<div class="count_title">用户总数</div>
							<div class="count_num">{{yonghuCount}}</div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('yonghufankui','首页总数')">
				<el-card v-show="countTypeList.closeyonghufankuiCountType" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								用户反馈
							</div>
							<div class="card_head_right">
								<el-icon @click="countTypeClick('hiddenyonghufankuiCountType')" class="showIcons"
									:class="countTypeList.hiddenyonghufankuiCountType?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="countTypeClick('closeyonghufankuiCountType')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
							
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddenyonghufankuiCountType">
							<div class="count_title">用户反馈总数</div>
							<div class="count_num">{{yonghufankuiCount}}</div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>
		<div class="card_list">
			<el-collapse-transition v-if="btnAuth('tiezi','首页统计')">
				<el-card v-show="cardTypeList.closetieziChartType1" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								帖子
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddentieziChartType1')" class="showIcons"
									:class="cardTypeList.hiddentieziChartType1?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closetieziChartType1')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddentieziChartType1">
							<div id="tiezineirongfenleiEchart1" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('tiezi','首页统计')">
				<el-card v-show="cardTypeList.closetieziChartType2" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								帖子
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddentieziChartType2')" class="showIcons"
									:class="cardTypeList.hiddentieziChartType2?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closetieziChartType2')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddentieziChartType2">
							<div id="tiezifabushijianEchart2" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>
	</div>
</template>

<script setup>
	import {
		inject,
		nextTick,
		ref,
		getCurrentInstance
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context.$project.projectName
	const countTypeList = ref({
	})
	const getCountList=()=>{
		countTypeList.value.closetieziCountType = true
		countTypeList.value.hiddentieziCountType = true
		if(btnAuth('tiezi','首页总数')){
			gettieziCount()
		}
		countTypeList.value.closejubaoxinxiCountType = true
		countTypeList.value.hiddenjubaoxinxiCountType = true
		if(btnAuth('jubaoxinxi','首页总数')){
			getjubaoxinxiCount()
		}
		countTypeList.value.closeyonghuCountType = true
		countTypeList.value.hiddenyonghuCountType = true
		if(btnAuth('yonghu','首页总数')){
			getyonghuCount()
		}
		countTypeList.value.closeyonghufankuiCountType = true
		countTypeList.value.hiddenyonghufankuiCountType = true
		if(btnAuth('yonghufankui','首页总数')){
			getyonghufankuiCount()
		}
	}
	const tieziCount = ref(0)
	const gettieziCount = () => {
		context?.$http({
			url:'tiezi/count',
			method: 'get'
		}).then(res=>{
			tieziCount.value = res.data.data
		})
	}
	const jubaoxinxiCount = ref(0)
	const getjubaoxinxiCount = () => {
		context?.$http({
			url:'jubaoxinxi/count',
			method: 'get'
		}).then(res=>{
			jubaoxinxiCount.value = res.data.data
		})
	}
	const yonghuCount = ref(0)
	const getyonghuCount = () => {
		context?.$http({
			url:'yonghu/count',
			method: 'get'
		}).then(res=>{
			yonghuCount.value = res.data.data
		})
	}
	const yonghufankuiCount = ref(0)
	const getyonghufankuiCount = () => {
		context?.$http({
			url:'yonghufankui/count',
			method: 'get'
		}).then(res=>{
			yonghufankuiCount.value = res.data.data
		})
	}
	const countTypeClick = (e) => {
		countTypeList.value[e] = !countTypeList.value[e]
	}
	const init=()=>{
		getCountList()
		getCardList()
	}
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	let echarts = inject("echarts")
	const cardTypeClick = (e) =>{
		cardTypeList.value[e] = !cardTypeList.value[e]
		setTimeout(()=>{
			getCardList()
		},1000)
	}
	const cardTypeList = ref({
		closetieziChartType1: true,
		hiddentieziChartType1: true,
		closetieziChartType2: true,
		hiddentieziChartType2: true,
	})
	const getCardList = () => {
		if(btnAuth('tiezi','首页统计')){
			gettieziChart1()
		}
		if(btnAuth('tiezi','首页统计')){
			gettieziChart2()
		}
	}
	const gettieziChart1 = () => {
		nextTick(()=>{
			var neirongfenleiEchart1 = echarts.init(document.getElementById("tiezineirongfenleiEchart1"),'macarons');
			context?.$http({
				url: "tiezi/group/neirongfenlei",
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let pArray = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].neirongfenlei);
				    yAxis.push(parseFloat((res[i].total)));
				    pArray.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].neirongfenlei
				    })
				}
				var option = {};
                option = {
                    title: {
                        text: '各内容分类数据统计',
                        left: 'center'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    tooltip: {
						trigger: 'item',
						formatter: '{b} : {c} ({d}%)'
                    },
                    series: [
                        {
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: pArray,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
				neirongfenleiEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				neirongfenleiEchart1.setOption(option);
				//根据窗口的大小变动图表
				neirongfenleiEchart1.resize();
			})
		})
	}
	const gettieziChart2 = () => {
		nextTick(()=>{
			var fabushijianEchart2 = echarts.init(document.getElementById("tiezifabushijianEchart2"),'macarons');
			context?.$http({
				url: "tiezi/group/fabushijian",
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let pArray = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].fabushijian);
				    yAxis.push(parseFloat((res[i].total)));
				    pArray.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].fabushijian
				    })
				}
				var option = {};
                option = {
                    title: {
                        text: '日发帖量数据统计',
                        left: 'center'
                    },
                    tooltip: {
                      trigger: 'item',
                      formatter: '{b} : {c}'
                    },
                    xAxis: {
                        type: 'category',
                        data: xAxis,
                        axisLabel: {
                            rotate: 40
                        }
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: yAxis,
                        type: 'bar'
                    }]
                };
				// 使用刚指定的配置项和数据显示图表。
				fabushijianEchart2.clear()
				fabushijianEchart2.setOption(option);
				//根据窗口的大小变动图表
				fabushijianEchart2.resize();
			})
		})
	}
	init()
</script>
<style lang="scss">
	.projectTitle{
		padding: 20px 0;
		margin: 20px 0 20px;
		color: #333;
		font-weight: bold;
		display: flex;
		width: 100%;
		font-size: 30px;
		justify-content: center;
		align-items: center;
		height: auto;
	}

	.showIcons {
		transition: transform 0.3s;
		margin-right: 10px;
	}

	.showIcons1 {
		transform: rotate(-180deg);
	}
	
	// 总数盒子
	.count_list{
		padding: 0 0 20px;
		display: flex;
		width: 100%;
		justify-content: center;
		align-items: flex-start;
		flex-wrap: wrap;
		// 总数card
		.card_view {
			border: 3px ridge #eee;
			border-radius: 0;
			box-shadow: 0px 0px 0px rgba(0,0,0,.12);
			margin: 0 1% 20px;
			width: 23%;
			box-sizing: border-box;
			height: auto;
			// card头部
			.el-card__header {
				border: 3px ridge #eee;
				background: linear-gradient(180deg, rgba(255,255,255,.6) 80%, rgba(202,210,231,1) 100%);
				width: 100%;
				border-width: 0 0 0px;
				// 头部盒子
				.index_card_head {
					display: flex;
					width: 100%;
					justify-content: space-between;
					align-items: center;
					height: 10px;
					// 标题
					.card_head_title {
						color: #333;
						font-size: 14px;
					}
					// 按钮盒子
					.card_head_right {
						display: flex;
						align-items: center;
						// 按钮
						.el-icon {
							cursor: pointer;
							color: #333;
							font-size: 20px;
						}
					}
				}
			}
			// body
			.el-card__body {
				padding: 0;
				background: linear-gradient(30deg, rgba(227,231,242,1) 0%, rgba(255,255,255,1) 20%, rgba(255,255,255,1) 80%, rgba(227,231,242,1) 100%);
				border-color: #eee;
				border-width: 0 1px 1px;
				border-style: solid;
				// body盒子
				.count_item{
					padding: 30px;
					flex-direction: column;
					display: flex;
					justify-content: center;
					align-items: center;
					text-align: center;
					// 总数标题
					.count_title{
						margin: 0 0px 0 0;
						color: #666;
						font-weight: 500;
						display: inline-block;
						font-size: 16px;
						line-height: 1.5;
					}
					// 总数数字
					.count_num{
						color: #c00;
						font-weight: 600;
						display: inline-block;
						font-size: 26px;
						line-height: 1.5;
					}
				}
			}
		}
	}
	// 首页盒子
	.home_view {
		padding: 0px 20px;
		margin: 0;
		background: url(http://clfile.zggen.cn/20240203/09a98f4852104a599055d47953ef0a23.png) no-repeat center top / 100% 100% !important;
		width: 100%;
		min-height: 100vh;
		height: auto;
	}
	// 统计图盒子
	.card_list {
		padding: 0 0 20px;
		display: flex;
		width: 100%;
		justify-content: space-between;
		align-items: flex-start;
		flex-wrap: wrap;
		// 统计图card
		.card_view {
			border: 3px ridge #eee;
			border-radius: 0;
			box-shadow: 0px 0px 0px rgba(0,0,0,.12);
			margin: 10px;
			width: calc(50% - 20px);
			box-sizing: border-box;
			height: auto;
			// 头部
			.el-card__header {
				border: 0px solid #eee;
				background: linear-gradient(180deg, rgba(255,255,255,.6) 80%, rgba(202,210,231,1) 100%);
				width: 100%;
				border-width: 0 0 0px;
				// 头部盒子
				.index_card_head {
					display: flex;
					width: 100%;
					justify-content: space-between;
					align-items: center;
					height: 10px;
					// 标题
					.card_head_title {
						color: #333;
						font-size: 14px;
					}
					// 按钮盒子
					.card_head_right {
						display: flex;
						align-items: center;
						// 按钮
						.el-icon{
							cursor: pointer;
							color: #333;
							font-size: 20px;
						}
					}
				}
			}
			// body
			.el-card__body {
				padding: 0;
				background: linear-gradient(30deg, rgba(227,231,242,1) 0%, rgba(255,255,255,1) 20%, rgba(255,255,255,1) 80%, rgba(227,231,242,1) 100%);
				border-color: #eee;
				border-width: 0 1px 1px;
				border-style: solid;
				// body盒子
				.card_item{
					padding: 10px;
					text-align: center;
				}
			}
		}
	}
</style>
