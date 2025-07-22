<template>
    <div class="bottom-right-menu">
        <RelaxButton>
            <i class="devicon-c-plain colored active-language-icon"></i>
            C
            <font-awesome-icon style="margin-left: 0.5rem" icon="fa-solid fa-caret-up" fix-width />
        </RelaxButton>
        <RelaxButton class="submit-btn">
            <font-awesome-icon class="action-icon" fixed-width icon="fa-solid fa-paper-plane" />
            Submit Code
        </RelaxButton>
        <RelaxButton class="submit-info-btn">
            <font-awesome-icon icon="fa-solid fa-gear" />
        </RelaxButton>
    </div>
</template>

<script setup>
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {library} from "@fortawesome/fontawesome-svg-core";
import {faCaretUp, faGear, faPaperPlane} from "@fortawesome/free-solid-svg-icons";
import {defineProps} from "vue";

library.add(faCaretUp, faPaperPlane, faGear)

const props = defineProps({
    pid: Number,
    lang:{
        type: String,
        default: "c_cpp"
    }
})
</script>

<style scoped>
.active-language-icon{
    padding-right: 0.5rem;
    display: inline-block;
    transform: scale(1.3);
}
.submit-btn{
    margin-right: 0px !important;
    border-right-width: 2px;
    border-right-style: solid;
    border-right-color: #d3d3d3;
}
.submit-info-btn{
    font-size: 17px;
}
.bottom-right-menu{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: center;
    justify-content: flex-end;
    align-items: center;
}
.action-icon{
    margin-right: 0.5rem;
}
</style>