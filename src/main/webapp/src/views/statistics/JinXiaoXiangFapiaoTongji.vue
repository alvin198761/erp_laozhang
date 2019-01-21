<template>
    <div>
        <el-row :gutter="10">
            <el-col :span="5">
                <el-card>
                    <div slot="header">查询条件</div>
                    <el-form label-width="80px">
                        <el-form-item label="税收分类">
                            <el-select v-model="form.tax_type" placeholder="请选择税收分类" style="width: 100%" size="small">
                                <el-option
                                        v-for="item in shuiShouLeixingList"
                                        :key="item.id"
                                        :label="item.name + '('+ item.tax_code +')'"
                                        :value="item.name">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="产品名称">
                            <el-input placeholder="产品名称" size="small" v-model="form.prod_name"></el-input>
                        </el-form-item>
                        <el-form-item label="规格型号">
                            <el-input placeholder="规格型号" size="small" v-model="form.spec_no"></el-input>
                        </el-form-item>
                        <!--<el-form-item label="日期">-->
                        <!--<el-input placeholder="日期" size="small" v-model="form.name"></el-input>-->
                        <!--</el-form-item>-->

                        <el-form-item label="单价">
                            <el-input placeholder="单价" size="small" v-model="form.price"></el-input>
                        </el-form-item>
                        <el-form-item label="数量">
                            <el-input placeholder="数量" size="small" v-model="form.num"></el-input>
                        </el-form-item>

                        <el-form-item>
                            <el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small" type="primary">
                                统计查询
                            </el-button>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-col>
            <el-col :span="19">
                <el-card>
                    <div slot="header">统计结果</div>
                    <el-row :gutter="20">
                        <el-col :span="6">
                            <el-card shadow="never" class="dashboard-stat blue" style="color: white">
                                <div style="text-align: center; "><h4>进项数量<br/><b>{{indataList.length}}</b></h4></div>
                            </el-card>
                        </el-col>
                        <el-col :span="6">
                            <el-card shadow="never" class="dashboard-stat purple" style="color: white">
                                <div style="text-align: center"><h4>进项总价<br/><b>{{inTotalPrice}}</b></h4></div>
                            </el-card>
                        </el-col>
                        <el-col :span="6">
                            <el-card shadow="never" class="dashboard-stat green" style="color: white">
                                <div style="text-align: center"><h4>销项数量<br/><b>{{outdataList.length}}</b></h4></div>
                            </el-card>
                        </el-col>
                        <el-col :span="6">
                            <el-card shadow="never" class="dashboard-stat red" style="color: white">
                                <div style="text-align: center"><h4>销项总价<br/><b>{{outTotalPrice}}</b></h4></div>
                            </el-card>
                        </el-col>

                    </el-row>
                </el-card>
                <br/>
                <el-card>
                    <div slot="header">结果信息</div>
                    <el-tabs v-model="activeName">
                        <el-tab-pane label="进项发票详情" name="first">
                            <el-table :data="indataList" v-loading="loading" element-loading-text="正在加载......"
                                      style="width: 100%">
                                <el-table-column type="expand">
                                    <template slot-scope="props">
                                        <el-form>
                                            <el-row :gutter="10">
                                                <el-col :span="12">
                                                    <el-form-item label="供应商电话">{{props.row.address_phone_no}}
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :span="12">
                                                    <el-form-item label="供应商姓名">{{props.row.address_name}}
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :span="24">
                                                    <el-form-item label="供应商地址">{{props.row.address}}</el-form-item>
                                                </el-col>
                                                <el-col :span="24">
                                                    <el-form-item label="备注">{{props.row.remark}}</el-form-item>
                                                </el-col>
                                                <el-col :span="24">
                                                    <el-form-item label="产品列表">
                                                        <el-table :data="props.row.prods" style="width: 100%">

                                                            <el-table-column prop="prod_id" label="产品">
                                                                <template slot-scope="sprops">
                                                                    {{sprops.row.prod_name}} ({{sprops.row.prod_no}})
                                                                </template>
                                                            </el-table-column>
                                                            <el-table-column prop="spec_no" label="规格型号"
                                                                             width="100"></el-table-column>
                                                            <el-table-column prop="priice" label="单价"
                                                                             width="100"></el-table-column>
                                                            <el-table-column prop="num" label="数量"
                                                                             width="100"></el-table-column>
                                                            <el-table-column prop="total" label="总价"
                                                                             width="100"></el-table-column>
                                                        </el-table>
                                                    </el-form-item>
                                                </el-col>

                                            </el-row>
                                        </el-form>
                                    </template>
                                </el-table-column>
                                <el-table-column label="供应商">
                                    <template slot-scope="props">
                                        {{props.row.vendor_name}}({{props.row.vendor_no}})
                                    </template>
                                </el-table-column>
                                <el-table-column prop="tax_type" label="税收分类" width="150"></el-table-column>
                                <el-table-column prop="target_name" label="姓名" width="100"></el-table-column>
                                <el-table-column prop="ticket_type" label="发票类型" width="150"></el-table-column>
                                <el-table-column prop="rate" label="税率" width="50"></el-table-column>
                            </el-table>
                        </el-tab-pane>
                        <el-tab-pane label="销项发票详情" name="second">
                            <el-table :data="outdataList" v-loading="loading" element-loading-text="正在加载......"
                                      style="width: 100%">
                                <el-table-column type="expand">
                                    <template slot-scope="props">
                                        <el-form>
                                            <el-row :gutter="10">
                                                <el-col :span="12">
                                                    <el-form-item label="供应商电话">{{props.row.address_phone_no}}
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :span="12">
                                                    <el-form-item label="供应商姓名">{{props.row.address_name}}
                                                    </el-form-item>
                                                </el-col>
                                                <el-col :span="24">
                                                    <el-form-item label="供应商地址">{{props.row.address}}</el-form-item>
                                                </el-col>
                                                <el-col :span="24">
                                                    <el-form-item label="备注">{{props.row.remark}}</el-form-item>
                                                </el-col>
                                                <el-col :span="24">
                                                    <el-form-item label="产品列表">
                                                        <el-table :data="props.row.prods" style="width: 100%">

                                                            <el-table-column prop="prod_id" label="产品">
                                                                <template slot-scope="sprops">
                                                                    {{sprops.row.prod_name}} ({{sprops.row.prod_no}})
                                                                </template>
                                                            </el-table-column>
                                                            <el-table-column prop="spec_no" label="规格型号"
                                                                             width="100"></el-table-column>
                                                            <el-table-column prop="priice" label="单价"
                                                                             width="100"></el-table-column>
                                                            <el-table-column prop="num" label="数量"
                                                                             width="100"></el-table-column>
                                                            <el-table-column prop="total" label="总价"
                                                                             width="100"></el-table-column>
                                                        </el-table>
                                                    </el-form-item>
                                                </el-col>

                                            </el-row>
                                        </el-form>
                                    </template>
                                </el-table-column>
                                <el-table-column label="供应商">
                                    <template slot-scope="props">
                                        {{props.row.vendor_name}}({{props.row.vendor_no}})
                                    </template>
                                </el-table-column>
                                <el-table-column prop="tax_type" label="税收分类" width="150"></el-table-column>
                                <el-table-column prop="target_name" label="姓名" width="100"></el-table-column>
                                <el-table-column prop="ticket_type" label="发票类型" width="150"></el-table-column>
                                <el-table-column prop="rate" label="税率" width="50"></el-table-column>
                            </el-table>
                        </el-tab-pane>

                    </el-tabs>
                    <br/>
                </el-card>
            </el-col>
        </el-row>
        <br/>
    </div>
