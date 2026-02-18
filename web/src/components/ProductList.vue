<template>
  <!--{{JSON.stringify(searchRequest, null, 2)}} -->
  <div class="container-fluid mt-4">
      <div class="row">

        <!-- 🔍 LINKE FILTER-SPALTE -->
        <aside class="col-12 col-lg-3 mb-4">
          <div class="card shadow-sm p-3">

            <h5 class="mb-3">Fahrzeug suchen</h5>

            <!-- Abholort -->
            <div class="mb-3">
              <label class="form-label">Ort der Abholung</label>
              <select class="form-select" v-model="searchRequest.pickupLocation">
                <option value="">Bitte wählen</option>
                <option value="widnau">Widnau</option>
                <option value="stgallen">St. Gallen</option>
              </select>
            </div>

            <!-- Abgabeort -->
            <div class="mb-3">
              <label class="form-label">Abgabeort</label>
              <select class="form-select" v-model="searchRequest.returnLocation">
                <option value="">Bitte wählen</option>
                <option value="widnau">Widnau</option>
                <option value="stgallen">St. Gallen</option>
              </select>
            </div>

            <!-- Fahrzeugtyp -->
            <div class="mb-3">
              <label class="form-label">Fahrzeugtyp</label>
              <select class="form-select" v-model="searchRequest.type">
                <option value="">Alle</option>
                <option value="pkw">PKW</option>
                <option value="suv">SUV</option>
                <option value="transporter">Transporter</option>
              </select>
            </div>

            <!-- Abhol-Datum -->
            <div class="mb-3">
              <label class="form-label">Abholungsdatum</label>
              <input
                type="date"
                class="form-control"
                v-model="searchRequest.dateFrom"
              />
            </div>

            <!-- Rückgabe-Datum -->
            <div class="mb-3">
              <label class="form-label">Rückgabedatum</label>
              <input
                type="date"
                class="form-control"
                v-model="searchRequest.dateTo"
              />
            </div>

            <!-- Währungssymbol (versteckt, da aktuell nur CHF unterstützt wird) -->
            <input type="text" v-model="searchRequest.moneySymbol" hidden />

            <!-- Preis -->
            <div class="mb-3">
              <label class="form-label">Preis bis (€)</label>
              <input
                type="number"
                class="form-control"
                v-model="searchRequest.maxPreis"
                placeholder="z.B. 300"
              />
            </div>

            <button class="btn btn-primary w-100" @click="applyFilters">
              Suchen
            </button>

          </div>
        </aside>


        <!-- 🛒 RECHTE PRODUKTLISTE -->
        <main class="col-12 col-lg-9">

          <div class="row">

            <div
              class="col-12 col-md-6 col-lg-4 mb-4"
              v-for="(item, index) in products"
              :key="index"
            >
              <div class="card h-100 shadow-sm">
                <!-- Bild -->
                <img
                :src="getImageUrl(item.image)"
                     class="card-img-top product-image"
                     alt="product image"
                 />

                <div class="card-body d-flex flex-column">
                  <h5 class="card-title"><a class="card-title" v-bind:href="'/product_detail/' + item.id">{{ item.name }}</a></h5>
                  <p class="card-text text-muted">
                    {{ item.description }}
                  </p>

                  <ul class="list-unstyled small" v-html="item.features"></ul>

                  <small class="text-muted">
                    Erfahrener Eigentümer:
                    <a :href="`/seller/${item.seller.id}`">
                      <!--{{ item.seller.name }}-->
                    </a>
                  </small>

                  <!--div class="mt-2">
                    <span v-if="item.freeDelivery" class="badge bg-success">
                      FREE Delivery
                    </span>
                    <span v-else class="text-muted">
                      <b>Delivery :</b> {{item.delivery}}
                    </span>
                  </div-->

                  <div class="flex-grow-1"></div>
                  <div class="d-flex justify-content-between align-items-center mt-3">
                  <!--div class="MuiStack-root sa-14q6fxi-MuiStack-root">
                      <div data-test="car-type" class="sa-1bt5aoj-car-search-item-status">Neu</div>
                      <div data-test="car-title" class="sa-e4v3uh-car-search-item-title">Škoda Scala</div>
                      <div data-test="car-subtitle" class="sa-rdzjfm-car-search-item-subtitle">Dynamic</div>
                      <div data-test="motor" class="sa-1tp4cps-car-search-item-motor">1,5 TSI 150 PS</div>
                  </div-->
                    <strong class="preis">
                      {{ item.moneyType }} {{ item.price }}
                    </strong>

                    <span
                      v-if="item.available > 0"
                      class="badge bg-primary"
                    >
                      Available
                    </span>
                    <span
                      v-else
                      class="badge bg-danger"
                    >
                      Not available
                    </span>
                  </div>

                  <div class="mt-3 d-flex gap-2">
                    <!--button class="btn btn-outline-primary btn-sm w-50">
                      Add
                    </button-->
                    <button class="btn btn-success btn-sm w-50" @click="$router.push('/product_detail/' + item.id)">
                      zum Angebot
                    </button>
                  </div>

                </div>
              </div>
            </div>

          </div>

          <!-- 🔽 MEHR ERGEBNISSE -->
          <div class="text-center my-4">
            <button class="btn btn-primary btn-lg">
              Mehr Ergebnisse
            </button>
          </div>

        </main>

      </div>
    </div>
