export default{
    state: {
        status:"matching",
        wsurl:"",
        socket:"",
        opponent_name:""
    },
    getters: {
        
    },
    mutations: {
        updateStatus(state,status){
            state.status=status
        },
        updateUrl(state,wsurl){
            state.wsurl=wsurl
        },
        updateName(state,name){
            state.opponent_name=name
        }
    },
    actions: {

    },
    modules: {

    }
}
