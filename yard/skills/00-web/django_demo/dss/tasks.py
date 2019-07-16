import time
from celery.task import Task


class DssTask(Task):
    name = "dss-task"

    def run(self, *args, **kwargs):
        print('start {}'.format(self.name))
        time.sleep(4)
        print('args={}, kwargs={}'.format(args, kwargs))
        print('end {}', self.name)
