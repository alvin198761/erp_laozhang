/*类型字典模拟数据},作者:唐植超,日期:2018-11-27 14:04:59*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {


    'GET /api/dict/fetchCompany': function (req, res, next) {
        setTimeout(function () {
            res.json({
                address: "测试地址",
                name: "测试公司",
                head: "负责人呵呵"
            });
        }, 500);
    },
    'POST //api/heartbeat': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}