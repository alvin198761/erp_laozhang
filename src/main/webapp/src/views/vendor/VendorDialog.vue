/*供应商新增与修改,作者:唐植超,日期:2018-11-27 17:05:46*/
<template>
    <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
            <el-row>
                <el-col>
                    <el-form-item label='供应商编号' prop='vendor_no'>
                        <el-input placeholder='请输入供应商编号' size="small" v-model='form.vendor_no'></el-input>
                    </el-form-item>
                    <el-form-item label='供应商名称' prop='vendor_name'>
                        <el-input placeholder='请输入供应商名称' size="small" v-model='form.vendor_name'></el-input>
                    </el-form-item>
                    <el-form-item label='供应商地址' prop='address'>
                        <el-input placeholder='请输入供应商地址' size="small" v-model='form.address'></el-input>
                    </el-form-item>
                    <el-form-item label='供应商级别' prop='level'>
                        <el-select v-model="form.level" placeholder="请选择供应商级别" style="width: 100%"  size="small">
                            <el-option
                                    v-for="item in levelList"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label='备注' prop='remark'>
                        <el-input placeholder='请输入备注' size="small" type="textarea" v-model='form.remark'></el-input>
                    </el-form-item>
                    <el-form-item label='联系人' prop='concat_id'>
                        <el-input placeholder='请输入联系人' size="small" v-model='form.concat_id'></el-input>
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

                    vendor_no: [
                        {required: true, message: '请输入供应商编号', trigger: 'blur'},
                    ],
                    vendor_name: [
                        {required: true, message: '请输入供应商名称', trigger: 'blur'},
                    ],
                    address: [
                        {required: true, message: '请输入供应商地址', trigger: 'blur'},
                    ],
                    level: [
                        {required: true, message: '请输入供应商级别', trigger: 'blur'},
                    ],
//                    concat_id: [
//                        {required: true, message: '请输入联系人', trigger: 'blur'},
//                    ],
                },
                levelList: [],
                concatList: []
            }
        },
        created(){
            this.loadLevelList();
//            this.loadConcatList();
        },
        methods: {
            loadConcatList(){
                const that = this;
                that.$http.post("/api/cus_person/queryList",{}).then(res =>{
                    that.concatList = res.data;
                }).catch(err =>{
                    that.$message.error("获取联系人出错:" + err)
                });
            },
            loadLevelList(){
                const that = this;
                that.$http.post("/api/dict/queryList", {
                    type: 5
                }).then(res => {
                    that.levelList = res.data;
                }).catch(err =>{
                    that.$message.error("获取供应商级别出错:" + err)
                });
            },
            save() {//新增及修改记录
                const that = this;
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                    that.$http.post("/api/vendor/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                    vendor_no: null,// 供应商编号
                    vendor_name: null,// 供应商名称
                    address: null,// 供应商地址
                    level: null,// 供应商级别
                    remark: null,// 备注
                    concat_id: null,// 联系人
                }
            },
            addDialog() {//新增
                this.title = "新增供应商";
                this.dialogMode = "save";
                this.form = this.initForm();
                this.show = true;
            },
            editDialog(row) {//修改
                this.title = "修改供应商";
                this.dialogMode = "update";
                this.form = {...row};
                this.show = true;
            },
        }

    }
</script>
<style></style>