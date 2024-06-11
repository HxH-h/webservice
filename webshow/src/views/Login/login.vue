<template>
    <CardItem>
        <template #head>
            <h1>登录</h1>
        </template>
        <template #body>
            <el-input v-model="admin.username" class="username" placeholder="用户名" /><br>
            <el-input v-model="admin.password" class="password" placeholder="密码" /><br>
            <el-button @click="submit">提交</el-button>
        </template>
    </CardItem>

</template>
<script>
import axios from 'axios';
import CardItem from '@/components/CardItem.vue';
import { ref } from 'vue'
let admin = ref({
    username: '',
    password: ''
})
export default {
    components: {
        CardItem,
    },
    setup() {
        function submit() {
            axios({
                method: "get",
                url: "http://localhost:8080/user/" + admin.username + "/" + admin.password,
                data: JSON.stringify(data),
                headers: {
                    "Content-Type": "application/json"
                }
            }).then(function (resp) {
                
            })
        }
        return {
            admin,
            submit
        }
    }
}


</script>
<style scoped>
.username {
    padding: 10px;
    width: 250px;
}

.password {
    padding: 10px;
    width: 250px;
}
</style>