<template>
    <section class="home w-80" style="height: 100%;">
        <div class="btn-add mt-3 d-flex flex-row text-white  justify-content-between  align-items-center">
            <div class="text text-center" v-if="!showForm" style="font-size: 25px;">Employee Table</div>

            <button class="btn btn-add-com" @click="addFunc()" v-if="roleId === 3" v-show="!showForm"> <i
                    class="fa-sharp fa-regular fa-plus"></i> Add
                Employee</button>
        </div>

        <!------------------ START TABLE ------------------->
        <div class="table-responsive text-nowrap" v-show="!showForm">
            <table class="table table-striped w-auto">
                <thead class="text-center">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col" style="width: 200px;">Full Name</th>
                        <th scope="col" style="width: 200px;">Mobile Phone</th>
                        <th scope=" col" style="width: 200px;">NIP</th>
                        <th scope="col" style="width: 200px;">Division</th>
                        <th scope="col" style="width: 200px;">Salary</th>
                        <th scope="col">Join Date</th>
                        <th scope="col" style="width: 120px;">Status</th>
                        <th scope="col" style="width: 200px;">Action</th>
                    </tr>
                </thead>
                <tbody v-if="employeeData.length > 0">
                    <tr class=" baris text-center shadow-lg bg-white" v-for="(item, index) in paginatedData" :key="index">
                        <th scope="row" class="text-center">{{ item?.id }}</th>
                        <td>{{ item?.fullName }}</td>
                        <td>{{ item?.mobilePhone }}</td>
                        <td>{{ item?.nip }}</td>
                        <td>{{ item?.division }}</td>
                        <td>{{ item?.salary }}</td>
                        <td>{{ item?.joinDate }}</td>
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
                        <td colspan="9" class="msg-null text-center">
                            <h3 class="color-text">Saat ini Tidak Ada Data Terkini !</h3>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>


        <div class="row d-flex justify-content-center next color-text" v-if="!showForm && employeeData.length > 7">
            <button type="button" class="btn btn-success" @click="previousPage"
                :disabled="currentPage == 1">Previous</button>
            <p class="ml-4 mr-4 font-italic mt-2">{{ currentPage }} / {{ pageCount }}</p>
            <button type="button" class="btn btn-success" @click="nextPage"
                :disabled="currentPage == pageCount">Next</button>
        </div>

        <!------------------ END TABLE ------------------>

        <!--------------------- START ADD COMPANY -------------------------->
        <div class="form" v-show="showForm">
            <div class="form-company d-flex flex-row align-items-center">
                <!-- <h6 class=" color-text">Form {{ roleId == 3 ? "Detail" : title }} Employee</h6> -->
                <button class="btn back mr-2 text-white mb-3 mt-2" v-if="showForm" v-on:click="BackButton(1)">
                    <i class="fas fa-arrow-left text-white"></i> Back
                </button>
                <button class=" btn-edit text-white mb-3 mt-2" v-if="submitBtn == 'Update Employee'"
                    @click="toggleShowUpdate()">
                    <i class="fa-solid fa-pen"></i> Edit
                </button>


            </div>
            <form class="iseng form-detail-company flex-row bg-white shadow-lg" @submit.prevent="SubmitEmployee">

                <div class="row d-flex flex-row justify-content-center" v-if="submitBtn == 'Update Employee'">
                    <h6 class="text-center mr-2 text-secondary ">Status Employee Saat ini</h6>
                    <h6 :class="colorStatus">{{ status }}</h6>
                </div>

                <div class="form-group">
                    <label for="name_employee">Full Name</label>
                    <input type="text" class="form-control company-detail" id="name_employee" placeholder="Employee Name"
                        required v-model="employeeData.fullName" :disabled="roleId === 1">
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="email_employee">Email Employee</label>
                        <input type="email" class="form-control" id="email_employee" placeholder="Email" required
                            v-model="employeeData.companyEmail" :disabled="roleId === 1">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="phone">Mobile Phone</label>
                        <input type="number" class="form-control" id="phone_employee" placeholder="Enter phone Number"
                            required v-model="employeeData.mobilePhone" :disabled="roleId === 1">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="nik_employee">NIK</label>
                        <input type="number" class="form-control company-detail" id="nik_employee" placeholder="Postal Code"
                            required v-model="employeeData.nik" :disabled="roleId === 1">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="bday">Date of Birth</label>
                        <input type="date" class="form-control" id="bday" required v-model="employeeData.dateBirth"
                            :disabled="roleId === 1">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="nip_employee">NIP</label>
                        <input type="number" class="form-control" id="nip_employee" placeholder="Enter NIP" required
                            v-model="employeeData.nip" :disabled="roleId === 1">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="join_date">Join Date</label>
                        <input type="date" class="form-control" id="join_date" required v-model="employeeData.joinDate"
                            :disabled="roleId === 1">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="npwp_employee">NPWP</label>
                        <input type="number" class="form-control" id="npwp_employee" placeholder="Enter NPWP" required
                            v-model="employeeData.npwp" :disabled="roleId === 1">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="salary">Salary</label>
                        <input type="number" class="form-control" id="salary" placeholder="Enter Salary" required
                            v-model="employeeData.salary" :disabled="roleId === 1">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="division">Division</label>
                        <input type="text" class="form-control" id="division" placeholder="Enter Division" required
                            v-model="employeeData.division" :disabled="roleId === 1">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="leave">Leaves Dates</label>
                        <input type="number" class="form-control" id="leave" placeholder="Enter leave date" required
                            v-model="employeeData.leaveDays" :disabled="roleId === 1">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="supervisor">Direct Supervisor</label>
                        <input type="text" class="form-control" id="supervisor" placeholder="Enter Supervisor" required
                            v-model="employeeData.directSupervisor" :disabled="roleId === 1">
                    </div>
                    <div class="form-group" v-if="submitBtn == 'Add Employee'">
                        <div class="mb-3">
                            <label for="formFile" class="form-label">Choose File</label>
                            <input @change="handleFileUpload('att', $event)" class="form-control" type="file" id="formFile">
                        </div>
                    </div>
                </div>


                <button class=" update-btn text-center"
                    v-if="showUpdateBtn && submitBtn == 'Update Employee'">Update</button>

                <button type="submit" v-if="employeeData.status == 2 || submitBtn == 'Add Employee'" v-show="roleId === 3"
                    class="btn add-employee mb-4 mt-4">{{ submitBtn }}</button>

                <button class="btn btn-deactive mb-4" v-if="employeeData.status == 2 || employeeData.status == 4"
                    @click.prevent="deactiveAlert()">{{ actBtn }}</button>
            </form>
            <!--------------------- END ADD COMPANY -------------------------->
        </div>
    </section>
