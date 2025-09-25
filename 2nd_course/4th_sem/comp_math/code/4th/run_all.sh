rm -rf out
mkdir out
touch out/output.md
echo "## Пример выполнения программы" > out/output.md

echo "\n### С входными данными из ручного вычисления:\n" >> out/output.md
echo "\`\`\`" >> out/output.md
python main.py -f data/input_from_handle_computing.txt -g out/1.png >> out/output.md
echo "\`\`\`" >> out/output.md
echo "![[1.png]]" >> out/output.md


echo "\n### Кубическая:\n" >> out/output.md
echo "\`\`\`" >> out/output.md
python main.py -f data/cubic.txt -g out/2.png >> out/output.md
echo "\`\`\`" >> out/output.md
echo "![[1.png]]" >> out/output.md

echo "\n### Экспоненциальная:\n" >> out/output.md
echo "\`\`\`" >> out/output.md
python main.py -f data/exponential.txt -g out/3.png >> out/output.md
echo "\`\`\`" >> out/output.md
echo "![[3.png]]" >> out/output.md

echo "\n### Линейная:\n" >> out/output.md
echo "\`\`\`" >> out/output.md
python main.py -f data/linear.txt -g out/4.png >> out/output.md
echo "\`\`\`" >> out/output.md
echo "![[4.png]]" >> out/output.md

echo "\n### Логарифмическая:\n" >> out/output.md
echo "\`\`\`" >> out/output.md
python main.py -f data/logarithmic.txt -g out/5.png >> out/output.md
echo "\`\`\`" >> out/output.md
echo "![[5.png]]" >> out/output.md

echo "\n### Степенная:\n" >> out/output.md
echo "\`\`\`" >> out/output.md
python main.py -f data/power.txt -g out/6.png >> out/output.md
echo "\`\`\`" >> out/output.md
echo "![[6.png]]" >> out/output.md
