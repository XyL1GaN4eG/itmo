# plotting.py

import numpy as np
import matplotlib.pyplot as plt

def plot_all(xs, ys, results, output_filename=None):
    """
    Построить на одном графике:
    - исходные точки (через scatter);
    - все эмпирические функции (линийно): φ(x) от минимального до максимального x с небольшим запасом.
    
    Параметр results – словарь вида:
    {
      "Linear": {"coef": (a,b), "func": lambda x: a + b x, "rmse": σ, ...},
      "Poly2": {"coef": (a,b,c), "func": lambda x: a+b x+c x^2, "rmse": σ, ...},
      ...
    }
    output_filename – если указан, сохраняем рисунок в файл, иначе показываем plt.show().
    """
    x_arr = np.array(xs)
    y_arr = np.array(ys)

    # Диапазон построения: от xmin - Δ до xmax + Δ, где Δ = 5% ширины
    xmin, xmax = np.min(x_arr), np.max(x_arr)
    x_span = xmax - xmin
    delta = 0.05 * x_span if x_span != 0 else 1.0
    plot_x = np.linspace(xmin - delta, xmax + delta, 500)

    plt.figure(figsize=(8, 6))
    plt.scatter(x_arr, y_arr, color='black', label='Исходные точки')

    # Цвета matplotlib по умолчанию последовательно назначает кривым разный цвет
    for name, info in results.items():
        func = info['func']
        # Если функция не реализована (nan коэфы), пропускаем
        try:
            plot_y = func(plot_x)
            if np.all(np.isnan(plot_y)):
                continue
        except Exception:
            continue
        plt.plot(plot_x, plot_y, label=f"{name} (σ={info['rmse']:.3f})")

    plt.xlabel("x")
    plt.ylabel("y")
    plt.title("Аппроксимирующие функции и исходные данные")
    plt.legend()
    plt.grid(True)

    if output_filename:
        plt.savefig(output_filename, dpi=300)
        plt.close()
        print(f"График сохранён в файл '{output_filename}'.")
    else:
        plt.show()
