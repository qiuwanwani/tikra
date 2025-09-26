<script setup lang="ts">
import { Layout } from 'ant-design-vue'
import GlobalHeader from '../components/GlobalHeader.vue'
import GlobalFooter from '../components/GlobalFooter.vue'

const { Content } = Layout
</script>

<template>
  <Layout class="layout">
    <!-- 头部导航 -->
    <GlobalHeader />

    <!-- 内容区域 -->
    <Content class="content">
      <div class="content-wrapper">
        <slot></slot>
      </div>
    </Content>

    <!-- 底部版权 -->
    <GlobalFooter />
  </Layout>
</template>

<style scoped>
.layout {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.content {
  flex: 1;
  padding: 24px;
  margin-bottom: 60px; /* 为底部留出空间 */
  background: #fff;
}

.content-wrapper {
  max-width: 1200px;
  margin: 0 auto;
  width: 100%;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .content {
    padding: 16px;
    margin-bottom: 80px; /* 移动端为底部留出更多空间 */
  }
}

/* 确保内容不会被固定的底部遮挡 */
@media (min-width: 769px) {
  .content {
    margin-bottom: 60px;
  }
}
</style>
