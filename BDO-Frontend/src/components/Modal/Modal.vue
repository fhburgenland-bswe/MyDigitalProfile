<template>
  <div v-if="isVisible" class="modal">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <form @submit.prevent="submitData" class="modal-form">
        <div class="input-group">
          <label for="value">Neuer Wert:</label>
          <input v-model="newValue" type="text" id="value" name="value" required>
        </div>
        <div class="button-group">
          <button type="button" class="cancel-btn" @click="closeModal">Abbrechen</button>
          <button type="submit" class="save-btn">Speichern</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, defineEmits } from 'vue';

const props = defineProps({
  field: String,
  isVisible: Boolean
});
const emits = defineEmits(['update', 'close']);

const newValue = ref('');

function closeModal() {
  emits('close');
}

function submitData() {
  emits('update', { field: props.field, value: newValue.value });
  newValue.value = ''; // Reset input after submission
}
</script>

<style scoped>
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

label {
  margin-bottom: 5px;
  font-weight: bold;
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
</style>
