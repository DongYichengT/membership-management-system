<template>
  <div class="customer-info-page">
    <h1>客户信息</h1>
    <p>用户名: {{ user.username }}</p>
    <p>积分: {{ user.points }}</p>
    <p>等级: {{ user.level }}</p>
    <h2>积分更改记录</h2>
    <ul>
      <li v-for="record in pointRecords" :key="record.id">
        {{ record.date }} - {{ record.pointsChange }}
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      user: null,
      pointRecords: []
    };
  },
  mounted() {
    const username = this.$route.params.username;
    this.fetchUserInfo(username);
  },
  methods: {
    async fetchUserInfo(username) {
      try {
        const response = await axios.get(
          `http://localhost:8085/api/customers/${username}`
        );
        this.user = response.data.user;
        this.pointRecords = response.data.pointRecords;
      } catch (error) {
        console.error("Error fetching user info:", error);
      }
    }
  }
};
</script>

<style scoped>
.customer-info-page p {
  margin: 5px;
}
.customer-info-page ul {
  list-style-type: none;
  padding-left: 0;
}
</style>