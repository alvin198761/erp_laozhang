/*客户联系人模拟数据},作者:唐植超,日期:2018-11-27 14:04:59*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/person/queryPage': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                cus_id: "@integer(100,200)",//客户id
                address_id: "@integer(100,200)",//收寄方ID
                cus_name: "@cword(5,10)",//收寄方ID
                address: "@cword(5,10)",//收寄方ID
            }],
            'number': '@integer(100,200)',
            'size': 10,
            'totalElements': 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },

    'POST /api/person/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/person/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/person/queryList': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                cus_id: "@integer(100,200)",//客户id
                address_id: "@integer(100,200)",//收寄方ID
                cus_name: "@cword(5,10)",//收寄方ID
                address: "@cword(5,10)",//收寄方ID
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },

    'POST /api/person/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}