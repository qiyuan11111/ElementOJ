
<template>
    <div class="common-layout">
        <el-container>
            <el-header style="padding: 0;">
                <NavMenu/>
            </el-header>
            <el-main style="background-color: #eef0f2;min-height: 100vh">
                <div class="container mundb-standard-container">
                    <router-view/>
                </div>
            </el-main>
        </el-container>
    </div>
</template>

<script setup>
/* eslint-disable vue/multi-word-component-names */

</script>

<style scoped>

</style>