<template>
    <div class="register-card">
        <el-form :model="form" style="padding-top: 0" @submit="register" :rules="rules" ref="ruleFormRef" label-width="80px">
            <el-form-item label="账号" size="large" prop="username">
                <el-input v-model="form.user_name" placeholder="User Name">
                </el-input>
            </el-form-item>
            <el-form-item label="邮箱" size="large" prop="email">
                <el-input v-model="form.email" placeholder="Email">
                </el-input>
            </el-form-item>
            <el-form-item label="密码" size="large" prop="password">
                <el-input v-model="form.password" placeholder="Password" type="password" show-password>
                </el-input>
            </el-form-item>
            <el-form-item label="重复密码" size="large" prop="repassword">
                <el-input v-model="form.repassword" placeholder="Repeat Password" type="password" show-password>
                </el-input>
            </el-form-item>
            <div style="text-align: right !important;">
<!--                <el-button-->
<!--                    type="danger"-->
<!--                    text-->
<!--                    size="large"-->
<!--                >重置-->
<!--                </el-button>-->
                <el-button
                    native-type="submit"
                    type="primary"
                    text
                    size="large"
                    style="margin-left: 0px"
                >注册
                </el-button>
            </div>

        </el-form>
    </div>
</template>

<script setup>

import {reactive, ref} from "vue";
import {submitRegister} from "@/api/ele-account";

const form = reactive({
    user_name: "",
    email: "",
    password: "",
    repassword: ""
})

const ruleFormRef = ref()

const validateUserName = (rule, value, callback) => {
    if (value === null || value.length <= 0) {
        callback(new Error('请输入账号！'))
    } else if (value.length <= 1 || value.length >= 20) {
        callback(new Error("账号长度需大于1且小于20！"))
    } else {
        callback()
    }
}

const validatePassword = (rule, value, callback) => {
    if (value === null || value.length <= 0) {
        callback(new Error('请输入密码！'))
    } else if (value.length <= 1 || value.length >= 20) {
        callback(new Error("密码长度需大于1且小于20！"))
    } else {
        callback()
    }
}

const validateRepassword = (rule, value, callback) => {
    if (value !== form.password) {
        callback(new Error('两次密码输入不一致！'))
    } else {
        callback()
    }
}

const validateEmail = (rule, value, callback) => {
    let regrex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-]{2,})+$/
    if (value === null || value.length <= 0) {
        callback(new Error('请输入邮箱！'))
    } else if (!regrex.test(value)) {
        callback(new Error("邮箱格式不正确！"))
    } else {
        callback()
    }
}

const rules = reactive({
    user_name: [
        {validator: validateUserName, trigger: 'change'},
    ],
    repassword:[
        {validator: validateRepassword, trigger: 'change'},
    ],
    password:[
        {validator: validatePassword, trigger: 'change'},
    ],
    email:[
        {validator: validateEmail, trigger: 'change'},
    ]
})

const register = (e) => {
    e.preventDefault()

    ruleFormRef.value.validate((valid) => {
        if (!valid) return false
    })

    submitRegister(form).then(res => {
        console.log(res)

    }).catch(() => {
    })
}

</script>

<style scoped>

</style>