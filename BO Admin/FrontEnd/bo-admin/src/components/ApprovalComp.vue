<template>
    <section class="home w-80" style="height: 100%;">
        <div class="btn-add mt-4 d-flex flex-row text-white justify-content-between align-items-center">
            <h5 class="color-text mt-3 mb-3 text-center text-color detail-comp" v-if="showDetail" style="font-size: 20px; ">
                Detail Company</h5>
            <button class="btn back text-white" v-if="showDetail" @click="BackButton(1)">
                <i class="fas fa-arrow-left text-white"></i> Back
            </button>

        </div>
        <div class="table">
            <div class="d-flex flex-row justify-content-between mb-2">
                <div>
                    <div class="container mr-3 d-flex">
                        <div>
                            <!---------------------- START DETAIL COMPANY ---------------------->
                            <div class="flex-column">
                                <div class="detail shadow">


                                </div>
                            </div>

                            <div class="form justify-content-center d-flex flex-row bg-white shadow-lg" v-if="showDetail">

                                <form class="form-detail-company flex-row" @submit.prevent="updateApprovFunc">

                                    <div class=" mb-3 d-flex flex-row text-center  justify-content-center">
                                        <h3 class="text-center mr-2 text-secondary ">Status Company Saat
                                            ini</h3>
                                        <h3 :class="colorStatus">{{ status }}</h3>
                                    </div>

                                    <div class="form-group">
                                        <label for="name_company">Company Name</label>
                                        <input disabled type="text" class="form-control company-detail" id="name_company"
                                            placeholder="Company Name" required v-model="appUpdateData.comName">
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="request_type">Reques Type</label>
                                            <input disabled type="text" class="form-control" id="request_type"
                                                placeholder="Email" required v-model="appUpdateData.reqType">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="request_by">Request By</label>
                                            <input disabled type="text" class="form-control" id="request_by" required
                                                v-model="appUpdateData.reqBy">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="approve_by">Approve By</label>
                                        <input disabled type="text" class="form-control company-detail" id="approve_by"
                                            placeholder="Approve By" required v-model="appUpdateData.apprBy">
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="created_time">Created Time</label>
                                            <input type="text" class="form-control" id="created_time"
                                                placeholder="Created Time" disabled v-model="appUpdateData.createdTime">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="update_time">Update Time</label>
                                            <input type="text" class="form-control" id="update_time"
                                                placeholder="Update Time" disabled v-model="appUpdateData.updateTime">
                                        </div>

                                    </div>

                                    <div class="buttons d-flex flex-row justify-content-between mt-3">
                                        <!-- <button type="submit" class="btn mt-2 btn-update">Update</button> -->
                                        <button type="button" class="btn btn-delete text-white " @click="showModalStatuss()"
                                            style="border-radius: 10px;" :disabled="appUpdateData.updateTime !== null">Edit
                                            Status</button>
                                    </div>
                                </form>
                            </div>
                            <!---------------------- END DETAIL COMPANY ------------------------>

                            <!---------------------- SHOW MODAL STATUS ------------------------>
                            <div>
                                <b-modal v-model="showModalStatus">
                                    <b-modal v-model="showModalStatus" hide-footer>
                                        <div>
                                            <h6> Status Confirmation</h6>
                                            <b-col>
                                                <div class="initombol">
                                                    <button @click="accept" class="btn btn-success mr-3">Approve</button>
                                                    <button @click="reject" class="btn btn-danger mr-3">Reject</button>
                                                    <!-- <button @click="deadactive"
                                                        class="btn btn-primary mr-3">Deadactive</button> -->
                                                </div>
                                            </b-col>

                                        </div>
                                    </b-modal>
                                </b-modal>
                            </div>
                            <!---------------------- END MODAL STATUS ------------------------>
                        </div>
                    </div>
                </div>
            </div>

            <!------------------ START TABLE ------------------->
            <div v-show="!showDetail">
                <div class="input-group col mb-4 ml-1 d-flex flex-row  justify-content-between ">
                    <div class="">
                        <h5 class="color-text daftar-company" v-if="!showDetail" style="font-size: 25px;">Approval</h5>
                    </div>
                    <div class="">
                        <div class="d-flex">
                            <div class="form-outline">
                                <input type="search" id="form1" class="form-control" placeholder="Search by Status" />
                            </div>
                            <button type="button" class="btn btn-primary ml-2">
                                <i class="fas fa-search"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <table class="table">
                    <thead class="text-center">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col" style="width: 170px;">Company Name</th>
                            <th scope="col" style="width: 150px;">Request Type</th>
                            <th scope="col">Request By</th>
                            <th scope="col" style="width: 150px;">Approve By</th>
                            <th scope="col" style="width: 100px;">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody v-if="appData.length > 0">
                        <tr class="baris  shadow-lg bg-white" v-for="(item, index) in paginatedData" :key="index">
                            <th scope="row" class="text-center">{{ item.id }}</th>
                            <td>{{ item.comName }}</td>
                            <td>{{ item.reqType }}</td>
                            <td>{{ item.reqBy }}</td>
                            <td>{{ item.apprBy }}</td>
                            <div class="status blue mx-2 text-center" v-if="item.status == 1">In Review</div>
                            <div class="status green mx-2 text-center" v-else-if="item.status == 2">Active</div>
                            <div class="status red mx-2 text-center" v-else-if="item.status == 3">Rejected</div>
                            <div class="status salmon mx-2 text-center" v-else>Deactive</div>
                            <td class="text-center">
                                <button type="button" class="btn btn-detail" v-on:click="showDetails(item)">Detail</button>
                            </td>
                        </tr>
                    </tbody>

                    <tbody v-else>
                        <tr class="msg-tr text-center">
                            <td colspan="6" class="msg-null text-center">
                                <h3 class="color-text">Saat ini Tidak Ada Data Terkini !</h3>
                            </td>
                        </tr>
                    </tbody>

                </table>
                <div class="row d-flex justify-content-center next color-text " v-if="!showDetail">
                    <button type="button" class="btn btn-success" @click="previousPage"
                        :disabled="currentPage == 1">Previous</button>
                    <p class="ml-4 mr-4 font-italic mt-2">{{ currentPage }} / {{ pageCount }}</p>
                    <button type="button" class="btn btn-success" @click="nextPage"
                        :disabled="currentPage == pageCount">Next</button>
                </div>
            </div>

            <!------------------ END TABLE ------------------>

        </div>

    </section>
