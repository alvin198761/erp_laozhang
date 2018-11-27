/*出库记录模拟数据},作者:唐植超,日期:2018-11-27 14:04:59*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

  'POST /api/outbound/queryPage': function (req, res, next) {
    var data = Mock.mock({
      'content|10': [{
                                    id: "@integer(100,200)",//主键
                                                cus_id: "@integer(100,200)",//客户id
                                                prod_id: "@integer(100,200)",//产品id
                                                price: "@integer(100,200)",//单价
                                                num: "@integer(100,200)",//数量
                                                total: "@integer(100,200)",//总价
                                                status: "@integer(100,200)",//送货状态
                                                remark : "@word(5,10)",// 备注
                          }],
      'number': '@integer(100,200)',
      'size': 10,
      'totalElements': 500,
    });
    setTimeout(function () {
      res.json(data);
    }, 500);
  },

  'POST /api/outbound/update': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },

  'POST /api/outbound/save': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },

  'POST /api/outbound/queryList': function (req, res, next) {
    var data = Mock.mock({
      'content|10': [{
                                    id: "@integer(100,200)",//主键
                                                cus_id: "@integer(100,200)",//客户id
                                                prod_id: "@integer(100,200)",//产品id
                                                price: "@integer(100,200)",//单价
                                                num: "@integer(100,200)",//数量
                                                total: "@integer(100,200)",//总价
                                                status: "@integer(100,200)",//送货状态
                                                remark : "@word(5,10)",// 备注
                          }]
    });
    setTimeout(function () {
      res.json(data.content);
    }, 500);
  },

  'POST /api/outbound/delete': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
}