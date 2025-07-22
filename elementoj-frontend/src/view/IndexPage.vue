<template>
    <el-card class="box-card">
        <el-row :gutter="20" align="middle">
            <el-col :md="16">
                <div ref="carousel_list">
                    <carousel-list />
                </div>
            </el-col>
            <el-col :md="8">
                <div ref="calendar_info">
                    <calendar-info />
                </div>
            </el-col>
        </el-row>
    </el-card>
    <el-row :gutter="20" align="top">


        <el-col :span="16">
            <el-card class="box-card" style="height: 300px">
                <h5 style="font-weight: bold;text-align: left">近期美图</h5>
            </el-card>
            <el-row :gutter="20" align="top">


                <el-col :span="12">
                    <el-card class="box-card" style="height: 300px">
                        <h5 style="font-weight: bold;text-align: left">近期推荐</h5>
                        <UploadPannel/>
                    </el-card>
                </el-col>
                <el-col :span="12">
                    <el-card class="box-card" style="height: 300px">
                        <h5 style="font-weight: bold;text-align: left">最新诱惑</h5>
                    </el-card>
                </el-col>


            </el-row>
        </el-col>
        <el-col :span="8">
            <el-card class="box-card" style="height: 300px">
                <h5 style="font-weight: bold;text-align: left">最新公告</h5>
                <span>恭喜钊博中了NLP顶级会议emnlp2023！！！！！！</span>
                <img :src="jober"/>
            </el-card>
        </el-col>


    </el-row>
</template>

<script setup>
import {ref, onMounted} from 'vue'
// import Upload from "@/components/index/UploadPannel.vue";
import UploadPannel from "@/components/index/UploadPannel.vue";

const jober = require('@/assets/img/jober.png')

const carousel_list = ref(null)
const calendar_info = ref(null)



onMounted(() => {
    console.log(carousel_list.value.$el)
    console.log(calendar_info.value)
})


</script>

<style scoped>


</style>