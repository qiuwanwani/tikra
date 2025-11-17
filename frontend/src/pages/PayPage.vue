<template>
  <div class="payment-container">
    <div class="payment-layout">
      <div class="layout-left">
        <div class="payment-summary-card">
          <div class="card-title">赞助支持</div>
          <div class="price-row">
            <span
              >感谢您考虑赞助本项目。<br /><br />
              您的赞助将帮助我们持续提供高质量的系统解决方案。<br /><br />
              所有的赞助仅用于项目的开发和维护。</span
            >
          </div>
        </div>
        <div class="payment-summary-card">
          <div class="card-title">赞助名单</div>
          <div class="price-row">
            <span>感谢以下赞助者对项目的支持：</span>
          </div>
          <div class="sponsors-table-container">
            <table class="sponsors-table">
              <thead>
                <tr>
                  <th>赞助者</th>
                  <th>金额</th>
                  <th>日期</th>
                  <th>留言</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(sponsor, index) in sponsors" :key="index">
                  <td>{{ sponsor.name }}</td>
                  <td>{{ sponsor.amount }}</td>
                  <td>{{ sponsor.date }}</td>
                  <td>{{ sponsor.message || '-' }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="layout-right">
        <div class="payment-methods-card">
          <h3 class="card-title">赞助方式</h3>

          <div class="payment-methods">
            <div
              v-for="method in paymentMethods"
              :key="method.value"
              class="payment-method-option"
              :class="{ active: paymentMethod === method.value }"
              :data-method="method.value"
              @click="changePaymentMethod(method.value)"
            >
              <div class="method-radio">
                <div class="radio-inner" v-if="paymentMethod === method.value"></div>
              </div>
              <div class="method-info">
                <div class="method-name">{{ method.label }}</div>
                <div class="method-desc">
                  {{ method.value === 'alipay' ? '支付宝扫码支付' : '微信扫码支付' }}
                </div>
              </div>
              <div class="method-icon">
                <component :is="method.icon" />
              </div>
            </div>
          </div>

          <!-- 支付二维码显示区域 -->
          <div class="payment-qrcode-container">
            <!-- 加载中动画 - 使用Ant Design的Spin组件 -->
            <div v-if="isImageLoading">
              <Spin size="large" tip="加载中..." />
            </div>
            <!-- 实际二维码图片 -->
            <div v-else-if="paymentMethod === 'wechat'" class="qrcode-wrapper">
              <img src="/wechatpay.jpg" alt="微信支付二维码" class="payment-qrcode" />
            </div>
            <div v-else-if="paymentMethod === 'alipay'" class="qrcode-wrapper">
              <img src="/alipay.jpg" alt="支付宝二维码" class="payment-qrcode" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { WechatOutlined, AlipayOutlined } from '@ant-design/icons-vue'
import { Spin } from 'ant-design-vue'

// 支付方式状态 - 默认不选择任何支付方式
const paymentMethod = ref('')
const isImageLoading = ref(false)
const paymentMethods = [
  { value: 'wechat', label: '微信支付', icon: WechatOutlined },
  { value: 'alipay', label: '支付宝', icon: AlipayOutlined },
]

// 赞助名单数据
const sponsors = ref([])

// 切换支付方式的方法
const changePaymentMethod = (value: string) => {
  // 设置为加载中状态
  isImageLoading.value = true
  // 切换支付方式
  paymentMethod.value = value

  // 添加1秒延迟后再显示图片
  setTimeout(() => {
    isImageLoading.value = false
  }, 500)
}

// 从JSON文件加载赞助数据
onMounted(async () => {
  try {
    const response = await fetch('/sponsors.json')
    if (response.ok) {
      sponsors.value = await response.json()
    }
  } catch (error) {
    console.error('Failed to load sponsors data:', error)
  }
})
</script>

<style scoped>
.payment-container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 48px 32px;
  background-color: #fff;
  min-height: 800px;
}

.payment-layout {
  display: flex;
  gap: 40px;
  align-items: stretch;
}

