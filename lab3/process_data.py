import json
import sys
import random
from print_result import print_result
from cm_timer import cm_timer_1
from unique import Unique

def load_data(path):
    with open(path, encoding='utf‑8') as f:
        return json.load(f)

@print_result
def f1(arg):
    # список профессий без повторений, игнорируя регистр → сортировка без учёта регистра
    return sorted({ item['job-name'].lower() for item in arg })

@print_result
def f2(arg):
    # фильтрация
    return list(filter(lambda x: x.lower().startswith('программист'), arg))

@print_result
def f3(arg):

    return list(map(lambda x: f"{x}, с опытом Python", arg))

@print_result
def f4(arg):
    salaries = (random.randint(100000, 200000) for _ in arg)
    # zip
    return [f"{job}, зарплата {sal} руб." for job, sal in zip(arg, salaries)]

if __name__ == '__main__':
    path = sys.argv[1] if len(sys.argv) > 1 else 'data_light.json'
    data = load_data(path)
    with cm_timer_1():
        f4(f3(f2(f1(data))))
