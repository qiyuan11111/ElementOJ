<template>

    <el-dropdown trigger="click">
        <el-button type="text">
            <span class="nav-link">
                Hello! {{ toRaw(store.state.user.user.nickName) }}
                <font-awesome-icon icon="fa-solid fa-caret-down"/>
            </span>
        </el-button>
        <!--        <div id="user-pannel" class="">click</div>-->
        <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item class="dropdown-header">
                    <img src="@/assets/img/default.png" class="mundb-avatar"
                         id="atsast_nav_avatar">
                    <div>
                        <h6><span id="nav-dropdown-username">{{ toRaw(store.state.user.user.nickName) }}</span><br>
                            <small>{{ toRaw(store.state.user.user.email) }}</small>
                        </h6>
                    </div>
                </el-dropdown-item>
                <el-divider style="margin-top: 5px; margin-bottom: 5px"/>
                <el-dropdown-item class="dropdown-item">
                    <font-awesome-icon icon="fa-solid fa-circle-user" fixed-width style="margin-right: 5px"/>
                    Information
                </el-dropdown-item>
                <el-dropdown-item class="dropdown-item">
                    <font-awesome-icon icon="fa-solid fa-sliders" fixed-width style="margin-right: 5px"/>
                    Settings
                </el-dropdown-item>
                <el-dropdown-item class="dropdown-item">
                    <font-awesome-icon icon="fa-solid fa-screwdriver-wrench" fixed-width style="margin-right: 5px"/>
                    Admin
                </el-dropdown-item>
                <el-divider style="margin-top: 5px; margin-bottom: 5px"/>
                <el-dropdown-item class="dropdown-item">
                    <font-awesome-icon icon="fa-solid fa-people-group" fixed-width style="margin-right: 5px"/>
                    My Team
                </el-dropdown-item>
                <el-divider style="margin-top: 5px; margin-bottom: 5px"/>
                <el-dropdown-item class="dropdown-item">
                    <font-awesome-icon icon="fa-solid fa-right-from-bracket" fixed-width style="margin-right: 5px"/>
                    Logout
                </el-dropdown-item>
            </el-dropdown-menu>
        </template>
    </el-dropdown>
</template>

<script setup>
/* eslint-disable */
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'
import {library} from "@fortawesome/fontawesome-svg-core";
import {
    faCaretDown,
    faCircleUser,
    faSliders,
    faScrewdriverWrench,
    faPeopleGroup,
    faRightFromBracket
} from '@fortawesome/free-solid-svg-icons'
import {useStore} from "vuex";
import {toRaw} from "vue";

library.add(faCaretDown, faCircleUser, faSliders, faScrewdriverWrench, faPeopleGroup, faRightFromBracket)

const store = useStore()

</script>

<style scoped>

</style>