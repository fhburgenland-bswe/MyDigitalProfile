import { test, expect, Page } from '@playwright/test';

test.describe('Registration Page E2E Tests', () => {
    test('shouldRegisterSuccessfully', async ({ page }: { page: Page }) => {
        await page.goto('/register');
        await page.fill('input[name="email"]', 'newuser@example.com');
        await page.fill('input[name="password"]', 'password');
        await page.fill('input[name="name"]', 'New User');
        await page.click('button[type="submit"]');
        await page.waitForURL('/Main');

        const welcomeMessage = await page.textContent('h1');
        expect(welcomeMessage).toBe('Welcome, newuser@example.com');
    });

    test('shouldShowErrorForMissingFields', async ({ page }: { page: Page }) => {
        await page.goto('/register');
        await page.fill('input[name="email"]', '');
        await page.fill('input[name="password"]', '');
        await page.click('button[type="submit"]');

        const errorMessage = await page.textContent('.error-message');
        expect(errorMessage).toBe('All fields are required');
    });

    test('shouldShowErrorForInvalidEmailFormat', async ({ page }: { page: Page }) => {
        await page.goto('/register');
        await page.fill('input[name="email"]', 'invalid-email');
        await page.fill('input[name="password"]', 'password');
        await page.click('button[type="submit"]');

        const errorMessage = await page.textContent('.error-message');
        expect(errorMessage).toBe('Invalid email format');
    });
});