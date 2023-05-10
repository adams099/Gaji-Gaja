<template>
    <body :class="{ dark: isDark }">
        <Sidebar></Sidebar>
        <CompanyComp></CompanyComp>
    </body>
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

    data() {
        return {
            isDark: false
        }
    },
    methods: {
        NyobaEmit() {
            this.isDark = !this.isDark
        }
    }
}

</script>