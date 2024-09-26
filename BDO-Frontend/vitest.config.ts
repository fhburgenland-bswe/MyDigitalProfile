import { fileURLToPath } from 'node:url';
import { mergeConfig, defineConfig, configDefaults } from 'vitest/config';
import viteConfig from './vite.config';

export default mergeConfig(
    viteConfig,
    defineConfig({
        test: {
            coverage: {
                enabled: true,
                reporter: ['text', 'json', 'html', 'lcov'],
                reportsDirectory: 'coverage/.tmp', // Hier den Pfad zur Coverage ändern
                include: ['src/**/*.ts', 'src/**/*.vue'],
                exclude: [
                    'node_modules',
                    'src/main.ts',
                    'src/**/*.d.ts',
                    'src/App.vue',
                    'src/router.ts',
                ],
            },
            environment: 'jsdom',
            include: ['tests/unit/**/*.test.ts'],
            exclude: [...configDefaults.exclude, 'e2e/*'],
            root: fileURLToPath(new URL('./', import.meta.url)),
        },
    })
);
