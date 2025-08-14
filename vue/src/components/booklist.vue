<template>
  <div>
    <h1>图书管理</h1>
    
    <input v-model="searchTitle" placeholder="搜索书名" />
    <button @click="findBook">搜索</button>
    
    <table>
      <thead>
        <tr>
          <th>书名</th>
          <th>作者</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="book in paginatedBooks" :key="book.id">
          <td>{{ book.title }}</td>
          <td>{{ book.author }}</td>
          <td>
            <button @click="startEdit(book)">编辑</button>
            <button @click="deleteBook(book.id)">删除</button>
          </td>
        </tr>
      </tbody>
    </table>

    <button @click="previousPage" :disabled="currentPage === 0">上一页</button>
    <button @click="nextPage" :disabled="currentPage >= totalPages - 1">下一页</button>

    <h2>添加新书</h2>
    <input v-model="newTitle" placeholder="书名" />
    <input v-model="newAuthor" placeholder="作者" />
    <button @click="addBook">添加</button>

    <div v-if="editingBookId">
      <h2>编辑书籍</h2>
      <input v-model="editedTitle" placeholder="书名" />
      <input v-model="editedAuthor" placeholder="作者" />
      <button @click="updateBook(editingBookId)">保存</button>
      <button @click="cancelEdit">取消</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      books: [],
      currentPage: 0,
      pageSize: 5,
      totalPages: 0,
      newTitle: '',
      newAuthor: '',
      editingBookId: null,
      editedTitle: '',
      editedAuthor: '',
      searchTitle: '',
    };
  },
  computed: {
    paginatedBooks() {
      const start = this.currentPage * this.pageSize;
      return this.books.slice(start, start + this.pageSize);
    },
  },
  methods: {
    async fetchBooks() {
      try {
        const response = await axios.get('http://localhost:8084/api/books');
        this.books = response.data;
        this.totalPages = Math.ceil(this.books.length / this.pageSize);
      } catch (error) {
        console.error('获取书籍时出错:', error);
      }
    },
    async addBook() {
      try {
        const bookData = {
          title: this.newTitle,
          author: this.newAuthor,
        };
        const response = await axios.post('http://localhost:8084/api/books', bookData);
        this.books.push(response.data);
        this.totalPages = Math.ceil(this.books.length / this.pageSize);
        
        this.newTitle = '';
        this.newAuthor = '';
      } catch (error) {
        console.error('添加书籍时出错:', error.response ? error.response.data : error.message);
      }
    },
    async findBook() {
      try {
        const response = await axios.get(`http://localhost:8084/api/books?title=${this.searchTitle}`);
        this.books = response.data;
        this.totalPages = 1; // 如果只找到一个书籍，总页数设为 1
      } catch (error) {
        console.error('查找书籍时出错:', error);
      }
    },
    startEdit(book) {
      this.editingBookId = book.id;
      this.editedTitle = book.title;
      this.editedAuthor = book.author;
    },
    cancelEdit() {
      this.editingBookId = null;
      this.editedTitle = '';
      this.editedAuthor = '';
    },
    async updateBook(bookId) {
      try {
        const response = await axios.put(`http://localhost:8084/api/books/${bookId}`, {
          title: this.editedTitle,
          author: this.editedAuthor,
        });
        const updatedBook = response.data;
        this.books = this.books.map(book =>
          book.id === updatedBook.id ? updatedBook : book
        );
        this.cancelEdit();
      } catch (error) {
        console.error('更新书籍时出错:', error);
      }
    },
    async deleteBook(bookId) {
      try {
        await axios.delete(`http://localhost:8084/api/books/${bookId}`);
        this.books = this.books.filter(book => book.id !== bookId);
        this.totalPages = Math.ceil(this.books.length / this.pageSize);
      } catch (error) {
        console.error('删除书籍时出错:', error);
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
  mounted() {
    this.fetchBooks();
  },
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
}
th {
  background-color: #f2f2f2;
}
</style>