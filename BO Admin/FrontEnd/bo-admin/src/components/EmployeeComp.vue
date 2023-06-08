<template>
    <section class="home " style="height: 100vh;">
        <div class="btn-add mt-3 d-flex flex-row text-white  justify-content-between  align-items-center">
            <div class="text text-center" v-if="!showForm" style="font-size: 25px;">Employee Table</div>

            <button class="btn btn-add-com" @click="addFunc()" v-if="roleId === 3" v-show="!showForm"> <i
                    class="fa-sharp fa-regular fa-plus"></i> Add
                Employee</button>
        </div>

        <!-- Modal search -->


        <b-modal id="modal-1" title="BootstrapVue" hide-footer hide-header ref="modalSearch"
            class="col col-lg-12 bg-primary">
            <form action="" class="form-search p-3 col col-lg-12 p-3" @submit.prevent="s">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text w-100">
                                <i class="fas fa-search"></i>
                            </span>
                        </div>
                        <input type="text" class="form-control input-search"
                            placeholder="Search (Email, Name, Req Type etc)" v-model="snam" required>
                    </div>

                    <div class="d-flex flex-column mt-4 mb-4">
                        <small class="form-text text-muted mb-1">Find By Status</small>
                        <button @click.prevent="sstat = 1"
                            :class="['btn', 'btn-status', 'mb-3', 'text-left', { 'activee': sstat === 1 }]">
                            Need Approve
                        </button>

                        <button @click.prevent="sstat = 2"
                            :class="['btn', 'btn-status', 'mb-3', 'text-left', { 'activee': sstat === 2 }]">
                            Approved
                        </button>

                        <button @click.prevent="sstat = 3"
                            :class="['btn', 'btn-status', 'mb-3', 'text-left', { 'activee': sstat === 3 }]">
                            Rejected
                        </button>

                        <button @click.prevent="sstat = 4"
                            :class="['btn', 'btn-status', 'mb-3', 'text-left', { 'activee': sstat === 4 }]">
                            Deactive
                        </button>
                    </div>

                </div>
                <div class="d-flex flex-row justify-content-end">
                    <button class="btn btn-primary btn-search w-100 " @click.prevent="EmpSearch()">
                        <i class="fas fa-search"></i> Search
                    </button>
                </div>

            </form>
        </b-modal>


        <!------------------ START TABLE ------------------->
        <div class="container-table">

            <div class="filter-search row d-flex flex-row justify-content-end mr-3" v-if="!showForm">

                <span class="mr-3 btn info-search">
                    {{ infoSearch == null ? 'Nothing Search' : infoSearch }}
                </span>

                <b-button v-b-modal.modal-1 class="btn btn-search-filter text-white mr-3">
                    <i class="fas fa-filter"></i> Search by Filter
                </b-button>
                <button class="btn btn-secondary mr-5" @click="resetFunc()">Reset</button>

                <!-- ASC & DSC -->
                <div @click="sascdesc(1)" class="btn btn-warning mr-2 text-white">
                    <i class="fas fa-arrow-up-a-z"></i> Asc
                </div>

                <button class="btn btn-success text-white mr-1" @click="sascdesc(2)"> <i class="fas fa-arrow-down-z-a"></i>
                    Dsc</button>
            </div>

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
                    <tbody v-if="employeeDatas.length > 0">
                        <tr class=" baris text-center shadow-lg bg-white" v-for="(item, index) in paginatedData"
                            :key="index">
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
                                <button type="button" class="btn btn-detail" @click="updateFunc(item)">{{ tableBtn
                                }}</button>
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
        </div>


        <div class="row d-flex justify-content-center next color-text" v-if="employeeDatas.length > 7 && !showForm">
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

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="name_employee">Full Name</label>
                        <input type="text" class="form-control company-detail" id="name_employee"
                            placeholder="Employee Name" required v-model="employeeData.fullName" :disabled="!showUpdateBtn">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="email_employee">Email Employee</label>
                        <input type="email" class="form-control" id="email_employee" placeholder="Email" required
                            v-model="employeeData.companyEmail" :disabled="!showUpdateBtn">
                    </div>
                </div>


                <div class="form-row">

                    <div class="form-group col-md-6">
                        <label for="phone">Mobile Phone</label>
                        <input type="number" class="form-control" id="phone_employee" placeholder="Enter phone Number"
                            required v-model="employeeData.mobilePhone" :disabled="!showUpdateBtn">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="nik_employee">NIK</label>
                        <input type="number" class="form-control company-detail" id="nik_employee" placeholder="Postal Code"
                            required v-model="employeeData.nik" :disabled="!showUpdateBtn">
                    </div>
                </div>

                <div class="form-row">

                    <div class="form-group col-md-6">
                        <label for="bday">Date of Birth</label>
                        <input type="date" class="form-control" id="bday" required v-model="employeeData.dateBirth"
                            :disabled="!showUpdateBtn">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="join_date">Join Date</label>
                        <input type="date" class="form-control" id="join_date" required v-model="employeeData.joinDate"
                            :disabled="!showUpdateBtn">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="nip_employee">NIP</label>
                        <input type="number" class="form-control" id="nip_employee" placeholder="Enter NIP" required
                            v-model="employeeData.nip" :disabled="!showUpdateBtn">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="npwp_employee">NPWP</label>
                        <input type="number" class="form-control" id="npwp_employee" placeholder="Enter NPWP" required
                            v-model="employeeData.npwp" :disabled="!showUpdateBtn">
                    </div>

                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="division">Division</label>
                        <input type="text" class="form-control" id="division" placeholder="Enter Division" required
                            v-model="employeeData.division" :disabled="!showUpdateBtn">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="supervisor">Direct Supervisor</label>
                        <input type="text" class="form-control" id="supervisor" placeholder="Enter Supervisor" required
                            v-model="employeeData.directSupervisor" :disabled="!showUpdateBtn">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="salary">Salary</label>
                        <input type="number" class="form-control" id="salary" placeholder="Enter Salary" required
                            v-model="employeeData.salary" :disabled="!showUpdateBtn">
                    </div>
                </div>
                <div class="form-row">

                    <div class="form-group col-md-6">
                        <label for="leave">Leaves Dates</label>
                        <input type="number" class="form-control" id="leave" placeholder="Enter leave date" required
                            v-model="employeeData.leaveDays" :disabled="!showUpdateBtn">
                    </div>

                    <!-- <div class="form-group" v-if="submitBtn == 'Add Employee'">
                        <div class="mb-3">
                            <label for="formFile" class="form-label">Choose File</label>
                            <input @change="handleFileUpload('att', $event)" class="form-control" type="file" id="formFile">
                        </div>
                    </div> -->
                </div>

                <button type="submit" v-if="employeeData.status == 2 || submitBtn == 'Add Employee'" v-show="showUpdateBtn"
                    :class="clasSubmit">{{ submitBtn }}</button>

                <!-- class="btn update-btn mb-4 mt-4" -->
                <button class="btn btn-deactive mb-4" v-if="employeeData.status == 2 || employeeData.status == 4"
                    @click.prevent="deactiveAlert()">{{ actBtn }}</button>
            </form>
            <!--------------------- END ADD COMPANY -------------------------->
        </div>
    </section>
