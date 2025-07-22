<template>
    <div class="left-side">
        <div class="left-side-pre">
            <LeftTopMenu :pid="props.problem.problemId" :time="props.problem.timeLimit"
                         :memory="props.problem.memoryLimit"/>
            <div class="detail-box">
                <LeftProblemDetail class="problem-detail" :problem="props.problem"/>
            </div>

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
.detail-box{
    display: flex;
    text-align: center;
    justify-content: center;
    align-content: center;
    flex-wrap: nowrap;
    align-items: center;
    flex-direction: column;
}
.problem-detail{
    max-width: 49vw;
    width: 100%
}
.left-side {
    overflow-y: auto;
    box-shadow: 0 0 40px rgba(0, 0, 0, .15);
    padding: 0 3rem 3rem;
    width: 49.82vw;
}

.cm-delay {
    animation-delay: .2s;
}

.cm-performance-optimistic {
    will-change: opacity;
}
</style>