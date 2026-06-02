<script setup>
import { computed, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { Location, ChatLineRound, Upload } from '@element-plus/icons-vue'

const districtOptions = [
  { label: '朝阳区', value: 'chaoyang' },
  { label: '海淀区', value: 'haidian' },
  { label: '丰台区', value: 'fengtai' },
]

const form = reactive({
  district: '',
  aqi: 80,
  content: '',
})

const selectedDistrictName = computed(() => {
  return districtOptions.find((item) => item.value === form.district)?.label || ''
})

const aqiLevel = computed(() => {
  if (form.aqi <= 50) return { text: '优', color: '#2f9e44' }
  if (form.aqi <= 100) return { text: '良', color: '#f59f00' }
  if (form.aqi <= 150) return { text: '轻度污染', color: '#f76707' }
  if (form.aqi <= 200) return { text: '中度污染', color: '#e03131' }
  if (form.aqi <= 300) return { text: '重度污染', color: '#9c36b5' }
  return { text: '严重污染', color: '#862e9c' }
})

const submitFeedback = () => {
  const payload = {
    district: form.district,
    districtName: selectedDistrictName.value,
    aqi: form.aqi,
    level: aqiLevel.value.text,
    content: form.content.trim(),
  }

  console.log('空气质量反馈表单数据:', payload)
  ElMessage.success('反馈已提交')
}
</script>

<template>
  <main class="feedback-page">
    <el-container class="page-shell">
      <el-header class="page-header">
        <p class="eyebrow">空气质量反馈</p>
        <h1>提交所在网格空气情况</h1>
      </el-header>

      <el-main class="page-main">
        <el-form class="feedback-form" label-position="top">
          <el-form-item label="网格地址">
            <el-select
              v-model="form.district"
              class="field-control"
              placeholder="请选择所在区域"
              size="large"
            >
              <el-option
                v-for="item in districtOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="空气质量指数等级">
            <div class="slider-panel">
              <div class="aqi-summary">
                <span class="aqi-number">{{ form.aqi }}</span>
                <span class="aqi-level" :style="{ color: aqiLevel.color }">
                  {{ aqiLevel.text }}
                </span>
              </div>
              <el-slider
                v-model="form.aqi"
                :min="0"
                :max="500"
                :step="1"
                show-input
                class="aqi-slider"
              />
            </div>
          </el-form-item>

          <el-form-item label="反馈内容">
            <el-input
              v-model="form.content"
              class="field-control"
              type="textarea"
              :rows="5"
              maxlength="300"
              show-word-limit
              resize="none"
              placeholder="请描述附近空气情况，例如异味、扬尘、烟雾或其他观察到的问题"
            />
          </el-form-item>

          <el-button
            class="submit-button"
            type="primary"
            size="large"
            @click="submitFeedback"
          >
            <el-icon><Upload /></el-icon>
            提交反馈
          </el-button>
        </el-form>
      </el-main>

      <el-footer class="page-footer">
        <div class="footer-item">
          <el-icon><Location /></el-icon>
          <span>{{ selectedDistrictName || '未选择网格' }}</span>
        </div>
        <div class="footer-item">
          <el-icon><ChatLineRound /></el-icon>
          <span>反馈将用于空气质量巡查</span>
        </div>
      </el-footer>
    </el-container>
  </main>
</template>

<style scoped>
.feedback-page {
  min-height: 100vh;
  background:
    linear-gradient(135deg, rgba(64, 158, 255, 0.16), rgba(103, 194, 58, 0.12)),
    #f5f7fb;
  color: #1f2d3d;
}

.page-shell {
  width: min(100%, 760px);
  min-height: 100vh;
  margin: 0 auto;
  padding: 28px 20px;
}

.page-header {
  height: auto;
  padding: 16px 0 22px;
}

.eyebrow {
  margin: 0 0 8px;
  color: #409eff;
  font-size: 14px;
  font-weight: 700;
}

.page-header h1 {
  margin: 0;
  font-size: 30px;
  line-height: 1.25;
  font-weight: 800;
}

.page-main {
  padding: 0;
}

.feedback-form {
  padding: 24px;
  border: 1px solid #e4e7ed;
  border-radius: 8px;
  background: rgba(255, 255, 255, 0.94);
  box-shadow: 0 18px 50px rgba(31, 45, 61, 0.08);
}

.field-control {
  width: 100%;
}

.slider-panel {
  width: 100%;
  padding: 18px 18px 10px;
  border-radius: 8px;
  background: #f8fafc;
}

.aqi-summary {
  display: flex;
  align-items: baseline;
  justify-content: space-between;
  gap: 16px;
  margin-bottom: 10px;
}

.aqi-number {
  color: #1f2d3d;
  font-size: 32px;
  font-weight: 800;
  line-height: 1;
}

.aqi-level {
  font-size: 15px;
  font-weight: 700;
}

.aqi-slider {
  --el-slider-main-bg-color: #409eff;
}

.submit-button {
  width: 100%;
  margin-top: 6px;
  font-weight: 700;
}

.submit-button .el-icon {
  margin-right: 6px;
}

.page-footer {
  display: flex;
  height: auto;
  justify-content: space-between;
  gap: 12px;
  padding: 18px 0 0;
  color: #667085;
  font-size: 13px;
}

.footer-item {
  display: inline-flex;
  align-items: center;
  gap: 6px;
}

@media (max-width: 480px) {
  .page-shell {
    padding: 20px 14px;
  }

  .page-header {
    padding-bottom: 18px;
  }

  .page-header h1 {
    font-size: 24px;
  }

  .feedback-form {
    padding: 18px 14px;
  }

  .slider-panel {
    padding: 16px 12px 8px;
  }

  .aqi-slider :deep(.el-slider__runway.show-input) {
    margin-right: 0;
  }

  .aqi-slider :deep(.el-slider__input) {
    width: 96px;
    margin-top: 12px;
    margin-left: 0;
  }

  .aqi-slider :deep(.el-slider__runway.show-input + .el-slider__input) {
    display: block;
  }

  .page-footer {
    flex-direction: column;
  }
}
</style>
