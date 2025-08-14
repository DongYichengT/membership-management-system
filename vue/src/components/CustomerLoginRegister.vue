<template>
  <div class="register">
	<img src="@/assets/newvip.png" class="newvip-image" alt="Newvip Text" /> 
    <h1>用户注册</h1>
    <form @submit.prevent="addUser" class="mb-4">
      <input v-model="newId" placeholder="ID" type="number" class="magic-input" required />
      <input v-model="newUsername" placeholder="用户名" class="magic-input" required />
      <input v-model="newEmail" placeholder="邮箱" class="magic-input" required />
      <input v-model="newPassword" placeholder="密码" type="password" class="magic-input" required />
      <button type="submit" class="magic-button">注册</button>
	  <button @click="goToWelcome" class="back-button">返回主页面</button>
    </form>

    <div v-if="message" class="mt-4 text-green-500 text-center">{{ message }}</div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      newId: null,
      newUsername: '',
      newEmail: '',
      newPassword: '',
      message: '',
    };
  },
  methods: {
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
        
        this.message = '注册成功！';

        this.newId = null;
        this.newUsername = '';
        this.newEmail = '';
        this.newPassword = '';
      } catch (error) {
        this.message = '注册失败：' + (error.response ? error.response.data : error.message);
      }
    },
  },
};
</script>

<style scoped>
.newvip-image {
  /* 使用绝对定位，方便精确调整图片在页面中的位置，使其不受文档流常规布局的影响 */
  position: absolute; /* 使用绝对定位 */
  /* 设置图片距离页面顶部的距离为20px，确定其垂直方向上的位置 */
  top: 10px; /* 距离顶部的距离 */
  /* 设置图片距离页面右侧的距离为20px，确定其水平方向上的位置 */
  right: 10px; /* 距离右侧的距离 */
  /* 让图片宽度根据自身内容自动调整大小，保持图片原始比例显示 */
  width: auto; /* 根据内容自动调整宽度 */
  /* 设置图片的最大宽度为600px，防止图片因尺寸过大而破坏页面整体布局效果 */
  max-width: 400px; /* 最大宽度 */
}
.register{
	/* 设置背景图片，引用项目中的资源文件，通过相对路径指向assets目录下的图片 */
	background-image: url('../assets/cgb.jpg'); /* 背景图 */
	/* 背景图片尺寸设置为覆盖整个容器，保证铺满页面 */
	background-size: cover;
	/* 背景图片位置居中显示 */
	background-position: center;
	/* 设置最小高度为视口高度，确保页面占满整个屏幕高度 */
	min-height: 100vh;
	/* 使用flex布局，使其内容在垂直方向排列 */
	display: flex;
	flex-direction: column;
	/* 垂直方向上的内容居中对齐 */
	justify-content: center;
	/* 水平方向上的内容居中对齐 */
	align-items: center;
}


h1 {
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
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
.magic-input {
  width: 100%;
  padding: 10px;
  margin: 10px 0;
  border: 2px solid #8B008B; /* 深紫色边框 */
  border-radius: 5px;
  transition: border-color 0.3s;
  background: #f9f9f9; /* 输入框背景 */
}

.magic-input:focus {
  border-color: #aaffff; /* 黄金色聚焦边框 */
  outline: none;
}

.magic-button {
  width: 100%;
  padding: 10px;
  background-color: #8B008B; /* 深紫色 */
  border: none;
  color: white;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s;
}

.magic-button:hover {
  background-color: #DA70D6; /* 悬停效果 */
  transform: scale(1.05); /* 按钮放大效果 */
}
</style>