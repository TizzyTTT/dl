
<a name="paths"></a>
## 资源

<a name="login-controller_resource"></a>
### Login-controller
Login Controller


<a name="loginusingget"></a>
#### 登录接口方法说明
```
GET /api/admin/login
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Path**|**cid**  <br>*必填*|cid|integer (int32)|
|**Query**|**username**  <br>*必填*||string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/api/admin/login?username=string
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "message" : "string",
  "result" : "object"
}
```


<a name="logoutusingpost"></a>
#### 注销接口方法说明
```
POST /api/admin/logout
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**user**  <br>*必填*|user|[User](#user)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/api/admin/logout
```


###### 请求 body
```json
{
  "password" : "string",
  "username" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "message" : "string",
  "result" : "object"
}
```



