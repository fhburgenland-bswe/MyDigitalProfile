import { test, expect } from '@playwright/test';

test.describe('Login Page Responsive Design Tests', () => {
  
  test('should display login form correctly on desktop screen size', async ({ page }) => {
    await page.setViewportSize({ width: 1920, height: 1080 });
    
    await page.goto('/login');
    
    const form = await page.$('form');
    const boundingBox = await form?.boundingBox();

    expect(boundingBox?.x).toBeGreaterThan(300); // Ensure the form isn't aligned to the left
    expect(boundingBox?.y).toBeGreaterThan(150); // Ensure form has top margin
  });

  test('should adapt login form for mobile screen size', async ({ page }) => {
    // Set viewport size to a typical mobile resolution
    await page.setViewportSize({ width: 375, height: 667 });
    
    await page.goto('/login');
    
    const emailInput = await page.$('input[name="email"]');
    const emailInputWidth = await emailInput?.evaluate((el) => window.getComputedStyle(el).width);

    expect(Number(emailInputWidth?.replace('px', ''))).toBeLessThan(350); // Ensure it's a mobile-optimized width
  });

  test('should show "Sie sind abgemeldet" toast on page load with loggedOut query', async ({ page }) => {
    // Go to login page with a query parameter to simulate logout
    await page.goto('/login?loggedOut=true');
    
    const toastMessage = await page.textContent('.Toastify__toast-body');
    
    expect(toastMessage).toBe('Sie sind abgemeldet.');
  });
});
