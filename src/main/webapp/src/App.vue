<template>
    <div id="app">
        <el-container style="min-height: 500px; border: 1px solid #eee">
            <el-header>
                <el-row>
                    <el-col :span="20" style="text-align: left">
                        <b>老张滴管理系统</b>
                    </el-col>
                    <el-col :span="4" style="text-align: right; font-size: 12px">
                        <el-dropdown>
                            <span>老张 <i class="el-icon-setting" style="margin-right: 15px"></i></span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item>修改密码</el-dropdown-item>
                                <el-dropdown-item>退出登录</el-dropdown-item>
                                <!--<el-dropdown-item>删除</el-dropdown-item>-->
                            </el-dropdown-menu>
                        </el-dropdown>
                    </el-col>
                </el-row>
            </el-header>
            <el-container>
                <el-aside width="200px" style="background-color: rgb(238, 241, 246)  ">
                    <el-menu router   :default-openeds="['/inbound']">
                        <el-submenu index="/bus">
                            <template slot="title">
                                <i class="el-icon-menu"></i>
                                <span>业务管理</span>
                            </template>
                            <el-menu-item index="/inbound">
                                入库管理
                            </el-menu-item>
                            <el-menu-item index="/quote">报价管理</el-menu-item>
                            <el-menu-item index="/outbound">出库管理</el-menu-item>
                            <el-menu-item index="/ticket">进销项发票</el-menu-item>
                        </el-submenu>
                        <el-submenu index="/info">
                            <template slot="title">
                                <i class="el-icon-document"></i>
                                <span>信息管理</span>
                            </template>
                            <el-menu-item index="/custom">客户信息</el-menu-item>
                            <el-menu-item index="/vendor">供应商信息</el-menu-item>
                            <el-menu-item index="/product">产品信息</el-menu-item>
                            <el-menu-item index="/bill">开票信息</el-menu-item>
                            <el-menu-item index="/address">寄收方信息</el-menu-item>
                        </el-submenu>
                        <el-menu-item index="/settings">
                            <i class="el-icon-setting"></i>
                            基础设置</el-menu-item>
                    </el-menu>
                </el-aside>
                <el-main>
                    <div style="min-height: 700px ; height: 700px">
                        <router-view></router-view>
                    </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                timer: null
            };
        },
        created() {
            const that = this;
            this.timer = setInterval(() => that.$http.get("/api/heartbeat"), 2 * 60 * 1000);
        },
        destroyed() {
            clearInterval(this.timer);
        }
    }
</script>


<style lang="less">
    html, body, #app {
        width: 100%;
        height: 100%;
        margin: 0px;
    }

    .el-header, .el-footer, .el-header a, .el-footer a {
        background-color: #545c64;
        color: #fff;
        text-align: center;
        line-height: 60px;
        text-decoration: none;
    }

    /*.el-aside {*/
    /*!*background-color: #D3DCE6;*!*/
    /*color: #333;*/
    /*text-align: center;*/
    /*line-height: 200px;*/
    /*}*/

    /*.el-main {*/
    /*background-color: #E9EEF3;*/
    /*color: #333;*/
    /*text-align: center;*/
    /*line-height: 160px;*/
    /*}*/

    /*body > .el-container {*/
    /*margin-bottom: 40px;*/
    /*}*/

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

    .el-table td {
        padding: 2px 0 !important;
    }

    input, button, .el-card, .el-message, .el-alert, textarea, .el-dialog, .el-select-dropdown, .el-tag, .el-radio-button:first-child .el-radio-button__inner, .el-radio-button:last-child .el-radio-button__inner, .el-popover, .el-message-box {
        border-radius: 0px !important;
    }

    .el-form-item__content, .el-form-item__label {
        /*line-height: 30px;*/
    }

    .el-input__inner, .el-button, .el-cascader {
        /*height: 30px;*/
        line-height: 30px;
        font-size: 12px !important;

    }

    .el-button {
        padding: 0 20px;
    }

    .el-button--text {
        padding-right: 0px;
        padding-left: 0px;
    }

    .el-tree-node__content {
        height: 30px;
    }

    /*下拉展示样式*/
    .el-table__expanded-cell .el-form {
        margin: 20px 0px 10px 58px;

    }

    /*    调整间距*/
    .el-form .el-row .el-col .el-form-item {
        margin-bottom: 15px;
    }

    /*调整验证距离*/
    .el-form-item__error {
        padding-top: 1px !important
    }

    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }
</style>
