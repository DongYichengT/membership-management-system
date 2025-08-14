<template>
  <div class="container">
    <h1 class="title">商品列表</h1>

    <div class="search-container">
      <div class="search-field">
        <label>根据积分数额查找:</label>
        <input type="number" v-model="searchPoints" placeholder="输入积分数额" />
        <button class="search-button" @click="searchByPoints">搜索</button>
      </div>
   
    </div>

    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>名称</th>
          <th>兑换积分</th>
          <th>最低等级</th>
          <th>操作</th>
		  <button @click="goToWelcome" class="back-button">返回主页面</button>
        </tr>
      </thead>
      <tbody>
        <tr v-for="product in products" :key="product.id">
          <td>{{ product.id }}</td>
          <td>{{ product.name }}</td>
          <td>{{ product.redeemPoints }}</td>
          <td>{{ product.minimumLevel }}</td>
          <td>
			  
            <button class="edit-button" @click="editProduct(product)">编辑</button>
            <button class="delete-button" @click="deleteProduct(product.id)">删除</button>
          </td>
        </tr>
      </tbody>
    </table>

    <h2 class="form-title">{{ editingProduct ? '编辑商品' : '新增商品' }}</h2>
    <form class="product-form" @submit.prevent="submitForm">
      <input type="hidden" v-model="editingProduct.id" />
      <div>
        <label>名称:</label>
        <input type="text" v-model="editingProduct.name" required />
      </div>
      <div>
        <label>兑换积分:</label>
        <input type="number" v-model="editingProduct.redeemPoints" required />
      </div>
      <div>
        <label>最低等级:</label>
        <input type="text" v-model="editingProduct.minimumLevel" required />
      </div>
	  	  
      <button class="submit-button" type="submit">{{ editingProduct ? '更新' : '新增' }}</button>
      <button class="cancel-button" type="button" @click="clearForm">取消</button>
    </form>
	
  </div>
</template>

<script>
export default {
  data() {
    return {
      products: [],
      editingProduct: {
        id: null,
        name: '',
        redeemPoints: null,
        minimumLevel: '',currentPage: 0, // 当前页
      pageSize: 5, // 每页显示的商品数量
      totalPages: 0, // 总页数
      },
      searchPoints: '',
      searchName: ''
    };
  },
  computed: {
    paginatedProducts() {
      const start = this.currentPage * this.pageSize;
      return this.users.slice(start, start + this.pageSize); // 返回当前页的用户
    }
  },
  mounted() {
    this.fetchProducts();
  },
  created() {
    this.fetchProducts();
  },
  methods: {
   async searchByPoints() {
   
   const response = await fetch(`http://localhost:8090/api/products/redeemPoints/${this.searchPoints}`);
   
   this.products = await response.json();
   
   },
    async searchByName() {
      const response = await fetch(`http://localhost:8090/api/products?name=${this.searchName}`);
      this.products = await response.json();
    },
    async fetchProducts() {
      const response = await fetch('http://localhost:8090/api/products');
      this.products = await response.json();
    },
    async deleteProduct(id) {
      await fetch(`http://localhost:8090/api/products/${id}`, {
        method: 'DELETE'
      });
      this.fetchProducts();
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
    editProduct(product) {
      this.editingProduct = { ...product };
    },
	goToWelcome() {
	      this.$router.push('/');
	},
    async submitForm() {
      const method = this.editingProduct.id ? 'PUT' : 'POST';
      const url = this.editingProduct.id 
        ? `http://localhost:8090/api/products/${this.editingProduct.id}` 
        : 'http://localhost:8090/api/products';
      
      await fetch(url, {
        method,
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.editingProduct)
      });

      this.clearForm();
      this.fetchProducts();
    },
    clearForm() {
      this.editingProduct = { id: null, name: '', redeemPoints: null, minimumLevel: '' };
    }
  },
  
};
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
  color: #ffffff; /* 字体颜色设置为白色 */
  text-align: center;
  /* 设置背景图片，引用项目中的资源文件，通过相对路径指向assets目录下的图片 */
  background-image: url('../assets/shop.png');
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

.title {
  text-align: center;
  margin-bottom: 20px;
}

.search-container {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.search-field {
  flex-grow: 1;
  margin-right: 10px;
}

.search-field:last-child {
  margin-right: 0;
}

.search-button, .submit-button, .edit-button, .delete-button, .cancel-button {
  padding: 10px 15px;
  background-color: #baaf7a; /* 按钮背景色 */
  color: #000000; /* 按钮文本颜色设置为黑色 */
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.search-button:hover, .submit-button:hover, .edit-button:hover, .delete-button:hover, .cancel-button:hover {
  background-color: #2b5782;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
  color: #ffffff; /* 表格文本颜色设置为白色 */
}

th {
  background-color: rgba(255, 255, 255, 0.2); /* 表头背景色稍微透明 */
}

.form-title {
  margin-top: 20px;
}

.product-form {
  display: flex;
  flex-direction: column;
}

.product-form div {
  margin-bottom: 10px;
}

.product-form label {
  margin-bottom: 5px;
  color: #ffffff; /* 标签颜色设置为白色 */
}

.product-form input {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}
</style>