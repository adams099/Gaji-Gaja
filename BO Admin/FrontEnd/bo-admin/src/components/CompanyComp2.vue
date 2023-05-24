<template>
    <section class="home w-80" style="height: 100%;">
        <div class="btn-add mt-3 d-flex flex-row text-white  justify-content-between align-items-center">
            <div class="text text-center" v-if="!showForm" style="font-size: 25px;">Company Table</div>
            <button class="btn btn-add-com" @click="addFunc()" v-if="roleId === 2" v-show="!showForm">Add
                Company</button>
        </div>

        <!------------------ START TABLE ------------------->
        <table class="table" v-show="!showForm">
            <thead class="text-center">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Company Name</th>
                    <th scope="col">Mail Address</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody v-if="companyData.length > 0">
                <tr class=" baris text-center shadow-lg bg-white" v-for="(item, index) in paginatedData" :key="index">
                    <th scope="row" class="text-center">{{ item?.id }}</th>
                    <td>{{ item?.comName }}</td>
                    <td>{{ item?.mailAddress }}</td>
                    <button type="button" class="status blue" v-if="item.status == 1">In Review</button>
                    <button type="button" class="status green" v-else-if="item.status == 2">Active</button>
                    <button type="button" class="status red" v-else-if="item.status == 3">Rejected</button>
                    <button type="button" class="status salmon" v-else>Deactive</button>
                    <td class="text-center">
                        <button type="button" class="btn btn-detail" @click="updateFunc(item)">{{ tableBtn }}</button>
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

        <div class="row d-flex justify-content-center next color-text" v-if="!showForm && companyData.length > 7">
            <button type="button" class="btn btn-success" @click="previousPage"
                :disabled="currentPage == 1">Previous</button>
            <p class="ml-4 mr-4 font-italic mt-2">{{ currentPage }} / {{ pageCount }}</p>
            <button type="button" class="btn btn-success" @click="nextPage"
                :disabled="currentPage == pageCount">Next</button>
        </div>

        <!------------------ END TABLE ------------------>

        <!--------------------- START ADD COMPANY -------------------------->
        <div class="form" v-show="showForm">
            <div class="form-company d-flex flex-row justify-content-between">
                <h6 class=" color-text">Form Add Company</h6>
                <button class="btn back text-white mb-3" v-if="showForm" v-on:click="BackButton(1)">
                    <i class="fas fa-arrow-left text-white"></i> Back
                </button>
            </div>
            <form class="iseng form-detail-company flex-row bg-white shadow-lg" @submit.prevent="SubmitCompany">
                <div class="form-group">
                    <label for="name_company">Company Name</label>
                    <input type="text" class="form-control company-detail" id="name_company" placeholder="Company Name"
                        required :disabled="roleId === 1" v-model="companyDatas.comName">
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="email_company">Email Company</label>
                        <input type="email" class="form-control" id="email_company" placeholder="Email" required
                            :disabled="roleId === 1" v-model="companyDatas.mailAddress">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="NPWP">NPWP</label>
                        <input type="text" class="form-control" id="NPWP" placeholder="Enter NPWP Number" required
                            :disabled="roleId === 1" v-model="companyDatas.comTaxNum">
                    </div>
                </div>


                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="postal_code">Postal Code</label>
                        <input type="text" class="form-control company-detail" id="postal_code" placeholder="Postal Code"
                            v-model="companyDatas.postal" required :disabled="roleId === 1">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="siup">SIUP</label>
                        <input type="text" class="form-control" id="siup" placeholder="Enter SIUP number"
                            v-model="companyDatas.siup" required :disabled="roleId === 1">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="admin_name">Admin Name</label>
                        <input type="text" class="form-control" id="admin_name" placeholder="Enter Admin Name" required
                            :disabled="roleId === 1" v-model="companyDatas.adminName">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="admin_email">Admin Email</label>
                        <input type="text" class="form-control" id="admin_email" placeholder="Enter Admin Email" required
                            :disabled="roleId === 1" v-model="companyDatas.adminEmail">
                    </div>
                </div>

                <div class="form-group">
                    <label for="address">Address</label>
                    <textarea class="form-control" placeholder="Input Address" id="address" rows="3" required
                        :disabled="roleId === 1" v-model="companyDatas.address"></textarea>
                </div>

                <button type="submit" v-show="roleId === 2" class="btn add-company">{{ submitBtn }}</button>
            </form>
            <!--------------------- END ADD COMPANY -------------------------->

        </div>
    </section>
</template>
  
