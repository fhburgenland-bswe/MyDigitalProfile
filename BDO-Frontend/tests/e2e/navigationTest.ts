import { test, expect, Page } from '@playwright/test';

test.describe('Navigation E2E Tests', () => {
    test('shouldNavigateBetweenPages', async ({ page }: { page: Page }) => {
        await page.goto('/');
        await page.click('a[href="/about"]');
        await page.waitForURL('/about');

        const aboutPageTitle = await page.textContent('h1');
        expect(aboutPageTitle).toBe('About Us');
    });

    test('shouldRestrictAccessToProfilePageWithoutLogin', async ({ page }: { page: Page }) => {
        await page.goto('/user-profile');
        await page.waitForURL('/login');

        const loginPageTitle = await page.textContent('h1');
        expect(loginPageTitle).toBe('Login');
    });
});