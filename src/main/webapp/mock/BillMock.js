/*开票信息模拟数据},作者:唐植超,日期:2018-11-27 14:04:59*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/bill/queryPage': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                vendor_id: "@integer(100,200)",//供应商
                bank: "@word(5,10)",// 开户行
                account: "@word(5,10)",// 账号
                taxpayer_no: "@word(5,10)",// 纳税人识别号
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

    'POST /api/bill/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/bill/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/bill/queryList': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                vendor_id: "@integer(100,200)",//供应商
                bank: "@word(5,10)",// 开户行
                account: "@word(5,10)",// 账号
                taxpayer_no: "@word(5,10)",// 纳税人识别号
                remark: "@word(5,10)",// 备注
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },

    'POST /api/bill/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}