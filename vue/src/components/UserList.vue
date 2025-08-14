<template>
	
  <div class="container mx-auto my-8">
    <img src="@/assets/magic_title.png" class="system-title" alt="System Title" />
    <img src="@/assets/userlist.png" class="userlist-title" alt="Userlist Title" />
    
    <form @submit.prevent="addUser" class="mb-4">
      <input v-model="newId" placeholder="ID" type="number" class="input-field" required />
      <input v-model="newUsername" placeholder="用户名" class="input-field" required />
      <input v-model="newEmail" placeholder="邮箱" class="input-field" required />
      <input v-model="newPassword" placeholder="密码" type="password" class="input-field" required />
      <button type="submit" class="btn-primary">添加用户</button>
    </form>

    <div class="mb-4">
      <input v-model="searchUsername" placeholder="按用户名查找" class="input-field" />
      <button @click="findUser" class="btn-secondary">查找用户</button>
	  <button @click="goToWelcome" class="back-button">返回主页面</button>
    </div>

    <ul class="space-y-2">
      <li v-for="user in paginatedUsers" :key="user.id" class="user-card">
        <template v-if="editingUserId === user.id">
          <input v-model="editedUsername" class="edit-input" />
          <input v-model="editedEmail" class="edit-input" />
          <input v-model="editedPassword" type="password" class="edit-input" />
          <button @click="updateUser(user.id)" class="btn-secondary">保存</button>
          <button @click="cancelEdit" class="btn-cancel">取消</button>
        </template>
        <template v-else>
          <span>
            ID: {{ user.id }}<br />
            用户名: {{ user.username }}<br />
            邮箱: {{ user.email }}<br />
            密码: {{ user.password }}<br />
            积分: {{ user.points }}<br />
          </span>
          <div class="flex space-x-2">
            <button @click="startEdit(user)" class="btn-warning">编辑</button>
            <button @click="deleteUser(user.id)" class="btn-danger">删除</button>
          </div>
        </template>
      </li>
    </ul>

    <!-- 分页控件 -->
    <div class="pagination">
      <button @click="previousPage" :disabled="currentPage === 0">上一页</button>
      <span>第 {{ currentPage + 1 }} 页 / 共 {{ totalPages }} 页</span>
      <button @click="nextPage" :disabled="currentPage >= totalPages - 1">下一页</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      users: [],
      newId: null,
      newUsername: '',
      newEmail: '',
      newPassword: '',
      searchUsername: '',
      editingUserId: null,
      editedUsername: '',
      editedEmail: '',
      editedPassword: '',
      currentPage: 0, // 当前页
      pageSize: 3, // 每页显示的用户数量
      totalPages: 0, // 总页数
    };
  },
  computed: {
    paginatedUsers() {
      const start = this.currentPage * this.pageSize;
      return this.users.slice(start, start + this.pageSize); // 返回当前页的用户
    }
  },
  mounted() {
    this.fetchUsers();
  },
  methods: {
	  
    async fetchUsers() {
      try {
        const response = await axios.get('http://localhost:8090/api/users');
        this.users = response.data; // 假设返回的数据是用户列表
        this.totalPages = Math.ceil(this.users.length / this.pageSize); // 计算总页数
      } catch (error) {
        console.error('获取用户时出错:', error);
      }
    },
	goToWelcome() {
	      this.$router.push('/');
	},
    async addUser() {
      try {
        const userData = {
          id: this.newId,
          username: this.newUsername,
          email: this.newEmail,
          password: this.newPassword,
        };

        const response = await axios.post('http://localhost:8090/api/users/register', userData);
        this.users.push(response.data);
        this.totalPages = Math.ceil(this.users.length / this.pageSize); // 更新总页数

        // 重置输入框
        this.newId = null;
        this.newUsername = '';
        this.newEmail = '';
        this.newPassword = '';
      } catch (error) {
        console.error('添加用户时出错:', error.response ? error.response.data : error.message);
      }
    },
    async findUser() {
      try {
        const response = await axios.get(`http://localhost:8090/api/users/search?username=${this.searchUsername}`);
        const foundUser = response.data;

        if (foundUser) {
          this.users = [foundUser];
          this.totalPages = 1; // 如果只找到一个用户，总页数设为 1
        } else {
          alert("未找到用户。");
        }
      } catch (error) {
        console.error('查找用户时出错:', error);
        alert("未找到用户。");
      }
    },
    startEdit(user) {
      this.editingUserId = user.id;
      this.editedUsername = user.username;
      this.editedEmail = user.email;
      this.editedPassword = user.password;
    },
    cancelEdit() {
      this.editingUserId = null;
      this.editedUsername = '';
      this.editedEmail = '';
      this.editedPassword = '';
    },
    async updateUser(userId) {
      try {
        const response = await axios.put(`http://localhost:8090/api/users/${userId}`, {
          username: this.editedUsername,
          email: this.editedEmail,
          password: this.editedPassword,
        });
        const updatedUser = response.data;
        this.users = this.users.map((user) =>
          user.id === updatedUser.id ? updatedUser : user
        );
        this.cancelEdit();
      } catch (error) {
        console.error('更新用户时出错:', error);
      }
    },
    async deleteUser(userId) {
      try {
        await axios.delete(`http://localhost:8090/api/users/${userId}`);
        this.users = this.users.filter((user) => user.id !== userId);
        this.totalPages = Math.ceil(this.users.length / this.pageSize); // 更新总页数
      } catch (error) {
        console.error('删除用户时出错:', error);
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages - 1) {
        this.currentPage++;
      }
    },
    previousPage() {
      if (this.currentPage > 0) {
        this.currentPage--;
      }
    },
  },
};
</script>


