import numpy as np
from sympy import symbols, diff, lambdify, solve, Eq

x_1_0 = 2
x_2_0 = 3
#начальная точка (2, 3)


#покоординатного спуска, градиентного спуска, наискорейшего спуска
def f(x_1, x_2, ex=0): # y(x) -> y но если надо вернуть не значение, а сам y(x), то это lambda
    if ex == 2:
        return f'4 * ({x_1}) ** 2 + 5 * ({x_2}) ** 2 - 3 * ({x_1}) * ({x_2}) + 9 * ({x_1}) - 2 * ({x_2}) + 5'
    if ex != 0:
        return lambda x1, x2: 4 * x_1 ** 2 + 5 * x_2 ** 2 - 3 * x_1 * x_2 + 9 * x_1 - 2 * x_2 + 5
    return 4 * x_1 ** 2 + 5 * x_2 ** 2 - 3 * x_1 * x_2 + 9 * x_1 - 2 * x_2 + 5


def coordinate_descent_method(x_1_0, x_2_0, eps):
    x_1 = symbols('x_1')
    x_2 = symbols('x_2')
    counter = 0
    while True:
        if counter % 2 == 0:
            df_dx_1 = diff(f(x_1, x_2_0), x_1)
            equation_1 = Eq(df_dx_1, 0)
            solutions_x_1 = solve(equation_1, x_1)
            x_1_new = float(solutions_x_1[0])
            if (abs(f(x_1_new, x_2_0) - f(x_1_0, x_2_0)) < eps):
                return f(x_1_new, x_2_0)
            if (f(x_1_new, x_2_0) < f(x_1_0, x_2_0)):
                x_1_0 = x_1_new
            print(f"Iteration: {counter+1}, x_1 = {x_1_new}, x_2 = {x_2_0}, f(x_1, x_2) = {f(x_1_new, x_2_0)}")
        if counter % 2 == 1:
            df_dx_2 = diff(f(x_1_0, x_2), x_2)
            equation_2 = Eq(df_dx_2, 0)
            solutions_x_2 = solve(equation_2, x_2)
            x_2_new = float(solutions_x_2[0])
            if (abs(f(x_1_0, x_2_new) - f(x_1_0, x_2_0)) < eps):
                return f"Сoordinates of the extremum: {x_1_0}, {x_2_new}, \nthe value of the function at a given point: {f(x_1_0, x_2_new)}"
                #return f(x_1_0, x_2_new)
            if (f(x_1_0, x_2_new) < f(x_1_0, x_2_0)):
                x_2_0 = x_2_new
            print(f"Iteration: {counter+1}, x_1 = {x_1_0}, x_2 = {x_2_new}, f(x_1, x_2) = {f(x_1_0, x_2_new)}")
        counter += 1

def newton(a, b, n, eps, fixed_var_value=0, grad_x1=0, grad_x2=0, x_1_0=0, x_2_0=0):
    x = symbols('x')
    h = symbols('h')
    fixed_sym = symbols('fixed')  # нужно подставить число
    if n == 1:
        expr = f(x, fixed_sym)
        expr = expr.subs(fixed_sym, fixed_var_value)
        df_dx = diff(expr, x)
        df_dx_2 = diff(df_dx, x)
        df_func = lambdify(x, df_dx, 'numpy')
        df2_func = lambdify(x, df_dx_2, 'numpy')
    elif n == 2:
        expr = f(fixed_sym, x)
        expr = expr.subs(fixed_sym, fixed_var_value)
        df_dx = diff(expr, x)
        df_dx_2 = diff(df_dx, x)
        df_func = lambdify(x, df_dx, 'numpy')
        df2_func = lambdify(x, df_dx_2, 'numpy')
    elif n == 3:
        # Подставляем числовые значения градиентов и координат
        expr = f(x_1_0 - h * grad_x1, x_2_0 - h * grad_x2)
        print(f"newton, expression 95: {expr}")
        df_dh = diff(expr, h)
        df_dh2 = diff(df_dh, h)
        # Создаем числовые функции
        df_func = lambdify(h, df_dh, 'numpy')
        df2_func = lambdify(h, df_dh2, 'numpy')
    elif n == 10:
        df_dh = diff(fixed_var_value, h)
        df_dh2 = diff(df_dh, h)
        print(df_dh)
        print(df_dh2)
        df_func = lambda h: eval(str(df_dh).replace("h", str(h)))
        df2_func = lambda h: eval(str(df_dh2).replace("h", str(h)))
    x0 = (a + b) / 2
    while True:
        x_new = x0 - df_func(x0) / df2_func(x0)
        if abs(df_func(x0)) <= eps:
            return float(x_new)
        x0 = x_new
        
