/*入库记录管理,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
    <div>
        <el-form :inline="true">
            <!--<el-form-item label="主键">-->
            <!--<el-input placeholder="请输入主键" size="small" v-model="form.id"></el-input>-->
            <!--</el-form-item>-->
            <el-form-item label="产品">
            <!--<el-input placeholder="请输入产品id" size="small" v-model="form.prod_id"></el-input>-->
                <el-select filterable v-model="form.prod_id" placeholder="请选择产品" style="width: 100%" size="small" >
                    <el-option label="全部" :value="-1"/>
                    <el-option
                            v-for="item in chanpingList"
                            :key="item.id"
                            :label="item.prod_name + '(' + item.prod_no + ')' + ' => 供应商：'+ item.vendor_name+'('+ item.vendor_no +')'"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="入库日期">
                <el-date-picker size="small"
                        v-model="form.date"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                </el-date-picker>
            <!--<el-input placeholder="请输入入库日期" size="small" v-model="form.date"></el-input>-->
            </el-form-item>
            <el-form-item label="发票类型">
                <el-select v-model="form.ticket_type" placeholder="请选择发票类型" style="width: 100%"
                           size="small">
                    <el-option label="全部" :value="-1"/>
                    <el-option
                            v-for="item in fapiaoLeixingList"
                            :key="item.id"
                            :label="item.name"
                            :value="item.name">
                    </el-option>
                </el-select>
                <!--<el-input placeholder="请输入发票类型" size="small" v-model="form.ticket_type"></el-input>-->
            </el-form-item>
            <el-form-item label="发票状态">
                <el-select v-model="form.ticket_status" placeholder="请选择发票状态" style="width: 100%"
                           size="small">
                    <el-option label="全部" :value="-1"/>
                    <el-option
                            v-for="item in fapiaoZhuangTaiList"
                            :key="item.id"
                            :label="item.name"
                            :value="item.name">
                    </el-option>
                </el-select>
            <!--<el-input placeholder="请输入发票状态" size="small" v-model="form.ticket_status"></el-input>-->
            </el-form-item>
            <!--<el-form-item label="备注">-->
            <!--<el-input placeholder="请输入备注" size="small" v-model="form.remark"></el-input>-->
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
                            <el-col :span="24">
                                <el-form-item label="备注">{{props.row.remark}}</el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </template>
            </el-table-column>
            <!--<el-table-column prop="id" label="主键"></el-table-column>-->
            <!--<el-table-column prop="prod_id" label="产品id"></el-table-column>-->
            <el-table-column  label="产品">
                <template slot-scope="props">
                    {{props.row.prod_name }}({{props.row.prod_no}})
                </template>
            </el-table-column>
            <el-table-column  label="供应商">
                <template slot-scope="props">
                    {{props.row.vendor_name }}({{props.row.vendor_no}})
                </template>
            </el-table-column>
            <el-table-column prop="date" label="入库日期">
                <template slot-scope="props">{{props.row.date |date_filter}}</template>
            </el-table-column>
            <el-table-column prop="ticket_type" label="发票类型" width="150"></el-table-column>
            <el-table-column prop="ticket_status" label="发票状态" width="150"></el-table-column>
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
        <InboundDialog ref="dialog" :refresh="refresh"></InboundDialog><br/>
    </div>
</template>
<script>
    import InboundDialog from './InboundDialog.vue';

    export default {
        components: {InboundDialog},
        data: function () {
            return {
                total: 0,
                page: 1,
                size: 20,
                dataList: [],
                form: {
                    id: null,// 主键
                    prod_id: -1,// 产品id
                    date: null,// 入库日期
                    ticket_type: -1,// 发票类型
                    ticket_status: -1,// 发票状态
                    remark: null,// 备注
                    vendor_id: -1
                },
                loading: false,
                chanpingList: [],
                fapiaoLeixingList: [],
                fapiaoZhuangTaiList: [],
                date: ''
            }
        },
        computed: {},
        created: function () {
            this.refresh();

            this.loadChanpingList();
            this.loadFapiaoLeixingList();
            this.loadFapiaoZhuangTaiList();
        },
        methods: {
            loadFapiaoZhuangTaiList(){
                const that = this;
                that.$http.post("/api/dict/queryList", {
                    type: 7
                }).then(res => {
                    that.fapiaoLeixingList = res.data;
                }).catch(err => {
                    that.$message.error("获取发票类型出错：" + err)
                });
            },
            loadFapiaoLeixingList(){
                const that = this;
                that.$http.post("/api/dict/queryList", {
                    type: 8
                }).then(res => {
                    that.fapiaoZhuangTaiList = res.data;
                }).catch(err => {
                    that.$message.error("获取发票状态出错：" + err)
                });
            },
            loadChanpingList(){
                const that = this;
                that.$http.post("/api/product/queryList", {}).then(res => {
                    that.chanpingList = res.data;
                    console.log(res.data);
                }).catch(err => {
                    that.$message.error("获取产品信息列表：" + err)
                });
            },
            refresh() {
                const that = this;
                that.loading = true;
                const requestData = {...that.form, page: that.page - 1, size: that.size};
                if(requestData.prod_id == -1){
                    requestData.prod_id = null;
                }
                if(requestData.vendor_id == -1){
                    requestData.vendor_id = null;
                }

                if(requestData.ticket_type == -1){
                    requestData.ticket_type = null;
                }
                if(requestData.ticket_status == -1){
                    requestData.ticket_status = null;
                }
                if(requestData.date != null && requestData.date.length == 2){
                    requestData.start =requestData.date[0];
                    requestData.end =requestData.date[1];
                }

                requestData.date = null;
                that.$http.post("/api/inbound/queryPage", JSON.stringify(requestData)).then(res => {
                    that.loading = false;
                    that.dataList = res.data.content;
                    that.total = res.data.totalElements;
                }).catch(res => {
                    that.$message.error("获取入库记录列表失败：" + res);
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
                    that.$http.delete("/api/inbound/delete", {
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