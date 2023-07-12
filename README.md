# Finance API
>API для управления и анализа бюджетов  
# Подготовка к сборке
- установить [JDK](https://jdk.java.net/archive/) 17 версии и выше
- установить [Docker](https://docs.docker.com/engine/install/)
- установить переменную среду JAVA_HOME="путь до jdk"/"jdk"
# Сборка
- запустить Docker
- выполнить скрипт по пути: ../practic-site/backend/scripts/rebuild.sh

# Описание
API позволяет создавать, читать, изменять и удалять доходы, расходы, планы на доходы, планы на расходы и бюджеты, 
а также добавлять в бюджеты доходы, расходы, планы на доходы и планы на расходы. Для доступа необходима аутентификация и авторизация
пользователя на основе JWT токенов Access(передается через header с префиксом Bearer и именем Authorization) и Refresh(передается через Cookie с именем Refresh).
Кроме того, API предоставляет возможность для анализа транзакций, планов и бюджетов.

# Postman Collection Import
https://github.com/satishusk/finance-postman-collection

# Документация
- user-service: http://localhost:8001/swagger-ui/index.html#/
- budget-service: http://localhost:8002/swagger-ui/index.html#/
- analysis-service: http://localhost:8003/swagger-ui/index.html#/
