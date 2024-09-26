/* eslint-env node */
// jest-transform-esm.cjs
const babelJest = require('babel-jest');

module.exports = babelJest.default.createTransformer({
    presets: [
        ['@babel/preset-env', { targets: { node: 'current' } }],
    ],
    plugins: [
        '@babel/plugin-transform-runtime',
    ],
});