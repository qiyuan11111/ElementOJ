<template>
    <div class="top-submit-side">
        <div class="pre-move-slider"></div>
        <LeftSide class="left-side" :problem="props.problem"/>
        <div class="move-slider"></div>
        <RightSide :lang="props.lang" :code="props.code" class="right-side"/>
    </div>
</template>

<script setup>
/* eslint-disable */
import {defineProps, toRef} from "vue";
import $ from "jquery";

const props = defineProps({
    problem: {
        type: Object,
        default: {
            title: "Title",
            problemId: 1,
            problemDescription: "Description",
            inputDescription: "Input",
            outputDescription: "Output",
            hint: "Hint",
            timeLimit: 0,
            memoryLimit: 0,
        }
    },
    lang:{
        type: String,
        default: "c_cpp"
    },
    code:{
        type: String,
        default: ""
    }
})

let $moux = "49%";
$(document).ready(function () {
    $(".move-slider").mousedown(function () {
        $(".pre-move-slider").fadeIn(0)
        $(".pre-move-slider").mousemove(function (e) {
            if (e.pageX <= window.innerWidth * 0.9 && e.pageX >= window.innerWidth * 0.4) {
                $moux = e.pageX - 0.0035 * window.innerWidth;
            }
            else if (e.pageX > window.innerWidth * 0.9) {
                $moux = window.innerWidth * 0.9 - 0.0035 * window.innerWidth;
            }
            else {
                $moux = window.innerWidth * 0.4 - 0.0035 * window.innerWidth;
            }
            let $leftlen = $moux / window.innerWidth * 100;
            let $rightlen = (window.innerWidth * (1 - 0.0035) - $moux) / window.innerWidth * 100;

            $(".left-side").css("width", $leftlen + "vw");
            $(".right-side").css("width", $rightlen + "vw");

        });

    });
    $(".pre-move-slider").mouseup(function () {
        $(".pre-move-slider").fadeOut(0)
    });
})

</script>

<style scoped>
.pre-move-slider{
    display: none;
    position: fixed;
    z-index: 1050;
    width: 100%;
    height: 658px;
    overflow: hidden;
    cursor: ew-resize;
}
.move-slider{
    display: block;
    width: 0.36vw;
    height: 100%;
    background: linear-gradient(90deg,#fafafa,#d4d4d4);
    cursor: ew-resize;
}
.top-submit-side{
    display: flex;
    flex-grow: 1;
    flex-shrink: 1;
    width: 100vw;
    justify-content: space-between;
    overflow: hidden;
}
</style>