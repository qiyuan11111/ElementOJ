import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router/router.js'
import '@/assets/js/include.js'
import Ripple from 'vue3-whr-ripple-directive'
import store from '@/store/index.js'
// import store from '@/store/modules/test.js'


Ripple.zIndex = 55;
createApp(App).use(router).use(store).directive('ripple', Ripple).mount('#app')




