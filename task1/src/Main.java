/*	
Дополнительное задание для практики:
Требуется написать метод, принимающий на вход размеры двумерного массива и выводящий массив в виде инкременированной цепочки чисел, идущих по спирали против часовой стрелки.
Примеры:
2х3
1 6
2 5
3 4

3х1
1 2 3
4х4
01 12 11 10
02 13 16 09
03 14 15 08
04 05 06 07

0х7
Ошибка!
*/


import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[][] arr = new String[][] { {"A", "B", "C", "D"}, {"E", "F", "G", "H"},
                {"I", "J", "K", "L"}, {"M", "N", "O", "P"} };

        printArrayCounterClock(arr);
    }

    //    Собственно метод из-за которой весь сыр-бор =)
    public static void printArrayCounterClock(final String[][] arr) {

//        Если массив не пройдет проверку выходим из метода
        if(!checkArray(arr)) {
            System.out.println("Некорректный массив");
            return;
        }

//        Получаем размеры двумерного массива
        final int size1 = arr.length;
        final int size2 = arr[0].length;

//        Объявляем массив в который будет скопирован наш массив в результирующем порядке
        String[][] arrC = new String[size1][size2];

//        Переменные нужны для обозначения индексов нового массива
        int row = 0;
        int col = 0;

//        Переменные флаги указывающие в каком "направлении" будут добавляться элементы в новый массив
//        Если движение вниз или вверх (down, up), то "двигаемся" по первой размерности, как бы по строке
//        Иначе (left, right) по столбцам
//        Не смог придумать ничего лучше )
        boolean down = false, right = false, up = false, left = false;


//        Цикл по первой размерности массива заданного массива
        for(int i = 0; i < size1; i++) {
//            Цикл по второй размерности заданного массива
            for (int j = 0; j < size2; j++) {
//                Если это первый элемент заданного массива, просто добавляем в первый элемент нового массива
                if(i == 0 && j == 0) {
                    arrC[0][0] = arr[0][0];
//                    В следующей итерации будем двигаться вниз
                    down = true;
//                если должны двигаться вниз
                } else if(down) {
//                    Если следующий элемент не превышает размер и ему еще не задано значение
                    if((row + 1 < size1) && (arrC[row + 1][col] == null)) {
//                        Копируем
                        arrC[++row][col] = arr[i][j];
//                        Если выходим за рамки либо значение уже задано, задаем следующее направление
                        if((row + 1 >= size1) || (arrC[row + 1][col] != null)) {
                            down = false;
                            right = true;
                        }
                    }
//                Следующие условия работают точно также, потому без комментов
                } else if (right) {
                    if((col + 1 < size2) && (arrC[row][col + 1] == null)) {
                        arrC[row][++col] = arr[i][j];
                        if((col + 1 >= size2) || (arrC[row][col + 1] != null)) {
                            right = false;
                            up = true;
                        }
                    }
                } else if (up) {
                    if((row - 1 >= 0) && (arrC[row - 1][col] == null)) {
                        arrC[--row][col] = arr[i][j];
                        if((row - 1) < 0 || (arrC[row - 1][col] != null)) {
                            up = false;
                            left = true;
                        }
                    }
                } else if (left) {
                    if((col - 1) >= 0 && (arrC[row][col - 1] == null)) {
                        arrC[row][--col] = arr[i][j];
                        if((col - 1) < 0 || (arrC[row][col - 1] != null)) {
                            left = false;
                            down = true;
                        }
                    }
                }
            }
        }

//        Выводим на экран результирующий массив
        for(String[] a : arrC) {
            System.out.println(Arrays.toString(a));
        }
    }

    //    Проверка массива
    private static boolean checkArray(String[][] arr) {

//        Если null либо неверный размер, возвращаем false
        if(arr == null || arr.length == 0)
            return false;

        /* Как я понял по условию, вторые размерности
        у всех элементов должны быть равны */
        int size2 = arr[0].length;
        for(String[] strings : arr)
            if(strings.length != size2)
                return false;

        return true;
    }
}
