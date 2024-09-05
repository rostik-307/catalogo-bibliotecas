describe('ListadoItem Component', () => {
    beforeEach(() => {

      cy.visit('/listado'); 
    });
  
    it('should render the component and elements correctly', () => {
      cy.get('h1').contains('Catálogo de artículos');
      cy.get('input[placeholder="Búsqueda por Id"]').should('exist');
      cy.get('button').contains('Crear nuevo artículo').should('exist');
      cy.get('table').should('exist');
    });
  });