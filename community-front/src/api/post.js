import request from '@/utils/request'

export const postApi = {
  newPost: (data) => request.post('/post', data),
  getPosts: (params) => request.get('/post/page', { params }),
  getPostDetail: (postId) => request.get(`/post/${postId}`),
  getByUserId: (params) => request.get(`/post/user`, { params }),
  updatePost: (data) => request.put('/post/user', data),
  deletePost: (postId) => request.delete(`/post/user/${postId}`)
}