import Vue from 'vue'
import App from './App.vue'
import router from "./router"
import Router from "vue-router";

Vue.config.productionTip = false

new Vue({
    router,//需要加载router路由配置
    render: h => h(App),
}).$mount('#app')
