// import Vue from 'vue'
// import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'

// Vue.use(Router)

// export default new Router({
//   routes: [
//     {
//       path: '/',
//       name: 'HelloWorld',
//       component: HelloWorld
//     }
//   ]
// })
// import Vue from 'vue';
// import Router from 'vue-router';
// import Welcome from '@/components/Welcome.vue';
// import AdminLogin from '@/components/AdminLogin.vue';
// import CustomerLogin from '@/components/CustomerLogin.vue';
// import CustomerInfo from '@/components/CustomerInfo.vue';

// Vue.use(Router);

// export default new Router({
//   routes: [
//     {
//       path: '/',
//       name: 'welcome',
//       component: Welcome
//     },
//     {
//       path: '/admin-login',
//       name: 'admin-login',
//       component: AdminLogin
//     },
//     {
//       path: '/customer-login',
//       name: 'customer-login',
//       component: CustomerLogin,
//       children: [
//         {
//           path: 'login',
//           name: 'customer-login-login',
//           component: () => import('@/components/CustomerLoginLogin.vue')
//         },
//         {
//           path: 'register',
//           name: 'customer-login-register',
//           component: () => import('@/components/CustomerLoginRegister.vue')
//         }
//       ]
//     },
//     {
//       path: '/customer-info/:username',
//       name: 'customer-info',
//       component: CustomerInfo
//     }
//   ]
// });


import Vue from 'vue';
import Router from 'vue-router';
import Welcome from '@/components/Welcome.vue';
import AdminLogin from '@/components/AdminLogin.vue';
import CustomerLogin from '@/components/CustomerLogin.vue';
import booklist from '@/components/booklist.vue';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'booklist',
      component: booklist
    },
    {
      path: '/admin-login',
      name: 'admin-login',
      component: AdminLogin
    },
    {
      path: '/customer-login',
      name: 'customer-login',
      component: CustomerLogin,
      children: [
        {
          path: 'login',
          name: 'customer-login-login',
          component: () => import('@/components/CustomerLoginLogin.vue')
        },
        {
          path: 'register',
          name: 'customer-login-register',
          component: () => import('@/components/CustomerLoginRegister.vue')
        }
      ]
    },
    {
      path: '/customer-info/:username',
      name: 'customer-info',
      component: CustomerInfo
    },
	
  ]
});