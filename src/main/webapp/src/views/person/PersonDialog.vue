/*客户联系人新增与修改,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
    <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
            <el-row>
                <el-col>
                    <el-form-item label='客户id' prop='cus_id'>
                        <el-input placeholder='请输入客户id' size="small" v-model='form.cus_id'></el-input>
                    </el-form-item>
                    <el-form-item label='收寄方ID' prop='address_id'>
                        <el-input placeholder='请输入收寄方ID' size="small" v-model='form.address_id'></el-input>
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
                    cus_id: [
                        {required: true, message: '请输入客户id', trigger: 'blur'},
                    ],
                    address_id: [
                        {required: true, message: '请输入收寄方ID', trigger: 'blur'},
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
                    that.$http.post("/api/cus_person/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                    cus_id: null,// 客户id
                    address_id: null,// 收寄方ID
                }
            },
            addDialog() {//新增
                this.title = "新增客户联系人";
                this.dialogMode = "save";
                this.form = this.initForm();
                this.show = true;
            },
            editDialog(row) {//修改
                this.title = "修改客户联系人";
                this.dialogMode = "update";
                this.form = {...row};
                this.show = true;
            },
        }

    }
</script>
<style></style>