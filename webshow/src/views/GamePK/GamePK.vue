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
import {ChessBoard} from '@/componetjs/ChessBoard.js'
import {Chess} from '@/componetjs/Chess.js'
export default{
    components:{
        CardItem,
    },
    setup (){
        //棋盘参数
        const canvasSize = 600;
        const size = 19;
        const margin = 30;
        const gridSize = (canvasSize - 2 * margin) / (size - 1);
        const canvas = ref(null);
        //获取棋盘原点
        var rect= null;
        //获取棋子对象
        var Chesses=null;
        function ClickHandle (e){
            //下棋
            Chesses.setChess(1);
        }
        
        //检测鼠标移动，并选择四角中的一角
        function MoveHandle (e){
            var x = e.clientX;
            var y = e.clientY;
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
            if(x<0||y<0||x>18||y>18){
                return ;
            }
            Chesses.ChessPossible(x,y)   
        }

        onMounted(() => {
            const ctx = canvas.value.getContext('2d');
            rect = canvas.value.getBoundingClientRect();
            //棋盘
            new ChessBoard(ctx,canvasSize,size,margin,gridSize)
            Chesses=new Chess(ctx,margin,gridSize)
            window.addEventListener('mousemove',MoveHandle);
        });
        
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