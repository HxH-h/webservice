export default{
    state: {
        status:"matching",
        socket:"",
        opponent_name:" "
    },
    getters: {
        
    },
    mutations: {
        updateStatus(state,status){
            state.status=status
        },
        updateSocket(state,socket){
            state.socket=socket
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