def coordinate_descent_method_with_2_lab(x_1_0, x_2_0, eps):
    x_1 = symbols('x_1')
    x_2 = symbols('x_2')
    counter = 0
    while True:
        if counter % 2 == 0:
            #x_1_new = half_method(-2, 0, 0.0001)
            x_1_new = newton(-2, 0, 1, 0.0001, x_2_0)
            if (abs(f(x_1_new, x_2_0) - f(x_1_0, x_2_0)) < eps):
                return f(x_1_new, x_2_0)
            if (f(x_1_new, x_2_0) < f(x_1_0, x_2_0)):
                x_1_0 = x_1_new
            print(f"Iteration: {counter+1}, x_1 = {x_1_new}, x_2 = {x_2_0}, f(x_1, x_2) = {f(x_1_new, x_2_0)}")
        if counter % 2 == 1:
            #x_2_new = half_method(-2, 0, 0.0001)
            x_2_new = newton(-2, 0, 2, 0.0001, x_1_0)
            if (abs(f(x_1_0, x_2_new) - f(x_1_0, x_2_0)) < eps):
                return f"Сoordinates of the extremum: {x_1_0}, {x_2_new}, \nthe value of the function at a given point: {f(x_1_0, x_2_new)}"
            if (f(x_1_0, x_2_new) < f(x_1_0, x_2_0)):
                x_2_0 = x_2_new
            print(f"Iteration: {counter+1}, x_1 = {x_1_0}, x_2 = {x_2_new}, f(x_1, x_2) = {f(x_1_0, x_2_new)}")
        counter += 1




def gradient(x_1_0, x_2_0, eps, lambd):
    x_1 = symbols('x_1')
    x_2 = symbols('x_2')
    counter = 0
    for i in range(100):
        #x_1_1 = x_1_0 - lambd * df_dx_1(x_1_0, x_2_0)
        #x_2_1 = x_2_0 - lambd * df_dx_2(x_1_0, x_2_0)
        x_1_1 = x_1_0 - lambd * eval(str(diff(f(x_1, x_2),x_1)).replace('x_1', str(x_1_0)).replace('x_2', str(x_2_0)))
        # print(x_1_1)
        x_2_1 = x_2_0 - lambd * eval(str(diff(f(x_1, x_2),x_2)).replace('x_1', str(x_1_0)).replace('x_2', str(x_2_0)))
        print(f"Iteration: {counter + 1}")
        print(f"x_{i} = ({x_1_1}, {x_2_1})")
        print(f"f(x_{i}) = {f(x_1_0, x_2_0)}")
        print(f"delt_f(x_{counter}) = {evwal(str(diff(f(x_1, x_2),x_1)).replace('x_1', str(x_1_0)).replace('x_2', str(x_2_0))), eval(str(diff(f(x_1, x_2),x_2)).replace('x_1', str(x_1_0)).replace('x_2', str(x_2_0)))}")
        print(f"x_1_1 = {x_1_1}, x_2_1 = {x_2_1}")
        print(f"f(x_{i + 1}) = {f(x_1_1, x_2_1)}")
        print(f"delta = {abs(f(x_1_0, x_2_0) - f(x_1_1, x_2_1))}")
        print("")
        if (abs(f(x_1_0, x_2_0) - f(x_1_1, x_2_1)) < eps):
            return f"Сoordinates of the extremum: {x_1_0}, {x_2_0}, \nthe value of the function at a given point: {f(x_1_0, x_2_0)}"
        if (f(x_1_0, x_2_0) - f(x_1_1, x_2_1) < 0):
            print(f"The step is too big, the step is reduced from {lambd} to {lambd * 0.8}")
            lambd *= 0.8
        else:
            x_1_0 = x_1_1
            x_2_0 = x_2_1
        counter += 1

