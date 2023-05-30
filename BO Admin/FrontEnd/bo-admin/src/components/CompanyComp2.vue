<template>
    <section class="home w-80" style="height: 100%;">
        <div class="btn-add mt-3 d-flex flex-row text-white  justify-content-between  align-items-center">
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
            <div class="form-company d-flex flex-row justify-content-between align-items-center">
                <h6 class=" color-text">Form {{ roleId == 1 ? "Detail" : title }} Company</h6>
                <button class="btn back text-white mb-3 mt-2" v-if="showForm" v-on:click="BackButton(1)">
                    <i class="fas fa-arrow-left text-white"></i> Back
                </button>
            </div>
            <form class="iseng form-detail-company flex-row bg-white shadow-lg" @submit.prevent="SubmitCompany">

                <div class="row d-flex flex-row justify-content-center" v-if="submitBtn == 'Update Company'">
                    <h6 class="text-center mr-2 text-secondary ">Status Company Saat ini</h6>
                    <h6 :class="colorStatus">{{ status }}</h6>
                </div>

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
                        <input type="number" class="form-control company-detail" id="postal_code" placeholder="Postal Code"
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
                            :disabled="roleId === 1 || submitBtn === 'Update Company'" v-model="companyDatas.adminEmail">
                    </div>
                </div>

                <div class="form-group" v-if="submitBtn == 'Add Company'">
                    <div class="mb-3">
                        <label for="formFile" class="form-label">Choose File</label>
                        <input @change="handleFileUpload('att', $event)" class="form-control" type="file" id="formFile">
                    </div>
                </div>

                <div class="form-group">
                    <label for="address">Address</label>
                    <textarea class="form-control" placeholder="Input Address" id="address" rows="3" required
                        :disabled="roleId === 1" v-model="companyDatas.address"></textarea>
                </div>

                <button type="submit" v-if="companyDatas.status == 2 || submitBtn == 'Add Company'" v-show="roleId === 2"
                    class="btn add-company mb-4 mt-4">{{ submitBtn }}</button>
                <button class="btn btn-deactive mb-4" v-if="companyDatas.status == 2 || companyDatas.status == 4"
                    @click.prevent="deactiveAlert()">{{ actBtn }}</button>
            </form>
            <!--------------------- END ADD COMPANY -------------------------->
        </div>
    </section>
</template>
  
