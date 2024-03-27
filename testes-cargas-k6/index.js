import insertProductMongo from './insert-products-mongodb.js';
import insertProductPostgres from './insert-products-postgres.js';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

export let options = {
    vus: 2000,
    duration: '240s',
    thresholds: {
        'http_req_duration': ['p(95)<500'], // Garante que 95% das requisições sejam concluídas em menos de 500ms
        'http_req_duration{url:http://localhost:8080/v1/create/product}': ['avg<300'], // Garante que o tempo de resposta médio para um endpoint específico seja inferior a 300ms
    }
};

export function handleSummary(data) {
    return {
        "insert-products.html": htmlReport(data),
    };
}

export default function () {
    // Executar o script 1
    insertProductMongo();
    // // Executar o script 2
    // insertProductPostgres();
}
