/*税收分类模拟数据},作者:唐植超,日期:2019-01-18 13:46:52*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/taxCategory/queryPage': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                name: "@word(5,10)",// 名称
                tax_code: "@word(5,10)",// 税收编码
            }],
            'number': '@integer(100,200)',
            'size': 10,
            'totalElements': 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },

    'POST /api/taxCategory/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/taxCategory/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/taxCategory/queryList': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                name: "@word(5,10)",// 名称
                tax_code: "@word(5,10)",// 税收编码
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },

    'POST /api/taxCategory/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}