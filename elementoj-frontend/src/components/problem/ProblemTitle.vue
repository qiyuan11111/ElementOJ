<template>
    <div class="title-info">
        <div class="title">
            <h3>{{ props.pid + '.' + props.title }}</h3>
        </div>
        <div class="limit-info" v-if="showSubInfo">
            <span class="time-limit">
                <font-awesome-icon icon="fa-solid fa-clock"/>
                <span>{{ ' ' + props.time + ' ' }}ms{{ " " }}{{ " " }}{{ " " }}{{ " " }}</span>
            </span>
            <span class="memory-limit">
                <font-awesome-icon icon="fa-solid fa-database"/>
                <span>{{ ' ' + props.memory / 1024 + ' ' }}KB{{ " " }}{{ " " }}{{ " " }}{{ " " }}</span>
            </span>
            <span class="io">
                <font-awesome-icon icon="fa-solid fa-right-to-bracket" />
                <span> Standard I/O</span>
            </span>
        </div>
    </div>
</template>

<script setup>
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {library} from "@fortawesome/fontawesome-svg-core";
import {faRightToBracket, faClock, faDatabase} from "@fortawesome/free-solid-svg-icons";

library.add(faClock, faRightToBracket, faDatabase)

import { defineProps } from 'vue'

const props = defineProps({
    title: String,
    pid: Number,
    time: Number,
    memory: Number,
    showSubInfo: {
        type: Boolean,
        default: true
    }
})

</script>

<style scoped>
.title-info {
    font-family: 'Consolas', serif;
    white-space: pre;
}

.title {
    text-align: center;
    color: #7a8e97;
    font-family: Consolas,Roboto-slab;
    margin-top: 20px;
    margin-bottom: 15px;
}

.title h3{
    font-size: 26px !important;
}

.limit-info {
    text-align: center;
    margin-top: 10px;
    color: #7a8e97;
}

.time-limit svg, .memory-limit svg, .io svg {
    font-size: small;
}
</style>