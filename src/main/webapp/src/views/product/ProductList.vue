/*产品管理,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
    <div>
        <el-form :inline="true">
            <el-form-item label="货号">
                <el-input placeholder="请输入货号" size="small" v-model="form.prod_no"></el-input>
            </el-form-item>
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
                            <el-col :span="6">
                                <el-form-item label="底价">{{props.row.price}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="售卖价(含税)">{{props.row.sell_price_yes}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="售卖价(不含税)">{{props.row.sell_price_no}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="市场价(不含税)">{{props.row.mark_price_yes}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="市场价(含税)">{{props.row.mark_price_no}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="价格类型">{{props.row.price_mode}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="货物来源">{{props.row.vendor_id}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="产品图片1">{{props.row.pic1}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="产品图片2">{{props.row.pic2}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="产品图片3">{{props.row.pic3}}</el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="备注">{{props.row.remark}}</el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </template>
            </el-table-column>
            <!--<el-table-column prop="id" label="主键"></el-table-column>-->
            <el-table-column prop="prod_no" label="货号"></el-table-column>
            <el-table-column prop="shuiShouLeiXing" label="税收类型"></el-table-column>
            <el-table-column prop="prod_name" label="产品名称"></el-table-column>
            <el-table-column prop="spec_no" label="规格型号"></el-table-column>
            <el-table-column prop="note" label="产品说明"></el-table-column>
            <el-table-column prop="danWei" label="单位"></el-table-column>
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
        <ProductDialog ref="dialog" :refresh="refresh"></ProductDialog>
    </div>
</template>
<script>
    import ProductDialog from './ProductDialog.vue';

    export default {
        components: {ProductDialog},
        data: function () {
            return {
                total: 0,
                page: 1,
                size: 20,
                dataList: [],
                form: {
                    id: null,// 主键
                    prod_no: null,// 货号
                    tax_type: null,// 税收类型
                    prod_name: null,// 产品名称
                    spec_no: null,// 规格型号
                    note: null,// 产品说明
                    unit: null,// 单位
                    price: null,// 底价
                    sell_price_yes: null,// 售卖价(含税)
                    sell_price_no: null,// 售卖价(不含税)
                    mark_price_yes: null,// 市场价(不含税)
                    mark_price_no: null,// 市场价(含税)
                    price_mode: null,// 价格类型
                    vendor_id: null,// 货物来源
                    remark: null,// 备注
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
                const requestData = {...that.form, page: that.page - 1, size: that.size};
                that.$http.post("/api/product/queryPage", JSON.stringify(requestData)).then(res => {
                    that.loading = false;
                    that.dataList = res.data.content;
                    that.total = res.data.totalElements;
                }).catch(res => {
                    that.$message.error("获取产品列表失败：" + res);
                    that.loading = false;
                });
            },
            doAdd() {
                this.$refs["dialog"].addDialog();
            },
            doEdit(row) {
                this.$refs["dialog"].editDialog(row);
            },
            doDelete(row) {
                const that = this;
                this.$confirm('你确定要删除吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    that.$http.delete("/api/product/delete", {
                        params: {id: row.id}
                    }).then(res => {
                        this.$message.success("删除成功");
                        that.refresh();
                    }).catch(res => {
                        that.$message.error("删除失败：" + res);
                    });
                }).catch(() => {
                });
            }
        }
    }
</script>
<style></style>