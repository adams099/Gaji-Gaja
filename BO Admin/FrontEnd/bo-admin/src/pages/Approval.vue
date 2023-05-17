<template>
    <div>
        <Sidebar></Sidebar>
        <Approval></Approval>
    </div>
</template>

<script>
import Sidebar from "../components/SideBar.vue"
import Approval from "../components/ApprovalComp.vue"

export default {
    name: "CompanyS",
    components: {
        Sidebar,
        Approval
    },
}

</script>