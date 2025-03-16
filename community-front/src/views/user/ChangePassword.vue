<script setup>
import { ref } from 'vue'
import { userApi } from '@/api/user'  // 引入修改密码的API
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const passwordForm = ref({
  oldPassword: '',
  newPassword: '',
  confirmPassword: ''
})
const router = useRouter()

const rules = {
  oldPassword: [
    { required: true, message: '请输入原密码', trigger: 'blur' }
  ],
  newPassword: [
    { required: true, message: '请输入新密码', trigger: ['blur', 'submit'] },
    { min: 6, max: 16, message: '密码长度需在6到16个字符之间', trigger: ['blur', 'submit'] }
  ],
  confirmPassword: [
    { 
      required: true, 
      message: '请确认新密码', 
      trigger: 'blur' 
    },
    {
      validator: (rule, value, callback) => {
        if (value !== passwordForm.value.newPassword) {
          return callback(new Error('两次输入的密码不一致'))
        }
        callback()
      },
      trigger: 'blur'
    }
  ]
}

const formRef = ref()

const updatePassword = async () => {
  await formRef.value.validate()
  // 获取当前用户的ID，这里假设从localStorage中获取已登录的用户信息
  const loginUser = JSON.parse(localStorage.getItem('loginUser'))
  const userId = loginUser?.userId

  if (userId) {
    const result = await userApi.updatePassword({
      userId,
      oldPassword: passwordForm.value.oldPassword,
      newPassword: passwordForm.value.newPassword
    })

    if (result.code) {
      ElMessage.success('密码修改成功')
      router.push('/index')  // 修改成功后跳转到首页
    } else {
      ElMessage.error(result.msg)
    }
  }
}
</script>

<template>
  <div id="container">
    <div class="change-password-form">
      <el-form ref="formRef" :model="passwordForm" :rules="rules" label-width="80px" validate-on-rule-change>
        <p class="title">修改密码</p>
        
        <el-form-item label="原密码" prop="oldPassword">
          <el-input type="password" v-model="passwordForm.oldPassword" placeholder="请输入原密码"></el-input>
        </el-form-item>

        <el-form-item label="新密码" prop="newPassword">
          <el-input type="password" v-model="passwordForm.newPassword" placeholder="请输入新密码"></el-input>
        </el-form-item>

        <el-form-item label="确认新密码" prop="confirmPassword">
          <el-input type="password" v-model="passwordForm.confirmPassword" placeholder="请确认新密码"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button class="button" type="primary" @click="updatePassword">提交修改</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style scoped>
#container {
  min-height: 100vh;
  padding: 10%;
  height: 420px;
  background-color: #e6f7ff;
}

.change-password-form {
  max-width: 400px;
  padding: 30px;
  margin: 0 auto;
  border: 1px solid #e0e0e0;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  background-color: white;
}

.title {
  font-size: 30px;
  font-family: '楷体';
  text-align: center;
  margin-bottom: 30px;
  font-weight: bold;
}

.button {
  margin-top: 30px;
  width: 120px;
}
</style>
