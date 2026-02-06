<template>
    <div v-for="imageId of images" v-bind:key="imageId">
        <img class="d-block w-100" v-bind:src="getImageUrl(imageId)" alt="image-detail">
    </div>
</template>


<script>
import {BASE_URL} from "@/common/config";

export default {
    name: "ImageCarousel",
    props: ['images'],
    methods: {
        getImageUrl(id) {
            return BASE_URL + '/filestore/' + id;
        }
    }
}
</script>

<style scoped>

</style>