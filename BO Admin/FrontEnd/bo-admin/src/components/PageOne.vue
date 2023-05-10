<template>
    <section class="home">
        <div class="text text-center mb-5">Dashboard</div>
        <!---------------------------------- START CARD ---------------------------------->
        <h4>Cards</h4>
        <div class="cards d-flex flex-row mb-5">
            <div class="card-1">
                <h5>Card 1</h5>
            </div>
            <div class="card-2">
                <h5 class="text-center">Card 2</h5>
            </div>
        </div>
        <!---------------------------------- END CARD ------------------------------------>

    </section>
</template>

<script>

export default {
    name: "PageOneS",
    methods: {
        showModal() {
            this.$refs['my-modal'].show()
        },
        hideModal() {
            this.$refs['my-modal'].hide()
        },
        toggleModal() {
            // We pass the ID of the button that we want to return focus to
            // when the modal has hidden
            this.$refs['my-modal'].toggle('#toggle-btn')
        }
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

.card-1 {
    width: 250px;
    height: 150px;
    background-color: #695CFE;
    border-radius: 30px;
    margin-left: 40px;
}

.card-2 {
    width: 250px;
    height: 150px;
    background-color: orange;
    border-radius: 30px;
    margin-left: 40px;

}

h4 {
    margin-left: 40px;
    color: gray;
}
</style>