<script setup>
import {ref, onMounted} from 'vue';
import {ElMessageBox, ElMessage} from 'element-plus';
import { useRouter } from 'vue-router';

//当前登录员工
const loginName = ref('');
const loginUserId = ref('');
const router = useRouter();

//钩子函数
onMounted(() => {
  const loginUser = JSON.parse(localStorage.getItem('loginUser'));
  if(loginUser && loginUser.username){
    loginName.value = loginUser.username;
    loginUserId.value = loginUser.userId;
  }
})

//退出登录
const logout = () => {
  //弹出确认框
  ElMessageBox.confirm('您确认退出登录吗?','提示',
    { confirmButtonText: '确认',cancelButtonText: '取消',type: 'warning'}
  ).then(async () => { //确认
    ElMessage.success('退出成功');
    localStorage.removeItem('loginUser');
    //跳转页面-登录
    router.push('/login');
  }).catch(() => { //取消
    ElMessage.info('您已取消退出');
  })
}

const user = () => {
  router.push(`/user/${loginUserId.value}`);
}
</script>

<template>
  <div class="common-layout">
    <el-container>
      <!-- Header 区域 -->
      <el-header class="header">
        <div class="header-left">
            <span class="title">重邮论坛</span>
            <a href="/index" class="home-link">首页</a>
          </div>
        <span class="right_tool">
          <a href="javascript:;" @click="user">
            <el-icon><UserFilled /></el-icon> 个人中心 &nbsp;&nbsp;&nbsp; |  &nbsp;&nbsp;&nbsp;
          </a>

          <router-link to="/user/change-password">
            <el-icon><EditPen /></el-icon> 修改密码 &nbsp;&nbsp;&nbsp; |  &nbsp;&nbsp;&nbsp;
          </router-link>
          <a href="javascript:;" @click="logout">
            <el-icon><SwitchButton /></el-icon> 退出登录 [当前登录：{{loginName}}]
          </a>
        </span>
      </el-header>
      
      <el-container>
        <!-- 左侧菜单 -->
        
        
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
      
    </el-container>
  </div>
</template>

<style scoped>
.header {
  background: #7dd3fa;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 30px;
}

.title {
  color: #f6f6f6;
  font-size: 40px;
  font-family: 楷体;
  font-weight: bolder;
}

.home-link {
  font-size: 16px;
  transition: opacity 0.3s;
}

.home-link:hover {
  opacity: 0.8;
}

.title {
  color: #f6f6f6;
  font-size: 40px;
  font-family: 楷体;
  line-height: 60px;
  font-weight: bolder;
}

.right_tool{
  float: right;
  line-height: 60px;
}

a {
  color: white;
  text-decoration: none;
}
</style>
