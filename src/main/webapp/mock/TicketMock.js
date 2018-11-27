/*进销项发票录入模拟数据},作者:唐植超,日期:2018-11-27 14:04:59*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

  'POST /api/ticket/queryPage': function (req, res, next) {
    var data = Mock.mock({
      'content|10': [{
                                    id: "@integer(100,200)",//主键
                                                vendor_id: "@integer(100,200)",//供应商id
                                                tax_type : "@word(5,10)",// 税收类型
                                                prod_id: "@integer(100,200)",//产品id
                                                target_name : "@word(5,10)",// 姓名
                                                target_id: "@integer(100,200)",//收寄方id
                                                remark : "@word(5,10)",// 备注
                                                num: "@integer(100,200)",//数量
                                                price: "@integer(100,200)",//单价
                                                total: "@integer(100,200)",//总价
                                                ticket_type: "@integer(100,200)",//发票类型
                                                rate: "@integer(100,200)",//税率
                                                type : "@word(5,10)",// 进出类型
                                                cus_id: "@integer(100,200)",//客户Id
                          }],
      'number': '@integer(100,200)',
      'size': 10,
      'totalElements': 500,
    });
    setTimeout(function () {
      res.json(data);
    }, 500);
  },

  'POST /api/ticket/update': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },

  'POST /api/ticket/save': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },

  'POST /api/ticket/queryList': function (req, res, next) {
    var data = Mock.mock({
      'content|10': [{
                                    id: "@integer(100,200)",//主键
                                                vendor_id: "@integer(100,200)",//供应商id
                                                tax_type : "@word(5,10)",// 税收类型
                                                prod_id: "@integer(100,200)",//产品id
                                                target_name : "@word(5,10)",// 姓名
                                                target_id: "@integer(100,200)",//收寄方id
                                                remark : "@word(5,10)",// 备注
                                                num: "@integer(100,200)",//数量
                                                price: "@integer(100,200)",//单价
                                                total: "@integer(100,200)",//总价
                                                ticket_type: "@integer(100,200)",//发票类型
                                                rate: "@integer(100,200)",//税率
                                                type : "@word(5,10)",// 进出类型
                                                cus_id: "@integer(100,200)",//客户Id
                          }]
    });
    setTimeout(function () {
      res.json(data.content);
    }, 500);
  },

  'POST /api/ticket/delete': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
}