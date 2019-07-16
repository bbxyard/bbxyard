from django.shortcuts import render
from django.http import JsonResponse
from .tasks import DssTask
from datetime import datetime


# Create your views here.

def do(request):
    # invoke task
    print('start to handle request')

    # task.delay()
    str_now = datetime.now().strftime("%Y-%m-%d")
    DssTask.delay(args=('arg1', 'hello {}'.format(str_now)))
    # DssTask.apply_async(args=('arg1', 'hello {}'.format(str_now)), queue='work_queue')

    print('end to handle request')

    return JsonResponse({'result': 'ok'})
