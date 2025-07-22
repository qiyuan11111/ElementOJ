<template>
    <div class="problem-list-card">
        <el-card class="box-card" style="width: 100%">
            <el-table v-loading="loading" :data="problemList">
                <el-table-column label="#" width="100">
                    <template #default="scope">
                        <span class="problem-id">{{"EOJ" + scope.row.problemId}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="Problem">
                    <template #default="scope">
                        <div class="title">
                            <span class="wemd-grey-text" style="font-size: 12px; margin-right: 3px">
                                <font-awesome-icon icon="fa-regular fa-circle" />
                            </span>
                            <span @click="toProblem(scope.row.problemId)">{{scope.row.title}}</span>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="Submitted" width="100">
                    <template #default>
                        <span class="submitted">20</span>
                    </template>
                </el-table-column>
                <el-table-column label="Passed" width="100">
                    <template #default>
                        <span class="passed">30</span>
                    </template>
                </el-table-column>
                <el-table-column label="AC Rate" width="80">
                    <template #default>
                        <span class="ac-rated">12.50%</span>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script setup>
import {ref} from 'vue';
import {getProblemList} from "@/api/ele-problem";
import {library} from "@fortawesome/fontawesome-svg-core";
import {faCircle} from "@fortawesome/free-regular-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {useRouter} from "vue-router";

library.add(faCircle)

const router = useRouter()

const problemList = ref([])

const loading = ref(true)

getProblemList().then(res => {
    let data = res.data

    loading.value = false
    problemList.value = data
}).catch(() => {
    problemList.value = []
})

const toProblem = (id) => {
    router.push({path: '/problem', query: {pid: id}})
}
</script>

<style scoped>
.el-card.is-always-shadow:hover {
    box-shadow: 0px 0px 30px rgba(0, 0, 0, 0.12);
}

.problem-id{
    vertical-align: top;
    color: #212529;
    font-weight: bold;
}

.title span:nth-child(2){
    transition: .2s ease-out .0s;
    color: #009688;
    cursor: pointer;
}

.title span:nth-child(2):hover{
    color: #004d40;
    /*margin-bottom: 1rem;*/
}

.submitted, .passed, .ac-rated{
    color: #212529;
}
</style>