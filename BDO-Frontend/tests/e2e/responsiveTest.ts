import { test, expect } from '@playwright/test';

test.describe('Login Page Responsive Design Tests', () => {
  
  test('should display login form correctly on desktop screen size', async ({ page }) => {
    // Set viewport size to a typical desktop resolution
    await page.setViewportSize({ width: 1920, height: 1080 });
    
    // Go to the login page
    await page.goto('/login');
    
    // Ensure the login form is centered on the screen
    const form = await page.$('form');
    const boundingBox = await form?.boundingBox();

    // Check that the form is in the center of the screen (roughly)
    expect(boundingBox?.x).toBeGreaterThan(300); // Ensure the form isn't aligned to the left
    expect(boundingBox?.y).toBeGreaterThan(150); // Ensure form has top margin
  });

  test('should adapt login form for mobile screen size', async ({ page }) => {
    // Set viewport size to a typical mobile resolution
    await page.setViewportSize({ width: 375, height: 667 });
    
    // Go to the login page
    await page.goto('/login');
    
    // Check that form elements adapt properly on small screen
    const emailInput = await page.$('input[name="email"]');
    const emailInputWidth = await emailInput?.evaluate((el) => window.getComputedStyle(el).width);

    expect(Number(emailInputWidth?.replace('px', ''))).toBeLessThan(350); // Ensure it's a mobile-optimized width
  });

  test('should show "Sie sind abgemeldet" toast on page load with loggedOut query', async ({ page }) => {
    // Go to login page with a query parameter to simulate logout
    await page.goto('/login?loggedOut=true');
    
    // Wait for the toast message to appear
    const toastMessage = await page.textContent('.Toastify__toast-body');
    
    // Ensure the toast contains the correct message
    expect(toastMessage).toBe('Sie sind abgemeldet.');
  });
});
