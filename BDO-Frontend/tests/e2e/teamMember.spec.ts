import { test, expect, Page } from '@playwright/test';

test.describe('Team Member E2E Tests', () => {
    test('should navigate to team members list', async ({ page }: { page: Page }) => {
        await page.goto('/');
        await page.fill('input[name="username"]', 'testuser');
        await page.fill('input[name="password"]', 'password');
        await page.click('button[type="submit"]');
        await page.waitForURL('/Main');

        await page.click('a[href="/team-members"]');
        await page.waitForURL('/team-members');

        const teamMemberList = await page.$$('li.team-member');
        expect(teamMemberList.length).toBeGreaterThan(0);
    });

    test('should view team member details', async ({ page }: { page: Page }) => {
        await page.goto('/team-members');
        await page.click('a[href="/team-members/1"]');
        await page.waitForURL('/team-members/1');

        const teamMemberName = await page.textContent('h1.team-member-name');
        expect(teamMemberName).toBe('John Doe');
    });

    test('should update team member details', async ({ page }: { page: Page }) => {
        await page.goto('/team-members/1');
        await page.click('button.edit');
        await page.fill('input[name="name"]', 'Jane Doe');
        await page.click('button.save');

        const updatedName = await page.textContent('h1.team-member-name');
        expect(updatedName).toBe('Jane Doe');
    });
});