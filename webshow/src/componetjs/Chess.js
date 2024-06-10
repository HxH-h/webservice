import {Game_Obj} from './Game_Obj.js'
export class Chess extends Game_Obj{
    constructor(ctx,margin,gridSize,rect){
        super()
        this.ctx=ctx
        this.rect=rect
        this.margin=margin
        this.gridSize=gridSize
        this.lastX=0
        this.lastY=0
        this.chessAry=[
            [0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
            [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
        ]
    }
    update(){
        this.render()

    }
    render(){
        let x=0;
        let y=0;
        for(let i=0;i<19;i++){
            for(let j=0;j<19;j++){ 
                if(this.chessAry[i][j]===1){ 
                    x=this.margin+this.gridSize*j;
                    y=this.margin+this.gridSize*i;
                    this.drawChess(x,y,'black')
                }else if(this.chessAry[i][j]===2){
                    x=this.margin+this.gridSize*j;
                    y=this.margin+this.gridSize*i;
                    this.drawChess(x,y,'white')
                }else if(this.chessAry[i][j]===3){
                    x=this.margin+this.gridSize*j;
                    y=this.margin+this.gridSize*i;

                    this.drawChessPossible(x,y)
                }
            }
          
        }
    }
    drawChess(x,y,color){
        this.ctx.beginPath();//开始绘制
        this.ctx.arc(x,y,6,0,2*Math.PI);//arc 的意思是“弧”
        this.ctx.strokeStyle=color;
        this.ctx.fillStyle=color;//设置填充颜色
        this.ctx.fill();
        this.ctx.stroke()
    }
    ChessPossible(x,y){
        if(x<0||y<0||x>18||y>18){
            return ;
        }
        if(this.chessAry[y][x]===0&&(x!=this.lastX||y!=this.lastY)){
            this.chessAry[this.lastY][this.lastX]=0
            this.chessAry[y][x]=3
            this.lastX=x
            this.lastY=y
        }
    }
    drawChessPossible(x,y){
        this.ctx.beginPath();//开始绘制
        this.ctx.arc(x,y,5,0,2*Math.PI);//arc 的意思是“弧”
        this.ctx.strokeStyle="white";
        this.ctx.stroke()
    }
}