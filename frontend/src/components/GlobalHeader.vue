<script setup lang="ts">
import { h, computed } from 'vue'
import { Layout, Menu, Button } from 'ant-design-vue'
import { AppstoreOutlined, MenuFoldOutlined, MenuUnfoldOutlined } from '@ant-design/icons-vue'
import type { MenuProps } from 'ant-design-vue'
import { useRouter, useRoute } from 'vue-router'

const { Header } = Layout
const router = useRouter()
const route = useRoute()

// 根据当前路由设置选中的菜单项
const selectedKeys = computed(() => {
  const path = route.path
  if (path === '/') return ['1']
  if (path === '/about') return ['2']
  return []
})

// 菜单项配置
const menuItems: MenuProps['items'] = [
  {
    key: '1',
    icon: () => h(AppstoreOutlined),
    label: '首页',
    onClick: () => router.push('/')
  },
  {
    key: '2',
    icon: () => h(MenuFoldOutlined),
    label: '关于',
    onClick: () => router.push('/about')
  },
  {
    key: '3',
    icon: () => h(MenuUnfoldOutlined),
    label: '功能',
    children: [
      {
        key: '3-1',
        label: '功能一'
      },
      {
        key: '3-2',
        label: '功能二'
      }
    ]
  }
]
</script>

<template>
  <Header class="header">
    <div class="header-left">
      <a href="#" class="logo">
        <img src="@/assets/logo.svg" alt="Logo" />
        <span class="site-title">网站标题</span>
      </a>
      <Menu
        mode="horizontal"
        :items="menuItems"
        :selectedKeys="selectedKeys"
        class="header-menu"
        theme="light"
      />
    </div>
    <div class="header-right">
      <!-- 暂时用登录按钮代替用户头像和昵称 -->
      <Button type="primary">登录</Button>
      <!-- 未来可以替换为以下代码： -->
      <!-- <Dropdown :menu="{ items: userMenu }" placement="bottomRight">
        <div class="user-info" @click.stop>
          <Avatar icon={<UserOutlined />} />
          <span class="nickname">用户名</span>
        </div>
      </Dropdown> -->
    </div>
  </Header>
</template>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 24px;
  background: #ffffff;
  height: 64px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.header-left {
  display: flex;
  align-items: center;
  flex: 1;
}

.logo {
  display: flex;
  align-items: center;
  margin-right: 24px;
  color: #1890ff;
  text-decoration: none;
}

.logo img {
  height: 32px;
  width: 32px;
  margin-right: 8px;
}

.site-title {
  font-size: 18px;
  font-weight: 600;
}

.header-menu {
  flex: 1;
}

.header-right {
  display: flex;
  align-items: center;
}

/* 确保按钮在白色背景上显示正常 */
:deep(.ant-btn-primary) {
  background-color: #1890ff;
}

/* 移除菜单栏下方的灰色边框线 */
:deep(.ant-menu) {
  border-bottom: none !important;
}

.user-info {
  display: flex;
  align-items: center;
  color: white;
  cursor: pointer;
}

.nickname {
  margin-left: 8px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .header {
    padding: 0 16px;
  }
  
  .site-title {
    display: none;
  }
  
  .header-menu {
    flex: none;
  }
}
</style>