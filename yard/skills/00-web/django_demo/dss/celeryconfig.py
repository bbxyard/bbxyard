from datetime import timedelta
import djcelery
djcelery.setup_loader()


CELERY_QUEUES = {
    'beat_tasks': {
        'exchange': 'beat_tasks',
        'exchange_type': 'direct',
        'binding_key': 'beat_tasks'
    },
    'work_queue': {
        'exchange': 'work_queue',
        'exchange_type': 'direct',
        'binding_key': 'work_queue'
    }
}

CELERY_DEFAULT_QUEUE = 'work_queue'

CELERY_IMPORTS = {
    'dss.tasks',
}

# for deadlock
CELERY_FORCE_EXECV = True

# worker cnt
CELERY_CONCURRENCY = 4

# for retry
CELERY_ACKS_LATE = True

# max tasks per work
CELERYD_MAX_TASKS_PER_CHILD = 100

# single task run time
CELERYD_TASK_TIME_LIMIT = 12 * 30


CELERYBEAT_SCHEDULE = {
    'task1': {
        'task': 'dss-task',
        'schedule': timedelta(seconds=5),
        'options': {
            'queue': 'beat_tasks'
        }
    }
}
