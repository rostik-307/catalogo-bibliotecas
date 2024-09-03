<template>
    <div class="home">
      <h1>Bienvenido al catálogo de Biblioteca</h1>
      <img alt="Vue logo" class="logo" src="../assets/home.jpg" width="125" height="125" /> 
     
    </div>


  <main>

<RouterView />
</main>
  </template>
  
  <style scoped>
  .home{
    display: flex;
  flex-direction: column;
  align-items: center;
  width: 240%;
  margin-top: 13%;
  margin-left: -100%;
  }
  nav {
    display: flex;
    justify-content:center ;
    gap: 2rem;
    margin-top: 0%;
  }
  
  .logo {
    height: 7cm;
    width: 9cm;
    margin: 2 6rem 0 0;
  }


  button {
    padding: 10px 20px;
    background-color: #42b983;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #369b74;
  }
  </style>
  