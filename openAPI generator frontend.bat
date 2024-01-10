npx @openapitools/openapi-generator-cli generate -i Backend\src\main\resources\api.yaml -g typescript-angular -o Frontend\src --server-variables=address=localhost,port=8080
pause