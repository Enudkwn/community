<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { postApi } from '@/api/post'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()
const isEditMode = ref(false)
const postId = ref(null)

const form = ref({
  title: '',
  content: ''
})

const rules = {
  title: [
    { required: true, message: '请输入标题', trigger: 'blur' },
    { max: 25, message: '标题不能超过25字', trigger: 'blur' }
  ],
  content: [{ required: true, message: '请输入内容', trigger: 'blur' }]
}

const submitPost = async () => {
  try {
    if (isEditMode.value) {
      await postApi.updatePost({ postId: postId.value, ...form.value })
      ElMessage.success('修改成功')
    } else {
      await postApi.newPost(form.value)
      ElMessage.success('发布成功')
    }
    router.push('/index')
  } catch (error) {
    ElMessage.error(isEditMode.value ? '修改失败' : '发布失败')
  }
}

onMounted(() => {
  if (route.params.postId) {
    isEditMode.value = true
    postId.value = route.params.postId
    postApi.getPostDetail(postId.value).then(res => {
      form.value = {
        title: res.data.title,
        content: res.data.content
      }
    })
  }
})
</script>

<template>
  <div class="post-create-container">
    <el-card class="form-card">
      <el-form :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入文章标题" maxlength="25" show-word-limit />
        </el-form-item>
        
        <el-form-item label="内容" prop="content">
          <el-input
            v-model="form.content"
            type="textarea"
            :rows="8"
            placeholder="请输入文章内容"
          />
        </el-form-item>
        
        <el-form-item>
          <el-button type="primary" @click="submitPost">立即发布</el-button>
          <el-button @click="router.go(-1)">返回</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.post-create-container {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
}

.form-card {
  padding: 20px;
}

</style>