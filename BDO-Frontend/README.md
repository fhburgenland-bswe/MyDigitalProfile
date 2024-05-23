# BDO-Frontend

This Project is a frontend for the BDO-Project. It is built with [Vite](https://vitejs.dev/), [Vue 3](https://v3.vuejs.org/), [TypeScript](https://www.typescriptlang.org/), [Toastify](https://vue3-toastify.js-bridge.com/)  and [Vitest](https://vitest.dev/)


## Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur).<br>
Alternatively, you can use IntelliJ Ultimate with the Vue.js plugin.


<hr>

## Starting the Frontend Project
1. Start backend according to the instructions in the backend project (``BDO-Backend/Readme.md``)
2. Start the frontend project according to the instructions in this file (``BDO-Frontend/Readme.md``)
    1. Run ``npm install`` to install all dependencies
    2. Run ``npm run dev`` to start the frontend
        1. Depending on your current setup you might need to install Toastify and JSON Server(see below for instructions)
            1. Install Toastify: ``npm install --save vue3-toastify``
            2. Install JSON Server: ``npm install -g json-server``
3. Open your browser and navigate to ``http://localhost:5173/`` (Default Port) to see the frontend

<hr>


## Type Support for `.vue` Imports in TS

TypeScript cannot handle type information for `.vue` imports by default, so we replace the `tsc` CLI with `vue-tsc` for type checking. In editors, we need [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) to make the TypeScript language service aware of `.vue` types.

## Customize configuration

See [Vite Configuration Reference](https://vitejs.dev/config/).



## Project Setup

```sh
npm install
```

### Compile and Hot-Reload for Development

```sh
npm run dev
```

## Depending on your current setup, before being able to compile the front end, you must install Toastify and JSON Server!

### Install Toastify
```sh
npm install --save vue3-toastify
```

### Install JSON Server
```sh
npm install -g json-server
```

### Run JSON Server

```sh
json-server --watch db.json --port 3001
```

### Type-Check, Compile and Minify for Production

```sh
npm run build
```

### Run Unit Tests with [Vitest](https://vitest.dev/) including coverage report

```sh
npm run test:coverage
```

### Lint with [ESLint](https://eslint.org/)

```sh
npm run lint
```
