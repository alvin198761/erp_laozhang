<template>
    <div id="app">
        <div class="page-header-fixed" style="height: 100%">
            <div class="header navbar navbar-inverse navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <div class=" responsive"><a class="brand" href="#/welcome" style="padding-left: 5px"> <span
                                style="font-size:20px;color:#ffffff">{{company.name}}</span><span
                                style="font-size:20px;color:#B53535">有限公司</span>
                        </a>
                            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse"
                               data-target=".nav-collapse"> <img
                                    src="/static/media/image/menu-toggler.png" alt=""/> </a></div>
                        <div class="responsive">
                        </div>
                        <div class=" responsive">
                            <ul class="nav pull-right">
                                <li class="dropdown user"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img alt="" src="/static/media/image/avatar1_small.jpg"/>
                                    <i class="fa fa-angle-down" style="cursor: pointer"></i> </a>
                                    <ul class="dropdown-menu">
                                        <!--<li><a href="#/personalCenter"><i class="fa fa-user"></i>个人中心</a></li>-->
                                        <!--<li><a href="#/modifyPassword"><i class="fa fa-key"></i>修改密码</a></li>-->
                                        <li><a href="/j_spring_security_logout"><i class="fa fa-sign-out"></i>退出登录</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="page-container" style="height: 100%">
                <div class="page-sidebar nav-collapse collapse" style="height: 100% !important;">
                    <!-- BEGIN SIDEBAR MENU -->
                    <ul class="page-sidebar-menu">

                        <li>
                            <H4 style="color: white ;padding-left: 10px">ERP系统</H4>
                        </li>
                        <li class="active ">
                            <a href="javascript:;">
                                <i class="icon-cogs"></i>
                                <span class="title">业务管理</span>
                                <span class="selected"></span>
                            </a>
                            <ul class="sub-menu">
                                <li>
                                    <a href="#/inbound">
                                        入库管理</a>
                                </li>
                            </ul>
                            <ul class="sub-menu">
                                <li>
                                    <a href="#/outbound">
                                        出库管理 </a>
                                </li>
                            </ul>
                            <ul class="sub-menu">
                                <li>
                                    <a href="#/quote">
                                        报价管理 </a>
                                </li>
                            </ul>
                            <ul class="sub-menu">
                                <li>
                                    <a href="#/ticket"> 进销项发票 </a>
                                </li>
                            </ul>
                        </li>

                        <li class="active ">
                            <a href="javascript:;">
                                <i class="icon-cogs"></i>
                                <span class="title">信息管理</span>
                                <span class="selected"></span>
                            </a>
                            <ul class="sub-menu">
                                <li>
                                    <a href="#/custom">
                                        客户信息</a>
                                </li>
                            </ul>
                            <ul class="sub-menu">
                                <li>
                                    <a href="#/vendor">
                                        供应商信息</a>
                                </li>
                            </ul>
                            <ul class="sub-menu">
                                <li>
                                    <a href="#/product">
                                        产品信息</a>
                                </li>
                            </ul>
                            <ul class="sub-menu">
                                <li>
                                    <a href="#/bill">
                                        开票信息</a>
                                </li>
                            </ul>
                            <ul class="sub-menu">
                                <li>
                                    <a href="#/address">
                                        寄收方信息 </a>
                                </li>
                            </ul>
                        </li>

                        <li class="active ">
                            <a href="javascript:;">
                                <i class="icon-cogs"></i>
                                <span class="title">统计相关</span>
                                <span class="selected"></span>
                            </a>
                            <ul class="sub-menu">
                                <li>
                                    <a href="#/jxxfptj">
                                        进销项发票统计 </a>
                                </li>
                            </ul>
                        </li>
                        <li class="active ">
                            <a href="#/settings">
                                <i class="icon-cogs"></i>
                                <span class="title">基础设置</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="page-content">
                    <div class="container-fluid"
                    >
                        <div class="row-fluid">
                            <div class="span12">
                                <div style="margin-top: 10px">
                                    <ul class="breadcrumb">
                                        <li :key="p.path" v-for="(p,index) in sitePath">
                                            <a v-if="p.path != '/'" :href="'#' + (p.path == '' ? '/welcome' : p.path)">{{p.name}}</a>
                                            <i class="el-icon-arrow-right" v-if="index<sitePath.length-1"/>
                                        </li>

                                    </ul>
                                    <div id="routerDiv" style="min-height: 885px">
                                        <router-view></router-view>
                                    </div>

                                    <br/><br/><br/></div>
                            </div>
                        </div>
                    </div>
                    <div class="footer">
                        <div class="footer-inner"> Copyright &copy; {{company.name}}版权所有</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'app',
        data: function () {
            return {
                timer: null,
                versionTip: {
                    tip_content: null,
                    enabled: null
                },
                sitePath: [],
                activeIndex: null,
                company: {}
            }
        },
        methods: {
            init() {

            },

        },
        components: {},
        created: function () {
            const that = this;
            this.timer = setInterval(() => that.$http.get("/api/heartbeat"), 2 * 60 * 1000);
            this.sitePath = this.$route.matched;
            this.activeIndex = this.sitePath [this.sitePath.length - 1];
            this.$http.get("/api/dict/fetchCompany").then(res => {
                that.company = res.data;
            }).catch(err => {
                this.$message.error("获取公司信息失败");
            })
        },
        mounted: function () {

        },
        destroyed: function () {
            clearInterval(this.timer);
        }
        , watch: {
            '$route': function (to, from) {
                this.sitePath = to.matched;
                this.activeIndex = this.sitePath[this.sitePath.length - 1];
            }
        }
    }
</script>

<style>
    /*@import "../node_modules/font-awesome/css/font-awesome.min.css";*/

    .bounce-enter-active {
        animation: bounce-in .5s;
    }

    .bounce-leave-active {
        animation: bounce-out .2s;
    }

    @keyframes bounce-in {
        0% {
            transform: scale(0);
        }
        50% {
            transform: scale(1.05);
        }
        100% {
            transform: scale(1);
        }
    }

    @keyframes bounce-out {
        0% {
            transform: scale(1);
        }
        50% {
            transform: scale(0.95);
        }
        100% {
            transform: scale(0);
        }
    }

    html, body {
        background: #eee;
        height: 100%;
        width: 100%;
        min-width: 1440px;
        min-height: 750px;
        overflow: auto;
    }

    body {
        /*background-color: #324057;*/
        margin: 0px;
        padding: 0px;
        /*background: url(assets/bg1.jpg) center !important;
            background-size: cover;*/

        /*background: #1F2D3D;*/
        font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;
        font-weight: 400;
        -webkit-font-smoothing: antialiased;

    }

    #app {
        /*position: absolute;*/
        /*top: 0px;*/
        /*bottom: 0px;*/
        width: 100%;
        height: 100% !important;
    }

    a {
        color: #0f0f0f;
    }

    .fc-widget-header {
        height: 45px;
        line-height: 45px;
        background-color: #4b8df8;
        color: white;
    }

    #external-events .fc-event {
        height: 30px;
        text-align: center;
        line-height: 30px;
        color: white;
    }

    #external-events .fc-event:hover, .fc-event:hover {
        color: red;
        border-bottom-width: 2px;
        border-bottom-color: red;
        border-bottom-style: solid;
        cursor: move !important;
    }

    #external-events {
        /*border: none !important;*/
        background-color: white !important;
    }

    #external-events h4 {
        text-align: center;
        padding-bottom: 2px;
        border-bottom-width: 1px !important;
        border-bottom-color: #ddd !important;
        border-bottom-style: solid !important;
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

    form{
        margin: 0 0 0 0
    }
</style>
