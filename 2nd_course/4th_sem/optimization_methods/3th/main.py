# main.py
from coordinate_descent import coordinate_descent, coordinate_descent_with_newton
from gradient_descent import gradient_descent
from steepest_descent import steepest_descent, steepest_descent_with_newton

def main():
    x1_0, x2_0 = 1, 1         # стартовая точка
    eps = 1e-4
    alpha = 0.1               # для простого градиентного спуска

    print("=== Gradient descent ===")
    res = gradient_descent(x1_0, x2_0, eps, alpha)
    print("Result:", res, "\n")

    print("=== Coordinate descent ===")
    res = coordinate_descent(x1_0, x2_0, eps)
    print("Result:", res, "\n")

    print("=== Coordinate descent with Newton ===")
    res = coordinate_descent_with_newton(x1_0, x2_0, eps)
    print("Result:", res, "\n")

    print("=== Steepest descent ===")
    res = steepest_descent(x1_0, x2_0, eps)
    print("Result:", res, "\n")

    print("=== Steepest descent with Newton ===")
    res = steepest_descent_with_newton(x1_0, x2_0, eps)
    print("Result:", res, "\n")

if __name__ == "__main__":
    main()
