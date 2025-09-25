import math


def f(x):
    return math.sqrt(1 + x ** 2) + math.exp(-2 * x)


def df(x):
    return x / math.sqrt(1 + x ** 2) - 2 * math.exp(-2 * x)


def d2f(x):
    return 1 / (1 + x ** 2) ** (3 / 2) + 4 * math.exp(-2 * x)
