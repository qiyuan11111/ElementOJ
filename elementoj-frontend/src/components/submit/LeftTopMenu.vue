<template>
    <div class="left-top-menu prob-header cm-performance-optimistic animate__animated animate__fadeInLeft">
        <button class="btn back-btn btn-outline-secondary" @click="backToProblem">
            <font-awesome-icon icon="fa-regular fa-hand-point-left" class="back-icon"/>
            Back
        </button>
        <div class="info-badge">
            <font-awesome-icon icon="fa-solid fa-star" fixed-width class="info-icon"/>
            ---
        </div>
        <div class="info-badge">
            <svg class="svg-inline--fa fa-timer fa-fw info-icon" aria-hidden="true" focusable="false"
                 data-prefix="fas" data-icon="timer" role="img" xmlns="http://www.w3.org/2000/svg"
                 viewBox="0 0 512 512">
                <path class="" fill="currentColor"
                      d="M256 0c-17.7 0-32 14.3-32 32V96c0 17.7 14.3 32 32 32s32-14.3 32-32V66.7C378.8 81.9 448 160.9 448 256c0 106-86 192-192 192S64 362 64 256c0-53.7 22-102.3 57.6-137.1c12.6-12.4 12.8-32.6 .5-45.3S89.5 60.8 76.8 73.1C29.5 119.6 0 184.4 0 256C0 397.4 114.6 512 256 512s256-114.6 256-256S397.4 0 256 0zM193 159c-9.4-9.4-24.6-9.4-33.9 0s-9.4 24.6 0 33.9l80 80c9.4 9.4 24.6 9.4 33.9 0s9.4-24.6 0-33.9l-80-80z"/>
            </svg>
            ---
        </div>
        <div class="info-badge" >
            <font-awesome-icon fixed-width icon="fa-solid fa-database" class="info-icon"/>
            ---
        </div>
    </div>
</template>

<script setup>
/* eslint-disable */
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {library} from "@fortawesome/fontawesome-svg-core";
import {faHandPointLeft} from "@fortawesome/free-regular-svg-icons";
import {useRouter} from "vue-router";
import {defineProps} from "vue";
import {faDatabase, faStar} from "@fortawesome/free-solid-svg-icons";

library.add(faHandPointLeft, faStar, faDatabase)

const router = useRouter()

const props = defineProps({
    pid: Number,
    time: Number,
    memory: Number,
})
const backToProblem = () => {
    router.push({path: "/problem", query: {pid: props.pid}})
}
</script>

<style scoped>
.prob-header {
    color: #7a8e97;
    display: flex;
    align-items: center;
    padding-top: 2rem;
    padding-bottom: 2rem;
    position: -webkit-sticky;
    position: sticky;
    top: 0;
    background: linear-gradient(0, hsla(0, 0%, 98%, 0), #fafafa 20%);
    z-index: 1;
}

.cm-performance-optimistic {
    will-change: opacity;
}

.back-btn {
    margin-bottom: 0;
}

.back-icon{
    margin-right: 3px;
}
.info-icon{
    margin-right: 5px;
}

.info-badge {
    display: flex;
    margin-left: 1rem;
    white-space: normal;
    margin-bottom: 0;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: center;
    align-items: center;
}
</style>