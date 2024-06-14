<template>
    <CardItem>
        <template #head>玩家信息 </template>
        <template #body>
            <el-row :guter="24">
                <el-col :span="6">
                    <div class="grid-content ep-bg-purple" />
                    <div class="userInfo">
                        <el-card style="max-width: 480px">
                            <template #header>
                                <div class="card-header">
                                    <span>{{ store.state.username }}</span>
                                </div>
                            </template>
                            <p> 账号: {{ store.state.id }} </p>
                            <p>对局数: </p>
                            <p>胜率: </p>
                            <!-- <p v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</p> -->
                            <template #footer>Footer content</template>
                        </el-card>
                    </div>
                </el-col>
                <el-col :span="18">
                    <div class="grid-content ep-bg-purple-light" />
                    <div class="historyList">
                        <el-table :data="tableData" style="width: 100%">
                            <el-table-column prop="date" label="Date" width="180" />
                            <el-table-column prop="name" label="Name" width="180" />
                            <el-table-column prop="address" label="Address" />
                        </el-table>
                    </div>
                </el-col>
            </el-row>
        </template>
    </CardItem>
</template>
<script>
import CardItem from '@/components/CardItem.vue'
import { useStore } from "vuex"
export default {
    components: {
        CardItem
    },
    setup() {
        const store = useStore()
        const tableData = [
            {
                date: '2016-05-03',
                name: 'Tom',
                address: 'No. 189, Grove St, Los Angeles',
            },
            {
                date: '2016-05-02',
                name: 'Tom',
                address: 'No. 189, Grove St, Los Angeles',
            },
            {
                date: '2016-05-04',
                name: 'Tom',
                address: 'No. 189, Grove St, Los Angeles',
            },
            {
                date: '2016-05-01',
                name: 'Tom',
                address: 'No. 189, Grove St, Los Angeles',
            },
        ]
        return {
            tableData,
            store
        }
    }
}

</script>
<style scoped>
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
.historyList{
    padding: 0 10%;
}
p{
    text-align: left;
}
</style>