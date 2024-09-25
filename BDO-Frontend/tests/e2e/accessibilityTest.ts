import { test, expect } from '@playwright/test';

test.describe('Login Page Accessibility Tests', () => {
  
  test('should have proper aria labels on form inputs and buttons', async ({ page }) => {
    // Go to the login page
    await page.goto('/login');

    // Check if email input has a correct aria-label
    const emailInput = await page.getAttribute('input[name="email"]', 'aria-label');
    expect(emailInput).toBe('E-Mail');

    // Check if password input has a correct aria-label
    const passwordInput = await page.getAttribute('input[name="password"]', 'aria-label');
    expect(passwordInput).toBe('Passwort');

    const loginButtonRole = await page.getAttribute('button[type="submit"]', 'role');
    expect(loginButtonRole).toBe('button');
  });

  test('should allow full keyboard navigation on login form', async ({ page }) => {
    // Go to the login page
    await page.goto('/login');

    // Press Tab key to focus the first input (email field)
    await page.keyboard.press('Tab');
    let activeElement = await page.evaluate(() => document.activeElement?.getAttribute('name'));
    expect(activeElement).toBe('email');

    // Press Tab key to move focus to password field
    await page.keyboard.press('Tab');
    activeElement = await page.evaluate(() => document.activeElement?.getAttribute('name'));
    expect(activeElement).toBe('password');

    // Press Tab key to move focus to submit button
    await page.keyboard.press('Tab');
    activeElement = await page.evaluate(() => document.activeElement?.getAttribute('type'));
    expect(activeElement).toBe('submit');
  });
  
  test('should show focus ring on focused elements for better visibility', async ({ page }) => {
    await page.goto('/login');

    await page.focus('input[name="email"]');
    const emailInputBoxShadow = await page.evaluate(() => window.getComputedStyle(document.activeElement!).boxShadow);
    expect(emailInputBoxShadow).toContain('0 0 10px'); // Ensure some form of focus ring or shadow is present

    await page.focus('button[type="submit"]');
    const buttonBoxShadow = await page.evaluate(() => window.getComputedStyle(document.activeElement!).boxShadow);
    expect(buttonBoxShadow).toContain('0 0 10px'); // Ensure the button also shows focus indication
  });
});
