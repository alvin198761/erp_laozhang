/*产品新增与修改,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
    <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
            <el-row>
                <el-col>
                    <el-form-item label='货号' prop='prod_no'>
                        <el-input placeholder='请输入货号' size="small" v-model='form.prod_no'></el-input>
                    </el-form-item>
                    <el-form-item label='税收类型' prop='tax_type'>
                        <el-input placeholder='请输入税收类型' size="small" v-model='form.tax_type'></el-input>
                    </el-form-item>
                    <el-form-item label='产品名称' prop='prod_name'>
                        <el-input placeholder='请输入产品名称' size="small" v-model='form.prod_name'></el-input>
                    </el-form-item>
                    <el-form-item label='规格型号' prop='spec_no'>
                        <el-input placeholder='请输入规格型号' size="small" v-model='form.spec_no'></el-input>
                    </el-form-item>
                    <el-form-item label='产品说明' prop='note'>
                        <el-input placeholder='请输入产品说明' size="small" v-model='form.note'></el-input>
                    </el-form-item>
                    <el-form-item label='单位' prop='unit'>
                        <el-input placeholder='请输入单位' size="small" v-model='form.unit'></el-input>
                    </el-form-item>
                    <el-form-item label='底价' prop='price'>
                        <el-input placeholder='请输入底价' size="small" v-model='form.price'></el-input>
                    </el-form-item>
                    <el-form-item label='售卖价(含税)' prop='sell_price_yes'>
                        <el-input placeholder='请输入售卖价(含税)' size="small" v-model='form.sell_price_yes'></el-input>
                    </el-form-item>
                    <el-form-item label='售卖价(不含税)' prop='sell_price_no'>
                        <el-input placeholder='请输入售卖价(不含税)' size="small" v-model='form.sell_price_no'></el-input>
                    </el-form-item>
                    <el-form-item label='市场价(不含税)' prop='mark_price_yes'>
                        <el-input placeholder='请输入市场价(不含税)' size="small" v-model='form.mark_price_yes'></el-input>
                    </el-form-item>
                    <el-form-item label='市场价(含税)' prop='mark_price_no'>
                        <el-input placeholder='请输入市场价(含税)' size="small" v-model='form.mark_price_no'></el-input>
                    </el-form-item>
                    <el-form-item label='价格类型' prop='price_mode'>
                        <el-input placeholder='请输入价格类型' size="small" v-model='form.price_mode'></el-input>
                    </el-form-item>
                    <el-form-item label='货物来源' prop='vendor_id'>
                        <el-input placeholder='请输入货物来源' size="small" v-model='form.vendor_id'></el-input>
                    </el-form-item>
                    <el-form-item label='产品图片1' prop='pic1'>
                        <el-input placeholder='请输入产品图片1' size="small" v-model='form.pic1'></el-input>
                    </el-form-item>
                    <el-form-item label='产品图片2' prop='pic2'>
                        <el-input placeholder='请输入产品图片2' size="small" v-model='form.pic2'></el-input>
                    </el-form-item>
                    <el-form-item label='产品图片3' prop='pic3'>
                        <el-input placeholder='请输入产品图片3' size="small" v-model='form.pic3'></el-input>
                    </el-form-item>
                    <el-form-item label='备注' prop='remark'>
                        <el-input placeholder='请输入备注' size="small" v-model='form.remark'></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <div slot="footer" style="text-align: right">
            <el-button @click="show = false">取消</el-button>
            <el-button type="primary" @click="save()">确定</el-button>
        </div>
    </el-dialog>
</template>
<script>
    export default {
        components: {},
        props: ["refresh"],
        data() {
            return {
                title: '',
                form: this.initForm(),
                dialogMode: "save",
                show: false,
                rules: {
                    prod_no: [
                        {required: true, message: '请输入货号', trigger: 'blur'},
                    ],
                    tax_type: [
                        {required: true, message: '请输入税收类型', trigger: 'blur'},
                    ],
                    prod_name: [
                        {required: true, message: '请输入产品名称', trigger: 'blur'},
                    ],
                    spec_no: [
                        {required: true, message: '请输入规格型号', trigger: 'blur'},
                    ],
                    note: [
                        {required: true, message: '请输入产品说明', trigger: 'blur'},
                    ],
                    unit: [
                        {required: true, message: '请输入单位', trigger: 'blur'},
                    ],
                    price: [
                        {required: true, message: '请输入底价', trigger: 'blur'},
                    ],
                    sell_price_yes: [
                        {required: true, message: '请输入售卖价(含税)', trigger: 'blur'},
                    ],
                    sell_price_no: [
                        {required: true, message: '请输入售卖价(不含税)', trigger: 'blur'},
                    ],
                    mark_price_yes: [
                        {required: true, message: '请输入市场价(不含税)', trigger: 'blur'},
                    ],
                    mark_price_no: [
                        {required: true, message: '请输入市场价(含税)', trigger: 'blur'},
                    ],
                    price_mode: [
                        {required: true, message: '请输入价格类型', trigger: 'blur'},
                    ],
                    vendor_id: [
                        {required: true, message: '请输入货物来源', trigger: 'blur'},
                    ],
                    pic1: [
                        {required: true, message: '请输入产品图片1', trigger: 'blur'},
                    ],
                    pic2: [
                        {required: true, message: '请输入产品图片2', trigger: 'blur'},
                    ],
                    pic3: [
                        {required: true, message: '请输入产品图片3', trigger: 'blur'},
                    ],
                    remark: [
                        {required: true, message: '请输入备注', trigger: 'blur'},
                    ],
                }
            }
        },
        methods: {
            save() {//新增及修改记录
                const that = this;
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                    that.$http.post("/api/product/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
                        that.show = false;
                        that.$message.success(that.title + "成功!");
                        that.refresh();
                    }).catch(res => {
                        that.$message.error(that.title + "出错!" + res);
                    });
                });
            },
            initForm() {//初始数据
                return {
                    id: null,// 主键
                    prod_no: null,// 货号
                    tax_type: null,// 税收类型
                    prod_name: null,// 产品名称
                    spec_no: null,// 规格型号
                    note: null,// 产品说明
                    unit: null,// 单位
                    price: null,// 底价
                    sell_price_yes: null,// 售卖价(含税)
                    sell_price_no: null,// 售卖价(不含税)
                    mark_price_yes: null,// 市场价(不含税)
                    mark_price_no: null,// 市场价(含税)
                    price_mode: null,// 价格类型
                    vendor_id: null,// 货物来源
                    pic1: null,// 产品图片1
                    pic2: null,// 产品图片2
                    pic3: null,// 产品图片3
                    remark: null,// 备注
                }
            },
            addDialog() {//新增
                this.title = "新增产品";
                this.dialogMode = "save";
                this.form = this.initForm();
                this.show = true;
            },
            editDialog(row) {//修改
                this.title = "修改产品";
                this.dialogMode = "update";
                this.form = {...row};
                this.show = true;
            },
        }

    }
</script>
<style></style>