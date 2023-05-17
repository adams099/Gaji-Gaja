<template>
    <section class="home">
        <div class="text text-center" v-if="!showDetail">All Company</div>
        <div class="table">
            <div class="d-flex flex-row justify-content-between mb-2">
                <h5 class="color-text" v-if="!showDetail">Daftar Company</h5>

                <div>
                    <div class="container mr-3 d-flex">
                        <div>
                            <!------------------------ MODAL BOX --------------------->
                            <div>
                                <b-button id="show-btn" @click="showModal" v-if="!showDetail" class="btn-primary shadow"
                                    style="margin-left: 272px;">Add
                                    Company</b-button>
                                <b-button id="show-btn " v-if="showDetail" @click="showDetail = false"
                                    class="btn-primary mt-3">Back</b-button>
                                <b-modal ref="my-modal" hide-footer title="Add Company">
                                    <div class="d-block">
                                        <div class="form-add">

                                            <div class="form-group">
                                                <label for="name_company">Company Name</label>
                                                <input type="text" v-model="companyDatas.comName" class="form-control"
                                                    id="name_company" placeholder="Enter Name Company" />
                                            </div>
                                            <div class="form-group">
                                                <label for="npwp">NPWP</label>
                                                <input type="text" v-model="companyDatas.comTaxNum" class="form-control"
                                                    id="npwp" placeholder="Enter NPWP" />
                                            </div>
                                            <div class="form-group">
                                                <label for="address">Address</label>
                                                <input type="text" v-model="companyDatas.address" class="form-control"
                                                    id="address" placeholder="Enter Address" />
                                            </div>
                                            <div class="form-group">
                                                <label for="email_company">Email</label>
                                                <input type="email" v-model="companyDatas.mailAddress" class="form-control"
                                                    id="email_company" placeholder="Enter Email Company" />
                                            </div>
                                            <div class="form-group">
                                                <label for="postal_code">Code Pos</label>
                                                <input type="text" class="form-control" v-model="companyDatas.postal"
                                                    id="postal_code" placeholder="Enter Code Pos" />
                                            </div>
                                            <div class="form-group">
                                                <label for="admin_name">Admin Name</label>
                                                <input type="text" v-model="companyDatas.adminName" class="form-control"
                                                    id="admin_name" placeholder="Enter Admin Name" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="dflex justify-content-center">
                                        <b-button variant="primary" block @click="SubmitCompany">Submit</b-button>
                                        <b-button variant="danger" block @click="toggleModal">Cancel</b-button>
                                    </div>
                                </b-modal>
                            </div>
                            <!--------------------- END ADD COMPANY  --------------------------->

                            <!---------------------- START DETAIL COMPANY ---------------------->
                            <div class="flex-column">
                                <div class="detail shadow">
                                    <h5 class="color-text mt-3 mb-3 text-center text-color text-white " v-if="showDetail"
                                        style="font-size: 20px; ">
                                        Detail Company</h5>
                                </div>
                            </div>

                            <div class="form justify-content-center d-flex flex-row bg-white shadow-lg" v-if="showDetail">

                                <form class="form-detail-company flex-row" @submit.prevent="updateCompanyFunc">
                                    <div class="form-group">
                                        <label for="name_company">Company Name</label>
                                        <input type="text" class="form-control company-detail" id="name_company"
                                            placeholder="Company Name" required v-model="updateCompany.comName">
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="email_company">Email Company</label>
                                            <input type="email" class="form-control" id="email_company" placeholder="Email"
                                                required v-model="updateCompany.mailAddress">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="NPWP">NPWP</label>
                                            <input type="text" class="form-control" id="NPWP"
                                                placeholder="Enter NPWP Number" required v-model="updateCompany.comTaxNum">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="Address">Address</label>
                                        <input type="text" class="form-control company-detail" id="Address"
                                            placeholder="Address" required v-model="updateCompany.address">
                                    </div>
                                    <div class="form-group">
                                        <label for="postal_code">Postal Code</label>
                                        <input type="text" class="form-control company-detail" id="postal_code"
                                            placeholder="Postal Code" required v-model="updateCompany.postal">
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="admin_name">Admin Name</label>
                                            <input type="text" class="form-control" id="admin_name"
                                                placeholder="Enter Admin Name" disabled v-model="updateCompany.adminName">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="admin_email">Admin Email</label>
                                            <input type="text" class="form-control" id="admin_email"
                                                placeholder="Enter Admin Email" disabled v-model="updateCompany.adminEmail">
                                        </div>

                                    </div>

                                    <button type="submit" class="btn btn-primary mt-2">Update</button>
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
                                                    <button @click="accept" class="btn btn-success mr-3">Accept</button>
                                                    <button @click="reject" class="btn btn-danger mr-3">Reject</button>
                                                    <button @click="deadactive"
                                                        class="btn btn-primary mr-3">Deadactive</button>
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
                <!-- END MODAL BOX -->
            </div>

            <!------------------ START TABLE ------------------->
            <table class="table " v-show="!showDetail">
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
                    <tr class=" baris text-center" v-for="(item, index) in paginatedData" :key="index">
                        <th scope="row" class="text-center">{{ item.id }}</th>
                        <td>{{ item.comName }}</td>
                        <td>{{ item.mailAddress }}</td>
                        <button type="button" class="status blue" v-if="item.status == 1">In Review</button>
                        <button type="button" class="status green" v-else-if="item.status == 2">Active</button>
                        <button type="button" class="status red" v-else-if="item.status == 3">Rejected</button>
                        <button type="button" class="status salmon" v-else>Deactive</button>
                        <td class="text-center">
                            <button type="button" class="btn btn-primary" @click="showDetails(item)">Detail</button>
                            <button type="button" class="btn btn-delete text-white "
                                @click="showModalStatuss(item)">Status</button>
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
            <div class="row d-flex justify-content-center next color-text" v-if="!showDetail">
                <button type="button" class="btn btn-success" @click="previousPage"
                    :disabled="currentPage == 1">Previous</button>
                <p class="ml-4 mr-4 font-italic mt-2">{{ currentPage }} / {{ pageCount }}</p>
                <button type="button" class="btn btn-success" @click="nextPage"
                    :disabled="currentPage == pageCount">Next</button>
            </div>
            <!------------------ END TABLE ------------------>

            <!-- iseng  -->

            <!-- 
                            <div class=" form justify-content-center d-flex flex-row bg-white shadow-lg"
                    v-if="showDetail"> -->
        </div>

    </section>
