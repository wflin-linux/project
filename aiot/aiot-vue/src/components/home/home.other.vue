<template>
	<div class="homeComponent homeProducts" style="padding-top: 8%;">
		<div>
			<!-- 标题 -->
			<!-- <h2 class="homeComponent__title">{{ $t('home.products.title') }}</h2> -->
			<h3 class="homeComponent__titleSmall">
				<el-divider>
					<div style="font-size: 27px;">
						实验室风采
					</div>

				</el-divider>
			</h3>

			<!-- 宗旨 -->
			<p class="homeComponent__tenet mb15" style="font-size: 18px; line-height: 150%; margin: 6%;margin-bottom: 0%;">
				办公区：可供新生教学、管理层办公和值班人员值班，同时协会培训和一些教学课程以及会议将在相应办公区进行。
			</p>
			<p class="homeComponent__tenet mb15" style="font-size: 18px; line-height: 150%; margin: 6%;margin-top: 2%;">
	
				实验区：基础设备有人工智能相关的器械，其中包括越疆魔术师机械臂、越疆m1机械臂、
				光固化打印机、智能服务机器人、机床等一系列的机器。其中越疆的魔术师机械臂和越疆的
				m1机械臂均有对应的比赛，其中越疆的m1机械臂的为国际类赛事，成绩达到要求即可获得盖
				有一带一路官方盖章的技能证书。实验室将打造数字化与信息化的高度融合，为实现智能制
				造奠定基础，并且通过实践学习，使学生掌握必要的科技知识，激发学生对科学技术的兴趣
				，提高学生的创新意识和实践能力，引导学生树立科学思想、科学态度，逐步形成科学的世界
				观和方法论，了解国内外科学技术发展的状况，为今后创造性地参与科技活动和社会实践打下基础。
			</p>
			
			<div style="margin-bottom: 20px">
				<video width="300px" height="187px" controls src="../../assets/video/01.mp4" style="margin-left: 16%;"></video>
				<video width="300px" height="187px" controls src="../../assets/video/02.mp4" style="margin-left: 5%;"></video>
				<video width="300px" height="187px" controls src="../../assets/video/03.mp4" style="margin-left: 16%;"></video>
				<video width="300px" height="187px" controls src="../../assets/video/04.mp4" style="margin-left: 5%;"></video>
				
				<!-- <video width="300px" height="187px" controls src="../../assets/video/05.mp4" style="margin-left: 3%;"></video> -->
				 <video width="300px" height="187px" controls src="../../assets/video/06.mp4" style="margin-left: 16%;"></video>
				<video width="300px" height="187px" controls src="../../assets/video/07.mp4" style="margin-left: 5%;"></video>
				<video width="300px" height="187px" controls src="../../assets/video/08.mp4" style="margin-left: 16%;"></video>
				<video width="300px" height="187px" controls src="../../assets/video/09.mp4" style="margin-left: 5%;"></video>
				<video width="300px" height="187px" controls src="../../assets/video/10.mp4" style="margin-left: 16%;"></video>
				<video width="300px" height="187px" controls src="../../assets/video/propaganda.mp4" style="margin-left: 5%;"></video>
				
			</div>
		</div>
	</div>
</template>

<script>
	import {
		getProductList,
		getProductTypesList
	} from '../../assets/apis/index.js';
	import {
		productsDetialMixin
	} from '../products/products.mix.js';
	export default {
		mixins: [productsDetialMixin],
		data() {
			return {
				productsTypeList: [],
				productsType: '',
				carouselIndex: 0,
				productsListAll: [],
				cardCounts: 2,
				loading: false,
				loading1: false,
				isShow: false,
				
			};
		},
		computed: {
			// productsList:{
			//      get(){
			//          return this.page1;
			//      },
			//      set(v){
			//          this.stepMap = v
			//      }
			//  }

			productsList() {
				// return this.productsListAll.slice(
				//   this.carouselIndex * 8,
				//   (this.carouselIndex + 1) * 8,
				// );
				return this.page1;
			},
		},
		mounted() {
			this.imgPage = this.page1;
			this.get();
		},
		methods: {
			async get() {
				// this.loading1 = true;
				// this.productsTypeList = await getProductTypesList();
				// this.loading1 = false;
				// this.productsTypeList.length = 3;
				// if (!this.productsTypeList.length) {
				//   return;
				// }
				// this.productsType = this.productsTypeList[0].id;
				// this.getProducts(this.productsType);
			},
			
			async getProducts(type = this.productsType) {
				this.loading = true;
				this.productsListAll = (
					await getProductList({
						page: 1,
						pageSize: 32,
						type: type,
					})
				).data;
				this.loading = false;

				this.cardCounts = parseInt(this.productsListAll.length / 8) + 1;
			},
		},
		watch: {
			'$i18n.locale'() {
				this.get();
			},
		},
	};
</script>

<style lang="scss">
	.homeProducts {
		$bgc: #f5f5f5;
		background-color: $bgc;

		.el-divider__text {
			background-color: $bgc;
		}

		.il .el-image__inner {
			display: inline-block;
			vertical-align: middle;
			height: inherit;
			height: initial;
		}
	}
</style>
