/*产品新增与修改,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
    <el-dialog :title="title" width="80%" :visible.sync="show" :close-on-click-modal="false"
               :close-on-press-escape="false">
        <el-form :model="form" ref="form" :rules="rules" label-width="150px">
            <el-row>
                <el-col :span="8">
                    <el-form-item label='货号' prop='prod_no'>
                        <el-input placeholder='请输入货号' size="small" v-model='form.prod_no'></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label='税收分类' prop='tax_type'>
                        <el-select v-model="form.tax_type" placeholder="请选择税收分类" style="width: 100%" size="small">
                            <el-option
                                    v-for="item in shuiShouLeiXingList"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.name">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="8">
                    <el-form-item label='产品名称' prop='prod_name'>
                        <el-input placeholder='请输入产品名称' size="small" v-model='form.prod_name'></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label='规格型号' prop='spec_no'>
                        <el-input placeholder='请输入规格型号' size="small" v-model='form.spec_no'></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="8">
                    <el-form-item label='产品说明' prop='note'>
                        <el-input placeholder='请输入产品说明' size="small" v-model='form.note'></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label='单位' prop='unit'>
                        <el-select v-model="form.unit" placeholder="请选择单位" style="width: 100%" size="small">
                            <el-option
                                    v-for="item in danWeiList"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.name">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label='价格类型' prop='price_mode'>
                        <el-select v-model="form.price_mode" placeholder="请选择价格类型" style="width: 100%" size="small">
                            <el-option
                                    v-for="item in jiaGeLeixingList"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.name">
                            </el-option>
                        </el-select>
                        <!--<el-input placeholder='请输入价格类型' size="small" v-model='form.price_mode'></el-input>-->
                    </el-form-item>
                </el-col>

                <el-col :span="8">
                    <el-form-item label='货物来源' prop='vendor_id'>
                        <el-select filterable  v-model="form.vendor_id" placeholder="请选择货物来源" style="width: 100%" size="small">
                            <el-option
                                    v-for="item in huoWuLaiYuanList"
                                    :key="item.id"
                                    :label="item.vendor_name + '('+item.vendor_no+')'"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label='是否含税' prop='has_tax'>
                        <el-select v-model="form.has_tax" placeholder="请选择是否含税" style="width: 100%" size="small">
                            <el-option label="含税" value="含税"></el-option>
                            <el-option label="不含税" value="不含税"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label='底价' prop='price'>
                        <el-input-number placeholder='请输入底价' size="small" v-model='form.price'></el-input-number>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label='售卖价' prop='sell_price'>
                        <el-input-number placeholder='请输入售卖价' size="small" v-model='form.sell_price'></el-input-number>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label='市场价' prop='mark_price'>
                        <el-input-number placeholder='请输入市场价' size="small" v-model='form.mark_price'></el-input-number>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12">
                    <el-form-item label='产品图片1' prop='pic1'>
                        <el-upload drag action="/api/product/uploadImg" name="image" :show-file-list="false"  :on-success="(response, file, fileList)=> uploadSuccess(response, file, fileList,'pic1')">
                            <i class="el-icon-upload"></i>
                            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                            <!--<div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>-->
                        </el-upload>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='产品图片1' prop='pic1'>
                        <div v-if="form.pic1 == null"  >
                            <br/>
                            <h1> 暂无图片</h1>
                        </div>
                        <div  v-if="form.pic1 != null">
                            <img  :src="'/api/product/productPic/' + form.pic1" style="width: 200px ; height: 200px" />
                        </div>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12">
                    <el-form-item label='产品图片2' prop='pic2'>
                        <!--<el-input placeholder='请输入产品图片2' size="small" v-model='form.pic2'></el-input>-->
                        <el-upload drag action="/api/product/uploadImg" name="image" :show-file-list="false" :on-success="(response, file, fileList)=> uploadSuccess(response, file, fileList,'pic2')">
                            <i class="el-icon-upload"></i>
                            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                        </el-upload>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='产品图片2' prop='pic2'>
                        <div v-if="form.pic2 == null"  >
                            <br/>
                            <h1> 暂无图片</h1>
                        </div>
                        <div  v-if="form.pic2 != null">
                            <img  :src="'/api/product/productPic/' + form.pic2" style="width: 200px ; height: 200px"/>
                        </div>
                    </el-form-item>
                </el-col>
            </el-row>

            <el-row>
                <el-col :span="12">
                    <el-form-item label='产品图片3' prop='pic3'>
                        <!--<el-input placeholder='请输入产品图片3' size="small" v-model='form.pic3'></el-input>-->
                        <el-upload drag action="/api/product/uploadImg" name="image" :show-file-list="false"  :on-success="(response, file, fileList)=> uploadSuccess(response, file, fileList,'pic3')">
                            <i class="el-icon-upload"></i>
                            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                            <!--<div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>-->
                        </el-upload>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label='产品图片3' prop='pic3'>
                        <div v-if="form.pic3 == null"  >
                            <br/>
                            <h1> 暂无图片</h1>
                        </div>
                        <div  v-if="form.pic3 != null">
                            <img  :src="'/api/product/productPic/' + form.pic3" style="width: 200px ; height: 200px"/>
                        </div>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
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
                    prod_no: [
                        {required: true, message: '请输入货号', trigger: 'blur'},
                    ],
                    tax_type: [
                        {required: true, message: '请输入税收类型', trigger: 'blur'},
                    ],
                    prod_name: [
                        {required: true, message: '请输入产品名称', trigger: 'blur'},
                    ],
                    spec_no: [
                        {required: true, message: '请输入规格型号', trigger: 'blur'},
                    ],
                    note: [
                        {required: true, message: '请输入产品说明', trigger: 'blur'},
                    ],
                    unit: [
                        {required: true, message: '请输入单位', trigger: 'blur'},
                    ],
                    price: [
                        {required: true, message: '请输入底价', trigger: 'blur'},
                    ],
                    sell_price: [
                        {required: true, message: '请输入售卖价', trigger: 'blur'},
                    ],
                    has_tax: [
                        {required: true, message: '请输选择是否含税', trigger: 'blur'},
                    ],
                    mark_price: [
                        {required: true, message: '请输入市场价', trigger: 'blur'},
                    ],
//                    mark_price_no: [
//                        {required: true, message: '请输入市场价(含税)', trigger: 'blur'},
//                    ],
                    price_mode: [
                        {required: true, message: '请输入价格类型', trigger: 'blur'},
                    ],
                    vendor_id: [
                        {required: true, message: '请输入货物来源', trigger: 'blur'},
                    ],
//                    pic1: [
//                        {required: true, message: '请输入产品图片1', trigger: 'blur'},
//                    ],
//                    pic2: [
//                        {required: true, message: '请输入产品图片2', trigger: 'blur'},
//                    ],
//                    pic3: [
//                        {required: true, message: '请输入产品图片3', trigger: 'blur'},
//                    ],
//                    remark: [
//                        {required: true, message: '请输入备注', trigger: 'blur'},
//                    ],
                },
                shuiShouLeiXingList: [],
                danWeiList: [],
                jiaGeLeixingList: [],
                huoWuLaiYuanList: [],

            }
        },
        created() {
            this.loadShuiShouLeiXingList();
            this.loadDanWeiList();
            this.loadJiaGeLeiXingList();
            this.loadHuoWuLaiYuanList();
        },
        methods: {
            loadHuoWuLaiYuanList(){
                const that = this;
                that.$http.post("/api/vendor/queryList", {

                }).then(res => {
                    that.huoWuLaiYuanList = res.data;
                }).catch(err => {
                    that.$message.error("获取货物来源出错:" + err)
                });
            },
            loadJiaGeLeiXingList(){
                const that = this;
                that.$http.post("/api/dict/queryList", {
                    type: 6
                }).then(res => {
                    that.jiaGeLeixingList = res.data;
                }).catch(err => {
                    that.$message.error("获取价格类型出错:" + err)
                });
            },
            loadDanWeiList() {
                const that = this;
                that.$http.post("/api/dict/queryList", {
                    type: 12
                }).then(res => {
                    that.danWeiList = res.data;
                }).catch(err => {
                    that.$message.error("获取单位出错:" + err)
                });
            },
            loadShuiShouLeiXingList() {
                const that = this;
                that.$http.post("/api/dict/queryList", {
                    type: 11
                }).then(res => {
                    that.shuiShouLeiXingList = res.data;
                }).catch(err => {
                    that.$message.error("获取税收类型出错:" + err)
                });
            },

            save() {//新增及修改记录
                const that = this;
                this.$refs['form'].validate((valid) => {
                    if (!valid) {
                        return;
                    }
                    that.$http.post("/api/product/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                    prod_no: null,// 货号
                    tax_type: null,// 税收类型
                    prod_name: null,// 产品名称
                    spec_no: null,// 规格型号
                    note: null,// 产品说明
                    unit: null,// 单位
                    price: 0,// 底价
                    sell_price: 0,// 售卖价(含税)
                    mark_price: 0,// 市场价(不含税)
                    price_mode: null,// 价格类型
                    vendor_id: null,// 货物来源
                    pic1: null,// 产品图片1
                    pic2: null,// 产品图片2
                    pic3: null,// 产品图片3
                    remark: null,// 备注
                    has_tax: null,//
                }
            },
            addDialog() {//新增
                this.title = "新增产品";
                this.dialogMode = "save";
                this.form = this.initForm();
                this.show = true;
            },
            editDialog(row) {//修改
                this.title = "修改产品";
                this.dialogMode = "update";
                this.form = {...row};
                this.show = true;
            },
            uploadSuccess(response, file, fileList,pic){
                this.form[pic] = response;
            }
        }

    }
</script>
<style></style>