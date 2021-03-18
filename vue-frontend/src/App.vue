<template>
   <div>
      <router-view></router-view>
   </div>
</template>

<script>
   import AppTemplate from './components/AppTemplate.vue';

   export default {
      name: 'App',
      components: {
         appTemplate: AppTemplate,
      }
   }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  @import '../src/assets/css/style.css';
</style>