<style scoped>
body {
  background-image: url('~@/assets/magic_background.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  height: 100vh;
  margin: 0;
}

.system-title{
  /* 使用绝对定位，方便调整其在页面中的具体位置 */
  position: absolute; /* 使用绝对定位 */
  /* 设置距离顶部的距离 */
  top: 20px; /* 距离顶部的距离 */
  /* 设置距离右侧的距离 */
  right: 20px; /* 距离右侧的距离 */
  /* 宽度根据图片内容自动调整 */
  width: auto; /* 根据内容自动调整宽度 */
  /* 设置最大宽度，防止图片过大影响布局 */
  max-width: 300px; /* 最大宽度 */
}


.userlist-title{
  /* 设置顶部和底部的外边距，使图片在垂直方向上有一定间隔 */
  margin: 40px 0; /* 顶部和底部的外边距 */
  /* 设置宽度占满父容器，使其在水平方向上铺满 */
  width: 40%; /* 使标题宽度占满 */
  /* 文本在图片内居中显示 */
  text-align: center; /* 文本居中 */
}

.container {
 
  margin: 0 auto; /* 居中 */
  background-image: url('~@/assets/magic_background.jpg'); /* 添加背景图 */
  background-size: cover; /* 背景图覆盖 */
  background-position: center; /* 背景图居中 */

}

.input-field {
  border: 2px solid #8B008B; /* 边框颜色 */
  border-radius: 5px;
  padding: 10px;
  margin-bottom: 10px;
  transition: border-color 0.3s;
}

.input-field:focus {
  border-color: #FFD700; /* 聚焦时边框颜色 */
  outline: none; /* 去掉默认聚焦轮廓 */
}

.btn-primary {
  background-color: #003366; /* 藏青色 */
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn-primary:hover {
  background-color: #00509E; /* 悬停效果 */
}

.btn-secondary {
  background-color: #8B008B; /* 深紫色 */
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn-secondary:hover {
  background-color: #A020F0; /* 悬停效果 */
}

.user-card {
  background-color: #fff;
  border-radius: 10px;
  padding: 15px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}
.back-button {
  /* 添加样式以使按钮看起来更好 */
  margin: 10px;
  padding: 10px 20px;
  background-color: #003366;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.back-button:hover {
  background-color: #00509E;
}
.edit-input {
  border: 2px solid #8B008B; /* 边框颜色 */
  border-radius: 5px;
  padding: 5px;
  margin-right: 10px;
}

.btn-warning {
  background-color: #FFD700; /* 黄色 */
  color: #000;
  padding: 5px 10px;
  border: none;
  border-radius: 5px;
}

.btn-danger {
  background-color: #FF6347; /* 红色 */
  color: white;
  padding: 5px 10px;
  border: none;
  border-radius: 5px;
}
</style>