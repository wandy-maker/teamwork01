<template>
  <div class="wrapper">
    <div class="header-box">
      <header>
        <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
        <p>反馈任务列表</p>
        <i></i>
      </header>
      <div class="top-ban"></div>
      <img src="../assets/header.png" />
    </div>

    <ul>
      <li v-for="aqiFeedback in aqiFeedbackArr" :key="aqiFeedback.afId">
        <div class="left">
          <div class="icon" :style="{backgroundColor: aqiFeedback.aqi.color}">
            {{aqiFeedback.aqi.chineseExplain}}
          </div>
          <div class="info">
            <p>
                {{aqiFeedback.gridProvince.provinceName}} 
                {{aqiFeedback.gridCity.cityName}} 
                {{aqiFeedback.afDate}}
            </p>
            <p>{{aqiFeedback.address}}</p>
          </div>
        </div>
        <div class="right" @click="toFeedbackInfo(aqiFeedback.afId)">
          <p>去检测</p>
          <i class="fa fa-hand-o-right"></i>
        </div>
      </li>
    </ul>
    <div class="no-data" v-if="aqiFeedbackArrSize==0">No Data</div>

    <footer>
      <img src="../assets/bottom.png" />
    </footer>
  </div>
</template>

<script setup>
import { ref, inject } from "vue";
import { useRouter } from "vue-router";
import { getSessionStorage } from "../common.js";

const router = useRouter();
const axios = inject("axios");
const gridMember = getSessionStorage("gridMember");

const aqiFeedbackArr = ref([]);
const aqiFeedbackArrSize = ref(0);

const init = () => {
  axios
    .post("aqiFeedback/listAqiFeedbackByGmId", {
        gmId: gridMember.gmId,
        state: 1
    })
    .then((response) => {
      aqiFeedbackArr.value = response.data;
      aqiFeedbackArrSize.value = aqiFeedbackArr.value.length;
    })
    .catch((error) => {
      console.log(error);
    });
};
init();

const toFeedbackInfo = (afId)=>{
    router.push({path:'/feedbackInfo',query:{afId:afId}});
}
</script>

<style scoped>
.wrapper {
  width: 100%;
  height: 100%;
  position: relative;
}

ul {
  width: 100%;
}
ul li {
  width: 100%;
  height: 18vw;
  display: flex;
  align-items: center;
  box-sizing: border-box;
  padding: 3vw;
  border-bottom: dashed 1px #999;
  color: #666;
}
ul li .left {
  flex: 4;
}
ul li .right {
  flex: 1;
}

ul li .left {
  display: flex;
  align-items: center;
}
ul li .left .icon {
  width: 8vw;
  height: 8vw;
  background-color: #fe0000;
  border-radius: 1vw;
  margin-right: 3vw;

  text-align: center;
  line-height: 8vw;

  font-size: 4vw;
  color: #fff;
  font-weight: 700;
}
ul li .left .info p:first-child {
  font-size: 4vw;
  font-weight: 700;
  margin-bottom: 1vw;
}
ul li .left .info p:last-child {
  font-size: 3.4vw;
}

ul li .right {
  display: flex;
  align-items: center;
  font-size: 4vw;

  box-sizing: border-box;
  padding: 1vw 2vw;
  border: solid 1px #c6e2ff;
  background-color: #ecf5ff;
  border-radius: 1vw;
  color: #409eff;
}
ul li .right p {
  margin-right: 1vw;
}

.no-data {
  text-align: center;
  font-size: 5vw;
  color: #999;
}
</style>