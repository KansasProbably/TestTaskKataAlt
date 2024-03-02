import java.util.Scanner;
//Калькулятор умеет выполнять операции сложения строк, вычитания строки из строки, умножения строки на число и деления строки на число: "a" + "b", "a" - "b", "a" * b, "a" / b. Данные передаются в одну строку (смотрите пример)! Решения, в которых каждая строка, число и арифметическая операция передаются с новой строки считаются неверными.
//Значения строк передаваемых в выражении выделяются двойными кавычками.
//Результатом сложения двух строк, является строка состоящая из переданных.
//Результатом деления строки на число n, является строка в n раз короче исходной (смотрите пример).
//Результатом умножения строки на число n, является строка, в которой переданная строка повторяется ровно n раз.
//Результатом вычитания строки из строки, является строка, в которой удалена переданная подстрока или сама исходная строка, если в нее нет вхождения вычитаемой строки (смотрите пример).
//Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. И строки длинной не более 10 символов. Если строка, полученная в результате работы приложения длиннее 40 символов, то в выводе после 40 символа должны стоять три точки (...)
//Калькулятор умеет работать только с целыми числами.
//Первым аргументом выражения, подаваемого на вход, должна быть строка, при вводе пользователем выражения вроде 3 + "hello", калькулятор должен выбросить исключение и прекратить свою работу.
//При вводе пользователем неподходящих чисел, строк или неподдерживаемых операций (например, деление строки на строку) приложение выбрасывает исключение и завершает свою работу.
//При вводе пользователем выражения, не соответствующего одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.






public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите данные: ");
        String input = scanner.nextLine();
        calc(input);
        printInQuotes(calc(input));

    }

    static String calc(String input) {
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", " - ", "/", "\\*"};
        // проверка знака операции
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        if (actionIndex == -1) {
            throw new RuntimeException("Неверный знак  операции");
        }

        String[] data = input.split(regexActions[actionIndex]);
        if (data.length > 2) {
            throw new RuntimeException("Допустимы операции только с двумя строками/числами");
        }


        String a, b;
        a = data[0];
        if(a.startsWith("\"")) {
            b = data[1];
        }else {
            throw new RuntimeException("Первое значение - строка!");
        }
        a=a.replace(" ","");
        b=b.replace(" ","");
        if (a.length() > 12 || b.length() > 12) {
            throw new RuntimeException("Максимальный размер строки - 10 символов");
        }


        String result = null;
        switch (actions[actionIndex]) {
            case "+":
                a = a.replace("\"", "");
                b = b.replace("\"", "");
                result = a + b;
                break;
            case "-":
                a = a.replace("\"", "");
                b = b.replace("\"", "");
                result = a.replace(b, "");
                break;
            case "*":
                if (b.contains("\"")) {
                    throw new RuntimeException("умножить можно только на число");
                }
                int c = Integer.parseInt(b);
                if (c > 10) {
                    throw new RuntimeException("числа должны быть от 1 до 10");
                }
                a = a.replace("\"", "");
                result = a.repeat(c);
                break;
            case "/":
                a = a.replace("\"", "");
                if (b.contains("\"")) {
                    throw new RuntimeException("делить можно только на число");
                }
                int e = Integer.parseInt(b);
                if (e > 10) {
                    throw new RuntimeException("числа должны быть от 1 до 10");
                }
                int result1 = a.length() / e;
                result = a.substring(0, result1);

                break;

        }


        return result;


    }


    static String printInQuotes(String text) {
        if(text.length()>40){
            System.out.println("\"" + text.substring(0,40) +"..." + "\"");
        }else{
        System.out.println("\"" + text + "\"");}
        return text;
    }
}



