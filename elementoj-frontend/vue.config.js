const { defineConfig } = require('@vue/cli-service')
const webpack = require('webpack')
const AutoImport = require('unplugin-auto-import/webpack')
const Components = require('unplugin-vue-components/webpack')
const { ElementPlusResolver } = require('unplugin-vue-components/resolvers')

module.exports = defineConfig({
    transpileDependencies: true,
    configureWebpack: {
        resolve: {
            alias: {
                components: '@/components'
            }
        },
        //配置webpack自动按需引入element-plus，
        plugins: [
            AutoImport({
                resolvers: [ElementPlusResolver()]
            }),
            Components({
                resolvers: [ElementPlusResolver()]
            }),
            new webpack.ProvidePlugin({
                $: 'jquery',
                jQuery: 'jquery',
                'windows.jQuery': 'jquery',
                Popper: ["popper.js", "default"]
            })
        ]
    },
    devServer: {
        // 配置服务器代理
        proxy: {
            "/expostulation": { // 代理接口前缀为/apis的请求
                "target": 'http://localhost:8501/news', // 对应s的代理地址
                "secure": false, // 接受运行在https上，默认不接受
                "changeOrigin": true, // 如果设置为true,那么本地会虚拟一个服务器接收你的请求并代你发送该请求，这样就不会有跨域问题（只适合开发环境）
                "pathRewrite": { //重写路径 比如'/apis/aaa/ccc'重写为'/aaa/ccc'
                    '^/api': ''
                }
            },
            "/auth": { // 代理接口前缀为/apis的请求
                "target": 'http://localhost:8501/', // 对应的代理地址
                "secure": false, // 接受运行在https上，默认不接受
                "changeOrigin": true, // 如果设置为true,那么本地会虚拟一个服务器接收你的请求并代你发送该请求，这样就不会有跨域问题（只适合开发环境）
                "pathRewrite": { //重写路径 比如'/apis/aaa/ccc'重写为'/aaa/ccc'
                    '^/api': ''
                }
            },
            "/problem": { // 代理接口前缀为/apis的请求
                "target": 'http://localhost:8501/problem', // 对应的代理地址
                "secure": false, // 接受运行在https上，默认不接受
                "changeOrigin": true, // 如果设置为true,那么本地会虚拟一个服务器接收你的请求并代你发送该请求，这样就不会有跨域问题（只适合开发环境）
                "pathRewrite": { //重写路径 比如'/apis/aaa/ccc'重写为'/aaa/ccc'
                    '^/api': ''
                }
            },
            "/test":{
                "target": 'http://localhost:8301/',
                "secure": false, // 接受运行在https上，默认不接受
                "changeOrigin": true, // 如果设置为true,那么本地会虚拟一个服务器接收你的请求并代你发送该请求，这样就不会有跨域问题（只适合开发环境）
                "pathRewrite": { //重写路径 比如'/apis/aaa/ccc'重写为'/aaa/ccc'
                    '^/api': ''
                }
            }
            // 配置多个代理
            // "/service": {
            //     "target": 'https://www.google.com/',
            //     "secure": false,
            //     "changeOrigin": true,
            // },
        }
    }
})
