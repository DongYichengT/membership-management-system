<template>
  <div>
    <h2>注册</h2>
    <input v-model="username" placeholder="用户名" />
    <input v-model="password" type="password" placeholder="密码" />
    <button @click="register">注册</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    register() {
      const userData = {
        username: this.username,
        password: this.password,
      };
      this.$http.post('http://localhost:8083/api/users', userData).then(() => {
        alert('注册成功');
        this.$router.push('/customer-login');
      });
    },
  },
};
</script>