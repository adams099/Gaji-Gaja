<template>
    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" placeholder="Enter Name" required :readonly="!isSave"
            v-model="inputData.name" />
    </div>

    <div class="form-group">
        <label for="phone_number">Phone Number</label>
        <input type="text" class="form-control" id="phone_number" placeholder="Enter Phone Number" required
            :readonly="!isSave" v-model="inputData.phone" />
    </div>

    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" id="email" placeholder="Enter email" required :readonly="!isSave"
            v-model="inputData.email" />
    </div>

    <div class="form-group">
        <label for="creationDate">Creation Date</label>
        <input type="creationDate" class="form-control" id="creationDate" :readonly="!isSave" v-model="inputData.created" />
    </div>

    <div class="form-group">
        <label for="creationDate">Creation Date</label>
        <input type="creationDate" class="form-control" id="creationDate" :readonly="!isSave" v-model="inputData.created" />
    </div>

    <div class="form-group">
        <label for="lastUpdated">Last Updated</label>
        <input type="lastUpdated" class="form-control" id="lastUpdated" :readonly="!isSave" v-model="inputData.update" />
    </div>

    <div class="form-group">
        <label for="lastLogin">Last Login</label>
        <input type="lastLogin" class="form-control" id="lastLogin" :readonly="!isSave" v-model="inputData.last" />
    </div>
</template>