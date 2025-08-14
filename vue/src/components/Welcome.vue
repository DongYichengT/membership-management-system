<template>
  <!-- 整个欢迎页面的根元素 -->
  <div class="welcome-page">
    <!-- 展示欢迎文字的图片，通过src属性引入项目中的资源，class用于样式设置，alt为图片无法显示时的替代文本 -->
    <img src="@/assets/welcome_text.png" class="welcome-image" alt="Welcome Text" />
    <!-- 展示系统标题的图片，同样设置了相应属性 -->
    <img src="@/assets/magic_title.png" class="system-title" alt="System Title" />
    <!-- 页面中的标题元素，这里显示了一些相关作业信息 -->
    <h1 class="magic-title">软国2202 董一澄 20222251050 期末大作业</h1>
    <!-- 另一个标题元素，显示时间信息 -->
    <h1 class="magic-title">2024.12</h1>
    <!-- 包含按钮的容器，用于布局按钮 -->
    <div class="button-container">
      <!-- 管理员登录按钮，点击时触发goToAdminLogin方法，按钮内包含一个字体图标表示管理员身份 -->
      <button @click="goToAdminLogin" class="magic-button">
        <i class="fas fa-user-shield"></i> 管理员专属通道
      </button>
      <!-- 顾客登录按钮，点击时触发goToCustomerLogin方法，按钮内包含表示顾客的字体图标 -->
      <button @click="goToCustomerLogin" class="magic-button">
        <i class="fas fa-user"></i> 顾客奇趣之门
      </button>
    </div>
    <!-- 页面中的描述性文字段落，用于向用户介绍相关信息 -->
    <p class="description">
      欢迎踏入“星耀奇物阁”——这片魔法与奇幻交织的梦幻之地，一家蕴藏着无尽奥秘与奇迹的魔法物品殿堂。在这里，每一件商品都蕴含着古老魔法的力量，从令人遁形无踪的隐身披风，到能满足您三个至深愿望的魔法灯，再到让您与林间生灵亲密对话的魔法项链……每一件都是对想象力与奇迹的颂歌。
      <br/><br/>
      为了进一步提升您的探索之旅，我们精心打造了一套充满魔力的会员管理系统，旨在为您开启一段更加个性化、尊享的购物奇遇。
      <br/><br/>
      【管理员专属通道】
      尊敬的守护者，您手握解锁奇迹的钥匙——“dyc20222251050”，这是通往管理奇境的神秘密码。通过此通道，您将拥有无上的权力，轻松实现对每一位探索者信息的精心编排与守护，无论是增添新星的光芒，还是修正过往的轨迹，一切尽在您的掌握之中。
      <br/><br/>
      【顾客奇趣之门】
      勇敢的探险家们，无论是久经沙场的会员，还是初来乍到的新朋友，这里都是您启程的港湾。通过我们的顾客入口，您可以轻松登录，探索属于自己的积分宝藏，翻阅每一次与奇迹相遇的记录。而对于那些即将揭开“星耀奇物阁”神秘面纱的新旅人，一扇欢迎注册的大门正为您缓缓开启，等待着您踏入这奇幻旅程的第一步。
      <br/><br/>
      让我们携手，在这片充满无限可能的魔法世界中，共同编织一段段关于勇气、智慧与奇迹的故事。欢迎来到“星耀奇物阁”会员管理系统，您的每一次点击，都将开启一段新的奇妙旅程！
    </p>
  </div>
</template>

<script>
export default {
  methods: {
    // 定义管理员登录的方法，点击管理员登录按钮时，使用Vue Router将页面导航到/admin-login路径
    goToAdminLogin() {
      this.$router.push("/admin-login");
    },
    // 定义顾客登录的方法，点击顾客登录按钮时，使用Vue Router将页面导航到/customer-login路径
    goToCustomerLogin() {
      this.$router.push("/customer-login");
    }
  }
};
</script>

<style scoped>
/* 欢迎页面的整体样式设置 */
.welcome-page {
  /* 文本内容在容器内居中显示 */
  text-align: center;
  /* 设置背景图片，引用项目中的资源文件，通过相对路径指向assets目录下的图片 */
  background-image: url('../assets/magic_background.jpg');
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
  /* 容器内的内边距，增加内容与容器边缘的距离 */
  padding: 50px;
  /* 设置字体颜色为金色，营造魔法奇幻风格 */
  color: #FFD700; /* 字体颜色 */
}

/* 系统标题图片的样式设置 */
.system-title{
  /* 设置顶部和底部的外边距，使图片在垂直方向上有一定间隔 */
  margin: 40px 0; /* 顶部和底部的外边距 */
  /* 设置宽度占满父容器，使其在水平方向上铺满 */
  width: 100%; /* 使标题宽度占满 */
  /* 文本在图片内居中显示 */
  text-align: center; /* 文本居中 */
}

/* 欢迎文字图片的样式设置 */
.welcome-image {
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

/* 标题元素（h1标签）的样式设置 */
.magic-title {
  /* 调整字体大小 */
  font-size: 24px; /* 调整为小字号 */
  /* 设置字体颜色 */
  color: #bf7f00; 
  /* 添加文字阴影，增加立体感 */
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
  /* 设置底部外边距，增加与下方元素的间隔 */
  margin-bottom: 40px;
}

/* 按钮容器的样式设置，用于布局按钮 */
.button-container {
  /* 使用flex布局，使其内部按钮在垂直方向排列 */
  display: flex;
  flex-direction: column;
  /* 内部按钮在水平方向上居中对齐 */
  align-items: center;
}

/* 按钮的样式设置 */
.magic-button {
  /* 设置按钮上下的外边距 */
  margin: 15px;
  /* 设置按钮内边距，调整按钮内部文本与边框的距离 */
  padding: 15px 30px;
  /* 设置按钮上文本的字体大小 */
  font-size: 20px;
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
  /* 设置按钮在鼠标悬停等交互时的过渡效果，使变化更平滑 */
  transition: all 0.3s ease;
}

/* 描述性文字段落（p标签）的样式设置 */
.description {
  /* 设置文字颜色为白色 */
  color: white; /* 文字颜色 */
  /* 调整字体大小 */
  font-size: 14px; /* 小字号 */
  /* 设置与上方按钮的间距 */
  margin-top: 20px; /* 与按钮的间距 */
  /* 限制文字段落的最大宽度，避免内容过宽影响阅读体验 */
  max-width: 800px; /* 限制文字宽度 */
  /* 设置行间距，使文字排版更清晰易读 */
  line-height: 1.5; /* 行间距 */
}
</style>