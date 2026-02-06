import api from "./api.service"

export const getOffers = () => api.get('/offers')
