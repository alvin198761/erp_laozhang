<template>
    <el-tabs v-model="activeName">
        <el-tab-pane label="客户性质" name="kehuxingzhi">
            <KehuxingzhiDictList></KehuxingzhiDictList>
        </el-tab-pane>
        <el-tab-pane label="供应商级别" name="gongyingshangjibie">
            <GongyingshangjibieDictList></GongyingshangjibieDictList>
        </el-tab-pane>
        <el-tab-pane label="价格类型" name="jiageleixing">
            <JiageleixingDictList></JiageleixingDictList>
        </el-tab-pane>
        <el-tab-pane label="发票类型" name="fapiaoleixing">
            <FapiaoleixingDictList></FapiaoleixingDictList>
        </el-tab-pane>
        <el-tab-pane label="发票状态" name="fapiaozhuangtai">
            <FapiaozhuangtaiDictList></FapiaozhuangtaiDictList>
        </el-tab-pane>
        <el-tab-pane label="单据状态" name="danjvzhuangtai">
            <DanjvzhuangtaiDictList></DanjvzhuangtaiDictList>
        </el-tab-pane>
        <el-tab-pane label="送货状态" name="songhuozhuangtai">
            <SonghuozhuangtaiDictList></SonghuozhuangtaiDictList>
        </el-tab-pane>
        <el-tab-pane label="税收分类" name="shuishoufenlei">
            <TaxCategory></TaxCategory>
            <!--<ShuishouzhuangtaiDictList></ShuishouzhuangtaiDictList>-->
        </el-tab-pane>
        <el-tab-pane label="单位设置" name="sanwei">
            <DanweiDictList></DanweiDictList>
        </el-tab-pane>
        <el-tab-pane label="公司设置" name="gongsishezhi">
            <Company></Company>
        </el-tab-pane>
    </el-tabs>
</template>
<script>
    import Company from './Company.vue';
    import KehuxingzhiDictList from './kehuxingzhi/KehuxingzhiDictList.vue';
    import GongyingshangjibieDictList from './gongyingshangjibie/GongyingshangjibieDictList.vue';
    import JiageleixingDictList from './jiageleixing/JiageleixingDictList.vue';
    import FapiaoleixingDictList from './fapiaoleixing/FapiaoleixingDictList.vue';
    import FapiaozhuangtaiDictList from './fapiaozhuangtai/FapiaozhuangtaiDictList.vue';
    import DanjvzhuangtaiDictList from './danjvzhuangtai/DanjvzhuangtaiDictList.vue';
    import SonghuozhuangtaiDictList from './songhuozhuangtai/SonghuozhuangtaiDictList.vue';
//    import ShuishouzhuangtaiDictList from './shuishouzhuangtai/ShuishouzhuangtaiDictList.vue';
    import DanweiDictList from './danwei/DanweiDictList.vue';
    import TaxCategory from './taxcategory/TaxCategoryList.vue';

    export default {
        components: {
            Company,
            KehuxingzhiDictList,
            GongyingshangjibieDictList,
            JiageleixingDictList,
            FapiaoleixingDictList,
            FapiaozhuangtaiDictList,
            DanjvzhuangtaiDictList,
            SonghuozhuangtaiDictList,
//            ShuishouzhuangtaiDictList,
            DanweiDictList,
            TaxCategory
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