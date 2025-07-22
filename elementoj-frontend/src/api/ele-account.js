import axios from '@/utils/axios';

export const submitLogin = (params) => axios.post('/auth/login', params);

export const submitRegister = (params) => axios.post('/auth/register', params);

export const getUserInfoByAccessToken = (params) => axios.post('/auth/info', params);