# api

接口请求层目录，按后端业务资源拆分文件，不按前端角色拆分。

后续建议文件：

- `request.js`：Axios 基础实例、请求拦截、响应拦截、错误提示。
- `supervisor.js`：公众监督员注册、登录、信息查询。
- `aqiFeedback.js`：空气质量反馈提交、查询、指派。
- `gridMember.js`：网格员登录、网格员查询。
- `statistics.js`：AQI 检测确认、统计数据查询。
- `admins.js`：系统管理员登录。

