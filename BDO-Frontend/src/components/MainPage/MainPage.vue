<script setup lang="ts">
import '@/assets/MainPage.css';

</script>

<template>

  <header>
    <div class="container">
      <nav>
        <ul>
          <div class="btn">
            <i class="fas fa-times close-btn"></i>
          </div>
          <li ><img id="personapicture" src="/src/assets/personaavatar.svg" alt="Avatar Picture" srcset=""> </li>
          <li>Max Muster</li>
        </ul>

        <div class="logo">
          <img src="/src/assets/bdologo.png" alt="" />
        </div>
      </nav>
    </div>

    <main>

    </main>
  </header>


</template>

<style scoped>


* {
  font-family: Poppins, serif;
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

body {
  min-height: 100vh;
  font-family: "poppins", sans-serif;
  position: relative;
}


header {
  padding: 0.5rem 0;
}


nav {
  display: flex;
  justify-content: space-between;
  align-items: center;

}

nav .logo {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: auto;
}

.logo img {
  width: 347px;
  height: 130px;
}

.logo span {
  font-size: 2rem;
}

nav ul {
  display: flex;
  align-items: center;
  list-style-type: none;
}

nav ul li {
  display: flex;
  margin: 0rem 0.2rem;
  align-items: center;
  padding: 0rem 0rem;
}

nav {
  width: 100%;
  text-decoration: none;
  font-size: 2.1rem;
  letter-spacing: 2.5px;
  color: black;

}

#personapicture{
  width: 100px;
}
</style>
