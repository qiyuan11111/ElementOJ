<template>
    <div class="login-card">
        <el-form :model="form" style="padding-top: 0" @submit="login" :rules="rules" ref="ruleFormRef" label-width="50px">
            <el-form-item label="账号" size="large" prop="username">
                <el-input v-model="form.user_name" placeholder="User Name">
                </el-input>
            </el-form-item>
            <el-form-item label="密码" size="large" prop="password">
                <el-input v-model="form.password" placeholder="Password" type="password" show-password>
                </el-input>
            </el-form-item>
            <div style="text-align: right !important;">
                <el-button
                    type="info"
                    text
                    size="large"
                >忘记密码？</el-button>
                <el-button
                    native-type="submit"
                    type="primary"
                    text
                    size="large"
                    style="margin-left: 0px"
                >登录</el-button>

            </div>


        </el-form>
    </div>
</template>

<script setup>
/* eslint-disable */
import {reactive, ref} from "vue";
import {submitLogin} from "@/api/ele-account";
import {setAccessToken, setRefreshToken} from "@/utils/cookie";
import {useRouter} from "vue-router";
import {userException} from "@/assets/js/exception/ele-user";
import {useStore} from "vuex";

const router = useRouter();
const store = useStore();

const form = reactive({
    user_name: "",
    password: "",
    grant_type: 'password',
    scope: 'webclient',
    client_id: 'elementoj',
    client_secret: '123456'
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

const rules = reactive({
    user_name: [
        {validator: validateUserName, trigger: 'change'},
    ],
    password:[
        {validator: validatePassword, trigger: 'change'},
    ]
})

// const validate = () => {
//     ruleFormRef.value.validate((valid) => {
//         return valid;
//     })
// }

const login = (e) => {
    e.preventDefault()

    ruleFormRef.value.validate((valid) => {
        if (!valid) return false
    })

    submitLogin(form).then(res => {
        // let code = res.code
        let data = res.data

        console.log(data)
        // switch (code) {
        //     case eleUserEpt.ELE_USER_INCONSISTENT_PASSWORDS:
        //     case eleUserEpt.ELE_USER_IRREGULAR_PASSWORD:
        //     case eleUserEpt.ELE_USER_NONE_PASSWORD:
        //     case eleUserEpt.ELE_USER_NONE_USERNAME:
        //     case eleUserEpt.ELE_USER_IRREGULAR_USERNAME:
        //         break;
        //     default: {
        //         console.log("yes1")
        //         break;
        //     }
        // }
        // console.log("yes")

        let refresh_token = data.refresh_token
        let access_token = data.access_token

        setAccessToken(access_token)
        setRefreshToken(refresh_token)
        // store.commit('user/setAuth', true)

        store.dispatch('user/refreshUserInfo').then(() => {
            router.push({path: '/'})
        })
        //
        //

    }).catch(() => {
    })
}
</script>

<style lang="scss">




</style>