/*税收分类新增与修改,作者:唐植超,日期:2019-01-18 13:46:52*/
<template>
    <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
            <el-row>
                <el-col>
                    <el-form-item label='名称' prop='name'>
                        <el-input placeholder='请输入名称' size="small" v-model='form.name'></el-input>
                    </el-form-item>
                    <el-form-item label='税收编码' prop='tax_code'>
                        <el-input placeholder='请输入税收编码' size="small" v-model='form.tax_code'></el-input>
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
                    name: [
                        {required: true, message: '请输入名称', trigger: 'blur'},
                        // {min: 1, max: 10, message: '名称长度不正确', trigger: 'blur'},
                    ],
                    tax_code: [
                        {required: true, message: '请输入税收编码', trigger: 'blur'},
                        // {min: 1, max: 10, message: '税收编码长度不正确', trigger: 'blur'},
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
                if (that.dialogMode == "save") {
                    that.$http.post("/api/taxCategory/queryCount", JSON.stringify({name: that.form.name})).then(res => {
                        if (res.data != 0) {
                        that.$message.error("该字典已经存在!");
                        return;
                    }
                    that.$http.post("/api/taxCategory/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
                        that.show = false;
                    that.$message.success(that.title + "成功!");
                    that.refresh();
                }).catch(res => {
                        that.$message.error(that.title + "出错!" + res);
                });
                }).catch(res => {
                        that.$message.error("验证常量出错!" + res);
                });
                    return ;
                }
                    that.$http.post("/api/taxCategory/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                    name: null,// 名称
                    tax_code: null,// 税收编码
                }
            },
            addDialog() {//新增
                this.title = "新增税收分类";
                this.dialogMode = "save";
                this.form = this.initForm();
                this.show = true;
            },
            editDialog(row) {//修改
                this.title = "修改税收分类";
                this.dialogMode = "update";
                this.form = {...row};
                this.show = true;
            },
        }

    }
</script>
<style></style>