/*报价模拟数据},作者:唐植超,日期:2018-11-27 14:04:59*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

  'POST /api/quote/queryPage': function (req, res, next) {
    var data = Mock.mock({
      'content|10': [{
                                    id: "@integer(100,200)",//主键
                                                cus_id: "@integer(100,200)",//客户id
                                                date: "@integer(100,200)",//报价日期
                                                prod_id: "@integer(100,200)",//产品id
                                                total: "@integer(100,200)",//总价
                                                priice: "@integer(100,200)",//单价
                                                remark : "@word(5,10)",// 备注
                                                status: "@integer(100,200)",//单据状态
                                                num: "@integer(100,200)",//数量
                          }],
      'number': '@integer(100,200)',
      'size': 10,
      'totalElements': 500,
    });
    setTimeout(function () {
      res.json(data);
    }, 500);
  },

  'POST /api/quote/update': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },

  'POST /api/quote/save': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },

  'POST /api/quote/queryList': function (req, res, next) {
    var data = Mock.mock({
      'content|10': [{
                                    id: "@integer(100,200)",//主键
                                                cus_id: "@integer(100,200)",//客户id
                                                date: "@integer(100,200)",//报价日期
                                                prod_id: "@integer(100,200)",//产品id
                                                total: "@integer(100,200)",//总价
                                                priice: "@integer(100,200)",//单价
                                                remark : "@word(5,10)",// 备注
                                                status: "@integer(100,200)",//单据状态
                                                num: "@integer(100,200)",//数量
                          }]
    });
    setTimeout(function () {
      res.json(data.content);
    }, 500);
  },

  'POST /api/quote/delete': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
}