<script>
import companyService from '@/services/companyService.js';
import approvService from '@/services/approvalService.js';
import adds from '@/services/userService.js';
export default {
    name: "CompanyS",

    // DATA
    data() {
        return {
            status: null,
            submitBtn: null,
            title: null,
            tableBtn: null,
            roleId: this.$session.get("jwt").roleId,
            colorStatus: null,
            actBtn: null,

            akun: {
                email: null,
            },
            isFound: false,

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
                file: null,
            },

            itemsPerPage: 7,
            currentPage: 1,
            companyData: {},
            showForm: false,
            showDetail: false,
            attendance: null,
        }
    },
    methods: {
        BackButton(back) {
            if (back == 1) {
                this.showForm = false
                this.getCompany();
            }
        },

        deactiveAlert() {
            let data = this.companyDatas;

            this.$swal({
                title: 'Are you sure?',
                text: 'You can\'t revert your action',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: data.status === 2 ? 'Deactive it!' : 'Reactive it!',
                cancelButtonText: 'No, Cancel!',
                showCloseButton: true,
                showLoaderOnConfirm: true
            }).then((result) => {
                if (result.value) {

                    if (data.status === 2) {
                        this.deactiveFunc();
                    } else {
                        this.reactiveFunc();
                    }
                } else {
                    this.BackButton(1);
                    this.$swal({
                        confirmButtonText: "Close",
                        icon: "error",
                        title: "Cancelled",
                        text: "Your file is still intact"
                    })
                }
            })
        },

        deactiveFunc() {
            let data = this.companyDatas;
            let apprv = this.apprvData;
            data.status = 1
            data.createdBy = this.$session.get('email');

            companyService.upload(data)
                .then((response) => {
                    console.log("add Company");
                    console.log(response.status);
                    this.showForm = !this.showForm;
                    apprv.comName = data.comName;
                    apprv.companyId = response.data.id;
                    apprv.reqBy = data.createdBy;
                    apprv.reqType = "Deactive Company";
                    apprv.status = data.status;

                    // add table approv
                    approvService.saveApprov(apprv)
                        .then((response) => {
                            console.log("add Approv");
                            console.log(response.status);
                            this.showForm = !this.showForm;
                            this.getCompany();
                            this.BackButton(1);
                            this.$swal({
                                title: "Deactive",
                                text: "Wait for other admin to Approv it!",
                                icon: 'success',
                                confirmButtonText: 'Close'
                            })
                        })
                        .catch((e) => {
                            console.log(e);
                            this.$toast.error('Error!', {
                                position: 'top-right',
                                timeout: 2500,
                            });
                        });
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
                        this.$toast.error('Error!', {
                            position: 'top-right',
                            timeout: 2500,
                        });
                    }
                });
        },

        reactiveFunc() {
            let data = this.companyDatas;
            let apprv = this.apprvData;
            data.status = 1
            data.createdBy = this.$session.get('email');

            companyService.upload(data)
                .then((response) => {
                    console.log("add Company");
                    console.log(response.status);
                    this.showForm = !this.showForm;
                    apprv.comName = data.comName;
                    apprv.companyId = response.data.id;
                    apprv.reqBy = data.createdBy;
                    apprv.reqType = "Reactive Company";
                    apprv.status = data.status;

                    // add table approv
                    approvService.saveApprov(apprv)
                        .then((response) => {
                            console.log("add Approv");
                            console.log(response.status);
                            this.showForm = !this.showForm;
                            this.getCompany();
                            this.BackButton(1);
                            this.$swal({
                                title: "Reactive",
                                text: "Wait for other admin to Approv it!",
                                icon: 'success',
                                confirmButtonText: 'Close'
                            })
                        })
                        .catch((e) => {
                            console.log(e);
                            this.$toast.error('Error!', {
                                position: 'top-right',
                                timeout: 2500,
                            });
                        });
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
                        this.$toast.error('Error!', {
                            position: 'top-right',
                            timeout: 2500,
                        });
                    }
                });
        },

        updateFunc(data) {
            this.showForm = !this.showForm;
            this.submitBtn = "Update Company"
            this.title = "Update"
            this.companyDatas = data;

            if (data.status == 1) {
                this.status = "In Review"
                this.colorStatus = "review"

            } else if (data.status == 2) {
                this.status = "Active"
                this.colorStatus = "active"
                this.actBtn = "Deactive"

            } else if (data.status == 3) {
                this.status = "Reject"
                this.colorStatus = "reject"

            } else {
                this.status = "Deactive"
                this.colorStatus = "deactive"
                this.actBtn = "Reactive"

            }
        },

        addFunc() {
            for (const property in this.companyDatas) {
                this.companyDatas[property] = null;
            }
            this.showForm = !this.showForm;
            this.submitBtn = "Add Company"
            this.title = "Add"
        },

        handleFileUpload(fieldName, event) {
            let file = event.target.files[0];

            if (fieldName === 'att') {
                let fileName = 'attachment';
                let fileExtension = file.type.split("/")[1];
                let newFile = new File([file], fileName + "." + fileExtension, { type: file.type });
                this.attendance = newFile;
            }
        },

        async SubmitCompany() {
            let data = this.companyDatas;
            let apprv = this.apprvData;
            let dataAkun = this.akun;
            let found = this.isFound;

            let idCompany = null;
            let file1 = this.attendance

            dataAkun.email = this.companyDatas.adminEmail;

            await adds.findByEmail(dataAkun)
                .then((response) => {
                    if (response.status == 200) {
                        found = true
                    }
                })
                .catch(() => {
                    console.log("valid-email");
                });

            if (this.submitBtn === "Add Company") {
                if (found) {
                    this.$toast.warning('Admin Email is already in use!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                } else {
                    data.status = 1;
                    data.createdBy = this.$session.get('email')
                    data.file = file1.name;

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

                            // Uploda
                            idCompany = apprv.companyId;

                            companyService.uploadImage(file1, idCompany)
                                .then(response => {
                                    console.log(response.status);
                                    console.log("uploading-attachment");
                                })
                                .catch(e => {
                                    console.log(e);
                                });

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
                                this.$toast.error('Error!', {
                                    position: 'top-right',
                                    timeout: 2500,
                                });
                            }
                        });

                }
            } else {
                data.status = 1
                data.createdBy = this.$session.get('email');

                this.$swal({
                    title: 'Are you sure?',
                    text: 'You can\'t revert your action',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: 'Update it!',
                    cancelButtonText: 'No, Cancel!',
                    showCloseButton: true,
                    showLoaderOnConfirm: true
                }).then((result) => {
                    if (result.value) {
                        companyService.upload(data)
                            .then((response) => {
                                console.log("add Company");
                                console.log(response.status);
                                this.showForm = !this.showForm;
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
                                        this.BackButton(1);
                                        this.$swal('Update', 'Wait for other admin to approv it!', 'success')
                                    })
                                    .catch((e) => {
                                        console.log(e);
                                        this.$toast.error('Error!', {
                                            position: 'top-right',
                                            timeout: 2500,
                                        });
                                    });
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
                                    this.$toast.error('Error!', {
                                        position: 'top-right',
                                        timeout: 2500,
                                    });
                                }
                            });
                    } else {
                        this.BackButton(1);
                        this.$swal({
                            confirmButtonText: "Close",
                            icon: "error",
                            title: "Cancelled",
                            text: "Your file is still intact"
                        })
                    }
                })
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
}

.iseng {
    margin-left: 100px;
}

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

.add-company {
    background-color: #695cfe;
    color: white;
    width: 100%;
    height: 50px;
    border-radius: 10px;
    margin-top: 15px;
    border-radius: 15px;
}

.btn-deactive {
    color: grey;
    width: 100%;
    height: 50px;
    border-radius: 10px;
    border-radius: 15px;
    border: 1px solid grey;
}

.btn-deactive:hover {
    background-color: orange;
    color: white;
    border: none;
}

.add-company:hover {
    background-color: #4a3ec6;
    color: white;
}

.btn-add {
    display: flex;
    justify-content: flex-end;
    /* margin-right: 80px; */
}

.btn-add-com {
    background-color: #695cfe;
    color: white;
    border-radius: 10px;
    height: 40px;
    margin-right: 5rem;
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
    margin-right: 11rem;


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
    width: 75vw;
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

/* .btn,
.btn-add-com {
    margin-right: 80px;
} */

input[type=file]::file-selector-button {
    margin-right: 20px;
    border: none;
    background: #5a4de5;
    padding: 5px 10px;
    border-radius: 7px;
    color: #fff;
    cursor: pointer;
    transition: background .2s ease-in-out;
}

input[type=file]::file-selector-button:hover {
    background: #4a3ec6;
}

input[type=file] {
    width: 100%;
    max-width: 100%;
    color: #444;
    /* padding: 10px; */
    height: 47px;
    background: #fff;
    border-radius: 10px;
    border: 1px solid #555;
}
</style>