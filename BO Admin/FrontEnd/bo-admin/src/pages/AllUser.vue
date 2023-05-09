<template>
    <div>
        <Sidebar></Sidebar>
        <AllUser></AllUser>
    </div>
</template>

<script>
import Sidebar from "../components/SideBar.vue"
import AllUser from "../components/AllUser.vue"

export default {
    name: "AllUser",
    components: {
        Sidebar,
        AllUser
    },
}

</script>