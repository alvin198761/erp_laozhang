<template>
    <el-tabs v-model="activeName">
        <el-tab-pane label="寄方列表" name="kehuxingzhi">
            <JiFangAddressList></JiFangAddressList>
        </el-tab-pane>
        <el-tab-pane label="收方列表" name="gongyingshangjibie">
            <ShouFangAddressList></ShouFangAddressList>
        </el-tab-pane>
    </el-tabs>
</template>
<script>

import JiFangAddressList from './JiFangAddressList.vue';
import ShouFangAddressList from './ShouFangAddressList.vue';
    export default {
        components: {
            JiFangAddressList,
            ShouFangAddressList
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