<template>
    <div class="problem-card">
        <!--        <button @click="test">5555</button>-->
        <el-card class="box-card animate__animated animate__slideInLeft" style="width: 100%">
            <ProblemDetail :problem="props.problem"/>
        </el-card>
    </div>
</template>

<script setup>
/* eslint-disable */
import {defineProps} from "vue";

const props = defineProps({
    problem: {
        type: Object,
        default: {
            title: "Title",
            problemId: 1,
            problemDescription: "Description",
            inputDescription: "Input",
            outputDescription: "Output",
            hint: "Hint",
            timeLimit: 0,
            memoryLimit: 0,
        }
    },
    isShowSubInfo: {
        type: Boolean,
        default: true
    }
})
</script>

<style scoped>

</style>