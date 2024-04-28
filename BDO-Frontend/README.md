# BDO-Frontend

This Project is a frontend for the BDO-Project. It is built with [Vite](https://vitejs.dev/), [Vue 3](https://v3.vuejs.org/), [TypeScript](https://www.typescriptlang.org/),  [Toastify](https://vue3-toastify.js-bridge.com/) and [JSON Server](https://www.npmjs.com/package/json-server)  


## Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur).<br>
Alternatively, you can use IntelliJ Ultimate with the Vue.js plugin.

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
