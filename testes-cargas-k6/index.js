import insertProductMongo from './insert-products-mongodb.js';
import insertProductPostgres from './insert-products-postgres.js';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

export let options = {
    vus: 1000,
    duration: '60s',
    thresholds: {
        'http_req_duration': ['p(95)<500'],
        'http_req_duration{url:http://localhost:8081/v1/create/product}': ['avg<300'],
    }
};

export function handleSummary(data) {
    const reportName = `./relatorios/insert-products-${new Date().getTime()}.html`;
    return {
        [reportName]: htmlReport(data),
    };
}

export default function () {
    // Executar o script 1
    insertProductMongo();
    // // Executar o script 2
    // insertProductPostgres();
}
