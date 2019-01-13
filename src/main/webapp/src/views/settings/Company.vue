/*类型字典新增与修改,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
    <div style="width: 600px;margin-left: auto ; margin-right: auto">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
            <el-row>
                <el-col>
                    <el-form-item label='公司名称' prop='name'>
                        <el-input placeholder='请输入公司名称' size="small" v-model='form.name'></el-input>
                    </el-form-item>
                    <el-form-item label='公司地址' prop='address'>
                        <el-input placeholder='请输入公司地址' size="small" v-model='form.address'></el-input>
                    </el-form-item>
                    <el-form-item label='负责人' prop='head'>
                        <el-input placeholder='请输入负责人' size="small" v-model='form.head'></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <div style="text-align: right">
            <el-button @click="show = false">取消</el-button>
            <el-button type="primary" @click="save()">确定</el-button>
        </div>
    </div>
</template>
<script>
    export default {
        components: {},
        data() {
            return {
                title: '',
                form: this.initForm(),
                dialogMode: "save",
                show: false,
                rules: {
                    address: [
                        {required: true, message: '请输入地址', trigger: 'blur'},
                    ],
                    name: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                    ],
                    head: [
                        {required: true, message: '请输入负责人', trigger: 'blur'},
                    ],
                }
            }
        },
        created(){
            this.refresh();
        },
        methods: {
            save() {//新增及修改记录
                const that = this;
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                    that.$http.post("/api/dict/saveCompany", JSON.stringify(that.form)).then(res => {
                        that.show = false;
                        that.$message.success(that.title + "成功!");
                        that.refresh();
                    }).catch(res => {
                        that.$message.error(that.title + "出错!" + res);
                    });
                });
            },
            initForm() {
                return {
                    address: "",
                    name: "",
                    head: ""
                }
            },
            refresh(){
                const that = this;
                this.$http.get("/api/dict/fetchCompany").then(res => {
                    that.form = res.data;
                }).catch(err => {
                    that.form = that.initForm();
                    this.$message.error("获取公司信息失败");
                })
            }
        }

    }
</script>
<style></style>