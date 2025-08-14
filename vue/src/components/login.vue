<template>
  <div>
    <h2>客户登录</h2>
    <form @submit.prevent="loginUser">
      <input v-model="username" placeholder="用户名" required />
      <input type="password" v-model="password" placeholder="密码" required />
      <button type="submit">登录</button>
      <button type="button" @click="goToRegister">去注册</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    loginUser() {
      const user = {
        username: this.username,
        password: this.password,
      };
      axios.post('http://localhost:8086/api/users/login', user)
        .then(response => {
          alert(response.data);
          this.$emit('login'); // 触发父组件方法
        })
        .catch(error => {
          console.error("登录失败:", error);
          alert('登录失败，用户名或密码错误。');
        });
    },
    goToRegister() {
      this.$emit('goToRegister'); // 触发去注册事件
    },
  },
};
</script>