<template>
  <div>
    <Sidebar></Sidebar>
    <ProfileComp></ProfileComp>
  </div>
</template>

<script>
import Sidebar from "../components/SideBar.vue";
import ProfileComp from "../components/ProfileComp.vue";
export default {
  name: "ProfilePage",
  components: {
    Sidebar,
    ProfileComp,
  },
};
</script>

<style></style>