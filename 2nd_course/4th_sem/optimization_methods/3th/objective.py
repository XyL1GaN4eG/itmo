# objective.py
import sympy as sp
import numpy as np

# символы
x1, x2 = sp.symbols('x1 x2')

# символическое выражение f
f_expr = 2*x1**2 + 4*x2**2 - 5*x1*x2 + 11*x1 + 8*x2 - 3

# численная функция f(x1, x2)
f = sp.lambdify((x1, x2), f_expr, 'numpy')
