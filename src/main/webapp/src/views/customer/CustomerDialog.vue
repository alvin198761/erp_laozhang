/*客户新增与修改,作者:唐植超,日期:2018-11-27 17:15:51*/
<template>
    <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
            <el-row>
                <el-col>
                    <el-form-item label='客户编号' prop='cus_no'>
                        <el-input placeholder='请输入客户编号' size="small" v-model='form.cus_no'></el-input>
                    </el-form-item>
                    <el-form-item label='单位名称' prop='cus_name'>
                        <el-input placeholder='请输入单位名称' size="small" v-model='form.cus_name'></el-input>
                    </el-form-item>
                    <el-form-item label='客户地址' prop='address'>
                        <el-input placeholder='请输入客户地址' size="small" v-model='form.address'></el-input>
                    </el-form-item>
                    <el-form-item label='客户性质' prop='cus_type'>
                        <el-select v-model="form.cus_type" placeholder="请选择客户性质" style="width: 100%"  size="small">
                            <el-option
                                    v-for="item in typeList"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
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

                    cus_no: [
                        {required: true, message: '请输入客户编号', trigger: 'blur'},
                    ],
                    cus_name: [
                        {required: true, message: '请输入单位名称', trigger: 'blur'},
                    ],
                    address: [
                        {required: true, message: '请输入客户地址', trigger: 'blur'},
                    ],
                    cus_type: [
                        {required: true, message: '请选择客户性质', trigger: 'blur'},
                    ],

                },
                typeList:[]
            }
        },
        created(){
            this.loadTypeList();
        },
        methods: {
            loadTypeList(){
                const that = this;
                that.$http.post("/api/dict/queryList",{
                    type: 4
                }).then(res => {
                    that.typeList = res.data;
                }).catch(err =>{
                    that.$message.error("获取客户性质出错:" + err)
                });
            },
            save() {//新增及修改记录
                const that = this;
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                    that.$http.post("/api/customer/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                    cus_no: null,// 客户编号
                    cus_name: null,// 单位名称
                    address: null,// 客户地址
                    cus_type: null,// 客户性质
                    remark: null,// 备注
                }
            },
            addDialog() {//新增
                this.title = "新增客户";
                this.dialogMode = "save";
                this.form = this.initForm();
                this.show = true;
            },
            editDialog(row) {//修改
                this.title = "修改客户";
                this.dialogMode = "update";
                this.form = {...row};
                this.show = true;
            },
        }

    }
</script>
<style></style>