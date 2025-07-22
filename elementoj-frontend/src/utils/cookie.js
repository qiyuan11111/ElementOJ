// import {error} from "@src/utils/error.js";
/* eslint-disable */
import {jwtDecode} from "jwt-decode";
import store from "@/store/index.js";
import {getUserInfoByAccessToken} from "@/api/ele-account";
import {toRaw} from "vue";

const LOGIN_ACCESS_TOKEN = 'accessToken'
const LOGIN_REFRESH_TOKEN = 'refreshToken'

export function isAuthenticated() {
    return store.state.user.isAuth;
}

// export function refreshUserInfo() {
//     if (!isAuthenticated()) {
//         getUserInfoByAccessToken({type: "all"}).then(res => {
//             // console.log(toRaw(store.state.user.user))
//             store.commit('user/setUser', res.data)
//             store.commit("user/setAuth", true)
//             // console.log(toRaw(store.state.user.user))
//             // console.log(store.state.user.isAuth)
//         }).catch(() => {
//
//         })
//     }
// }

export function getAccessToken() {
    return _getCookie(LOGIN_ACCESS_TOKEN)
}

export function getRefreshToken() {
    return _getCookie(LOGIN_REFRESH_TOKEN)
}

export function setAccessToken(token) {
    _setCookie(LOGIN_ACCESS_TOKEN, token)
    // refreshUserInfo()
}

export function setRefreshToken(token) {
    _setCookie(LOGIN_REFRESH_TOKEN, token)
}

export function logout() {
    _setCookie(LOGIN_ACCESS_TOKEN, '', 0)
    _setCookie(LOGIN_REFRESH_TOKEN, '', 0)
    // store.commit('user/logout')
    // store.commit("user/setAuth", false)
}

function _getCookie(name) {
    let start, end
    if (document.cookie.length > 0) {
        start = document.cookie.indexOf(name + '=')
        if (start !== -1) {
            start = start + name.length + 1
            end = document.cookie.indexOf(';', start)
            if (end === -1) {
                end = document.cookie.length
            }
            return unescape(document.cookie.substring(start, end))
        }
    }
    return ''
}

function _setCookie(name, value, expire) {
    let date = new Date()
    date.setDate(date.getDate() + expire)
    document.cookie = name + '=' + escape(value) + '; path=/' +
        (expire ? ';expires=' + date.toGMTString() : '')
}