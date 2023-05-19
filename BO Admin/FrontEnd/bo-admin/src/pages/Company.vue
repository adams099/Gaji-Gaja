<template>
    <div>
        <Sidebar></Sidebar>
        <CompanyComp2></CompanyComp2>
    </div>
</template>

<script>
import Sidebar from "../components/SideBar.vue"
import CompanyComp2 from "../components/CompanyComp2.vue"

export default {
    name: "CompanyS",
    components: {
        Sidebar,
        CompanyComp2
    },
}

</script>