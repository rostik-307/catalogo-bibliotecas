describe('InsertarItem Component', () => {
  beforeEach(() => {
    cy.visit('/insertar'); 
  });

  it('should load categories and create a new item', () => {
    // Mock de la respuesta de categorías
    cy.intercept('GET', '/api/category', {
      statusCode: 200,
      body: [
        { id: 1, name: 'Categoría 1' },
        { id: 2, name: 'Categoría 2' }
      ]
    }).as('getCategorias');

    // Mock de la respuesta de creación de ítem
    cy.intercept('POST', '/api/item', {
      statusCode: 201,
      body: {
        id: 1,
        name: 'Nuevo Ítem',
        details: 'Detalles del ítem',
        releaseYear: 2023,
        categoryId: 1
      }
    }).as('postItem');

    // Esperar a que las categorías se carguen
    cy.wait('@getCategorias');

    // Llenar el formulario
    cy.get('input[name="name"]').type('Nuevo Ítem');
    cy.get('textarea[name="details"]').type('Detalles del ítem');
    cy.get('input[name="releaseYear"]').type('2023');
    cy.get('select[name="categoria"]').select('1');

    // Enviar el formulario
    cy.get('form').submit();

    // Esperar a que el ítem se cree
    cy.wait('@postItem');

    // Verificar que el mensaje de éxito se muestre
    cy.contains('Ítem creado y categoría asociada exitosamente.');

    // Verificar que los detalles del ítem creado se muestren
    cy.contains('Ítem Creado');
    cy.contains('ID: 1');
    cy.contains('Nombre: Nuevo Ítem');
    cy.contains('Detalles: Detalles del ítem');
    cy.contains('Año de Lanzamiento: 2023');
    cy.contains('Categoría: 1');
  });
});