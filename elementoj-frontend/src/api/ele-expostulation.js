import axios from '@/utils/axios';
export const getRandomExpostulation = () => axios.post('/expostulation/getRandom');
export const test = (params) => axios.post('/expostulation/test', params);