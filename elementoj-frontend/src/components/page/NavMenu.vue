<template>
    <div id="nav-container" style="position:sticky;top:0;z-index:899;">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="position: absolute;width: 100%">
            <a class="navbar-brand" href="javascript:void(0)" @click="redirect('/')">
                <img :src="img" height="30">
            </a>
            <!--            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">-->
            <!--                <span class="navbar-toggler-icon"></span>-->
            <!--            </button>-->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="javascript:void(0)" @click="redirect('/problemset')">
                            <font-awesome-icon style="display: none" icon="fa-solid fa-business-time"/>
                            <svg class="svg-inline--fa fa-books fa-fw" aria-hidden="true" focusable="false"
                                 data-prefix="fas" data-icon="books" role="img" xmlns="http://www.w3.org/2000/svg"
                                 viewBox="0 0 512 512">
                                <!--! Font Awesome Pro 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                                <path class="" fill="currentColor"
                                      d="M0 32C0 14.3 14.3 0 32 0H96c17.7 0 32 14.3 32 32V96H0V32zm0 96H128V384H0V128zM0 416H128v64c0 17.7-14.3 32-32 32H32c-17.7 0-32-14.3-32-32V416zM160 32c0-17.7 14.3-32 32-32h64c17.7 0 32 14.3 32 32V96H160V32zm0 96H288V384H160V128zm0 288H288v64c0 17.7-14.3 32-32 32H192c-17.7 0-32-14.3-32-32V416zm203.6-19.9L320 232.6V142.8l100.4-26.9 66 247.4L363.6 396.1zM412.2 85L320 109.6V11l36.9-9.9c16.9-4.6 34.4 5.5 38.9 22.6L412.2 85zM371.8 427l122.8-32.9 16.3 61.1c4.5 17-5.5 34.5-22.5 39.1l-61.4 16.5c-16.9 4.6-34.4-5.5-38.9-22.6L371.8 427z"/>
                            </svg>
                            Problem
                        </a>
                    </li>
                    <!--                    <li class="nav-item">-->
                    <!--                        <a class="nav-link " href="/web/20220501144338/https://acm.njupt.edu.cn/problem"> <i class="MDI book-multiple"></i> Problem</a>-->
                    <!--                    </li>-->
                    <!--                    <li class="nav-item">-->
                    <!--                        <a class="nav-link " href="/web/20220501144338/https://acm.njupt.edu.cn/dojo"> <i class="MDI coffee"></i> Dojo</a>-->
                    <!--                    </li>-->
                    <!--                    <li class="nav-item">-->
                    <!--                        <a class="nav-link " href="/web/20220501144338/https://acm.njupt.edu.cn/status"> <i class="MDI buffer"></i> Status<div class="ripple-container"></div></a>-->
                    <!--                    </li>-->
                    <!--                    <li class="nav-item">-->
                    <!--                        <a class="nav-link " href="/web/20220501144338/https://acm.njupt.edu.cn/rank"> <i class="MDI certificate"></i> Rank</a>-->
                    <!--                    </li>-->
                    <!--                    <li class="nav-item">-->
                    <!--                        <a class="nav-link " href="/web/20220501144338/https://acm.njupt.edu.cn/contest"> <i class="MDI trophy-variant"></i> Contest<div class="ripple-container"></div></a>-->
                    <!--                    </li>-->
                    <!--                    <li class="nav-item">-->
                    <!--                        <a class="nav-link " href="/web/20220501144338/https://acm.njupt.edu.cn/group"> <i class="MDI account-multiple"></i> Group<div class="ripple-container"></div></a>-->
                    <!--                    </li>-->
                    <!--                    <li class="nav-item">-->
                    <!--                        <a class="nav-link" href="/web/20220501144338/https://acm.njupt.edu.cn/jscpc" target=""> <i class="MDI open-in-new"></i> JSCPC</a>-->
                    <!--                    </li>-->
                    <!--                    <li class="nav-item">-->
                    <!--                        <a class="nav-link" href="/web/20220501144338/https://acm.njupt.edu.cn/babel" target="_blank"> <i class="MDI open-in-new"></i> BABEL</a>-->
                    <!--                    </li>-->
                </ul>
                <!--                <button @click="test">{{ isAuth }}</button>-->
                <!--                <button @click="test1">{{ auth }}</button>-->
                <ul class="navbar-nav mundb-nav-right">
                    <!--                    <form id="search-box" action="" method="get" class="form-inline my-2 my-lg-0 mundb-inline">-->
                    <!--                        <span class="bmd-form-group"><input id="search-key" class="form-control mr-sm-2 atsast-searchBox" name="q" type="search" value="" placeholder="Omni Search" autocomplete="off" aria-label="search"></span>-->
                    <!--                        <input type="hidden" name="tab" value="problems">-->
                    <!--                    </form>-->
                    <template v-if="isAuth">
                        <UserPannel/>
                    </template>

                    <li class="nav-item mundb-no-shrink" v-else>
                        <a class="nav-link " href="javascript:void(0)" @click="redirect('/login')">Account</a>
                    </li>

                </ul>
            </div>
        </nav>
    </div>
</template>

<script setup>
/* eslint-disable */
import {useRouter} from 'vue-router'
import UserPannel from "@/components/page/UserPannel.vue";
import {isAuthenticated} from "@/utils/cookie";
import {useStore} from "vuex";
import {computed} from "vue";
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'
import {library} from "@fortawesome/fontawesome-svg-core";
import {faBusinessTime} from "@fortawesome/free-solid-svg-icons";

library.add(faBusinessTime)


const img = require('@/assets/icon.svg')
const router = useRouter()
const store = useStore()

const isAuth = computed(() => store.state.user.isAuth)

// const auth = computed({
//     get: () => store.state.test.isAuth
// })

// const test1 = () => {
//     store.commit('test/setAuth');
//     console.log(store.state.test.isAuth)
// }

const redirect = (url) => {
    router.push(url)
}

const test = () => {
    console.log(store.state.user.isAuth)
    // if(store.state.user.isAuth){
    //     store.commit('user/setAuth', false)
    // }else {
    //     store.commit('user/setAuth', true)
    // }
}


// import 'jquery'

</script>

<style scoped>

</style>