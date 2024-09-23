<template>
  <div v-if="teamMember">
    <h1>{{ teamMember.name }}</h1>
    <p><strong>Email:</strong> {{ teamMember.email }}</p>
    <p><strong>Role:</strong> {{ teamMember.role }}</p>
    <button @click="editMode = !editMode">Edit</button>
    <div v-if="editMode">
      <input v-model="teamMember.name" placeholder="Name" />
      <input v-model="teamMember.email" placeholder="Email" />
      <input v-model="teamMember.role" placeholder="Role" />
      <button @click="updateTeamMember">Save</button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { getTeamMemberById, updateTeamMember } from '@/services/teamMemberService';

export default defineComponent({
  name: 'TeamMemberDetail',
  setup() {
    const route = useRoute();
    const teamMember = ref(null);
    const editMode = ref(false);

    onMounted(async () => {
      const id = route.params.id;
      teamMember.value = await getTeamMemberById(id);
    });

    const updateTeamMember = async () => {
      if (teamMember.value) {
        await updateTeamMember(teamMember.value.id, teamMember.value);
        editMode.value = false;
      }
    };

    return { teamMember, editMode, updateTeamMember };
  },
});
</script>