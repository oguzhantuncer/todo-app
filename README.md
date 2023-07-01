# Spring boot & React Todo Application

Bu projede Spring ve React uygulamları kullanılarak bir Todo-List uygulaması yapılmıştır. Kullanıcı yapmak istediği işleri kaydedip sonradan 
isterse edit işlemi yapabilir. Sonrasında tamamladığı işleri tamamlandı olaraka işaretleyebilir. En sonda hepsini silip
yeni işler ekleyebilir.

## Özellikler

- Todo kaydedebilme
- Todo editleyebilme
- Todo silebilme
- Completed olanları silebilme
- not_completed olanları silebilme

## Kullanılan Teknolojiler
**Frontend:** 
 - HTML 
 - CSS
 - Bootstrap
 - React

**Backend:** 
 - Java 17
 - Spring Boot 
 - Spring Data JPA 
 - Spring MVC 
 - MySQL
 - Maven
 - Docker

```bash
src folder:
- config
  - SwaggerConfig
- controller
  - util
    - GlobalControllerException
  - TodoController
- error
  - ErrorDetailDTO
  - ErrorDTO
- exception
  - BaseException
  - BusinessException
  - DomainNotFoundExcepiton
  - SystemException
- model
  - entity
    - Todo
  - enums
    - TodoStatus
  - request
    - TodoRequest
- repository
  - TodoRepository
- service
  - TodoService
- util
  - FrontendURL
  - PersistLink

```

## Persist Links:

    // Github URL
    // https://github.com/oguzhantuncer/todo-app

    // Frontend URL
    // http://localhost:3000/

    // Backend URL
    // http://localhost:8080/todo

    // Swagger URL
    // http://localhost:8080/swagger-ui/index.html#/

## Endpoints:

| EndPoint            | Method | Description                                |
|---------------------|:------:|--------------------------------------------|
| /todo               |  POST  | Todo kaydeder                              |
| /todo/id            |  PUT   | Kaydedilen todoyu düzenler                 |
| /todo               |  GET   | Bütün todoları getirir                     |
| /todo/completed     |  GET   | Sadece completed olan todoları getirir     |
| /todo/not-completed |  GET   | Sadece not-completed olan todoları getirir |
| /todo/id            |  DEL   | Id'si verilen todoyu siler                 |
| /todo               |  DEL   | Bütün todoları siler                       |
| /todo/completed     |  DEL   | Sadece completed olan todoları siler       |


## API Kullanımı
Projeyi klonlayın
```bash
git clone https://github.com/oguzhantuncer/todo-app
```
Proje Dizinine Gidin
```bash
cd todo-app
```
Bağımlılıkları Yükleyin
```bash
mvn clean install 
```
Uygulamayı Çalıştırın
```bash
cd target
java -jar todo-0.0.1-SNAPSHOT.jar
```

## Dockerization
### Image oluşturma
Projenin ana dizininde aşağıdaki komut çalıştırılır
```bash
docker build -t todo-app:latest .        
```
### Docker Compose
Projenin ana dizininde aşağıdaki komut çalıştırılır
```bash
docker-compose up       
```

## Frontend
Frontend klasörünün olduğu dizine gidilir
```bash
cd frontend
```
Node paketleri yüklenir ve Uygulama çalıştırılır
```bash
npm install
npm start
```
