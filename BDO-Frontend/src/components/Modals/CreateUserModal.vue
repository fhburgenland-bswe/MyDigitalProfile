<script setup>
import { ref, defineEmits } from 'vue';
import { createUser } from '@/services/user.service';

const emits = defineEmits(['close']);
const { isVisible, close } = defineProps({
  isVisible: Boolean,
  close: Function
});
const newUser = ref({
  email: '',
  password: '',
  personalnummer: '',
  vorname: '',
  nachname: '',
  geburtsdatum: '',
  standort: '',
  plz: '',
  ort: '',
  strasse: '',
  hausnummer: '',
  skills: '',
  karrierelevel: ''
});

const errorMessages = {
  personalnummer: "Die Personalnummer darf nur Zahlen enthalten.",
  vorname: "Der Vorname darf nur Buchstaben enthalten.",
  nachname: "Der Nachname darf nur Buchstaben enthalten.",
  geburtsdatum: "Das Geburtsdatum muss im Format TT.MM.JJJJ sein.",
  standort: "Der Standort darf nur Buchstaben enthalten.",
  plz: "Die PLZ darf nur Zahlen enthalten.",
  ort: "Der Ort darf nur Buchstaben enthalten.",
  strasse: "Die Straße darf nur Buchstaben enthalten.",
  hausnummer: "Die Hausnummer darf Buchstaben, Zahlen und spezielle Zeichen wie / und . enthalten.",
  skills: "Die Skills dürfen nur Buchstaben, Zahlen und spezielle Zeichen wie / und . enthalten.",
  karrierelevel: "Das Karrierelevel darf nur Buchstaben enthalten."
};

const errors = ref({});

const patterns = {
  personalnummer: /^\d+$/,
  vorname: /^[a-zA-ZäöüßÄÖÜ]+$/,
  nachname: /^[a-zA-ZäöüßÄÖÜ]+$/,
  standort: /^[a-zA-ZäöüßÄÖÜ\s]+$/,
  plz: /^\d+$/,
  strasse: /^[a-zA-ZäöüßÄÖÜ\s]+$/,
  hausnummer: /^[0-9A-Za-zäöüßÄÖÜ\s\/.-]*$/,
  skills: /^[0-9A-Za-zäöüßÄÖÜ\s\/.-]*$/,
  karrierelevel: /^[a-zA-ZäöüßÄÖÜ]+$/
};

function validateField(field, value) {
  if (patterns[field] && !patterns[field].test(value)) {
    errors.value[field] = errorMessages[field] || 'Invalid input.';
    return false;
  }
  errors.value[field] = ''; // Clear error
  return true;
}

function closeModal() {
  newUser.value = {
    email: '',
    password: '',
    personalnummer: '',
    vorname: '',
    nachname: '',
    geburtsdatum: '',
    standort: '',
    plz: '',
    ort: '',
    strasse: '',
    hausnummer: '',
    skills: '',
    karrierelevel: ''
  };
  errors.value = {};
  emits('close');
}


function submitNewUser() {
  const isValid = Object.keys(newUser.value).every(key => validateField(key, newUser.value[key]));
  if (isValid) {
    createUser(newUser.value).then(() => {
      alert("Benutzer wurde erfolgreich angelegt");
      closeModal();
    }).catch(error => {

    });
  } else {
    alert("Es sind noch Fehler im Formular vorhanden. Bitte überprüfen Sie Ihre Eingaben.");
  }
}


function formatLabel(field) {
  return field.charAt(0).toUpperCase() + field.slice(1).replace(/([A-Z])/g, ' $1').trim();
}

function inputType(field) {
  if (field === 'email') return 'email';
  if (field === 'password') return 'password';
  if (field === 'geburtsdatum') return 'date';
  return 'text';
}
</script>

<template>
  <div v-if="isVisible" class="modal">
    <div class="modal-content">
      <h2>Neuen Benutzer anlegen</h2>
      <form @submit.prevent="submitNewUser" class="modal-form">
        <div class="input-group" v-for="(value, key) in newUser" :key="key">
          <label :for="key">{{ formatLabel(key) }}</label>
          <input v-model="newUser[key]" :type="inputType(key)" :id="key" :name="key" required>
          <span v-if="errors[key]" class="error-message">{{ errors[key] }}</span>
        </div>
        <div class="button-group">
          <button type="button" class="cancel-btn" @click="closeModal">Abbrechen</button>
          <button type="submit" class="save-btn">Speichern</button>
        </div>
      </form>
    </div>
  </div>
</template>



<style scoped>

input[type="date"] {
  width: 100%;
  padding: 8px;
  border: 2px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  transition: border-color 0.3s;
  appearance: none;
  background-position: right 10px center;
  background-repeat: no-repeat;
  cursor: pointer;
}

input[type="date"]:focus {
  border-color: #6200ee;
}

.error-message {
  color: red;
  font-size: 0.8em;
}

label{
  display: block;
  margin: 10px 0
}

.modal {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 10;
}

.modal-content {
  background-color: #fff;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
  width: 90%;
  max-width: 500px;
  max-height: 80vh;
  overflow-y: auto;
  animation: modalFadeIn 0.3s ease-out;
}


@keyframes modalFadeIn {
  from { transform: translateY(-50px); opacity: 0; }
  to { transform: translateY(0); opacity: 1; }
}

.close {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 32px;
  cursor: pointer;
  color: #333;
}

.modal-form {
  display: flex;
  flex-direction: column;
}

.input-group {
  margin-bottom: 20px;
}

input[type="text"] {
  width: 100%;
  padding: 8px;
  border: 2px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  transition: border-color 0.3s;
}

input[type="text"]:focus {
  border-color: #6200ee;
}



.button-group {
  display: flex;
  justify-content: space-between;
}

.save-btn, .cancel-btn {
  padding: 10px 20px;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.save-btn {
  background-color: #26348c;
}

.save-btn:hover {
  background-color: #3137af;
}

.cancel-btn {
  background-color: #888;
}

.cancel-btn:hover {
  background-color: #666;
}

input[type="email"], input[type="password"] {
  width: 100%;
  padding: 8px;
  border: 2px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  transition: border-color 0.3s;
}

input[type="email"]:focus, input[type="password"]:focus {
  border-color: #6200ee;
}
</style>

