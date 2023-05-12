<template>
    <section class="home">
        <div class="text text-center">All Company</div>
        <div class="table">
            <div class="d-flex flex-row justify-content-between mb-2">
                <h5 class="color-text">Daftar Company</h5>
                <div>
                    <div class="container mr-3">
                        <div>
                            <!------------------------ MODAL BOX --------------------->
                            <div>
                                <b-button id="show-btn" @click="showModal" class="btn-primary">Add</b-button>
                                <b-modal ref="my-modal" hide-footer title="Add Company">
                                    <div class="d-block">
                                        <div class="form">
                                            <!-- Company Name -->
                                            <div class="form-group">
                                                <label for="name_company">Name Company</label>
                                                <input type="text" class="form-control" id="name_company"
                                                    placeholder="Enter Name Company" />
                                            </div>
                                            <!-- NPWP -->
                                            <div class="form-group">
                                                <label for="npwp">NPWP</label>
                                                <input type="text" class="form-control" id="npwp"
                                                    placeholder="Enter NPWP" />
                                            </div>
                                            <!-- Address -->
                                            <div class="form-group">
                                                <label for="address">Address</label>
                                                <input type="text" class="form-control" id="address"
                                                    placeholder="Enter Address" />
                                            </div>
                                            <!-- Email Company -->
                                            <div class="form-group">
                                                <label for="email_company">Email</label>
                                                <input type="email" class="form-control" id="email_company"
                                                    placeholder="Enter Email Company" />
                                            </div>
                                            <!-- Code Pos -->
                                            <div class="form-group">
                                                <label for="postal_code">Code Pos</label>
                                                <input type="email" class="form-control" id="postal_code"
                                                    placeholder="Enter Code Pos" />
                                            </div>
                                            <!-- Nama Admin -->
                                            <div class="form-group">
                                                <label for="admin_name">Admin Name</label>
                                                <input type="text" class="form-control" id="admin_name"
                                                    placeholder="Enter Email Company" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="dflex justify-content-center">
                                        <b-button variant="primary" block @click="toggleModal">Submit</b-button>
                                        <b-button variant="danger" block @click="toggleModal">Cancel</b-button>
                                    </div>
                                </b-modal>
                            </div>
                            <!--------------------- END ADD COMPANY  --------------------------->

                            <!---------------------- START DETAIL COMPANY ---------------------->
                            <div>
                                <b-modal ref="company-modal" hide-footer title="Detail Company" class="detail-modal ">
                                    <div class="d-block">
                                        <div class="form ">
                                            <!-- Company Name -->
                                            <div class="form-group">
                                                <label for="name_company">Name Company</label>
                                                <input type="text" class="form-control" id="name_company"
                                                    placeholder="Enter Name Company" />
                                            </div>
                                            <!-- NPWP -->
                                            <div class="form-group">
                                                <label for="npwp">NPWP</label>
                                                <input type="text" class="form-control" id="npwp"
                                                    placeholder="Enter NPWP" />
                                            </div>
                                            <!-- Address -->
                                            <div class="form-group">
                                                <label for="address">Address</label>
                                                <input type="text" class="form-control" id="address"
                                                    placeholder="Enter Address" />
                                            </div>
                                            <!-- Email Company -->
                                            <div class="form-group">
                                                <label for="email_company">Email</label>
                                                <input type="email" class="form-control" id="email_company"
                                                    placeholder="Enter Email Company" />
                                            </div>
                                            <!-- Code Pos -->
                                            <div class="form-group">
                                                <label for="postal_code">Code Pos</label>
                                                <input type="email" class="form-control" id="postal_code"
                                                    placeholder="Enter Code Pos" />
                                            </div>
                                            <!-- Nama Admin -->
                                            <div class="form-group">
                                                <label for="admin_name">Admin Name</label>
                                                <input type="text" class="form-control" id="admin_name"
                                                    placeholder="Enter Email Company" />
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Action Button -->
                                    <div class="dflex justify-content-center">
                                        <b-button variant="primary" block @click="toggleModalDetail"
                                            style="border-radius: 10px;">Approve</b-button>
                                        <b-button variant="warning" block @click="toggleModalDetail"
                                            style="border-radius: 10px; color: white;">Reject</b-button>
                                        <b-button variant="danger" block @click="toggleModalDetail"
                                            style="border-radius: 10px;">Deactive</b-button>
                                    </div>
                                </b-modal>
                            </div>
                            <!---------------------- END DETAIL COMPANY ---------------------->
                        </div>
                    </div>
                </div>
                <!-- END MODAL BOX -->
            </div>

            <!------------------ START TABLE ------------------->
            <table class="table">
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
                            <button type="button" class="btn btn-primary" @click="showModalDetail">Detail</button>
                            <button type="button" class="btn btn-delete text-white">Status</button>
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
            companyData: []
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
        }
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
</style>