<template>
    <el-row :gutter="33">
        <el-col :md="18">
            <div ref="problem_detail">
                <ProblemCard :problem="problem"/>
            </div>
        </el-col>
        <el-col :md="6">
            <div ref="problem_info">
                <ProblemActionCard :pid="problem.problemId"/>
            </div>
        </el-col>
    </el-row>
</template>

<script setup>
/* eslint-disable */
import {getProblemDetailById} from "@/api/ele-problem";
import {exception} from "@/assets/js/exception/default";
import {useRoute} from "vue-router";
import {ref} from "vue";

const route = useRoute()

const problem = ref({
    problemId: 0,
    title: '',
    problemDescription: '',
    inputDescription: '',
    outputDescription: '',
    hint: '',
    timeLimit: 0,
    memoryLimit: 0,
})

getProblemDetailById({pid: route.query.pid}).then(res => {
    let data = res.data
    let code = res.code

    switch (code) {
        case exception.ELE_SUCCESSFUL: {
            problem.value = data
            // console.log(problem.value.problemDescription)
            console.log(problem.value)
        }
    }
})
</script>

<style scoped>



</style>