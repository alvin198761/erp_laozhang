/*出库记录管理,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
    <div>
        <el-form :inline="true">
            <!--<el-form-item label="主键">-->
            <!--<el-input placeholder="请输入主键" size="small" v-model="form.id"></el-input>-->
            <!--</el-form-item>-->
            <el-form-item label="客户">
                <el-select filterable  v-model="form.cus_id" placeholder="请选择客户" style="width: 100%" size="small">
                    <el-option label="全部" :value="-1"></el-option>
                    <el-option
                            v-for="item in kehuList"
                            :key="item.id"
                            :label="item.cus_name + '('+item.cus_no+')'"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="出库日期">
                <el-date-picker  size="small"
                        v-model="form.date"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="送货状态">
                <!--<el-input placeholder="请输入送货状态" size="small" v-model="form.status"></el-input>-->
                <el-select v-model="form.status" placeholder="请选择送货状态" style="width: 100%" size="small">
                    <el-option label="全部" :value="-1"></el-option>
                    <el-option
                            v-for="item in songHongZhuangTaiList"
                            :key="item.id"
                            :label="item.name"
                            :value="item.name">
                    </el-option>
                </el-select>
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
                                <el-form-item label="备注">{{props.row.remark}}</el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </template>
            </el-table-column>
            <!--<el-table-column prop="id" label="主键"></el-table-column>-->
            <el-table-column  label="客户">
                <template slot-scope="props">
                    {{props.row.cus_name}}({{props.row.cus_no}})
                </template>
            </el-table-column>
            <!--<el-table-column label="产品">-->
                <!--<template slot-scope="props">-->
                    <!--{{props.row.cus_name}}({{props.row.cus_no}})-->
                <!--</template>-->
            <!--</el-table-column>-->
            <!--<el-table-column prop="price" label="单价"></el-table-column>-->
            <!--<el-table-column prop="num" label="数量"></el-table-column>-->
            <!--<el-table-column prop="total" label="总价"></el-table-column>-->
            <el-table-column  label="出库日期">
                <template slot-scope="props">
                    {{props.row.date | date_filter}}
                </template>
            </el-table-column>
            <el-table-column prop="status" label="送货状态" width="150"></el-table-column>
            <el-table-column label="操作" width="200">
                <template slot-scope="props">
                    <div>
                        <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
                        <el-button type="text" @click="doDelete(props.row)">删除</el-button>
                        <a :href="'/preview/outbound/'+ props.row.id" class="el-button el-button--text" target="_blank">打印预览</a>
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
        <OutboundDialog ref="dialog" :refresh="refresh"></OutboundDialog><br/>
    </div>
</template>
<script>
    import OutboundDialog from './OutboundDialog.vue';

    export default {
        components: {OutboundDialog},
        data: function () {
            return {
                total: 0,
                page: 1,
                size: 20,
                dataList: [],
                form: {
                    id: null,// 主键
                    cus_id: -1,// 客户id
//                    prod_id: null,// 产品id
//                    price: null,// 单价
//                    num: null,// 数量
//                    total: null,// 总价
                    status: -1,// 送货状态
                    remark: null,// 备注
                    date: null
                },
                loading: false,
                kehuList:[],
                songHongZhuangTaiList:[],
            }
        },
        computed: {},
        created: function () {
            this.refresh();
            this.loadKehuList();
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
            loadKehuList(){
                const that = this;
                that.$http.post("/api/customer/queryList",{}).then(res => {
                    that.kehuList  = res.data;
                }).catch(err => {
                    that.$message.error("获取客户信息出错：" +err)
                });
            },
            refresh() {
                const that = this;
                that.loading = true;
                const requestData = {...that.form, page: that.page - 1, size: that.size};
                if(requestData.status == -1){
                    requestData.status = null;
                }
                if(requestData.cus_id == -1){
                    requestData.cus_id = null;
                }
                if(requestData.date != null && requestData.date.length == 2){
                    requestData.start = requestData.date[0];
                    requestData.end = requestData.date[1];
                }
                requestData.date = null;
                that.$http.post("/api/outbound/queryPage", JSON.stringify(requestData)).then(res => {
                    that.loading = false;
                    that.dataList = res.data.content;
                    that.total = res.data.totalElements;
                }).catch(res => {
                    that.$message.error("获取出库记录列表失败：" + res);
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
                if(row.prods.length > 0){
                    this.$alert("请先删除产品列表");
                    return ;
                }
                this.$confirm('你确定要删除吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    that.$http.delete("/api/outbound/delete", {
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