</template>
  
<script>
import companyService from '@/services/companyService.js';
import approvalService from '@/services/approvalService.js';
import adds from '@/services/userService.js'
import userService from '@/services/userService.js';

export default {
    name: "CompanyS",

    // DATA
    data() {
        return {
            searchByStatus: "",
            status: null,
            colorStatus: null,
            itemsPerPage: 7,
            currentPage: 1,

            companyData: {},

            buatAkun: {
                name: null,
                email: null,
                pass: null,
                roleId: null,
                statId: null,
                companyId: null,
                created: null,
                createdBy: null,
                phone: null,
                apprBy: null,
                update: null
            },

            appData: {},

            appUpdateData: {
                id: null,
                status: null,
                comName: null,
                reqType: null,
                reqBy: null,
                createdTime: null,
                apprBy: null,
                updateTime: null,
            },

            appUpdate: {},

            updateCompany: {
                id: null,
                comName: null,
                comTaxNum: null,
                siup: null,
                address: null,
                mailAddress: null,
                postal: null,
                adminName: null,
                adminEmail: null,
                status: null,
                createdBy: null,
                createdTime: null,
                apprBy: null,
                updateTime: null
            },

            showDetail: false,
            showModalStatus: false,
            isDisable: false,
        }
    },

    methods: {

        updateApprovFunc() {
            let data = this.appUpdateData;

            approvalService.saveApprov(data)
                .then((response) => {
                    console.log(response.status);
                    this.$toast.success('Approval Data has been successfully Update!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    this.getApproval();
                    this.showDetail = !this.showDetail;
                })
                .catch((e) => {
                    try {
                        e["code"] === "ERR_NETWORK";
                        console.log(e["code"]);
                        this.$toast.error("ERROR NETWORK CONNECTION", {
                            position: "top-right",
                            timeout: 2500,
                        });
                    } catch (error) {
                        this.$toast.error('Error', {
                            position: 'top-right',
                            timeout: 2500,
                        });
                    }
                });

        },

        previousPage() {
            this.currentPage--;
        },

        showDetails(data) {
            this.showDetail = !this.showDetail
            this.appUpdateData = data;
            this.appUpdate = data;

            if (data.status == 1) {
                this.status = "In Review"
                this.colorStatus = "review"

            } else if (data.status == 2) {
                this.status = "Active"
                this.colorStatus = "active"

            } else if (data.status == 3) {
                this.status = "Reject"
                this.colorStatus = "reject"

            } else {
                this.status = "Deactive"
                this.colorStatus = "deactive"

            }
        },

        showModalStatuss() {
            this.showModalStatus = !this.showModalStatus

        },

        // navigasi ke halaman berikutnya
        nextPage() {
            this.currentPage++;
        },

        BackButton(back) {
            if (back == 1) {
                this.showDetail = false
            }
        },

        //test doang ya
        accept() {
            let data = this.appUpdate;
            if (data.reqBy == this.$session.get('email')) {
                this.$toast.warning('Action Disable', {
                    position: 'top-right',
                    timeout: 2500,
                });
            } else {
                data.status = 2;
                data.apprBy = this.$session.get('email');

                const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()';
                let randomString = '';
                const charactersLength = characters.length;
                const length = 8;
                for (let i = 0; i < length; i++) {
                    randomString += characters.charAt(Math.floor(Math.random() * charactersLength));
                }

                approvalService.saveApprov(data)
                    .then((response) => {
                        this.$toast.success('Status has been successfully Update!', {
                            position: 'top-right',
                            timeout: 2500,
                        });
                        console.log(response.status);
                        this.showModalStatus = false;
                        this.getApproval();
                        this.showDetail = !this.showDetail
                    })
                    .catch((e) => {
                        try {
                            e["code"] === "ERR_NETWORK";
                            console.log(e["code"]);
                            this.$toast.error("ERROR NETWORK CONNECTION", {
                                position: "top-right",
                                timeout: 2500,
                            });
                        } catch (error) {
                            this.$toast.error('Error', {
                                position: 'top-right',
                                timeout: 2500,
                            });
                        }
                    });
                companyService.getCompanyById(data.companyId)
                    .then((response) => {
                        // console.log(response.data)
                        let comData = response.data
                        comData.status = 2
                        if (data.reqType == "Deactive Company") {
                            comData.status = 4
                        }
                        companyService.upload(comData)
                        if (data.reqType == "Add Company") {

                            let akun = this.buatAkun
                            akun.email = comData.adminEmail;
                            akun.name = comData.adminName;
                            akun.status = 4;
                            akun.pass = randomString;
                            akun.roleId = 3;
                            akun.statId = 4;
                            akun.createdBy = this.$session.get('email');
                            adds.register(akun)
                            let semail = {
                                "email": akun.email,
                                "subject": "Your Credential Login",
                                "body": akun.pass
                            }
                            // console.log(semail)
                            userService.asemail(semail)

                        }
                    })
                    .catch(() => {
                    });
                this.showModalStatus = false
            }
        },

        reject() {
            let data = this.appUpdate;
            if (data.reqBy == this.$session.get('email')) {
                this.$toast.warning('Action Disable', {
                    position: 'top-right',
                    timeout: 2500,
                });
            } else {
                data.status = 3;
                data.apprBy = this.$session.get('email');
                approvalService.saveApprov(data)
                    .then((response) => {
                        this.$toast.success('Status has been successfully Update!', {
                            position: 'top-right',
                            timeout: 2500,
                        });
                        console.log(response.status);
                        this.showModalStatus = false;
                        this.getApproval();
                        this.showDetail = !this.showDetail
                    })
                    .catch(() => {
                        this.$toast.error('Error', {
                            position: 'top-right',
                            timeout: 2500,
                        });
                    });
                companyService.getCompanyById(data.companyId)
                    .then((response) => {
                        let comData = response.data
                        comData.status = 3
                        companyService.upload(response.data)
                    })
                    .catch(() => {
                    });
                this.showModalStatus = false
            }
        },

        // GET APPROVAL
        getApproval() {
            approvalService
                .getAll()
                .then((response) => {
                    this.appData = response.data;
                    console.log("get Approval");
                })
                .catch((e) => {
                    try {
                        e["code"] === "ERR_NETWORK";
                        console.log(e["code"]);
                        this.$toast.error("ERROR NETWORK CONNECTION", {
                            position: "top-right",
                            timeout: 2500,
                        });
                    } catch (error) {
                        console.log(e);
                    }
                });
        },
    },

    // MOUNTED
    mounted() {
        this.getApproval();
    },

    computed: {
        // hitung jumlah halaman
        pageCount() {
            const itemCount = this.appData.length;
            const pageCount = Math.ceil(itemCount / this.itemsPerPage);
            return pageCount;
        },
        // ambil data sesuai halaman saat ini
        paginatedData() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            return this.appData.slice(startIndex, endIndex);
        },
    },
};
</script>
  
