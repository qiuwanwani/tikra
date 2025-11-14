<template>
  <BasicLayout />
</template>

<script setup lang="ts">
import BasicLayout from './layout/BasicLayout.vue'
import { useLoginUserStore } from './stores/loginUser'

const loginUserStore = useLoginUserStore()
loginUserStore.fetchLoginUser()
</script>
