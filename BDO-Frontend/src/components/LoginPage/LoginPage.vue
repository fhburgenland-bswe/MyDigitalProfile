<script setup lang="ts">
import '@/assets/LoginPage.css';

</script>

<template>
  
  <div class="LoginContainer">
    <div id="bdo-logo"><img src="/src/assets/bdologo.png"></div>
    <div class="logintext">In Account einloggen</div>

    <form action="" method="post">
      <label for="email">E-Mail</label>
      <div class="input-box"><input type="email" name="" id="" placeholder="max.muster@bdo.de" required></div>
      <label for="password">Passwort</label>
      <div class="input-box"><input type="password" name="" id="" placeholder="Enter your password" required></div>
      <button type="submit" value="Login">Login</button>
    </form>

  </div>

</template>

<style scoped>

</style>
