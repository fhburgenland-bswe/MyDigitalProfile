<script setup lang="ts">
import '@/assets/MainPage.css';

</script>

<template>

  <header>
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
  grid-template-columns: min-content min-content min-content; /* Three columns with different widths */
  grid-template-rows: repeat(11, auto); /* 11 rows to accommodate all items */
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