</template>
  
<script>
import companyService from '@/services/companyService.js';
import adds from '@/services/userService.js';

export default {
    name: "CompanyS",

    // DATA
    data() {
        return {
            itemsPerPage: 7,
            currentPage: 1,

            companyData: {
            },

            companyDatas: {
                comName: null,
                mailAddress: null,
                comTaxNum: null,
                address: null,
                siup: null,
                postal: null,
                adminName: null,
                adminEmail: null,
                status: 1,
                createdBy: null,
                createdTime: null,
                apprBy: null,
                updateTime: null
            },

            updateCompany: {
                id: null,
                comName: null,
                comTaxNum: null,
                address: null,
                mailAddress: null,
                postal: null,
                adminName: null,
                adminEmail: null,
                status: null,
            },

            buatAkun: {
                name: null,
                pass: null,
                email: null,
                roleId: 3,
                statId: 1,
                username: null,
                createdBy: null,
                approved: null
            },
            showDetail: false,
            showModalStatus: false,

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
        updateCompanyFunc() {
            let data = this.updateCompany;
            // console.log(data);
            companyService.upload(data)
                .then((response) => {
                    // console.log(response.data);
                    console.log(response.status);
                    this.$toast.success('Company Data has been successfully Update!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    this.getCompany()
                    this.showDetail = !this.showDetail;
                })
                .catch(() => {
                    this.$toast.error('Error', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                });

        },

        previousPage() {
            this.currentPage--;
        },
        showDetails(test) {
            this.showDetail = !this.showDetail
            // console.log(test);
            for (const property in this.updateCompany) {
                this.updateCompany[property] = test[property];
            }
        },

        showModalStatuss(test) {
            this.showModalStatus = !this.showModalStatus
            // console.log(test);
            for (const property in this.updateCompany) {
                this.updateCompany[property] = test[property];
            }
        },
        // navigasi ke halaman berikutnya
        nextPage() {
            this.currentPage++;
        },

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
            for (const property in this.companyDatas) {
                this.companyDatas[property] = null
            }
            this.$refs["my-modal"].toggle("#toggle-btn");
        },
        toggleModalDetail() {
            this.$refs["company-modal"].toggle("#toggle-btn");
        },

        //test doang ya
        accept() {
            // handle accept action
            let data = this.updateCompany;
            data.status = 2;
            companyService.upload(data)
                .then((response) => {
                    this.$toast.success('Company status has been successfully Update!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    // console.log(response.data);
                    console.log(response.status);
                    this.showModalStatus = false;
                    this.getCompany()
                })
                .catch(() => {
                    this.$toast.error('Error', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                });
            this.showModalStatus = false
        },
        reject() {
            // handle reject action
            let data = this.updateCompany;
            data.status = 3;
            companyService.upload(data)
                .then((response) => {
                    this.$toast.success('Company status has been successfully Rejected!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    console.log(response.status);
                    this.showModalStatus = false;
                    this.getCompany()
                })
                .catch(() => {
                    this.$toast.error('Error', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                });
        },
        deadactive() {
            // handle review action
            let data = this.updateCompany;
            data.status = 4;
            companyService.upload(data)
                .then((response) => {
                    this.$toast.success('Company status has been successfully Deactive!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    console.log(response.status);
                    this.showModalStatus = false;
                    this.getCompany()
                })
                .catch(() => {
                    this.$toast.error('Error', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                });
        },

        // GET COMPANY
        getCompany() {
            companyService
                .getAll()
                .then((response) => {
                    this.companyData = response.data;
                    // console.log(this.companyData);
                    console.log("get Company");
                })
                .catch((e) => {
                    console.log(e);
                });
        },
        SubmitCompany() {
            let datas = this.buatAkun
            let data = this.companyDatas
            datas.email = data.mailAddress
            datas.pass = "testing"
            datas.createdBy = this.$session.get('email')
            datas.name = data.adminName
            datas.username = data.adminName + 123
            data.status = 1;
            data.adminEmail = this.$session.get('email')
            data.sender = this.$session.get("jwt").data.substr(23, this.$session.get("jwt").data.length)
            // console.log(data);
            companyService.upload(data)
                .then((response) => {
                    console.log("add Company");
                    console.log(response.status);
                    adds.register(datas)
                        .then((response) => {
                            // this.companyData = response.data;
                            console.log("add User");
                            console.log(response.status);
                            this.$toast.success('Company Data has been successfully added!', {
                                position: 'top-right',
                                timeout: 2500,
                            });
                            this.toggleModal()
                            this.getCompany()
                        })
                        .catch(() => {
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
        },

        // Post Data Company


    },

    // MOUNTED
    mounted() {
        this.getCompany();

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

thead {
    background-color: #695cfe;
    color: aliceblue;
}

.table {
    padding-left: 20px;
    padding-right: 20px;
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

form {
    width: 60vw;
    padding: 40px 40px;
    border-radius: 20px;
}

.form {
    /* left: 200px; */
    margin-left: 100px;
    border-radius: 20px;
    margin-bottom: 5px;
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
    background-color: transparent;

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

.detail {
    margin-left: 100px;
    background-color: #695cfe;
    width: 200px;
    border-radius: 20px;
    font-size: 5px;

}

/* .status {
  margin-top: 17px;
  background-color: transparent;
  border: 2px solid blue;
  color: blue;
  border-radius: 5px;
  font-size: 13px;
  padding: 3px;

} */

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
</style>