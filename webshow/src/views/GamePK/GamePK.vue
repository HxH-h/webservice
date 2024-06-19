<template>
    <CardItem>
        <template #head>对战模式 </template>
        <template #body>
            <canvas id="can" ref="canvas" :width="canvasSize" :height="canvasSize" 
                v-if="store.state.wssocket.status === 'playing'"></canvas>
            <div :width="canvasSize" :height="canvasSize" v-else-if="store.state.wssocket.status === 'matching'">
                <el-row>
                    <el-col :span="12">
                        <el-text class="mx-1">{{ store.state.username }}</el-text>
                    </el-col>
                    <el-col :span="12">
                        <el-text class="mx-1">{{ store.state.wssocket.opponent_name }}</el-text>
                    </el-col>
                </el-row>
                <el-button @click="click_button">{{ button }}</el-button>
            </div>
        </template>
    </CardItem>
</template>


<script>
import { ref, onMounted, onUnmounted } from 'vue';
import CardItem from '@/components/CardItem.vue'
import { ChessBoard } from '@/componetjs/ChessBoard.js'
import { Chess } from '@/componetjs/Chess.js'
import { useStore } from "vuex"
export default {
    components: {
        CardItem,
    },
    setup() {
        //棋盘参数
        const canvasSize = 600;
        const size = 19;
        const margin = 30;
        const gridSize = (canvasSize - 2 * margin) / (size - 1);
        const canvas = ref(null);
        const button = ref("开始匹配")
        const timer = ref()
        //获取棋盘原点
        var rect = null;
        //获取棋子对象
        var Chesses = null;
        function ClickHandle(e) {
            //下棋
            var pos=Chesses.setChess(1);
            store.state.wssocket.socket.send(JSON.stringify({
                event: "down",
                position: pos
            }))
            //console.log(pos)
        }
        function DoNothing(){

        }
        //检测鼠标移动，并选择四角中的一角
        function MoveHandle(e) {
            var x = e.clientX;
            var y = e.clientY;
            x -= rect.left;
            y -= rect.top;
            x = (x - margin) / gridSize
            y = (y - margin) / gridSize

            var dx = x - parseInt(x)
            var dy = y - parseInt(y)
            if (dx > 0.5 && dy > 0.5) {
                x = parseInt(x) + 1;
                y = parseInt(y) + 1;

            } else if (dx < 0.5 && dy > 0.5) {
                x = parseInt(x);
                y = parseInt(y) + 1;

            } else if (dx > 0.5 && dy < 0.5) {
                x = parseInt(x) + 1;
                y = parseInt(y);

            } else {
                x = parseInt(x);
                y = parseInt(y);
            }
            if (x < 0 || y < 0 || x > 18 || y > 18) {
                return;
            }
            Chesses.ChessPossible(x, y)
        }
        function click_button() {
            if (button.value === "开始匹配") {
                button.value = "取消"
                store.state.wssocket.socket.send(JSON.stringify({
                    event: "start-matching"
                }))
            } else {
                button.value = "开始匹配"
                store.state.wssocket.socket.send(JSON.stringify({
                    event: "stop-matching"
                }))
            }
        }
        function render() {
            const ctx = canvas.value.getContext('2d');
            rect = canvas.value.getBoundingClientRect();
            //棋盘
            new ChessBoard(ctx, canvasSize, size, margin, gridSize)
            Chesses = new Chess(ctx, margin, gridSize)
            window.addEventListener('mousemove', MoveHandle);
        }

        const store = useStore()
        let socket = null
        onMounted(() => {
            socket = new WebSocket(store.state.wssocket.wsurl)
            store.state.wssocket.socket = socket
            socket.onopen = () => {
                console.log("connected")
            }
            socket.onmessage = msg => {
                const data = JSON.parse(msg.data)
                //判断消息类型
                if (data.event === "match_success") {
                    store.state.wssocket.opponent_name = data.oppont_name
                    store.state.wssocket.status = "playing"
                    store.state.wssocket.turn=data.turn
                    timer.value = setTimeout(() => {
                        render()
                    }, 150)
                } else if (data.event === "InitChess") {
                    timer.value = setTimeout(() => {
                        Chesses.InitChessBoard(data.InitChess)
                    }, 150)
                } else if(data.event === "start_turn"){
                    if(data.start_turn==store.state.wssocket.turn){
                        document.getElementById("can").onclick=ClickHandle
                        store.state.wssocket.turn="2"
                    }else{
                        document.getElementById("can").onclick=DoNothing
                        console.log("is not your turn")
                    }
                }else if(data.event === "synchronization"){
                    Chesses.InitChessBoard(data.synchronization)
                }
            }

            socket.onclose = () => {
                store.state.wssocket.status = "matching"
                store.state.wssocket.opponent_name = ""
                button.value = "开始匹配"
                console.log("disconnected")
            }


        });
        onUnmounted(() => {
            socket.close()
        })

        return {
            canvas,
            canvasSize,
            button,
            store,
            ClickHandle,
            click_button,
            DoNothing
        }
    }
}

</script>
<style scoped>
canvas {
    border: 1px solid black;
}

.el-row {
    margin-bottom: 20px;
}

.el-row:last-child {
    margin-bottom: 0;
}

.el-col {
    border-radius: 4px;
}

.grid-content {
    border-radius: 4px;
    min-height: 36px;
}
</style>