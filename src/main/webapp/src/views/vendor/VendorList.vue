/*供应商管理,作者:唐植超,日期:2018-11-27 17:05:46*/
<template>
    <div>
        <el-form :inline="true">
            <el-form-item label="供应商编号">
                <el-input placeholder="请输入供应商编号" size="small" v-model="form.vendor_no"></el-input>
            </el-form-item>
            <el-form-item label="供应商名称">
                <el-input placeholder="请输入供应商名称" size="small" v-model="form.vendor_name"></el-input>
            </el-form-item>
            <!--<el-form-item label="供应商地址">-->
            <!--<el-input placeholder="请输入供应商地址" size="small" v-model="form.address"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="供应商级别">-->
                <!--<el-input placeholder="请输入供应商级别" size="small" v-model="form.level"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="备注">-->
            <!--<el-input placeholder="请输入备注" size="small" v-model="form.remark"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="联系人">-->
            <!--<el-input placeholder="请输入联系人" size="small" v-model="form.concat_id"></el-input>-->
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
                                <el-form-item label="供应商地址">{{props.row.address}}</el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item label="备注">{{props.row.remark}}</el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </template>
            </el-table-column>
            <!--<el-table-column prop="id" label="主键"></el-table-column>-->
            <el-table-column prop="vendor_no" label="供应商编号"></el-table-column>
            <el-table-column prop="vendor_name" label="供应商名称"></el-table-column>
            <el-table-column prop="level_name" label="供应商级别"></el-table-column>
            <!--<el-table-column prop="concat_id" label="联系人"></el-table-column>-->
            <el-table-column label="操作" width="200">
                <template slot-scope="props">
                    <div>
                        <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
                        <el-button type="text" @click="doDelete(props.row)">删除</el-button>
                        <el-button type="text" @click="personList(props.row)">联系人管理</el-button>
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
        <VendorDialog ref="dialog" :refresh="refresh"></VendorDialog>
        <VendorPersonListDialog ref="personListDialog"></VendorPersonListDialog>
        <br/>
    </div>
</template>
<script>
    import VendorDialog from './VendorDialog.vue';
    import VendorPersonListDialog from './vendor_person/VendorPersonListDialog.vue';

    export default {
        components: {VendorDialog,VendorPersonListDialog},
        data: function () {
            return {
                total: 0,
                page: 1,
                size: 20,
                dataList: [],
                form: {
                    id: null,// 主键
                    vendor_no: null,// 供应商编号
                    vendor_name: null,// 供应商名称
                    address: null,// 供应商地址
                    level: null,// 供应商级别
                    remark: null,// 备注
                    concat_id: null,// 联系人
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
                that.$http.post("/api/vendor/queryPage", JSON.stringify(requestData)).then(res => {
                    that.loading = false;
                    that.dataList = res.data.content;
                    that.total = res.data.totalElements;
                }).catch(res => {
                    that.$message.error("获取供应商列表失败：" + res);
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
                    that.$http.delete("/api/vendor/delete", {
                        params: {id: row.id}
                    }).then(res => {
                        this.$message.success("删除成功");
                        that.refresh();
                    }).catch(res => {
                        that.$message.error("删除失败：" + res);
                    });
                }).catch(() => {
                });
            },
            personList(row){
                this.$refs["personListDialog"].showDialog(row.id);
            }
        }
    }
</script>
<style></style>