<script>
import companyService from '@/services/companyService.js';
import approvService from '@/services/approvalService.js';
// import adds from '@/services/userService.js';
export default {
    name: "CompanyS",

    // DATA
    data() {
        return {
            submitBtn: null,
            tableBtn: null,
            roleId: this.$session.get("jwt").roleId,

            apprvData: {
                id: null,
                status: null,
                comName: null,
                reqType: null,
                reqBy: null,
                createdTime: null,
                apprBy: null,
                updateTime: null,
                companyId: null,
            },

            companyDatas: {
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
                updateTime: null,
            },

            itemsPerPage: 7,
            currentPage: 1,

            companyData: {
            },

            showForm: false,
            showDetail: false,
        }
    },
    methods: {
        updateFunc(data) {
            this.showForm = !this.showForm;
            this.submitBtn = "Update Company"
            this.companyDatas = data;
        },

        addFunc() {
            for (const property in this.companyDatas) {
                this.companyDatas[property] = null;
            }
            this.showForm = !this.showForm;
            this.submitBtn = "Add Company"
        },

        SubmitCompany() {
            let data = this.companyDatas
            let apprv = this.apprvData


            if (this.submitBtn === "Add Company") {
                data.status = 1;
                data.createdBy = this.$session.get('email')

                companyService.upload(data)
                    .then((response) => {
                        console.log("add Company");
                        console.log(response.status);
                        apprv.comName = data.comName;
                        apprv.companyId = response.data.id;
                        apprv.reqBy = data.createdBy;
                        apprv.reqType = "Add Company";
                        apprv.status = data.status;
                        this.$toast.success('CompanyData has been successfully added!', {
                            position: 'top-right',
                            timeout: 2500,
                        });

                        // add table approv
                        approvService.saveApprov(apprv)
                            .then((response) => {
                                console.log("add Approv");
                                console.log(response.status);
                                this.showForm = !this.showForm;
                                this.getCompany();
                            })
                            .catch((e) => {
                                console.log(e);
                                this.$toast.error('Error!', {
                                    position: 'top-right',
                                    timeout: 2500,
                                });
                            });

                        // adds
                        // adds.register(akun)
                        //     .then((response) => {
                        //         // this.companyData = response.data;
                        //         console.log("add User");
                        //         console.log(response.status);
                        //         this.$toast.success('Company Data has been successfully added!', {
                        //             position: 'top-right',
                        //             timeout: 2500,
                        //         });
                        //         this.showForm = !this.showForm;
                        //         this.getCompany();
                        //     })
                        //     .catch(() => {
                        //         this.$toast.error('Error!', {
                        //             position: 'top-right',
                        //             timeout: 2500,
                        //         });
                        //     });
                    })
                    .catch(() => {
                        this.$toast.error('Error!', {
                            position: 'top-right',
                            timeout: 2500,
                        });
                    });
            } else {
                data.status = 1
                data.createdBy = this.$session.get('email');
                companyService.upload(data)
                    .then((response) => {
                        console.log("add Company");
                        console.log(response.status);
                        this.$toast.success('Company Data has been successfully Update!', {
                            position: 'top-right',
                            timeout: 2500,
                        });
                        this.showForm = !this.showForm;
                        //
                        apprv.comName = data.comName;
                        apprv.companyId = response.data.id;
                        apprv.reqBy = data.createdBy;
                        apprv.reqType = "Update Company";
                        apprv.status = data.status;

                        // add table approv
                        approvService.saveApprov(apprv)
                            .then((response) => {
                                console.log("add Approv");
                                console.log(response.status);
                                this.showForm = !this.showForm;
                                this.getCompany();
                            })
                            .catch((e) => {
                                console.log(e);
                                this.$toast.error('Error!', {
                                    position: 'top-right',
                                    timeout: 2500,
                                });
                            });
                    })
                    .catch(() => {
                        this.$toast.error('Error!', {
                            position: 'top-right',
                            timeout: 2500,
                        });
                    });
            }



        },

        // GET COMPANY
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

        previousPage() {
            this.currentPage--;
        },

        showDetails(test) {
            this.showDetail = !this.showDetail
            for (const property in this.updateCompany) {
                this.updateCompany[property] = test[property];
            }
        },


        nextPage() {
            this.currentPage++;
        },

        BackButton(back) {
            if (back == 1) {
                this.showForm = false
                this.getCompany();
            }
        },

    },

    computed: {
        // hitung jumlah halaman
        pageCount() {
            const itemCount = this.companyData.length;
            const pageCount = Math.ceil(itemCount / this.itemsPerPage);
            return pageCount;
        },
        // ambil data sesuai halaman saat ini
        paginatedData() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            return this.companyData.slice(startIndex, endIndex);
        },
    },

    mounted() {
        this.getCompany();

        if (this.roleId === 1) {
            this.tableBtn = "Detail"
        } else {
            this.tableBtn = "Update"
        }
    },


};
</script>
  
<style scoped>
.form-company {
    margin-left: 95px;
    margin-right: 95px;
}

table {
    width: 70vw;
    margin-left: 50px;
    margin-top: 10px;
}



thead {
    background-color: #695cfe;
    color: white;
    text-align: center;
}

form {
    width: 60vw;
    padding: 40px 40px;
    border-radius: 25px;
    height: 90vh;
}

.iseng {
    margin-left: 100px;
}

.add-company {
    background-color: #695cfe;
    color: white;
    width: 100%;
    border-radius: 10px;
    margin-top: 15px;
}

.btn-add {
    display: flex;
    justify-content: flex-end;
    margin-right: 80px;
}

.btn-add-com {
    background-color: #695cfe;
    color: white;
    border-radius: 10px;
    height: 40px;

}

.btn-detail {
    background-color: #5a4de5;
    color: white;
}

.btn-detail:hover {
    background-color: #4a3ec6;
    color: white;
}

.back {
    background-color: #695cfe;
    color: white;
    border-radius: 10px;
    margin-right: 70px;
    margin-left: 95px;


}

.btn-add-com:hover {
    color: white;
    background-color: #5a4de5;
}

h6 {
    font-size: 25px;
}

.next {
    /* background-color: #695cfe; */
    position: fixed;
    bottom: 0;
    left: 700px;
    margin-bottom: 10px;
}

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
</style>