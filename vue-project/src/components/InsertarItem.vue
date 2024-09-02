<template>
  <div class="item">
    <!-- Combo para seleccionar el tipo de ítem -->
    <div class="combo-accion">
      <label for="tipoItem">Seleccionar tipo de ítem:</label>
      <select id="tipoItem" v-model="tipoSeleccionado" @change="cambiarATipo">
        <option value="">Seleccione un tipo</option>
        <option value="CD">Insertar CD</option>
        <option value="VHS">Insertar VHS</option>
        <option value="Libro">Insertar Libro</option>
        <option value="Revista">Insertar Revista</option>
      </select>
    </div>

    <!-- Formulario para crear el ítem -->
    <div v-if="tipoSeleccionado">
      <h2>Crear {{ tipoSeleccionado }}</h2>

      <label for="itemName">Nombre del {{ tipoSeleccionado }}:</label>
      <input id="itemName" v-model="nuevoItem.name" />

      <label for="itemDetails">Detalles del {{ tipoSeleccionado }}:</label>
      <input id="itemDetails" v-model="nuevoItem.details" />

      <label for="categoriaId">ID de la Categoría:</label>
      <input id="categoriaId" v-model="nuevoItem.id_cat" placeholder="Ingrese el ID de la categoría" />

      <!-- Botón para añadir ítem -->
      <button @click="añadirItem">Añadir {{ tipoSeleccionado }}</button>

      <!-- Mostrar los datos del ítem creado -->
      <div v-if="itemCreado">
        <h3>{{ tipoSeleccionado }} Creado:</h3>
        <p>ID: {{ itemCreado.id }}</p>
        <p>Nombre: {{ itemCreado.name }}</p>
        <p>Detalles: {{ itemCreado.details }}</p>
        <p>ID de Categoría: {{ itemCreado.id_cat }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

// Variables reactivas
const tipoSeleccionado = ref(''); // Tipo de ítem seleccionado
const nuevoItem = ref({ name: '', details: '', id_cat: '' });
const itemCreado = ref(null); // Almacena los datos del ítem creado

// Función para cambiar el tipo de ítem
const cambiarATipo = () => {
  resetFormularioItem(); // Resetea el formulario cuando cambias de tipo
};

// Función para añadir el ítem
const añadirItem = () => {
  if (!nuevoItem.value.id_cat) {
    alert('Por favor, ingrese el ID de la categoría.');
    return;
  }

  axios.post(`/api/items`, nuevoItem.value)
    .then(response => {
      console.log(`${tipoSeleccionado.value} añadido correctamente:`, response.data);
      // Limpia el formulario tras la creación
      nuevoItem.value = { name: '', details: '', id_cat: '' };
      itemCreado.value = response.data;
    })
    .catch(error => {
      console.error(`Error al añadir ${tipoSeleccionado.value}:`, error);
    });
};

// Función para resetear el formulario de ítem
const resetFormularioItem = () => {
  nuevoItem.value = { name: '', details: '', id_cat: '' }; // Limpia el formulario
};
</script>

<style scoped>
.item {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 50%;
  margin-top: 5%;
}

.combo-accion {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
  width: 100%;
}

.combo-accion label {
  margin-bottom: 10px;
}

.combo-accion select {
  width: 100%;
  padding: 10px;
}
</style>