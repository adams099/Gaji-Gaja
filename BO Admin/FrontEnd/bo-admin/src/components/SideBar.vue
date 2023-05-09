<template>
    <nav :class="{ close: isClose }" class="sidebar">
        <header>
            <div class="image-text">
                <span class="image">
                    <img class="img" src="../assets/bo-admin.png" alt="">
                </span>

                <div class="text logo-text">
                    <span class="name">BO ADMIN</span>
                    <span class="profession">Gaji Gaja</span>
                </div>
            </div>

            <i class='bx bx-chevron-right toggle' v-on:click="SidebarClose"><b-icon icon="arrow-right-circle"
                    class="rounded-circle p-1" variant="light"
                    style="width: 30px; height: 30px; margin-left: 0px; background-color: #695CFE;"></b-icon></i>
        </header>

        <div class="menu-bar">
            <div class="menu">

                <!-- <li class="search-box">
                        <i class='bx bx-search icon'></i>
                        <input type="text" placeholder="Search...">
                    </li> -->

                <ul class="menu-links">

                    <!--------------- DASHBOARD --------------->
                    <li class="">
                        <a href="#">
                            <b-icon icon="house" class="rounded-circle p-1" variant="light"
                                style="width: 30px; height: 30px; margin-left: 10px; background-color: #695CFE;"></b-icon>
                            <span class="text nav-text" style="margin-left: 5px;">Dashboard</span>
                        </a>
                    </li>

                    <!--------------- ALL USER --------------->
                    <li class="">
                        <a href="#">
                            <b-icon icon="person" class="rounded-circle p-1" variant="light"
                                style="width: 30px; height: 30px; margin-left: 10px; background-color: #695CFE;"></b-icon>
                            <span class="text nav-text" style="margin-left: 5px;">All User</span>
                        </a>
                    </li>

                    <!--------------- COMPANY --------------->
                    <li class="">
                        <a href="#">
                            <b-icon icon="building" class="rounded-circle p-1" variant="light"
                                style="width: 30px; height: 30px; margin-left: 10px; background-color: #695CFE;"></b-icon>
                            <span class="text nav-text" style="margin-left: 5px;">Company</span>
                        </a>
                    </li>

                </ul>
            </div>

            <div class="bottom-content">
                <li class="">
                    <a href="#">
                        <b-icon icon="door-closed" class="rounded-circle p-1" variant="light"
                            style="width: 30px; height: 30px; margin-left: 10px; background-color: #695CFE;"></b-icon>
                        <span class="text nav-text" style="margin-left: 5px;">Logout</span>
                    </a>
                </li>

                <li class="mode">
                    <div class="sun-moon">
                        <b-icon icon="moon" class="rounded-circle p-1 mt-2" variant="light"
                            style="width: 30px; height: 30px; margin-left: 10px; background-color: #695CFE;"></b-icon>
                        <i class='bx bx-sun icon sun'></i>
                    </div>
                    <span class="mode-text text">{{ textMode }}</span>

                    <div class="toggle-switch" v-on:click="ModeLandD" v-if="!isClose">
                        <span class="switch"></span>
                    </div>
                </li>

            </div>
        </div>

    </nav>
</template>

<script>
export default {
    name: "DetalFormAnggota",
    data() {
        return {
            isClose: false,
            textMode: "Dark Mode"
        };
    },
    methods: {
        SidebarClose() {
            this.isClose = !this.isClose
        },
        ModeLandD() {
            // this.$emit
            this.$emit('TestEmit')
            if (this.textMode == "Dark Mode") {
                this.textMode = "Light Mode"
            } else {
                this.textMode = "Dark Mode"
            }
        }
    },
}
</script>

<style scoped></style>