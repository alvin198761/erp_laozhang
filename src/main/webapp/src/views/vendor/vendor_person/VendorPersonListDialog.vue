<template>
    <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
        <div>
            <el-form :inline="true">
                <!--<el-form-item label="主键">-->
                <!--<el-input placeholder="请输入主键" size="small" v-model="form.id"></el-input>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="type">-->
                <!--<el-input placeholder="请输入type" size="small" v-model="form.type"></el-input>-->
                <!--</el-form-item>-->
                <!--<el-form-item label="name">-->
                <!--<el-input placeholder="请输入name" size="small" v-model="form.name"></el-input>-->
                <!--</el-form-item>-->
                <el-form-item>
                    <!--<el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>-->
                    <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
                </el-form-item>
            </el-form>
            <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" style="width: 100%">
                <el-table-column prop="name" label="姓名">
                    <template slot-scope="props">
                        <el-input v-model="props.row.name" size="small"></el-input>
                    </template>
                </el-table-column>
                <el-table-column prop="phone_no" label="联系方式">
                    <template slot-scope="props">
                        <el-input v-model="props.row.phone_no" size="small"></el-input>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="150">
                    <template slot-scope="props">
                        <div>
                            <el-button type="text" @click="doSave(props.row)">保存</el-button>
                            <el-button type="text" @click="doDelete(props.row)">删除</el-button>
                        </div>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </el-dialog>
</template>
<script>


    export default {
        components: {},
        data: function () {
            return {
                show: false,
                dataList: [],
                title: '供应商联系人',
                form: {
                    id: null,// 主键
                    bus_id: null,//
                    bus_type: 2,
                    name: null,
                    phone_no: null// name
                },
                loading: false
            }
        },
        computed: {},
        created: function () {

        },
        methods: {
            refresh() {
                const that = this;
                that.loading = true;
                that.$http.post("/api/person/queryList", JSON.stringify(that.form)).then(res => {
                    that.loading = false;
                    that.dataList = res.data;
                }).catch(res => {
                    that.$message.error("获取客户联系人列表失败：" + res);
                    that.loading = false;
                });
            },
            doAdd() {
                this.dataList.push({
                    id: null,// 主键
                    bus_id: null,// type
                    name: null,
                    phone_no: null// name
                });
            },
            doSave(row) {
               const that = this;
               that.$http.post("/api/person/save",JSON.stringify({
                   ...row,
                   bus_id:that.form.bus_id,
                   bus_type: 2,
               })).then(res => {
                   that.$message.success("保存成功!");
                   that.refresh();
               }).catch(err =>{
                   that.$message.error("保存失败:" + err)
               });
            },
            doDelete(row) {
                const that = this;
                this.$confirm('你确定要删除吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    that.$http.delete("/api/person/delete", {
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
            showDialog(cus_id) {
                this.form.bus_id = cus_id;
                this.refresh();
                this.show = true;
            }

        }
    }
</script>
<style></style>