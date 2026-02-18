<template>
    <hr/><!--{{JSON.stringify(productFeatures, null, 2)}}-->

    <section class="grid relative">
        <div class="row">
            {{ productFeatures }}
        </div>
    <hr/>
    </section>

    <section class="grid relative">
        <div class="row">
            info 2
        </div>
    <hr/>
    </section>

    <section class="grid relative">
        <div class="row">
            info 3
        </div>
        <img class="d-block w-100" :src="imageMapUrl" alt="image-map" />
    <hr />
    </section>
</template>

<script>

export default{
    name: "ProductFeatures",
    props:['productFeatures'],
    data(){
        return {
            imageMapUrl : "http://localhost:8080/images/map.jpg"
        }
    }
}
</script>

<style scoped>
.body-m {
    font-family: var(--font-family-sans-serif);
    font-weight: 400;
    font-size: 16px;
    line-height: 1.5;
    letter-spacing: 0;
}
div {
    display: block;
    unicode-bidi: isolate;
}
.block {
    display: block !important;
}
p {
    font-size: var(--base-font-size);
    margin: 0 0 var(--spacing-4) 0;
}
.relative {
    position: relative !important;
}
.grid {
    display: grid;
    min-height: 0;
    min-width: 0;
}
.grid-gap-tiny {
    grid-gap: calc(var(--spacing-1) / 2);
}
section {
    display: block;
    unicode-bidi: isolate;
}
svg:not(:root) {
    overflow: hidden;
}
.dropdown, .dropup {
    position: relative;
}

.transition-background-border-color {
    transition: border-color var(--transition-fast) ease-in-out, background var(--transition-fast) ease-in-out;
}
.transition-color {
    transition: color var(--transition-fast) ease-in-out;
}

.pt2 {
    padding-top: var(--spacing-2) !important;
}
.pb2 {
    padding-bottom: var(--spacing-2) !important;
}
.br2 {
    border-radius: var(--border-radius-base) !important;
}
.cell-regular, .cell-standalone {
    margin-left: var(--spacing-2);
    margin-right: var(--spacing-2);
    padding-left: var(--spacing-2);
    padding-right: var(--spacing-2);
}
.h-100\% {
    height: 100% !important;
}
.w-100\% {
    width: 100% !important;
}
.flex-row {
    -webkit-box-direction: normal;
    -webkit-box-orient: horizontal;
    -webkit-flex-direction: row;
    -moz-flex-direction: row;
    -ms-flex-direction: row;
    flex-direction: row;
}
.flex-align-center {
    -webkit-box-align: baseline;
    -ms-flex-align: baseline;
    -webkit-align-items: center;
    -moz-align-items: center;
    align-items: center;
}
.flex {
    display: -webkit-box !important;
    display: -webkit-flex !important;
    display: -moz-flex!important;
    display: -ms-flexbox!important;
    display: flex !important;
}
.flex-grow {
    -webkit-box-flex: 1 !important;
    -webkit-flex-grow: 1 !important;
    -moz-flex-grow: 1!important;
    -ms-flex-positive: 1!important;
    flex-grow: 1 !important;
}
.flex-wrap {
    -webkit-flex-wrap: wrap;
    -moz-flex-wrap: wrap;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
}
.space-x-1>*+* {
    margin-left: var(--spacing-1);
}
.space-x-3>*+* {
    margin-left: var(--spacing-3);
}
.mb0 {
    margin-bottom: var(--spacing-0) !important;
}
.fetter-text {
  font-weight: bold;
}
</style>