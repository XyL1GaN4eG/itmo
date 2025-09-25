#!/bin/bash

# Директория, в которой нужно выполнять поиск и замену
DIR="."

# Проверка, передана ли директория как аргумент
if [ $# -eq 1 ]; then
  DIR="$1"
fi

# Проверка существования директории
if [ ! -d "$DIR" ]; then
  echo "Ошибка: директория $DIR не существует."
  exit 1
fi

# Функция для поиска и замены текста в файлах .java
replace_text() {
  local file="$1"
  if grep -q "General.dataClasses" "$file"; then
    sed -i 's/General\.dataClasses/Server.General.dataClasses/g' "$file"
    echo "Заменено в файле: $file"
  fi
}

# Поиск всех .java файлов в директории и поддиректориях
find "$DIR" -type f -name "*.java" -print0 | while IFS= read -r -d '' file; do
  replace_text "$file"
done

echo "Поиск и замена завершены."
