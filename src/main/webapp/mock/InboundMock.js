/*入库记录模拟数据},作者:唐植超,日期:2018-11-27 14:04:59*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/inbound/queryPage': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                prod_id: "@word(5,10)",// 产品id
                date: "@integer(100,200)",//入库日期
                ticket_type: "@integer(100,200)",//发票类型
                ticket_status: "@integer(100,200)",//发票状态
                remark: "@word(5,10)",// 备注
                prod_name: "@cword(5,10)",//产品名称
            }],
            'number': '@integer(100,200)',
            'size': 10,
            'totalElements': 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },

    'POST /api/inbound/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/inbound/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/inbound/queryList': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                prod_id: "@word(5,10)",// 产品id
                date: "@integer(100,200)",//入库日期
                ticket_type: "@integer(100,200)",//发票类型
                ticket_status: "@integer(100,200)",//发票状态
                remark: "@word(5,10)",// 备注
                prod_name: "@cword(5,10)",//产品名称
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },

    'POST /api/inbound/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}