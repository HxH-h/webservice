<template>
    <CardItem > 
        <template #head>对战模式 </template>    
        <template #body>
            <canvas ref="canvas" :width="canvasSize" :height="canvasSize" @click="ClickHandle"></canvas>
        </template>
    </CardItem>
</template>


<script >
import {ref, onMounted } from 'vue';
import CardItem from '@/components/CardItem.vue'
export default{
    components:{
        CardItem,
    },
    setup (){
        const canvasSize = 600;
        const size = 19;
        const margin = 30;
        const gridSize = (canvasSize - 2 * margin) / (size - 1);
        const canvas = ref(null);
        function ClickHandle (e){
            var x = e.clientX;
            var y = e.clientY;
            var rect = canvas.value.getBoundingClientRect();
            x -= rect.left;
            y -= rect.top;
            console.log(x, y);
            const ctx = canvas.value.getContext('2d');
            drawChess(ctx,x,y)
        }
        function drawChess(ctx,x,y){
            ctx.beginPath();//开始绘制
            ctx.arc(x,y,4,0,2*Math.PI);//arc 的意思是“弧”
            ctx.strokeStyle="black";
            ctx.fillStyle="black";//设置填充颜色
            ctx.fill();
            ctx.stroke()
        }
        function drawChessPossible(ctx,x,y){
            ctx.beginPath();//开始绘制
            ctx.arc(x,y,4,0,2*Math.PI);//arc 的意思是“弧”
            ctx.strokeStyle="white";
            ctx.stroke()
        }
        function MoveHandle (e){
            var x = e.clientX;
            var y = e.clientY;
            var rect = canvas.value.getBoundingClientRect();
            const ctx = canvas.value.getContext('2d');
            x -= rect.left;
            y -= rect.top;
            x=(x-margin)/gridSize
            y=(y-margin)/gridSize
            
            var dx=x-parseInt(x)
            var dy=y-parseInt(y)
            if(dx>0.5&&dy>0.5){
                x=parseInt(x)+1;
                y=parseInt(y)+1;

            }else if(dx<0.5&&dy>0.5){
                x=parseInt(x);
                y=parseInt(y)+1;

            }else if(dx>0.5&&dy<0.5){
                x=parseInt(x)+1;
                y=parseInt(y);
                
            }else{
                x=parseInt(x);
                y=parseInt(y);
            }
            x=margin+parseInt(x)*gridSize
            y=margin+parseInt(y)*gridSize
            drawChessPossible(ctx,x,y)   
        }

        onMounted(() => {
            const ctx = canvas.value.getContext('2d');
            
            window.addEventListener('mousemove',MoveHandle);
            // 绘制棋盘背景
            ctx.fillStyle = '#DEB887'; // 使用米色背景
            ctx.fillRect(0, 0, canvasSize, canvasSize);

            // 绘制网格线
            ctx.strokeStyle = '#000000'; // 黑色线条
            for (let i = 0; i < size; i++) {
             // 垂直线
              ctx.beginPath();
              ctx.moveTo(margin + i * gridSize, margin);
              ctx.lineTo(margin + i * gridSize, canvasSize - margin);
              ctx.stroke();
            
              // 水平线
               ctx.beginPath();
              ctx.moveTo(margin, margin + i * gridSize);
              ctx.lineTo(canvasSize - margin, margin + i * gridSize);
              ctx.stroke();
            }

            // 绘制星位
            const starPositions = [3, 9, 15];
            starPositions.forEach(x => {
              starPositions.forEach(y => {
                drawStar(ctx, margin + x * gridSize, margin + y * gridSize);
              });
            });
        });
        function drawStar(ctx, x, y) {
            ctx.beginPath();
            ctx.arc(x, y, 3, 0, Math.PI * 2, true);
            ctx.fillStyle = '#000000'; // 黑色星位
            ctx.fill();
        }
        return{
            canvas,
            canvasSize,
            ClickHandle
        }    
    }
}

</script>
<style>
canvas {
  border: 1px solid black;
}

</style>