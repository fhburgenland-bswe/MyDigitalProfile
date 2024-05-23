<script setup>
import { ref, defineEmits, defineProps } from 'vue';
import { createUser } from '@/services/user.service';
import { toast } from "vue3-toastify";

const emits = defineEmits(['close']);
const props = defineProps({
  isVisible: Boolean
});

const newUser = ref({
  pnr: '',
  vorname: '',
  nachname: '',
  username: '',
  passwort: '',
  geburtsdatum: '',
  strasse: '',
  hausNr: '',
  plz: '',
  ort: '',
  standort: '',
  karriereLevel: 'UNBEKANNT' // Default value
});

const errorMessages = {
  vorname: "Der Vorname darf nur Buchstaben enthalten.",
  nachname: "Der Nachname darf nur Buchstaben enthalten.",
  geburtsdatum: "Das Geburtsdatum muss im Format TT.MM.JJJJ sein.",
  standort: "Der Standort darf nur Buchstaben enthalten.",
  plz: "Die PLZ darf nur Zahlen enthalten.",
  ort: "Der Ort darf nur Buchstaben enthalten.",
  strasse: "Die Straße darf nur Buchstaben enthalten.",
  hausNr: "Die Hausnummer darf Buchstaben, Zahlen und spezielle Zeichen wie / und . enthalten."
};

const errors = ref({});

const patterns = {
  vorname: /^[a-zA-ZäöüßÄÖÜ]+$/,
  nachname: /^[a-zA-ZäöüßÄÖÜ]+$/,
  standort: /^[a-zA-ZäöüßÄÖÜ\s]+$/,
  plz: /^\d+$/,
  strasse: /^[a-zA-ZäöüßÄÖÜ\s]+$/,
  hausNr: /^[0-9A-Za-zäöüßÄÖÜ\s\/.-]*$/
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
    pnr: '',
    vorname: '',
    nachname: '',
    username: '',
    passwort: '',
    geburtsdatum: '',
    strasse: '',
    hausNr: '',
    plz: '',
    ort: '',
    standort: '',
    karriereLevel: 'UNBEKANNT' // Reset to default value
  };
  errors.value = {};
  emits('close');
}

function formatDate(dateStr) {
  const date = new Date(dateStr);
  const day = String(date.getDate()).padStart(2, '0');
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const year = date.getFullYear();
  return `${day}.${month}.${year}`;
}

async function submitNewUser() {
  const isValid = Object.keys(newUser.value).every(key => validateField(key, newUser.value[key]));
  if (isValid) {
    const userData = {...newUser.value, geburtsdatum: formatDate(newUser.value.geburtsdatum)};
    console.log('Submitting new user:', JSON.stringify(userData));
    try {
      const response = await createUser(userData);
      console.log('User created successfully:', response);
      toast("Benutzer wurde erfolgreich angelegt", {
        theme: "colored",
        type: "success",
        position: "bottom-center",
        autoClose: 2000,
        dangerouslyHTMLString: true,
      });
      closeModal();
    } catch (error) {
      console.error('Error creating user:', error.response || error.message || error);
      toast("Fehler beim Anlegen des Benutzers: " + (error.response?.data || error.message), {
        theme: "colored",
        type: "error",
        position: "bottom-center",
        autoClose: 2000,
        dangerouslyHTMLString: true,
      });
    }
  } else {
    toast("Es sind noch Fehler im Formular vorhanden. Bitte überprüfen Sie Ihre Eingaben.", {
      theme: "colored",
      type: "error",
      position: "bottom-center",
      autoClose: 2000,
      dangerouslyHTMLString: true,
    });
  }
}

const fieldLabels = {
  pnr: 'Personalnummer',
  vorname: 'Vorname',
  nachname: 'Nachname',
  username: 'E-Mail',
  passwort: 'Passwort',
  geburtsdatum: 'Geburtsdatum',
  strasse: 'Straße',
  hausNr: 'Hausnummer',
  plz: 'PLZ',
  ort: 'Ort',
  standort: 'Standort',
  karriereLevel: 'Karrierelevel'
};

function formatLabel(field) {
  return fieldLabels[field] || field.charAt(0).toUpperCase() + field.slice(1).replace(/([A-Z])/g, ' $1').trim();
}

function inputType(field) {
  if (field === 'username') return 'email';
  if (field === 'passwort') return 'password';
  if (field === 'geburtsdatum') return 'date';
  return 'text';
}

const karriereLevels = [
  { label: 'Junior Consultant', value: 'JUNIOR_CONSULTANT' },
  { label: 'Consultant', value: 'CONSULTANT' },
  { label: 'Senior Consultant', value: 'SENIOR_CONSULTANT' },
  { label: 'Manager', value: 'MANAGER' },
  { label: 'Senior Manager', value: 'SENIOR_MANAGER' },
  { label: 'Director', value: 'DIRECTOR' },
  { label: 'Associate Partner', value: 'ASSOCIATE_PARTNER' },
  { label: 'Partner', value: 'PARTNER' },
  { label: 'Unbekannt', value: 'UNBEKANNT' }
];
</script>

<template>
  <div v-if="isVisible" class="modal">
    <div class="modal-content">
      <h2>Neuen Benutzer anlegen</h2>
      <form @submit.prevent="submitNewUser" class="modal-form">
        <div class="input-group" v-for="(value, key) in newUser" :key="key" v-if="key !== 'karriereLevel'">
          <label :for="key" v-if="key !== 'karriereLevel'">{{ formatLabel(key) }}</label>
          <input v-model="newUser[key]" :type="inputType(key)" :id="key" :name="key" required v-if="key !== 'karriereLevel'">
          <span v-if="errors[key]" class="error-message">{{ errors[key] }}</span>
        </div>
        <div class="input-group">
          <label for="karriereLevel">{{ formatLabel('karriereLevel') }}</label>
          <select v-model="newUser.karriereLevel" id="karriereLevel" name="karriereLevel">
            <option v-for="level in karriereLevels" :key="level.value" :value="level.value">{{ level.label }}</option>
          </select>
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
input[type="date"],
select {
  width: 100%;
  padding: 8px;
  border: 2px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  transition: border-color 0.3s;
}

input[type="date"]:focus,
select:focus {
  border-color: #6200ee;
}

.error-message {
  color: red;
  font-size: 0.8em;
}

label {
  display: block;
  margin: 10px 0;
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
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 90%;
  max-width: 500px;
  max-height: 80vh;
  overflow-y: auto;
  animation: modalFadeIn 0.3s ease-out;
}

@keyframes modalFadeIn {
  from {
    transform: translateY(-50px);
    opacity: 0;
  }
  to {
    transform: translateY(0);
    opacity: 1;
  }
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
