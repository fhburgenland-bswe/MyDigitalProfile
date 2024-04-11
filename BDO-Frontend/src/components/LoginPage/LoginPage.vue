<script setup lang="ts">

</script>

<template>
  <div class="login-page-wrapper">
    <div class="LoginContainer">
      <div id="bdo-logo"><img src="/src/assets/bdologo.png"></div>
      <div class="logintext">In Account einloggen</div>

      <form action="" method="post">
        <label for="email">E-Mail</label>
        <div class="input-box"><input type="email" name="" id="" placeholder="max.muster@bdo.de" required></div>
        <label for="password">Passwort</label>
        <div class="input-box"><input type="password" name="" id="" placeholder="Enter your password" required></div>
        <button type="submit" value="Login">Login</button>
      </form>

    </div>
  </div>
</template>

<style scoped>

.login-page-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  background-color: white;
}

.LoginContainer{
  font-family: Poppins, serif;
  max-width: fit-content;
  margin: auto auto;


  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;


}

#bdo-logo img{
  width: 347px;
  padding-bottom: 90px;


}

.logintext{
  font-weight: bold;
  letter-spacing: 1px;
  align-self: flex-start;
  padding-bottom: 25px;
  font-size: 28px;
}

label{
  font-family: Poppins, serif;
  font-size: 16px;
  margin-bottom: 5px;
}

input[type=email] {
  max-width: 400px;
  width: 400px;
  padding: 12px 20px;
  margin: 10px 0 35px;
  box-sizing: border-box;
  font-size: 14px;
  border: 2px solid lightgrey;
  border-radius: 5px;
}


input[type=password] {
  max-width: 400px;
  width: 400px;
  padding: 12px 20px;
  margin: 10px 0 35px;
  box-sizing: border-box;
  font-size: 14px;
  border: 2px solid lightgrey;

  border-radius: 5px;
}

button {
  max-width: 400px;
  width: 400px;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  font-size: 16px;

  background-color: #26348c;
  color: white;
  border-radius: 5px;
  font-family: Poppins, serif;
  border: none;
}


button:hover{
  background-color: #3137af;
}

input[type=email]:hover,
input[type=email]:focus,
input[type=email]:active,
input[type=password]:hover,
input[type=password]:focus,
input[type=password]:active {
  border: 2px solid #d1e9ff;
  box-shadow: 0 0 10px #d1e9ff;
  outline: none;
}



/* Mobile Version begins here */

@media screen and (max-width: 500px) {
  #bdo-logo img {
    width: 250px;
    padding-bottom: 15vh;
  }

  .logintext {
    font-size: 18px;
  }

  label {
    font-size: 14px;
  }

  input[type=email],
  input[type=password],
  button {
    max-width: 80vw;
    min-width: 100px;
  }
}


</style>
