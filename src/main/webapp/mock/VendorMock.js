/*供应商模拟数据},作者:唐植超,日期:2018-11-27 17:05:46*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/vendor/queryPage': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                vendor_no: "@word(5,10)",// 供应商编号
                vendor_name: "@word(5,10)",// 供应商名称
                address: "@integer(100,200)",//供应商地址
                level: "@integer(100,200)",//供应商级别
                remark: "@word(5,10)",// 备注
                concat_id: "@integer(100,200)",//联系人
                level_name:"@word(5,10)",//级别名称
            }],
            'number': '@integer(100,200)',
            'size': 10,
            'totalElements': 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },

    'POST /api/vendor/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/vendor/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/vendor/queryList': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                vendor_no: "@word(5,10)",// 供应商编号
                vendor_name: "@word(5,10)",// 供应商名称
                address: "@integer(100,200)",//供应商地址
                level: "@integer(100,200)",//供应商级别
                remark: "@word(5,10)",// 备注
                concat_id: "@integer(100,200)",//联系人
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },

    'POST /api/vendor/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}