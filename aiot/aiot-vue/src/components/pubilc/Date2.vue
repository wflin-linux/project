<template>
  <div class="container">
    <div class="dateTimeBox yearBox isActive">{{nowYear}}年</div>
    <div id="monthArea" ref="monthArea">
      <div class="dateTimeBox" ref="monthBox" v-for="(item,index) in monthList" :key=""
        :class="{'isActive': item.isActive}">{{item.value}}
      </div>
    </div>
    <div id="dateArea" ref="dateArea">
      <div class="dateTimeBox" ref="dateBox" v-for="(item,index) in dateList" :key=""
        :class="{'isActive': item.isActive}">{{item.value}}</div>
    </div>
    <div id="hourArea" ref="hourArea">
      <div class="dateTimeBox" ref="hourBox" v-for="(item,index) in hourList" :key=""
        :class="{'isActive': item.isActive}">{{item.value}}</div>
    </div>
    <div id="minuteArea" ref="minuteArea">
      <div class="dateTimeBox" ref="minuteBox" v-for="(item,index) in minuteList" :key=""
        :class="{'isActive': item.isActive}">{{item.value}}
      </div>
    </div>
    <div id="secondArea" ref="secondArea">
      <div class="dateTimeBox" ref="secondBox" v-for="(item,index) in secondList" :key=""
        :class="{'isActive': item.isActive}">{{item.value}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      nowYear: '',//当前年份
      monthList: [],//月份数组
      dateList: [],//日期数组
      hourList: [],//小时数组
      minuteList: [],//分钟数组
      secondList: [],//秒钟数组
    }
  },
  mounted() {
    var that = this;
    that.initTimeList();
  },
  methods: {
    //设置刻度
    initTimeList() {
      var that = this;
      for (var i = 1; i < 13; i++) {
        that.monthList.push({
          value: i + "月",
          isActive: false
        });
      }
      for (var i = 1; i < 32; i++) {
        that.dateList.push({
          value: i + "日",
          isActive: false
        });
      }
      for (var i = 0; i < 24; i++) {
        that.hourList.push({
          value: i + "时",
          isActive: false
        });
      }
      for (var i = 0; i < 60; i++) {
        that.minuteList.push({
          value: i + "分",
          isActive: false
        });
      }
      for (var i = 0; i < 60; i++) {
        that.secondList.push({
          value: i + "秒",
          isActive: false
        });
      }
      that.$nextTick(function () {
        that.initData();
      });
    },
    //初始化样式
    initData() {
      var that = this;
      //初始化月份样式
      that.setRoundStyle(that.$refs.monthBox, 60, 30);
      //初始化日期样式
      that.setRoundStyle(that.$refs.dateBox, 120, 360 / 31);
      //初始化小时样式
      that.setRoundStyle(that.$refs.hourBox, 180, 15);
      //初始化分钟样式
      that.setRoundStyle(that.$refs.minuteBox, 240, 6);
      //初始化秒钟样式
      that.setRoundStyle(that.$refs.secondBox, 300, 6);
      that.setNowDate();
      that.run();
    },
    /**
     * 设置圆圈样式
     * dataList:刻度数组, radius:半径, eachDeg:每个刻度间的度数
     */
    setRoundStyle(dataList, radius, eachDeg) {
      for (var i = 0; i < dataList.length; i++) {
        var translateX = Math.round(Math.cos(i * eachDeg * (Math.PI / 180)) * 1000000) / 1000000 * radius;
        var translateY = Math.round(Math.sin(i * eachDeg * (Math.PI / 180)) * 1000000) / 1000000 * radius;
        var rotateDeg = i * eachDeg;
        dataList[i].style.transform = "translate(" + translateX + "px, " + translateY + "px) rotate(" + rotateDeg + "deg)";
      }
    },
    //设置当前时间
    setNowDate() {
      var that = this;
      var nowDate = new Date();
      //当前年份
      that.nowYear = nowDate.getFullYear();
      //当前月份
      that.rotateBox(that.$refs.monthArea, nowDate.getMonth() * (-360 / 12), that.monthList[nowDate.getMonth()], nowDate.getMonth() - 1 < 0 ? that.monthList[that.monthList.length - 1] : that.monthList[nowDate.getMonth() - 1]);
      //当前日期
      that.rotateBox(that.$refs.dateArea, (nowDate.getDate() - 1) * (-360 / 31), that.dateList[nowDate.getDate() - 1], nowDate.getDate() - 2 < 0 ? that.dateList[that.dateList.length - 1] : that.dateList[nowDate.getDate() - 2]);
      //当前小时
      that.rotateBox(that.$refs.hourArea, nowDate.getHours() * (-360 / 24), that.hourList[nowDate.getHours()], nowDate.getHours() - 1 < 0 ? that.hourList[that.hourList.length - 1] : that.hourList[nowDate.getHours() - 1]);
      //当前分钟
      that.rotateBox(that.$refs.minuteArea, nowDate.getMinutes() * (-360 / 60), that.minuteList[nowDate.getMinutes()], nowDate.getMinutes() - 1 < 0 ? that.minuteList[that.minuteList.length - 1] : that.minuteList[nowDate.getMinutes() - 1]);
      //当前秒钟
      that.rotateBox(that.$refs.secondArea, nowDate.getSeconds() * (-360 / 60), that.secondList[nowDate.getSeconds()], nowDate.getSeconds() - 1 < 0 ? that.secondList[that.secondList.length - 1] : that.secondList[nowDate.getSeconds() - 1]);
    },
    /**
     * 旋转刻度
     * dateTimeArea:要旋转的区域，rotateDeg：转到当前时间所需要旋转的度数，dateTimeBox：当前时间刻度，lastdateTimeBox：前一个高亮的刻度
     */
    rotateBox(dateTimeArea, rotateDeg, dateTimeBox, lastdateTimeBox) {
      var that = this;
      dateTimeArea.style.transform = "rotate(" + rotateDeg + "deg)";
      dateTimeBox.isActive = true;
      lastdateTimeBox.isActive = false;
    },
    //动起来
    run() {
      var that = this;
      var timer;
      clearInterval(timer);
      timer = setInterval(function () {
        that.setNowDate();
      }, 1000);
    }
  }
}
</script>

<style scoped>
.container {
  width: 700px;
  height: 700px;
  position: relative;
  background-color: #000;
  color: #666;
}
.dateTimeBox {
  position: absolute;
  width: 80px;
  height: 20px;
  text-align: center;
  top: 50%;
  margin-top: -10px;
  left: 50%;
  margin-left: -40px;
}
#monthArea,
#dateArea,
#hourArea,
#minuteArea,
#secondArea {
  position: absolute;
  width: 100%;
  height: 100%;
  transition: transform 1s;
}
.isActive {
  color: #fff;
}
</style>
