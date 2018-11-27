/*开票信息新增与修改,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
      <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
            <el-form :model="form" ref="form" :rules="rules" label-width="100px">
                  <el-row>
                        <el-col>
                                                            <el-form-item label='主键' prop='id'>
                                    <el-input placeholder='请输入主键'  size="small" v-model='form.id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='供应商' prop='vendor_id'>
                                    <el-input placeholder='请输入供应商'  size="small" v-model='form.vendor_id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='开户行' prop='bank'>
                                    <el-input placeholder='请输入开户行'  size="small" v-model='form.bank'></el-input>
                                </el-form-item>
                                                            <el-form-item label='账号' prop='account'>
                                    <el-input placeholder='请输入账号'  size="small" v-model='form.account'></el-input>
                                </el-form-item>
                                                            <el-form-item label='纳税人识别号' prop='taxpayer_no'>
                                    <el-input placeholder='请输入纳税人识别号'  size="small" v-model='form.taxpayer_no'></el-input>
                                </el-form-item>
                                                            <el-form-item label='备注' prop='remark'>
                                    <el-input placeholder='请输入备注'  size="small" v-model='form.remark'></el-input>
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
                    id :[
            {required: true, message: '请输入主键', trigger: 'blur'},
            {min: 1, max: 10, message: '主键长度不正确', trigger: 'blur'},
        ],
                    vendor_id :[
            {required: true, message: '请输入供应商', trigger: 'blur'},
            {min: 1, max: 10, message: '供应商长度不正确', trigger: 'blur'},
        ],
                    bank :[
            {required: true, message: '请输入开户行', trigger: 'blur'},
            {min: 1, max: 10, message: '开户行长度不正确', trigger: 'blur'},
        ],
                    account :[
            {required: true, message: '请输入账号', trigger: 'blur'},
            {min: 1, max: 10, message: '账号长度不正确', trigger: 'blur'},
        ],
                    taxpayer_no :[
            {required: true, message: '请输入纳税人识别号', trigger: 'blur'},
            {min: 1, max: 10, message: '纳税人识别号长度不正确', trigger: 'blur'},
        ],
                    remark :[
            {required: true, message: '请输入备注', trigger: 'blur'},
            {min: 1, max: 10, message: '备注长度不正确', trigger: 'blur'},
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
              that.$http.post("/api/bill/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                        id : null,// 主键
                        vendor_id : null,// 供应商
                        bank : null,// 开户行
                        account : null,// 账号
                        taxpayer_no : null,// 纳税人识别号
                        remark : null,// 备注
                  }
      },
      addDialog() {//新增
        this.title = "新增开票信息";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改开票信息";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    }

  }
</script>
<style></style>