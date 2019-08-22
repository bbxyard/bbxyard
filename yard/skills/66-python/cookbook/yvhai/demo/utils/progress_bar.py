from time import sleep

def progress(percent=0, width=30):
  left = width * percent // 100
  right = width - left
  print('\r[', '#' * left, ' ' * right, ']',
    f'{percent:.0f}%',
    sep='', end='', flush=True
    )


if __name__ == '__main__':
  for i in range(101):
    progress(i)
    sleep(0.1)
