.<template>
  <div class="content">
    <LoginComp></LoginComp>
  </div>
</template>
    
    <script>
import LoginComp from "../components/LoginComp.vue";
export default {
  name: "LoginPage",
  components: {
    LoginComp,
  },
};
</script>
    
    <style scoped>
.content {
  height: 100vh;
}
</style>