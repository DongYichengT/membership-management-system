import Vue from 'vue';
import Router from 'vue-router';
import Welcome from './components/Welcome.vue';
import AdminLogin from './components/AdminLogin.vue';
import CustomerLogin from './components/CustomerLogin.vue';
import UserList from './components/UserList.vue';
import CustomerInfo from './components/CustomerInfo.vue';
import Register from './components/Register.vue';
import CustomerLoginRegister from './components/CustomerLoginRegister.vue';
import CustomerLoginLogin from './components/CustomerLoginLogin.vue';
import ProductList from './components/ProductList.vue'; // 新增导入

Vue.use(Router);

export default new Router({
  routes: [
    { path: '/', component: Welcome },
    { path: '/admin-login', component: AdminLogin },
    { path: '/customer-login', component: CustomerLogin },
    { path: '/user-list', component: UserList },
    { path: '/customer-info/:username', component: CustomerInfo },
    { path: '/register', component: Register },
    { path: '/customer-login/register', component: CustomerLoginRegister },
    { path: '/customer-login/login', component: CustomerLoginLogin },
    { path: '/product-list', component: ProductList } // 新增路由
  ]
});