<template>
    <el-card class="box-card" shadow="never">
        <template #header>
            <div class="box-card-header">
                <span>{{ props.title }}</span>
            </div>
        </template>
        <div class="box-card-body" lang="en">
            {{ props.text }}
        </div>
    </el-card>
</template>

<script setup>
import { defineProps } from 'vue'

const props = defineProps({
    title: String,
    text: String
})
</script>

<style scoped>
.box-card-header{
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.box-card >>> .el-card__header {
    padding: 0 !important;
}
.box-card >>> .el-card__body{
    padding: 0 !important;
}
.box-card-header{
    padding: 11px 14px 11px 14px;
    font-family: Roboto-slab;
    font-weight: 700;
    font-size: 16px;
    background-color: #e9e9e9;
    border-radius: 5px 5px 0 0;
}
.box-card-body{
    font-family: Roboto-slab;
    padding: 14px;
    font-size: 15px;
    color: #2e2e2e;
    font-weight: 400;
    text-align: left;
    -webkit-hyphens: auto;
    -moz-hyphens: auto;
    hyphens:auto;
    word-wrap: break-word;
    word-break: keep-all !important;
}
.box-card{
    margin-bottom: 35px;
    margin-top: 20px;
}
</style>