<template>
    <div class="bottom-submit-side">
        <BottomLeftMenu/>
        <BottomRightMenu :pid="props.pid" :lang="props.lang"/>
    </div>
</template>

<script setup>
import {defineProps} from "vue";

const props = defineProps({
    lang:{
        type: String,
        default: "c_cpp"
    },
    pid: Number
})
</script>

<style scoped>
.bottom-submit-side{
    height: 4rem;
    flex-grow: 0;
    flex-shrink: 0;
    box-shadow: 0 0 40px rgba(0,0,0,.15);
    border-top: 1px solid rgba(0,0,0,.15);
    background-image: linear-gradient(120deg,#fdfbfb,#ebedee);
    padding: 0.5rem 1.25rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
    overflow-y: hidden;
    overflow-x: auto;
}
</style>