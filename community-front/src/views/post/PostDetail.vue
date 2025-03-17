<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { postApi } from '@/api/post'
import { replyApi } from '@/api/reply'
import { ElMessage } from 'element-plus'

const route = useRoute()
const postId = ref('')
const postDetail = ref({})
const comments = ref([])
const total = ref(0)
const currentPage = ref(1)
const pageSize = ref(10)
const replyContent = ref('')

// 获取文章详情
const getPostDetail = async () => {
  try {
    const res = await postApi.getPostDetail(postId.value)
    postDetail.value = res.data
  } catch (error) {
    console.error('获取文章详情失败:', error)
  }
}

// 获取评论列表
const loadComments = async () => {
  try {
    const res = await replyApi.getReplies({
      postId: postId.value,
      page: currentPage.value,
      pageSize: pageSize.value
    })
    comments.value = res.data.rows
    total.value = res.data.total
  } catch (error) {
    console.error('加载评论失败:', error)
  }
}

// 分页变化处理
const handlePageChange = (val) => {
  currentPage.value = val
  loadComments()
}

// 提交回复
const handleReply = async () => {
  if (!replyContent.value.trim()) {
    ElMessage.error('回复内容不能为空')
    return
  }
  try {
    await replyApi.createReply({
      postId: postId.value,
      content: replyContent.value
    })
    ElMessage.success('回复成功')
    replyContent.value = ''
    loadComments()
  } catch (error) {
    console.error('回复失败:', error)
    ElMessage.error('回复失败')
  }
}
onMounted(() => {
  postId.value = route.params.postId
  getPostDetail()
  loadComments()
})
</script>

<template>
  <div class="container">
    <!-- 文章内容区域 -->
    <div class="post-content">
      <h1>{{ postDetail.title }}</h1>
      <div class="meta">
        <span>作者：<router-link :to="`/user/public/${postDetail.createUser || ''}`">
          {{ postDetail.createUsername }}
        </router-link></span>
        <span>发布时间：{{ postDetail.createTime }}</span>
      </div>
      <div class="content">{{ postDetail.content }}</div>
    </div>

    <!-- 回复表单 -->
    <div class="reply-form">
      <el-form :model="replyContent">
        <el-form-item>
          <el-input
            v-model="replyContent"
            type="textarea"
            :rows="3"
            placeholder="请输入您的回复"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleReply">提交回复</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 评论列表 -->
    <div class="comments">
      <h3>评论（{{ total }}条）</h3>
      <div v-for="comment in comments" :key="comment.id" class="comment-item">
        <div class="user-info">
          <span>{{ comment.createUsername }}</span>
          <span>{{ comment.createTime }}</span>
        </div>
        <div class="comment-content">{{ comment.content }}</div>
      </div>

      <!-- 分页组件 -->
      <el-pagination
        :current-page="currentPage"
        :page-size="pageSize"
        :total="total"
        layout="total, prev, pager, next"
        @current-change="handlePageChange"
      />
    </div>
  </div>
</template>

<style scoped>
.container {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
}
.post-content {
  margin-bottom: 40px;
}
.meta {
  color: #666;
  margin: 15px 0;
}
.meta span {
  margin-right: 20px;
}
.content {
  line-height: 1.6;
  font-size: 16px;
}
.comments {
  border-top: 1px solid #eee;
  padding-top: 20px;
}
.comment-item {
  margin: 15px 0;
  padding: 10px;
  border: 1px solid #eee;
  border-radius: 4px;
}
.user-info {
  color: #666;
  margin-bottom: 8px;
}
.user-info span {
  margin-right: 15px;
}
.reply-form {
  margin-bottom: 30px;
  border-bottom: 1px solid #eee;
  padding-bottom: 20px;
}
.el-button {
  margin-left: auto;
  display: block;
}
</style>