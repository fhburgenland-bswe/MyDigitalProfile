import { test, expect, Page } from '@playwright/test';

test.describe('Login Page E2E Tests', () => {
    test('shouldLoginSuccessfully', async ({ page }: { page: Page }) => {
        await page.goto('/login');
        await page.fill('input[name="email"]', 'testuser@example.com');
        await page.fill('input[name="password"]', 'password');
        await page.click('button[type="submit"]');
        await page.waitForURL('/Main');

        const welcomeMessage = await page.textContent('h1');
        expect(welcomeMessage).toBe('Welcome, testuser@example.com');
    });

    test('shouldShowErrorForEmptyFields', async ({ page }: { page: Page }) => {
        await page.goto('/login');
        await page.fill('input[name="email"]', '');
        await page.fill('input[name="password"]', '');
        await page.click('button[type="submit"]');

        const errorMessage = await page.textContent('.error-message');
        expect(errorMessage).toBe('All fields are required');
    });

    test('shouldShowErrorForInvalidEmailFormat', async ({ page }: { page: Page }) => {
        await page.goto('/login');
        await page.fill('input[name="email"]', 'invalid-email');
        await page.fill('input[name="password"]', 'password');
        await page.click('button[type="submit"]');

        const errorMessage = await page.textContent('.error-message');
        expect(errorMessage).toBe('Invalid email format');
    });
});