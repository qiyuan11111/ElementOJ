<template>
    <router-view/>
</template>

<script setup>
/* eslint-disable */
import {onMounted, reactive, ref, computed} from "vue";
import {getUserInfoByAccessToken} from "@/api/ele-account";
import {useStore} from "vuex";
// import {refreshUserInfo} from "@/utils/cookie";

const store = useStore()
// const b = ref(1)
const auth = computed({
    get: () => store.state.test.isAuth
})

onMounted(() => {
    store.dispatch('user/refreshUserInfo');
})


const debounce = (fn, delay) => {
    let timer = null;
    return function () {
        let context = this;
        let args = arguments;
        clearTimeout(timer);
        timer = setTimeout(function () {
            fn.apply(context, args);
        }, delay);
    }
}

const _ResizeObserver = window.ResizeObserver;
window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
    constructor(callback) {
        callback = debounce(callback, 16);
        super(callback);
    }
}


</script>

<style>
.el-card.is-always-shadow:hover {
    box-shadow: 0px 0px 30px rgba(0, 0, 0, 0.12);
}
a{
    text-decoration: none !important;
}

.router-link-active {
    text-decoration: none !important;
}

#app {
    font-family: Roboto,Helvetica,Arial,sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
}

body {
    margin: 0;
    /*font-family: -apple-system,BlinkMacSystemFont,Helvetica Neue,PingFang SC,Microsoft YaHei,Source Han Sans SC,Noto Sans CJK SC,WenQuanYi Micro Hei,sans-serif;*/

}

.box-card, .el-col {
    margin-bottom: 20px
}

:root {
    --blue: #2196f3;
    --indigo: #3f51b5;
    --purple: #9c27b0;
    --pink: #e91e63;
    --red: #f44336;
    --orange: #ff9800;
    --yellow: #ffeb3b;
    --green: #4caf50;
    --teal: #009688;
    --cyan: #00bcd4;
    --white: #fff;
    --gray: #6c757d;
    --gray-dark: #343a40;
    --primary: #009688;
    --secondary: #6c757d;
    --success: #4caf50;
    --info: #03a9f4;
    --warning: #ff5722;
    --danger: #f44336;
    --light: #f5f5f5;
    --dark: #424242;
    --breakpoint-xs: 0;
    --breakpoint-sm: 576px;
    --breakpoint-md: 768px;
    --breakpoint-lg: 992px;
    --breakpoint-xl: 1200px;
    --font-family-sans-serif: "Roboto", "Helvetica", "Arial", sans-serif;
    --font-family-monospace: SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace
}

