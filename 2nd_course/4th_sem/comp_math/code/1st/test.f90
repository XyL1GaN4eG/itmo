program simple_iterations
    implicit none
    integer :: n, i, j, iter, max_iter
    integer :: answer
    real(8), allocatable :: A(:, :), A_old(:, :), b(:), x(:), x_new(:)
    real(8) :: epsilon, norm, sum
    logical :: diag_dominant
    integer :: input_choice
    character(len = 100) :: filename
    real(8) :: norm_b, row_sum_b

    ! Выбор источника данных
    print *, 'Выберите источник данных:'
    print *, '1 - Из файла'
    print *, '2 - С клавиатуры'
    read(*, *) input_choice

    if(input_choice == 1) then
        print *, 'Введите имя файла:'
        read(*, *) filename

        ! Открытие файла с обработкой ошибок
        open(unit = 10, file = filename, status = 'old', action = 'read', iostat = answer)
        if(answer /= 0) then
            print *, 'Ошибка открытия файла:', filename
            stop
        end if

        ! Чтение данных из файла
        read(10, *) n
        if(n > 20) then
            print *, 'Ошибка: n должно быть <= 20'
            close(10)
            stop
        end if

        ! Выделение памяти для массивов
        allocate(A(n, n), b(n), x(n), x_new(n))

        ! Чтение матрицы A
        do i = 1, n
            read(10, *) (A(i, j), j = 1, n)
        end do

        ! Чтение вектора b
        read(10, *) (b(i), i = 1, n)

        ! Чтение точности epsilon [[3]]
        read(10, *) epsilon
        close(10)
        print *, 'Epsilon:', epsilon
    else if(input_choice == 2) then
        ! Ввод размера системы
        print *, 'Enter the size of the system (n <= 20):'
        read(*, *) n
        if (n > 20) then
            print *, 'Error: n must be <= 20'
            stop
        end if

        ! Выделение памяти для матрицы A и векторов b, x, x_new
        allocate(A(n, n), b(n), x(n), x_new(n))

        ! Ввод коэффициентов матрицы A
        print *, 'Enter the coefficients of matrix A (row by row):'
        do i = 1, n
            read(*, *) (A(i, j), j = 1, n)
        end do

        ! Ввод вектора правой части b
        print *, 'Enter the right-hand side vector b:'
        read(*, *) (b(i), i = 1, n)

        ! Ввод точности epsilon
        print *, 'Enter the epsilon:'
        read(*, *) epsilon

    else
        print *, "Неверный выбор"
        stop
    end if
    A_old = A
    ! Перестановка строк и столбцов для достижения диагонального преобладания
    call rearrange_matrix(A, b, n, diag_dominant)

    ! Проверка, удалось ли сделать матрицу диагонально доминирующей
    if (.not. diag_dominant) then
        print *, 'Error: The matrix cannot be made diagonally dominant.'
        !        stop
    end if


    ! Вычисление нормы матрицы B [[2]]
    norm_b = 0.0
    do i = 1, n
        row_sum_b = 0.0
        do j = 1, n
            if (j /= i) then
                row_sum_b = row_sum_b + abs(A(i, j) / A(i, i))
            end if
        end do
        norm_b = max(norm_b, row_sum_b)
    end do

    print *, 'Норма матрицы B:', norm_b
    if(norm_b >= 1.0) then
        print *, 'Предупреждение: норма матрицы B >= 1 - сходимость не гарантирована!'

        print *, A
    end if

    ! Начальное приближение (все x_i = 0)
    x = 0.0

    ! Итерационный процесс
    max_iter = 1000  ! Максимальное количество итераций
    iter = 0  ! Счетчик итераций
    !    epsilon = 0.001
    do
        x_new = x
        norm = 0.0

        ! Вычисление нового приближения
        do i = 1, n
            sum = b(i)
            do j = 1, n
                if (j /= i) then
                    sum = sum - A(i, j) * x(j)
                end if
            end do
            x_new(i) = sum / A(i, i)
            norm = max(norm, abs(x_new(i) - x(i)))
        end do

        x = x_new
        iter = iter + 1

        ! Вывод нормы разницы на текущей итерации
        print '(A, I4, A, E12.5)', 'Итерация ', iter, ': вектор погрешности = ', norm

        if (norm < epsilon .or. iter >= max_iter) exit
    end do



    ! Вывод результатов
    print *, 'Solution found after ', iter, ' iterations:'
    do i = 1, n
        print *, 'x(', i, ') = ', x(i)
    end do

    ! Проверка решения
    print *, 'Checking solution accuracy...'
    do i = 1, n
        sum = 0.0
        do j = 1, n
            sum = sum + A_old(i, j) * x(j)

        end do
        ! r_i=∣(A⋅x)_i−b_i∣
        print *, 'Equation ', i, ':', sum, ' =? ', b(i), ' Error: ', sum - b(i)
    end do

    ! Освобождение памяти
    deallocate(A, b, x, x_new)

