<template>
    <ProblemTitle :title="problem.title" :pid="problem.problemId" :time="problem.timeLimit"
                  :memory="problem.memoryLimit" :show-sub-info="isShowSubInfo"/>
    <div class="divide-title"
         v-if="isCardShow(problem.problemDescription) || isCardShow(problem.inputDescription) || isCardShow(problem.outputDescription)">
        <font-awesome-icon icon="fa-solid fa-bars"/>
        <span>Problem Details</span>
    </div>
    <ProblemTextCard v-if="isCardShow(problem.problemDescription)" title="Problem Description"
                     :text="problem.problemDescription"/>
    <ProblemTextCard v-if="isCardShow(problem.inputDescription)" title="Input Description"
                     :text="problem.inputDescription"/>
    <ProblemTextCard v-if="isCardShow(problem.outputDescription)" title="Output Description"
                     :text="problem.outputDescription"/>
    <div class="divide-title">
        <font-awesome-icon icon="fa-solid fa-bars"/>
        <span>Sample Testcase</span>
    </div>
    <TestCaseCard :id="1" in="555" out="555"/>
    <div class="divide-title" v-if="isCardShow(problem.hint)">
        <font-awesome-icon icon="fa-solid fa-bars"/>
        <span>Extra</span>
    </div>
    <ProblemTextCard v-if="isCardShow(problem.hint)" title="Hint" :text="problem.hint"/>
    <!--            <div style="">-->

    <!--                -->
    <!--&lt;!&ndash;                <div v-if="result.getProblemForm.problem.isSpj"&ndash;&gt;-->
    <!--&lt;!&ndash;                     style="text-align: center;color: red;margin-top: 10px"><span>Special Judge</span>&ndash;&gt;-->
    <!--&lt;!&ndash;                </div>&ndash;&gt;-->
    <!--            </div>-->
</template>

<script setup>
/* eslint-disable */
import {toRef, defineProps} from 'vue';
import {library} from "@fortawesome/fontawesome-svg-core";
import {faBars} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

library.add(faBars)

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
const problem = toRef(props, "problem")

const isCardShow = (text) => {
    return text != null && typeof text == "string" && text.length > 0
}

const test = () => {
    console.log(problem)
    console.log(props.problem.title)
}


</script>

<style scoped>
.divide-title {
    text-align: left;
    font-size: 17px;
    color: #7a8e97;
    font-weight: 400;
    box-sizing: border-box;
    margin-top: 10px;
    margin-bottom: 10px;
}

.divide-title span {
    margin-left: 10px;
}
</style>