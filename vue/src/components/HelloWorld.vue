<template>
  <div class="container mx-auto my-8">
    <h1 class="text-3xl font-bold mb-4">Book List</h1>
    <h1 class="text-3xl font-bold mb-4">软国2202 付轩宇</h1>

    <form @submit.prevent="addBook" class="mb-4">
      <input v-model="newTitle" placeholder="Title" class="rounded-md border border-gray-300 px-4 py-2 mr-2" required />
      <input v-model="newAuthor" placeholder="Author" class="rounded-md border border-gray-300 px-4 py-2 mr-2" required />
      <button type="submit" class="bg-blue-500 text-white rounded-md px-4 py-2">Add</button>
    </form>

    <!-- 查找图书功能 -->
    <div class="mb-4">
      <input v-model="searchId" placeholder="Search by ID" type="number" class="rounded-md border border-gray-300 px-4 py-2 mr-2" />
      <button @click="findBook" class="bg-green-500 text-white rounded-md px-4 py-2">Find Book</button>
    </div>

    <ul class="space-y-2">
      <li v-for="book in books" :key="book.id" class="bg-white rounded-md shadow-md p-4 flex justify-between items-center">
        <template v-if="editingBookId === book.id">
          <input v-model="editedTitle" class="border border-gray-300 rounded-md px-2 py-1" />
          <input v-model="editedAuthor" class="border border-gray-300 rounded-md px-2 py-1" />
          <button @click="updateBook(book.id)" class="bg-green-500 text-white rounded-md px-2 py-1">Save</button>
          <button @click="cancelEdit" class="bg-gray-500 text-white rounded-md px-2 py-1">Cancel</button>
        </template>
        <template v-else>
          <span>{{ book.id }}: {{ book.title }} ({{ book.author }})</span>
          <div class="flex space-x-2">
            <button @click="startEdit(book)" class="bg-yellow-500 text-white rounded-md px-2 py-1">Edit</button>
            <button @click="deleteBook(book.id)" class="bg-red-500 text-white rounded-md px-2 py-1">Delete</button>
          </div>
        </template>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      books: [], // 存储图书列表
      newTitle: '', // 新图书标题
      newAuthor: '', // 新图书作者
      searchId: '', // 查找图书ID
      editingBookId: null, // 当前编辑的图书ID
      editedTitle: '', // 编辑时的图书标题
      editedAuthor: '', // 编辑时的图书作者
    };
  },
  mounted() {
    this.fetchBooks(); // 组件加载时获取图书
  },
  methods: {
    async fetchBooks() {
      try {
        const response = await axios.get('http://localhost:8087/api/books');
        this.books = response.data; // 将获取的图书数据存储到 books 数组
      } catch (error) {
        console.error('Error fetching books:', error);
      }
    },
    async addBook() {
      try {
        const bookData = {
          title: this.newTitle,
          author: this.newAuthor,
        };

        const response = await axios.post('http://localhost:8087/api/books', bookData);
        
        this.books.push(response.data); // 将新图书添加到图书列表

        // 清空输入框
        this.newTitle = '';
        this.newAuthor = '';
      } catch (error) {
        console.error('Error adding book:', error.response ? error.response.data : error.message);
      }
    },
    async findBook() {
      try {
        const response = await axios.get(`http://localhost:8087/api/books/${this.searchId}`); 
        const foundBook = response.data;

        // 如果找到图书，将其添加到图书列表中
        if (foundBook) {
          this.books = [foundBook]; // 仅显示找到的图书
        } else {
          alert("Book not found.");
        }
      } catch (error) {
        console.error('Error finding book:', error);
        alert("Book not found.");
      }
    },
    startEdit(book) {
      this.editingBookId = book.id; // 设置正在编辑的图书ID
      this.editedTitle = book.title; // 加载图书信息
      this.editedAuthor = book.author; // 加载图书信息
    },
    cancelEdit() {
      this.editingBookId = null; // 取消编辑状态
      this.editedTitle = ''; // 清空编辑输入框
      this.editedAuthor = ''; // 清空编辑输入框
    },
    async updateBook(bookId) {
      try {
        const response = await axios.put(`http://localhost:8087/api/books/${bookId}`, {
          title: this.editedTitle,
          author: this.editedAuthor,
        });
        const updatedBook = response.data; // 获取更新后的图书数据
        this.books = this.books.map((book) =>
          book.id === updatedBook.id ? updatedBook : book // 更新图书列表
        );
        this.cancelEdit(); // 取消编辑状态
      } catch (error) {
        console.error('Error updating book:', error);
      }
    },
    async deleteBook(bookId) {
      try {
        await axios.delete(`http://localhost:8087/api/books/${bookId}`); // 删除图书
        this.books = this.books.filter((book) => book.id !== bookId); // 更新图书列表
      } catch (error) {
        console.error('Error deleting book:', error);
      }
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: 0 auto;
}
</style>