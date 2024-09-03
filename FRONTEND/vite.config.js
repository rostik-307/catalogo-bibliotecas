import { fileURLToPath, URL } from 'node:url';
import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import vueDevTools from 'vite-plugin-vue-devtools';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
  server: {
    proxy: {
      '/listado': {
        target: 'http://localhost:8080',  
        changeOrigin: true,
        secure: false,
        ws: true,
      },
      '/insertar': {
        target: 'http://localhost:8080', 
        changeOrigin: true,
        secure: false,
        ws: true,
      },
    },
  },
});
