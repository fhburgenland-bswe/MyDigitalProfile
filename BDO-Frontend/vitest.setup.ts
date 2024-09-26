import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';

// Erstelle einen neuen Mock-Adapter
const mock = new MockAdapter(axios);

// Definiere deine Mock-Endpoints
mock.onGet('http://localhost:8081/api/team-members').reply(200, [
    { id: 1, name: 'John Doe' },
]);

mock.onGet('http://localhost:8081/api/team-members/1').reply(200, { id: 1, name: 'John Doe' });

mock.onPut('http://localhost:8081/api/team-members/1').reply(200, { id: 1, name: 'John Doe Updated' });

// Für alle anderen Endpoints eine Standardantwort festlegen
mock.onAny().reply(config => {
    console.log(`Mocking request to ${config.url}`, config);
    return [200, {}]; // Standardantwort, falls keine spezifische Antwort definiert ist
});
