import math
from bisection import bisection_method
from golden_section import golden_section_method
from secant import secant_method
from newton import newton_method
from quadratic_approximation import quadratic_approximation_method
from utils import f, df, d2f

a, b = 0, 1
epsilon = 0.01
initial_guess = 0.5
max_steps = 100
h=0.25

print("Bisection Method:")
result_bisection = bisection_method(df, a, b, epsilon, max_steps)
print(f"Approximate minimum at x = {result_bisection:.5f}\n")

print("Golden Section Method:")
result_golden = golden_section_method(f, a, b, epsilon, max_steps)
print(f"Approximate minimum at x = {result_golden:.5f}\n")

print("Secant Method:")
result_secant = secant_method(df, a, b, epsilon, max_steps)
print(f"Approximate root at x = {result_secant:.5f}\n")

print("Newton's Method:")
result_newton = newton_method(df, d2f, initial_guess, epsilon, max_steps)
print(f"Approximate root at x = {result_newton:.5f}\n")

# LAB3
print("\nQuadratic Approximation Method:")
result_quadratic = quadratic_approximation_method(f, initial_guess, h, epsilon, epsilon, max_steps)
print(f"Approximate minimum at x = {result_quadratic:.5f}")
