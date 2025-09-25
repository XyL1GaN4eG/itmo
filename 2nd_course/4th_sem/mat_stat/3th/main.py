import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from scipy.stats import chisquare, norm, kstest

# Чтение данных
data = pd.read_csv('data.csv')

# Суммарный балл по трём экзаменам
data['total_score'] = data[['math score', 'reading score', 'writing score']].sum(axis=1)

# Гистограмма суммарных баллов
plt.figure(figsize=(10, 6))
plt.hist(data['total_score'], bins=15, density=True, alpha=0.6, color='skyblue', edgecolor='black')

# Параметры нормального распределения (мат. ожидание и стандартное отклонение)
mu, sigma = np.mean(data['total_score']), np.std(data['total_score'])

# График нормального распределения
x = np.linspace(min(data['total_score']), max(data['total_score']), 1000)
plt.plot(x, norm.pdf(x, mu, sigma), 'r-', lw=2, label='Normal Distribution Fit')
plt.title('Гистограмма суммарных баллов с наложением нормального распределения')
plt.xlabel('Суммарный балл')
plt.ylabel('Плотность вероятности')
plt.legend()
plt.grid(True)
plt.show()

# Проведение критерия согласия хи-квадрат
observed_freq, bins = np.histogram(data['total_score'], bins=15)
expected_freq = len(data) * (norm.cdf(bins[1:], mu, sigma) - norm.cdf(bins[:-1], mu, sigma))
# Нормализуем, чтобы сумма совпадала с количеством выборок
expected_freq *= observed_freq.sum() / expected_freq.sum()

chi2_stat, p_value_chi2 = chisquare(f_obs=observed_freq, f_exp=expected_freq)

# Проведение теста Колмогорова-Смирнова
ks_stat, p_value_ks = kstest(data['total_score'], 'norm', args=(mu, sigma))

# Результаты тестов
print(chi2_stat, p_value_chi2, ks_stat, p_value_ks)
