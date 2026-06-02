# 东软环保公众监督系统 — E-R 图文档

## 实体关系总览

```
┌──────────────────┐       ┌──────────────────┐
│   grid_province  │       │    supervisor    │
│   (省区域表)      │       │   (公众监督员表)   │
├──────────────────┤       ├──────────────────┤
│ PK province_id   │       │ PK tel_id        │
│    province_name │       │    password      │
│    province_abbr │       │    real_name     │
│    remarks       │       │    birthday      │
└──────┬───────────┘       │    sex           │
       │                   │    remarks       │
       │1                  └────────┬─────────┘
       │                           │
       │                           │1
       │                           │
       ├───< grid_city >───┐       │
       │    (市区域表)      │       │
       │                   │       │
       │    1              │       │
       │                   │       │
       │    ┌──────────────┘       │
       │    │                      │
       │    │n                     │n
       │    │                      │
┌──────┴────┴───────────┐         │
│    aqi_feedback       │         │
│   (反馈信息表)         │         │
├───────────────────────┤         │
│ PK af_id              │         │
│ FK tel_id    ──────────────────┘
│ FK province_id        │
│ FK city_id            │
│    address            │
│    information        │
│ FK estimated_grade ───┼────────────┐
│    af_date            │            │
│    af_time            │            │
│ FK gm_id ─────────────┼──────┐     │
│    assign_date        │      │     │
│    assign_time        │      │     │
│    state              │      │     │
│    remarks            │      │     │
└───────────────────────┘      │     │
                               │     │
       ┌───────────────────────┘     │
       │                             │
       │n                            │n
       │                             │
┌──────┴──────────┐         ┌───────┴──────────┐
│  grid_member    │         │       aqi        │
│  (网格员表)      │         │ (AQI等级定义表)   │
├─────────────────┤         ├──────────────────┤
│ PK gm_id        │         │ PK aqi_id        │
│    gm_name      │         │    chinese_explain│
│    gm_code (UK) │         │    aqi_explain   │
│    password     │         │    color         │
│ FK province_id  │         │    health_impact │
│ FK city_id      │         │    take_steps    │
│    tel          │         │    so2_min/max   │
│    state        │         │    co_min/max    │
│    remarks      │         │    spm_min/max   │
└────────┬────────┘         │    remarks       │
         │                  └──────────────────┘
         │1
         │
         │
┌────────┴─────────┐
│   statistics     │
│  (实测统计表)     │
├──────────────────┤
│ PK id            │
│ FK province_id   │
│ FK city_id       │
│    address       │
│    so2_value     │
│    so2_level     │
│    co_value      │
│    co_level      │
│    spm_value     │
│    spm_level     │
│ FK aqi_id        │
│    confirm_date  │
│    confirm_time  │
│ FK gm_id         │
│ FK fd_id         │
│    information   │
│    remarks       │
└──────────────────┘

┌──────────────────┐
│     admins       │
│  (系统管理员表)   │
├──────────────────┤
│ PK admin_id      │
│    admin_code(UK)│
│    password      │
│    remarks       │
└──────────────────┘
```

---

## 实体关系说明

### 一对多关系 (One-to-Many)

| 主表 (1)        | 从表 (n)        | 外键           | 说明                     |
|----------------|-----------------|----------------|--------------------------|
| grid_province  | grid_city       | province_id    | 一个省包含多个市          |
| grid_province  | grid_member     | province_id    | 一个省有多个网格员        |
| grid_city      | grid_member     | city_id        | 一个市有多个网格员        |
| grid_province  | aqi_feedback    | province_id    | 一个省有多条反馈          |
| grid_city      | aqi_feedback    | city_id        | 一个市有多条反馈          |
| supervisor     | aqi_feedback    | tel_id         | 一个监督员可提交多条反馈   |
| supervisor     | statistics      | fd_id          | 一个监督员对应多条统计记录 |
| grid_member    | aqi_feedback    | gm_id          | 一个网格员负责多条反馈     |
| grid_member    | statistics      | gm_id          | 一个网格员提交多条统计数据 |
| aqi            | aqi_feedback    | estimated_grade| 一个AQI等级对应多条反馈    |
| aqi            | statistics      | aqi_id         | 一个AQI等级对应多条统计   |

### 实体属性说明

#### grid_province (省区域) — 16条记录
| 属性            | 类型          | 说明          |
|----------------|--------------|--------------|
| province_id    | INT (PK, AI) | 省区域编号     |
| province_name  | VARCHAR(20)  | 省名称        |
| province_abbr  | VARCHAR(2)   | 省简称(京/津/冀等)|
| remarks        | VARCHAR(200) | 备注          |

#### grid_city (市区域) — 17条记录
| 属性         | 类型          | 说明      |
|-------------|--------------|----------|
| city_id     | INT (PK, AI) | 市区域编号 |
| city_name   | VARCHAR(20)  | 市名称    |
| province_id | INT (FK)     | 所属省    |

