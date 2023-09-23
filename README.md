# job4j_cars
Проект "Продаже автомобилей"

[![github actions][actions-image]][actions-url]
[![coverage][codecov-image]][codecov-url]

### Используемые технологии

![tech-1.png](readme/image/tech-1.png)

Веб приложение по продаже автомобилей на языке Java с Hibernate.
Возможности сайта:
- При входе на сайте гостевому пользователю доступен просмотр объявлений с фиильтрами, 
  и  просмотр детальной информации по оюъявлению.
- В объявлении: фото, описание, марка, модель, пробег, стоимость, цвет и другие атрибуты.
- После регистрации ии авторизации пользователя появляется возможно создавать новые объявления.
- Свои объявления возможно редактировать, удалять, снимать с продажи, возвращать в продажу.
- Если при создание объявления не выбрано фото, то будет отображаться изображение по умолчанию.

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

### Главная страница, список всех объявлений
Доступные фильтры:
- Все (показывает объявления на продаже)
- За день (объявления поданные за текущий день)
- С пробегом
- Новые
- Все включая проданные автомобили

![post.png](readme/image/post.png)

### Страница добавления нового объявления
![create.png](readme/image/create.png)

### Страница с объявлениями авторизованного пользователя
![my.png](readme/image/my.png)

### Страница с детальной информацией объявления
Для своего объявления доступны:
- Редактирование объявления
- Если авто в статусе "Не продано", доступна кнопка снятие с продажи
- Если авто в статусе "Продано", доступна кнопка вернуть на продажу
- Удаление объявления

![details.png](readme/image/details.png)

Для чужого объявления действия не доступны:

![details2.png](readme/image/details2.png)

### Страница редактирования объявления
![update.png](readme/image/update.png)

### Главная страница при гостевом входе
![guest.png](readme/image/guest.png)

### Страница регистрации нового пользователя
![registration.png](readme/image/registration.png)

### Ошибка при регистрации нового пользователя
![registration_error.png](readme/image/registration_error.png)

### Успешная регистрация нового пользователя
![registration_success.png](readme/image/registration_success.png)

### Страница авторизации пользователя
![authorization.png](readme/image/authorization.png)

### Ошибка при авторизации пользователя
![authorization_error.png](readme/image/authorization_error.png)

[actions-image]: https://github.com/kamikhaylov/job4j_todo/actions/workflows/maven.yml/badge.svg
[actions-url]: https://github.com/kamikhaylov/job4j_todo/actions/workflows/maven.yml
[codecov-image]: https://codecov.io/gh/kamikhaylov/job4j_cars/graph/badge.svg?token=88F9IMOKGF
[codecov-url]: https://codecov.io/gh/kamikhaylov/job4j_cars