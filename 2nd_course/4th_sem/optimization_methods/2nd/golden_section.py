import math


def golden_section_method(f, a, b, epsilon, max_steps=5):
    r = (math.sqrt(5) - 1) / 2  # ≈0.618
    steps = 0
    while (b - a) >= epsilon and steps < max_steps:
        x1 = a + (b - a) * (1 - r)
        x2 = a + (b - a) * r
        if f(x1) < f(x2):
            b = x2
        else:
            a = x1
        steps += 1
        print(f"Step {steps}: a={a:.5f}, b={b:.5f}, interval length={b - a:.5f}")
    return (a + b) / 2
