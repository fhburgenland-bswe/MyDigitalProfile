import { defineConfig } from '@playwright/test';

export default defineConfig({
    use: {
        baseURL: 'http://localhost:8080',
        headless: true,
        trace: 'on', // Enable trace for all tests
    },
    webServer: {
        command: 'npm run dev',
        port: 8080,
    },
    reporter: [
        ['list'], // Default reporter
        ['html', { outputFolder: 'playwright-report' }], // HTML report
        ['junit', { outputFile: 'results.xml' }] // JUnit report
    ],
});