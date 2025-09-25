def quadratic_approximation_method(f, x0, h, epsilon_func=1e-6, epsilon_coord=1e-6, max_steps=100):
    """
    Квадратичная аппроксимация с логикой обновления точек как в примере
    :param f: целевая функция
    :param x0: начальная точка
    :param h: начальный шаг
    :param epsilon_func: точность изменения функции
    :param epsilon_coord: точность изменения координаты
    :param max_steps: максимальное число итераций
    """
    
    # Инициализация первых трех точек
    f1 = f(x0)
    f2 = f(x0+h)
    if f1 > f2:
        x1 = x0 - h  # Сдвиг влево
        x2 = x0      # Начальная точка
        x3 = x0 + h  # Сдвиг вправо
    else:
        x1 = x0      # Начальная точка
        x2 = x0 + h  # Сдвиг вправо
        x3 = x0 + 2 * h # Дальнейший сдвиг вправо
    
    for step in range(max_steps):
        print(f"\nIteration {step}:")
        f1 = f(x1)
        f2 = f(x2)
        f3 = f(x3)
        print("  x_1 = ", x1, " (f(x_1) = ", f1, ")")
        print("  x_2 = ", x2, " (f(x_2) = ", f2, ")")
        print("  x_3 = ", x3, " (f(x_3) = ", f3, ")")
        
        # ШАГ 6: вычисление текущего минимума
        points = [x1, x2, x3]
        f_values = [f1, f2, f3]
        # min_index = 
        
        f_min = min(f1, f2, f3)
        print("F_min = ", f_min)

        # ШАГ 7: вычисление x_hat (минимум квадратичной аппроксимации)
        denominator = 2 * (f1 * (x2 - x3) + f2 * (x3 - x1) + f3 * (x1 - x2))
        if denominator == 0:
            continue
        numerator =  f1 * (x2 ** 2 - x3 ** 2) + f2 * (x3 ** 2-x1 ** 2) + f3 * (x1 ** 2 - x2 ** 2)
        x_hat = numerator / denominator
        
        f_hat = f(x_hat)
        
        # ШАГ 8
        func = {f1: x1, f2: x2, f3: x3, f_hat: x_hat}
        sorted_x_mins = [
            func[x] for x in sorted([f1, f2, f3, f_hat])
        ]
        x_min = sorted_x_mins[0]
        f_stop_criteria = (abs((f_min - f_hat)/f_hat)) < epsilon_func
        x_stop_criteria = (abs((x_min - x_hat) / x_hat)) < epsilon_coord
        if f_stop_criteria and x_stop_criteria:
            print("DONE")
            return x_hat
        else:
            if x_hat >= x1 and x_hat<=x3:
                best_x = min(x_hat, x_min)
                sorted_points = sorted([x1, x2, x3])
                
                index = 0
                for i in range(len(sorted_points)):
                    if best_x > sorted_points[i]:
                        index = i
                        
                if index >= 1:
                    sorted_points = sorted([best_x, x2, x3])
                else:
                    sorted_points = sorted([best_x, x1, x2])
                
                x1, x2, x3 = (
                    sorted_points[0],
                    sorted_points[1],
                    sorted_points[2],
                )
            else:
                x1, x2, x3 = x2, x2+h, x2 + h * 2
                f1 = f(x1)
                f2 = f(x2)
                if f1>f2:
                    x3 = x_hat + 2*h
                else:
                    x3 = x_hat - h
                sorted_points = sorted([x1, x2, x3])
                x1, x2, x3 = sorted_points[0], sorted_points[1], sorted_points[2],
        print("x_hat = ", x_hat)
                
    return x_hat
