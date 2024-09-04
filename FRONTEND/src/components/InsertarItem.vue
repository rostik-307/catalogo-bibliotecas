<template>
  <div class="item">
    <!-- Formulario para crear el ítem con la opción de seleccionar una categoría -->
    <div>
      <h2>Crear Ítem</h2>

      <label for="itemName">Nombre del Ítem:</label>
      <input id="itemName" v-model="nuevoItem.name" />

      <label for="itemDetails">Detalles del Ítem:</label>
      <input id="itemDetails" v-model="nuevoItem.details" :disabled="itemCreado !== null" />

      <label for="releaseYear">Año de lanzamiento:</label>
      <input id="releaseYear" v-model="nuevoItem.releaseYear" type="number" placeholder="Ingrese el año de lanzamiento" />

      <label for="categoriaId">Seleccione una Categoría:</label>
      <select id="categoriaId" v-model="nuevoItem.categoriaId">
        <option v-for="categoria in categorias" :key="categoria.id" :value="categoria.id">
          {{ categoria.id }} - {{ categoria.name }}
        </option>
      </select>

      <!-- Botón para crear el ítem -->
      <button @click="crearItem">Crear Ítem</button>

      <!-- Mostrar los datos del ítem creado -->
      <div v-if="itemCreado">
        <h3>Ítem Creado:</h3>
        <p>ID: {{ itemCreado.id }}</p>
        <p>Nombre: {{ itemCreado.name }}</p>
        <p>Detalles: {{ itemCreado.details }}</p>
        <p>Año de lanzamiento: {{ itemCreado.releaseYear }}</p>
        <p>Categoría: {{ itemCreado.categoryName }}</p>
      </div>

      <!-- Mostrar mensaje de éxito o error -->
      <div v-if="mensaje">
        <p>{{ mensaje }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

// Variables reactivas
const nuevoItem = ref({ name: '', details: '', releaseYear: '', categoriaId: '' });
const itemCreado = ref(null); // Almacena los datos del ítem creado
const categorias = ref([]); // Almacena las categorías disponibles
const mensaje = ref(''); // Almacena mensajes de éxito o error

// Función para cargar las categorías desde el servidor
const cargarCategorias = async () => {
  try {
    const response = await axios.get('/api/category');
    categorias.value = response.data;
  } catch (error) {
    console.error('Error al cargar las categorías:', error);
  }
};

// Función para crear el ítem y asociar la categoría seleccionada
const crearItem = async () => {
  if (!nuevoItem.value.categoriaId) {
    alert('Por favor, seleccione una categoría.');
    return;
  }

  try {
    // Serializar los datos antes de enviarlos
    const datosSerializados = JSON.parse(JSON.stringify(nuevoItem.value));
    console.log('Datos enviados al servidor:', datosSerializados);
    const response = await axios.post('/api/item', datosSerializados);

    itemCreado.value = response.data;
    mensaje.value = 'Ítem creado y categoría asociada exitosamente.';
    nuevoItem.value = { name: '', details: '', releaseYear: '', categoriaId: '' }; // Limpia el formulario de ítem
  } catch (error) {
    mensaje.value = 'Error al crear el ítem o asociar la categoría.';
    console.error('Error al crear el ítem:', error);
  }
};

// Cargar las categorías cuando el componente se monta
onMounted(cargarCategorias);
</script>

<style scoped>
.item {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: fit-content;
}

label {
  margin-top: 10px;
  display: block;
}

input, select {
  display: block;
  margin-top: 5px;
}

button {
  margin-top: 20px;
}

.mensaje {
  color: green;
  font-weight: bold;
  margin-top: 20px;
}

.error-message {
  color: red;
  font-weight: bold;
  margin-top: 20px;
}
</style>