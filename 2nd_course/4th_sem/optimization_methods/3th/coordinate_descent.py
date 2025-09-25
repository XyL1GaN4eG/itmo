# coordinate_descent.py
import sympy as sp
from objective import f_expr, f
from newton import newton_method

x1, x2 = sp.symbols('x1 x2')

def coordinate_descent(x1_0, x2_0, eps):
    counter = 0
    while True:
        if counter % 2 == 0:
            # оптимизируем по x1 аналитически
            df1 = sp.diff(f_expr.subs(x2, x2_0), x1)
            x1_new = float(sp.solve(df1, x1)[0])
            if abs(f(x1_new, x2_0) - f(x1_0, x2_0)) < eps:
                return x1_new, x2_0, f(x1_new, x2_0)
            x1_0 = x1_new
            print(f"Iter {counter+1}: x1 = {x1_new}, x2 = {x2_0}, f = {f(x1_new, x2_0)}")
        else:
            # оптимизируем по x2
            df2 = sp.diff(f_expr.subs(x1, x1_0), x2)
            x2_new = float(sp.solve(df2, x2)[0])
            if abs(f(x1_0, x2_new) - f(x1_0, x2_0)) < eps:
                return x1_0, x2_new, f(x1_0, x2_new)
            x2_0 = x2_new
            print(f"Iter {counter+1}: x1 = {x1_0}, x2 = {x2_new}, f = {f(x1_0, x2_new)}")
        counter += 1

def coordinate_descent_with_newton(x1_0, x2_0, eps):
    counter = 0
    while True:
        if counter % 2 == 0:
            # df/dx1 и d2f/dx1 при x2=x2_0
            df1 = sp.diff(f_expr.subs(x2, x2_0), x1)
            d2f1 = sp.diff(df1, x1)
            df1_func = sp.lambdify(x1, df1, 'numpy')
            d2f1_func = sp.lambdify(x1, d2f1, 'numpy')
            x1_new = newton_method(df1_func, d2f1_func, x1_0, eps)
            if abs(f(x1_new, x2_0) - f(x1_0, x2_0)) < eps:
                return x1_new, x2_0, f(x1_new, x2_0)
            x1_0 = x1_new
            print(f"Iter {counter+1}: x1 = {x1_new}, x2 = {x2_0}, f = {f(x1_new, x2_0)}")
        else:
            # df/dx2 и d2f/dx2 при x1=x1_0
            df2 = sp.diff(f_expr.subs(x1, x1_0), x2)
            d2f2 = sp.diff(df2, x2)
            df2_func = sp.lambdify(x2, df2, 'numpy')
            d2f2_func = sp.lambdify(x2, d2f2, 'numpy')
            x2_new = newton_method(df2_func, d2f2_func, x2_0, eps)
            if abs(f(x1_0, x2_new) - f(x1_0, x2_0)) < eps:
                return x1_0, x2_new, f(x1_0, x2_new)
            x2_0 = x2_new
            print(f"Iter {counter+1}: x1 = {x1_0}, x2 = {x2_new}, f = {f(x1_0, x2_new)}")
        counter += 1
