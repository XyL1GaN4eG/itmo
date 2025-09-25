# gradient_descent.py
import sympy as sp
from objective import f_expr, f

x1, x2 = sp.symbols('x1 x2')

# символические градиенты
df_dx1 = sp.diff(f_expr, x1)
df_dx2 = sp.diff(f_expr, x2)
df_dx1_func = sp.lambdify((x1, x2), df_dx1, 'numpy')
df_dx2_func = sp.lambdify((x1, x2), df_dx2, 'numpy')

def gradient_descent(x1_0, x2_0, eps, alpha):
    counter = 0
    while True:
        g1 = df_dx1_func(x1_0, x2_0)
        g2 = df_dx2_func(x1_0, x2_0)
        x1_new = x1_0 - alpha * g1
        x2_new = x2_0 - alpha * g2
        print(f"Iter {counter+1}: x = ({x1_new:.5f}, {x2_new:.5f}), f = {f(x1_0, x2_0):.5f}")
        if abs(f(x1_0, x2_0) - f(x1_new, x2_new)) < eps:
            return x1_new, x2_new, f(x1_new, x2_new)
        x1_0, x2_0 = x1_new, x2_new
        counter += 1
