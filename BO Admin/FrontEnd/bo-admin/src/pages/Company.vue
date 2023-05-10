<template>
    <div>
        <Sidebar></Sidebar>
        <CompanyComp></CompanyComp>
    </div>
</template>

<script>
import Sidebar from "../components/SideBar.vue"
import CompanyComp from "../components/CompanyComp.vue"

export default {
    name: "CompanyS",
    components: {
        Sidebar,
        CompanyComp
    },
}

</script>