<template>
    <VideoPlayer :videoUrl="videoUrl" />
</template>

<script setup>
import { ref } from 'vue';
import VideoPlayer from "@/components/index/VideoPlayer.vue";

const videoUrl = ref('http://localhost:7803/streams');
</script>

<style scoped>

</style>