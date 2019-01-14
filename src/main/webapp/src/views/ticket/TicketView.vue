<template>
    <el-tabs v-model="activeName">
        <el-tab-pane label="进货发票记录" name="kehuxingzhi">
            <InTicketList></InTicketList>
        </el-tab-pane>
        <el-tab-pane label="出货发票记录" name="gongyingshangjibie">
            <OutTicketList></OutTicketList>
        </el-tab-pane>
    </el-tabs>
</template>
<script>

    import InTicketList from './InTicketList.vue';
    import OutTicketList from './OutTicketList.vue';
    export default {
        components: {
            InTicketList,
            OutTicketList
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