</template>
<script>

    export default{
        components: {},
        data: function () {
            return {
                indataList: [],
                outdataList: [],
                form: {
                    tax_type: null,
                    spec_no: null,
                    prod_name: null,
                    price: null,
                    num: null,
                },
                shuiShouLeixingList: [],
                loading: false,
                activeName: 'first'
            }
        },
        computed: {
            inTotalPrice(){
                if (this.indataList.length == 0) {
                    return 0;
                }
                let total = 0;
                for (let i = 0; i < this.indataList.length; i++) {
                    if (!this.indataList[i].prods) {
                        continue;
                    }
                    for (let j = 0; j < this.indataList[i].prods.length; j++) {
                        let pitem = this.indataList[i].prods[j];
                        total += pitem.priice * pitem.num;
                    }
                }
                return total; //计算总价
            },
            outTotalPrice(){
                if (this.outdataList.length == 0) {
                    return 0;
                }
                let total = 0;
                for (let i = 0; i < this.outdataList.length; i++) {
                    if (!this.outdataList[i].prods) {
                        continue;
                    }
                    for (let j = 0; j < this.outdataList[i].prods.length; j++) {
                        let pitem = this.outdataList[i].prods[j];
                        total += pitem.priice * pitem.num;
                    }
                }
                return total; //计算总价
            }
        },
        created: function () {
            this.loadShuiShouLeixingList();
        },
        methods: {
            refresh(){
                this.loadDataList(1);
                this.loadDataList(2);
            },
            loadDataList(type){
                const that = this;
                that.loading = true;
                const requestData = {... that.form};
                requestData.type = type;
                var method = "queryInList";
                if (type == 2) {
                    method = "queryOutList";
                }
                that.$http.post("/api/ticket/" + method, JSON.stringify(requestData)).then(res => {
                    that.loading = false;
                    if (type == 1) {
                        that.indataList = res.data
                    } else {
                        that.outdataList = res.data
                    }
                }).catch(res => {
                    that.$message.error("获取进销项发票录入列表失败：" + res);
                    that.loading = false;
                });
            },
            loadShuiShouLeixingList(){
                const that = this;
                that.$http.post("/api/taxCategory/queryList", {}).then(res => {
                    that.shuiShouLeixingList = res.data;
                }).catch(err => {
                    that.$message.error("获取税收分类出错:" + err)
                });
            },
        }
    }
</script>
<style>

</style>
