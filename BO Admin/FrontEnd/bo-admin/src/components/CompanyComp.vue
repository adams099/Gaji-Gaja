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
                                                <input type="text" class="form-control" id="postal_code"
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
                                            placeholder="name_company">
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="email_company">Email Company</label>
                                            <input type="email" class="form-control" id="email_company" placeholder="Email">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="NPWP">NPWP</label>
                                            <input type="text" class="form-control" id="NPWP" placeholder="NPWP">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="Address">Address</label>
                                        <input type="text" class="form-control company-detail" id="Address"
                                            placeholder="Address">
                                    </div>
                                    <div class="form-group">
                                        <label for="postal_code">Postal Code</label>
                                        <input type="text" class="form-control company-detail" id="postal_code"
                                            placeholder="postal_code">
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="admin_name">Admin Name</label>
                                            <input type="text" class="form-control" id="admin_name">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="admin_email">Admin Email</label>
                                            <input type="text" class="form-control" id="admin_email"
                                                placeholder="Enter Admin Email" />
                                        </div>

                                    </div>

                                    <button type="submit" class="btn btn-primary">Update</button>
                                </form>
                            </div>
                            <!---------------------- END DETAIL COMPANY ------------------------>

                            <!---------------------- SHOW MODAL STATUS ------------------------>


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
            companyData: [],
            showDetail: false,
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

/* SHOW MODAL STYLE */
/* Style untuk modal box */
.modal {
    display: none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.4);
}

/* Style untuk konten modal box */
.modal-dialog {
    position: relative;
    margin: 10% auto;
    padding: 0;
    width: 90%;
    max-width: 600px;
}

.modal-content {
    position: relative;
    background-color: #fefefe;
    margin: auto;
    padding: 0;
    border: 1px solid #888;
    width: 100%;
}

/* Style untuk tombol close */
.close {
    position: absolute;
    top: 0;
    right: 0;
    font-size: 28px;
    font-weight: bold;
    color: #000;
    cursor: pointer;
    padding: 12px 16px;
}



.form {
    width: 1000px;

}

.company-detail {
    width: 700px;
}

/* SHOW MODAL STYLE */
</style>