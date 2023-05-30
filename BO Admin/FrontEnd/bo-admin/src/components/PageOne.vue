<template>
    <section class="home w-80" style="height: 100%;">
        <div class="text text-center mb-5 ">Dashboard</div>
        <!---------------------------------- START CARD ---------------------------------->
        <h4 class="color-text">Cards</h4>
        <div class="multi-cards col">
            <div class="cards row d-flex flex-row justify-content-around ">
                <!-- CARD 1 -->
                <div class="card-1 text-left shadow-lg ">
                    <b-col class="col d-flex flex-column ml-5 mt-3">
                        <div class="icon"></div>
                        <label class="jmlh row">Jumlah Company</label>
                        <label class="angka row" v-if="leng > 0">{{ leng }}</label>
                        <label class="angka mt-2 row" v-else
                            style="font-size: 15px; border: 1px dashed white; padding: 5px; border-radius: 10px;">Company
                            Kosong</label>
                    </b-col>
                </div>

                <!-- CARD 2 -->
                <div class="card-2 text-left shadow-lg">
                    <b-col class="col d-flex flex-column ml-5 mt-3">
                        <label class="jmlh">Need Approve</label>
                        <label class="angka" v-if="inReview > 0">{{ inReview }}</label>
                        <label class="angka mt-2" v-else
                            style="font-size: 15px; border: 1px dashed white; padding: 5px; border-radius: 10px;">Approval
                            Kosong</label>
                    </b-col>
                </div>
            </div>

            <div class="cards-2 row mt-5 d-flex flex-row justify-content-around mr-5 ml-5">
                <!-- CARD 3 -->
                <div class="card-3 text-center shadow">
                    <b-col class="d-flex flex-column">
                        <label class="jmlh">Approved</label>
                        <label class="angka" v-if="approved > 0">{{ approved }}</label>
                        <label class="angka mt-2" v-else
                            style="font-size: 15px; border: 1px dashed white; padding: 5px; border-radius: 10px;">Approved
                            Kosong</label>
                    </b-col>
                </div>

                <!-- CARD 4 -->
                <div class="card-4 text-center shadow">
                    <b-col class="d-flex flex-column">
                        <label class="jmlh">Rejected</label>
                        <label class="angka" v-if="rejected > 0">{{ rejected }}</label>
                        <label class="angka mt-2" v-else
                            style="font-size: 15px; border: 1px dashed white; padding: 5px; border-radius: 10px;">Rejected
                            Kosong</label>
                    </b-col>
                </div>

                <!-- CARD 5 -->
                <div class="card-5 text-center shadow">
                    <b-col class="d-flex flex-column">
                        <label class="jmlh">Not Acctive</label>
                        <label class="angka" v-if="notActive > 0">{{ notActive }}</label>
                        <label class="angka mt-2" v-else
                            style="font-size: 15px; border: 1px dashed white; padding: 5px; border-radius: 10px;">Not Active
                            Kosong</label>
                    </b-col>
                </div>
            </div>
            <!---------------------------------- END CARD ------------------------------------>
        </div>

    </section>
</template>

<script>
import companyService from '@/services/companyService'

export default {
    name: "PageOneS",

    // DATA
    data() {
        return {
            userData: [],
            companyData: [],
            inReview: null,
            approved: null,
            rejected: null,
            notActive: null,
            leng: null,

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

        // COUNT COMPANY REVIEW
        countInReview() {
            let status = 1
            companyService
                .getStatusCount(status)
                .then((response) => {
                    this.inReview = response.data;
                })
                .catch((e) => {
                    console.log(e);
                });
        },

        // COUNT COMPANY APPROVED
        countApproved() {
            let status = 2
            companyService
                .getStatusCount(status)
                .then((response) => {
                    this.approved = response.data;
                })
                .catch((e) => {
                    console.log(e);
                });
        },

        // COUNT COMPANY REJECT
        countRejected() {
            let status = 3
            companyService
                .getStatusCount(status)
                .then((response) => {
                    this.rejected = response.data;
                })
                .catch((e) => {
                    console.log(e);
                });
        },

        // COUNT COMPANY NOT ACTIVE
        countNotActive() {
            let status = 4
            companyService
                .getStatusCount(status)
                .then((response) => {
                    this.notActive = response.data;
                })
                .catch((e) => {
                    console.log(e);
                });
        },

        // COUNT LENGTH COMPANY DATA
        countLeng() {
            companyService
                .getLength()
                .then((response) => {
                    this.leng = response.data;
                })
                .catch((e) => {
                    console.log(e);
                });
        },


    },

    // MOUNTED
    mounted() {
        this.countInReview();
        this.countApproved();
        this.countRejected();
        this.countNotActive();
        this.countLeng();
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
    width: 400px;
    height: 200px;
    background-color: #695CFE;
    border-radius: 30px;

}

.card-2:hover {
    transform: scale(1.2);
    transition: transform 400ms;
}

.card-2 {
    width: 400px;
    height: 200px;
    background-color: orange;
    border-radius: 30px;

}


.card-3:hover {
    transform: scale(1.2);
    transition: transform 400ms;
}

.card-3 {
    width: 300px;
    height: 170px;
    background-color: green;
    border-radius: 30px;



}

.card-4:hover {
    transform: scale(1.2);
    transition: transform 400ms;
}

.card-4 {
    width: 300px;
    height: 170px;
    background-color: rgba(0, 9, 128, 0.758);
    border-radius: 30px;

}

.card-5:hover {
    transform: scale(1.2);
    transition: transform 400ms;
}

.card-5 {
    width: 300px;
    height: 170px;
    background-color: rgba(255, 0, 0, 0.694);
    border-radius: 30px;

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