<script setup lang="ts">
import '@/assets/MainPage.css';

</script>

<template>
  <div class="main-page-wrapper">
    <div class="header-wrapper">
      <div class="Headercontainer">
        <div class="persona">
          <div class="profilepic"><img id="personapic" src="/src/assets/personaavatar.svg"></div>
          <div class="username">Max Muster</div>
        </div>
        <div class="logo-wrapper">
          <div id="bdo-logo"><img src="/src/assets/bdologo.png"></div>
        </div>
      </div>
    </div>

    <div class="grid-wrapper">

      <div class="grid-container">
        <div class="grid-item">Personalnummer</div>
        <div class="grid-item">1234567890</div>
        <div class="grid-item"> </div>
      </div>

      <div class="grid-container">
        <div class="grid-item">Vorname</div>
        <div class="grid-item">Max</div>
        <div class="grid-item"> </div>
      </div>

      <div class="grid-container">
        <div class="grid-item">Nachname</div>
        <div class="grid-item">Muster</div>
        <div class="grid-item"> </div>
      </div>

      <div class="grid-container">
        <div class="grid-item">Geburtsdatum</div>
        <div class="grid-item">01.01.2000</div>
        <div class="grid-item"> </div>
      </div>

      <div class="grid-container">
        <div class="grid-item">Standort</div>
        <div class="grid-item">Düsseldorf</div>
        <div class="grid-item"> <img id="pencil" src="/src/assets/pencil.svg"> </div>
      </div>

      <div class="grid-container">
        <div class="grid-item">PLZ</div>
        <div class="grid-item">1010</div>
        <div class="grid-item"> <img id="pencil" src="/src/assets/pencil.svg"> </div>
      </div>

      <div class="grid-container">
        <div class="grid-item">Ort</div>
        <div class="grid-item">Wien</div>
        <div class="grid-item"> <img id="pencil" src="/src/assets/pencil.svg"> </div>
      </div>

      <div class="grid-container">
        <div class="grid-item">Straße</div>
        <div class="grid-item">Hauptstraße</div>
        <div class="grid-item"> <img id="pencil" src="/src/assets/pencil.svg"> </div>
      </div>

      <div class="grid-container">
        <div class="grid-item">Hausnummer</div>
        <div class="grid-item">12/3/2</div>
        <div class="grid-item"> <img id="pencil" src="/src/assets/pencil.svg"> </div>
      </div>

      <div class="grid-container">
        <div class="grid-item">Skills</div>
        <div class="grid-item">Java, ...</div>
        <div class="grid-item"> </div>
      </div>

      <div class="grid-container">
        <div class="grid-item">Karrierelevel</div>
        <div class="grid-item">Junior</div>
        <div class="grid-item"> </div>
      </div>
    </div>
  </div>

</template>

<style scoped>
  .main-page-wrapper{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    font-family: Poppins, serif;
    }

  #bdo-logo img{
    width: 347px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .header-wrapper{
    display: flex;
    width: 100%;
  }
  .Headercontainer {
    display: flex;
    width: 1080px;
    align-items: center;
    justify-content: flex-start;

  }

  .persona{
    flex-grow: 1;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .logo-wrapper{
  align-items: center;
  }

  .username{
    font-size:30px;
  }

  #personapic{
    width: 70px;
    height: 70px;
  }

  .grid-wrapper{
    margin-top: 50px;
    font-size: 40px;
  }

  .grid-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr); /* adjust as needed */
    height: 50px;
  }

  .grid-item {
    display: flex;
    align-items: center;
    padding: 10px;
  }

  .grid-container > .grid-item:nth-child(2) {
    justify-content: flex-end;
  }

</style>