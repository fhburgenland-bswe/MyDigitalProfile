<template>
  <div class="user-profile">
    <h1>Stammdaten</h1>
    <div v-if="user">
      <p><strong>Name:</strong> {{ user.name }}</p>
      <p><strong>Email:</strong> {{ user.email }}</p>
      <p><strong>Adresse:</strong> {{ user.address }}</p>
    </div>
    <div v-else>
      <p>Loading...</p>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import { getUserData } from '@/services/user.service';

export default defineComponent({
  name: 'UserProfile',
  setup() {
    const user = ref(null);

    onMounted(async () => {
      user.value = await getUserData();
    });

    return { user };
  },
});
</script>

<style scoped>
.user-profile {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

@media (max-width: 1024px) {
  .user-profile {
    padding: 10px;
  }
}
</style>