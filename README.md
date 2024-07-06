# Spring

# Домашнее задание:

* ## Восстановить пример, рассмотренный на уроке (запустить эврику и 2 сервиса; заставить их взаимодействовать) Сдать скриншот страницы /eureka/apps с зарегистрированными приложениями. На скрине должно быть видно оба сервиса (book-service, issuer-service)

* ## 2.* Добавить третий сервис: сервис читателей. Обогатить ручку GET /issue, чтобы она возвращала подробную информацию:


##### // http://localhost:8380/api/issue 
```bsl
// 20240706170841
// http://localhost:8380/api/issue

[
  {
    "id": "0bb6e821-b259-4b04-9dc3-3cde4e7e477b",
    "issueAt": "2024-08-01",
    "book": {
      "id": "54988274-57b3-48d9-bc66-c935d12b0d83",
      "name": "Pale Kings and Princes",
      "author": {
        "id": "7e038d27-fea2-40e6-a7e3-1c380c58eb6b",
        "firstName": "Heath",
        "lastName": "Reichel"
      }
    },
    "reader": {
      "id": "41ccc33d-5c33-4ecd-bfed-7326a5d29ffa",
      "firstname": "Cecelia",
      "lastname": "Raynor"
    }
  }
```
