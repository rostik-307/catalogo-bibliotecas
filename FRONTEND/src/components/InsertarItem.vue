<template>
  <div class="item">
    <!-- Formulario para crear el ítem -->
    <div>
      <h2>Crear Ítem</h2>

      <label for="itemName">Nombre del Ítem:</label>
      <input id="itemName" v-model="nuevoItem.name" />

      <label for="itemDetails">Detalles del Ítem:</label>
      <input id="itemDetails" v-model="nuevoItem.details" />

      <label for="releaseYear">Año de lanzamiento:</label>
      <input id="releaseYear" v-model="nuevoItem.releaseYear" type="number" placeholder="Ingrese el año de lanzamiento" />

      <!-- Botón para crear el ítem -->
      <button @click="crearItem">Crear Ítem</button>

      <!-- Mostrar los datos del ítem creado -->
      <div v-if="itemCreado">
        <h3>Ítem Creado:</h3>
        <p>ID: {{ itemCreado.id }}</p>
        <p>Nombre: {{ itemCreado.name }}</p>
        <p>Detalles: {{ itemCreado.details }}</p>
        <p>Año de lanzamiento: {{ itemCreado.releaseYear }}</p>
      </div>
    </div>

    <!-- Formulario para asociar la categoría, se muestra solo si el ítem ha sido creado -->
    <div v-if="itemCreado">
      <h2>Asociar Categoría al Ítem (ID: {{ itemCreado.id }})</h2>

      <label for="categoriaId">ID de la Categoría:</label>
      <input id="categoriaId" v-model="categoria.id_cat" placeholder="Ingrese el ID de la categoría" />

      <!-- Botón para añadir la categoría -->
      <button @click="asociarCategoria">Asociar Categoría</button>

      <!-- Mostrar mensaje de éxito o error -->
      <div v-if="mensaje">
        <p>{{ mensaje }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

// Variables reactivas
const nuevoItem = ref({ name: '', details: '', releaseYear: '' });
const itemCreado = ref(null); // Almacena los datos del ítem creado
const categoria = ref({ id_cat: '' });
const mensaje = ref(''); // Almacena mensajes de éxito o error

// Función para crear el ítem
const crearItem = async () => {
  try {
    const response = await axios.post('/api/item', nuevoItem.value);
    itemCreado.value = response.data;
    nuevoItem.value = { name: '', details: '', releaseYear: '' }; // Limpia el formulario de ítem
    mensaje.value = ''; // Limpia cualquier mensaje anterior
  } catch (error) {
    console.error('Error al crear el ítem:', error);
  }
};

// Función para asociar la categoría al ítem creado
const asociarCategoria = async () => {
  if (!categoria.value.id_cat) {
    alert('Por favor, ingrese el ID de la categoría.');
    return;
  }

  try {
    const response = await axios.post(`/api/item/${itemCreado.value.id}/associate-category`, categoria.value);
    mensaje.value = 'Categoría asociada exitosamente.';
    categoria.value.id_cat = ''; // Limpia el campo de categoría
  } catch (error) {
    mensaje.value = 'Error al asociar la categoría.';
    console.error('Error al asociar la categoría:', error);
  }
};
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

input {
  display: block;
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
