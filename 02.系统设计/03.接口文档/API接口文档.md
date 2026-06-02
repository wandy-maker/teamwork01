# 东软环保公众监督系统 — API 接口文档

## 概述

- **基础URL**: `http://localhost:8080/nepm`
- **请求方式**: 全部使用 `POST`
- **请求体格式**: `application/json`
- **响应格式**: `application/json`
- **前端应用**: Vue 3 网格员移动端 (端口 8083)

---

## 1. 网格员模块

### 1.1 网格员登录

**接口**: `POST /gridMember/getGridMemberByCodeByPass`

**描述**: 网格员使用登录编码和密码进行身份验证。登录成功后前端将返回的网格员信息存入 sessionStorage，并跳转到反馈任务列表页。

**请求参数**:

```json
{
    "gmCode": "caocao",
    "password": "123"
}
```

| 字段       | 类型   | 必填 | 说明         |
|-----------|--------|------|-------------|
| gmCode    | String | 是   | 网格员登录编码 |
| password  | String | 是   | 登录密码      |

**成功响应**:

```json
{
    "gmId": 1,
    "gmName": "曹操",
    "gmCode": "caocao",
    "password": "123",
    "provinceId": 1,
    "cityId": 1,
    "tel": "12687643458",
    "state": 0,
    "gridProvince": {
        "provinceId": 1,
        "provinceName": "北京市",
        "provinceAbbr": "京"
    },
    "gridCity": {
        "cityId": 1,
        "cityName": "北京市",
        "provinceId": 1
    }
}
```

**失败响应**: 返回空字符串 `""`，前端提示"登录编码或密码输入不正确！"

---

## 2. 空气质量反馈模块

### 2.1 查询已指派反馈列表

**接口**: `POST /aqiFeedback/listAqiFeedbackByGmId`

**描述**: 网格员登录后，查询分配给自己的状态为"已指派"的反馈任务列表。

**请求参数**:

```json
{
    "gmId": 1,
    "state": 1
}
```

| 字段  | 类型    | 必填 | 说明                        |
|------|---------|------|----------------------------|
| gmId | Integer | 是   | 网格员编号                   |
| state| Integer | 是   | 反馈状态 (0:未指派, 1:已指派, 2:已确认) |

**成功响应**:

```json
[
    {
        "afId": 18,
        "telId": "15560023569",
        "provinceId": 1,
        "cityId": 1,
        "address": "怀柔区北辰街道78号",
        "information": "人类的环境破坏已经变得千疮百孔，保护大自然，人人有责。",
        "estimatedGrade": 5,
        "afDate": "2022-04-26",
        "afTime": "10:13:41",
        "gmId": 1,
        "assignDate": "2022-11-27",
        "assignTime": "11:03:29",
        "state": 2,
        "supervisor": {
            "telId": "15560023569",
            "realName": "曲灵风"
        },
        "gridProvince": {
            "provinceId": 1,
            "provinceName": "北京市"
        },
        "gridCity": {
            "cityId": 1,
            "cityName": "北京市"
        },
        "aqi": {
            "aqiId": 5,
            "chineseExplain": "五",
            "aqiExplain": "重度污染",
            "color": "#98004B"
        }
    }
]
```

### 2.2 查询反馈详情

**接口**: `POST /aqiFeedback/getAqiFeedbackById`

**描述**: 网格员点击"去检测"后，根据反馈ID查询完整详情，包含监督员、区域、预估AQI等级等全部信息。

**请求参数**:

```json
{
    "afId": 18
}
```

| 字段 | 类型    | 必填 | 说明     |
|-----|---------|------|---------|
| afId| Integer | 是   | 反馈编号 |

**成功响应**: 返回单个 AqiFeedback 对象，包含所有关联数据（supervisor, gridProvince, gridCity, aqi, gridMember）。

### 2.3 更新反馈状态

**接口**: `POST /aqiFeedback/updateAqiFeedbackState`

**描述**: 网格员提交实测数据后，将反馈状态从"已指派(1)"更新为"已确认(2)"。

**请求参数**:

```json
{
    "afId": 18,
    "state": 2
}
```

