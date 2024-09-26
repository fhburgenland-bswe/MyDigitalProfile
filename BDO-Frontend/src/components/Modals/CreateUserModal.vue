<script setup lang="ts">
import { ref, defineEmits, defineProps, computed } from 'vue';
import { createUser } from '@/services/user.service';
import { toast } from "vue3-toastify";

const emits = defineEmits(['close']);
const props = defineProps<{
  isVisible: boolean;
}>();

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
  karriereLevel: ''
});

const errorMessages = {
  pnr: 'Invalid Personalnummer',
  vorname: 'Invalid Vorname',
  nachname: 'Invalid Nachname',
  username: 'Invalid E-Mail',
  passwort: 'Invalid Passwort',
  geburtsdatum: 'Invalid Geburtsdatum',
  strasse: 'Invalid Straße',
  hausNr: 'Invalid Hausnummer',
  plz: 'Invalid PLZ',
  ort: 'Invalid Ort',
  standort: 'Invalid Standort',
  karriereLevel: 'Invalid Karrierelevel'
};

const errors = ref<Record<string, string>>({});

const patterns = {
  pnr: /^\d+$/,
  vorname: /^[A-Za-zäöüßÄÖÜ\s]+$/,
  nachname: /^[A-Za-zäöüßÄÖÜ\s]+$/,
  username: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
  passwort: /^.{6,}$/,
  geburtsdatum: /^\d{4}-\d{2}-\d{2}$/,
  strasse: /^[A-Za-zäöüßÄÖÜ\s]+$/,
  hausNr: /^[0-9A-Za-zäöüßÄÖÜ\s/.-]*$/,
  plz: /^\d+$/,
  ort: /^[A-Za-zäöüßÄÖÜ\s]+$/,
  standort: /^[A-Za-zäöüßÄÖÜ\s]+$/,
  karriereLevel: /^[A-Z_]+$/
};

function validateField(field: string, value: string) {
  if (patterns[field] && !patterns[field].test(value)) {
    errors.value[field] = errorMessages[field];
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
    karriereLevel: ''
  };
  errors.value = {};
  emits('close');
}

function formatDate(dateStr: string) {
  const date = new Date(dateStr);
  const day = String(date.getDate()).padStart(2, '0');
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const year = date.getFullYear();
  return `${day}.${month}.${year}`;
}

async function submitNewUser() {
  const isValid = Object.keys(newUser.value).every(key => validateField(key, newUser.value[key]));
  if (isValid) {
    try {
      await createUser(newUser.value);
      toast("User created successfully", {
        theme: "colored",
        type: "success",
        position: "bottom-center",
        autoClose: 2000,
        dangerouslyHTMLString: true,
      });
      closeModal();
    } catch (error) {
      toast("Error creating user: " + error.message, {
        theme: "colored",
        type: "error",
        position: "bottom-center",
        autoClose: 2000,
        dangerouslyHTMLString: true,
      });
    }
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

function formatLabel(field: string) {
  return fieldLabels[field] || field.charAt(0).toUpperCase() + field.slice(1).replace(/([A-Z])/g, ' $1').trim();
}

function inputType(field: string) {
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

const filteredKarriereLevels = computed(() => {
  return karriereLevels.filter(level => level.value !== 'UNBEKANNT');
});
</script>

<template>
  <div v-if="isVisible" class="modal">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <form @submit.prevent="submitNewUser" class="modal-form">
        <div v-for="(value, key) in newUser" :key="key" class="input-group">
          <label :for="key">{{ formatLabel(key) }}</label>
          <input
              v-if="key !== 'karriereLevel'"
              :type="inputType(key)"
              :id="key"
              v-model="newUser[key]"
              @blur="validateField(key, newUser[key])"
          />
          <select v-else v-model="newUser[key]" @blur="validateField(key, newUser[key])">
            <option v-for="level in filteredKarriereLevels" :key="level.value" :value="level.value">
              {{ level.label }}
            </option>
          </select>
          <span v-if="errors[key]" class="error-message">{{ errors[key] }}</span>
        </div>
        <div class="button-group">
          <button type="submit" class="save-btn">Save</button>
          <button type="button" class="cancel-btn" @click="closeModal">Cancel</button>
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
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
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