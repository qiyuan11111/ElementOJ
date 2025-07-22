// import { createStore } from 'vuex';
import {reactive} from 'vue';

const store = {
    namespaced: true,
    state: reactive({
        user:{
            a : "555",
            b: "asdfas",
        },
        isAuth: 0
    }),
    mutations: {
        setAuth(state) {
            state.isAuth++;
        }
    }
};

export default store;