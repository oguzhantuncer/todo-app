import axios from 'axios'

const CONTEXT_PATH = '/todo'

class Service {

    create(request) {
        return axios.post(CONTEXT_PATH, request);
    }

    getAll() {
        return axios.get(CONTEXT_PATH);
    }

    getAllCompleted() {
        return axios.get(`${CONTEXT_PATH}/completed`);
    }

    getAllNotCompleted() {
        return axios.get(`${CONTEXT_PATH}/not-completed`);
    }

    delete(id) {
        return axios.delete(`${CONTEXT_PATH}/${id}`);
    }

    deleteCompleted() {
        return axios.delete(`${CONTEXT_PATH}/completed`);
    }
 
    deleteAll() {
        return axios.delete(`${CONTEXT_PATH}`);
    }

    update(id,request) {
        return axios.put(`${CONTEXT_PATH}/${id}`, request);
    }

}

export default new Service();