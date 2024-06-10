import {Game_Obj} from './Game_Obj.js'
export class ChessBoard extends Game_Obj{
    constructor(ctx,canvasSize,size,margin,gridSize){
        super()
        this.ctx=ctx
        this.canvasSize = canvasSize;
        this.size = size;
        this.margin = margin;
        this.gridSize = gridSize;
    }
    update(){
        //this.ctx.clearRect(0,0,this.canvasSize,this.canvasSize)
        this.render()
        
    }
    render(){
        this.ctx.fillStyle = '#DEB887'; // 使用米色背景
            this.ctx.fillRect(0, 0, this.canvasSize, this.canvasSize);

            // 绘制网格线
            this.ctx.strokeStyle = '#000000'; // 黑色线条
            for (let i = 0; i < this.size; i++) {
             // 垂直线
              this.ctx.beginPath();
              this.ctx.moveTo(this.margin + i * this.gridSize, this.margin);
              this.ctx.lineTo(this.margin + i * this.gridSize, this.canvasSize - this.margin);
              this.ctx.stroke();
            
              // 水平线
               this.ctx.beginPath();
              this.ctx.moveTo(this.margin, this.margin + i * this.gridSize);
              this.ctx.lineTo(this.canvasSize - this.margin, this.margin + i * this.gridSize);
              this.ctx.stroke();
            }

            // 绘制星位
            const starPositions = [3, 9, 15];
            starPositions.forEach(x => {
              starPositions.forEach(y => {
                this.drawStar(this.ctx, this.margin + x * this.gridSize, this.margin + y * this.gridSize);
              });
            });
    }
    drawStar(ctx, x, y) {
        ctx.beginPath();
        ctx.arc(x, y, 3, 0, Math.PI * 2, true);
        ctx.fillStyle = '#000000'; // 黑色星位
        ctx.fill();
    }
}