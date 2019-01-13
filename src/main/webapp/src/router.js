import Vue from "vue";
import Router from "vue-router";
import RouterViewPage from "./views/RouterViewPage.vue";

import InboundList from './views/inbound/InboundList.vue';
import AddressList from './views/address/AddressList.vue';
import BillList from './views/bill/BillList.vue';
import Settings from './views/settings/Settings.vue';
import OutboundList from './views/outbound/OutboundList.vue';
import ProductList from './views/product/ProductList.vue';
import QuoteList from './views/quote/QuoteList.vue';
import TicketList from './views/ticket/TicketList.vue';
import VendorList from './views/vendor/VendorList.vue';
import CustomerList from './views/customer/CustomerList.vue';

Vue.use(Router)


export default new Router({
    routes: [
        {
            path: '/',
            component: RouterViewPage,
            name: '首页',
            redirect: '/inbound',
            children: [
                {
                    path: '/inbound', component: InboundList, name: '入库',
                },
                {
                    path: '/address', component: AddressList, name: '寄收方',
                },
                {
                    path: '/bill', component: BillList, name: '开票信息',
                },
                {
                    path: '/outbound', component: OutboundList, name: '出库',
                },
                {
                    path: '/product', component: ProductList, name: '产品',
                },
                {
                    path: '/quote', component: QuoteList, name: '报价',
                },
                {
                    path: '/ticket', component: TicketList, name: '进销项发票',
                },
                {
                    path: '/vendor', component: VendorList, name: '供应商',
                },
                {
                    path: '/custom', component: CustomerList, name: '客户',
                },
                {
                    path: '/settings' ,component : Settings, name: '基础设置',

                }
            ]
        }
    ]
})
