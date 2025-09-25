def f(x1, x2):
    return 2 * x1 ** 2 + 4 * x2 ** 2 - 5 * x1 * x2 + 11 * x1 + 4 * x1 - 8 * x2 + 1

def gradient(x1, x2):
    dx1 = 4 * x1 - 5 * x2 + 4
    dx2 = -5 * x1 + 8 * x2 - 8
    return (dx1, dx2)

# Метод покоординатного спуска
print("Метод покоординатного спуска:")
x1, x2 = 1.0, 1.0
for i in range(3):
    new_x1 = (5 * x2 - 4) / 4
    new_x2 = (5 * new_x1 + 8) / 8
    print(f"Итерация {i+1}: x1 = {new_x1:.6f}, x2 = {new_x2:.6f}, f(x) = {f(new_x1, new_x2):.6f}")
    x1, x2 = new_x1, new_x2

# Градиентный спуск
print("\nМетод градиентного спуска:")
x1, x2 = 1.0, 1.0
alpha = 0.1
for i in range(3):
    grad = gradient(x1, x2)
    new_x1 = x1 - alpha * grad[0]
    new_x2 = x2 - alpha * grad[1]
    print(f"Итерация {i+1}: x1 = {new_x1:.6f}, x2 = {new_x2:.6f}, f(x) = {f(new_x1, new_x2):.6f}")
    x1, x2 = new_x1, new_x2

# Наискорейший спуск
def compute_alpha(x1, x2, dx1, dx2):
    a = 2 * dx1**2 + 4 * dx2**2 - 5 * dx1 * dx2
    b = (4 * x1 * dx1 + 8 * x2 * dx2 - 5 * x1 * dx2 - 5 * x2 * dx1 + 4 * dx1 - 8 * dx2)
    return -b / (2 * a) if a != 0 else 0

print("\nМетод наискорейшего спуска:")
x1, x2 = 1.0, 1.0
for i in range(3):
    grad = gradient(x1, x2)
    dx1, dx2 = -grad[0], -grad[1]
    alpha = compute_alpha(x1, x2, dx1, dx2)
    new_x1 = x1 + alpha * dx1
    new_x2 = x2 + alpha * dx2
    print(f"Итерация {i+1}: x1 = {new_x1:.6f}, x2 = {new_x2:.6f}, f(x) = {f(new_x1, new_x2):.6f}")
    x1, x2 = new_x1, new_x2