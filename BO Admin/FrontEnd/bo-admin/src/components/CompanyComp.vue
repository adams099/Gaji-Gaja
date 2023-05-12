<template>
    <section class="home">
        <div class="text text-center" v-if="!showDetail">All Company</div>
        <div class="table">
            <div class="d-flex flex-row justify-content-between mb-2">
                <h5 class="color-text" v-if="!showDetail">Daftar Company</h5>

                <div>
                    <div class="container mr-3">
                        <div>
                            <!------------------------ MODAL BOX --------------------->
                            <div>
                                <b-button id="show-btn" @click="showModal" v-if="!showDetail"
                                    class="btn-primary ">Add</b-button>
                                <b-button id="show-btn " v-if="showDetail" @click="showDetail = false"
                                    class="btn-primary mt-3">Back</b-button>
                                <b-modal ref="my-modal" hide-footer title="Add Company">
                                    <div class="d-block">
                                        <div class="form-add">
                                            <!-- Company Name -->
                                            <div class="form-group">
                                                <label for="name_company">Company Name</label>
                                                <input type="text" class="form-control" id="name_company"
                                                    placeholder="Enter Name Company" v-model="companyData.comName" />
                                                <span class="valid" v-if="error.emailada">Email sudah ada</span>
                                                <span class="valid" v-if="error.email">Email harus diisi!</span>
                                            </div>
                                            <!-- NPWP -->
                                            <div class="form-group">
                                                <label for="npwp">NPWP</label>
                                                <input type="text" class="form-control" id="npwp" placeholder="Enter NPWP"
                                                    v-model="companyData.comTaxNum" />
                                            </div>
                                            <!-- Address -->
                                            <div class="form-group">
                                                <label for="address">Address</label>
                                                <input type="text" class="form-control" id="address"
                                                    placeholder="Enter Address" v-model="companyData.address" />
                                            </div>
                                            <!-- Email Company -->
                                            <div class="form-group">
                                                <label for="email_company">Email</label>
                                                <input type="email" class="form-control" id="email_company"
                                                    placeholder="Enter Email Company" v-model="companyData.mailAddress" />
                                            </div>
                                            <!-- Code Pos -->
                                            <div class="form-group">
                                                <label for="postal_code">Code Pos</label>
                                                <input type="text" class="form-control" id="postal_code"
                                                    placeholder="Enter Code Pos" v-model="companyData.postal" />
                                            </div>
                                            <!-- Nama Admin -->
                                            <div class="form-group">
                                                <label for="admin_name">Admin Name</label>
                                                <input type="text" class="form-control" id="admin_name"
                                                    placeholder="Enter Email Company" v-model="companyData.adminName" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="dflex justify-content-center">
                                        <b-button variant="primary" block @click="toggleModal"
                                            type="submit">Submit</b-button>
                                        <b-button variant="danger" block @click="toggleModal">Cancel</b-button>
                                    </div>
                                </b-modal>
                            </div>
                            <!--------------------- END ADD COMPANY  --------------------------->

                            <!---------------------- START DETAIL COMPANY ---------------------->
                            <div class="flex-column">
                                <div class="detail">
                                    <h5 class="color-text mt-3 mb-3 text-center" v-if="showDetail">Detail Company</h5>
                                </div>
                            </div>

                            <div class="form justify-content-center d-flex flex-row" v-if="showDetail">

                                <form class="form-detail-company flex-row">
                                    <div class="form-group">
                                        <label for="name_company">Company Name</label>
                                        <input type="text" class="form-control company-detail" id="name_company"
                                            placeholder="Company Name" v-model="companyData.comName">
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="email_company">Email Company</label>
                                            <input type="email" class="form-control" id="email_company" placeholder="Email"
                                                v-model="companyData.mailAddress">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="NPWP">NPWP</label>
                                            <input type="text" class="form-control" id="NPWP"
                                                placeholder="Enter NPWP Number" v-model="companyData.comTaxNum">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="Address">Address</label>
                                        <input type="text" class="form-control company-detail" id="Address"
                                            placeholder="Address" v-model="companyData.address">
                                    </div>
                                    <div class="form-group">
                                        <label for="postal_code">Postal Code</label>
                                        <input type="text" class="form-control company-detail" id="postal_code"
                                            placeholder="postal_code" v-model="companyData.postal">
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="admin_name">Admin Name</label>
                                            <input type="text" class="form-control" id="admin_name"
                                                placeholder="Enter Admin Name" v-model="companyData.adminName">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="admin_email">Admin Email</label>
                                            <input type="text" class="form-control" id="admin_email"
                                                placeholder="Enter Admin Email" v-model="companyData.adminEmail">
                                        </div>

                                    </div>

                                    <button type="submit" class="btn btn-primary">Update</button>
                                </form>
                            </div>
                            <!---------------------- END DETAIL COMPANY ------------------------>

                            <!---------------------- SHOW MODAL STATUS ------------------------>
                            <div class="show-modal-status">
                                <div v-if="showModalStatus" class="modal d-flex flex-column">
                                    <div class="modal-content d-flex justify-content-center">
                                        <h5 class="text-center mb-2">Status</h5>
                                        <div class="d-flex flex-column button-action col col-md-6 ">
                                            <button class="btn btn-success button-status">Approve</button>
                                            <button class="btn btn-danger mt-4 mb-4 button-status">Reject</button>
                                            <button class="btn btn-warning button-status mb-2 text-white">Deactice</button>
                                        </div>

                                        <div class="back-button">
                                            <button @click="showModalStatus = false"
                                                class="btn btn-secondary mt-3 ml-3">Back</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!---------------------- END MODAL STATUS ------------------------>
                        </div>
                    </div>
                </div>
                <!-- END MODAL BOX -->
            </div>

            <!------------------ START TABLE ------------------->
            <table class="table" v-show="!showDetail">
                <thead class="text-center">
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Company Name</th>
                        <th scope="col">Email Admin</th>
                        <th scope="col">Status</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody v-if="companyData.length > 0">
                    <tr class=" baris text-center" v-for="(item, index) in companyData" :key="index">
                        <th scope="row" class="text-center">1</th>
                        <td>{{ item.comName }}</td>
                        <td>{{ item.adminEmail }}</td>
                        <td>In Review</td>
                        <td class="text-center">
                            <button type="button" class="btn btn-primary" @click="showDetail = true">Detail</button>
                            <button type="button" class="btn btn-delete text-white"
                                @click="showModalStatus = true">Status</button>
                        </td>
                    </tr>
                </tbody>

                <tbody v-else>
                    <tr class="msg-tr text-center">
                        <td colspan="6" class="msg-null text-center">
                            <h3 class="color-text">Saat ini Tidak Ada Data Company !</h3>
                        </td>
                    </tr>
                </tbody>
            </table>
            <!------------------ END TABLE ------------------>
        </div>
    </section>
