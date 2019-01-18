/*进销项发票录入管理,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
    <div>
        <el-form :inline="true">
            <!--<el-form-item label="主键">-->
            <!--<el-input placeholder="请输入主键" size="small" v-model="form.id"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="供应商id">-->
            <!--<el-input placeholder="请输入供应商id" size="small" v-model="form.vendor_id"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="税收类型">-->
            <!--<el-input placeholder="请输入税收类型" size="small" v-model="form.tax_type"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="产品id">-->
            <!--<el-input placeholder="请输入产品id" size="small" v-model="form.prod_id"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="姓名">-->
            <!--<el-input placeholder="请输入姓名" size="small" v-model="form.target_name"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="收寄方id">-->
            <!--<el-input placeholder="请输入收寄方id" size="small" v-model="form.target_id"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="备注">-->
            <!--<el-input placeholder="请输入备注" size="small" v-model="form.remark"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="数量">-->
            <!--<el-input placeholder="请输入数量" size="small" v-model="form.num"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="单价">-->
            <!--<el-input placeholder="请输入单价" size="small" v-model="form.price"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="总价">-->
            <!--<el-input placeholder="请输入总价" size="small" v-model="form.total"></el-input>-->
            <!--</el-form-item>-->
            <el-form-item label="发票类型">
                <el-input placeholder="请输入发票类型" size="small" v-model="form.ticket_type"></el-input>
            </el-form-item>
            <el-form-item label="税率">
                <el-input placeholder="请输入税率" size="small" v-model="form.rate"></el-input>
            </el-form-item>
            <!--<el-form-item label="进出类型">-->
                <!--<el-input placeholder="请输入进出类型" size="small" v-model="form.type"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="客户Id">-->
            <!--<el-input placeholder="请输入客户Id" size="small" v-model="form.cus_id"></el-input>-->
            <!--</el-form-item>-->
            <el-form-item>
                <el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>
                <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" style="width: 100%">
            <el-table-column type="expand">
                <template slot-scope="props">
                    <el-form>
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-form-item label="电话">{{props.row.address_phone_no}}</el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="姓名">{{props.row.address_name}}</el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="地址">{{props.row.address}}</el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="备注">{{props.row.remark}}</el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="产品列表">
                                    <el-table :data="props.row.prods"  style="width: 100%">

                                        <el-table-column prop="prod_id" label="产品">
                                            <template slot-scope="sprops">
                                                {{sprops.row.prod_name}} ({{sprops.row.prod_no}})
                                            </template>
                                        </el-table-column>
                                        <el-table-column prop="priice" label="单价" width="100"></el-table-column>
                                        <el-table-column prop="num" label="数量"  width="100"></el-table-column>
                                        <el-table-column prop="total" label="总价"  width="100"></el-table-column>
                                    </el-table>
                                </el-form-item>
                            </el-col>

                        </el-row>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column  label="供应商">
                <template slot-scope="props">
                    {{props.row.vendor_name}}({{props.row.vendor_no}})
                </template>
            </el-table-column>
            <!--<el-table-column   label="产品">-->
                <!--<template slot-scope="props">-->
                    <!--{{props.row.prod_name}}({{props.row.prod_no}})-->
                <!--</template>-->
            <!--</el-table-column>-->
            <el-table-column prop="tax_type" label="税收分类"></el-table-column>
            <el-table-column prop="target_name" label="姓名"></el-table-column>
            <!--<el-table-column prop="num" label="数量"></el-table-column>-->
            <!--<el-table-column prop="price" label="单价"></el-table-column>-->
            <!--<el-table-column prop="total" label="总价"></el-table-column>-->
            <el-table-column prop="ticket_type" label="发票类型"></el-table-column>
            <el-table-column prop="rate" label="税率"></el-table-column>
            <el-table-column label="操作" width="150">
                <template slot-scope="props">
                    <div>
                        <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
                        <el-button type="text" @click="doDelete(props.row)">删除</el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <br/>
        <div style="text-align: right" v-if="total > 0">
            <el-pagination small layout="total,sizes,prev, pager, next" :current-page="page" :total="total"
                           @current-change="(curr) => {this.page = curr ; this.refresh();}"
                           :page-sizes="[10, 15, 20, 100]" @size-change="(s) => {this.size = s ; this.refresh();}"
                           :page-size="size"></el-pagination>
        </div>
        <InTicketDialog ref="dialog" :refresh="refresh"></InTicketDialog>
    </div>
</template>
<script>
    import InTicketDialog from './InTicketDialog.vue';

    export default {
        components: {InTicketDialog},
        data: function () {
            return {
                total: 0,
                page: 1,
                size: 20,
                dataList: [],
                form: {
                    id: null,// 主键
                    vendor_id: null,// 供应商id
                    tax_type: null,// 税收类型
//                    prod_id: null,// 产品id
                    target_name: null,// 姓名
                    target_id: null,// 收寄方id
                    remark: null,// 备注
//                    num: null,// 数量
//                    price: null,// 单价
//                    total: null,// 总价
                    ticket_type: null,// 发票类型
                    rate: null,// 税率
                    type: 1,// 进出类型
                    cus_id: null,// 客户Id
                },
                loading: false
            }
        },
        computed: {},
        created: function () {
            this.refresh();
        },
        methods: {
            refresh() {
                const that = this;
                that.loading = true;
                const requestData = {... that.form, page : that.page - 1, size :   that.size  } ;
                that.$http.post("/api/ticket/queryPage", JSON.stringify(requestData)).then(res => {
                    that.loading = false;
                    that.dataList = res.data.content;
                    that.total = res.data.totalElements;
                }).catch(res => {
                    that.$message.error("获取进销项发票录入列表失败：" + res);
                that.loading = false;
            }) ;
            },
            doAdd() {
                this.$refs["dialog"].addDialog();
            },
            doEdit(row) {
                this.$refs["dialog"].editDialog(row);
            },
            doDelete(row) {
                const that = this;
                if(row.prods.length > 0){
                    this.$alert("请先删除产品列表");
                    return ;
                }
                this.$confirm('你确定要删除吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    that.$http.delete("/api/ticket/delete", {
                    params: {id: row.id}
                }).then(res => {
                    this.$message.success("删除成功");
                that.refresh();
            }).
                catch(res => {
                    that.$message.error("删除失败：" + res);
            })                ;
            }). catch(() => {}
            )
                ;
            }
        }
    }
</script>
<style></style>