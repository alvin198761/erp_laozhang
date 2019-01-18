<template>
    <el-tabs v-model="activeName">
        <el-tab-pane label="客户开票信息" name="kehuxingzhi">
            <CusBillList></CusBillList>
        </el-tab-pane>
        <el-tab-pane label="供应商开票信息"  name="gongyingshangjibie">
            <VendorBillList></VendorBillList>
        </el-tab-pane>
    </el-tabs>
</template>
<script>

    import CusBillList from './CusBillList.vue';
    import VendorBillList from './VendorBillList.vue';
    export default {
        components: {
            CusBillList,
            VendorBillList
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