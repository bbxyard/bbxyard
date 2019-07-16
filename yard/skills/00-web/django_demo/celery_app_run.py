from celery_app import task1
from celery_app import task2

task1.add.delay(2, 4)
task2.multiply.delay(4, 5)

print('end...')
