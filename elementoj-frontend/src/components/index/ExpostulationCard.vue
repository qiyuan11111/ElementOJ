<template>
    <div style="margin-top: 30px;font-weight: 100;">
        <el-card shadow="never">
            <h5 style="font-weight: 100;margin-bottom: 20px">一言</h5>
            <span>{{ expostulation }}</span>
        </el-card>
    </div>
</template>

<script setup>
import {ref} from 'vue';
import {getRandomExpostulation} from "@/api/ele-expostulation";

const expostulation = ref("")

getRandomExpostulation().then(res => {
    expostulation.value = res.data.expostulation
}).catch(() => {
    expostulation.value = '一言获取失败'
})
</script>

<style scoped>

</style>