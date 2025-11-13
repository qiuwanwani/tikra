<script setup lang="ts">
import { RouterView } from 'vue-router'
import BasicLayout from './layout/BasicLayout.vue'
import { healthCheck } from '@/api/healthController.ts'

healthCheck().then((res) => {
  console.log(res)
})
</script>

<template>
  <BasicLayout>
    <RouterView />
  </BasicLayout>
</template>
