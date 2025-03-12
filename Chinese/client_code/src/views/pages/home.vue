<template>
	<div>
		<div class="home_box">
			<!-- 帖子首页展示 -->
			<div class="homeList_view">
				<div class="homeList_title">帖子展示</div>
				<div class="home_list_two">
					<mySwiper :data="tieziHomeList" :type="3"
						:loop="true"
						:navigation="true"
						:pagination="false"
						:paginationType="2"
						:scrollbar="false"
						:slidesPerView="4"
						:spaceBetween="30"
						:autoHeight="false"
						:centeredSlides="false"
						:freeMode="true"
						:effectType="0"
						:direction="horizontal"
						:autoplay="false"
						:slidesPerColumn="1">
						<template #default="scope">
							<div class="home_item animation_box" @click="detailClick('tiezi',scope.row.id)">
								<div class="home_img_box">
									<img class="home_img" v-if="isHttp(scope.row.fengmian)" :src="scope.row.fengmian.split(',')[0]" alt="">
									<img class="home_img" v-else :src="scope.row.fengmian?$config.url + scope.row.fengmian.split(',')[0]:''" alt="">
								</div>
								<div class="home_content">
									<div class='home_title'>
										标题：{{scope.row.biaoti}}
									</div>
								</div>
							</div>
						</template>
					</mySwiper>
				</div>
				<div class="homeList_more_view" @click="moreClick('tiezi')">
					<span class="homeList_more_text">LEARN MORE</span>
				</div>
			</div>
		</div>
	</div>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance
	} from 'vue';
	import {
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	//帖子首页展示
	const tieziHomeList = ref([])
	const gettieziHomeList = () => {
		context?.$http({
			url: 'tiezi/list',
			method: 'get',
			params: {
				page: 1,
				limit: 12
			}
		}).then(res => {
			tieziHomeList.value = res.data.data.list
		})
	}
	//判断图片链接是否带http
	const isHttp = (str) => {
        return str && str.substr(0,4)=='http';
    }
	//跳转详情
	const detailClick = (table,id) => {
		router.push(`/index/${table}Detail?id=${id}`)
	}
	const moreClick = (table) => {
		router.push(`/index/${table}List`)
	}
	const init = () => {
		//帖子首页展示
		gettieziHomeList()
	}
	init()
</script>

<style lang="scss">
	.home_box {
		padding: 0;
		margin: 0 auto;
		overflow: hidden;
		color: #666;
		display: flex;
		width: 100%;
		font-size: 14px;
		justify-content: space-between;
		align-items: flex-start;
		flex-wrap: wrap;
	}
	
	// 推荐
	.recomList_view {
		padding: 50px 0 120px;
		margin: 0px 0 0;
		background: url(http://clfile.zggen.cn/20231229/5315f6ee2bd84beb9ef022dcf6f723aa.jpg) no-repeat center bottom,#fff;
		width: 100%;
		order: 3;
		.recomList_title {
			padding: 0 0 0 80px;
			margin: 20px auto 40px;
			color: #fff;
			background: url(http://clfile.zggen.cn/20231229/c16b1e8143674870b2388c54506a0997.png) no-repeat left center / 99% 100%;
			font-weight: 500;
			width: 80%;
			font-size: 16px;
			line-height: 36px;
			text-align: left;
			height: 36px;
		}
		// list
		.recommend_list_two {
			padding: 0 10%;
			display: flex;
			width: 100%;
			flex-wrap: wrap;
			.recommend_item {
				cursor: pointer;
				margin: 0;
				background: rgba(255,255,255,.8);
				width: 100%;
				position: relative;
				.recommend_img_box {
					cursor: pointer;
					margin: 0 0 5px;
					width: 100%;
					height: 320px;
					.recommend_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.recommend_content {
					background: none;
					width: 100%;
					.recommend_title {
						color: #333;
						font-weight: 500;
						width: 100%;
						font-size: 14px;
						line-height: 24px;
						text-align: left;
					}
					.recommend_price {
						color: #f00;
						width: 100%;
						font-size: 14px;
						text-align: right;
					}
				}
			}
		}
		// list
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
		// 更多
		.recommend_more_view {
			cursor: pointer;
			border: 1px solid #ddd;
			border-radius: 30px;
			padding: 8px 0;
			margin: 20px auto 0;
			color: #999;
			background: #f6f6f6;
			display: none;
			width: 120px;
			text-align: center;
			.recommend_more_text {
			}
		}
	}
	// 推荐
	// 新闻资讯
	.newsList_view {
		padding: 40px 0 0;
		margin: 0 auto;
		background: #fff;
		width: 100%;
		order: 4;

		.newsList_title {
			margin: 0 auto 20px;
			color: #666;
			background: none;
			font-weight: 500;
			width: 600px;
			font-size: 24px;
			text-align: center;
		}
		// list
		.news_list_two {
			padding: 0 10%;
			display: flex;
			width: 100%;
			flex-wrap: wrap;
			.news_item {
				margin: 0 10px;
				background: #fff;
				display: flex;
				width: 100%;
				justify-content: space-between;
				.news_img_box {
					width: 49%;
					height: 240px;
					.news_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.news_content {
					padding: 0 0 5px 10px;
					width: 49%;
					.news_title {
						overflow: hidden;
						color: #000;
						white-space: nowrap;
						font-weight: 500;
						width: 100%;
						font-size: 14px;
						line-height: 60px;
						text-overflow: ellipsis;
						text-align: left;
					}
					.news_text {
						overflow: hidden;
						text-indent: 2em;
						width: 100%;
						font-size: 14px;
						line-height: 24px;
						height: 96px;
					}
					.news_time {
						color: #999;
						width: 100%;
						line-height: 40px;
						text-align: right;
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
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
		// 更多
		.news_more_view {
			cursor: pointer;
			border: 1px solid #ddd;
			border-radius: 30px;
			padding: 8px 0;
			margin: 20px auto 0;
			color: #999;
			background: #f6f6f6;
			display: none;
			width: 120px;
			text-align: center;
			.news_more_text {
			}
		}
	}
	// 新闻资讯
	// 首页展示
	.homeList_view {
		padding: 30px 0 10px;
		margin: 0 auto;
		width: 100%;
		order: 1;

		.homeList_title {
			padding: 0 0 0 80px;
			margin: 20px auto 40px;
			color: #fff;
			background: url(http://clfile.zggen.cn/20231229/c16b1e8143674870b2388c54506a0997.png) no-repeat left center / 99% 100%;
			font-weight: 500;
			width: 80%;
			font-size: 16px;
			line-height: 36px;
			text-align: left;
			height: 36px;
		}
		// list
		.home_list_two {
			padding: 0 10%;
			margin: 20px auto;
			display: flex;
			width: 100%;
			flex-wrap: wrap;
			.home_item {
				margin: 0 10px;
				background: #fff;
				width: 100%;
				.home_img_box {
					width: 100%;
					height: 320px;
					.home_img {
						object-fit: cover;
						width: 100%;
						height: 100%;
					}
				}
				.home_content {
					.home_title {
						overflow: hidden;
						color: #000;
						white-space: nowrap;
						font-weight: 500;
						width: 100%;
						font-size: 14px;
						line-height: 40px;
						text-overflow: ellipsis;
						text-align: center;
					}
				}
			}
		}
		// list
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
		// 更多
		.homeList_more_view {
			cursor: pointer;
			border: 1px solid #ddd;
			border-radius: 30px;
			padding: 8px 0;
			margin: 20px auto;
			color: #999;
			background: #f6f6f6;
			display: none;
			width: 120px;
			text-align: center;
			.homeList_more_text {
			}
		}
	}
	// 首页展示
</style>