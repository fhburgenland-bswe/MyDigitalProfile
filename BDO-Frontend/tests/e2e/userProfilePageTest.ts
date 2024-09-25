import { test, expect, Page } from '@playwright/test';

test.describe('User Profile Page E2E Tests', () => {
    test('shouldViewUserProfile', async ({ page }: { page: Page }) => {
        await page.goto('/login');
        await page.fill('input[name="email"]', 'testuser@example.com');
        await page.fill('input[name="password"]', 'password');
        await page.click('button[type="submit"]');
        await page.waitForURL('/Main');

        await page.goto('/user-profile');
        const userProfile = await page.textContent('.user-profile');
        expect(userProfile).toContain('testuser@example.com');
    });

    test('shouldShowErrorForInvalidDataOnEdit', async ({ page }: { page: Page }) => {
        await page.goto('/login');
        await page.fill('input[name="email"]', 'testuser@example.com');
        await page.fill('input[name="password"]', 'password');
        await page.click('button[type="submit"]');
        await page.waitForURL('/Main');

        await page.goto('/user-profile');
        await page.click('button.edit');
        await page.fill('input[name="name"]', '');
        await page.click('button.save');

        const errorMessage = await page.textContent('.error-message');
        expect(errorMessage).toBe('Name cannot be empty');
    });
});