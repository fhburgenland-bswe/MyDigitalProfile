<template>
  <div>
    <h1>Team Members</h1>
    <ul>
      <li v-for="member in teamMembers" :key="member.id">
        <router-link :to="{ name: 'TeamMemberDetail', params: { id: member.id } }">{{ member.name }}</router-link>
      </li>
    </ul>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import { getTeamMembers } from '@/services/teamMemberService';

export default defineComponent({
  name: 'TeamMemberList',
  setup() {
    const teamMembers = ref([]);

    onMounted(async () => {
      teamMembers.value = await getTeamMembers();
    });

    return { teamMembers };
  },
});
</script>