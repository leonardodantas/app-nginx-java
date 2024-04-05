import insertProductMongo from './insert-products-mongodb.js';
import insertProductPostgres from './insert-products-postgres.js';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";
import { group } from 'k6';

export let options = {
    vus: 5,
    duration: '15s'
};

export function handleSummary(data) {
    const reportName = `./relatorios/insert-products-${new Date().getTime()}.html`;
    return {
        [reportName]: htmlReport(data),
    };
}

export default function () {
    group('Inserindo produtos MongoDB', function () {
        insertProductMongo();
    });

    // group('Inserindo produtos PostgresSQL', function () {
    //     insertProductPostgres();
    // });
}
