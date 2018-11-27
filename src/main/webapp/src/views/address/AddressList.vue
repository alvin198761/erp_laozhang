/*收寄信息管理,作者:唐植超,日期:2018-11-27 14:04:59*/
<template>
      <div><h3>收寄信息</h3>
            <hr/>
            <el-form :inline="true">
                                    <el-form-item label="主键">
                        <el-input placeholder="请输入主键" size="small" v-model="form.id"></el-input>
                    </el-form-item>
                                    <el-form-item label="类型">
                        <el-input placeholder="请输入类型" size="small" v-model="form.type"></el-input>
                    </el-form-item>
                                    <el-form-item label="电话">
                        <el-input placeholder="请输入电话" size="small" v-model="form.target_phone"></el-input>
                    </el-form-item>
                                    <el-form-item label="地址">
                        <el-input placeholder="请输入地址" size="small" v-model="form.target_addr"></el-input>
                    </el-form-item>
                                    <el-form-item label="姓名">
                        <el-input placeholder="请输入姓名" size="small" v-model="form.target_name"></el-input>
                    </el-form-item>
                                    <el-form-item label="收寄方id">
                        <el-input placeholder="请输入收寄方id" size="small" v-model="form.target_id"></el-input>
                    </el-form-item>
                                    <el-form-item label="备注">
                        <el-input placeholder="请输入备注" size="small" v-model="form.remark"></el-input>
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
                                              <el-form-item label="主键">{{props.row.id}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="类型">{{props.row.type}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="电话">{{props.row.target_phone}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="地址">{{props.row.target_addr}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="姓名">{{props.row.target_name}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="收寄方id">{{props.row.target_id}}</el-form-item>
                                          </el-col>
                                                                                <el-col :span="6">
                                              <el-form-item label="备注">{{props.row.remark}}</el-form-item>
                                          </el-col>
                                                                            </el-row>
                                </el-form>
                        </template>
                  </el-table-column>
                                    <el-table-column prop="id" label="主键"></el-table-column>
                                    <el-table-column prop="type" label="类型"></el-table-column>
                                    <el-table-column prop="target_phone" label="电话"></el-table-column>
                                    <el-table-column prop="target_addr" label="地址"></el-table-column>
                                    <el-table-column prop="target_name" label="姓名"></el-table-column>
                                    <el-table-column prop="target_id" label="收寄方id"></el-table-column>
                                    <el-table-column prop="remark" label="备注"></el-table-column>
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
              <el-pagination small layout="sizes,prev, pager, next" :current-page="page" :total="total" @current-change="(curr) => {this.page = curr ; this.refresh();}"
               :page-sizes="[10, 15, 20, 100]" @size-change="(s) => {this.size = s ; this.refresh();}" :page-size="size"></el-pagination>
            </div>
        <AddressDialog ref="dialog" :refresh="refresh"></AddressDialog>
  </div>
</template>
<script>
  import AddressDialog from './AddressDialog.vue';

  export default {
      components: { AddressDialog },
      data: function () {
      return {
        total: 0,
        page: 1,
        size: 20,
        dataList: [],
        form: {
                    id : null,// 主键
                    type : null,// 类型
                    target_phone : null,// 电话
                    target_addr : null,// 地址
                    target_name : null,// 姓名
                    target_id : null,// 收寄方id
                    remark : null,// 备注
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
        that.$http.post("/api/address/queryPage", JSON.stringify(requestData)).then(res => {
		          that.loading = false;
		          that.dataList = res.data.content;
		          that.total = res.data.totalElements;
		        }).catch(res => {
		          that.$message.error("获取收寄信息列表失败：" + res);
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
          that.$http.delete("/api/address/delete", {
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