:root {
    --animation-timing-function: cubic-bezier(0.4, 0, 0.2, 1);
    --wemd-materialize-red: #e51c23;
    --wemd-materialize-red-lighten-5: #fdeaeb;
    --wemd-materialize-red-lighten-4: #f8c1c3;
    --wemd-materialize-red-lighten-3: #f3989b;
    --wemd-materialize-red-lighten-2: #ee6e73;
    --wemd-materialize-red-lighten-1: #ea454b;
    --wemd-materialize-red-darken-1: #d0181e;
    --wemd-materialize-red-darken-2: #b9151b;
    --wemd-materialize-red-darken-3: #a21318;
    --wemd-materialize-red-darken-4: #8b1014;
    --wemd-red: #f44336;
    --wemd-red-lighten-5: #ffebee;
    --wemd-red-lighten-4: #ffcdd2;
    --wemd-red-lighten-3: #ef9a9a;
    --wemd-red-lighten-2: #e57373;
    --wemd-red-lighten-1: #ef5350;
    --wemd-red-darken-1: #e53935;
    --wemd-red-darken-2: #d32f2f;
    --wemd-red-darken-3: #c62828;
    --wemd-red-darken-4: #b71c1c;
    --wemd-red-accent-1: #ff8a80;
    --wemd-red-accent-2: #ff5252;
    --wemd-red-accent-3: #ff1744;
    --wemd-red-accent-4: #d50000;
    --wemd-pink: #e91e63;
    --wemd-pink-lighten-5: #fce4ec;
    --wemd-pink-lighten-4: #f8bbd0;
    --wemd-pink-lighten-3: #f48fb1;
    --wemd-pink-lighten-2: #f06292;
    --wemd-pink-lighten-1: #ec407a;
    --wemd-pink-darken-1: #d81b60;
    --wemd-pink-darken-2: #c2185b;
    --wemd-pink-darken-3: #ad1457;
    --wemd-pink-darken-4: #880e4f;
    --wemd-pink-accent-1: #ff80ab;
    --wemd-pink-accent-2: #ff4081;
    --wemd-pink-accent-3: #f50057;
    --wemd-pink-accent-4: #c51162;
    --wemd-purple: #9c27b0;
    --wemd-purple-lighten-5: #f3e5f5;
    --wemd-purple-lighten-4: #e1bee7;
    --wemd-purple-lighten-3: #ce93d8;
    --wemd-purple-lighten-2: #ba68c8;
    --wemd-purple-lighten-1: #ab47bc;
    --wemd-purple-darken-1: #8e24aa;
    --wemd-purple-darken-2: #7b1fa2;
    --wemd-purple-darken-3: #6a1b9a;
    --wemd-purple-darken-4: #4a148c;
    --wemd-purple-accent-1: #ea80fc;
    --wemd-purple-accent-2: #e040fb;
    --wemd-purple-accent-3: #d500f9;
    --wemd-purple-accent-4: #a0f;
    --wemd-deep-purple: #673ab7;
    --wemd-deep-purple-lighten-5: #ede7f6;
    --wemd-deep-purple-lighten-4: #d1c4e9;
    --wemd-deep-purple-lighten-3: #b39ddb;
    --wemd-deep-purple-lighten-2: #9575cd;
    --wemd-deep-purple-lighten-1: #7e57c2;
    --wemd-deep-purple-darken-1: #5e35b1;
    --wemd-deep-purple-darken-2: #512da8;
    --wemd-deep-purple-darken-3: #4527a0;
    --wemd-deep-purple-darken-4: #311b92;
    --wemd-deep-purple-accent-1: #b388ff;
    --wemd-deep-purple-accent-2: #7c4dff;
    --wemd-deep-purple-accent-3: #651fff;
    --wemd-deep-purple-accent-4: #6200ea;
    --wemd-indigo: #3f51b5;
    --wemd-indigo-lighten-5: #e8eaf6;
    --wemd-indigo-lighten-4: #c5cae9;
    --wemd-indigo-lighten-3: #9fa8da;
    --wemd-indigo-lighten-2: #7986cb;
    --wemd-indigo-lighten-1: #5c6bc0;
    --wemd-indigo-darken-1: #3949ab;
    --wemd-indigo-darken-2: #303f9f;
    --wemd-indigo-darken-3: #283593;
    --wemd-indigo-darken-4: #1a237e;
    --wemd-indigo-accent-1: #8c9eff;
    --wemd-indigo-accent-2: #536dfe;
    --wemd-indigo-accent-3: #3d5afe;
    --wemd-indigo-accent-4: #304ffe;
    --wemd-blue: #2196f3;
    --wemd-blue-lighten-5: #e3f2fd;
    --wemd-blue-lighten-4: #bbdefb;
    --wemd-blue-lighten-3: #90caf9;
    --wemd-blue-lighten-2: #64b5f6;
    --wemd-blue-lighten-1: #42a5f5;
    --wemd-blue-darken-1: #1e88e5;
    --wemd-blue-darken-2: #1976d2;
    --wemd-blue-darken-3: #1565c0;
    --wemd-blue-darken-4: #0d47a1;
    --wemd-blue-accent-1: #82b1ff;
    --wemd-blue-accent-2: #448aff;
    --wemd-blue-accent-3: #2979ff;
    --wemd-blue-accent-4: #2962ff;
    --wemd-light-blue: #03a9f4;
    --wemd-light-blue-lighten-5: #e1f5fe;
    --wemd-light-blue-lighten-4: #b3e5fc;
    --wemd-light-blue-lighten-3: #81d4fa;
    --wemd-light-blue-lighten-2: #4fc3f7;
    --wemd-light-blue-lighten-1: #29b6f6;
    --wemd-light-blue-darken-1: #039be5;
    --wemd-light-blue-darken-2: #0288d1;
    --wemd-light-blue-darken-3: #0277bd;
    --wemd-light-blue-darken-4: #01579b;
    --wemd-light-blue-accent-1: #80d8ff;
    --wemd-light-blue-accent-2: #40c4ff;
    --wemd-light-blue-accent-3: #00b0ff;
    --wemd-light-blue-accent-4: #0091ea;
    --wemd-cyan: #00bcd4;
    --wemd-cyan-lighten-5: #e0f7fa;
    --wemd-cyan-lighten-4: #b2ebf2;
    --wemd-cyan-lighten-3: #80deea;
    --wemd-cyan-lighten-2: #4dd0e1;
    --wemd-cyan-lighten-1: #26c6da;
    --wemd-cyan-darken-1: #00acc1;
    --wemd-cyan-darken-2: #0097a7;
    --wemd-cyan-darken-3: #00838f;
    --wemd-cyan-darken-4: #006064;
    --wemd-cyan-accent-1: #84ffff;
    --wemd-cyan-accent-2: #18ffff;
    --wemd-cyan-accent-3: #00e5ff;
    --wemd-cyan-accent-4: #00b8d4;
    --wemd-teal: #009688;
    --wemd-teal-lighten-5: #e0f2f1;
    --wemd-teal-lighten-4: #b2dfdb;
    --wemd-teal-lighten-3: #80cbc4;
    --wemd-teal-lighten-2: #4db6ac;
    --wemd-teal-lighten-1: #26a69a;
    --wemd-teal-darken-1: #00897b;
    --wemd-teal-darken-2: #00796b;
    --wemd-teal-darken-3: #00695c;
    --wemd-teal-darken-4: #004d40;
    --wemd-teal-accent-1: #a7ffeb;
    --wemd-teal-accent-2: #64ffda;
    --wemd-teal-accent-3: #1de9b6;
    --wemd-teal-accent-4: #00bfa5;
    --wemd-green: #4caf50;
    --wemd-green-lighten-5: #e8f5e9;
    --wemd-green-lighten-4: #c8e6c9;
    --wemd-green-lighten-3: #a5d6a7;
    --wemd-green-lighten-2: #81c784;
    --wemd-green-lighten-1: #66bb6a;
    --wemd-green-darken-1: #43a047;
    --wemd-green-darken-2: #388e3c;
    --wemd-green-darken-3: #2e7d32;
    --wemd-green-darken-4: #1b5e20;
    --wemd-green-accent-1: #b9f6ca;
    --wemd-green-accent-2: #69f0ae;
    --wemd-green-accent-3: #00e676;
    --wemd-green-accent-4: #00c853;
    --wemd-light-green: #8bc34a;
    --wemd-light-green-lighten-5: #f1f8e9;
    --wemd-light-green-lighten-4: #dcedc8;
    --wemd-light-green-lighten-3: #c5e1a5;
    --wemd-light-green-lighten-2: #aed581;
    --wemd-light-green-lighten-1: #9ccc65;
    --wemd-light-green-darken-1: #7cb342;
    --wemd-light-green-darken-2: #689f38;
    --wemd-light-green-darken-3: #558b2f;
    --wemd-light-green-darken-4: #33691e;
    --wemd-light-green-accent-1: #ccff90;
    --wemd-light-green-accent-2: #b2ff59;
    --wemd-light-green-accent-3: #76ff03;
    --wemd-light-green-accent-4: #64dd17;
    --wemd-lime: #cddc39;
    --wemd-lime-lighten-5: #f9fbe7;
    --wemd-lime-lighten-4: #f0f4c3;
    --wemd-lime-lighten-3: #e6ee9c;
    --wemd-lime-lighten-2: #dce775;
    --wemd-lime-lighten-1: #d4e157;
    --wemd-lime-darken-1: #c0ca33;
    --wemd-lime-darken-2: #afb42b;
    --wemd-lime-darken-3: #9e9d24;
    --wemd-lime-darken-4: #827717;
    --wemd-lime-accent-1: #f4ff81;
    --wemd-lime-accent-2: #eeff41;
    --wemd-lime-accent-3: #c6ff00;
    --wemd-lime-accent-4: #aeea00;
    --wemd-yellow: #ffeb3b;
    --wemd-yellow-lighten-5: #fffde7;
    --wemd-yellow-lighten-4: #fff9c4;
    --wemd-yellow-lighten-3: #fff59d;
    --wemd-yellow-lighten-2: #fff176;
    --wemd-yellow-lighten-1: #ffee58;
    --wemd-yellow-darken-1: #fdd835;
    --wemd-yellow-darken-2: #fbc02d;
    --wemd-yellow-darken-3: #f9a825;
    --wemd-yellow-darken-4: #f57f17;
    --wemd-yellow-accent-1: #ffff8d;
    --wemd-yellow-accent-2: #ff0;
    --wemd-yellow-accent-3: #ffea00;
    --wemd-yellow-accent-4: #ffd600;
    --wemd-amber: #ffc107;
    --wemd-amber-lighten-5: #fff8e1;
    --wemd-amber-lighten-4: #ffecb3;
    --wemd-amber-lighten-3: #ffe082;
    --wemd-amber-lighten-2: #ffd54f;
    --wemd-amber-lighten-1: #ffca28;
    --wemd-amber-darken-1: #ffb300;
    --wemd-amber-darken-2: #ffa000;
    --wemd-amber-darken-3: #ff8f00;
    --wemd-amber-darken-4: #ff6f00;
    --wemd-amber-accent-1: #ffe57f;
    --wemd-amber-accent-2: #ffd740;
    --wemd-amber-accent-3: #ffc400;
    --wemd-amber-accent-4: #ffab00;
    --wemd-orange: #ff9800;
    --wemd-orange-lighten-5: #fff3e0;
    --wemd-orange-lighten-4: #ffe0b2;
    --wemd-orange-lighten-3: #ffcc80;
    --wemd-orange-lighten-2: #ffb74d;
    --wemd-orange-lighten-1: #ffa726;
    --wemd-orange-darken-1: #fb8c00;
    --wemd-orange-darken-2: #f57c00;
    --wemd-orange-darken-3: #ef6c00;
    --wemd-orange-darken-4: #e65100;
    --wemd-orange-accent-1: #ffd180;
    --wemd-orange-accent-2: #ffab40;
    --wemd-orange-accent-3: #ff9100;
    --wemd-orange-accent-4: #ff6d00;
    --wemd-deep-orange: #ff5722;
    --wemd-deep-orange-lighten-5: #fbe9e7;
    --wemd-deep-orange-lighten-4: #ffccbc;
    --wemd-deep-orange-lighten-3: #ffab91;
    --wemd-deep-orange-lighten-2: #ff8a65;
    --wemd-deep-orange-lighten-1: #ff7043;
    --wemd-deep-orange-darken-1: #f4511e;
    --wemd-deep-orange-darken-2: #e64a19;
    --wemd-deep-orange-darken-3: #d84315;
    --wemd-deep-orange-darken-4: #bf360c;
    --wemd-deep-orange-accent-1: #ff9e80;
    --wemd-deep-orange-accent-2: #ff6e40;
    --wemd-deep-orange-accent-3: #ff3d00;
    --wemd-deep-orange-accent-4: #dd2c00;
    --wemd-brown: #795548;
    --wemd-brown-lighten-5: #efebe9;
    --wemd-brown-lighten-4: #d7ccc8;
    --wemd-brown-lighten-3: #bcaaa4;
    --wemd-brown-lighten-2: #a1887f;
    --wemd-brown-lighten-1: #8d6e63;
    --wemd-brown-darken-1: #6d4c41;
    --wemd-brown-darken-2: #5d4037;
    --wemd-brown-darken-3: #4e342e;
    --wemd-brown-darken-4: #3e2723;
    --wemd-blue-grey: #607d8b;
    --wemd-blue-grey-lighten-5: #eceff1;
    --wemd-blue-grey-lighten-4: #cfd8dc;
    --wemd-blue-grey-lighten-3: #b0bec5;
    --wemd-blue-grey-lighten-2: #90a4ae;
    --wemd-blue-grey-lighten-1: #78909c;
    --wemd-blue-grey-darken-1: #546e7a;
    --wemd-blue-grey-darken-2: #455a64;
    --wemd-blue-grey-darken-3: #37474f;
    --wemd-blue-grey-darken-4: #263238;
    --wemd-grey: #9e9e9e;
    --wemd-grey-lighten-5: #fafafa;
    --wemd-grey-lighten-4: #f5f5f5;
    --wemd-grey-lighten-3: #eee;
    --wemd-grey-lighten-2: #e0e0e0;
    --wemd-grey-lighten-1: #bdbdbd;
    --wemd-grey-darken-1: #757575;
    --wemd-grey-darken-2: #616161;
    --wemd-grey-darken-3: #424242;
    --wemd-grey-darken-4: #212121;
    --wemd-dark: #3b393e;
    --wemd-black: #000;
    --wemd-white: #fff;
    --wemd-transparent: transparent
}
</style>
