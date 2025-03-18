<script setup>
import { ref, onMounted } from 'vue'
import { postApi } from '@/api/post'

const tableData = ref([])
const total = ref(0)
const currentPage = ref(1)
const pageSize = ref(10)

const loadData = async () => {
  try {
    const res = await postApi.getPosts({
      page: currentPage.value,
      pageSize: pageSize.value
    })
    tableData.value = res.data.rows
    total.value = res.data.total
  } catch (error) {
    console.error('加载数据失败:', error)
  }
}

const handleSizeChange = (newSize) => {
  pageSize.value = newSize
  currentPage.value = 1
  loadData()
  localStorage.setItem('pagination', JSON.stringify({
    currentPage: currentPage.value,
    pageSize: pageSize.value
  }))
}

const handleCurrentChange = (val) => {
  currentPage.value = val
  loadData()
}

onMounted(() => {
  const saved = localStorage.getItem('pagination')
  if (saved) {
    const { currentPage: savedPage, pageSize: savedSize } = JSON.parse(saved)
    currentPage.value = savedPage
    pageSize.value = savedSize
  }
  loadData()
})
</script>

<template>
  <div class="container">
    <div 
      v-for="row in tableData" 
      :key="row.postId"
      class="card-item cursor-pointer"
      @click="$router.push({ name: 'postDetail', params: { postId: row.postId } })"
    >
      <el-link 
        type="primary" 
        class="title"
      >
        {{ row.title }}
      </el-link>
      
      <div class="meta">
        <span>作者：{{ row.createUsername }}</span>
        <span>时间：{{ new Date(row.createTime).toLocaleDateString() }}</span>
        <!-- <span>👍 {{ row.likeCount }}</span> -->
      </div>
      
      <div class="content-preview">
        {{ row.content?.slice(0, 50) }}{{ row.content?.length > 50 ? '...' : '' }}
      </div>
    </div>

    <el-pagination
      :current-page="currentPage"
      :page-size="pageSize"
      :total="total"
      layout="total, sizes, prev, pager, next, jumper"
      @current-change="handleCurrentChange"
      @size-change="handleSizeChange"
    />
  </div>
</template>

<style scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.card-item {
  background: #fff;
  border: 1px solid #ebeef5;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 16px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
  border: 1px solid transparent;
  transition: all 0.3s ease;
}

.card-item:hover {
  box-shadow: 0 0 0 1px #409eff;
  border-color: rgba(64, 158, 255, 0.1);
}

.card-item .el-link {
  font-size: 18px;
  margin-bottom: 12px;
  display: block;
}

.meta {
  color: #909399;
  font-size: 13px;
  margin-bottom: 12px;
}

.meta span {
  margin-right: 15px;
}

.content-preview {
  color: #606266;
  line-height: 1.6;
  font-size: 14px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.el-pagination {
  margin-top: 30px;
}
</style>
.card-item .title:hover {
}