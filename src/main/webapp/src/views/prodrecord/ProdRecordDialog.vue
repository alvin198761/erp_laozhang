/*产品信息记录新增与修改,作者:唐植超,日期:2019-01-18 13:46:52*/
<template>
      <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
            <el-form :model="form" ref="form" :rules="rules" label-width="100px">
                  <el-row>
                        <el-col>
                                                            <el-form-item label='主键' prop='id'>
                                    <el-input placeholder='请输入主键'  size="small" v-model='form.id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='产品id' prop='prod_id'>
                                    <el-input placeholder='请输入产品id'  size="small" v-model='form.prod_id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='总价' prop='total'>
                                    <el-input placeholder='请输入总价'  size="small" v-model='form.total'></el-input>
                                </el-form-item>
                                                            <el-form-item label='单价' prop='priice'>
                                    <el-input placeholder='请输入单价'  size="small" v-model='form.priice'></el-input>
                                </el-form-item>
                                                            <el-form-item label='数量' prop='num'>
                                    <el-input placeholder='请输入数量'  size="small" v-model='form.num'></el-input>
                                </el-form-item>
                                                            <el-form-item label='业务ID' prop='bus_id'>
                                    <el-input placeholder='请输入业务ID'  size="small" v-model='form.bus_id'></el-input>
                                </el-form-item>
                                                            <el-form-item label='业务类型' prop='bus_type'>
                                    <el-input placeholder='请输入业务类型'  size="small" v-model='form.bus_type'></el-input>
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
            // {min: 1, max: 10, message: '主键长度不正确', trigger: 'blur'},
        ],
                    prod_id :[
            {required: true, message: '请输入产品id', trigger: 'blur'},
            // {min: 1, max: 10, message: '产品id长度不正确', trigger: 'blur'},
        ],
                    total :[
            {required: true, message: '请输入总价', trigger: 'blur'},
            // {min: 1, max: 10, message: '总价长度不正确', trigger: 'blur'},
        ],
                    priice :[
            {required: true, message: '请输入单价', trigger: 'blur'},
            // {min: 1, max: 10, message: '单价长度不正确', trigger: 'blur'},
        ],
                    num :[
            {required: true, message: '请输入数量', trigger: 'blur'},
            // {min: 1, max: 10, message: '数量长度不正确', trigger: 'blur'},
        ],
                    bus_id :[
            {required: true, message: '请输入业务ID', trigger: 'blur'},
            // {min: 1, max: 10, message: '业务ID长度不正确', trigger: 'blur'},
        ],
                    bus_type :[
            {required: true, message: '请输入业务类型', trigger: 'blur'},
            // {min: 1, max: 10, message: '业务类型长度不正确', trigger: 'blur'},
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
              that.$http.post("/api/prodRecord/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                        prod_id : null,// 产品id
                        total : null,// 总价
                        priice : null,// 单价
                        num : null,// 数量
                        bus_id : null,// 业务ID
                        bus_type : null,// 业务类型
                  }
      },
      addDialog() {//新增
        this.title = "新增产品信息记录";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改产品信息记录";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    }

  }
</script>
<style></style>