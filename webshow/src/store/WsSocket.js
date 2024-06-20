export default{
    state: {
        status:"matching",
        wsurl:"",
        socket:"",
        opponent_name:"",
        turn:" "
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
        },

        updateTurn(state,turn){
            state.turn=turn
        }
    },
    actions: {

    },
    modules: {

    }
}
