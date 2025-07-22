<template>
    <div class="left-problem-detail cm-performance-optimistic cm-delay animate__animated animate__fadeInLeft">
        <div class="problem-detail-pre">
            <ProblemDetail :is-show-sub-info="false" :problem="props.problem"/>
        </div>
    </div>
</template>

<script setup>
/* eslint-disable */
import {defineProps, toRef} from "vue";

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
})
</script>

<style scoped>
.problem-detail-pre{
    display: block;
    all: initial;
    color: #7a8e97;
}
</style>