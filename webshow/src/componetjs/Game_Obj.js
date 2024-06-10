const Game_Object = [];
export class Game_Obj{
    constructor(){
        Game_Object.push(this)
        this.timedelta=0;
    }
    update(){

    }


}

let last_timestamp
const step = timestamp => {
    for(let obj of Game_Object){
        obj.timedelta=timestamp-last_timestamp
        obj.update()
    }
    last_timestamp=timestamp
    requestAnimationFrame(step)
}

requestAnimationFrame(step)




