import request from "@/utils/request";

//登录
export const registerApi = (data) => request.post("/register", data);