describe('Insertar Item', () => {
  beforeEach(() => {
    // Interceptar la solicitud de categorías
    cy.intercept('GET', '/api/category').as('getCategorias');
    // Interceptar la solicitud para crear un ítem
    cy.intercept('POST', '/api/item').as('createItem');
    
    cy.visit('/insertar');
  });

  it('debe cargar los elementos básicos de la página', () => {
    
    cy.wait('@getCategorias');

    // Verificar que el select de categorías y los inputs existen
    cy.get('select#categoria').should('exist');
    cy.get('input#name').should('exist');
    cy.get('input#details').should('exist');
    cy.get('input#releaseYear').should('exist');

    // Verificar que el botón de envío existe
    cy.get('button[type="submit"]').should('exist');
  });

  it('debe permitir el envío del formulario cuando todos los campos están llenos', () => {
    
    cy.wait('@getCategorias');

    // Completar el formulario correctamente
    cy.get('input#name').type('Ítem con categoría');
    cy.get('input#details').type('Detalles del ítem');
    cy.get('input#releaseYear').type('2023');
    cy.get('select#categoria').select('1'); 

    // Enviar el formulario
    cy.get('button[type="submit"]').click();

    // Verificar que la solicitud para crear el ítem se haya realizado
    cy.wait('@createItem').then((interception) => {
      expect(interception.response.statusCode).to.equal(200);
    });

    // Verificar el mensaje de éxito
    cy.contains('Ítem creado y categoría asociada exitosamente.').should('exist');
  });
});