def steepest_descent_method_with_2_lab(x_1_0, x_2_0, eps):
    counter = 1
    x_1= symbols('x_1')
    x_2 = symbols('x_2')
    h = symbols('h')

    for i in range(100):
        print(f"Iteration {counter}")
        grad_x1 = float(diff(f(x_1, x_2), x_1).subs({x_1: x_1_0, x_2: x_2_0}))
        grad_x2 = float(diff(f(x_1, x_2), x_2).subs({x_1: x_1_0, x_2: x_2_0}))
        print(f"derivative_1 = {grad_x1}")
        print(f"derivative_2 = {grad_x2}")
        phi = f(x_1_0 - h * grad_x1, x_2_0 - h * grad_x2, 2)
        print(f"equation for finding h: {phi}")
        h_val = newton(-2, 0, 10, 0.0001, phi, grad_x1, grad_x2, x_1_0, x_2_0)
        # d_phi = diff(phi, h)
        # print(f"derivative of h_1: {d_phi}")
        # h_val = solve(d_phi, h)[0]
        print(f"h = {h_val}")

        d2_phi = diff(phi, h, 2).subs(h, h_val)

        if d2_phi > 0:
            print("Second derivative of h_1 is greater than 0")
            x_1_new = x_1_0 - float(h_val) * grad_x1
            x_2_new = x_2_0 - float(h_val) * grad_x2
            print(f"x_1{i+1} = {x_1_new}, x_2{i+1} = {x_2_new}")
            # Проверка сходимости
            if abs(x_1_new - x_1_0) < eps and abs(x_2_new - x_2_0) < eps:
                break
            x_1_0, x_2_0 = x_1_new, x_2_new
        counter += 1
        print()

    return f"Сoordinates of the extremum: {x_1_0}, {x_2_0}, \nthe value of the function at a given point: {f(x_1_0, x_2_0)}"

def steepest_descent_method(x_1_0, x_2_0, eps):
    counter = 1
    x_1, x_2 = symbols('x_1 x_2')
    h = symbols('h')

    for i in range(100):
        print(f"Iteration {counter}")
        grad_x1 = diff(f(x_1, x_2), x_1).subs({x_1: x_1_0, x_2: x_2_0})
        grad_x2 = diff(f(x_1, x_2), x_2).subs({x_1: x_1_0, x_2: x_2_0})
        print(f"derivative_1 = {grad_x1}")
        print(f"derivative_2 = {grad_x2}")
        print(x_1_0 - h * grad_x1, x_2_0 - h * grad_x2)
        phi = f(x_1_0 - h * grad_x1, x_2_0 - h * grad_x2)
        print(f"equation for finding h: {phi}")

        d_phi = diff(phi, h)
        print(f"derivative of h_1: {d_phi}")
        h_val = solve(d_phi, h)[0]
        print(f"h = {h_val}")

        d2_phi = diff(phi, h, 2).subs(h, h_val)

        if d2_phi > 0:
            print("Second derivative of h_1 is greater than 0")
            x_1_new = x_1_0 - float(h_val) * grad_x1
            x_2_new = x_2_0 - float(h_val) * grad_x2
            print(f"x_1{i+1} = {x_1_new}, x_2{i+1} = {x_2_new}")

            # Проверка сходимости
            if abs(x_1_new - x_1_0) < eps and abs(x_2_new - x_2_0) < eps:
                break

            x_1_0, x_2_0 = x_1_new, x_2_new
        counter += 1
        print("")

    return f"Сoordinates of the extremum: {x_1_0}, {x_2_0}, \nthe value of the function at a given point: {f(x_1_0, x_2_0)}"

print("Gradient: ")
print(gradient(2, 3, 0.0001, 0.1))
print("")
print("Coordinate descent method: ")
print(coordinate_descent_method(2, 3, 0.0001))
print("")
print("Coordinate descent method with newton: ")
print(coordinate_descent_method_with_2_lab(2, 3, 0.0001))
print("")
print("Steepest descent method: ")
print(steepest_descent_method(2, 3, 0.0001))
print("")
print("Steepest descent method with newton: ")
print("")
print(steepest_descent_method_with_2_lab(2, 3, 0.0001))