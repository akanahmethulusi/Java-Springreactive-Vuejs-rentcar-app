<template>
    <div class="home">

        <!-- HERO -->
        <section class="hero">
            <div class="overlay">
                <h1>Teilen statt besitzen</h1>
                <p>Finde Fahrten, Autos und mehr</p>

                <form class="search-box" @submit.prevent="search">
                    <input v-model="searchForm.from" placeholder="Von" />
                    <input v-model="searchForm.to" placeholder="Nach" />
                    <button>Suchen</button>
                </form>
            </div>
        </section>

        <!-- FEATURES -->
        <section class="features">
            <div class="feature" v-for="f in features" :key="f.title">
                <h3>{{ f.title }}</h3>
                <p>{{ f.text }}</p>
            </div>
        </section>

        <!-- CARDS -->
        <section class="cards">
            <div class="card" v-for="item in offers" :key="item.id">
                <img :src="item.image" />
                <h3>{{ item.title }}</h3>
                <p>{{ item.price }} CHF</p>
            </div>
        </section>

        <!-- CTA -->
        <section class="cta">
            <h2>Jetzt loslegen</h2>
            <button>Registrieren</button>
        </section>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import axios from "axios"

const searchForm = ref({
  from: "",
  to: ""
})

const features = ref([
  { title: "Günstig", text: "Spare Geld durch Teilen" },
  { title: "Nachhaltig", text: "Weniger Autos, weniger CO₂" },
  { title: "Flexibel", text: "Fahre wann du willst" }
])

const offers = ref([])

const searchOffers = async () => {
  const { data } = await axios.get("${BASE_URL}/offers")
  offers.value = data
}

const search = () => {
  console.log("Search:", searchForm.value)
}

onMounted(searchOffers)
</script>

<script>
export default {
  name: 'HomeView'
}
</script>

<style scoped>
.hero {
  height: 70vh;
  background: url("") center/cover;
  display: flex;
  align-items: center;
  justify-content: center;
}

.overlay {
  background: rgba(0,0,0,.55);
  color: white;
  padding: 2rem;
  text-align: center;
}

.search-box {
  display: flex;
  gap: 1rem;
  margin-top: 1rem;
}

.features, .cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
  gap: 1.5rem;
  padding: 3rem;
}

.card {
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 5px 15px rgba(0,0,0,.1);
}

.cta {
  background: #0a7cff;
  color: white;
  padding: 4rem;
  text-align: center;
}
</style>