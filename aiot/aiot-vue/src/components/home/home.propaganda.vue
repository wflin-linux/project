<template>
	<div class="homeComponent companyIntroduce" style="padding-top: 8%;">
		<div>
			<!-- 标题 -->
			<!-- <h2 class="homeComponent__title">{{ $t('home.introduce.title') }}</h2> -->
			<h3 class="homeComponent__titleSmall mytitle">
				<el-divider>
					<div style="font-size: 27px;">
						宣传视频
					</div>

				</el-divider>
			</h3>
			<br />

			<div style="width: 800px;margin: auto;margin-bottom: 4%;">
				<video width="800px" height="500px" controls autoplay loop src="../../assets/video/propaganda.mp4"
				style="text-align: center;"></video>
			</div>


		</div>
	</div>
</template>

<script>
	// import { getCompanyInfo } from '../../assets/apis/index.js';
	export default {
		data() {
			return {
				// companyLogo: null,
			};
		},
		//  mounted() {
		//    let companyInfo = getCompanyInfo();

		// console.log(companyInfo)

		//    if (companyInfo) {
		//      this.companyLogo = companyInfo;
		//   console.log(this.companyLogo)
		//    }
		//  },
	};
</script>

<style lang="scss" scoped>
	.companyIntroduce {

		// padding: 30px 0 30px;
		// &__title {
		//   font-size: 36px;
		//   color: #333;
		//   margin-bottom: 5px;
		// }
		&__brief {
			text-align: center;

			&>p {
				display: inline-block;
				font-size: 18px;
				color: #777;
				max-width: 1000px;
			}
		}

		.fs16 {
			font-size: 16px;
		}

	}

	// .mytitle{
	// 	font-size: 30px;
	// }
</style>
