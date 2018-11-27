/*客户模拟数据},作者:唐植超,日期:2018-11-27 17:15:51*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/customer/queryPage': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                cus_no: "@word(5,10)",// 客户编号
                cus_name: "@word(5,10)",// 单位名称
                address: "@word(5,10)",// 客户地址
                cus_type: "@integer(100,200)",//客户性质
                remark: "@word(5,10)",// 备注
            }],
            'number': '@integer(100,200)',
            'size': 10,
            'totalElements': 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },

    'POST /api/customer/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/customer/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/customer/queryList': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                cus_no: "@word(5,10)",// 客户编号
                cus_name: "@word(5,10)",// 单位名称
                address: "@word(5,10)",// 客户地址
                cus_type: "@integer(100,200)",//客户性质
                remark: "@word(5,10)",// 备注
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },

    'POST /api/customer/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}