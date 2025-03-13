



---
# 接口文档
---
## 通用
---
- ### 注册：POST /register
#### 请求示例
```json
{
    "username": "test",
    "password": "123456"
}
```
#### 响应示例
```json

{
    "code": 1,
    "msg": "success",
  	"data": {
        "userId": 1
  	}
}
```
- ## 登录：POST /login
#### 请求示例
```json
{
    "username": "test",
    "password": "123456"
}
```
#### 响应示例
```json
{
    "code": 1,
    "msg": "success",
    "data": {
        "userId": 1,
        "username": "test",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ0ZXN0IiwiZXhwIjoxNzQxODk3ODk3fQ.OUZzi4EcV7ZAinj-kJt1bLw_DOp8yWmsWjS2bh9O87k"
    }
}
```
---
## 文章相关
---
- ### 发布文章 POST /post
#### 请求示例
```json
{
   "title": "test",
   "content": "testtesttetsttesttest"
}
```
#### 响应示例
```json
{
    "code": 1,
    "msg": "success",
    "data": null
}
```
- ### 分页查询 GET /post
#### 请求示例

|  参数名  |  类型  | 必填 |     示例值     |          描述          |
| :------: | :----: | :--: | :------------: | :--------------------: |
|   page   | string |  否  |     `"1"`      | 当前页码（默认第1页）  |
| pageSize | string |  否  |     `"2"`      | 每页数据量（默认10条） |
|  title   | string |  否  |     `"t"`      |   标题关键词模糊匹配   |
|  begin   | string |  否  | `"2025-03-01"` | 开始日期（YYYY-MM-DD） |
|   end    | string |  否  | `"2025-03-15"` | 结束日期（YYYY-MM-DD） |

#### 响应示例
```json
{
    "code": 1,
    "msg": "success",
    "data": {
        "total": 7,
        "rows": [
            {
                "title": "test",
                "createUser": 1,
                "content": "testtesttetsttesttest",
                "likeCount": 0,
                "createTime": "2025-03-13T16:14:56",
                "updateTime": "2025-03-13T16:14:56",
                "postId": 8
            },
            {
                "title": "test",
                "createUser": 1,
                "content": "testtesttetsttesttest",
                "likeCount": 0,
                "createTime": "2025-03-13T16:14:05",
                "updateTime": "2025-03-13T16:14:05",
                "postId": 7
            }
        ]
    }
}
```
----
## 回复相关
---
- ### 发布回复 POST /reply
#### 请求示例
```json
{
    "postId": 2,
    "content": "testtesttesttesttest"
}
```
#### 响应示例
```json
{
    "code": 1,
    "msg": "success",
    "data": null
}
```
- ### 分页查询 GET /reply
#### 请求示例

|  参数名  |  类型  | 必填 |  示例值  |          描述          |
| :------: | :----: | :--: | :------: | :--------------------: |
|   page   | string |  否  |  `"1"`   | 当前页码（默认第1页）  |
| pageSize | string |  否  |  `"10"`  | 每页数据量（默认10条） |
|  postId  | string |  是  | `"test"` |     所回复文章的id     |

#### 响应示例
```json
{
    "code": 1,
    "msg": "success",
    "data": {
        "total": 2,
        "rows": [
            {
                "title": null,
                "createUser": 1,
                "content": "testtesttesttesttest",
                "likeCount": 0,
                "createTime": "2025-03-13T16:24:54",
                "updateTime": null,
                "postId": 2
            },
            {
                "title": null,
                "createUser": 1,
                "content": "testtesttesttesttest",
                "likeCount": 0,
                "createTime": "2025-03-13T16:19:23",
                "updateTime": null,
                "postId": 2
            }
        ]
    }
}
```















