<template>
    <div class="bottom-left-menu">
        <RelaxButton :active="true">
            <font-awesome-icon icon="fa-solid fa-book" fixed-width />
        </RelaxButton>
        <RelaxButton :active="true">
            <font-awesome-icon icon="fa-solid fa-square-pen" fixed-width />
        </RelaxButton>
        <RelaxButton>
            <font-awesome-icon icon="fa-solid fa-clock-rotate-left" fixed-width />
            <span style="margin-left: 5px">History</span>
        </RelaxButton>
    </div>
</template>

<script setup>
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {library} from "@fortawesome/fontawesome-svg-core";
import {faBook, faClockRotateLeft, faSquarePen} from "@fortawesome/free-solid-svg-icons";

library.add(faBook, faSquarePen, faClockRotateLeft)

</script>

<style scoped>
.bottom-left-menu{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: flex-start;
    align-items: center;
}
</style>