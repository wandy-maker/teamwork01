import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import 'font-awesome/css/font-awesome.min.css'

//vue路由守卫实现权限认证
router.beforeEach(function(to,from,next){
    //从前端session中获取登录信息
    let gridMember = sessionStorage.getItem('gridMember');
    //路由到“网格分配”、“预估AQI”、“历史反馈列表”，是需要判断是否登录
    if(to.path=='/feedbackList'||to.path=='/feedbackInfo'){
        if(gridMember==null){
            router.push('/login');
        }
    }
    next();
});

createApp(App).use(router).mount('#app')
