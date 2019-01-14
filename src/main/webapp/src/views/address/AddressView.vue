<template>
    <el-tabs v-model="activeName">
        <el-tab-pane label="客户地址列表" name="kehuxingzhi">
            <KeHuAddressList></KeHuAddressList>
        </el-tab-pane>
        <el-tab-pane label="供应商列表" name="gongyingshangjibie">
            <GongYingShangAddressList></GongYingShangAddressList>
        </el-tab-pane>
    </el-tabs>
</template>
<script>

import KeHuAddressList from './KeHuAddressList.vue';
import GongYingShangAddressList from './GongYingShangAddressList.vue';
    export default {
        components: {
            KeHuAddressList,
            GongYingShangAddressList
        },
        data() {
            return {
                activeName: 'kehuxingzhi'
            }
        },
        methods: {}

    }
</script>
<style></style>