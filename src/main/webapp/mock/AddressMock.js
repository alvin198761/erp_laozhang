/*收寄信息模拟数据},作者:唐植超,日期:2018-11-27 14:04:59*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/address/queryPage': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                type: "@integer(100,200)",//类型
                target_phone: "@word(5,10)",// 电话
                target_addr: "@word(5,10)",// 地址
                target_name: "@word(5,10)",// 姓名
                target_id: "@integer(100,200)",//收寄方id
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

    'POST /api/address/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/address/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/address/queryList': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                type: "@integer(100,200)",//类型
                target_phone: "@word(5,10)",// 电话
                target_addr: "@word(5,10)",// 地址
                target_name: "@word(5,10)",// 姓名
                target_id: "@integer(100,200)",//收寄方id
                remark: "@word(5,10)",// 备注
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },

    'POST /api/address/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}