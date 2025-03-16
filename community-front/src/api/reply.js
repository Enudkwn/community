import request from '@/utils/request'

export const replyApi = {
  createReply: (data) => request.post('/reply', data),
  getReplies: (params) => request.get('/reply', { params })
}