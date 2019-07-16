import time
from .app import app


@app.task
def add(x, y):
    time.sleep(3)
    return x + y
