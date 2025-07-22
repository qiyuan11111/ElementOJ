import createPersistedState from "vuex-persistedstate";

export default {
    state: {},
    mutations: {},
    actions: {},
    getters: {},
    plugins: [
        createPersistedState({
            storage: window.localStorage,
            key: "ele-account",
            paths: ["user", "isAuth"],
        })
    ]
}