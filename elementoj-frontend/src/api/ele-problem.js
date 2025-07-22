import axios from '@/utils/axios';

export const getProblemList = () => axios.post('/problem/list');

export const getProblemDetailById = (params) => axios.post('/problem/get', params);