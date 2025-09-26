<script setup lang="ts">
import { Layout } from 'ant-design-vue'

const { Footer } = Layout
</script>

<template>
  <Footer class="footer">
    <div class="footer-content">
      <div class="copyright">
        © 2025 网站名称 版权所有
      </div>
      <div class="links">
        <a href="#" class="link">关于我们</a>
        <a href="#" class="link">隐私政策</a>
        <a href="#" class="link">使用条款</a>
        <a href="#" class="link">联系我们</a>
      </div>
    </div>
  </Footer>
</template>

<style scoped>
.footer {
  background: #f0f2f5;
  padding: 24px 0;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 100;
  height: 60px;
  display: flex;
  align-items: center;
}

.footer-content {
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
}

.copyright {
  color: rgba(0, 0, 0, 0.45);
  font-size: 14px;
}

.links {
  display: flex;
  gap: 24px;
}

.link {
  color: rgba(0, 0, 0, 0.45);
  font-size: 14px;
  text-decoration: none;
  transition: color 0.3s;
}

.link:hover {
  color: #1890ff;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .footer {
    height: auto;
    padding: 16px 0;
  }
  
  .footer-content {
    flex-direction: column;
    gap: 12px;
    text-align: center;
  }
  
  .links {
    justify-content: center;
    gap: 16px;
  }
}
</style>