# data_loader.py
import sys

def load_from_console():
    """
    Считывает с консоли сначала число точек n (должно быть от 8 до 12),
    затем в n строках пары x y (через пробел).
    Возвращает два списка: xs, ys.
    При некорректном вводе – печатает сообщение об ошибке и завершает программу.
    """
    print("Введите число точек (от 8 до 12):")
    try:
        n = int(sys.stdin.readline().strip())
    except Exception:
        print("Ошибка: некорректное число точек.")
        sys.exit(1)

    if not (8 <= n <= 12):
        print(f"Ошибка: ожидается число от 8 до 12, а получено {n}.")
        sys.exit(1)

    xs = []
    ys = []
    print(f"Введите {n} строк, каждая строка: x y (через пробел).")
    for i in range(n):
        line = sys.stdin.readline().strip()
        parts = line.split()
        if len(parts) != 2:
            print(f"Ошибка: в строке {i+1} не две величины.")
            sys.exit(1)
        try:
            x = float(parts[0])
            y = float(parts[1])
        except ValueError:
            print(f"Ошибка: некорректные числа в строке {i+1}.")
            sys.exit(1)
        xs.append(x)
        ys.append(y)
    return xs, ys

def load_from_file(filename):
    """
    Считает данные из текстового файла. Ожидается, что в файле:
    - в первой строке либо (не обязано) число точек n (если есть, то 8<=n<=12),
      либо сразу пара x y.
    - каждая следующая строка – пара x y, разделённые пробелом или табуляцией.
    Возвращает два списка: xs, ys.
    При ошибках – печатает сообщение и sys.exit(1).
    """
    xs = []
    ys = []
    try:
        with open(filename, 'r', encoding='utf-8') as f:
            lines = [ln.strip() for ln in f if ln.strip()]
    except FileNotFoundError:
        print(f"Ошибка: файл '{filename}' не найден.")
        sys.exit(1)

    if not lines:
        print("Ошибка: файл пуст.")
        sys.exit(1)

    # Проверим, является ли первая строка числом (количество точек)
    first = lines[0].split()
    start_idx = 0
    if len(first) == 1:
        try:
            n = int(first[0])
            if not (8 <= n <= 12):
                print(f"Ошибка: в файле указано число точек {n}, но должно быть от 8 до 12.")
                sys.exit(1)
            start_idx = 1
            if len(lines) - 1 != n:
                print(f"Ошибка: заявлено {n} точек, а в файле найдено {len(lines)-1}.")
                sys.exit(1)
        except ValueError:
            # первая строка не число – интерпретируем как пара x y
            pass

    # Читаем пары x y
    for idx, line in enumerate(lines[start_idx:], start=1):
        parts = line.split()
        if len(parts) != 2:
            print(f"Ошибка: в строке {start_idx + idx} не две величины.")
            sys.exit(1)
        try:
            x = float(parts[0])
            y = float(parts[1])
        except ValueError:
            print(f"Ошибка: некорректные числа в строке {start_idx + idx}.")
            sys.exit(1)
        xs.append(x)
        ys.append(y)

    if not (8 <= len(xs) <= 12):
        print(f"Ошибка: в файле {len(xs)} точек, а должно быть от 8 до 12.")
        sys.exit(1)

    return xs, ys
