# job4j_cars
Проект "АвтоМаг"

[![github actions][actions-image]][actions-url]
[![coverage][codecov-image]][codecov-url]

### Используемые технологии

![tech-1.png](src/main/resources/image/readme/tech-1.png)

Веб приложение по продаже машин на языке Java с Hibernate.
Возможности сайта:
- На сайте находятся объявления.
- В объявлении: описание, марка машины, тип кузова, фото.
- Объявление имеет статус продано или нет.

### Архитектура приложения трехслойное
- Слой контроллеры
- Слой сервисы
- Слой работы с БД

### Требуемое окружение
- JDK 17
- Apache Maven 3.8.5
- PostgreSQL 13
- Браузер

### Подготовка к запуску приложения
- Создать БД cars хост `jdbc:postgresql://localhost:5432/cars`
- Собрать jar с приложением, выполнив команду `mvn install`
- Запустить приложение, выполнив команду: `java -jar job4j_cars`
- Перейти в браузере по ссылке `http://localhost:8080/post/list`

[actions-image]: https://github.com/kamikhaylov/job4j_cars/actions/workflows/maven.yml/badge.svg
[actions-url]: https://github.com/kamikhaylov/job4j_cars/actions/workflows/maven.yml
[codecov-image]: https://codecov.io/gh/kamikhaylov/job4j_cars/graph/badge.svg?token=88F9IMOKGF
[codecov-url]: https://codecov.io/gh/kamikhaylov/job4j_cars