</template>
  
<script>
import employeeService from '@/services/employeeService';
import approvalService from '@/services/approvalService';
import adds from '@/services/userService.js';

export default {
    name: "EmployeeS",

    // DATA
    data() {
        return {
            // status: null,

            updateBtn: null,
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
                employeeName: null,
                reqType: null,
                reqBy: null,
                createdTime: null,
                apprBy: null,
                updateTime: null,
                companyId: null,
                employeeId: null
            },

            employeeData: {
                id: null,
                leaveDays: null,
                nik: null,
                nip: null,
                npwp: null,
                fullName: null,
                mobilePhone: null,
                division: null,
                directSupervisor: null,
                status: null,
                createdBy: null,
                approveBy: 1,
                companyEmail: null,
                salary: null,
                dateBirth: null,
                joinDate: null,
                createdTime: null,
                updatedTime: null,
                document: null
            },

            itemsPerPage: 7,
            currentPage: 1,
            employeeDatas: {},
            showForm: false,
            showDetail: false,
            showUpdateBtn: false
        }
    },
    methods: {
        BackButton(back) {
            if (back == 1) {
                this.showForm = false
                this.getEmployee();
            }
        },




        updateFunc(data) {
            this.showForm = !this.showForm;
            this.submitBtn = "Update Employee"
            this.title = "Update"
            this.employeeDatas = data;

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
            for (const property in this.employeeData) {
                this.employeeData[property] = null;
            }
            this.showForm = !this.showForm;
            this.submitBtn = "Add Employee"
            this.title = "Add"
        },

        async SubmitEmployee() {
            let data = this.employeeData;
            let apprv = this.apprvData;
            let dataAkun = this.akun;
            let found = this.isFound;

            let idCompany = null;
            let file1 = this.attendance

            dataAkun.email = this.employeeData.adminEmail;

            await adds.findByEmail(dataAkun)
                .then((response) => {
                    if (response.status == 200) {
                        found = true
                    }
                })
                .catch(() => {
                    console.log("valid-email");
                });

            if (this.submitBtn === "Add Employee") {
                if (found) {
                    this.$toast.warning('Admin Email is already in use!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                } else {
                    data.status = 1;
                    data.createdBy = this.$session.get('email')
                    data.file = file1.name;

                    employeeService.upload(data)
                        .then((response) => {
                            console.log("add Employee");
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
                            approvalService.saveApprov(apprv)
                                .then((response) => {
                                    console.log("add Approv");
                                    console.log(response.status);
                                    this.showForm = !this.showForm;
                                    this.getEmployee();
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

                            employeeService.uploadImage(file1, idCompany)
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
                        employeeService.upload(data)
                            .then((response) => {
                                console.log("add Employee");
                                console.log(response.status);
                                this.showForm = !this.showForm;
                                apprv.comName = data.comName;
                                apprv.companyId = response.data.id;
                                apprv.reqBy = data.createdBy;
                                apprv.reqType = "Update Employee";
                                apprv.status = data.status;

                                // add table approv
                                approvalService.saveApprov(apprv)
                                    .then((response) => {
                                        console.log("add Approv");
                                        console.log(response.status);
                                        this.showForm = !this.showForm;
                                        this.getEmployee();
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
        getEmployee() {
            employeeService
                .getAll()
                .then((response) => {
                    this.employeeData = response.data;
                    console.log("get Employee");
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
            for (const property in this.updateEmployee) {
                this.updateEmployee[property] = test[property];
            }
        },


        toggleShowUpdate() {
            this.showUpdateBtn = !this.showUpdateBtn
        },

        nextPage() {
            this.currentPage++;
        },

    },

    computed: {
        // hitung jumlah halaman
        pageCount() {
            const itemCount = this.employeeData.length;
            const pageCount = Math.ceil(itemCount / this.itemsPerPage);
            return pageCount;
        },
        // ambil data sesuai halaman saat ini
        paginatedData() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            return this.employeeData.slice(startIndex, endIndex);
        },
    },

    mounted() {
        this.getEmployee();

        if (this.roleId === 3) {
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
    /* width: auto; */
    margin-left: 50px;
    margin-top: 10px;
    margin-right: 20px;
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

.add-employee {
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
    /* margin-right: 5rem; */
    margin-right: 20px;
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
    margin-right: 10px;


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

.update-btn {
    background-color: rgb(43, 161, 43);
    color: white;
    width: 100%;
    /* height: 50px; */
    padding-top: 12px;
    padding-bottom: 12px;
    border-radius: 10px;
    margin-top: 15px;
    border-radius: 15px;
    border: none;
}

.update-btn:hover {
    background-color: rgb(30, 126, 30);

}

.btn-edit {
    background-color: rgb(43, 161, 43);
    border: none;
    border-radius: 7px;
    padding: 7px 15px;
}
</style>