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
import { useStore } from "vuex"
import CardItem from '@/components/CardItem.vue';
import { ref } from 'vue'
export default {
    components: {
        CardItem,
    },
    setup() {
        let admin = ref({
            username: '',
            password: ''
        })
        const store = useStore()
        function submit() {
            store.dispatch("getToken",admin.value)
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