.layout-left {
  flex: 3;
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.layout-right {
  flex: 2;
  display: flex;
  flex-direction: column;
}

.payment-summary-card,
.payment-methods-card {
  background: white;
  border-radius: 12px;
  padding: 30px;
  border: 1px solid #f0f0f0;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.08);
}

.payment-methods-card {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.card-title {
  font-size: 24px;
  font-weight: 600;
  color: #1a1a1a;
  margin: 0 0 24px 0;
  position: relative;
  padding-bottom: 12px;
  border-bottom: 1px solid #f0f0f0;
}

.payment-method-option {
  display: flex;
  align-items: center;
  padding: 18px 20px;
  border-radius: 12px;
  background-color: #eeeeee;
  margin-bottom: 24px;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  border: 1px solid transparent;
}

.payment-method-option:last-child {
  margin-bottom: 0;
}

.payment-method-option.active {
  background-color: #f0f7ff;
  border: 1px solid #1890ff;
}

.payment-method-option[data-method='wechat'].active {
  background-color: #f6ffed;
  border: 1px solid #52c41a;
}

.payment-method-option:hover {
  background-color: #f5f5f5;
  border: 1px solid #1890ff;
}

.payment-method-option[data-method='wechat']:hover {
  background-color: #f5f5f5;
  border: 1px solid #52c41a;
}

.payment-method-option.active:hover {
  background-color: #e6f3ff;
  border: 1px solid #1890ff;
}

.payment-method-option[data-method='wechat'].active:hover {
  background-color: #e8f7e4;
  border: 1px solid #52c41a;
}

.method-radio {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  border: 2px solid #d9d9d9;
  margin-right: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.payment-method-option.active .method-radio {
  border-color: #1890ff;
}

.payment-method-option[data-method='wechat'].active .method-radio {
  border-color: #52c41a;
}

.radio-inner {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: #1890ff;
}

.payment-method-option[data-method='wechat'].active .radio-inner {
  background-color: #52c41a;
}

.method-info {
  flex-grow: 1;
}

.method-name {
  font-weight: 600;
  font-size: 16px;
  color: #262626;
  margin-bottom: 4px;
}

.method-desc {
  font-size: 14px;
  color: #737373;
}

.method-icon {
  margin-left: 16px;
  font-size: 28px;
  color: #8c8c8c;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s ease;
}

.payment-method-option.active .method-icon {
  color: #1890ff;
  transform: scale(1.1);
}

.payment-method-option[data-method='wechat'].active .method-icon {
  color: #52c41a;
}

.payment-method-option:hover .method-icon {
  color: #40a9ff;
}

.payment-method-option[data-method='wechat']:hover .method-icon {
  color: #73d13d;
}

/* 赞助名单表格样式 */
.sponsors-table-container {
  width: 100%;
  overflow-x: auto;
  margin-top: 16px;
}

.sponsors-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 14px;
}

.sponsors-table th,
.sponsors-table td {
  padding: 12px 16px;
  text-align: left;
  border-bottom: 1px solid #f0f0f0;
}

.sponsors-table th {
  background-color: #fafafa;
  font-weight: 600;
  white-space: nowrap;
}

.sponsors-table tbody tr:hover {
  background-color: #fafafa;
}

.sponsors-table td:first-child {
  font-weight: 500;
}

.sponsors-table td:nth-child(2) {
  font-weight: 500;
}

.sponsors-table td:nth-child(3) {
  white-space: nowrap;
}

.sponsors-table td:nth-child(4) {
  max-width: 180px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
@media (max-width: 992px) {
  .payment-layout {
    flex-direction: column;
  }
  .layout-left,
  .layout-right {
    flex: none;
  }
  .payment-content {
    padding: 30px;
  }
}

@media (max-width: 768px) {
  .payment-container {
    padding: 30px 20px;
  }
  .payment-content {
    padding: 24px;
  }

  .sponsors-table th,
  .sponsors-table td {
    padding: 10px 12px;
    font-size: 13px;
  }
}

/* 支付二维码样式 */
.payment-qrcode-container {
  margin-top: 24px;
  border-top: 1px solid #f0f0f0;
  display: flex;
  justify-content: center;
  position: relative;
  align-items: center;
}

.qrcode-wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #ffffff;
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
    border: 1px solid #f0f0f0;
  }

  /* 未选择支付方式时的提示样式 */
  .no-payment-selected {
    text-align: center;
    color: #8c8c8c;
    font-size: 16px;
    padding: 20px;
  }

.payment-qrcode {
  max-width: 250px;
  max-height: 250px;
  width: 100%;
  height: auto;
  display: block;
}

@media (max-width: 992px) {
  .payment-layout {
    flex-direction: column;
  }
  .layout-left,
  .layout-right {
    flex: none;
  }
  .payment-content {
    padding: 30px;
  }
}

@media (max-width: 768px) {
  .payment-container {
    padding: 30px 20px;
  }
  .payment-content {
    padding: 24px;
  }

  .sponsors-table th,
  .sponsors-table td {
    padding: 10px 12px;
    font-size: 13px;
  }

  .payment-qrcode {
    max-width: 200px;
    max-height: 200px;
  }
}

@media (max-width: 576px) {
  .payment-header {
    text-align: center;
  }
  .page-title {
    font-size: 24px;
    display: inline-block;
  }
  .payment-container {
    padding: 20px 16px;
  }
  .payment-content {
    padding: 20px;
    border-radius: 12px;
  }

  .sponsors-table-container {
    margin-left: -20px;
    margin-right: -20px;
    width: calc(100% + 40px);
    padding: 0 20px;
  }

  .sponsors-table th,
  .sponsors-table td {
    padding: 8px 10px;
    font-size: 12px;
  }

  .payment-qrcode {
    max-width: 180px;
    max-height: 180px;
  }

  .qrcode-wrapper {
    padding: 15px;
  }
}
</style>
