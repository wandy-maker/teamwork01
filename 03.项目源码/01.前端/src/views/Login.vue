<template>
  <div class="wrapper">
    <header>
      <img src="../assets/header.png" />
    </header>

    <h1>东软环保公众监督平台-网格员端</h1>

    <div class="login">
      <div class="login-input">
        <i class="fa fa-user-o"></i>
        <input
          type="text"
          v-model="gridMemberForm.gmCode"
          placeholder="请输入登录编码"
        />
      </div>
      <div class="login-input">
        <i class="fa fa-key"></i>
        <input
          type="password"
          v-model="gridMemberForm.password"
          placeholder="请输入登录密码"
        />
      </div>
      <div class="btn" @click="login">登录</div>
    </div>

    <footer>
      <img src="../assets/bottom.png" />
    </footer>
  </div>
</template>

<script setup>
import { reactive, inject } from "vue";
import { useRouter } from "vue-router";
import { setSessionStorage } from "../common.js";

const router = useRouter();
const axios = inject("axios");

const gridMemberForm = reactive({
  gmCode: "",
  password: "",
});

const login = () => {
  if (gridMemberForm.gmCode == "") {
    alert("登录编码不能为空！");
    return;
  }
  if (gridMemberForm.password == "") {
    alert("登录密码不能为空！");
    return;
  }

  axios
    .post("gridMember/getGridMemberByCodeByPass", gridMemberForm)
    .then((response) => {
      if (response.data != "") {
        setSessionStorage("gridMember", response.data);
        router.push("/feedbackList");
      } else {
        alert("登录编码或密码输入不正确！");
      }
    })
    .catch((error) => {
      console.log(error);
    });
};
</script>

<style scoped>
/********************** 总容器 *******************/
.wrapper {
  width: 100%;
  height: 100%;
  position: relative;
}

/********************** header *******************/
header {
  width: 100%;
}
header img {
  display: block;
  width: 100%;
  height: 200px;
}

h1 {
  width: 78vw;
  margin: 0 auto;
  text-align: center;
  font-size: 5.4vw;
  color: #6ab9f2;
  box-sizing: border-box;
  padding: 6vw 0 1vw;
  border-bottom: solid 2px #6ab9f2;
}

/********************** login *******************/
.login {
  width: 80vw;
  margin: 0 auto;
  margin-top: 5vw;
}
.login .login-input {
  width: 100%;
  height: 12vw;
  background-image: linear-gradient(90deg, #f1f1f1, #e1e1e1);
  border-radius: 6vw;

  display: flex;
  align-items: center;
  box-sizing: border-box;
  padding-left: 6vw;
  margin-top: 8vw;
}
.login .login-input .fa {
  color: #6ab9f2;
  font-size: 5vw;
  margin-right: 2vw;
}
.login .login-input input {
  border: none;
  outline: none;
  background-image: linear-gradient(90deg, #f1f1f1, #e1e1e1);
}
.login .btn {
  width: 100%;
  height: 12vw;
  margin-top: 8vw;
  background-image: linear-gradient(90deg, #67c9ef, #7394f1);
  border-radius: 6vw;
  font-size: 5vw;
  color: #fff;
  text-align: center;
  line-height: 12vw;
  font-weight: 500;
  letter-spacing: 1vw;

  user-select: none;
}

/********************** footer *******************/
footer {
  width: 100%;
  position: absolute;
  left: 0;
  bottom: 0;
}
footer img {
  display: block;
  width: 100%;
  height: 100px;
}
</style>