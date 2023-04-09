<script setup>
import HeaderComponent from '../components/commons/HeaderComponent.vue';
const components = {
  HeaderComponent,
};
</script>

<template>
    <div>
        <HeaderComponent />
          <RouterView />
        <footer class="text-center text-lg-start text-muted">
          <div class="text-center p-4">
              &copy; Copyright 2022 Livr'avenir
          </div>
      </footer>
      </div>
</template>


