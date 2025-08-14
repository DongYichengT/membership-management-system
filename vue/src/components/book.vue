<template>
  <div>
    <h1>图书列表1111</h1>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>书名</th>
          <th>作者</th>
          <th>ISBN</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="book in books" :key="book.id">
          <td>{{ book.id }}</td>
          <td>{{ book.title }}</td>
          <td>{{ book.author }}</td>
          <td>{{ book.isbn }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      books: []
    };
  },
  mounted() {
    // 调用获取图书列表的方法
    this.fetchBooks();
  },
  methods: {
    async fetchBooks() {
      try {
        const response = await fetch('http://localhost:8091/api/books');
        const data = await response.json();
        this.books = data;
      } catch (error) {
        console.error('Error fetching books:', error);
      }
    }
  }
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th,
td {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: left;
}

th {
  background-color: #007BFF;
  color: white;
}
</style>