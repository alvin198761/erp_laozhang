/*产品模拟数据},作者:唐植超,日期:2018-11-27 14:04:59*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/product/queryPage': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                prod_no: "@word(5,10)",// 货号
                tax_type: "@word(5,10)",// 税收类型
                prod_name: "@word(5,10)",// 产品名称
                spec_no: "@word(5,10)",// 规格型号
                note: "@word(5,10)",// 产品说明
                unit: "@word(5,10)",// 单位
                price: "@integer(100,200)",//底价
                sell_price_yes: "@integer(100,200)",//售卖价(含税)
                sell_price_no: "@integer(100,200)",//售卖价(不含税)
                mark_price_yes: "@integer(100,200)",//市场价(不含税)
                mark_price_no: "@integer(100,200)",//市场价(含税)
                price_mode: "@integer(100,200)",//价格类型
                vendor_id: "@integer(100,200)",//货物来源
                pic1: "@word(5,10)",// 产品图片1
                pic2: "@word(5,10)",// 产品图片2
                pic3: "@word(5,10)",// 产品图片3
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

    'POST /api/product/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/product/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/product/queryList': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                prod_no: "@word(5,10)",// 货号
                tax_type: "@word(5,10)",// 税收类型
                prod_name: "@word(5,10)",// 产品名称
                spec_no: "@word(5,10)",// 规格型号
                note: "@word(5,10)",// 产品说明
                unit: "@word(5,10)",// 单位
                price: "@integer(100,200)",//底价
                sell_price_yes: "@integer(100,200)",//售卖价(含税)
                sell_price_no: "@integer(100,200)",//售卖价(不含税)
                mark_price_yes: "@integer(100,200)",//市场价(不含税)
                mark_price_no: "@integer(100,200)",//市场价(含税)
                price_mode: "@integer(100,200)",//价格类型
                vendor_id: "@integer(100,200)",//货物来源
                pic1: "@word(5,10)",// 产品图片1
                pic2: "@word(5,10)",// 产品图片2
                pic3: "@word(5,10)",// 产品图片3
                remark: "@word(5,10)",// 备注
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },

    'POST /api/product/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}