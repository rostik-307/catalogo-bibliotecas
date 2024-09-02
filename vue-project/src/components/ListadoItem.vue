<template>
    <div>
        <h1>Lista Item</h1>

        <!-- Caja de búsqueda por ID -->
        <input
            type="text"
            v-model="searchId"
            placeholder="Introduzca Id"
        />
        <button @click="buscar">Buscar</button>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Detalles</th>
                    <th>Año</th>
                    <th>Categoría</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in filteredItems" :key="item.id">
                    <td>{{ item.id }}</td>
                    <td>{{ item.details }}</td>
                    <td>{{ item.release_year }}</td>
                    <td>{{ item.categoria_id }}</td>
                    <td>
                        <!-- Botones de acción para cada categoría -->
                        <button @click="showDetails(item)">View Details</button>
                        <button @click="editItem(item.id)">Edit</button>
                        <button @click="deleteItem(item.id)">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>

        <!-- Modal para mostrar detalles del item -->
        <div v-if="showModal" class="modal" @click.self="closeModal">
            <div class="modal-content">
                <span class="close" @click="closeModal">&times;</span>
                <h2>Item Details</h2>
                <p><strong>ID:</strong> {{ selectedItem.id }}</p>
                <p><strong>Descripción:</strong> {{ selectedItem.description }}</p>
                <p><strong>Año:</strong> {{ selectedItem.year }}</p>
                <p><strong>Categoría:</strong> {{ selectedItem.category }}</p>
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
        const searchId = ref('');
        const filteredItems = ref([]);
        const selectedItem = ref(null);
        const showModal = ref(false);
        const router = useRouter();

        const fetchItems = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/items');
                items.value = response.data;
                filteredItems.value = response.data;
            } catch (error) {
                console.error('Error fetching items:', error);
            }
        };

        const buscar = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/items/${searchId.value}`);
                filteredItems.value = [response.data]; // Mostrar solo el item buscado
            } catch (error) {
                console.error('Error al buscar item:', error);
            }
        };

        const showDetails = (item) => {
            selectedItem.value = item;
            showModal.value = true;
        };

        const editItem = (id) => {
            router.push(`/item/${id}/edit`);
        };

        const deleteItem = async (id) => {
            try {
                await axios.delete(`http://localhost:8080/api/items/${id}`);
                fetchItems(); // Refresca la lista de items después de eliminar
            } catch (error) {
                console.error('Error deleting item:', error);
            }
        };

        const closeModal = () => {
            showModal.value = false;
        };

        fetchItems();

        return {
            items,
            searchId,
            filteredItems,
            selectedItem,
            showModal,
            buscar,
            showDetails,
            editItem,
            deleteItem,
            closeModal
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

.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
    max-width: 500px;
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