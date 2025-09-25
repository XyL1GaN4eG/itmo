import numpy as np
import pandas as pd
from scipy.stats import t, f

df = pd.read_csv('data.csv')
display(df.head())

X_raw = df[['MPG.city', 'MPG.highway', 'Horsepower']].values  # независимые
y        = df['Price'].values.reshape(-1, 1)                 # зависимая

n = X_raw.shape[0]
X = np.hstack([np.ones((n, 1)), X_raw])   # [1, MPG.city, MPG.highway, Horsepower]

XtX    = X.T @ X
XtX_inv= np.linalg.inv(XtX)
Xty    = X.T @ y
beta   = XtX_inv @ Xty             # вектор (4×1)

y_pred = X @ beta
residuals = y - y_pred

rss = (residuals**2).sum()
sigma2 = rss / (n - 3 - 1)

se = np.sqrt(np.diag(sigma2 * XtX_inv))   # массив из 4 SE
t_crit = t.ppf(1 - 0.05/2, df=n-3-1)

for j, name in enumerate(['Intercept','MPG.city','MPG.highway','Horsepower']):
    lo = beta[j,0] - t_crit * se[j]
    hi = beta[j,0] + t_crit * se[j]
    print(f"{name}: [{lo:.3f}, {hi:.3f}]")

tss = ((y - y.mean())**2).sum()
r2  = 1 - rss / tss
print("R² =", r2)

# Ограниченная модель
X0 = X[:, [0, 3]]   # только Intercept и Horsepower
beta0 = np.linalg.inv(X0.T@X0) @ (X0.T@y)
rss0  = ((y - X0@beta0)**2).sum()

q = 2
F_stat = ((rss0 - rss)/q) / (rss/(n-3-1))
p_valF = 1 - f.cdf(F_stat, q, n-3-1)
print("F =", F_stat, "p =", p_valF)