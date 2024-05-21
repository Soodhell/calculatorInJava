import java.util.Scanner;

public class Main{
    static public void main(String[] str){

        Scanner scanner = new Scanner(System.in);
        String equation = scanner.nextLine();

        System.out.println(SolvingEquations.solvingEquations(equation));

    }
}

class SolvingEquations{

    static public int solvingEquations(String equation){

        StringBuilder[] massEquation = new StringBuilder[40];
        int j = 0;


        for (int i = 0; i < 40; i++) {
            massEquation[i] = new StringBuilder("");
        }

        for (int i = 0; i < equation.length(); i++) {

            if (Character.getNumericValue(equation.charAt(i)) >= Character.getNumericValue('0')
                    && Character.getNumericValue(equation.charAt(i))<='9'){
                massEquation[j].append(equation.charAt(i));
                continue;
            }

            if (equation.charAt(i) == '('){

                massEquation[j].append(equation.charAt(i));
                j++;
                continue;

            }

            if(equation.charAt(i) == ')'){
                j++;
                massEquation[j].append(equation.charAt(i));
                continue;
            }

            j++;
            massEquation[j].append(equation.charAt(i));
            j++;

        }

        return solvingEquationsMass(massEquation, j);

    }

    static private int solvingEquationsMass(StringBuilder[] equation, int j){

        int i = 0;

        while (i <= j){

            if (equation[i].toString().equals("*") || equation[i].toString().equals("/")){

                if (equation[i+1].toString().equals("(") && equation[i+3].toString().equals(")")){
                    equation[i+1] = equation[i+2];

                    for (int k = i+4; k <= j; k++) {

                        equation[k-2] = equation[k];
                        equation[k-1] = equation[k+1];

                    }

                }

                if (equation[i+1].toString().equals("(")){
                    i += 2;
                    continue;
                }else if (equation[i-1].toString().equals(")")){
                    i += 2;
                    continue;
                }

                if (equation[i].toString().equals("*")){
                    equation[i-1] = equation[i+1] =
                            new StringBuilder(
                                    String.valueOf(
                                            Integer.parseInt(equation[i-1].toString()) *
                                                    Integer.parseInt(equation[i+1].toString()))
                            );
                }

                if (equation[i].toString().equals("/") && !equation[i+1].toString().equals("0")){
                    equation[i-1] = equation[i+1] =
                            new StringBuilder(
                                    String.valueOf(
                                            Integer.parseInt(equation[i-1].toString()) /
                                                    Integer.parseInt(equation[i+1].toString()))
                            );
                }

                for (int k = i+1; k <= j; k++) {

                    equation[k-2] = equation[k];
                    equation[k-1] = equation[k+1];

                }

                j -= 2;
                i -= 1;

            }

            if (equation[i].toString().equals("-") || equation[i].toString().equals("+")){

                if (equation[i+1].toString().equals("(") && equation[i+3].toString().equals(")")){
                    equation[i+1] = equation[i+2];

                    for (int k = i+4; k <= j; k++) {

                        equation[k-2] = equation[k];
                        equation[k-1] = equation[k+1];

                    }

                }

                if (equation[i+1].toString().equals("(")){
                    i += 2;
                    continue;
                }else if (equation[i-1].toString().equals(")")){
                    i += 2;
                    continue;
                }

                if (equation[i+2].toString().equals("*") || equation[i+2].toString().equals("/")){
                    i += 2;
                    continue;
                }
                if (i-2 > 0)
                    if (equation[i-2].toString().equals("*") || equation[i-2].toString().equals("/")){
                        i += 2;
                        continue;
                    }

                if (equation[i].toString().equals("-")){
                    equation[i-1] = equation[i+1] =
                            new StringBuilder(
                                    String.valueOf(
                                            Integer.parseInt(equation[i-1].toString()) -
                                                    Integer.parseInt(equation[i+1].toString()))
                            );
                }

                if (equation[i].toString().equals("+")){
                    equation[i-1] = equation[i+1] =
                            new StringBuilder(
                                    String.valueOf(
                                            Integer.parseInt(equation[i-1].toString()) +
                                                    Integer.parseInt(equation[i+1].toString()))
                            );
                }

                for (int k = i+1; k <= j; k++) {

                    equation[k-2] = equation[k];
                    equation[k-1] = equation[k+1];

                }

                j -= 2;
                i -= 1;

            }

            i++;

        }

        if (equation[1].toString().equals("-")
                || equation[1].toString().equals("+")
                || equation[1].toString().equals("/")
                || equation[1].toString().equals("*"))
            return solvingEquationsMass(equation, j);

        return Integer.parseInt(equation[0].toString());
    }
}