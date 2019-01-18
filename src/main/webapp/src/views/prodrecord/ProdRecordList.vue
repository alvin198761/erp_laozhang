/*产品信息记录管理,作者:唐植超,日期:2019-01-18 13:46:52*/
<template>
      <div>
          <h3>产品信息</h3><br/>
          <!-- <h3>产品信息记录</h3> <hr/> -->
            <el-form :inline="true">
                                    <el-form-item label="主键">
                        <el-input placeholder="请输入主键" size="small" v-model="form.id"></el-input>
                    </el-form-item>
                                    <el-form-item label="产品id">
                        <el-input placeholder="请输入产品id" size="small" v-model="form.prod_id"></el-input>
                    </el-form-item>
                                    <el-form-item label="总价">
                        <el-input placeholder="请输入总价" size="small" v-model="form.total"></el-input>
                    </el-form-item>
                                    <el-form-item label="单价">
                        <el-input placeholder="请输入单价" size="small" v-model="form.priice"></el-input>
                    </el-form-item>
                                    <el-form-item label="数量">
                        <el-input placeholder="请输入数量" size="small" v-model="form.num"></el-input>
                    </el-form-item>
                                    <el-form-item label="业务ID">
                        <el-input placeholder="请输入业务ID" size="small" v-model="form.bus_id"></el-input>
                    </el-form-item>
                                    <el-form-item label="业务类型">
                        <el-input placeholder="请输入业务类型" size="small" v-model="form.bus_type"></el-input>
                    </el-form-item>
                                  <el-form-item>
                        <el-button icon="search" @click="refresh" title="根据输入的条件查询" size="small">查询</el-button>
                        <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
                      </el-form-item>
                </el-form>
            <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" style="width: 100%">

                                    <el-table-column prop="prod_id" label="产品id"></el-table-column>
                                    <el-table-column prop="total" label="总价"></el-table-column>
                                    <el-table-column prop="priice" label="单价"></el-table-column>
                                    <el-table-column prop="num" label="数量"></el-table-column>
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
              <el-pagination small layout="total,sizes,prev, pager, next" :current-page="page" :total="total" @current-change="(curr) => {this.page = curr ; this.refresh();}"
               :page-sizes="[10, 15, 20, 100]" @size-change="(s) => {this.size = s ; this.refresh();}" :page-size="size"></el-pagination>
            </div>
        <ProdRecordDialog ref="dialog" :refresh="refresh"></ProdRecordDialog>
  </div>
</template>
<script>
  import ProdRecordDialog from './ProdRecordDialog.vue';

  export default {
      components: { ProdRecordDialog },
      data: function () {
      return {
        total: 0,
        page: 1,
        size: 20,
        dataList: [],
        form: {
                    id : null,// 主键
                    prod_id : null,// 产品id
                    total : null,// 总价
                    priice : null,// 单价
                    num : null,// 数量
                    bus_id : null,// 业务ID
                    bus_type : null,// 业务类型
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
        that.$http.post("/api/prodRecord/queryPage", JSON.stringify(requestData)).then(res => {
		          that.loading = false;
		          that.dataList = res.data.content;
		          that.total = res.data.totalElements;
		        }).catch(res => {
		          that.$message.error("获取产品信息记录列表失败：" + res);
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
          that.$http.delete("/api/prodRecord/delete", {
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