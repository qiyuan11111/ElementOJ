<template>
    <el-card class="box-card" shadow="never">
        <div class="box-card-header">
            <span>{{ "Sample Input " + props.id }}</span>
            <font-awesome-icon @click="copy(1)" class="in-icon" icon="fa-solid fa-copy"/>
        </div>
        <div class="box-card-body" lang="en">
            {{ props.in }}
        </div>
        <div class="box-card-header">
            <span>{{ "Sample Output " + props.id }}</span>
            <font-awesome-icon @click="copy(0)" class="out-icon" icon="fa-solid fa-copy"/>
        </div>
        <div class="box-card-body" lang="en">
            {{ props.out }}
        </div>
    </el-card>
</template>

<script setup>
/* eslint-disable */
import {library} from "@fortawesome/fontawesome-svg-core";
import {faCopy} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {defineProps} from 'vue'

library.add(faCopy)

const props = defineProps({
    id: Number,
    in: String,
    out: String
})

const copy = (type) => {
    navigator.clipboard.writeText(type === 1? props.in: props.out).then(() => {
        ElMessage({
            showClose: false,
            message: '已复制！',
            type: 'success',
        })
    }).catch(() => {
        ElMessage({
            showClose: false,
            message: '复制失败！',
            type: 'error',
        })
    })
}
</script>
<style scoped>
.box-card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.box-card >>> .el-card__header {
    padding: 0 !important;
}

.box-card >>> .el-card__body {
    padding: 0 !important;
}

.box-card-header {
    padding: 11px 14px 11px 14px;
    font-family: Roboto-slab;
    font-weight: 700;
    font-size: 16px;
    background-color: #e9e9e9;
    /*border-radius: 5px 5px 0 0;*/
}

.box-card-body {
    font-family: Consolas !important;
    padding: 14px;
    font-size: 15px;
    color: #2e2e2e;
    font-weight: 400;
    text-align: left;
    -webkit-hyphens: auto;
    -moz-hyphens: auto;
    hyphens: auto;
    word-wrap: break-word;
    word-break: keep-all !important;
    overflow-x: auto;
    overflow-y: hidden;
}

.box-card {
    margin-bottom: 35px;
    margin-top: 20px;
}

.in-icon, .out-icon {
    cursor: pointer;
    color: #009688;
}

.in-icon:hover, .out-icon:hover {
    color: #004d40;
}
</style>