<template>
    <div class="block text-center">
        <el-carousel arrow="always" height="400px" @change="change">
<!--            <el-carousel-item v-for="item in 4" :key="item" style="height: 400px">-->
<!--                <div></div>-->
<!--            </el-carousel-item>-->
            <template v-if="false">
                <el-carousel-item>
                    <span style="    height: 100%;
    display: flex;
    flex-direction: row;
    align-content: center;
    justify-content: center;
    align-items: center;"> 未登录不允许查看</span>
                </el-carousel-item>

            </template>
            <template v-else>
                <el-carousel-item>
                    <el-image style="width: 100%;height: 100%;" :src="img[0]" fit="cover" />
                </el-carousel-item>
                <el-carousel-item>
                    <el-image style="width: 100%;height: 100%;" :src="img[1]" fit="cover" />
                </el-carousel-item>
                <el-carousel-item>
                    <el-image style="width: 100%;height: 100%;" :src="img[2]" fit="cover" />
                </el-carousel-item>
                <el-carousel-item>
                    <el-image style="width: 100%;height: 100%;" :src="img[3]" fit="cover" />
                </el-carousel-item>
                <el-carousel-item>
                    <el-image style="width: 100%;height: 100%;" :src="img[4]" fit="cover" />
                </el-carousel-item>
            </template>

        </el-carousel>
    </div>

</template>

<script setup>
import { reactive } from 'vue';

const getRandomInt = (max) => {
    return Math.floor(Math.random() * max);
}

const change = (pre) => {
    console.log(pre)
    let index = (pre + 2) % 5
    let path = files[getRandomInt(len)].slice(2)
    img[index] = require('@/assets/img/photo/' + path)
}


const files = require.context('@/assets/img/photo/', false, /.jpg$/).keys()
// console.log(files)
const len = files.length
let path1 = files[getRandomInt(len)].slice(2)
let path2 = files[getRandomInt(len)].slice(2)
let path3 = files[getRandomInt(len)].slice(2)
let path4 = files[getRandomInt(len)].slice(2)
let path5 = files[getRandomInt(len)].slice(2)

const img = reactive([require('@/assets/img/photo/' + path1),
require('@/assets/img/photo/' + path2),
require('@/assets/img/photo/' + path3),
require('@/assets/img/photo/' + path4),
require('@/assets/img/photo/' + path5)])

</script>

<style scoped>
.el-carousel__item div {
    display: flex;
    color: #475669;
    opacity: 0.75;
    margin: 0;
}

/*.el-carousel__item:nth-child(2n) {*/
/*    background-color: #99a9bf;*/
/*}*/

/*.el-carousel__item:nth-child(2n + 1) {*/
/*    background-color: #d3dce6;*/
/*}*/

</style>