</template>
  
<script>
import companyService from '@/services/companyService.js';

export default {
    name: "CompanyS",

    // DATA
    data() {
        return {
            showDetail: false,
            showModalStatus: false,

            // Company Data
            companyData: {
                "comName": null,
                "comTaxNum": null,
                "address": null,
                "mailAddress": null,
                "postal": null,
                "adminName": null,
                "adminEmail": null,
                "status": null,
                "sender": null
            },
            error: {
                "comname": false,
                "comtax": false,
                "address": false,
                "email": false,
                "emailada": false,
                "pass": false,
            }
        }
    },

    methods: {
        // SHOW MODAL BOX
        showModal() {
            this.$refs["my-modal"].show();
        },
        showModalDetail() {
            this.$refs["company-modal"].show();
        },
        hideModal() {
            this.$refs["my-modal"].hide();
        },
        toggleModal() {
            this.$refs["my-modal"].toggle("#toggle-btn");
        },
        toggleModalDetail() {
            this.$refs["company-modal"].toggle("#toggle-btn");
        },

        // GET COMPANY
        getCompany() {
            companyService
                .getAll()
                .then((response) => {
                    this.companyData = response.data;
                    console.log(this.companyData);
                })
                .catch((e) => {
                    console.log(e);
                });
        },

        // Post Data Company


    },

    // MOUNTED
    mounted() {
        this.getCompany();

    }
};
</script>
  
<style scoped>
h5 {
    margin-left: 20px;
    color: gray;
}

thead {
    background-color: #695cfe;
    color: aliceblue;
}

.table {
    padding-left: 20px;
    padding-right: 20px;
}

.btn-danger {
    /* margin-left: 10px; */
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

.add-user {
    background-color: #695cfe;
    color: whitesmoke;
}

.show-botton {
    background-color: #695cfe;
}

.show-botton:hover {
    background-color: #695cfe;
}

.form-select {
    width: 100%;
    height: 40px;
    border: 1px solid rgb(200, 200, 200);
    border-radius: 5px;
}

.btn-primary {
    background-color: #695cfe;
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

.baris {
    background-color: #e4e9f7;
}

.form-detail-company {
    border: 1px solid #695cfe;
    padding: 40px 20px;
    border-radius: 20px;
}


/* Style untuk konten modal box */
/* SHOW MODAL STYLE */

.modal {
    top: 150px;
    left: 200px;
    position: absolute;
    margin: 50px 50px;
    width: 25vw;
    height: 300px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-radius: 15px;
}

.modal-content {
    border: 1px solid grey;
    background-color: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
}

.modal.fade .modal-dialog {
    transition: opacity 0.3s linear;
    opacity: 0;
}

.modal.fade.show .modal-dialog {
    opacity: 1;
}

.button-status {
    width: 250px;
}
</style>