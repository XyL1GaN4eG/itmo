# models.py

import numpy as np

def linear_model(x, a, b):
    """
    Линейная функция: φ(x) = a + b x
    """
    return a + b * x

def poly2_model(x, a, b, c):
    """
    Полином 2-й степени: φ(x) = a + b x + c x^2
    """
    return a + b * x + c * x**2

def poly3_model(x, a, b, c, d):
    """
    Полином 3-й степени: φ(x) = a + b x + c x^2 + d x^3
    """
    return a + b * x + c * x**2 + d * x**3

def exp_model(x, A, B):
    """
    Экспоненциальная функция: φ(x) = A * exp(B x)
    """
    return A * np.exp(B * x)

def log_model(x, A, B):
    """
    Логарифмическая функция: φ(x) = A + B * ln(x)
    Замечание: определена только при x > 0.
    """
    # np.log выдаст предупреждение, если x <= 0
    return A + B * np.log(x)

def power_model(x, A, B):
    """
    Степенная функция: φ(x) = A * x^B
    Замечание: если x <= 0, то np.log(x) не определён. Будем проверять снаружи.
    """
    return A * x**B
