<template>
  <div class="homeComponent companyIntroduce" style="height: 500px;">
    <div>
      <!-- 标题 -->
      <!-- <h2 class="homeComponent__title">{{ $t('home.introduce.title') }}</h2> -->
      <h3 class="homeComponent__titleSmall mytitle">
        <el-divider>
			<div style="font-size: 27px;">
				AIOT实验室
			</div>
		
		</el-divider>
      </h3>
	  <br />
      <!-- 简介 -->
      <div class="companyIntroduce__brief mb25">
        <p style="line-height: 150%;">
          AIOT智慧物联协会是目前整个科成投资最大，设备最先进的协会，
		  实验室内有价值百万的设备供研发使用。协会在校内设有专属会议室
		  ，同时在校外国腾科技园内设有实验室与办公区，为你提供舒适的学习和办公环境。
		  协会主要研发方向为人工智能与物联网，在这里不光可以学习专业知识，还可以结识更多的同学，
		  为你美好的大学生活打下良好的基础。这里给你施展才华的舞台，为你提供心灵恬静的港湾，
		  还能携手挑战专属的国家级比赛，给你思考、动手合作的体验。在进入大学后，要激活第二身份，
		  也就是加入协会或者是担任班干部，选择AIOT智慧物联协会绝对是一个不错的选择，这里有更优越的条件，
		  有学长学姐带你走向成功，使你拥有更加丰富多彩的大学生活，AIOT智慧物联协会欢迎你的加入!
        </p>
      </div>
	  <br />
	  <br />
      <!-- 宗旨 -->
     <!-- <p class="homeComponent__tenet ">
        商信诺人寿的使命是帮助我们的客户更健康，更幸福，更有保障！
      </p> -->
      <!-- 商标 -->
      <img
		src="../../assets/img/AIOT_logo.png"
        class="mb5 companyLogo mb15"
        alt=""
        style="width:400px;margin-left: 32%;"
      />
      <!-- <el-image
        :src=""
      
       
      ></el-image> -->
      <!-- 了解更多 -->
      <!-- <div>
        <el-link type="primary" class="fs16" href="/#/app/aboutUs/index">{{
          $t('common.more')
        }}</el-link>
      </div> -->
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
    & > p {
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
