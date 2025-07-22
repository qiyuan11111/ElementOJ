<template>
    <button @click="handleClick" type="button" :class="['btn', 'btn-secondary', active? 'cm-active': '']">
        <slot></slot>
    </button>
</template>

<script setup>
/* eslint-disable */
import {defineProps} from "vue";

const props = defineProps({
    active: {
        type: Boolean,
        default: false
    }
})

const emit = defineEmits(['click']);
const handleClick = () => {
    emit('click');
}
</script>

<style scoped>
.btn{
    padding: 0.75rem 1rem;
    margin-right: 10px;
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: flex-start;
    align-items: center;
    white-space: nowrap;
    margin-bottom: 0px;
}
.cm-active{
    box-shadow: inset 0 0 15px rgba(0,0,0,.25);
}
</style>