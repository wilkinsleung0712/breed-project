# Dog Breeds Project

## Infrastructure decision
### Database
- Filebase internal H2 database
### AWS S3
- localstack hosted S3 storge


## How to run
Application can be run in two different mode 
- local without any network.  
- or with designed AWS account

### How to run locally
- Run
`docker-compose up` from project directory
- start application `./gradlew bootRunDev` with spring profile `dev`

### How to use other AWS credential
- start application `./gradlew bootRun` with spring default profile
- application will look for aws credential in `.aws` folder. (by `aws configuration`)
- additional configuration required to change in `application.properties` file
```aspectj
# will need to provid a pre created S3 bucket name for use
aws.bucket.name=breeds-project 
```

## Useful Link
- [h2-console](http://localhost:8080/h2-console)
- [open-api-protal](http://localhost:8080/swagger-ui/index.htm)
