<template>
	<router-view />
</template>
<script>
	const debounce = (fn, delay) => {
		let timer = null;
		return function() {
			let context = this;
			let args = arguments;
			clearTimeout(timer);
			timer = setTimeout(function() {
				fn.apply(context, args);
			}, delay);
		}
	}

	const _ResizeObserver = window.ResizeObserver;
	window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
		constructor(callback) {
			callback = debounce(callback, 16);
			super(callback);
		}
	}
</script>
<style lang="scss">
	body {
		margin: 0;
	}
	* {
		box-sizing: border-box;
	}
	.app-contain {
		width: 100%;
		// padding: 0 8%;
		// box-sizing: border-box;
	}
	.section_title {
		border: 0px solid #ddd;
		border-radius: 0px;
		padding: 0 0 0 80px;
		margin: 20px 0;
		color: #fff;
		background: url(http://clfile.zggen.cn/20231229/c16b1e8143674870b2388c54506a0997.png) no-repeat left center / 99% 100%;
		font-weight: 500;
		letter-spacing: 2px;
		width: 100%;
		font-size: 16px;
		line-height: 40px;
		text-align: left;
	}

	#app {
		font-family: Avenir, Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		// text-align: center;
		color: #2c3e50;
	}

	nav {
		padding: 30px;

		a {
			font-weight: bold;
			color: #2c3e50;

			&.router-link-exact-active {
				color: #42b983;
			}
		}
	}
</style>