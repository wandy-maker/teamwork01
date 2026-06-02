<template>
  <router-view/>
</template>

<script setup>
import axios from 'axios';
import { provide } from 'vue';

//设置共通的基础URL
axios.defaults.baseURL = 'http://localhost:8080/nepm/';
provide('axios',axios);
</script>

<style>
/********************** CSS 重置 *********************/
html,body,div,span,h1,h2,h3,h4,h5,h6,ul,ol,li,p{
    margin: 0;
    padding: 0;
}
html,body,#app{
    width: 100%;
    height: 100%;
    font-family: "微软雅黑";
}
ul,ol{
    list-style: none;
}
a{
    text-decoration: none;
}

/*************** 头部共通样式 **************/
.header-box{
    width: 100%;
}
.header-box header{
    width: 100%;
    height: 15.7vw;
    background-color: #FFF;
    color: #555;

    display: flex;
    align-items: center;
    justify-content: space-between;
    box-sizing: border-box;
    padding: 0 3.6vw;

    position: fixed;
    left: 0;
    top: 0;
}
.header-box .top-ban{
    width: 100%;
    height: 15.7vw;
}
.header-box img{
    width: 100%;
    display: block;
    height: 200px;
}
.header-box .fa{
    font-size: 8vw;
}

/*************** 底部共通样式 **************/
footer{
    width: 100%;
    position: absolute;
    left: 0;
    bottom: 0;
}
footer img{
    width: 100%;
    display: block;
    height: 100px;
}
</style>
