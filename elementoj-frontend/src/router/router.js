import {createRouter, createWebHashHistory} from 'vue-router'

const routes = [
    {
        path: '/submit',
        component: () => import('@/view/SubmitPage.vue'),
    }, {
        path: '/',
        component: () => import('@/module/SpaceBetween.vue'),
        children: [{
            path: '',
            components: {
                default: () => import('@/view/IndexPage.vue'),
            }
        }, {
            path: 'problemset',
            components: {
                default: () => import('@/view/ProblemSetPage.vue'),
            }
        }, {
            path: 'problem',
            components: {
                default: () => import('@/view/ProblemPage.vue'),
            }
        }, {
            path: 'login',
            components: {
                default: () => import('@/view/AccountPage.vue'),
            },
            children: [{
                path: '',
                component: () => import('@/components/account/LoginCard.vue'),
            }]
        }, {
            path: 'register',
            components: {
                default: () => import('@/view/AccountPage.vue'),
            },
            children: [{
                path: '',
                component: () => import('@/components/account/RegisterCard.vue'),
            }]
        }]
    },
]
const router = createRouter({
    // history: createWebHistory(),
    history: createWebHashHistory(),
    routes,
})

export default router;