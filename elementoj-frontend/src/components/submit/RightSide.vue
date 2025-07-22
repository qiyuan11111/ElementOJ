<template>
    <div class="right-side">
        <v-ace-editor class="right-editor" :value="props.code" :lang="props.lang" theme="twilight" :options="{
              enableBasicAutocompletion: true,
              enableSnippets: true,
              enableLiveAutocompletion: true,
              fontSize: 18,
              tabSize: 4,
              showPrintMargin: false,
              highlightActiveLine: true,
        }"/>
    </div>
</template>

<script setup>
import {defineProps, ref} from "vue";
import {VAceEditor} from "vue3-ace-editor";
import '@/assets/js/ace-config'
import 'ace-builds/css/theme/twilight.css'

const props = defineProps({
    lang:{
        type: String,
        default: "c_cpp"
    },
    code:{
        type: String,
        default: ""
    }
})

</script>

<style scoped>
.right-editor {
    height: 100%;
    width: 100%;
}

.right-side {
    width: 49.82vw;
    padding-bottom: 10px;
    padding-top: 10px;
    padding-right: 5px;
    background-color: black;
}
</style>