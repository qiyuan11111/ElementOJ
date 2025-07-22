import axios from 'axios';
import qs from 'qs';
import {getAccessToken} from "@/utils/cookie";
// import {getAccessToken, getRefreshToken} from "@src/utils/cookie";

axios.defaults.baseURL = 'api';

// 响应拦截器
axios.interceptors.response.use(
    res => res.data,  // 拦截到响应对象，将响应对象的 data 属性返回给调用的地方
    err => Promise.reject(err)
)

axios.interceptors.request.use(config => {
    if (config.url !== "/refresh" && config.url !== "/auth/login"){
        config.headers['Authorization'] = "Bearer " + getAccessToken()
    }

    config.data = convertKeysToCamelCase(config.data);

    //发请求前做的一些处理，数据转化，配置请求头，设置token,设置loading等，根据需求去添加
    if (config.method.toLowerCase() === "get") { //配置get请求数据(这里是容错处理)
        if (config.data !== undefined) config.params = config.data
    }
    if (config.method.toLowerCase() === "post") { //post请求配置数据转换和请求头
        config.data = qs.stringify(config.data); //数据转化,也可以使用qs转换
        config.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
        // config.headers['Content-Type'] = 'application/json;charset=UTF-8'
    }

    return config
}, error => {
    Promise.reject(error)
});

function convertKeysToCamelCase(obj) {
    if (obj && typeof obj === 'object') {
        const result = {};
        Object.keys(obj).forEach(key => {
            const camelCaseKey = key.replace(/_([a-z])/g, (match, group) => group.toUpperCase());
            result[camelCaseKey] = convertKeysToCamelCase(obj[key]);
        });
        return result;
    } else if(obj && Array.isArray(obj)) {
        const result = [];
        obj.forEach(item => {
            result.push(convertKeysToCamelCase(item));
        });
        return result;
    } else {
        return obj;
    }
}

export default axios