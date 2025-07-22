<template>
    <div class="video-player">
        <video
            ref="video"
            :src="props.videoUrl"
            width="640"
            height="360"
            controls
            @play="onPlay"
            @pause="onPause"
            @progress="onProgress"
            @ended="onEnded"
            @error="onError"
        >
            您的浏览器不支持 HTML5 视频元素。
        </video>
        <p v-if="isBuffering">缓冲中...</p>
    </div>
</template>

<script setup>
import { ref, defineProps } from 'vue';

// 传递的属性 (prop)，用于接收视频的 URL
const props = defineProps({
    videoUrl: {
        type: String,
        required: true,
    },
});

// 使用 ref 来管理组件的状态
const isBuffering = ref(false);
const video = ref(null);

// 事件处理函数
const onPlay = () => {
    console.log('视频开始播放');
    isBuffering.value = false;
};

const onPause = () => {
    console.log('视频暂停');
};

const onProgress = () => {
    console.log('视频正在缓冲...');
    isBuffering.value = true;
};

const onEnded = () => {
    console.log('视频播放结束');
};

const onError = (event) => {
    console.error('视频播放出错', event);
};
</script>

<style scoped>
.video-player {
    display: flex;
    flex-direction: column;
    align-items: center;
}

p {
    color: red;
    font-weight: bold;
}
</style>