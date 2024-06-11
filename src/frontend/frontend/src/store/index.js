import { createStore } from 'vuex';

export default createStore({
    state: {
        finca: null,
    },
    mutations: {
        setFinca(state, finca) {
            state.finca = finca;
        },
    },
});