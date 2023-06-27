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
			<p class="homeComponent__tenet mb15" style="font-size: 18px; line-height: 150%; margin: 6%;">
				校内实验室基础硬件资源池包括物联网实训平台、智慧农场、智慧家具
				、智能机械狗和python小车等一系列的与智慧物联网有关的设备，
				其中物联网实训平台上包括的组件有智慧网关、温湿度采集器、智慧门锁等，
				物联网实训平台上的所有组件均可与智慧网关链接，通过网关控制实训平台上所有的组件，
				也可通过温湿度采集器实现风扇的开关等。此外，还有操作相对简单的python小车和智能机械狗
				，均可通过模块化编程实现。智慧农场有着与物联网实训平台相似的控制中枢，
				均可由学生自行编写控制代码来实现不同的功能。在应用方面，
				我们会集成更多的人工智能与物联网领域的相关应用，
				智能音箱、人脸识别、图像识别、语音合成、无人驾驶等等：
				围绕机械电子设备和智能制造领域与信息化智能化相关主题，以提高企业数宇化、
				信息化与智能化水平为目标开展相关研究，为学生提供简单化的实验教学过程企业级综合项目实战。
			</p>
			
			<!-- 产品轮播展示 -->
			<div v-loading="loading" style="min-height:150px">
				<el-carousel :interval="30000" :height="'500px'" :indicator-position="'outside'"
					@change="carouselChange">
					<el-carousel-item v-for="item in cardCounts" :key="item">
						<div>
							<el-row :gutter="25">
								<el-col :xs="24" :sm="12" :md="7" v-for="item in imgPage" class="mb25 cursor il"
									style="margin-left: 16px;">
									<div>
										<img :src="item.image" class="mb5 companyLogo mb15" alt=""
											style="width:300px;height: 180px;margin-right: 50px;" />

										<p class="products__item__txt" style="text-align: center;">{{ item.name }}</p>
									</div>

								</el-col>
							</el-row>
						</div>
					</el-carousel-item>
				</el-carousel>
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
				imgPage: [],
				page1: [{
						image: require("../../assets/img/laboratory/01.png"),
						name: "01"
					},
					{
						image: require("../../assets/img/laboratory/02.png"),
						name: "02"
					},
					{
						image: require("../../assets/img/laboratory/03.png"),
						name: "03"
					},
					{
						image: require("../../assets/img/laboratory/07.jpg"),
						name: "04"
					},
					{
						image: require("../../assets/img/laboratory/08.jpg"),
						name: "05"
					},
					{
						image: require("../../assets/img/laboratory/09.jpg"),
						name: "06"
					}
				],
				page2: [{
						image: require("../../assets/img/laboratory/10.jpg"),
						name: "07"
					},
					{
						image: require("../../assets/img/laboratory/11.jpg"),
						name: "08"
					},
					{
						image: require("../../assets/img/laboratory/12.jpg"),
						name: "09"
					},
					{
						image: require("../../assets/img/laboratory/04.png"),
						name: "10"
					},
					{
						image: require("../../assets/img/laboratory/05.png"),
						name: "11"
					},
					{
						image: require("../../assets/img/laboratory/06.png"),
						name: "12"
					}
				]
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
			carouselChange() {
				let current = arguments[0];

				this.carouselIndex = current;
				this.imgPage = null;
				if (this.carouselIndex === 1) {

					this.imgPage = this.page2;
					// console.log(this.carouselIndex)
					// console.log(this.imgPage)

				} else {

					this.imgPage = this.page1;
					// console.log(this.carouselIndex)
					// console.log(this.imgPage)
				}
				// console.log(this.imgPage)
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
