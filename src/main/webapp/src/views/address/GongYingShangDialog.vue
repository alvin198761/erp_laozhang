/*收寄信息新增与修改,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
    <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
            <el-row>
                <el-col>
                    <el-form-item label='电话' prop='target_phone'>
                        <el-input placeholder='请输入电话' size="small" v-model='form.target_phone'></el-input>
                    </el-form-item>
                    <el-form-item label='地址' prop='target_addr'>
                        <el-input placeholder='请输入地址' size="small" v-model='form.target_addr'></el-input>
                    </el-form-item>
                    <el-form-item label='姓名' prop='target_name'>
                        <el-input placeholder='请输入姓名' size="small" v-model='form.target_name'></el-input>
                    </el-form-item>
                    <el-form-item label='供应商' prop='target_id'>
                        <el-select filterable  v-model="form.target_id" placeholder="请选择供应商" style="width: 100%" size="small">
                            <el-option
                                    v-for="item in gongyingList"
                                    :key="item.id"
                                    :label="item.vendor_name + '('+item.vendor_no+')'"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                        <!--<el-input placeholder='请输入收寄方id' size="small" v-model='form.target_id'></el-input>-->
                    </el-form-item>
                    <el-form-item label='备注' prop='remark'>
                        <el-input placeholder='请输入备注' size="small" v-model='form.remark' type="textarea"></el-input>
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
                    target_phone: [
                        {required: true, message: '请输入电话', trigger: 'blur'},
                    ],
                    target_addr: [
                        {required: true, message: '请输入地址', trigger: 'blur'},
                    ],
                    target_name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                    ],
                    target_id: [
                        {required: true, message: '请选择客户', trigger: 'blur'},
                    ]
                },
                gongyingList: []
            }
        },
        created(){
            this.loadGongyingList();
        },
        methods: {
            loadGongyingList(){
                const that = this;
                that.$http.post("/api/vendor/queryList",{}).then(res => {
                    that.gongyingList  = res.data;
                }).catch(err => {
                    that.$message.error("获取客户信息出错：" +err)
                });
            },
            save() {//新增及修改记录
                const that = this;
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                    that.$http.post("/api/address/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                    type: 2,// 类型
                    target_phone: null,// 电话
                    target_addr: null,// 地址
                    target_name: null,// 姓名
                    target_id: null,// 收寄方id
                    remark: null,// 备注
                }
            },
            addDialog() {//新增
                this.title = "新增供应商地址信息";
                this.dialogMode = "save";
                this.form = this.initForm();
                this.show = true;
            },
            editDialog(row) {//修改
                this.title = "修改供应商地址信息";
                this.dialogMode = "update";
                this.form = {...row};
                this.show = true;
            },
        }

    }
</script>
<style></style>