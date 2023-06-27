<template>
  <el-calendar v-model="value" class="Calendar" style="padding: 0;margin: 0;"></el-calendar>
</template>
<script>
  export default {
    data() {
      return {
        value: new Date(),
      };
    },
  };
</script>
<style lang="scss">
  .mainBox {
    margin-left: 0;
    padding: 0;
    width: 810%;
    height:20%;

    .wh_content_all {
      background-color: #08214d;

      .wh_top_changge li {
        color: #1fb331;
      }

      .wh_content_item {
        .wh_item_date {
          color: #1ae558;
        }

        .wh_other_dayhide {
          color: #bfbfbf;
        }

        .wh_chose_day {
          background: #d1ff19;
          color: #0d0b0b;
        }

        .wh_isToday {
          background: #33ad53;
          color: #0d0b0b;
        }
      }
    }
  }
</style>
