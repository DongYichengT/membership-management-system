<template>
  <!-- 管理员登录页面的根元素，使用类名来应用相应的样式 -->
  <div class="admin-login-page">
    <!-- 展示系统标题的图片，通过src属性引入项目中的资源，class用于样式设置，alt为图片无法显示时的替代文本 -->
    <img src="@/assets/magic_title.png" class="system-title" alt="System Title" />
    <!-- 页面中二级标题元素，用于提示这是管理员专属通道 -->
    <h2>管理员专属通道</h2>
    <!-- 密码输入框，设置类型为密码，使用v-model指令双向绑定数据，将输入的值与组件实例中的password属性关联起来，placeholder属性用于显示提示文本 -->
    <input
      type="password"
      v-model="password"
      placeholder="请输入密码"
    />
    <!-- 登录按钮，绑定了click事件，点击时会触发login方法 -->
    <button @click="login">登录</button>
    <!-- 根据条件v-if判断是否显示错误信息，当errorMessage有值时，显示包含错误信息的div元素，class为error用于应用相应的样式 -->
    <div v-if="errorMessage" class="error">{{ errorMessage }}</div>
    <!-- 提示管理员登录密码的元素，这里直接显示了密码内容（实际应用中可能需要更安全的处理方式，此处仅为示例展示） -->
    <h4>密码：dyc20222251050</h4>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router"; 

export default {
  data() {
    return {
      // 用于绑定密码输入框输入的值，初始为空字符串
      password: "",
      // 用于存储登录时可能出现的错误信息，初始为空字符串
      errorMessage: "",
    };
  },
  methods: {
    async login() {
      // 判断输入的密码是否与预设的管理员密码（dyc20222251050）一致
      if (this.password === "dyc20222251050") {
        try {
          // 若密码正确，将密码输入框的值清空，准备下一次登录操作
          this.password = ""; 
          // 同时清空可能存在的错误信息
          this.errorMessage = ""; 
          // 使用Vue Router进行页面导航，跳转到/user-list路径对应的页面（假设该路径已在路由配置中定义好）
          router.push("/user-list"); 
        } catch (error) {
          // 如果在登录过程中（比如路由跳转出现异常等情况）出现错误，将错误信息打印到控制台，方便调试排查问题
          console.error("Error during login:", error);
        }
      } else {
        // 如果输入的密码与预设密码不一致，设置错误信息提示用户密码错误，需要重新输入
        this.errorMessage = "密码错误，请重新输入";
      }
    }
  }
};
</script>

<style scoped>
/* 系统标题图片的样式设置 */
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
  max-width: 600px; /* 最大宽度 */
}

/* 管理员登录页面整体的样式设置 */
.admin-login-page {
  /* 文本内容在容器内居中显示 */
  text-align: center;
  /* 设置背景图片，引用项目中的资源文件，通过相对路径指向assets目录下的图片 */
  background-image: url('../assets/magic_background.jpg'); /* 背景图 */
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
  /* 设置字体颜色为金色，营造魔法奇幻风格 */
  color: #FFD700; /* 字体颜色 */
  /* 容器内的内边距，增加内容与容器边缘的距离 */
  padding: 20px;
  /* 设置容器的边框圆角效果，使页面看起来更圆润美观 */
  border-radius: 15px;
  /* 给容器添加阴影效果，增加立体感和层次感 */
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.5);
}

/* 二级标题（h2标签）的样式设置 */
h2 {
  /* 设置字体大小 */
  font-size: 36px;
  /* 设置底部外边距，增加与下方元素的间隔 */
  margin-bottom: 20px;
  /* 添加文字阴影，增加立体感 */
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
}

/* 输入框（input标签）的样式设置 */
input {
  /* 设置上下外边距 */
  margin: 10px;
  /* 设置内边距，调整输入框内部文本与边框的距离 */
  padding: 10px;
  /* 设置字体大小 */
  font-size: 16px;
  /* 设置边框样式，包括边框宽度、颜色等，这里边框颜色为金色 */
  border: 2px solid #FFD700; /* 边框颜色 */
  /* 设置边框的圆角效果 */
  border-radius: 5px;
  /* 设置输入框的背景颜色为半透明的白色，增加页面整体的层次感和美观度 */
  background-color: rgba(255, 255, 255, 0.8); /* 半透明背景 */
  /* 设置输入框内文字的颜色 */
  color: #333; /* 输入框字体颜色 */
  /* 设置当输入框获得焦点时（比如用户点击输入框准备输入内容）边框颜色变化的过渡效果，过渡时间为0.3秒 */
  transition: border-color 0.3s;
}

/* 当输入框获得焦点时的样式变化，通过:focus伪类选择器设置 */
input:focus {
  /* 改变边框颜色，这里设置为紫色，用于突出显示当前正在操作的输入框 */
  border-color: #8B008B; /* 聚焦时的边框颜色 */
  /* 去掉浏览器默认的聚焦轮廓，使页面显示更符合自定义的样式风格 */
  outline: none; /* 去掉默认聚焦轮廓 */
}

/* 按钮（button标签）的样式设置 */
button {
  /* 设置按钮内边距 */
  padding: 10px 20px;
  /* 设置按钮上文本的字体大小 */
  font-size: 16px;
  /* 设置按钮的背景颜色为藏青色 */
  background-color: #003366; /* 藏青色 */
  /* 设置按钮文本颜色为白色 */
  color: white;
  /* 去除按钮边框 */
  border: none;
  /* 设置按钮边框的圆角效果，使按钮更美观 */
  border-radius: 10px;
  /* 给按钮添加阴影效果，增加立体感和层次感 */
  box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.4);
  /* 设置鼠标指针样式为手型，表示可点击 */
  cursor: pointer;
  /* 设置按钮在鼠标悬停等交互时的过渡效果，这里包括背景颜色变化和按钮缩放变化，过渡时间分别为0.3秒和0.2秒 */
  transition: background-color 0.3s, transform 0.2s;
}

/* 按钮在鼠标悬停时的样式变化 */
button:hover {
  /* 改变按钮的背景颜色，加深颜色，用于提示用户鼠标悬停在按钮上 */
  background-color: #00509E; /* 悬停效果 */
  /* 对按钮进行放大操作，使其在悬停时有一定的视觉反馈，增强交互感，这里放大比例为1.05倍 */
  transform: scale(1.05); /* 按钮放大效果 */
}

/* 错误信息显示元素（class为error的div元素）的样式设置 */
.error {
  /* 设置文字颜色为红色，用于突出显示错误信息 */
  color: red;
  /* 设置顶部外边距，与上方元素间隔开一定距离 */
  margin-top: 10px;
  /* 设置字体大小 */
  font-size: 14px;
  /* 添加文字阴影，增加立体感 */
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
}
</style>