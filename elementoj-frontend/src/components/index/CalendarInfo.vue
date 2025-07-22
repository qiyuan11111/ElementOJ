<template>
    <div class="am-u-md-4 lg-punch am-text-center" style="margin: 30px 0;">
        <div class="lg-punch-padding am-show-lg"></div>
        <div class="lg-index-calendar lg-fg-greendark">
            <span class="lg-punch-small ">{{ getMonthText() }}</span>
            <span class="lg-punch-big">{{ getDayText() }}</span>
            <span class="lg-punch-small ">{{ getWeekText() }}</span>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 lg-small">
                <!--距 <strong>NOI2017</strong> 还剩 <strong>-2253 天</strong><br>-->
                <!--距 <strong>CSP-J/S 2023 第一轮</strong> 还剩 <strong>-1 天</strong><br>-->
                距 <strong>中秋国庆</strong> 还剩 <strong>? 天</strong><br>
                距 <strong>开题</strong> 还剩 <strong>? 天</strong><br>
                <img style="height: 10px" :src="img">
            </div>
        </div>
        <div style="clear: both"></div>
        <ExpostulationCard/>
    </div>
    <div style="clear: both"></div>
</template>

<script setup>
import ExpostulationCard from "@/components/index/ExpostulationCard.vue";

const img = require('@/assets/img/test.webp')

const getMonth = () => {
    let mon = new Date().getMonth() + 1;
    return mon;
}

const getWeekText = () => {
    let week = new Date().getDay();
    let arr = ['日', '一', '二', '三', '四', '五', '六'];
    return "星期" + arr[week]
}

const getDayText = () => {
    let date = new Date().getDate();
    return date
}
const getMonthText = () => {
    let mon = getMonth()
    switch (mon) {
        case 1:
            return "一月大"
        case 2:
            return "二月平"
        case 3:
            return "三月大"
        case 4:
            return "四月小"
        case 5:
            return "五月大"
        case 6:
            return "六月小"
        case 7:
            return "七月大"
        case 8:
            return "八月大"
        case 9:
            return "九月小"
        case 10:
            return "十月大"
        case 11:
            return "十一月小"
        case 12:
            return "十二月大"
    }
}
</script>

<style scoped>
[class*=am-u-] + [class*=am-u-]:last-child {
    float: right;
}

@media only screen and (min-width: 641px) {
    [class*=am-u-] {
        padding-left: 1rem;
        padding-right: 1rem;
    }

    .am-u-md-4 {
        width: 33.33333333%;
    }
}


.am-text-center {
    text-align: center !important;
}


[class*=am-u-] {
    width: 100%;
    padding-left: 1rem;
    padding-right: 1rem;
    float: left;
    position: relative;
}

.lg-index-calendar {
    margin-top: 10px;
    margin-bottom: 25px;
}

.lg-fg-greendark {
    color: rgba(5, 67, 16, 0.79) !important;
}

.lg-punch .lg-punch-small {
    display: inline-block;
    width: 20px;
    vertical-align: top;
    line-height: 17px;
    margin-left: 10px;
    margin-right: 10px;
    font-weight: bold;
}

.lg-punch .lg-punch-big {
    display: inline-block;
    font-size: 85px;
    vertical-align: top;
    line-height: 55px;
    font-weight: bolder;
}

.lg-punch .lg-punch-small {
    display: inline-block;
    width: 20px;
    vertical-align: top;
    line-height: 17px;
    margin-left: 10px;
    margin-right: 10px;
    font-weight: bold;
}
</style>