<template>
    <div>
        <h2>视频分块上传</h2>
        <input type="file" @change="handleFileChange" accept="video/*" />
        <button @click="uploadChunks" :disabled="!file">上传视频</button>

        <div v-if="uploadProgress >= 0">
            上传进度: {{ uploadProgress }}%
        </div>
        <VideoPannel/>
        <button @click="testvideo" >nihao</button>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import VideoPannel from "@/components/index/VideoPannel.vue";
import axios from 'axios';
// import qs from 'qs';
const file = ref(null);
const chunkSize = 5 * 1024 * 1024; // 5MB 分块大小
const uploadProgress = ref(-1); // 上传进度

const handleFileChange = (event) => {
    file.value = event.target.files[0];
};

const testvideo = () => {
    try {
        const response = axios.post('http://localhost:7803/video/test');
        console.log(response)
    } catch (error) {
        console.error('块上传失败', error);
        throw error;
    }
}

const uploadChunks = async () => {
    if (!file.value) return;

    const totalChunks = Math.ceil(file.value.size / chunkSize);
    let currentChunk = 0;

    const uploadChunk = async (chunk, index) => {
        const formData = new FormData();
        formData.append('file', chunk);
        formData.append('filename', file.value.name);
        formData.append('chunkIndex', index);
        formData.append('totalChunks', totalChunks);

        try {
            let service = axios.create({})
            const response = await service({
                url: 'http://localhost:7803/chunk',
                method: 'POST',
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
                data: formData
            });
            return response.data;
        } catch (error) {
            console.error('块上传失败', error);
            throw error;
        }
    };

    for (let start = 0; start < file.value.size; start += chunkSize) {
        const chunk = file.value.slice(start, start + chunkSize);
        await uploadChunk(chunk, currentChunk);
        currentChunk++;

        // 更新上传进度
        uploadProgress.value = Math.floor((currentChunk / totalChunks) * 100);
    }

    // 所有块上传完成后，通知后端合并块
    await mergeChunks(totalChunks);
};

const mergeChunks = async (totalChunks) => {
    try {
        const response = await axios.post('http://localhost:7803/merge', {
            filename: file.value.name,
            totalChunks: totalChunks
        });
        console.log('合并完成', response.data);
    } catch (error) {
        console.error('合并失败', error);
    }
};


</script>

<style scoped>
/* 简单的样式 */
button {
    margin-top: 10px;
}
</style>