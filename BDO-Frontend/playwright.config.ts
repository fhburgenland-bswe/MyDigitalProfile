import { defineConfig } from '@playwright/test';

export default defineConfig({
    use: {
        baseURL: 'http://localhost:5174',
        headless: true,
        trace: 'on', // Enable trace for all tests
    },
    reporter: [
        ['list'], // Default reporter
        ['html', { outputFolder: 'playwright-report' }], // HTML report
        ['junit', { outputFile: 'results.xml' }] // JUnit report
    ],
});