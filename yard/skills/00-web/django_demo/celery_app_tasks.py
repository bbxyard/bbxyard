import os
import time
from celery import Celery


REDIS_PORT = os.getenv('REDIS_PORT', 6379)
broker = 'redis://localhost:{}/1'.format(REDIS_PORT)
backend = 'redis://localhost:{}/2'.format(REDIS_PORT)

app = Celery('my_task', broker=broker, backend=backend)


@app.task
def add(x, y):
    print('enter call func...')
    time.sleep(4)
    print('leave call func...')
    return x + y
