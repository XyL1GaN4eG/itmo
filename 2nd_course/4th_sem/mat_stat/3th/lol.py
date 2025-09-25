import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from scipy import stats

# 1️⃣  Чтение данных
data = pd.read_csv("data.csv")

# Суммарный балл по трём экзаменам
random_variable = data["math score"] + data["reading score"] + data["writing score"]

# Параметры распределения
alpha = 0.05
average_sample = np.mean(random_variable)
sd_sample = np.std(random_variable, ddof=1)

# 2️⃣  Разбиение на интервалы по правилу Стерджесса
n = int(1 + np.log2(len(random_variable)))
intervals = np.linspace(random_variable.min(), random_variable.max(), n + 1)

# Частоты
cumul_distr_func = stats.norm.cdf(intervals, loc=average_sample, scale=sd_sample)
theoretical_frequencies = (cumul_distr_func[1:] - cumul_distr_func[:-1]) * len(random_variable)
real_frequencies, _ = np.histogram(random_variable, bins=intervals, density=False)

    
# 3️⃣  Реализация хи-квадрат вручную
chi_value = sum((real_frequencies - theoretical_frequencies) ** 2 / theoretical_frequencies)
df = len(intervals) - 1 - 2  # 2 — оцененные параметра (среднее и дисперсия)
p_value = 1 - stats.chi2.cdf(chi_value, df=df)

print("\n--- Метод, реализованный самостоятельно ---")
if p_value > alpha:
    print("✔️ Принимаем гипотезу H0 — распределение близко к нормальному")
else:
    print("❌ Отклоняем гипотезу H0 — распределение отличается от нормального")
print(f'Статистика Хи-квадрат: {chi_value:.4f}\nР-значение: {p_value:.4f}')
# 4️⃣  Готовая реализация критерия Хи-квадрат
# Нормализуем частоты
theoretical_frequencies *= real_frequencies.sum() / theoretical_frequencies.sum()

# Теперь можно проводить тест
statistic, p_value1 = stats.chisquare(f_obs=real_frequencies, f_exp=theoretical_frequencies, ddof=2)

print("\n--- Готовая реализация метода ---")
if p_value1 > alpha:
    print("✔️ Принимаем гипотезу H0 — распределение близко к нормальному")
else:
    print("❌ Отклоняем гипотезу H0 — распределение отличается от нормального")
print(f'Статистика Хи-квадрат: {statistic:.4f}\nР-значение: {p_value1:.4f}')

# 5️⃣  Визуализация частот
x_values = 0.5 * (intervals[1:] + intervals[:-1])

plt.figure(figsize=(10, 5))
plt.bar(x_values, real_frequencies, width=25.5, label="Наблюдаемые значения", color="skyblue", edgecolor='black')
plt.plot(x_values, theoretical_frequencies, label="Теоретические значения", color="orange", linestyle='--')
plt.title("График частот")
plt.xlabel("Суммарный балл")
plt.ylabel("Частота")
plt.legend()
plt.grid(True)
plt.show()

# Визуализация эмпирической функции распределения
x_values = np.linspace(random_variable.min(), random_variable.max(), len(random_variable))
compute = lambda x: np.sum(random_variable <= x) / len(random_variable)

real_values = [compute(x) for x in x_values]
theoretical_values = stats.norm.cdf(x_values, loc=average_sample, scale=sd_sample)

plt.figure(figsize=(10, 5))
plt.plot(x_values, real_values, label="Эмпирическая функция", color="skyblue")
plt.plot(x_values, theoretical_values, label="Теоретическая функция", color="orange", linestyle='--')
plt.title("Сравнение эмпирической и теоретической функций распределения")
plt.xlabel("Суммарный балл")
plt.ylabel("Вероятность")
plt.legend()
plt.grid(True)
plt.show()
# Критерий Колмогорова-Смирнова
stat_ks, p_ks = stats.kstest(random_variable, 'norm', args=(average_sample, sd_sample))
print("\n--- Критерий Колмогорова-Смирнова ---")
if p_ks > alpha:
    print("✔️ Принимаем H₀")
else:
    print("❌ Отклоняем H₀")