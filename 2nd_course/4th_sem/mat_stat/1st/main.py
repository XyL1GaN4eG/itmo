import numpy as np
import matplotlib.pyplot as plt


# Функция оценки методом моментов
def estimate_theta_moments(samples):
    second_moment = np.mean(samples ** 2)
    theta_estimate = np.sqrt(3 * second_moment)
    return theta_estimate


# Параметры эксперимента
theta_real = 10  # Истинное значение θ
n_values = [10, 50, 100, 500]  # Объемы выборок
num_simulations = 1000  # Количество симуляций для каждого n
threshold = 0.1 * theta_real  # Пороговое отклонение (10% от θ_real)

# Генерация данных и оценки
results = {}
for n in n_values:
    estimates = []
    for _ in range(num_simulations):
        # Генерация выборки из распределения U[-θ_real, θ_real]
        sample = np.random.uniform(-theta_real, theta_real, n)
        estimate = estimate_theta_moments(sample)
        estimates.append(estimate)
    results[n] = np.array(estimates)

# Анализ результатов
print("Анализ оценок:")
for n in n_values:
    est = results[n]
    bias = np.mean(est) - theta_real
    variance = np.var(est)
    mse = np.mean((est - theta_real) ** 2)
    over_threshold = np.sum(np.abs(est - theta_real) > threshold)

    print(f"\nОбъем выборки: {n}")
    print(f"Средняя оценка: {np.mean(est):.4f}")
    print(f"Смещение: {bias:.4f}")
    print(f"Дисперсия: {variance:.4f}")
    print(f"MSE: {mse:.4f}")
    print(f"Количество оценок за порогом ({threshold}): {over_threshold}/{num_simulations}")

# Визуализация
plt.figure(figsize=(14, 7))

# Гистограммы распределения оценок
plt.subplot(1, 2, 1)
for n in n_values:
    plt.hist(results[n], bins=30, density=True, alpha=0.6, label=f'n={n}')
plt.axvline(theta_real, color='red', linestyle='--', label='Истинное θ')
plt.title('Распределение оценок θ')
plt.xlabel('Оценка θ')
plt.ylabel('Плотность')
plt.legend()

# Boxplot для сравнения оценок по объемам выборок
plt.subplot(1, 2, 2)
box_data = [results[n] for n in n_values]
plt.boxplot(box_data, labels=n_values, patch_artist=True)
plt.axhline(theta_real, color='red', linestyle='--', label='Истинное θ')
plt.title('Box-plot оценок θ по объему выборки')
plt.xlabel('Объем выборки')
plt.ylabel('Оценка θ')
plt.legend()

plt.tight_layout()
plt.show()

# График MSE от объема выборки
plt.figure(figsize=(8, 5))
mse_values = [np.mean((results[n] - theta_real) ** 2) for n in n_values]
plt.plot(n_values, mse_values, marker='o', linestyle='--')
plt.title('Среднеквадратичная ошибка (MSE) от объема выборки')
plt.xlabel('Объем выборки')
plt.ylabel('MSE')
plt.grid(True)
plt.show()