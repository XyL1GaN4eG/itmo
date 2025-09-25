# newton.py
def newton_method(df, d2f, x0, epsilon, max_steps=100):
    steps = 0
    x = x0
    while steps < max_steps:
        df_val = df(x)
        if abs(df_val) < epsilon:
            break
        d2f_val = d2f(x)
        if d2f_val == 0:
            break
        x_next = x - df_val / d2f_val
        if abs(x_next - x) < epsilon:
            break
        print(f"Step {steps + 1}: x = {x:.5f}, f'(x) = {df_val:.5f}")
        x = x_next
        steps += 1
    return x