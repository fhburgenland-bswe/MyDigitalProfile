<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { getUserData, updateUserData, logout } from '@/services/user.service';
import ModalComponent from '@/components/Modals/UpdateDataModal.vue';
import { useRouter } from 'vue-router';
import { toast } from "vue3-toastify";
import CreateUserModal from '@/components/Modals/CreateUserModal.vue';

const isMenuOpen = ref(false);
const isModalVisible = ref(false);
const editableField = ref('');
const isCreateUserModalVisible = ref(false);
const avatarMenuOpen = ref(false);
const router = useRouter();

function toggleMenu() {
  isMenuOpen.value = !isMenuOpen.value;
}

function toggleAvatarMenu() {
  avatarMenuOpen.value = !avatarMenuOpen.value;
}

function handleLogout() {
  logout();
  router.push({ path: '/', query: { loggedOut: 'true' } });
}

function showModal(field) {
  editableField.value = field;
  isModalVisible.value = true;
}

function showCreateUserModal() {
  isCreateUserModalVisible.value = true;
  isMenuOpen.value = false;
}

function closeCreateUserModal() {
  isCreateUserModalVisible.value = false;
}

const userData = ref<any>(null);
const error = ref(null);

onMounted(async () => {
  const userId = localStorage.getItem('userId');
  const username = localStorage.getItem('username');

  if (!userId || !username) {
    handleLogout();
    return;
  }

  try {
    const data = await getUserData();
    userData.value = data;
  } catch (err) {
    console.error('Error fetching user data:', err);
    error.value = 'Error fetching user data: ' + err.message;
    console.log('Error set in data:', error.value);
  }
});

async function handleUpdate({ field, value }) {
  try {
    await updateUserData(userData.value.id, { [field]: value });
    userData.value[field] = value;
    toast("Daten erfolgreich geändert", {
      theme: "colored",
      type: "success",
      position: "bottom-center",
      autoClose: 2000,
      dangerouslyHTMLString: true,
    });
    console.log('Data updated successfully');
  } catch (error) {
    console.error('Error updating user data:', error);
  }
  isModalVisible.value = false;
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
        <div class="navbar-title">{{ userData ? userData.vorname : '' }} {{ userData ? userData.nachname : '' }}</div>
        <div class="navbar-avatar">
          <img id="personapicture" src="@/assets/personaavatar.svg" alt="Avatar Picture" srcset="">
        </div>
      </div>

      <ul class="mobile-menu" v-show="isMenuOpen" style="position: absolute; background-color: #fff; width: 100%;">
        <div class="btn">
          <i class="fas fa-times close-btn"></i>
        </div>
        <li @click="showCreateUserModal">Neuen Benutzer anlegen</li>
        <li @click="handleLogout">Logout</li>
      </ul>

      <div class="desktop-navbar">
        <div style="cursor: pointer; position: relative;">
          <img @click="toggleAvatarMenu" id="personapicture" src="@/assets/personaavatar.svg" alt="Avatar Picture">
          <ul v-if="avatarMenuOpen" class="avatar-dropdown-menu">
            <li @click="showCreateUserModal">Neuen Benutzer anlegen</li>
            <li @click="handleLogout">Logout</li>
          </ul>
        </div>
        <div class="navbar-title">{{ userData ? userData.vorname : '' }} {{ userData ? userData.nachname : '' }}</div>
      </div>

      <div class="logo">
        <img src="@/assets/bdologo.png" alt="" />
      </div>

      <div class="emptydiv">

      </div>
    </nav>

  </header>
  <main>
    <div v-if="error">{{ error }}</div>
    <div v-if="userData" class="container">
      <div class="grid-item">Personalnummer</div>
      <div class="grid-item">{{ userData.pnr }}</div>
      <div class="grid-item"></div>
    </div>
    <div v-else>Loading...</div>
    <div v-if="userData" class="container">
      <div class="grid-item">Vorname</div>
      <div class="grid-item">{{ userData.vorname }}</div>
      <div class="grid-item"></div>
    </div>
    <div v-if="userData" class="container">
      <div class="grid-item">Nachname</div>
      <div class="grid-item">{{ userData.nachname }}</div>
      <div class="grid-item"></div>
    </div>
    <div v-if="userData" class="container">
      <div class="grid-item">Geburtsdatum</div>
      <div class="grid-item">{{ userData.geburtsdatum }}</div>
      <div class="grid-item"></div>
    </div>
    <div v-if="userData" class="container">
      <div class="grid-item">Standort</div>
      <div class="grid-item">{{ userData.standort }}</div>
      <div class="grid-item ">
        <img src="@/assets/pencil.svg" alt="Pencil Icon" @click="showModal('standort')" class="editable-icon">
      </div>
    </div>
    <div v-if="userData" class="container">
      <div class="grid-item">PLZ</div>
      <div class="grid-item">{{ userData.plz }}</div>
      <div class="grid-item">
        <img src="@/assets/pencil.svg" alt="Pencil Icon" @click="showModal('plz')" class="editable-icon">
      </div>
    </div>
    <div v-if="userData" class="container">
      <div class="grid-item">Ort</div>
      <div class="grid-item">{{ userData.ort }}</div>
      <div class="grid-item">
        <img src="@/assets/pencil.svg" alt="Pencil Icon" @click="showModal('ort')" class="editable-icon">
      </div>
    </div>
    <div v-if="userData" class="container">
      <div class="grid-item">Straße</div>
      <div class="grid-item">{{ userData.strasse }}</div>
      <div class="grid-item">
        <img src="@/assets/pencil.svg" alt="Pencil Icon" @click="showModal('strasse')" class="editable-icon">
      </div>
    </div>
    <div v-if="userData" class="container">
      <div class="grid-item">Hausnummer</div>
      <div class="grid-item">{{ userData.hausNr }}</div>
      <div class="grid-item">
        <img src="@/assets/pencil.svg" alt="Pencil Icon" @click="showModal('hausnummer')" class="editable-icon">
      </div>
    </div>
    <div v-if="userData" class="container">
      <div class="grid-item">Skills</div>
      <div class="grid-item">{{ userData.skills }}</div>
      <div class="grid-item"></div>
    </div>
    <div v-if="userData" class="container">
      <div class="grid-item">Karrierelevel</div>
      <div class="grid-item">{{ userData.karriereLevel }}</div>
      <div class="grid-item"></div>
    </div>
  </main>
  <ModalComponent :field="editableField" :isVisible="isModalVisible" @update="handleUpdate" @close="isModalVisible = false" />
  <CreateUserModal :isVisible="isCreateUserModalVisible" @close="closeCreateUserModal" />
</template>

<style scoped>


#personapicture:hover {
  box-shadow: 0 2px 8px rgba(0,0,0,0.2);
}

