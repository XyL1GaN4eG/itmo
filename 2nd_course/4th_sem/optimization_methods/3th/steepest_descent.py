# steepest_descent.py
import sympy as sp
from objective import f_expr, f
from newton import newton_method

x1, x2, h = sp.symbols('x1 x2 h')

# символические производные
df_dx1 = sp.diff(f_expr, x1)
df_dx2 = sp.diff(f_expr, x2)

def steepest_descent(x1_0, x2_0, eps):
    counter = 0
    while True:
        g1 = float(df_dx1.subs({x1: x1_0, x2: x2_0}))
        g2 = float(df_dx2.subs({x1: x1_0, x2: x2_0}))

        # находи оптимальный шаг h: решение d(phi)/dh = 0
        phi = f_expr.subs({x1: x1_0 - h*g1, x2: x2_0 - h*g2})
        dphi = sp.diff(phi, h)
        h_val = float(sp.solve(dphi, h)[0])

        x1_new = x1_0 - h_val * g1
        x2_new = x2_0 - h_val * g2
        print(f"Iter {counter+1}: x = ({x1_new:.5f}, {x2_new:.5f}), h = {h_val:.5f}, f = {f(x1_0, x2_0):.5f}")

        if abs(f(x1_0, x2_0) - f(x1_new, x2_new)) < eps:
            return x1_new, x2_new, f(x1_new, x2_new)

        x1_0, x2_0 = x1_new, x2_new
        counter += 1

def steepest_descent_with_newton(x1_0, x2_0, eps):
    counter = 0
    while True:
        g1 = float(df_dx1.subs({x1: x1_0, x2: x2_0}))
        g2 = float(df_dx2.subs({x1: x1_0, x2: x2_0}))

        # строим phi(h) и её производные
        phi = f_expr.subs({x1: x1_0 - h*g1, x2: x2_0 - h*g2})
        dphi = sp.diff(phi, h)
        d2phi = sp.diff(dphi, h)
        dphi_func = sp.lambdify(h, dphi, 'numpy')
        d2phi_func = sp.lambdify(h, d2phi, 'numpy')

        # одно­мерная оптимизация h через ваш newton_method
        h0 = 0.0
        h_val = newton_method(dphi_func, d2phi_func, h0, eps)

        x1_new = x1_0 - h_val * g1
        x2_new = x2_0 - h_val * g2
        print(f"Iter {counter+1}: x = ({x1_new:.5f}, {x2_new:.5f}), h = {h_val:.5f}, f = {f(x1_0, x2_0):.5f}")

        if abs(f(x1_0, x2_0) - f(x1_new, x2_new)) < eps:
            return x1_new, x2_new, f(x1_new, x2_new)

        x1_0, x2_0 = x1_new, x2_new
        counter += 1
