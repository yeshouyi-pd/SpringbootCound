import Vue from 'vue'
import App from './App.vue'
import router from "./router"
import axios from "axios";

Vue.config.productionTip = false
Vue.prototype.$ajax = axios;//添加内置熟悉 Vue.prototype.xx 全局变量

new Vue({
    router,//需要加载router路由配置
    render: h => h(App),
}).$mount('#app')
