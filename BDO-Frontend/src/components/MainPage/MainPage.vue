<script setup lang="ts">
import '@/assets/MainPage.css';

import { ref } from 'vue';

const isMenuOpen = ref(false);

function toggleMenu() {
  isMenuOpen.value = !isMenuOpen.value;
}
</script>

<template>

  <header>
      <nav>
        <div class="mobile-navbar">
          <button :class="{ collapsed: !isMenuOpen }" @click="toggleMenu" class="hamburger-menu" aria-label="Toggle navigation">
            <span class="toggler-icon top-bar"></span>
            <span class="toggler-icon middle-bar"></span>
            <span class="toggler-icon bottom-bar"></span>
          </button>
          <div class="navbar-title">Max Muster</div>
          <div class="navbar-avatar">
            <img id="personapicture" src="/src/assets/personaavatar.svg" alt="Avatar Picture" srcset="">
          </div>
        </div>

        <ul v-show="isMenuOpen" style="position: absolute; background-color: #fff; width: 100%;">
          <div class="btn">
            <i class="fas fa-times close-btn"></i>
          </div>
          <li>Entry 1</li>
          <li>Entry 2</li>
        </ul>

        <div class="logo">
          <img src="/src/assets/bdologo.png" alt="" />
        </div>
      </nav>

  </header>
    <main>
      <div class="container">
        <div class="grid-item">Personalnummer</div>
        <div class="grid-item">1234567890</div>
        <div class="grid-item"></div>
      </div>

      <div class="container">
        <div class="grid-item">Vorname</div>
        <div class="grid-item">Max</div>
        <div class="grid-item"></div>
      </div>

      <div class="container">
        <div class="grid-item">Nachname</div>
        <div class="grid-item">Muster</div>
        <div class="grid-item"></div>
      </div>

      <div class="container">
        <div class="grid-item">Geburtsdatum</div>
        <div class="grid-item">01.01.2000</div>
        <div class="grid-item"></div>
      </div>

      <div class="container">
        <div class="grid-item">Standort</div>
        <div class="grid-item">Düsseldorf</div>
        <div class="grid-item"><img src="/src/assets/pencil.svg" alt="Pencil Icon" srcset=""></div>
      </div>

      <div class="container">
        <div class="grid-item">PLZ</div>
        <div class="grid-item">1010</div>
        <div class="grid-item"><img src="/src/assets/pencil.svg" alt="Pencil Icon" srcset=""></div>
      </div>

      <div class="container">
        <div class="grid-item">Ort</div>
        <div class="grid-item">Wien</div>
        <div class="grid-item"><img src="/src/assets/pencil.svg" alt="Pencil Icon" srcset=""></div>
      </div>

      <div class="container">
        <div class="grid-item">Straße</div>
        <div class="grid-item">Hauptstraße</div>
        <div class="grid-item"><img src="/src/assets/pencil.svg" alt="Pencil Icon" srcset=""></div>
      </div>

      <div class="container">
        <div class="grid-item">Hausnummer</div>
        <div class="grid-item">12/3/2</div>
        <div class="grid-item"><img src="/src/assets/pencil.svg" alt="Pencil Icon" srcset=""></div>
      </div>

      <div class="container">
        <div class="grid-item">Skills</div>
        <div class="grid-item">Java</div>
        <div class="grid-item"></div>
      </div>

      <div class="container">
        <div class="grid-item">Karrierelevel</div>
        <div class="grid-item">Junior</div>
        <div class="grid-item"></div>
      </div>
    </main>



</template>

<style scoped>

* {
  font-family: Poppins, serif;
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

main{
  margin: 100px auto;
  max-width: 600px;
  display: grid;
  grid-template-columns: min-content min-content min-content;
  grid-template-rows: repeat(11, auto);
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
  gap: 20px;
}

.container{
  display: contents;
}


.grid-item:nth-child(2) {
  text-align: right;
  margin-left: 40px;
}

.grid-item:nth-child(3) {
  justify-self: start;
  width: fit-content;
}


body {
  min-height: 100vh;
  margin: 0;
  padding: 0;
  font-family: "poppins", sans-serif;
  position: relative;
}


header {
  padding: 0.5rem 0;
  margin: 0;
}


nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0;
  padding: 0;
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
  margin: 0 0.2rem;
  align-items: center;
  padding: 0;
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


.mobile-navbar {
  display: none;
  background-color: #6200ee;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  width: 100%;
  margin: 0;
  box-sizing: border-box;
}

.mobile-navbar .hamburger-menu,
.mobile-navbar .navbar-title,
.mobile-navbar .navbar-avatar {
  display: flex;
  align-items: center;
}

nav ul {
  display: none; /* By default, the menu is hidden */
}

@media (max-width: 900px) {

  main{
    font-size: 1.2em;
  }

  .logo img{
    display: none;
  }


  nav ul {
    display: block; /* Make sure to set it to block or flex as per your design when visible */
    position: absolute; /* Optional: depending on your layout, you might want absolute positioning */
    top: 60px; /* Adjust based on your nav bar height */
    left: 0;
    right: 0;
    background-color: #f8f9fa; /* Light background for the dropdown */
    padding: 20px; /* Padding for aesthetics */
    box-shadow: 0 8px 16px rgba(0,0,0,0.1); /* Optional: for better visibility */
    z-index: 1000; /* Make sure it's above other content */
  }

  .mobile-navbar {
    display: flex; /* Ensures the mobile navbar is visible */
  }

  .navbar-toggler.collapsed .top-bar,
  .navbar-toggler.collapsed .bottom-bar {
    transform: rotate(0deg); /* Reset rotation when collapsed */
  }

  .navbar-toggler.collapsed .middle-bar {
    opacity: 1; /* Make middle bar visible when collapsed */
  }

  .navbar-title {
    font-size: 1.5rem;
  }

  #personapicture{
    width: 50px;
  }
}

.mobile-navbar .hamburger-menu {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  width: 30px; /* Width of the button */
  height: 25px; /* Height of the button */
  background: transparent;
  border: none;
  cursor: pointer;
  padding: 0;
  box-shadow: none;
  outline: none;
}

.toggler-icon {
  display: block;
  width: 100%;
  height: 3px;
  background-color: #fff; /* Change this as needed */
  transition: all 0.3s ease-in-out;
}

/* Animation for the toggle */
.hamburger-menu:not(.collapsed) .top-bar {
  transform: rotate(45deg);
  transform-origin: 10% 10%;
}

.hamburger-menu:not(.collapsed) .middle-bar {
  opacity: 0;
}

.hamburger-menu:not(.collapsed) .bottom-bar {
  transform: rotate(-45deg);
  transform-origin: 10% 90%;
}


</style>
