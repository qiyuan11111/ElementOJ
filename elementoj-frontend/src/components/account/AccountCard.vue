<template>
    <div class="account-card">
        <el-card class="box-card" style="margin-left: 10px; margin-right: 10px;">
            <div class="card-header">
                <ul class="nav nav-tabs card-header-tabs nav-justified nav-tabs-material" id="accountTab"
                    role="tablist">
                    <li class="nav-item">
                        <router-link to="/login">
                            <span :class="{'nav-link':true, 'active':router.currentRoute.value.fullPath==='/login'}"
                                  id="login-tab">Login
                            </span>
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link to="/register">
                            <span :class="{'nav-link':true, 'active':router.currentRoute.value.fullPath==='/register'}"
                                  id="register-tab">Register
                            </span>
                        </router-link>

                    </li>
                    <div class="nav-tabs-indicator" id="nav-tabs-indicator"
                         :style="{left: router.currentRoute.value.fullPath==='/register'? 'unset': '0px', right: router.currentRoute.value.fullPath==='/register'? '0px': 'unset'}"></div>
                </ul>
            </div>

            <div class="card-body" style="padding-left: 2rem; padding-right: 3rem">
                <div class="form-group bmd-form-group">
                    <router-view/>
                </div>
            </div>
        </el-card>
    </div>

</template>

<script setup>
import {useRouter} from "vue-router";
//
const router = useRouter();
console.log()

</script>

<style lang="scss">
.account-card {
    .el-card__body, .card-header {
        padding: 0px
    }

    .card_body {
        padding: var(--el-card-padding);
    }

    #accountTab {
        position: relative;
    }

    .el-card .card-header > ul {
        margin: 0;
    }

    .el-card .card-header .nav-tabs .nav-link.active {
        color: #ff4081;
    }

    .el-card .card-header > ul .nav-link {
        padding: 1rem;
        border: none !important;
    }

    .nav-tabs .nav-link {
        border-bottom: 0.214rem solid transparent;
        color: rgba(0, 0, 0, .54);
    }

    .nav-tabs-material .nav-tabs-indicator {
        background-color: #ff4081;
        bottom: -1px;
        display: block;
        width: 50%;
        height: 0.15rem;
        position: absolute;
        transition: .2s ease-out .0s;
    }

    .card-header-tabs {
        margin-right: 0;
        margin-left: 0;
    }

    label {
        margin-bottom: 0;
    }


    .bmd-form-group {
        padding-top: 1rem;;
    }
}


</style>