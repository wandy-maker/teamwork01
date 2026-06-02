<template>
  <div class="wrapper">
    <div class="header-box">
      <header>
        <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
        <p>反馈任务详细信息</p>
        <i class="fa fa-bars" @click="toFeedbackList"></i>
      </header>
      <div class="top-ban"></div>
      <img src="../assets/header.png" />
    </div>

    <table border="1" class="info">
      <tr>
        <td>反馈用户</td>
        <td>
          {{aqiFeedback.supervisor.realName}} 
          {{aqiFeedback.supervisor.telId}} 
        </td>
      </tr>
      <tr>
        <td>网格</td>
        <td>
          {{aqiFeedback.gridProvince.provinceName}} 
          {{aqiFeedback.gridCity.cityName}} 
        </td>
      </tr>
      <tr>
        <td>详细地址</td>
        <td>{{aqiFeedback.address}}</td>
      </tr>
      <tr>
        <td>预估等级</td>
        <td>
          {{aqiFeedback.aqi.chineseExplain}} 
          {{aqiFeedback.aqi.aqiExplain}} 
        </td>
      </tr>
      <tr>
        <td>信息描述</td>
        <td>{{aqiFeedback.information}}</td>
      </tr>
    </table>

    <div class="actual">
      <table>
        <tr>
          <td>SO2浓度</td>
          <td><input type="text" v-model="so2Value" placeholder="请输入SO2浓度" /></td>
          <td>ug/m3</td>
          <td>
            <p :style="{backgroundColor:so2LevelObj.color}">
              {{so2LevelObj.chineseExplain}}
            </p>
          </td>
        </tr>
        <tr>
          <td>CO浓度</td>
          <td><input type="text" v-model="coValue" placeholder="请输入CO浓度" /></td>
          <td>ug/m3</td>
          <td>
            <p :style="{backgroundColor:coLevelObj.color}">
              {{coLevelObj.chineseExplain}}
            </p>
          </td>
        </tr>
        <tr>
          <td>PM2.5</td>
          <td><input type="text" v-model="spmValue" placeholder="请输入PM2.5浓度" /></td>
          <td>ug/m3</td>
          <td>
            <p :style="{backgroundColor:spmLevelObj.color}">
              {{spmLevelObj.chineseExplain}}
            </p>
          </td>
        </tr>
      </table>
      <h3 :style="{backgroundColor:aqiObj.color}">
        实测AQI等级：{{aqiObj.chineseExplain}}级 {{aqiObj.aqiExplain}}
      </h3>
      <div class="btn" @click="submitData">提交实测数据</div>
    </div>

    <footer>
      <img src="../assets/bottom.png" />
    </footer>
  </div>
</template>

