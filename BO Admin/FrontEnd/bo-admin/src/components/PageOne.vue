<template>
    <section class="home">
        <div class="text text-center mb-5">Dashboard</div>
        <!---------------------------------- START CARD ---------------------------------->
        <h4 class="color-text">Cards</h4>
        <div class="cards d-flex flex-row mb-5">
            <!-- CARD 1 -->
            <div class="card-1 text-center shadow-lg">
                <b-col>
                    <div class="icon"></div>
                    <label class="jmlh">Jumlah User</label>
                    <p></p>
                    <label class="angka" v-if="userData.length > 0">{{ userData.length }}</label>
                    <label class="angka mt-2" v-else
                        style="font-size: 20px; border: 1px dashed white; padding: 10px; border-radius: 10px;">User
                        Kosong</label>
                </b-col>
            </div>

            <!-- CARD 2 -->
            <div class="card-2 text-center shadow-lg">
                <b-col>
                    <label class="jmlh">Jumlah Company</label>
                    <p></p>
                    <label class="angka" v-if="companyData.length > 0">{{ companyData.length }}</label>
                    <label class="angka mt-2" v-else
                        style="font-size: 20px; border: 1px dashed white; padding: 10px; border-radius: 10px;">Company
                        Kosong</label>
                </b-col>
            </div>

            <!-- CARD 3 -->
            <div class="card-3 text-center shadow">
                <b-col>
                    <label class="jmlh">Company Di Approve</label>
                    <p></p>
                    <label class="angka" v-if="companyData.length > 0">{{ companyData.length }}</label>
                    <label class="angka mt-2" v-else
                        style="font-size: 20px; border: 1px dashed white; padding: 10px; border-radius: 10px;">Company
                        Kosong</label>
                </b-col>
            </div>
        </div>
        <!---------------------------------- END CARD ------------------------------------>

    </section>
</template>

<script>
import userService from '@/services/userService'
import companyService from '@/services/companyService'

export default {
    name: "PageOneS",

    // DATA
    data() {
        return {
            userData: [],
            companyData: [],


        };
    },

    // METHODS
    methods: {
        showModal() {
            this.$refs['my-modal'].show()
        },
        hideModal() {
            this.$refs['my-modal'].hide()
        },
        toggleModal() {

            this.$refs['my-modal'].toggle('#toggle-btn')
        },

        getUser() {
            userService
                .getAll()
                .then((response) => {
                    this.userData = response.data;
                    console.log("get User");
                })
                .catch((e) => {
                    console.log(e);
                });
        },

        getCompany() {

            companyService
                .getAll()
                .then((response) => {
                    this.companyData = response.data;
                    console.log("get Company");
                })
                .catch((e) => {
                    console.log(e);
                });
        },



    },

    // MOUNTED

    mounted() {
        this.getUser();
        this.getCompany();
    }
}
</script>

<style scoped>
h5 {
    margin-left: 20px;
    color: gray;
}

thead {
    background-color: #695CFE;
    color: aliceblue;
}

.table {
    padding-left: 20px;
    padding-right: 20px;
}

.btn-delete {
    margin-left: 10px;
    background-color: rgb(195, 10, 10);
    color: white;
}

table {
    border-collapse: separate !important;
    border-spacing: 0 !important;
}

table tr th,
table tr td {
    border-right: 1px solid #dee2e6 !important;
    border-bottom: 1px solid #dee2e6 !important;
}

table tr th:first-child,
table tr td:first-child {
    border-left: 1px solid #dee2e6 !important;
}

table tr th {
    border-top: 1px solid #dee2e6 !important;
}

/* top-left border-radius */
table tr:first-child th:first-child {
    border-top-left-radius: 0.25rem !important;
}

/* top-right border-radius */
table tr:first-child th:last-child {
    border-top-right-radius: 0.25rem !important;
}

/* bottom-left border-radius */
table tr:last-child td:first-child {
    border-bottom-left-radius: 0.25rem !important;
}

/* bottom-right border-radius */
table tr:last-child td:last-child {
    border-bottom-right-radius: 0.25rem !important;
}

/* .home {
    margin-right: 50px;
} */

.add-user {
    background-color: #695CFE;
    color: whitesmoke;
}

.show-botton {
    background-color: #695CFE;
}

.show-botton:hover {
    background-color: #695CFE;
}

.form-select {
    width: 100%;
    height: 40px;
    border: 1px solid rgb(200, 200, 200);
    border-radius: 5px;
}

.btn-primary {
    background-color: #695CFE;
}

/* CARD */

.cards:hover {
    cursor: pointer;
}

.card-1:hover {
    transform: scale(1.2);
    transition: transform 400ms;
}

.card-1 {
    width: 250px;
    height: 150px;
    background-color: #695CFE;
    border-radius: 30px;
    margin-left: 40px;
}

.card-2:hover {
    transform: scale(1.2);
    transition: transform 400ms;
}

.card-2 {
    width: 250px;
    height: 150px;
    background-color: orange;
    border-radius: 30px;
    margin-left: 40px;

}


.card-3:hover {
    transform: scale(1.2);
    transition: transform 400ms;
}

.card-3 {
    width: 250px;
    height: 150px;
    background-color: green;
    border-radius: 30px;
    margin-left: 40px;

}

.jmlh {
    color: white;
    font-size: 20px;
    margin-top: 10px;
}

.angka {
    color: white;
    font-size: 50px;
    /* margin-top: 2px; */
}

h4 {
    margin-left: 40px;
    color: gray;
    margin-bottom: 20px;
    font-size: 30px;
    width: 200px;
    /* background-color: #695CFE; */
}

h5 {
    font-size: 16px;
}

p {
    margin-bottom: 0px;
}
</style>