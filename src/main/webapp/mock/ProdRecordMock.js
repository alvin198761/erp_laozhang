/*产品信息记录模拟数据},作者:唐植超,日期:2019-01-18 13:46:52*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/prodRecord/queryPage': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                prod_id: "@integer(100,200)",//产品id
                total: "@integer(100,200)",//总价
                priice: "@integer(100,200)",//单价
                num: "@integer(100,200)",//数量
                bus_id: "@integer(100,200)",//业务ID
                bus_type: "@integer(100,200)",//业务类型
            }],
            'number': '@integer(100,200)',
            'size': 10,
            'totalElements': 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },

    'POST /api/prodRecord/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/prodRecord/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/prodRecord/queryList': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                prod_id: "@integer(100,200)",//产品id
                total: "@integer(100,200)",//总价
                priice: "@integer(100,200)",//单价
                num: "@integer(100,200)",//数量
                bus_id: "@integer(100,200)",//业务ID
                bus_type: "@integer(100,200)",//业务类型
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },

    'POST /api/prodRecord/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}