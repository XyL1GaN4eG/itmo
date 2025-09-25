# regression.py

import numpy as np
from scipy.optimize import curve_fit
from models import (
    linear_model,
    poly2_model,
    poly3_model,
    exp_model,
    log_model,
    power_model,
)
from metrics import compute_S

def fit_linear(xs, ys):
    """
    МНК для линейной модели φ(x)=a+bx через нормальные уравнения.
    Возвращает коэффициенты a, b и массив предсказаний phi_i.
    """
    n = len(xs)
    X = np.vstack((np.ones(n), xs)).T  # матрица n×2: [1, x_i]
    y = np.array(ys)
    # (X^T X) β = X^T y
    # Решаем через np.linalg.lstsq -- более стабильный вариант
    beta, *_ = np.linalg.lstsq(X, y, rcond=None)
    a, b = beta
    print("линейная")
    print("коэфы a:", a)
    print("коэфы b:", b)
    phi = linear_model(np.array(xs), a, b)
    S = compute_S(phi, ys)
    return (a, b), phi, S

def fit_poly2(xs, ys):
    """
    МНК для полинома 2-й степени. Возвращает coeffs (a,b,c) и phi, S.
    """
    # np.polyfit вернёт [c, b, a], т.к. в порядке от старшей степени.
    
    coeffs = np.polyfit(xs, ys, deg=2)
    c, b, a = coeffs  # перепакуем в a,b,c
    phi = poly2_model(np.array(xs), a, b, c)
    S = compute_S(phi, ys)
    print("вторая степень")
    print("коэфы a:", a)
    print("коэфы b:", b)
    print("коэфы c:", c)
    return (a, b, c), phi, S

def fit_poly3(xs, ys):
    """
    МНК для полинома 3-й степени. Возвращает (a,b,c,d), phi, S.
    """
    coeffs = np.polyfit(xs, ys, deg=3)
    d, c, b, a = coeffs  # np.polyfit: [d, c, b, a]
    print("третья степень")
    print("коэфы a:", a)
    print("коэфы b:", b)
    print("коэфы c:", c)
    print("коэфы d:", d)
    phi = poly3_model(np.array(xs), a, b, c, d)
    S = compute_S(phi, ys)
    return (a, b, c, d), phi, S

def fit_exp(xs, ys):
    """
    МНК для экспоненциальной φ(x) = A * exp(B x) через scipy.curve_fit.
    Возвращает (A, B), phi, S.
    """
    x_arr = np.array(xs)
    y_arr = np.array(ys)

    
    
    # Если в yi есть нули или противоположный знак, curve_fit может выдать предупреждение.
    # Мы не логарифмируем вручную, а даём curve_fit самому минимизировать S.
    # Сделаем начальное приближение: A0 = y0 (если y0 != 0), B0=0
    if np.any(x_arr <= 0):
        phi = np.full_like(x_arr, np.nan, dtype=float)
        return (np.nan, np.nan), phi, np.nan
    
    A0 = y_arr[0] if y_arr[0] != 0 else 1.0
    try:
        popt, _ = curve_fit(exp_model, x_arr, y_arr, p0=[A0, 0.0], maxfev=10000)
    except RuntimeError:
        # Если не сходится, возвращаем (nan, nan)
        popt = (np.nan, np.nan)
        phi = np.full_like(x_arr, np.nan)
        S = np.nan
        return popt, phi, S

    A, B = popt
    phi = exp_model(x_arr, A, B)
    S = compute_S(phi, ys)
    print("коэфы a:", A)
    print("коэфы b:", B)
    return (A, B), phi, S

def fit_log(xs, ys):
    """
    МНК для логарифмической φ(x) = A + B ln(x). x должны быть > 0.
    Если хотя бы один x <=0, возвращаем (nan), phi=array(nan), S=nan.
    """
    x_arr = np.array(xs)
    y_arr = np.array(ys)
    if np.any(x_arr <= 0):
        # Нельзя логарифмировать
        phi = np.full_like(x_arr, np.nan, dtype=float)
        return (np.nan, np.nan), phi, np.nan

    # Начальное приближение: A0 = y0, B0 = 1.0
    A0 = y_arr[0]
    try:
        popt, _ = curve_fit(log_model, x_arr, y_arr, p0=[A0, 1.0], maxfev=10000)
    except RuntimeError:
        popt = (np.nan, np.nan)
        phi = np.full_like(x_arr, np.nan)
        S = np.nan
        return popt, phi, S

    A, B = popt
    print("коэфы a:", A)
    print("коэфы b:", B)
    phi = log_model(x_arr, A, B)
    S = compute_S(phi, ys)
    return (A, B), phi, S

def fit_power(xs, ys):
    """
    МНК для степенной φ(x) = A * x^B. x должны быть > 0 или < 0, но тогда
    x^B для нецелых B может быть комплексным. Мы будем требовать x>0.
    Если хотя бы один x <= 0, вернём (nan).
    """
    x_arr = np.array(xs)
    y_arr = np.array(ys)
    if np.any(x_arr <= 0) or np.any(y_arr == 0):
        # Степенная модель не применима (или слишком неопределённо)
        phi = np.full_like(x_arr, np.nan, dtype=float)
        return (np.nan, np.nan), phi, np.nan

    # Подготовим данные для линейного МНК: ln(y) = ln(A) + B ln(x)
    ln_x = np.log(x_arr)
    ln_y = np.log(y_arr)
    n = len(ln_x)
    # Прямой МНК для ln_y = c0 + c1 * ln_x
    X = np.vstack((np.ones(n), ln_x)).T
    print("Вот", X)
    beta, *_ = np.linalg.lstsq(X, ln_y, rcond=None)
    print("Вот бета: ",beta)
    c0, B = beta
    A = np.exp(c0)
    phi = power_model(x_arr, A, B)
    S = compute_S(phi, ys)
    print("коэфы a:", A)
    print("коэфы b:", B)
    return (A, B), phi, S
