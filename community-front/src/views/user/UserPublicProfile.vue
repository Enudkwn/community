<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { userApi } from '@/api/user'
import { postApi } from '@/api/post'

const route = useRoute()
const userId = ref(route.params.userId)
const userInfo = ref({})
const posts = ref([])

const currentPage = ref(1)
const pageSize = ref(Number(localStorage.getItem('userPageSize')) || 10)
const totalPosts = ref(0)

const getUserInfo = async () => {
  if (!userId.value) {
    console.error('用户ID参数无效')
    ElMessage.error('用户信息加载失败')
    return
  }
  try {
    const res = await userApi.selectByUserId(userId.value)
    userInfo.value = res.data
  } catch (error) {
    console.error('获取用户信息失败')
    ElMessage.error('用户信息加载失败')
  }
}

const getPosts = async () => {
  try {
    const res = await postApi.getByUserId({
      userId: userId.value,
      page: currentPage.value,
      pageSize: pageSize.value
    })
    posts.value = res.data.rows
    totalPosts.value = res.data.total
  } catch (error) {
    console.error('加载文章失败')
  }
}

const handlePageChange = (newPage) => {
  currentPage.value = newPage
  getPosts()
}

const handleSizeChange = (newSize) => {
  pageSize.value = newSize
  localStorage.setItem('userPageSize', newSize)
  currentPage.value = 1
  getPosts()
}

onMounted(() => {
  console.log('路由参数：', route.params)
  getUserInfo()
  getPosts()
})
</script>

<template>
  <div class="profile-container">
    <div class="user-info">
      <h2>{{ userInfo.username || '匿名用户' }}</h2>
      <p class="intro">{{ userInfo.intro || '该用户暂未填写简介' }}</p>
      <div class="stats">
        <span>注册时间：{{ userInfo.createTime ? new Date(userInfo.createTime).toLocaleDateString() : '未知时间' }}</span>
        <span v-if="userInfo.email" class="email">邮箱：{{ userInfo.email }}</span>
      </div>
    </div>

    <div class="posts-section">
      <h3>发表的文章（{{ totalPosts }}篇）</h3>
      <div 
        v-for="post in posts" 
        :key="post.postId"
        class="post-item"
        @click="$router.push({ name: 'postDetail', params: { postId: post.postId } })"
      >
        <h4>{{ post.title }}</h4>
        <p class="post-excerpt">{{ post.content.substring(0, 100) }}...</p>
        <div class="post-meta">
          {{ new Date(post.createTime).toLocaleDateString() }}
        </div>
      </div>

      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :total="totalPosts"
        layout="prev, pager, next, sizes"
        :page-sizes="[5, 10, 20]"
        @current-change="handlePageChange"
        @size-change="handleSizeChange"
        class="pagination-container"
      />
    </div>
  </div>
</template>

<style scoped>
.profile-container {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
}
.user-info {
  padding: 20px;
  background: #f5f7fa;
  border-radius: 4px;
  margin-bottom: 30px;
}
.intro {
  color: #666;
  margin: 15px 0;
}
.stats {
  color: #909399;
  font-size: 14px;
}
.posts-section {
  border-top: 1px solid #ebeef5;
  padding-top: 20px;
}
.post-item {
  padding: 15px;
  margin: 10px 0;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  cursor: pointer;
  transition: box-shadow 0.3s;
}
.post-item:hover {
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);
}
.post-title {
  font-size: 16px;
  color: #303133;
}
.post-meta {
  color: #909399;
  font-size: 14px;
  margin-top: 8px;
}
</style>