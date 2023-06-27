<template>
  <el-header style="margin: 0; padding: 0">
    <div class="navHead">
      <span class="textTitle">{{value}}</span>
    </div>
  </el-header>
</template>

<script>
  export default {
  	data() {
  		return {
  			value: 'title'
  		};
  	},
}
  
</script>

<style scoped>
  .navHead{
    margin-top:10px,0,0,0 ;
    border: 1px red solid;
    background-color: skyblue;
  }
  .textTitle{
    
  }
  
</style>