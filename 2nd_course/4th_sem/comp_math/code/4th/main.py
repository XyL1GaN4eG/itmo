# main.py

import sys
import argparse
import numpy as np

from data_loader import load_from_console, load_from_file
from regression import (
    fit_linear,
    fit_poly2,
    fit_poly3,
    fit_exp,
    fit_log,
    fit_power,
)
from metrics import compute_rmse, compute_pearson, compute_r2, interpret_r2
from plotting import plot_all

def main():
    parser = argparse.ArgumentParser(
        description="Поиск наилучшей эмпирической функции через метод наименьших квадратов"
    )
    group = parser.add_mutually_exclusive_group(required=True)
    group.add_argument(
        "-f", "--file", metavar="FILE", help="Загрузить исходные данные из файла"
    )
    group.add_argument(
        "-c", "--console", action="store_true", help="Ввести исходные данные с консоли"
    )
    parser.add_argument(
        "-o", "--output", metavar="OUT", help="Записать результаты в файл (по умолчанию – консоль)"
    )
    parser.add_argument(
        "-g", "--plot", metavar="PLOT", help="Сохранить график в файл (png). Если не указан, показываем окно."
    )

    args = parser.parse_args()

    # Загрузка данных
    if args.console:
        xs, ys = load_from_console()
    else:
        xs, ys = load_from_file(args.file)

    xs = np.array(xs, dtype=float)
    ys = np.array(ys, dtype=float)

    # Словарь для хранения результатов всех моделей
    all_results = {}

    # 1) Линейная
    try:
        (a_lin, b_lin), phi_lin, S_lin = fit_linear(xs, ys)
        rmse_lin = compute_rmse(phi_lin, ys)
    except Exception as e:
        print("Ошибка при линейном приближении:", e)
        (a_lin, b_lin), phi_lin, S_lin, rmse_lin = (np.nan, np.nan), np.full_like(xs, np.nan), np.nan, np.nan
    all_results["Линейная"] = {
        "coef": (a_lin, b_lin),
        "func": lambda x, a=a_lin, b=b_lin: a + b * x,
        "phi": phi_lin,
        "S": S_lin,
        "rmse": rmse_lin,
    }

    # 2) Полином 2-й степени
    try:
        (a2, b2, c2), phi2, S2 = fit_poly2(xs, ys)
        rmse2 = compute_rmse(phi2, ys)
    except Exception as e:
        print("Ошибка при квадратичном приближении:", e)
        (a2, b2, c2), phi2, S2, rmse2 = (np.nan, np.nan, np.nan), np.full_like(xs, np.nan), np.nan, np.nan
    all_results["Полином_2"] = {
        "coef": (a2, b2, c2),
        "func": lambda x, a=a2, b=b2, c=c2: a + b * x + c * x**2,
        "phi": phi2,
        "S": S2,
        "rmse": rmse2,
    }

    # 3) Полином 3-й степени
    try:
        (a3, b3, c3, d3), phi3, S3 = fit_poly3(xs, ys)
        rmse3 = compute_rmse(phi3, ys)
    except Exception as e:
        print("Ошибка при кубическом приближении:", e)
        (a3, b3, c3, d3), phi3, S3, rmse3 = (np.nan, np.nan, np.nan, np.nan), np.full_like(xs, np.nan), np.nan, np.nan
    all_results["Полином_3"] = {
        "coef": (a3, b3, c3, d3),
        "func": lambda x, a=a3, b=b3, c=c3, d=d3: a + b * x + c * x**2 + d * x**3,
        "phi": phi3,
        "S": S3,
        "rmse": rmse3,
    }

    # 4) Экспоненциальная
    try:
        (A_exp, B_exp), phi_exp, S_exp = fit_exp(xs, ys)
        rmse_exp = compute_rmse(phi_exp, ys)
    except Exception as e:
        print("Ошибка при экспоненциальном приближении:", e)
        (A_exp, B_exp), phi_exp, S_exp, rmse_exp = (np.nan, np.nan), np.full_like(xs, np.nan), np.nan, np.nan
    all_results["Экспоненциальная"] = {
        "coef": (A_exp, B_exp),
        "func": lambda x, A=A_exp, B=B_exp: A * np.exp(B * x),
        "phi": phi_exp,
        "S": S_exp,
        "rmse": rmse_exp,
    }

    # 5) Логарифмическая
    try:
        (A_log, B_log), phi_log, S_log = fit_log(xs, ys)
        rmse_log = compute_rmse(phi_log, ys)
    except Exception as e:
        print("Ошибка при логарифмическом приближении:", e)
        (A_log, B_log), phi_log, S_log, rmse_log = (np.nan, np.nan), np.full_like(xs, np.nan), np.nan, np.nan
    all_results["Логарифмическая"] = {
        "coef": (A_log, B_log),
        "func": lambda x, A=A_log, B=B_log: A + B * np.log(x),
        "phi": phi_log,
        "S": S_log,
        "rmse": rmse_log,
    }

    # 6) Степенная
    try:
        (A_pow, B_pow), phi_pow, S_pow = fit_power(xs, ys)
        rmse_pow = compute_rmse(phi_pow, ys)
    except Exception as e:
        print("Ошибка при степенном приближении:", e)
        (A_pow, B_pow), phi_pow, S_pow, rmse_pow = (np.nan, np.nan), np.full_like(xs, np.nan), np.nan, np.nan
    all_results["Степенная"] = {
        "coef": (A_pow, B_pow),
        "func": lambda x, A=A_pow, B=B_pow: A * x**B,
        "phi": phi_pow,
        "S": S_pow,
        "rmse": rmse_pow,
    }

    # ========== Вычисления для линейной модели: коэффициент корреляции Пирсона =
    R_lin, pearson_err = compute_pearson(xs, ys)
    r2_lin = compute_r2(ys, all_results["Линейная"]["phi"])
    interp_lin = interpret_r2(r2_lin)

    # ========== Поиск наилучшей модели (по минимальному σ)
    # Собираем все σ, пропуская nan
    rmses = [info["rmse"] for info in all_results.values() if not np.isnan(info["rmse"])]
    if rmses:
        min_rmse = min(rmses)
        best_methods = [
            name for name, info in all_results.items()
            if not np.isnan(info["rmse"]) and np.isclose(info["rmse"], min_rmse)
        ]
    else:
        min_rmse = np.nan
        best_methods = []   

    # ========== Подготовка вывода =
    out_lines = []
    out_lines.append("Результаты приближения (метод наименьших квадратов):")
    out_lines.append("")

    for name, info in all_results.items():
        coef = info["coef"]
        rmse = info["rmse"]
        S = info["S"]
        out_lines.append(f"=== {name} ===")
        out_lines.append(f"Коэффициенты: {coef}")
        out_lines.append(f"Мера отклонения S = {S:.3f}")
        out_lines.append(f"Среднеквадратичное отклонение σ = {rmse:.3f}")
        # Подать массивы x_i, y_i, φ(x_i), ε_i
        phi_arr = info["phi"]
        out_lines.append("i\t x_i\t y_i\t φ(x_i)\t ε_i = φ(x_i)-y_i")
        for i in range(len(xs)):
            xi = xs[i]
            yi = ys[i]
            phi_i = phi_arr[i]
            eps_i = phi_i - yi if not np.isnan(phi_i) else np.nan
            out_lines.append(f"{i+1}\t {xi:.3f}\t {yi:.3f}\t {phi_i:.3f}\t {eps_i:.3f}")
        out_lines.append("")

    # Вывод для линейной: корреляция и R²
    out_lines.append("=== Дополнительно для линейной модели ===")
    if pearson_err:
        out_lines.append(f"Корреляция Пирсона R: не вычислена ({pearson_err})")
    else:
        out_lines.append(f"Корреляция Пирсона R = {R_lin:.3f}")
    out_lines.append(f"Коэффициент детерминации R² = {r2_lin:.3f}")
    out_lines.append(f"Качество по R²: {interp_lin}")
    out_lines.append("")

    # Какая модель лучшая
    if best_methods:
        if len(best_methods) == 1:
            out_lines.append(
                f"Наилучшая аппроксимирующая функция: {best_methods[0]} (σ = {min_rmse:.3f})"
            )
        else:
            methods_list = ", ".join(best_methods)
            out_lines.append(
                f"Несколько лучших функций (σ = {min_rmse:.3f}): {methods_list}"
            )
    else:
        out_lines.append("Не удалось определить лучшую модель (все σ = nan).")

    # Сохранение/печать результатов
    if args.output:
        try:
            with open(args.output, 'w', encoding='utf-8') as f:
                for line in out_lines:
                    f.write(line + "")
            print(f"Результаты записаны в файл '{args.output}'.")
        except Exception as e:
            print("Ошибка при записи в файл:", e)
            print("Выводим результаты в консоль:")
            print("\n".join(out_lines))
    else:
        print("\n".join(out_lines))

    # Построение графика
    try:
        plot_all(xs, ys, {
            "Линейная": {
                "func": all_results["Линейная"]["func"],
                "rmse": all_results["Линейная"]["rmse"],
                "phi": all_results["Линейная"]["phi"]
            },
            "Полином_2": {
                "func": all_results["Полином_2"]["func"],
                "rmse": all_results["Полином_2"]["rmse"],
                "phi": all_results["Полином_2"]["phi"]
            },
            "Полином_3": {
                "func": all_results["Полином_3"]["func"],
                "rmse": all_results["Полином_3"]["rmse"],
                "phi": all_results["Полином_3"]["phi"]
            },
            "Экспоненциальная": {
                "func": all_results["Экспоненциальная"]["func"],
                "rmse": all_results["Экспоненциальная"]["rmse"],
                "phi": all_results["Экспоненциальная"]["phi"]
            },
            "Логарифмическая": {
                "func": all_results["Логарифмическая"]["func"],
                "rmse": all_results["Логарифмическая"]["rmse"],
                "phi": all_results["Логарифмическая"]["phi"]
            },
            "Степенная": {
                "func": all_results["Степенная"]["func"],
                "rmse": all_results["Степенная"]["rmse"],
                "phi": all_results["Степенная"]["phi"]
            },
        }, output_filename=args.plot)
    except Exception as e:
        print("Ошибка при построении графиков:", e)


if __name__ == "__main__":
    main()
