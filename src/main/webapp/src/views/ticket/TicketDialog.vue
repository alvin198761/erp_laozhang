/*进销项发票录入新增与修改,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
      <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
            <el-form :model="form" ref="form" :rules="rules" label-width="100px">
                  <el-row>
                        <el-col>
                                                            <el-form-item label='主键' prop='id'>
                                    <el-input placeholder='请输入主键'  size="small" v-model='form.id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='供应商id' prop='vendor_id'>
                                    <el-input placeholder='请输入供应商id'  size="small" v-model='form.vendor_id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='税收类型' prop='tax_type'>
                                    <el-input placeholder='请输入税收类型'  size="small" v-model='form.tax_type'></el-input>
                                </el-form-item>
                                                            <el-form-item label='产品id' prop='prod_id'>
                                    <el-input placeholder='请输入产品id'  size="small" v-model='form.prod_id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='姓名' prop='target_name'>
                                    <el-input placeholder='请输入姓名'  size="small" v-model='form.target_name'></el-input>
                                </el-form-item>
                                                            <el-form-item label='收寄方id' prop='target_id'>
                                    <el-input placeholder='请输入收寄方id'  size="small" v-model='form.target_id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='备注' prop='remark'>
                                    <el-input placeholder='请输入备注'  size="small" v-model='form.remark'></el-input>
                                </el-form-item>
                                                            <el-form-item label='数量' prop='num'>
                                    <el-input placeholder='请输入数量'  size="small" v-model='form.num'></el-input>
                                </el-form-item>
                                                            <el-form-item label='单价' prop='price'>
                                    <el-input placeholder='请输入单价'  size="small" v-model='form.price'></el-input>
                                </el-form-item>
                                                            <el-form-item label='总价' prop='total'>
                                    <el-input placeholder='请输入总价'  size="small" v-model='form.total'></el-input>
                                </el-form-item>
                                                            <el-form-item label='发票类型' prop='ticket_type'>
                                    <el-input placeholder='请输入发票类型'  size="small" v-model='form.ticket_type'></el-input>
                                </el-form-item>
                                                            <el-form-item label='税率' prop='rate'>
                                    <el-input placeholder='请输入税率'  size="small" v-model='form.rate'></el-input>
                                </el-form-item>
                                                            <el-form-item label='进出类型' prop='type'>
                                    <el-input placeholder='请输入进出类型'  size="small" v-model='form.type'></el-input>
                                </el-form-item>
                                                            <el-form-item label='客户Id' prop='cus_id'>
                                    <el-input placeholder='请输入客户Id'  size="small" v-model='form.cus_id'></el-input>
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
            {required: true, message: '请输入供应商id', trigger: 'blur'},
            {min: 1, max: 10, message: '供应商id长度不正确', trigger: 'blur'},
        ],
                    tax_type :[
            {required: true, message: '请输入税收类型', trigger: 'blur'},
            {min: 1, max: 10, message: '税收类型长度不正确', trigger: 'blur'},
        ],
                    prod_id :[
            {required: true, message: '请输入产品id', trigger: 'blur'},
            {min: 1, max: 10, message: '产品id长度不正确', trigger: 'blur'},
        ],
                    target_name :[
            {required: true, message: '请输入姓名', trigger: 'blur'},
            {min: 1, max: 10, message: '姓名长度不正确', trigger: 'blur'},
        ],
                    target_id :[
            {required: true, message: '请输入收寄方id', trigger: 'blur'},
            {min: 1, max: 10, message: '收寄方id长度不正确', trigger: 'blur'},
        ],
                    remark :[
            {required: true, message: '请输入备注', trigger: 'blur'},
            {min: 1, max: 10, message: '备注长度不正确', trigger: 'blur'},
        ],
                    num :[
            {required: true, message: '请输入数量', trigger: 'blur'},
            {min: 1, max: 10, message: '数量长度不正确', trigger: 'blur'},
        ],
                    price :[
            {required: true, message: '请输入单价', trigger: 'blur'},
            {min: 1, max: 10, message: '单价长度不正确', trigger: 'blur'},
        ],
                    total :[
            {required: true, message: '请输入总价', trigger: 'blur'},
            {min: 1, max: 10, message: '总价长度不正确', trigger: 'blur'},
        ],
                    ticket_type :[
            {required: true, message: '请输入发票类型', trigger: 'blur'},
            {min: 1, max: 10, message: '发票类型长度不正确', trigger: 'blur'},
        ],
                    rate :[
            {required: true, message: '请输入税率', trigger: 'blur'},
            {min: 1, max: 10, message: '税率长度不正确', trigger: 'blur'},
        ],
                    type :[
            {required: true, message: '请输入进出类型', trigger: 'blur'},
            {min: 1, max: 10, message: '进出类型长度不正确', trigger: 'blur'},
        ],
                    cus_id :[
            {required: true, message: '请输入客户Id', trigger: 'blur'},
            {min: 1, max: 10, message: '客户Id长度不正确', trigger: 'blur'},
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
              that.$http.post("/api/ticket/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                        vendor_id : null,// 供应商id
                        tax_type : null,// 税收类型
                        prod_id : null,// 产品id
                        target_name : null,// 姓名
                        target_id : null,// 收寄方id
                        remark : null,// 备注
                        num : null,// 数量
                        price : null,// 单价
                        total : null,// 总价
                        ticket_type : null,// 发票类型
                        rate : null,// 税率
                        type : null,// 进出类型
                        cus_id : null,// 客户Id
                  }
      },
      addDialog() {//新增
        this.title = "新增进销项发票录入";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改进销项发票录入";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    }

  }
</script>
<style></style>