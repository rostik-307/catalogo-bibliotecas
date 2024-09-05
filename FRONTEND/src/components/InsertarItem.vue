<template>
  <div>
    <h1>Insertar Nuevo Ítem</h1>
    <form @submit.prevent="crearItem">
      <div>
        <label for="name">Nombre:</label>
        <input type="text" id="name" v-model="nuevoItem.name" required />
      </div>
      <div>
        <label for="details">Detalles:</label>
        <input type="text" id="details" v-model="nuevoItem.details" required />
      </div>
      <div>
        <label for="releaseYear">Año de Lanzamiento:</label>
        <input type="number" id="releaseYear" v-model="nuevoItem.releaseYear" required />
      </div>
      <div>
        <label for="categoria">Categoría:</label>
        <select id="categoria" v-model="nuevoItem.categoriaId" required>
       <option value="" disabled>Seleccione una categoría</option>
        <option v-for="categoria in categorias" :key="categoria.id" :value="categoria.id">
    {{ categoria.id }} - {{ categoria.name }}
      </option>
</select>

      </div>
      <button type="submit">Crear Ítem</button>
    </form>
    <p v-if="mensaje">{{ mensaje }}</p>
    
    <!-- Mostrar detalles del ítem creado -->
    <div v-if="itemCreado">
      <h2>Ítem Creado</h2>
      <p><strong>ID:</strong> {{ itemCreado.id }}</p>
      <p><strong>Nombre:</strong> {{ itemCreado.name }}</p>
      <p><strong>Detalles:</strong> {{ itemCreado.details }}</p>
      <p><strong>Año de Lanzamiento:</strong> {{ itemCreado.releaseYear }}</p>
      <p><strong>Categoría:</strong> {{ itemCreado.categoryId }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

// Variables reactivas
const nuevoItem = ref({ name: '', details: '', releaseYear: '', categoriaId: null });
const itemCreado = ref(null); // Almacena los datos del ítem creado
const categorias = ref([]); // Almacena las categorías disponibles
const mensaje = ref(''); // Almacena mensajes de éxito o error

// Función para cargar las categorías desde el servidor
const cargarCategorias = async () => {
  try {
    const response = await axios.get('/api/category');
    categorias.value = response.data; // Cargar las categorías desde el backend
  } catch (error) {
    console.error('Error al cargar las categorías:', error);
  }
};

// Función para crear el ítem y asociar la categoría seleccionada
const crearItem = async () => {
  console.log("ID de la categoría seleccionada:", nuevoItem.value.categoriaId);

  if (!nuevoItem.value.categoriaId) {
    alert('Por favor, seleccione una categoría.');
    return;
  }

  try {
    const datosSerializados = {
      name: nuevoItem.value.name,
      details: nuevoItem.value.details,
      releaseYear: nuevoItem.value.releaseYear,
      categoryId: nuevoItem.value.categoriaId // Enviamos solo el ID de la categoría
    };

    console.log('Datos enviados al servidor:', datosSerializados);

    const response = await axios.post('/api/item', datosSerializados);
    
    itemCreado.value = response.data;
    mensaje.value = 'Ítem creado y categoría asociada exitosamente.';
    nuevoItem.value = { name: '', details: '', releaseYear: '', categoriaId: null };
  } catch (error) {
    mensaje.value = 'Error al crear el ítem o asociar la categoría.';
    console.error('Error al crear el ítem:', error);
  }
};


// Cargar las categorías cuando el componente se monta
onMounted(cargarCategorias);
</script>

<style scoped>

</style>