<style scoped>
h5 {
    margin-left: 20px;
    color: gray;
}

h6 {
    text-align: center;
    margin-bottom: 20px;
}

.initombol {
    text-align: center;
}

/* TABLE STYLE */
table {
    border-collapse: separate !important;
    border-spacing: 0 !important;

}

thead {
    background-color: #695cfe;
    color: aliceblue;
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

.table {
    padding-left: 20px;
    padding-right: 20px;
}

form {
    width: 60vw;
    padding: 40px 40px;
    border-radius: 25px;
}

.form {
    /* left: 200px; */
    margin-left: 100px;
    border-radius: 20px;
    margin-bottom: 5px;
}

.detail {
    margin-left: 100px;
    background-color: #695cfe;
    width: 200px;
    border-radius: 20px;
    font-size: 5px;

}

/* PAGINATION STYLE */
.next {
    /* background-color: #695cfe; */
    position: fixed;
    bottom: 0;
    left: 700px;
    margin-bottom: 10px;
}


/* STATUS STYLE */
.status {
    margin-top: 17px;
    background-color: transparent;
    border-radius: 5px;
    font-size: 13px;
    padding: 3px;

}

.blue {
    border: 2px solid blue;
    color: blue;
}

.salmon {
    border: 2px solid salmon;
    color: salmon;
}

.red {
    border: 2px solid red;
    color: red;
}

.green {
    border: 2px solid green;
    color: green;
}

.row {
    position: fixed;

}

.back {
    background-color: #695cfe;
    margin-right: 125px;
    border-radius: 10px;
    height: 40px;
    margin-right: 15rem;
}


/* BUTTON STYLE */
.btn-primary {
    background-color: #695cfe;
}

.btn-delete {
    margin-left: 10px;
    background-color: rgba(195, 10, 10, 0.84);
    color: white;
    height: 50px;
    width: 150px;
    border-radius: 15px;
}

.btn-update {
    border-radius: 10px;
    background-color: #695cfe;
    color: white;
}

.btn-update:hover {
    border-radius: 10px;
    background-color: #594ed1;
    color: white;
}

.daftar-company {
    font-size: 30px;
}

.detail-comp {
    /* background-color: #695cfe; */
    padding: 5px;
    border-radius: 10px;
    margin-left: 130px;
    color: grey;
}

.btn-detail {
    background-color: #594ed1;
    color: white;
}

.btn-detail:hover {
    background-color: #4e44ba;
    color: white;
}

/* color status */
.review {
    color: rgb(88, 88, 233);
    font-weight: 600;
}

.active {
    color: green;
    font-weight: 600;
}

.reject {
    color: red;
    font-weight: 600;
}

.deactive {
    color: grey;
    font-weight: 600;
}
</style>