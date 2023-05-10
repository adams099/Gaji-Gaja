<template>
    <div>
        <Sidebar></Sidebar>
        <AllUser></AllUser>
    </div>
</template>

<script>
import Sidebar from "../components/SideBar.vue"
import AllUser from "../components/AllUserComp.vue"

export default {
    name: "AllUserComp",
    components: {
        Sidebar,
        AllUser
    },
}

</script>