import axios from "axios";

const getApiBaseUrl = () => {
  if (typeof window !== 'undefined' && window._env_?.VITE_API_BASE_URL) {
    return window._env_.VITE_API_BASE_URL;
  }
  return import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";
};

// Public axios instance without authentication
// Use this for endpoints that don't require login (e.g., guest booking lookup)
const publicAxiosClient = axios.create({
  baseURL: getApiBaseUrl(),
  withCredentials: false, // No cookies for public requests
});

// No interceptors needed for public client
// Backend should handle these endpoints without authentication

export default publicAxiosClient;
