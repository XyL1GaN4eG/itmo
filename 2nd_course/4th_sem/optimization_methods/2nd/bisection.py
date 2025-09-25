# bisection.py
def bisection_method(df, a, b, epsilon, max_steps=100):
    if df(a) * df(b) >= 0:
        raise ValueError("Функция не меняет знак на интервале [a, b].")

    steps = 0
    while (b - a) >= epsilon and steps < max_steps:
        c = (a + b) / 2
        if df(c) == 0:
            break
        elif df(c) * df(a) < 0:
            b = c
        else:
            a = c
        steps += 1
        print(f"Step {steps}: a={a:.5f}, b={b:.5f}, interval length={b - a:.5f}")
    return (a + b) / 2