# metrics.py

import numpy as np

def compute_S(phi_arr, y_arr):
    """
    Мера отклонения S = sum[(phi(x_i) - y_i)^2].
    Здесь phi_arr и y_arr – одномерные iterable (списки или numpy-ы).
    """
    phi = np.array(phi_arr, dtype=float)
    y = np.array(y_arr, dtype=float)
    return np.sum((phi - y) ** 2)

def compute_rmse(phi_arr, y_arr):
    """
    σ = sqrt( S / n ), где n = len(phi_arr).
    """
    n = len(phi_arr)
    if n == 0:
        return np.nan
    S = compute_S(phi_arr, y_arr)
    return np.sqrt(S / n)

def compute_pearson(xs, ys):
    """
    Коэффициент корреляции Пирсона R для x и y.
    Вернёт (R, сообщение).
    """
    x = np.array(xs, dtype=float)
    y = np.array(ys, dtype=float)
    if len(x) < 2:
        return np.nan, "Недостаточно точек для вычисления корреляции."
    xm = np.mean(x)
    ym = np.mean(y)
    num = np.sum((x - xm) * (y - ym))
    den = np.sqrt(np.sum((x - xm) ** 2) * np.sum((y - ym) ** 2))
    if den == 0:
        return np.nan, "Нельзя вычислить (деление на ноль)."
    R = num / den
    return R, None

def compute_r2(y_true, y_pred):
    """
    Коэффициент детерминации R^2 = 1 - SS_res/SS_tot
    SS_res = sum((y_i - phi_i)^2)
    SS_tot = sum((y_i - y_mean)^2)
    """
    y = np.array(y_true, dtype=float)
    phi = np.array(y_pred, dtype=float)
    ss_res = np.sum((y - phi) ** 2)
    ss_tot = np.sum((y - np.mean(y)) ** 2)
    if ss_tot == 0:
        return np.nan
    return 1 - ss_res / ss_tot

def interpret_r2(r2):
    """
    Возвращает строку-описание качества по R^2:
    - R^2 >= 0.9: "Отличное приближение"
    - 0.7 <= R^2 < 0.9: "Хорошее приближение"
    - 0.5 <= R^2 < 0.7: "Удовлетворительное приближение"
    - R^2 < 0.5: "Плохое приближение"
    Если r2 nan – соответствующее сообщение.
    """
    if np.isnan(r2):
        return "R^2 не определён."
    if r2 >= 0.9:
        return "Отличное приближение (R² ≥ 0.9)."
    elif r2 >= 0.7:
        return "Хорошее приближение (0.7 ≤ R² < 0.9)."
    elif r2 >= 0.5:
        return "Удовлетворительное приближение (0.5 ≤ R² < 0.7)."
    else:
        return "Плохое приближение (R² < 0.5)."
