<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { userApi } from '@/api/user'
import { postApi } from '@/api/post'
import { ElMessage, ElMessageBox } from 'element-plus'

const router = useRouter()

const route = useRoute()
const userId = ref(route.params.userId)
const userInfo = ref({})
const posts = ref([])

const dialogVisible = ref(false)
const form = ref({
  intro: ''
})

// 获取用户信息
const getUserInfo = async () => {
  try {
    const res = await userApi.selectByUserId(userId.value)
    userInfo.value = res.data
  } catch (error) {
    ElMessage.error('获取用户信息失败')
  }
}

const currentPage = ref(1)
const pageSize = ref(10)
const totalPosts = ref(0)

// 获取用户文章
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
    ElMessage.error('加载文章失败')
  }
}

const handlePageChange = (newPage) => {
  currentPage.value = newPage
  getPosts()
}

const handleSizeChange = (newSize) => {
  pageSize.value = newSize
  currentPage.value = 1
  getPosts()
}

const handleEdit = (postId) => {
  router.push(`/post/edit/${postId}`)
}

const handleSaveIntro = async () => {
  try {
    await userApi.updateIntro({ 
      userId: userId.value,
      intro: form.value.intro 
    })
    userInfo.value.intro = form.value.intro
    dialogVisible.value = false
    ElMessage.success('简介更新成功')
  } catch (error) {
    ElMessage.error('简介更新失败')
  }
}

const handleDelete = async (postId) => {
  try {
    await ElMessageBox.confirm('确认删除该文章吗？', '警告', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    }).catch(() => {
      return Promise.reject('cancel')
    })
    await postApi.deletePost(postId)
    ElMessage.success('删除成功')
    getPosts()
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除失败')
    }
  }
}

onMounted(() => {
  getUserInfo()
  getPosts()
})
</script>

<template>
  <div class="user-profile">
    <!-- 用户信息卡片 -->
    <el-card class="user-info-card">
      <div class="user-header">
        <h2>{{ userInfo.username }}</h2>
        <p class="user-id">用户ID：{{ userInfo.userId }}</p>
      </div>
      
      <div class="user-meta">
        <p>注册时间：{{ new Date(userInfo.createTime).toLocaleDateString() }}</p>
        <el-link type="primary" :underline="false" @click="dialogVisible = true">
          {{ userInfo.intro || '点击添加个人简介' }}
        </el-link>

        <!-- 编辑个人简介的对话框 -->
        <el-dialog v-model="dialogVisible" title="编辑个人简介" width="30%">
          <el-form :model="form" label-width="80px">
            <el-form-item label="新简介">
              <el-input
                v-model="form.intro"
                type="textarea"
                :rows="4"
                placeholder="请输入新的个人简介"
                maxlength="150"
                show-word-limit
              />
            </el-form-item>
          </el-form>
          <template #footer>
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="handleSaveIntro">保存</el-button>
          </template>
        </el-dialog>
      </div>
    </el-card>

    <!-- 发布按钮 -->
    <div class="post-action">
      <el-button type="primary" @click="$router.push('/post/create')">发布新文章</el-button>
    </div>

    <!-- 文章列表 -->
    <div class="post-list">
      <h3>发布的文章（{{ totalPosts }}篇）</h3>
      <div v-for="post in posts" :key="post.postId" class="post-item">
        <el-card>
          <div class="post-actions">
            <el-button type="primary" size="small" @click.stop="handleEdit(post.postId)">编辑</el-button>
            <el-button type="danger" size="small" @click.stop="handleDelete(post.postId)">删除</el-button>
          </div>
          <router-link :to="`/post/${post.postId}`">
            <h4>{{ post.title }}</h4>
            <p class="post-excerpt">{{ post.content.substring(0, 100) }}...</p>
            <div class="post-meta">
              <span>{{ new Date(post.createTime).toLocaleDateString() }}</span>
            </div>
          </router-link>
        </el-card>
      </div>
      <el-pagination
        :current-page="currentPage"
        :page-size="pageSize"
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
.user-profile {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
}

.user-info-card {
  margin-bottom: 30px;
}

.user-header {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.user-header h2 {
  font-size: 24px;
  margin-right: 15px;
}

.user-id {
  color: #666;
  font-size: 14px;
}

.user-meta {
  color: #666;
  line-height: 1.8;
}

.user-intro {
  margin-top: 10px;
  padding: 10px;
  background-color: #f5f5f5;
  border-radius: 4px;
}

.post-list h3 {
  margin-bottom: 20px;
  color: #333;
}

.post-item {
  margin-bottom: 15px;
}

.post-item a {
  text-decoration: none;
  color: inherit;
}

.post-excerpt {
  color: #666;
  margin: 10px 0;
}

.post-meta {
  font-size: 12px;
  color: #999;
}
.post-actions {
  position: absolute;
  right: 10px;
  top: 10px;
  display: flex;
  gap: 8px;
  z-index: 10;
}

.el-card {
  position: relative;
  padding: 20px;
}

.post-excerpt {
  margin-top: 15px;
}
</style>