#### supervisor (公众监督员) — 30条记录
| 属性      | 类型          | 说明            |
|----------|--------------|------------------|
| tel_id   | VARCHAR(11) PK| 手机号(即监督员编号)|
| password | VARCHAR(20)  | 登录密码         |
| real_name| VARCHAR(20)  | 真实姓名         |
| birthday | VARCHAR(20)  | 出生日期         |
| sex      | INT          | 性别(1男 0女)    |

#### grid_member (网格员) — 34条记录
| 属性         | 类型          | 说明                    |
|-------------|--------------|-------------------------|
| gm_id       | INT (PK, AI) | 网格员编号               |
| gm_name     | VARCHAR(20)  | 网格员姓名               |
| gm_code     | VARCHAR(20) UK| 登录编码(唯一)           |
| password    | VARCHAR(20)  | 登录密码                 |
| province_id | INT (FK)     | 负责省区域               |
| city_id     | INT (FK)     | 负责市区域               |
| tel         | VARCHAR(20)  | 联系电话                 |
| state       | INT          | 工作状态(0工作 1非工作 2其他)|

#### aqi (AQI等级定义) — 6条记录
| 属性             | 类型          | 说明              |
|-----------------|--------------|-------------------|
| aqi_id          | INT (PK, AI) | 等级编号(1-6)      |
| chinese_explain | VARCHAR(10)  | 等级中文(一/二...六)|
| aqi_explain     | VARCHAR(20)  | 等级描述(优/良/...严重污染)|
| color           | VARCHAR(7)   | 表示颜色(#02E300等)|
| health_impact   | VARCHAR(500) | 健康影响说明        |
| take_steps      | VARCHAR(500) | 建议措施           |
| so2_min/max     | INT          | SO₂浓度阈值范围     |
| co_min/max      | INT          | CO浓度阈值范围     |
| spm_min/max     | INT          | PM2.5浓度阈值范围  |

#### aqi_feedback (反馈信息) — 39条记录
| 属性            | 类型          | 说明                        |
|----------------|--------------|-----------------------------|
| af_id          | INT (PK, AI) | 反馈编号                     |
| tel_id         | VARCHAR(20) FK| 监督员手机号                 |
| province_id    | INT (FK)     | 省区域                       |
| city_id        | INT (FK)     | 市区域                       |
| address        | VARCHAR(200) | 详细地址                     |
| information    | VARCHAR(400) | 反馈描述                     |
| estimated_grade| INT (FK)     | 公众预估的AQI等级             |
| af_date        | VARCHAR(20)  | 反馈日期                     |
| af_time        | VARCHAR(20)  | 反馈时间                     |
| gm_id          | INT (FK)     | 指派网格员(默认0表示未指派)    |
| assign_date    | VARCHAR(20)  | 指派日期                     |
| assign_time    | VARCHAR(20)  | 指派时间                     |
| state          | INT          | 状态(0未指派/1已指派/2已确认)|

#### statistics (实测统计) — 39条记录
| 属性         | 类型          | 说明                |
|-------------|--------------|---------------------|
| id          | INT (PK, AI) | 统计编号             |
| province_id | INT (FK)     | 省区域               |
| city_id     | INT (FK)     | 市区域               |
| so2_value   | INT          | SO₂实测浓度(μg/m³)   |
| so2_level   | INT          | SO₂对应AQI级别       |
| co_value    | INT          | CO实测浓度(μg/m³)    |
| co_level    | INT          | CO对应AQI级别        |
| spm_value   | INT          | PM2.5实测浓度(μg/m³) |
| spm_level   | INT          | PM2.5对应AQI级别     |
| aqi_id      | INT (FK)     | 综合AQI级别          |
| confirm_date| VARCHAR(20)  | 确认日期             |
| confirm_time| VARCHAR(20)  | 确认时间             |
| gm_id       | INT (FK)     | 检测网格员           |
| fd_id       | VARCHAR(20) FK| 反馈者手机号         |

#### admins (系统管理员) — 1条记录
| 属性       | 类型          | 说明         |
|-----------|--------------|-------------|
| admin_id  | INT (PK, AI) | 管理员编号   |
| admin_code| VARCHAR(20) UK| 登录编码    |
| password  | VARCHAR(20)  | 登录密码    |
| remarks   | VARCHAR(200) | 备注       |

---

## 核心业务流程对应的数据操作

1. **公众监督员提交反馈** → INSERT aqi_feedback (state=0)
2. **管理员指派网格员** → UPDATE aqi_feedback SET gm_id=?, state=1, assign_date, assign_time
3. **网格员查看任务** → SELECT aqi_feedback JOIN ... WHERE gm_id=? AND state=1
4. **网格员提交实测** → INSERT statistics + UPDATE aqi_feedback SET state=2
