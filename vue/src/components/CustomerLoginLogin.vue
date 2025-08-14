<template>
  <div class="user-dashboard">
   
	<img src="@/assets/oldvip.png" class="oldvip-image" alt="Oldvip Text" /> 
	<h1>用户登录</h1>
	<h2>try 用户名 jack123 密码 jack1</h2>
    <input v-model="username" placeholder="用户名" required />
    <input type="password" v-model="password" placeholder="密码" required />
    <button @click="login">登录</button>
	<button @click="goToWelcome" class="back-button">返回主页面</button>

    <div v-if="userInfo">
      <h3>用户信息</h3>
	 
      <p>用户名: {{ userInfo.username }}</p>
      <p>积分: {{ userInfo.points }}</p>
      <p>会员等级: {{ membershipLevel }}</p>

      <h4>积分记录</h4>
      <ul>
        <li v-if="pointsLogs.length === 0">暂无积分记录</li>
        <li v-for="log in pointsLogs" :key="log.recordId">
          {{ log.createdTime }} - {{ log.point }} 积分
          <span v-if="log.action === 'earn'" class="earn"> (获取)</span>
          <span v-if="log.action === 'spend'" class="spend"> (消费)</span>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      username: '',
      password: '',
      userInfo: null,
      membershipLevel: '',
      pointsLogs: [],
    };
  },
  methods: {
	  goToWelcome() {
	        this.$router.push('/');
	      },
    async login() {
      try {
        const loginResponse = await axios.post('http://localhost:8090/api/users/auth/login', {
          username: this.username,
          password: this.password,
        });

        if (loginResponse.status === 200) {
          this.loadUserInfo();
        }
      } catch (error) {
        alert('登录失败：' + (error.response ? error.response.data : error.message));
      }
    },
    async loadUserInfo() {
      try {
        const userResponse = await axios.get(`http://localhost:8090/api/users/search?username=${this.username}`);
        this.userInfo = userResponse.data;

        const levelResponse = await axios.get(`http://localhost:8090/api/users/${this.userInfo.id}/level`);
        this.membershipLevel = levelResponse.data;

        const pointsResponse = await axios.get(`http://localhost:8090/api/users/${this.username}/points`);
        this.pointsLogs = pointsResponse.data.pointsLogs || [];
      } catch (error) {
        alert('加载用户信息失败：' + (error.response ? error.response.data : error.message));
      }
    },
  },
};
</script>

<style scoped>
.user-dashboard{
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
.oldvip-image {
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



h1 {
  color: #000000; 
 
}

h2, h3 {
  color: #8B008B; /* 深紫色字体 */
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
}

input {
  width: 80%;
  padding: 10px;
  margin: 10px 0;
  border: 2px solid #8B008B; /* 深紫色边框 */
  border-radius: 5px;
  transition: border-color 0.3s;
}

input:focus {
  border-color: #aaffff; /* 黄金色聚焦边框 */
  outline: none;
}

button {
  width: 80%;
  padding: 10px;
  background-color: #8B008B; /* 深紫色 */
  border: none;
  color: white;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s;
}

button:hover {
  background-color: #DA70D6; /* 悬停效果 */
  transform: scale(1.05); /* 按钮放大效果 */
}

.earn {
  color: green;
}

.spend {
  color: red;
}
</style>