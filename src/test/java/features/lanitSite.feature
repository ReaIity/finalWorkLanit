#language: ru
Функционал: тестовый
  Сценарий: тест
    И Начинаем тест
    * Открываем страницу "Главная страница"
    И На странице "Главная страница" находим кнопку "Войти"
      Затем на странице "Страница входа" ввести логин "Логин"
      Также на странице "Страница входа" ввести пароль "Пароль"
    И на странице "Главная страница" проверить наличие иконки "Иконка"
    И на странице "Главная страница" нажать на кнопку статуса "Кнопка неактивна"
    И на странице "Главная страница" в выпадающем меню нажать кнопку подписки "Подписаться"
      Затем повторить шаги
      И на странице "Главная страница" нажать на кнопку статуса "Кнопка неактивна"
      И на странице "Главная страница" в выпадающем меню нажать кнопку подписки "Подписаться"
    И на странице "Главная страница" нажать кнопку со списком всех подписок "Подписки"
    И Проверить наличие подписок
    И на странице "Страница подписок" отписаться "Отписаться" от новых тем

