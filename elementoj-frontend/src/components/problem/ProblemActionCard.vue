<template>
    <div class="problem-action-card">
        <el-card class="box-card animate__animated animate__slideInRight" style="width: 100%">
            <el-button class="submit-code" text @click="toSubmitPage">
                <font-awesome-icon class="action-icon" fixed-width icon="fa-solid fa-paper-plane" />
                Submit
            </el-button>
            <el-divider  class="card-divider"/>
            <el-button class="submit-code" text>
                <font-awesome-icon class="action-icon" fixed-width icon="fa-solid fa-check-to-slot" />
                Submitted Info
            </el-button>
            <el-button class="submit-code" text>
                <svg class="svg-inline--fa fa-file-chart-column fa-fw action-icon" aria-hidden="true" focusable="false"
                     data-prefix="fas" data-icon="file-chart-column" role="img"  xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512">
                    <path class="" fill="currentColor" d="M64 0C28.7 0 0 28.7 0 64V448c0 35.3 28.7 64 64 64H320c35.3 0 64-28.7 64-64V160H256c-17.7 0-32-14.3-32-32V0H64zM256 0V128H384L256 0zM216 248V424c0 13.3-10.7 24-24 24s-24-10.7-24-24V248c0-13.3 10.7-24 24-24s24 10.7 24 24zm88 64V424c0 13.3-10.7 24-24 24s-24-10.7-24-24V312c0-13.3 10.7-24 24-24s24 10.7 24 24zM128 376v48c0 13.3-10.7 24-24 24s-24-10.7-24-24V376c0-13.3 10.7-24 24-24s24 10.7 24 24z"/></svg>
                Testcase Data
            </el-button>
        </el-card>
    </div>
</template>

<script setup>
/* eslint-disable */
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {library} from "@fortawesome/fontawesome-svg-core";
import {faCheckToSlot, faPaperPlane} from "@fortawesome/free-solid-svg-icons";
import {defineProps} from "vue";
import {useRouter} from "vue-router";

library.add(faPaperPlane, faCheckToSlot)

const router = useRouter()

const props = defineProps({
    pid: Number
})

const toSubmitPage = () => {
    router.push({
        path: '/submit',
        query: {
            pid: props.pid
        }
    })
}

</script>


<style scoped>
.action-icon{
    margin-right: 0.3rem;
}
.card-divider{
    margin: 0rem 0!important;
}
.box-card >>> .el-card__body{
    padding: 0px;
}
.submit-code{
    padding: 0px;
    text-align: left!important;
    justify-content: flex-start;
    padding: 1.35rem 1rem;
    margin: 0.3125rem 0!important;
    /*border-radius: 0!important;*/
    /*color: #6c757d;*/
    /*border-color: #ccc;*/
    width: 100%;
    /*position: relative;*/
    /*flex: 1 1 auto;*/

    /*letter-spacing: 0;*/
    /*cursor: pointer;*/
    /*outline: 0;*/
    /*transition: box-shadow .2s cubic-bezier(.4,0,1,1),background-color .2s cubic-bezier(.4,0,.2,1),color .2s cubic-bezier(.4,0,.2,1);*/
    /*will-change: box-shadow,transform;*/
}
.submit-code >>> span{
    font-size: .875rem;
    text-transform: uppercase;
}
</style>