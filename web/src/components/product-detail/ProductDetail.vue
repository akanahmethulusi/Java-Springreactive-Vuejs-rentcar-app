<template>
    <div class="container shopping-cart">
        <div class="content">
            <h3>{{ productDetail.name }}</h3>

            <div class="row">
                <div class="col-md-12 col-lg-12">
                    <div class="row">
                        <div class="col-md-5">
                            <aside class="booking-aside sticky">
                                <section class="price-box">

                                    <p class="price" v-if="productDetail.price !== null">
                                        {{ productDetail.moneySymbol }} {{ productDetail.price }}
                                    </p>

                                    <!--{{ JSON.stringify(productDetail, null, 2) }}-->

                                    <p class="label">Gesamtpreis</p>
                                </section>


                                <button class="button-primary-m w-100">
                                    Fortfahren
                                </button>
                            </aside>

                        </div>
                        <div class="col-md-7">
                            <image-carousel v-bind:images="productDetail.images" />
                            <product-features v-bind:productFeatures="productDetail.features" />
                        </div>
                    </div>
                    <hr>
                </div>
            </div>

            <div class="row">
                <product-description v-bind:description="productDetail.description" />
            </div>

            <div class="row"><!-- TODO:Diese productId ruft mit Pagenation angöhire Comment. Diese sollte nicht von ProdDetailsResponse gestehen werden. Sondern braucht man eine andere Response -->
                <product-comments v-bind:productId="productDetail.id" />
            </div>
        </div>
    </div>
</template>

<script>
import ProductFeatures from "@/components/product-detail/ProductFeatures";
import ProductDescription from "@/components/product-detail/ProductDescription";
import ProductComments from "@/components/product-detail/ProductComments";
import ImageCarousel from "@/components/product-detail/ImageCarousel";

import {getProductDetailById} from "@/common/product.service";


export default {
  name: 'ProductDetail',
  components: {ImageCarousel, ProductComments, ProductDescription, ProductFeatures},
  data() {
    return {productDetail: {}
    }
  },
  mounted() {
    getProductDetailById(this.$route.params.id).then(response => {
      this.productDetail = response.data;
    });
  }
}
</script>
<!-- Damit die alle Images angeizegt werden, muss man stage Manager verbinden  -->

<style scoped lang='scss'>
.booking-aside {
  top: 6rem;
  background: white;
  border-radius: 12px;
  padding: 16px;
}
.price {
  font-size: 20px;
  font-weight: bold;
}
[class*=button-][class*=button-primary] {
    background-color: var(--color-blue-60);
    color: var(--color-gray-0);
}

[class*=button-][class*=button-primary-m], [class*=button-][class*=button-secondary-m] {
    font-size: 16px;
    font-weight: var(--font-weight-semibold);
    line-height: 1.5;
    padding: 13px 20px;
    border-radius: var(--border-radius-small);
}

</style>