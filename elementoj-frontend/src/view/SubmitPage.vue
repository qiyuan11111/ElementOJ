<template>
    <div class="box">
        <div class="editor-container">
            <TopSubmitSide :lang="lang" :problem="problem" :code="code"/>
            <BottomSubmitSide :lang="lang" :pid="problem.problemId"/>
        </div>
    </div>
</template>

<script setup>
import {getProblemDetailById} from "@/api/ele-problem";
import {exception} from "@/assets/js/exception/default";
import {useRoute} from "vue-router";
import {ref} from "vue";

const route = useRoute()

const lang = ref('c_cpp')

const code = ref('')

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
.box{
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    box-sizing: border-box;
}
.editor-container{
    height: 100vh;
    width: 100vw;
    display: flex;
    flex-direction: column;
    overflow: hidden;
    filter: blur(0);
    transition: filter .2s ease-out 0s;
    box-sizing: border-box;
}
</style>