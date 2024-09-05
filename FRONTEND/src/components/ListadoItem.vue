<template>
    <div>
        <h1><del>Listado</del> Catálogo de artículos</h1>
        <RouterLink class="clear-router" to="/insertar"><button>Crear nuevo artículo</button></RouterLink>


        <!-- Caja de búsqueda por ID -->
        <input type="text" v-model="searchId" placeholder="Busqueda por Id" />
        <button @click="buscar">Buscar</button>
        <button @click="resetear">Reiniciar</button>

        <table class="details-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Detalles</th>
                    <th>Año</th>
                    <th>Categoría</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in filteredItems" :key="item.id">
                    <td>{{ item.id }}</td>
                    <td>{{ item.name }}</td>
                    <td>{{ item.details }}</td>
                    <td>{{ item.releaseYear }}</td>
                    <td>{{ item.categoryName }}</td>
                    <td>
                        <button @click="showDetails(item)">Detalles</button>
                        <button @click="editItem(item)">Editar</button>
                        <button @click="deleteItem(item.id)">Borrar</button>
                    </td>
                </tr>
            </tbody>
        </table>
        <!-- Modal para mostrar detalles del item -->
        <div v-if="showModal" class="modal" @click.self="closeModal">
            <div class="modal-content">
                
                <span class="close" @click="closeModal">&times;</span>
                <div>
                    <h2>Detalles de artículo</h2>
                </div>
                <table class="details-table">
                    <tr>
                        <td>ID:</td>
                        <td>{{ selectedItem.id }}</td>
                    </tr>
                    <tr>
                        <td>Nombre:</td>
                        <td>{{ selectedItem.name }}</td>
                    </tr>
                    <tr>
                        <td>Detalles:</td>
                        <td>{{ selectedItem.details }}</td>
                    </tr>
                    <tr>
                        <td>Año:</td>
                        <td>{{ selectedItem.releaseYear }}</td>
                    </tr>
                    <tr>
                        <td>Categoría:</td>
                        <td>{{ selectedItem.categoryName }}</td>
                    </tr>
                </table>
            </div>
        </div>


        <!-- Formulario de edición -->
        <div v-if="showEditForm" class="edit-form modal" @click.self="closeEditForm">
            <div class="modal-content">
                <span class="close" @click="closeEditForm">&times;</span>
                <span>
                    <h2>Editar Artículo</h2>
                </span>
                <form @submit.prevent="updateItem">
                    <label for="name">Nombre:</label>   
                    <input type="text" v-model="selectedItem.name" id="name" required>

                    <label for="details">Detalles:</label>
                    <input type="text" v-model="selectedItem.details" id="details" required>

                    <label for="releaseYear">Año:</label>
                    <input type="number" v-model="selectedItem.releaseYear" id="releaseYear" required>

                    <label for="category">Categoría:</label>
                    <select v-model="selectedItem.categoryId" id="category" required>
                        <option v-for="category in categories" :key="category.id" :value="category.id">
                            {{ category.name }}
                        </option>
                    </select>
                    <button type="submit">Actualizar artículo</button>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
    setup() {
        const items = ref([]);
        const categories = ref([]);
        const searchId = ref('');
        const filteredItems = ref([]);
        const selectedItem = ref(null);
        const selectedCategoryId = ref(null);
        const showModal = ref(false);
        const showEditForm = ref(false);
        const router = useRouter();

        const fetchItems = async () => {
            try {
                const response = await axios.get('/api/item');
                items.value = response.data;
                filteredItems.value = response.data;
            } catch (error) {
                console.error('Error fetching items:', error);
            }
        };

        const fetchCategories = async () => {
            try {
                const response = await axios.get('/api/category');
                categories.value = response.data;
            } catch (error) {
                console.error('Error fetching categories:', error);
            }
        };

        const buscar = async () => {
            try {
                const response = await axios.get(`/api/item/${searchId.value}`);
                filteredItems.value = [response.data]; // Mostrar solo el item buscado
            } catch (error) {
                console.error('Error al buscar item:', error);
            }
        };

        const resetear = () => {
            filteredItems.value = items.value;
            searchId.value = '';
        };

        const showDetails = (item) => {
            selectedItem.value = item;
            showModal.value = true;
        };

        const editItem = (item) => {
            selectedItem.value = { ...item };
            selectedCategoryId.value = item.category ? item.category.id : null;
            showEditForm.value = true;
        };

        const updateItem = async () => {
            try {
                // Asignar la categoría seleccionada al selectedItem
                selectedItem.value.category = {
                    id: selectedCategoryId.value, // Solo asignamos el ID de la categoría
                };

                await axios.put(`/api/item/${selectedItem.value.id}`, selectedItem.value);

                fetchItems(); // Refresca la lista de items después de la actualización
                showEditForm.value = false;
            } catch (error) {
                console.error('Error updating item:', error);
            }
        };

        const closeEditForm = () => {
            showEditForm.value = false;
        };

        const deleteItem = async (id) => {
            try {
                await axios.delete(`/api/item/${id}`);
                fetchItems(); // Refresca la lista de items después de eliminar
            } catch (error) {
                console.error('Error deleting item:', error);
            }
        };

        const closeModal = () => {
            showModal.value = false;
        };

        const cancelEdit = () => {
            showEditForm.value = false;
        };

        fetchItems();
        fetchCategories();

        return {
            items,
            categories,
            searchId,
            filteredItems,
            selectedItem,
            selectedCategoryId,
            closeEditForm,
            showModal,
            showEditForm,
            buscar,
            resetear,
            showDetails,
            editItem,
            updateItem,
            deleteItem,
            closeModal,
            cancelEdit,
        };
    }
};
</script>

<style>
.modal {
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.5);
}


.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

</style>