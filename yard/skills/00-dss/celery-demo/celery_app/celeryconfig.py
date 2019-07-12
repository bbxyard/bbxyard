BROKER_URL = "redis://localhost:9999/1"

CELERY_RESULT_BACKEND = "redis://localhost:9999/2"

CELERY_TIMEZONE = "Asia/Shanghai"


# 导入任务模块
CELERY_IMPORTS = {
    'celery_app.task1',
    'celery_app.task2'
}