.avatar-dropdown-menu {
  display: flex;
  flex-direction: column;
  position: absolute;
  background-color: #ffffff;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
  border-radius: 8px;
  min-width: 300px;
  width: fit-content;
  transition: visibility 0.3s, opacity 0.3s ease-in-out;
  visibility: hidden;
  margin-top: -10px;
}

.avatar-dropdown-menu li {
  padding: 12px 16px;
  width: 100%;
  cursor: pointer;
  list-style-type: none;
  transition: background-color 0.3s ease;
}

.avatar-dropdown-menu li:hover {
  background-color: #f0f0f0;
}

.avatar-dropdown-menu li:not(:last-child) {
  border-bottom: 1px solid #eeeeee;
}

.desktop-navbar img:hover + .avatar-dropdown-menu,
.avatar-dropdown-menu:hover {
  visibility: visible;
  opacity: 1;
}


.desktop-navbar{
  float: left;
  display: flex;
  align-items: center;
  position: relative;
  width: 30%;
}

.emptydiv{
  width: 30%;
}



.editable-icon {
  cursor: pointer;
  transition: transform 0.3s ease;
}

.editable-icon:hover {
  transform: scale(1.1);
  filter: brightness(0) saturate(100%) invert(27%) sepia(99%) saturate(6357%) hue-rotate(191deg) brightness(91%) contrast(92%);

}

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
  width: 100%;
}


nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0;
  padding: 0 0;
  width: 100%;
  text-decoration: none;
  font-size: 2.1rem;
  letter-spacing: 2px;
  color: black;
}


nav .logo {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: auto;
  width: 30%;
}


.logo img {
  width: 347px;
  height: 130px;
}

.logo span {
  font-size: 2rem;
}

nav ul {
  margin-top: 10px;
  display: flex;
  align-items: center;
  list-style-type: none;
  width: 100%;
}

nav ul li {
  display: flex;
  margin: 0 0.2rem;
  align-items: center;
  padding: 0.5rem 0;
  font-size: 1rem;
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










@media (max-width: 900px) {

  .emptydiv{
    display: none;
  }
  nav .logo{
    display: none;
  }

  .desktop-navbar{
    display: none;
  }

  .navbar-title-desktop,
  .navbar-avatar{
    display: none;
  }

  main{
    font-size: 1.2em;
  }

  .logo img{
    display: none;
  }


  nav ul {
    display: block;
    position: absolute;
    top: 60px;
    left: 0;
    right: 0;
    background-color: #f8f9fa;
    padding: 20px;
    box-shadow: 0 8px 16px rgba(0,0,0,0.1);
    width: 100%;
    z-index: 1000;
  }

  .mobile-navbar {
    display: flex;
    width: 100%;
  }

  .navbar-toggler.collapsed .top-bar,
  .navbar-toggler.collapsed .bottom-bar {
    transform: rotate(0deg);
  }

  .navbar-toggler.collapsed .middle-bar {
    opacity: 1;
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
  width: 30px;
  height: 25px;
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
  background-color: #fff;
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

.mobile-menu{
  left:0;
  right: 0;
  padding: 0;
}

.mobile-menu li{
  margin: 0;
  cursor: pointer;
  padding: 15px;
}
.mobile-menu li:hover {
  background-color: #f0f0f0;
}

</style>