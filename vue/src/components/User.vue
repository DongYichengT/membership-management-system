<template>
  <div class="user-list-page">
    <h1>会员用户列表</h1>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>用户名</th>
          <th>邮箱</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.id }}</td>
          <td>{{ user.username }}</td>
          <td>{{ user.email }}</td>
          <td>
            <button @click="editUser(user.id)">编辑</button>
            <button @click="deleteUser(user.id)">删除</button>
          </td>
        </tr>
      </tbody>
    </table>
    <button @click="addNewUser">添加新用户</button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      users: []
    };
  },
  mounted() {
    this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get("http://localhost:8085/api/users");
        this.users = response.data;
      } catch (error) {
        console.error("Error fetching users:", error);
      }
    },
    async addNewUser() {
      // 这里模拟一个新增用户的数据示例，实际需根据后端接口要求调整
      const newUser = {
        username: "new_user",
        email: "new@example.com"
      };
      try {
        await axios.post("http://localhost:8085/api/users", newUser);
        this.fetchUsers(); // 新增后重新获取用户列表，更新展示
      } catch (error) {
        console.error("Error adding new user:", error);
      }
    },
    async editUser(id) {
      // 假设编辑用户时弹出表单获取新数据等逻辑，这里简单示例
      const updatedUser = {
        username: "updated_user",
        email: "updated@example.com"
      };
      try {
        await axios.put(`http://localhost:8085/api/users/${id}`, updatedUser);
        this.fetchUsers(); // 更新后重新获取列表展示最新数据
      } catch (error) {
        console.error("Error editing user:", error);
      }
    },
    async deleteUser(id) {
      try {
        await axios.delete(`http://localhost:8085/api/users/${id}`);
        this.fetchUsers(); // 删除后重新获取列表更新展示
      } catch (error) {
        console.error("Error deleting user:", error);
      }
    }
  }
};
</script>

<style scoped>
.user-list-page table {
  width: 100%;
  border-collapse: collapse;
}
.user-list-page th,
.user-list-page td {
  border: 1px solid #ccc;
  padding: 8px;
}
.user-list-page button {
  margin: 5px;
  padding: 5px 10px;
  font-size: 14px;
}
</style>