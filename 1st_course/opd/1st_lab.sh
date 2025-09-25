sudo rm -rf lab0

#Первый этап

mkdir lab0
cd lab0

mkdir arcanine4
cd arcanine4

echo Живет Forest Grassland Urban > pidove
echo -e "Ходы Covet Helping\nHand Hyper Voice Last Resort Sleep Talk Snore\nUproar" > herdier
echo -e "Возможности Overland=7 Surface=7 Jump=3 Power=2\nIntelligence=3" > tyrogue

cd ..

echo -e "Способности  Focus Unbreakable Inner Focus\nSteadfast" > lucario0
echo -e "Живет Forest Grassland" > meganium8
echo -e "Тип покемона \n PSYCHIC NONE" > wobbuffet6

mkdir xatu1
cd xatu1

mkdir mantine
mkdir liepard
mkdir dewott

cd ..
mkdir zubat4
cd zubat4

echo -e "weight=793.7 height=83.0 atk=11 \n def 18" > aggron
echo -e "Ходы Ancientpower Bind Body Slam Bullet Seed\nDouble-Edge Earth Power Gastro Acid Giga Drain Mud-Slap Pain Split" > lileep
echo Тип покемона 	ROCK NONE > rampardos

cd ..
# -------------------------------------------------------------------------------------
#Второй этап, раздача прав
chmod a+rxw arcanine4

cd arcanine4 #переход в арканину и раздача прав там

chmod u-rxw pidove
chmod g+rw pidove
chmod g-x pidove
chmod u-rxw,g+r,g-wx,o+rw,o-x herdier
chmod u+rw,u-x,g-rwx,o+r,o-wx tyrogue #раздача прав в арканине

cd .. #переход в основную папку

chmod u+r,u-wx,g-rwx,o+r,o-wx lucario0
chmod u+r,u-wx,g-rwx,o-rwx meganium8
chmod 044 wobbuffet6

chmod 733 xatu1 

cd xatu1 #переход в хатул

chmod u+rx,u-w,g+w,g-rx,o+r,o-wx mantine
chmod 307 liepard
chmod 750 dewott

cd .. #обратно в основную директорию

chmod u+wx,u-r,g+rwx,o+wx,o-r zubat4

cd zubat4 #и опять в зубата

chmod 444 aggron
chmod 006 lileep
chmod 666 rampardos

cd .. 


# -------------------------------------------------------------------------------------
#Третий этап


# ls -lR

# chmod u+rw lucario0
cat lucario0 > arcanine4/herdierlucario #копипаст содержимого файла
ln lucario0 arcanine4/herdierlucario #создание жесткой ссылки
ln -s "$(pwd)"/lucario0 "$(pwd)"/zubat4/aggronlucario #создание символической ссылки

chmod u+rwx xatu1
chmod u+rwx xatu1/liepard

cp -R xatu1 xatu1/dewott #скопировать сам файл
cat zubat4/rampardos arcanine4/tyrogue > wobbuffet6_25 #объединение содержимого файлов в новый файл
cp lucario0 xatu1/dewott
ln -s "$(pwd)"/xatu1 "$(pwd)"/Copy_54 

chmod 733 xatu1 
cd xatu1 #переход в хатул
chmod 307 liepard

# -------------------------------------------------------------------------------------
#Четвертый этап

# ______________________________________________________________
# 1)

wc -m arcanine4/pidove arcanine4/herdier arcanine4/tyrogue zubat4/aggron > /tmp/result.txt | 2>&1


# ______________________________________________________________
# 2)
cd ..

ls -lR lab0 | grep -E ".*4$" | sort -k 8 | tail -4 | 2>&1 

# ______________________________________________________________
# 3)

(cat -n lab0/arcanine4/pidove lab0/arcanine4/herdier | grep -v "t$" -i ) 2>/dev/null
# -n добавляет в начало каждой строки ее номер
													# фильтрует конкатенацию на предмет отсутсвия строк, заканчивающихся на t
													# -v - инвертирование совпадений
													# -i - независимо от регистра
													# "t$" - доллар означает что символ перед ним - последний
																		# подавление ошибок

# ______________________________________________________________
# 4)
cd lab0

grep -nH '.*' lab0/*e | sort
	# -n - добавляет номера строк
	# -H - выводит имя файла при совпадении
		#'.*' - ищет все строки
			  # во всех директориях, которые заканчиваются на e			  № dj dct[ lbhtrnjhbz[^ rjnjhst pfrfyxbdf.ncz yf у


# ______________________________________________________________
# 5)

wc -c arcanine4/* | sort

# ______________________________________________________________
# 6)

ls -lR lab0 | grep "^m" | sort -k 8 | head -4


# -------------------------------------------------------------------------------------
# #Пятый этап

# cd ..

# первый пункт
chmod u+rwx meganium8
rm -f meganium8

# второй пункт
rm -f arcanine4/herdier

cd .. # в надпапке lab0

# третий пункт
ls
chmod -R u+rwx lab0
cd lab0
# ls | grep "^Copy_" | rm
rm "$(pwd)"/Copy_*

# четвертый пункт

# ls -lR lab0 | grep "^herdierlucar" | sort
cd arcanine4
rm "$(pwd)"/herdierlucar*

cd ..

# пятый пункт
rm -rf zubat4 
# шестой пункт
rmdir xatu1/mantine


# шпоргалочка