<!--{{JSON.stringify(imageUrl, null, 2)}}-->

</template>

<script>
import {BASE_URL} from "@/common/config";
import {getAll, searchProducts} from "@/common/product.service.js";

export default {
  name: 'ProductList',
  components: {},
  data(){
    return{
        searchRequest: {
            pickupLocation: '',
            returnLocation: '',
            type: '',
            dateFrom: '',
            dateTo: '',
            moneySymbol: 'CHF',
            maxPreis: null
        },
        products: []
    };
  },
  created(){
    //this.getAllProducts();
    this.applyFilters();
    if(! this.searchRequest.dateFrom && !this.searchRequest.dateTo){
        this.getAllProducts();
        return;
    }
  },

  methods:{
      getAllProducts(){
        getAll().then(response => {
          this.products = response.data;
        })
      },
      getImageUrl(id) {
            return BASE_URL + '/filestore/' + id;
      },

      applyFilters(){
        const searchRequest = {
            lat: 47.3769,
            lon: 8.5417,
            radiusKm: 20,
            pickupLocation: this.searchRequest.dateFrom || null,
            returnLocation: this.searchRequest.dateTo || null,
            maxPrice: this.searchRequest.maxPreis,
            type: this.searchRequest.type
        }

        searchProducts(searchRequest).then(response => {
            this.products = response.data;
        })
        .catch(error => {
            console.error('Error searching products:', error);
        });
      },

     imageUrl(imagePath) {
        // Prefer configured BASE_URL; fallback to the usual backend dev URL
        const base = (typeof BASE_URL !== 'undefined' && BASE_URL) ? BASE_URL : 'http://localhost:8080';
        if (!imagePath) return `${base}/images/placeholder.png`;
        if (imagePath.startsWith('http://') || imagePath.startsWith('https://')) return imagePath;
        // Normalize: remove leading slashes from imagePath, ensure base has no trailing slash
        const cleanedBase = base.replace(/\/$/, '');
        const cleanedPath = imagePath.replace(/^\//, '');
        return `${cleanedBase}/${cleanedPath}`;
      }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.product-image {
  height: 220px;
  object-fit: cover;
}
.preis {
  font-size: 1.2rem;
}

.shopping-cart {
  font-family: 'Montserrat', sans-serif;
}

.shopping-cart.dark {
  background-color: #f6f6f6;
}

.shopping-cart .content {
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.075);
  background-color: white;
}

.shopping-cart .block-heading {
  padding-top: 50px;
  margin-bottom: 40px;
  text-align: center;
}

.shopping-cart .block-heading p {
  text-align: center;
  max-width: 420px;
  margin: auto;
  opacity: 0.7;
}

.shopping-cart .dark .block-heading p {
  opacity: 0.8;
}

.shopping-cart .block-heading h1,
.shopping-cart .block-heading h2,
.shopping-cart .block-heading h3 {
  margin-bottom: 1.2rem;
  color: #3b99e0;
}

.shopping-cart .items {
  margin: auto;
}

.shopping-cart .items .product {
  margin-bottom: 20px;
  padding-top: 20px;
  padding-bottom: 20px;
}

.shopping-cart .items .product .info {
  padding-top: 0px;
  text-align: center;
}

.shopping-cart .items .product .info .product-name {
  font-weight: 600;
}

.shopping-cart .items .product .info .product-name .product-info {
  font-size: 14px;
  margin-top: 15px;
}

.shopping-cart .items .product .info .product-name .product-info .product-info-detail {
  margin-bottom: 15px;
}

.shopping-cart .items .product .info .product-name .product-info .value {
  font-weight: 400;
}

.shopping-cart .items .product .info .quantity .quantity-input {
  margin: auto;
  width: 80px;
}

.shopping-cart .items .product .info .preis {
  margin-top: 15px;
  font-weight: bold;
  font-size: 22px;
}

.shopping-cart .summary {
  border-top: 2px solid #5ea4f3;
  background-color: #f7fbff;
  height: 100%;
  padding: 30px;
}

.shopping-cart .summary h3 {
  text-align: center;
  font-size: 1.3em;
  font-weight: 600;
  padding-top: 20px;
  padding-bottom: 20px;
}

.shopping-cart .summary .summary-item:not(:last-of-type) {
  padding-bottom: 10px;
  padding-top: 10px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
}

.shopping-cart .summary .text {
  font-size: 1em;
  font-weight: 600;
}

.shopping-cart .summary .preis {
  font-size: 1em;
  float: right;
}

.shopping-cart .summary button {
  margin-top: 20px;
}

.control-buttons {
  display: flex;
  flex-direction: column;
}

@media (min-width: 768px) {
  .shopping-cart .items .product .info {
    padding-top: 25px;
    text-align: left;
  }

  .shopping-cart .items .product .info .preis {
    font-weight: bold;
    font-size: 22px;
    top: 17px;
  }

  .shopping-cart .items .product .info .quantity {
    text-align: center;
  }
  .shopping-cart .items .product .info .quantity .quantity-input {
    padding: 4px 10px;
    text-align: center;
  }


}
</style>