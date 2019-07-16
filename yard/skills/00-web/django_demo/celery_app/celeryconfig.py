from datetime import timedelta
from celery.schedules import crontab
import os

REDIS_PORT = os.getenv('REDIS_PORT', 6379)

BROKER_URL = 'redis://localhost:{}/1'.format(REDIS_PORT)
CELERY_RESULT_BACKEND = 'redis://localhost:{}/2'.format(REDIS_PORT)

CELERY_IMPORTS = (
    'celery_app.task1',
    'celery_app.task2'
)

CELERY_TIMEZONE = 'Asia/Shanghai'

CELERYBEAT_SCHEDULE = {
    'task1': {
        'task': 'celery_app.task1.add',
        'schedule': timedelta(seconds=10),
        'args': (2, 8)
    },
    'task2': {
        'task': 'celery_app.task2.multiply',
        'schedule': crontab(hour=10, minute=5),
        'args': (4, 5)
    }
}
