import request from '@/utils/request'

export const userApi = {
    selectByUserId: (userId) => request.get(`/user/${userId}`),
    updatePassword: (data) => request.put('/user/password', data),
    updateIntro: (data) => request.put('/user/intro', data)
}