# Итоговый проект | ORM-фреймворки для Java

## Модели данных

1. [AnswerOption](src/main/java/org/example/entities/AnswerOption.java)
2. [Assignment](src/main/java/org/example/entities/Assignment.java)
3. [Category](src/main/java/org/example/entities/Category.java)
4. [Course](src/main/java/org/example/entities/Course.java)
5. [CourseReview](src/main/java/org/example/entities/CourseReview.java)
6. [CourseTag](src/main/java/org/example/entities/CourseTag.java)
7. [Enrollment](src/main/java/org/example/entities/Enrollment.java)
8. [Lesson](src/main/java/org/example/entities/Lesson.java)
9. [Module](src/main/java/org/example/entities/Module.java)
10. [Profile](src/main/java/org/example/entities/Profile.java)
11. [Question](src/main/java/org/example/entities/Question.java)
12. [Quiz](src/main/java/org/example/entities/Quiz.java)
13. [QuizSubmission](src/main/java/org/example/entities/QuizSubmission.java)
14. [Submission](src/main/java/org/example/entities/Submission.java)
15. [Tag](src/main/java/org/example/entities/Tag.java)
16. [User](src/main/java/org/example/entities/User.java)

Спроектированы 16 сущностей, настроены связи 1-1, 1-N, N-N.

## Репозитории и CRUD-операции

1. [AnswerOptionRepository](src/main/java/org/example/repositories/AnswerOptionRepository.java)
2. [AssignmentRepository](src/main/java/org/example/repositories/AssignmentRepository.java)
3. [CategoryRepository](src/main/java/org/example/repositories/CategoryRepository.java)
4. [CourseRepository](src/main/java/org/example/repositories/CourseRepository.java)
5. [CourseReviewRepository](src/main/java/org/example/repositories/CourseReviewRepository.java)
6. [CourseTagRepository](src/main/java/org/example/repositories/CourseTagRepository.java)
7. [EnrollmentRepository](src/main/java/org/example/repositories/EnrollmentRepository.java)
8. [LessonRepository](src/main/java/org/example/repositories/LessonRepository.java)
9. [ModuleRepository](src/main/java/org/example/repositories/ModuleRepository.java)
10. [ProfileRepository](src/main/java/org/example/repositories/ProfileRepository.java)
11. [QuestionRepository](src/main/java/org/example/repositories/QuestionRepository.java)
12. [QuizRepository](src/main/java/org/example/repositories/QuizRepository.java)
13. [QuizSubmissionRepository](src/main/java/org/example/repositories/QuizSubmissionRepository.java)
14. [SubmissionRepository](src/main/java/org/example/repositories/SubmissionRepository.java)
15. [TagRepository](src/main/java/org/example/repositories/TagRepository.java)
16. [UserRepository](src/main/java/org/example/repositories/UserRepository.java)

Для всех сущностей реализованы Spring Data репозитории с CRUD-операциями: создание, чтение, обновление, удаление.

## [Управление курсами и контентом](src/main/java/org/example/services/CourseService.java)

Реализованы операции:

- Создание курса с указанием категории и преподавателя
- Добавление модулей и уроков
- Извлечение данных

## [Запись на курс](src/main/java/org/example/services/EnrollmentService.java)

Реализованы операции:

- Регистрации студентов с проверкой уникальности записей
- Возможность отписаться от курса
- Получение списка курсов и студентов

## [Задания и решения](src/main/java/org/example/services/AssignmentService.java)

Реализованы операции:

- Добавление заданий преподавателем
- Отправка решений студентами
- Предотвращение повторной отправки задания
- Просмотр и оценка решений

## [Тесты/викторины](src/main/java/org/example/services/QuizService.java)

Реализованы операции:

- Создание и удаление теста
- Прохождение теста
- Просмотр результатов прохождения тестов

## Конфигурация приложения

[Настройки подключения](application.yaml) к базе данных вынесены в .env

## Инструкция по запуску

1. Клонировать репозиторий
2. Указать параметры подключения к базе данных в .env
3. mvn clean install
4. java -jar <путь к jar файлу>

## Архитектура приложения

### entities
 Содержит все модели данных
 
### repositories
 Содержит все репозитории для работы с базой данных
 
### services
Содержит реализацию бизнес логики по работе с данными