# secant.py
def secant_method(df, x0, x1, epsilon, max_steps=100):
    steps = 0
    while steps < max_steps:
        df_x0 = df(x0)
        df_x1 = df(x1)
        if abs(df_x1) < epsilon:
            break
        if df_x1 - df_x0 == 0:
            break
        x_next = x1 - df_x1 * (x1 - x0) / (df_x1 - df_x0)
        if abs(x_next - x1) < epsilon:
            x1 = x_next
            break
        x0, x1 = x1, x_next
        steps += 1
        print(f"Step {steps}: x = {x1:.5f}, f'(x) = {df(x1):.5f}")
    return x1