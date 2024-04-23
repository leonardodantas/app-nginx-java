import http from 'k6/http';
import { check } from 'k6';
import { SharedArray } from 'k6/data';
import { Counter, Rate, Trend } from 'k6/metrics';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";
import { scenario } from 'k6/execution';
import { uuidv4 } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

const products = new SharedArray('products', function () {
    return JSON.parse(open('./jsons/products.json'));
});

let requestsCounter = new Counter('requests_total');
let successfulRequestsCounter = new Counter('successful_requests_total');
let errorRate = new Rate('errors');
let responseTimeTrend = new Trend('response_time');

export function handleSummary(data) {
    const reportName = `./relatorios/insert-products-mongo.html`;
    return {
        [reportName]: htmlReport(data),
    };
}

export const options = {
    scenarios: {
      'use-all-the-data': {
        executor: 'shared-iterations',
        vus: 612,
        iterations: products.length,
        maxDuration: '20s',
      },
    },
  };


export default function () {
        let product = products[scenario.iterationInTest];

        let params = {
            headers: {
                'Content-Type': 'application/json',
                'Trace-Id': uuidv4()
            },
        };

        let startTime = new Date().getTime();
        let response = http.post('http://localhost/v1/create/product', JSON.stringify(product), params);
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
            'status is 400': (r) => r.status === 400,
        });

}
