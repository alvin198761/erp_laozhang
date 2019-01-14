/*入库记录新增与修改,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
    <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
            <el-row>
                <el-col>
                    <el-form-item label='产品' prop='prod_id'>
                        <!--<el-input placeholder='请输入产品id' size="small" v-model='form.prod_id'></el-input>-->
                        <el-select v-model="form.prod_id" placeholder="请选择产品" style="width: 100%"  size="small">
                            <el-option
                                    v-for="item in chanpingList"
                                    :key="item.id"
                                    :label="item.prod_name + '(' + item.prod_no + ')'"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label='入库日期' prop='date'>
                                <el-date-picker
                                        type="date"
                                        placeholder='请输入入库日期' size="small" v-model='form.date'>
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label='发票类型' prop='ticket_type'>
                                <!--<el-input placeholder='请输入发票类型' size="small" v-model='form.ticket_type'></el-input>-->
                                <el-select v-model="form.ticket_type" placeholder="请选择发票类型" style="width: 100%"  size="small">
                                    <el-option
                                            v-for="item in fapiaoLeixingList"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.name">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label='发票状态' prop='ticket_status'>
                                <!--<el-input placeholder='请输入发票状态' size="small" v-model='form.ticket_status'></el-input>-->
                                <el-select v-model="form.ticket_status" placeholder="请选择发票状态" style="width: 100%"  size="small">
                                    <el-option
                                            v-for="item in fapiaoZhuangTaiList"
                                            :key="item.id"
                                            :label="item.name"
                                            :value="item.name">
                                    </el-option>
                                </el-select>

                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-form-item label='备注' prop='remark'>
                        <el-input placeholder='请输入备注' size="small" type="textarea" v-model='form.remark'></el-input>
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
                    prod_id: [
                        {required: true, message: '请输入产品id', trigger: 'blur'},
                    ],
                    date: [
                        {required: true, message: '请输入入库日期', trigger: 'blur'},
                    ],
                    ticket_type: [
                        {required: true, message: '请输入发票类型', trigger: 'blur'},
                    ],
                    ticket_status: [
                        {required: true, message: '请输入发票状态', trigger: 'blur'},
                    ],
//                    remark: [
//                        {required: true, message: '请输入备注', trigger: 'blur'},
//                    ],
                },
                chanpingList: [],
                fapiaoLeixingList: [],
                fapiaoZhuangTaiList: [],
            }
        },
        created(){
            this.loadChanpingList();
            this.loadFapiaoLeixingList();
            this.loadFapiaoZhuangTaiList();
        },
        methods: {
            loadFapiaoZhuangTaiList(){
                const that = this;
                that.$http.post("/api/dict/queryList",{
                    type: 7
                }).then(res => {
                    that.fapiaoLeixingList = res.data;
                }).catch(err => {
                    that.$message.error("获取发票类型出错：" + err)
                });
            },
            loadFapiaoLeixingList(){
                const that = this;
                that.$http.post("/api/dict/queryList",{
                    type: 8
                }).then(res => {
                    that.fapiaoZhuangTaiList = res.data;
                }).catch(err => {
                    that.$message.error("获取发票状态出错：" + err)
                });
            },
            loadChanpingList(){
                const that = this;
                that.$http.post("/api/product/queryList", {}).then(res => {
                    that.chanpingList = res.data;
                }).catch(err => {
                    that.$message.error("获取产品信息列表：" + err)
                });
            },
            save() {//新增及修改记录
                const that = this;
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                    that.$http.post("/api/inbound/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                    prod_id: null,// 产品id
                    date: new Date(),// 入库日期
                    ticket_type: null,// 发票类型
                    ticket_status: null,// 发票状态
                    remark: null,// 备注
                }
            },
            addDialog() {//新增
                this.title = "新增入库记录";
                this.dialogMode = "save";
                this.form = this.initForm();
                this.show = true;
            },
            editDialog(row) {//修改
                this.title = "修改入库记录";
                this.dialogMode = "update";
                this.form = {...row};
                this.show = true;
            },
        }

    }
</script>
<style></style>