import {createStore} from 'vuex';

import user from './modules/user';
// import test from './modules/test';
// import global from './global';

export default createStore({
    ...global,
    modules: {
        user,
        // test
    }
})