contains

    ! Подпрограмма для перестановки строк и столбцов для достижения диагонального преобладания
    subroutine rearrange_matrix(A, b, n, diag_dominant)
        real(8), intent(inout) :: A(n, n), b(n)
        integer, intent(in) :: n
        logical, intent(out) :: diag_dominant
        integer :: i, j, max_row, max_col
        real(8) :: max_val, diag, row_sum, temp

        diag_dominant = .true.

        do i = 1, n
            ! Перестановка строк
            ! Находим строку с максимальным элементом в текущем столбце (i)
            max_row = i
            max_val = abs(A(i, i))
            do j = i, n
                if (abs(A(j, i)) > max_val) then
                    max_val = abs(A(j, i))
                    max_row = j
                end if
            end do
            ! Если максимальный элемент не на диагонали, меняем строки местами
            if (max_row /= i) then
                call swap_rows(A, b, i, max_row, n)
            end if

            ! Проверка диагонального преобладания
            ! Вычисляем сумму модулей элементов строки, исключая диагональный элемент
            diag = abs(A(i, i))
            row_sum = 0.0
            do j = 1, n
                if (j /= i) then
                    row_sum = row_sum + abs(A(i, j))
                end if
            end do
            ! Если диагональный элемент больше суммы остальных элементов строки, переходим к следующей строке
            if (diag > row_sum) cycle

            ! Перестановка столбцов
            ! Если диагональное преобладание не достигнуто, ищем столбец с максимальным элементом в текущей строке
            max_col = i
            max_val = abs(A(i, i))
            do j = i, n
                if (abs(A(i, j)) > max_val) then
                    max_val = abs(A(i, j))
                    max_col = j
                end if
            end do
            ! Если максимальный элемент не на диагонали, меняем столбцы местами
            if (max_col /= i) then
                call swap_columns(A, i, max_col, n)
            end if

            ! Финальная проверка диагонального преобладания
            ! После перестановки столбцов снова проверяем диагональное преобладание
            diag = abs(A(i, i))
            row_sum = 0.0
            do j = 1, n
                if (j /= i) then
                    row_sum = row_sum + abs(A(i, j))
                end if
            end do
            ! Если диагональное преобладание не достигнуто, устанавливаем флаг diag_dominant в .false. и выходим
            if (diag <= row_sum) then
                diag_dominant = .false.
                return
            end if
        end do
    end subroutine rearrange_matrix

    ! Подпрограмма для перестановки строк в матрице A и векторе b
    subroutine swap_rows(A, b, i, j, n)
        real(8), intent(inout) :: A(n, n), b(n)
        integer, intent(in) :: i, j, n
        real(8) :: temp
        integer :: k

        ! Меняем местами строки i и j в матрице A и векторе b
        do k = 1, n
            temp = A(i, k)
            A(i, k) = A(j, k)
            A(j, k) = temp
        end do
        temp = b(i)
        b(i) = b(j)
        b(j) = temp
    end subroutine swap_rows

    ! Подпрограмма для перестановки столбцов в матрице A
    subroutine swap_columns(A, i, j, n)
        real(8), intent(inout) :: A(n, n)
        integer, intent(in) :: i, j, n
        real(8) :: temp
        integer :: k

        ! Меняем местами столбцы i и j в матрице A
        do k = 1, n
            temp = A(k, i)
            A(k, i) = A(k, j)
            A(k, j) = temp
        end do
    end subroutine swap_columns

end program simple_iterations