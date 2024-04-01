import http from 'k6/http';
import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';
import { Counter, Rate, Trend } from 'k6/metrics';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

const products = new SharedArray('products', function () {
    return JSON.parse(open('./jsons/products.json'));
});

// Defina métricas personalizadas
let requestsCounter = new Counter('requests_total');
let successfulRequestsCounter = new Counter('successful_requests_total');
let errorRate = new Rate('errors');
let responseTimeTrend = new Trend('response_time');
const requestsOverThreshold = new Rate('requests_over_threshold');

export function handleSummary(data) {
    return {
        "insert-products-postgres.html": htmlReport(data),
    };
}

export default function () {
    for (let i = 0; i < products.length; i++) {
        let product = products[i];

        let params = {
            headers: {
                'Content-Type': 'application/json',
            },
        };

        let startTime = new Date().getTime();
        let response = http.post('http://localhost:8081/v2/create/product', JSON.stringify(product), params);
        let endTime = new Date().getTime();
        let responseTime = endTime - startTime;

        if (response.timings.duration > 180) {
            // Incrementa a métrica de requisições com tempo de resposta maior que x
            requestsOverThreshold.add(true);
        }

        // Registre métricas
        requestsCounter.add(1); // Incrementa o contador de requisições
        responseTimeTrend.add(responseTime); // Adiciona o tempo de resposta ao trend

        if (response.status === 201) {
            successfulRequestsCounter.add(1); // Incrementa o contador de requisições bem-sucedidas se o status for 201
        } else {
            errorRate.add(1); // Incrementa a taxa de erro se o status não for 201
        }

        // Verifica se a requisição foi bem-sucedida
        check(response, {
            'status is 201': (r) => r.status === 201,
        });

        // Aguarda um curto período antes de enviar a próxima requisição
        // sleep(1);
    }
}
