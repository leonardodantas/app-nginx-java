import http from 'k6/http';
import { check } from 'k6';
import { SharedArray } from 'k6/data';
import { Counter, Rate, Trend } from 'k6/metrics';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

const products = new SharedArray('products', function () {
    return JSON.parse(open('./jsons/products.json'));
});

let requestsCounter = new Counter('requests_total');
let successfulRequestsCounter = new Counter('successful_requests_total');
let errorRate = new Rate('errors');
let responseTimeTrend = new Trend('response_time');

export let options = {
    vus: 10,
    duration: '60s'
};

export function handleSummary(data) {
    const reportName = `./relatorios/mongo/insert-products-mongo-${new Date().getTime()}.html`;
    return {
        [reportName]: htmlReport(data),
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
        let response = http.post('http://localhost:8081/v1/create/product', JSON.stringify(product), params);
        let endTime = new Date().getTime();
        let responseTime = endTime - startTime;

        requestsCounter.add(1);
        responseTimeTrend.add(responseTime);

        if (response.status === 201) {
            successfulRequestsCounter.add(1); 
        } else {
            errorRate.add(1);
        }

        check(response, {
            'status is 201': (r) => r.status === 201,
        });

    }
}