</template>
  
<script>
import employeeService from '@/services/employeeService';
import userService from "@/services/userService";

export default {
    name: "EmployeeS",

    // DATA
    data() {
        return {
            // status: null,
            submitBtn: null,
            updateBtn: null,
            title: null,
            tableBtn: null,
            roleId: this.$session.get("jwt").roleId,
            colorStatus: null,
            actBtn: null,
            clasSubmit: null,
            isFound: false,
            comId: null,

            userData: {
                id: null,
                email: null,
                companyId: null,
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
                dateBirths: null,
                dateBirth: null,
                joinDates: null,
                joinDate: null,
                createdTime: null,
                updatedTime: null,
                document: null,
                companyId: null,
            },

            itemsPerPage: 7,
            currentPage: 1,
            employeeDatas: {},
            bappData: {},
            showForm: false,
            showDetail: false,
            showUpdateBtn: false,

            // search data
            infoSearch: null,
            sstat: null,
            snam: null,
            sass: null,
        }
    },
    methods: {
        hideModalSearch() {
            this.$refs.modalSearch.hide();
        },
        resetFunc() {
            this.employeeDatas = this.bappData
            this.infoSearch = null;
        },
        BackButton(back) {
            this.showUpdateBtn = false;
            if (back == 1) {
                this.showForm = false
                this.getEmployee();
            }
        },

        deactiveAlert() {
            let data = this.employeeData;

            this.$swal({
                title: "Are you sure?",
                text: "You can't revert your action",
                icon: "warning",
                showCancelButton: true,
                confirmButtonText: data.status == 2 ? "Deactive it!" : "Reactive it!",
                cancelButtonText: "No, Cancel!",
                showCloseButton: true,
                showLoaderOnConfirm: true,
            }).then((result) => {
                if (result.value) {
                    if (data.status == 2) {
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
                        text: "Your file is still intact",
                    });
                }
            });
        },

        deactiveFunc() {
            let data = this.employeeData;
            data.status = 4;
            data.joinDates = data.joinDate + " 00:00"
            data.dateBirths = data.dateBirth + " 00:00"
            data.joinDate = null
            data.dateBirth = null

            employeeService
                .upload(data)
                .then((response) => {
                    console.log("DEACTIVE");
                    console.log(response.status);
                    this.showForm = !this.showForm;
                    this.getEmployee();
                    this.BackButton(1);
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
                        this.$toast.error("Error!", {
                            position: "top-right",
                            timeout: 2500,
                        });
                    }
                });
        },

        reactiveFunc() {
            let data = this.employeeData;
            data.status = 2;
            data.joinDates = data.joinDate + " 00:00"
            data.dateBirths = data.dateBirth + " 00:00"
            data.joinDate = null
            data.dateBirth = null

            employeeService
                .upload(data)
                .then((response) => {
                    console.log("REACTIVE");
                    console.log(response.status);
                    this.showForm = !this.showForm;
                    this.getEmployee();
                    this.BackButton(1);
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
                        this.$toast.error("Error!", {
                            position: "top-right",
                            timeout: 2500,
                        });
                    }
                });
        },

        updateFunc(data) {
            this.showForm = !this.showForm;
            this.submitBtn = "Update Employee"
            this.clasSubmit = "btn update-btn mb-4 mt-4"
            this.title = "Update"
            this.employeeData = data;

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
            this.showUpdateBtn = true;
            this.showForm = !this.showForm;
            this.submitBtn = "Add Employee"
            this.title = "Add"
            this.clasSubmit = "btn add-employee mb-4 mt-4";
        },

        // POST METHOD
        async SubmitEmployee() {
            let data = this.employeeData;
            // let idEmployee = null;
            // let file1 = this.attendance

            data.createdBy = this.$session.get('email');

            if (this.submitBtn === "Add Employee") {
                data.status = 2;
                data.createdBy = this.$session.get('email');
                data.joinDates = data.joinDate + " 00:00"
                data.dateBirths = data.dateBirth + " 00:00"
                data.joinDate = null
                data.dateBirth = null
                data.companyId = this.comId

                employeeService.upload(data).then((response) => {
                    console.log(response.status);
                    this.$toast.success('Employee Data has been successfully added!', {
                        position: 'top-right',
                        timeout: 2500,
                    });
                    this.getEmployee();
                    this.showForm = !this.showForm;
                }).catch((e) => {
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
                data.joinDates = data.joinDate + " 00:00"
                data.dateBirths = data.dateBirth + " 00:00"
                data.joinDate = null
                data.dateBirth = null

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
                                console.log("Update Employee");
                                console.log(response.status);
                                this.showForm = !this.showForm;
                                this.getEmployee();
                                this.BackButton(1);
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


        async getData() {
            try {
                await this.getUser();
                this.getEmployee();
            } catch (error) {
                console.log(error);
            }
        },

        getUser() {
            return new Promise((resolve, reject) => {
                let data = this.userData;
                data.email = this.$session.get("email");

                userService
                    .findEmail(data)
                    .then((response) => {
                        if (response.status === 200) {
                            this.comId = response.data.companyId;
                            resolve(); // Menandakan bahwa getUser telah selesai dengan baik
                        } else {
                            reject("Error: getUser failed"); // Menandakan bahwa getUser gagal
                        }
                    })
                    .catch((e) => {
                        reject(e);
                    });
            });
        },

        getEmployee() {
            let data = this.comId;

            employeeService
                .getAllByComId(data)
                .then((response) => {
                    this.employeeDatas = response.data;
                    this.bappData = response.data;
                    console.log("get Employee");
                })
                .catch((e) => {
                    console.log(e);
                });
        },

        // FILTER EMPLOYEE
        EmpSearch() {
            console.log(this.sstat);
            this.hideModalSearch();
            console.log(this.snam + this.sstat);
            if (this.snam != null && this.snam != '') {
                this.sname()
            }
            if (this.sstat != null && this.sstat != '') {
                this.sstatus()

                if (this.sstat === 1) {
                    this.dataStat = "Need Approve"
                } else if (this.sstat === 2) {
                    this.dataStat = "Approved"
                } else if (this.sstat === 3) {
                    this.dataStat = "Rejected"
                } else {
                    this.dataStat = "Deactive"
                }
            }

            if (this.snam != null && this.dataStat != null) {
                this.infoSearch = this.snam + " & " + this.dataStat
            } else if (this.dataStat == null) {
                this.infoSearch = this.snam
            } else {
                this.infoSearch = this.dataStat
            }
            this.snam = null
            this.sstat = null
        },

        sstatus() {
            let data = this.sstat.toString();
            const sortedArray = [...this.employeeDatas];
            sortedArray.sort((a, b) => {
                return a.status - b.status;
            });
            const filteredArray = sortedArray.filter(obj => obj.status == data);
            console.log(filteredArray);
            this.employeeDatas = filteredArray;
            console.log(data);
        },

        sname() {
            let data = this.snam;
            const searchQuery = data.toLowerCase().trim();

            if (!searchQuery) {
                return this.employeeDatas;
            }
            console.log(searchQuery);
            const filteredObjects = this.employeeDatas.filter(obj => {
                const fullName = obj.fullName && obj.fullName.toLowerCase().includes(searchQuery);
                const nip = obj.nip && obj.nip.toLowerCase().includes(searchQuery);
                const division = obj.division && obj.division.toLowerCase().includes(searchQuery);

                return fullName || nip || division;
            });
            console.log("2");

            console.log('Filtered Objects:', filteredObjects); // Log the filtered objects

            this.employeeDatas = filteredObjects;
        },
        sascdesc(dih) {
            let data = dih;
            console.log(data);
            const sortedArray = [...this.employeeDatas];
            sortedArray.sort((a, b) => {
                if (data == 1) {
                    return a.fullName.localeCompare(b.fullName);
                } else {
                    return b.fullName.localeCompare(a.fullName);
                }
            });
            this.employeeDatas = sortedArray;
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
            const itemCount = this.employeeDatas.length;
            const pageCount = Math.ceil(itemCount / this.itemsPerPage);
            return pageCount;
        },

        // ambil data sesuai halaman saat ini
        paginatedData() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            return this.employeeDatas.slice(startIndex, endIndex);
        },
    },

    async mounted() {
        this.getData().catch((error) => {
            console.log(error);
        });

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

.add-employee:hover {
    background-color: #4a3ec6;
    color: white;
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
    margin-right: 15px;
    border: none;
    background: #5a4de5;
    padding: 2px 7px;
    border-radius: 15px;
    color: #fff;
    cursor: pointer;
    transition: background .2s ease-in-out;
    font-size: 14px;
}

input[type=file]::file-selector-button:hover {
    background: #4a3ec6;
}

input[type=file] {
    width: 100%;
    max-width: 100%;
    color: #444;
    /* padding: 10px; */
    height: 40px;
    background: #fff;
    border-radius: 10px;
    border: 1px solid #455;
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

/* filter css */
.input-search {
    height: 60px;
    border-radius: 10px;
}

.btn-search {
    padding: 15px 15px;
    border-radius: 15px;
}

.btn-status {
    border: 1px solid #695cfe;
}

.btn-status:hover {
    background-color: #695cfe;
    color: white;
}

.btn-search-filter {
    background-color: #695cfe;
    color: white;
    border: none;
}

.btn-search-filter:hover {
    color: white;
    background-color: #4e44ba;
    border: none;
}

.activee {
    background-color: #695cfe;
    color: white;
}

.info-search {
    border: 1px dashed grey;
}

.info-search:hover {
    cursor: none;
}
</style>