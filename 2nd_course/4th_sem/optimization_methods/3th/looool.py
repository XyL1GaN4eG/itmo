h = 0.2
a = 0
n = 10
def f(x):
	return 4 * x ** 3 - 5 * x ** 2 + 6 * x - 7
summ = 0
for i in range(n):
	summ += f(a + i * h + h / 2)
print(summ)