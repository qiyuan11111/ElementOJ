
import {getUserInfoByAccessToken} from "@/api/ele-account";
import {reactive} from "vue";

export default {
    namespaced: true,
    state: reactive({
        user: {
            nickName: "undefined",
            email: "undefined",
        },
        isAuth: false,
    }),
    mutations: {
        setUser(state, user) {
            state.user = user;
        },
        setAuth(state, isAuth) {
            state.isAuth = isAuth;
        },
    },
    actions: {
        refreshUserInfo({commit}) {
            getUserInfoByAccessToken({type: "all"}).then(res => {
                commit('setUser', res.data);
                commit('setAuth', true);
            }).catch(() => {
                commit('setUser', {
                    nickName: "undefined",
                    email: "undefined",
                });
                commit('setAuth', false);
            })
        }
    },
    getters: {},

}