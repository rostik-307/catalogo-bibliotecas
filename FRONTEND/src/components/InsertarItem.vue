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
    // Verificar que categoriaId no sea null o undefined
    if (!nuevoItem.value.categoriaId) {
      throw new Error('El ID de la categoría no es válido.');
    }

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