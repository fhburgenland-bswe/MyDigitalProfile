import { fileURLToPath } from 'node:url'
import { mergeConfig, defineConfig, configDefaults } from 'vitest/config'
import viteConfig from './vite.config'

export default mergeConfig(
  viteConfig,
  defineConfig({
    test: {
        coverage:{
            enabled: true,
            reporter: ['text', 'json', 'html'],
            include: ['src/**/*.ts', 'src/**/*.vue'],
            exclude: ['node_modules', 'tests', 'src/main.ts', 'src/**/*.d.ts']
        },
      environment: 'jsdom',
      exclude: [...configDefaults.exclude, 'e2e/*'],
      root: fileURLToPath(new URL('./', import.meta.url))
    }
  })
)
