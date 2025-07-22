<template>
    <el-row :gutter="20">

        <el-col :md="16">
            <div ref="problem_list">
                <ProblemListCard />
            </div>

        </el-col>
        <el-col :md="8">
            <div ref="problem_info">
                <el-card class="box-card">

                </el-card>
            </div>
        </el-col>
    </el-row>

</template>

<script setup>

</script>

<style scoped>
.el-card.is-always-shadow:hover {
    box-shadow: 0px 0px 30px rgba(0, 0, 0, 0.12);
}
</style>