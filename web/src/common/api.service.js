import axios from 'axios'


export function get(path = '') {
  return axios.get(path).catch((err) => {
    console.error('API GET Error:', err)
    throw new Error(`API GET Error: ${err}`)
  })
}

export function post(path = '', params = {}) {
  return axios.post(path, params).catch((err) => {
    console.error('API POST Error:', err)
    throw new Error(`API POST Error: ${err}`)
  })
}

export default axios.create({
    baseURL: process.env.VUE_APP_BASE_URL || 'http://localhost:3000/api',
    headers: {
        'Content-Type': 'application/json',
    }
})