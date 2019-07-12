from datetime import timedelta
from celery.schedules import crontab

BROKER_URL = "redis://localhost:9999/1"

CELERY_RESULT_BACKEND = "redis://localhost:9999/2"

CELERY_TIMEZONE = "Asia/Shanghai"


# 导入任务模块
CELERY_IMPORTS = {
    'celery_app.task1',
    'celery_app.task2'
}


# 定时任务
CELERYBEAT_SCHEDULE = {
    "task1": {
        "task": "celery_app.task1.add",
        "schedule": timedelta(seconds=10),
        "args": (2, 8)
    },
    "task2": {
        "task": "celery_app.task2.multiply",
        "schedule": crontab(hour=23, minute=20),
        "args": (4, 5)
    }
}
