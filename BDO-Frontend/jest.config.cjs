module.exports = {
  // preset: '@vue/cli-plugin-unit-jest/presets/no-babel',
  testMatch: [
    "<rootDir>/tests/unit/CreateUserModal.test.ts",
    "<rootDir>/tests/unit/LoginPage.test.ts",
    "<rootDir>/tests/unit/MainPage.test.ts",
    "<rootDir>/tests/unit/UpdateDataModal.test.ts",
    "<rootDir>/tests/unit/user.service.test.ts",
    "<rootDir>/tests/unit/teamMemberService.test.ts"
  ],
  transform: {
    '^.+\\.ts?$': 'ts-jest',
  },
  moduleFileExtensions: ['ts', 'js', 'json', 'node'],
  collectCoverage: true,
  coverageDirectory: 'coverage',
};