<script setup>
import { ref, inject, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import { getSessionStorage, getCurDate, getCurTime } from "../common.js";

const route = useRoute();
const router = useRouter();
const axios = inject("axios");
const gridMember = getSessionStorage("gridMember");

const aqiFeedback = ref({
  supervisor: {},
  gridProvince: {},
  gridCity: {},
  aqi: {},
  gridMember: {}
});

const aqiArr = ref([
  {
    aqiId: 1,
    chineseExplain: '一',
    color: '#02E300'
  }
]);

const so2Value = ref('');
const coValue = ref('');
const spmValue = ref('');

//根据so2Value、coValue、spmValue数据的输入值计算等级
const so2LevelObj = ref(
  computed(()=>{
    let result = {};
    //数据越界的处理
    if(so2Value.value<aqiArr.value[0].so2Min){
      return aqiArr.value[0];
    }
    if(so2Value.value>aqiArr.value[aqiArr.value.length-1].so2Max){
      return aqiArr.value[aqiArr.value.length-1];
    }
    //根据实测数据获取aqi等级
    for(let i=0;i<aqiArr.value.length;i++){
      if(so2Value.value>=aqiArr.value[i].so2Min && so2Value.value<=aqiArr.value[i].so2Max){
        result = aqiArr.value[i];
      }
    }
    return result;
  })
);
const coLevelObj = ref(
  computed(()=>{
    let result = {};
    //数据越界的处理
    if(coValue.value<aqiArr.value[0].coMin){
      return aqiArr.value[0];
    }
    if(coValue.value>aqiArr.value[aqiArr.value.length-1].coMax){
      return aqiArr.value[aqiArr.value.length-1];
    }
    //根据实测数据获取aqi等级
    for(let i=0;i<aqiArr.value.length;i++){
      if(coValue.value>=aqiArr.value[i].coMin && coValue.value<=aqiArr.value[i].coMax){
        result = aqiArr.value[i];
      }
    }
    return result;
  })
);
const spmLevelObj = ref(
  computed(()=>{
    let result = {};
    //数据越界的处理
    if(spmValue.value<aqiArr.value[0].spmMin){
      return aqiArr.value[0];
    }
    if(spmValue.value>aqiArr.value[aqiArr.value.length-1].spmMax){
      return aqiArr.value[aqiArr.value.length-1];
    }
    //根据实测数据获取aqi等级
    for(let i=0;i<aqiArr.value.length;i++){
      if(spmValue.value>=aqiArr.value[i].spmMin && spmValue.value<=aqiArr.value[i].spmMax){
        result = aqiArr.value[i];
      }
    }
    return result;
  })
);
//取so2Value、coValue、spmValue中的最大aqi等级作为总的aqi等级
const aqiObj = ref(
  computed(()=>{
    let result = {};
    let maxAqi = Math.max(
      so2LevelObj.value.aqiId,
      coLevelObj.value.aqiId,
      spmLevelObj.value.aqiId
    );
    for(let i=0;i<aqiArr.value.length;i++){
      if(aqiArr.value[i].aqiId==maxAqi){
        result = aqiArr.value[i];
      }
    }
    return result;
  })
);

const init = ()=>{
  //根据主键查询反馈任务详情
  axios
    .post("aqiFeedback/getAqiFeedbackById", {
        afId: route.query.afId
    })
    .then((response) => {
      aqiFeedback.value = response.data;
      
    })
    .catch((error) => {
      console.log(error);
    });

  //查询aqi基础数据
  axios
    .post("aqi/listAqiAll")
    .then((response) => {
      aqiArr.value = response.data;
    })
    .catch((error) => {
      console.log(error);
    });
}
init();

const submitData = ()=>{
  if(so2Value.value===''||coValue.value===''||spmValue.value===''){
    alert('aqi等级分项数据值不能为空！');
    return;
  }
  if(isNaN(so2Value.value)||isNaN(coValue.value)||isNaN(so2Value.value)){
    alert('aqi等级分项数据值必须为数字！');
    return;
  }
  if(!confirm('确认要提交此数据吗？')){
    return;
  }
  let param = {
    provinceId: aqiFeedback.value.provinceId,
	  cityId: aqiFeedback.value.cityId,
	  address: aqiFeedback.value.address,
	  so2Value: so2Value.value,
	  so2Level: so2LevelObj.value.aqiId,
	  coValue: coValue.value,
	  coLevel: coLevelObj.value.aqiId,
	  spmValue: spmValue.value,
	  spmLevel: spmLevelObj.value.aqiId,
	  aqiId: aqiObj.value.aqiId,
	  confirmDate: getCurDate(),
	  confirmTime: getCurTime(),
	  gmId: gridMember.gmId,
	  fdId: aqiFeedback.value.telId,
	  information: aqiFeedback.value.information
  }
  axios
    .post("statistics/saveStatistics",param)
    .then((response) => {
      if(response.data==1){
        //更新反馈信息状态
        updateAqiFeedbackState();
        //跳转回反馈任务列表
        router.push('/feedbackList');
      }else{
        alert('提交实测数据失败');
      }
    })
    .catch((error) => {
      console.log(error);
    });
}
const updateAqiFeedbackState = ()=>{
  axios
    .post("aqiFeedback/updateAqiFeedbackState",{
      afId: aqiFeedback.value.afId,
      state: 2
    })
    .then((response) => {
      if(response.data!=1){
        alert('更新反馈信息失败！');
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

const toFeedbackList = ()=>{
  router.push('/feedbackList');
}
</script>

<style scoped>
.wrapper {
  width: 100%;
  height: 100%;
  position: relative;
}

.info {
  width: 90%;
  margin: 0 auto;
  margin-top: 2vw;
  border-collapse: collapse;
  border: solid 1px #ddd;
  color: #666;
}

.info tr td {
  box-sizing: border-box;
  padding: 1.4vw;
  font-size: 3.6vw;
}
.info tr td:first-child {
  background-color: #f5f7fa;
  width: 22%;
}

.actual {
  width: 90%;
  margin: 0 auto;
  margin-top: 4vw;
  color: #666;
}

.actual table {
  margin-top: 1vw;
  font-size: 3.6vw;
}
.actual table tr {
  height: 8vw;
}
.actual table tr td input {
  border: none;
  outline: none;
  width: 45vw;
  height: 7vw;
  border: solid 1px #999;
  border-radius: 3.5vw;
  box-sizing: border-box;
  padding-left: 3vw;
}
.actual table tr td:last-child p {
  margin-left: 4vw;
  width: 7vw;
  height: 7vw;
  text-align: center;
  line-height: 7vw;
  background-color: #ef0000;
  border-radius: 1.4vw;
  color: #fff;
  font-size: 3.6vw;
  font-weight: 700;
}
.actual h3 {
  width: 100%;
  height: 8vw;
  text-align: center;
  line-height: 8vw;
  font-size: 4vw;
  color: #fff;
  background-color: #ef0000;
  margin: 3vw 0;
}
.actual .btn {
  width: 40vw;
  height: 8vw;
  margin: 0 auto;
  text-align: center;
  line-height: 8vw;
  background-color: #409eff;
  border-radius: 2vw;
  font-size: 4vw;
  font-weight: 700;
  color: #fff;
}
</style>