| 字段  | 类型    | 必填 | 说明     |
|------|---------|------|---------|
| afId | Integer | 是   | 反馈编号 |
| state| Integer | 是   | 新状态   |

**成功响应**: `1` (整数，表示影响行数)

---

## 3. 空气质量指数模块

### 3.1 查询全部AQI等级

**接口**: `POST /aqi/listAqiAll`

**描述**: 获取全部6级空气质量指数定义数据，用于前端根据实测浓度值动态计算并展示AQI等级和对应颜色。

**请求参数**: 无

**成功响应**:

```json
[
    {
        "aqiId": 1,
        "chineseExplain": "一",
        "aqiExplain": "优",
        "color": "#02E300",
        "healthImpact": "空气质量令人满意，基本无空气污染",
        "takeSteps": "各类人群可正常活动",
        "so2Min": 0,
        "so2Max": 50,
        "coMin": 0,
        "coMax": 5,
        "spmMin": 0,
        "spmMax": 35
    },
    ...
    {
        "aqiId": 6,
        "chineseExplain": "六",
        "aqiExplain": "严重污染",
        "color": "#7E0123",
        "so2Min": 1601,
        "so2Max": 2620,
        "coMin": 91,
        "coMax": 150,
        "spmMin": 251,
        "spmMax": 500
    }
]
```

---

## 4. 实测统计模块

### 4.1 保存实测数据

**接口**: `POST /statistics/saveStatistics`

**描述**: 网格员输入SO₂、CO、PM2.5实测浓度值后提交，系统保存实测数据并自动关联计算后的AQI等级。

**请求参数**:

```json
{
    "provinceId": 1,
    "cityId": 1,
    "address": "怀柔区北辰街道78号",
    "so2Value": "425",
    "so2Level": 3,
    "coValue": "42",
    "coLevel": 4,
    "spmValue": "56",
    "spmLevel": 2,
    "aqiId": 4,
    "confirmDate": "2022-11-27",
    "confirmTime": "11:09:31",
    "gmId": 1,
    "fdId": "15560023569",
    "information": "人类的环境破坏已经变得千疮百孔，保护大自然，人人有责。"
}
```

| 字段         | 类型    | 必填 | 说明               |
|-------------|---------|------|-------------------|
| provinceId  | Integer | 是   | 省区域编号          |
| cityId      | Integer | 是   | 市区域编号          |
| address     | String  | 是   | 详细地址            |
| so2Value    | String  | 是   | SO₂实测浓度(μg/m³) |
| so2Level    | Integer | 是   | SO₂对应AQI级别     |
| coValue     | String  | 是   | CO实测浓度(μg/m³)  |
| coLevel     | Integer | 是   | CO对应AQI级别      |
| spmValue    | String  | 是   | PM2.5实测浓度(μg/m³)|
| spmLevel    | Integer | 是   | PM2.5对应AQI级别   |
| aqiId       | Integer | 是   | 综合AQI级别(取最大值)|
| confirmDate | String  | 是   | 确认日期(YYYY-MM-DD)|
| confirmTime | String  | 是   | 确认时间(HH:mm:ss) |
| gmId        | Integer | 是   | 网格员编号          |
| fdId        | String  | 是   | 反馈者手机号         |
| information | String  | 是   | 反馈信息描述         |

**成功响应**: `1` (整数)

---

## 5. 业务状态码

| 状态码 | 含义   | 说明                       |
|-------|--------|---------------------------|
| 0     | 未指派 | 公众监督员提交反馈后初始状态 |
| 1     | 已指派 | 系统管理员已将任务指派给网格员 |
| 2     | 已确认 | 网格员已完成实测并提交数据    |

## 6. 前端路由与权限

| 路由            | 页面          | 是否需要登录 |
|----------------|---------------|-------------|
| /              | 登录页        | 否          |
| /login         | 登录页        | 否          |
| /feedbackList  | 反馈任务列表   | 是          |
| /feedbackInfo  | 反馈详情/实测  | 是          |

路由守卫通过 `router.beforeEach` 检查 `sessionStorage` 中的 `gridMember` 实现权限认证。
