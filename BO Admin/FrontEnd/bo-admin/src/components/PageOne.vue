<template>
    <section class="home w-80" style="height: 100%; display: ">
        <div class="text text-center mb-5 ">Dashboard</div>
        <!---------------------------------- START CARD ---------------------------------->

        <div class="statistic col d-flex flex-column justify-content-between align-items-center flex-wrap">

            <div class="need-action-card">
                <h4 class="color-text">Cards</h4>

                <div class="d-flex justify-content-between row-first ">

                    <div class="col card-a  d-flex justify-content-between">
                        <div class="first-card bg-white row row-cols-1 d-flex  flex-row align-items-center">
                            <div class="col-sm">
                                <i class="fas fa-building icon-1"></i>
                            </div>
                            <div class="col-sm ">
                                <h6>All Company</h6>
                                <h1 v-if="leng > 0">{{ leng }}</h1>
                                <p v-else style="font-size: 12px; color: rgba(255, 0, 0, 0.864);" class="mt-2">Company is
                                    Empty</p>
                            </div>
                        </div>

                    </div>

                    <div class="col card-b   d-flex flex-row ">
                        <div class="first-card bg-white row row-cols-1 align-items-center">
                            <div class="col-sm">
                                <i class="fa-sharp fa-solid fa-clipboard-question icon-1"></i>
                            </div>
                            <div class="col-sm ">
                                <h6>Need Approve</h6>
                                <h1 v-if="inReview > 0">{{ inReview }}</h1>
                                <p v-else style="font-size: 12px; color: rgba(255, 0, 0, 0.864);">Need Approve is Empty</p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class=" row-second bg-white shadow-lg d-flex  justify-content-around align-items-center ">
                <div class="cardss d-flex flex-row justify-content-between flex-wrap">

                    <div class="group-card">
                        <div class="second-card">
                            <div class="card-c d-flex align-items-center justify-content-around">
                                <i class="fa-solid fa-circle-check icon-2"></i>
                            </div>
                            <h1 class="mt-2" v-if="approved > 0">{{ approved }}</h1>
                            <h1 class="mt-2" v-else style="font-size: 12px; color: rgba(255, 0, 0, 0.864);">Approved is
                                Empty</h1>
                            <p class="text-secondary">Approved</p>
                        </div>
                    </div>

                    <div class="group-card">
                        <div class="second-card">
                            <div class="card-e d-flex align-items-center justify-content-around">
                                <i class="fa-solid fa-circle-xmark icon-2"></i>
                            </div>
                            <h1 class="mt-2" v-if="rejected > 0">{{ rejected }}</h1>
                            <p v-else style="font-size: 12px; color: rgba(255, 0, 0, 0.864);">Rejected is Empty</p>
                            <p class="text-secondary">Rejected</p>
                        </div>
                    </div>

                    <div class="group-card">
                        <div class="second-card">
                            <div class="card-d d-flex align-items-center justify-content-around">
                                <i class="fa-sharp fa-solid fa-clock icon-2"></i>
                            </div>
                            <h1 class="mt-2" v-if="notActive > 0">{{ notActive }}</h1>
                            <p v-else style="color: rgba(255, 0, 0, 0.864); font-size: 12px;">Not Active is Empty</p>
                            <p class="text-secondary">Not Active</p>
                        </div>
                    </div>


                </div>

            </div>
        </div>



        <!-- <div class="multi-cards col">
            <div class="cards row d-flex flex-row justify-content-around ">
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

                <div class="card-2 text-left shadow-lg">
                    <b-col class="col d-fle x flex-column ml-5 mt-3">
                        <label class="jmlh">Need Approve</label>
                        <label class="angka" v-if="inReview > 0">{{ inReview }}</label>
                        <label class="angka mt-2" v-else
                            style="font-size: 15px; border: 1px dashed white; padding: 5px; border-radius: 10px;">Approval
                            Kosong</label>
                    </b-col>
                </div>
            </div>

            <div class="cards-2 row mt-5 d-flex flex-row justify-content-around mr-5 ml-5">
                <div class="card-3 text-center shadow">
                    <b-col class="d-flex flex-column">
                        <label class="jmlh">Approved</label>
                        <label class="angka" v-if="approved > 0">{{ approved }}</label>
                        <label class="angka mt-2" v-else
                            style="font-size: 15px; border: 1px dashed white; padding: 5px; border-radius: 10px;">Approved
                            Kosong</label>
                    </b-col>
                </div>

                <div class="card-4 text-center shadow">
                    <b-col class="d-flex flex-column">
                        <label class="jmlh">Rejected</label>
                        <label class="angka" v-if="rejected > 0">{{ rejected }}</label>
                        <label class="angka mt-2" v-else
                            style="font-size: 15px; border: 1px dashed white; padding: 5px; border-radius: 10px;">Rejected
                            Kosong</label>
                    </b-col>
                </div>

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
        </div> -->
        <!---------------------------------- END CARD ------------------------------------>

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
    margin-left: 50px;
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

.card-c {
    width: 90px;
    height: 90px;
    background-color: white;
    border-radius: 100%;
    margin-top: 10px;
}

.card-e {
    width: 90px;
    height: 90px;
    background-color: white;
    border-radius: 100%;
    margin-top: 10px;
}

.card-d {
    width: 90px;
    height: 90px;
    background-color: white;
    border-radius: 100%;
    margin-top: 10px;
}

.row-second {
    width: 50vw;
    height: 40vh;
    margin-left: 50px;
    border-radius: 50px;
    margin-top: 50px;
}

.row-first {
    margin-left: 50px;
    width: 50vw;
}

.second-card {
    width: 200px;
    height: 220px;
    margin: 20px 20px;
    /* background-color: whitesmoke; */
    background-color: #695cfe26;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
    border-radius: 30px;
    padding: 10px;
}

.first-card {
    width: 355px;
    height: 170px;
    border-radius: 30px;
    padding: 30px;
}

.icon-1 {
    width: 100px;
    height: 100px;
    /* background-color: whitesmoke; */
    border-radius: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 35px;
}

.icon-2 {
    font-size: 40px;
}

.fa-building {
    background-color: #695CFE;
    color: white;
}

.fa-clipboard-question {
    color: white;
    background-color: rgb(14, 186, 129);
}

.fa-circle-check {
    color: rgb(59, 172, 59);
}

.fa-circle-xmark {
    color: orange;
}

.fa-clock {
    color: rgb(201, 55, 55);
}
</style>