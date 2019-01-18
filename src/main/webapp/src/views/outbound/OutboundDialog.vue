/*出库记录新增与修改,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
    <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">

            <el-row>
                <el-col>

                    <el-form-item label='客户' prop='cus_id'>
                        <!--<el-input placeholder='请输入客户id' size="small" v-model='form.cus_id'></el-input>-->
                        <el-select filterable  v-model="form.cus_id" placeholder="请选择客户" style="width: 100%" size="small">
                            <el-option
                                    v-for="item in kehuList"
                                    :key="item.id"
                                    :label="item.cus_name + '('+item.cus_no+')'"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <!--<el-row>-->
                <!--<el-col>-->
                    <!--<el-form-item label='产品' prop='prod_id'>-->
                        <!--&lt;!&ndash;<el-input placeholder='请输入产品id' size="small" v-model='form.prod_id'></el-input>&ndash;&gt;-->
                        <!--<el-select v-model="form.prod_id" placeholder="请选择产品" style="width: 100%"  size="small">-->
                            <!--<el-option-->
                                    <!--v-for="item in chanpingList"-->
                                    <!--:key="item.id"-->
                                    <!--:label="item.prod_name + '(' + item.prod_no + ')'"-->
                                    <!--:value="item.id">-->
                            <!--</el-option>-->
                        <!--</el-select>-->
                    <!--</el-form-item>-->
                <!--</el-col>-->
            <!--</el-row>-->
            <!--<el-row>-->
                <!--<el-col :span="8">-->
                    <!--<el-form-item label='单价' prop='price'>-->
                        <!--<el-input-number placeholder='请输入单价' size="small" v-model='form.price'></el-input-number>-->
                    <!--</el-form-item>-->
                <!--</el-col>-->
                <!--<el-col :span="8">-->
                    <!--<el-form-item label='数量' prop='num'>-->
                        <!--<el-input-number placeholder='请输入数量' size="small" v-model='form.num'></el-input-number>-->
                    <!--</el-form-item>-->
                <!--</el-col>-->
                <!--<el-col :span="8">-->
                    <!--<el-form-item label='总价' prop='total'>-->
                        <!--{{form.price * form.num}}-->
                        <!--&lt;!&ndash;<el-input placeholder='请输入总价' size="small" v-model='form.total'></el-input>&ndash;&gt;-->
                    <!--</el-form-item>-->
                <!--</el-col>-->
            <!--</el-row>-->
            <el-row>
                <el-col>
                    <el-form-item label='送货状态' prop='status'>
                        <!--<el-input placeholder='请输入送货状态' size="small" v-model='form.status'></el-input>-->
                        <el-select v-model="form.status" placeholder="请选择送货状态" style="width: 100%" size="small">
                            <el-option
                                    v-for="item in songHongZhuangTaiList"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.name">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-form-item label='产品列表' prop='prods'>
                <el-table :data="form.prods" element-loading-text="正在加载......" style="width: 100%">
                    <el-table-column prop="prod_id" label="产品">
                        <template slot-scope="sprops">
                            <el-select v-model="sprops.row.prod_id" placeholder="请选择产品" style="width: 100%"  size="small">
                                <el-option
                                        v-for="item in chanpingList"
                                        :key="item.id"
                                        :label="item.prod_name + '(' + item.prod_no + ')'"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </template>
                    </el-table-column>
                    <el-table-column prop="priice" label="单价" width="150">
                        <template slot-scope="sprops">
                            <el-input-number  size="small" v-model="sprops.row.priice" :min="0"></el-input-number>
                        </template>
                    </el-table-column>
                    <el-table-column prop="num" label="数量" width="150">
                        <template slot-scope="sprops">
                            <el-input-number  size="small" v-model="sprops.row.num" :min="1"></el-input-number>
                        </template>
                    </el-table-column>
                    <el-table-column prop="total" label="总价" width="100">
                        <template slot-scope="sprops">
                            {{sprops.row.priice * sprops.row.num}}
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" width="150">
                        <template slot-scope="sprops">
                            <div>
                                <el-button type="text" :disabled="form.prods.indexOf(sprops.row) != form.prods.length -1" @click="form.prods.push({priice: 0 , num: 1,bus_type:2})">添加</el-button>
                                <el-button type="text" :disabled="form.prods.length == 1" @click="form.prods.splice(form.prods.indexOf(sprops.row),1)">删除
                                </el-button>
                            </div>
                        </template>
                    </el-table-column>
                </el-table>
            </el-form-item>
            <el-row>
                <el-col>
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
                    cus_id: [
                        {required: true, message: '请选择客户', trigger: 'blur'},
                    ],
//                    prod_id: [
//                        {required: true, message: '请选择产品', trigger: 'blur'},
//                    ],
//                    price: [
//                        {required: true, message: '请输入单价', trigger: 'blur'},
//                    ],
//                    num: [
//                        {required: true, message: '请输入数量', trigger: 'blur'},
//                    ],
//                    total: [
//                        {required: true, message: '请输入总价', trigger: 'blur'},
//                    ],
                    status: [
                        {required: true, message: '请输入送货状态', trigger: 'blur'},
                    ],
//                    remark: [
//                        {required: true, message: '请输入备注', trigger: 'blur'},
//                    ],
                },
                kehuList:[],
                chanpingList:[],
                songHongZhuangTaiList:[],
            }
        },
        created(){
            this.loadKehuList();
            this.loadChanPinList();
            this.loadSongHuoZhuangTaiList();
        },
        methods: {
            loadSongHuoZhuangTaiList(){
                const that = this;
                that.$http.post("/api/dict/queryList", {
                    type: 10
                }).then(res => {
                    that.songHongZhuangTaiList = res.data;
                }).catch(err => {
                    that.$message.error("获取送货状态出错:" + err)
                });
            },
            loadChanPinList(){
                const that = this;
                that.$http.post("/api/product/queryList", {}).then(res => {
                    that.chanpingList = res.data;
                }).catch(err => {
                    that.$message.error("获取产品信息列表：" + err)
                });

            },
            loadKehuList(){
                const that = this;
                that.$http.post("/api/customer/queryList",{}).then(res => {
                    that.kehuList  = res.data;
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
                    that.form.total = that.form.price * that.form.num;
                    that.$http.post("/api/outbound/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
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
                    status: null,// 送货状态
                    remark: null,// 备注
                    prods:[{priice: 0 , num: 1,bus_type:2}]
                }
            },
            addDialog() {//新增
                this.title = "新增出库记录";
                this.dialogMode = "save";
                this.form = this.initForm();
                this.show = true;
            },
            editDialog(row) {//修改
                this.title = "修改出库记录";
                this.dialogMode = "update";
                this.form = {...row};
                this.show = true;
            },
        }

    }
</script>
<style></style>