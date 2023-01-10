import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    private static int strToNumber(char letter) {
        switch (letter) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            default: return -2;
        }
    }
    private static String roman(int letter) {
        switch (letter) {
            case 1:
                return "I";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 9:
                return "IX";
            case 10:
                return "X";
            case 40:
                return "XL";
            case 50:
                return "L";
            case 90:
                return "XC";
            case 100:
                return "C";
            default:
                return "`";
        }
    }
private static String ToDecimal(int dec){
        String f="";
while (dec>0) {
    if (100<=dec){
        if(roman(dec)=="`"){
            dec-=100;
            f+="C";
        }
        else {
            f+="C";
            break;
        }
    } else if (90<=dec) {
        if (roman(dec)=="`") {
            dec -= 90;
            f += "XC";
        }
        else {
            f+="XC";
            break;
        }
    } else if (50<=dec) {
        if(roman(dec)=="`"){
            dec-=50;
            f+="L";
        }
        else {
            f+="L";
            break;
        }
    } else if (40<=dec) {
        if(roman(dec)=="`") {
            dec -= 40;
            f += "XL";
        }
        else {
            f+="XL";
            break;
        }
    } else if (10 <= dec) {
        if (roman(dec)=="`") {
            dec -= 10;
            f += "X";
        }
        else {
            f+="X";
            break;
        }
    } else if (9 <= dec) {
        if (roman(dec) == "`") {
            dec -= 9;
            f += "IX";
        }
        else {
            f+="IX";
            break;
        }
    } else if (5 <= dec) {
        if (roman(dec) == "`") {
            dec -= 5;
            f += "V";
        }

        else {
            f+="V";
            break;
        }
    }
        else if (4 <= dec) {
            if (roman(dec) == "`") {
                dec -= 4;
                f += "IV";
break;
            }
            else {
                f+="IV";
                break;
            }
        }
        else if (1 <= dec) {
            if (roman(dec) == "`") {
                dec -= 1;
                f += "I";
            }
            else {
                f+="I";
                break;
            }
        }
}
return f;
}
    public static int ToRoman(String roman) {
        if (roman.length() == 0)
            return 0;
        int sum = 0;
        int count = 0;
        int prevNumber = strToNumber(roman.charAt(0));
        for (int i = 1; i < roman.length(); i++) {
            char ch = roman.charAt(i);
            int number = strToNumber(ch);
            if(number==-2){
                return -2;
            }
            if (roman.charAt(i - 1) == roman.charAt(i)) {
                count += 1;
                if (count > 2) {
                    break;
                }
            } else {
                count = 0;
            }
            if (number <= prevNumber) {
                sum += prevNumber;
            } else {
                sum -= prevNumber;
            }
            prevNumber = number;
        }
        sum += prevNumber;
        if (count <= 2) {
            return sum;
        } else {
            return -2;
        }
    }
    public static String calc(String input) throws ErrorExpression {
        int index = -1, count = 0;
        char[] g = {'+', '-', '*', '/'};
        String[] f = {"\\+", "-", "\\*", "/"};
        for (int i = 0; i < input.length(); i++) {
            for (int q=0; q<g.length;q++) {
                if (input.charAt(i)==g[q]) {
                    index = q;
                    count+=1;
                    if(count>1){
                        throw new ErrorExpression("Некорретное выражение");
                    }
                }
            }
        }
        int a, b,sum=0;
        if (index != -1) {
            String[] numbers = input.split(f[index]);
            if ((ToRoman(numbers[0]) != -2 && ToRoman(numbers[1]) != -2) || (ToRoman(numbers[0]) == -2 && ToRoman(numbers[1]) == -2)) {
                if (ToRoman((numbers[0])) != -2 && ToRoman(numbers[1]) != -2) {
                    a = ToRoman(numbers[0]);
                    b = ToRoman(numbers[1]);
                } else {
                    a = Integer.parseInt(numbers[0]);
                    b = Integer.parseInt(numbers[1]);
                }
                if(10>=a&10>=b&&a>=1&&b>=1) {
                    switch (g[index]) {
                        case '+':
                            sum = (a + b);
                            break;
                        case '-':
                            sum = (a - b);
                            break;
                        case '*':
                            sum = (a * b);
                            break;
                        case '/':
                            sum = (a / b);
                            break;
                    }
                }
                else throw new ErrorExpression("Некорретное выражение");
            }                            else throw new ErrorExpression("Некорретное выражение");
            if(ToRoman((numbers[0])) != -2 && ToRoman(numbers[1]) != -2){
                if(sum>0) {
                    return String.valueOf(ToDecimal(sum));
                }
                else {
                    throw new ErrorExpression("В римской системе нет отрицательных чисел");
                }
                }
        } else {
            throw new ErrorExpression("Некорретное выражение");
        }
        return String.valueOf(sum);
    }
    public static void main(String[] args)throws ErrorExpression {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            System.out.println(calc(input));
        }
    }

}




