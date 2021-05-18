package pk.asghar.talha.calculator;

public class Expression {


//    7+8+3

    //operands = [19]
    //operators = [ ]


    public static boolean hasInvalidData(final char []temp){

        if( (temp.length==0) || (temp[temp.length-1] == '.' ) )
            return true;

        for(int i=0; i<temp.length ; i++)
        {
            if(temp[i]=='.' && temp[i+1]=='.')
                return true;

            if (! (temp[i]=='1' ||temp[i]=='2'||temp[i]=='3'||temp[i]=='4'||
                    temp[i]=='5'||temp[i]=='6'||temp[i]=='7'||
                    temp[i]=='8'||temp[i]=='9'||temp[i]=='0'||
                    temp[i]=='+' ||temp[i]=='-'||temp[i]=='*'||
                    temp[i]=='/' || temp[i]=='.' ) )
            {
                return true;
            }



        }

        return false;
    }

    public static boolean hasInvalidOperator(final char []temp){

        if(temp.length==0)
            return true;

        else if ( (temp[0]=='+' ||temp[0]=='-'||temp[0]=='*'|| temp[0]=='/' ) ||
                (temp[(temp.length-1)]=='+' ||temp[(temp.length-1)]=='-' ||
                        temp[(temp.length-1)]=='*' || temp[(temp.length-1)]=='/')    )
        {

            return true;

        }


        for(int i=0; i <= (temp.length - 2) ; i++)
        {
            if ( (temp[i]=='+' ||temp[i]=='-'||temp[i]=='*'|| temp[i]=='/' ) &&
                    (temp[(i+1)]=='+' ||temp[(i+1)]=='-' ||
                            temp[(i+1)]=='*'|| temp[(i+1)]=='/' )    )
            {

                return true;

            }


        }

        return false;


    }

    public static int countOperators(final char[] temp){

        int total =0;

        for(int i=0; i<temp.length ; i++){

            if ( temp[i]=='+' ||temp[i]=='-' ||
                    temp[i]=='*'|| temp[i]=='/' )
            {
                total++;
            }
        }

        return total;

    }


    public static boolean hasOperator(final char[]temp, char sign){

        for(int i=0; i<temp.length ; i++){

            if ( temp[i]==sign  ){

                return true;
            }
        }

        return false;
    }


    public static int operatorIsAt(final char[]temp, char sign){

        for (int i=0; (hasOperator(temp,sign)) && (i< temp.length) ; i++){

            if ( temp[i]==sign  ){

                return i;
            }

        }

        return -99;

    }

    public static boolean hasAnyOperator(final char[]temp){

        for(int i=0; i<temp.length ; i++){

            if ( hasOperator(temp,'-')||hasOperator(temp,'+')||
                    hasOperator(temp,'/') || hasOperator(temp,'*')  ){

                return true;
            }
        }

        return false;
    }

    public static char[] getOperators(final char []temp){

        char []op = new char[countOperators(temp)];

        int opIndex=0;

        for(int i=0; i<temp.length ; i++){

            if(temp[i]=='+' ||temp[i]=='-'||temp[i]=='*'||temp[i]=='/')
                op[opIndex++] = temp[i];


        }

        return op;

    }


    public static double[] getNumbers(final char[]temp)
            throws java.lang.NumberFormatException{

        double []num = new double[countOperators(temp)+1];

        int numIndex=0;

        String digit = "";

        for(int i=0; i<temp.length ; i++){

            if(temp[i]=='1' ||temp[i]=='2'||temp[i]=='3'||temp[i]=='4'
                    ||temp[i]=='5'||temp[i]=='6'||temp[i]=='7'||
                    temp[i]=='8'||temp[i]=='9'||temp[i]=='0'|| temp[i]=='.'  )
            {
                digit+=temp[i];
            }
            else{
                num[numIndex++] = Double.parseDouble(digit);
                digit="";
            }

            if(i==temp.length-1){
                num[numIndex++] = Double.parseDouble(digit);
                digit="";

            }

        }	// end of for loop


        return num;
    }

    public static char[] updateOperators(final char []temp,final int index){

        char[] newOp = new char[temp.length-1];

        System.arraycopy(temp,0,newOp,0,index);
        if((temp.length-1)>index)
            System.arraycopy(temp,index+1,newOp,index,(temp.length-1)-index);

        return newOp;
    }

    public static double[] updateNumbers(final double []temp,final int index){

        double[] newNum = new double[temp.length-1];

        System.arraycopy(temp,0,newNum,0,index);
        if((temp.length-1)>index)
            System.arraycopy(temp,index+1,newNum,index,(temp.length-1)-index);

        return newNum;
    }



    public static double DMAS(char []inputData){

        char[] operators = getOperators(inputData);
        double [] numbers = getNumbers(inputData);

        System.out.print("\nNumbers entered are : "+numbers.length);
        for(double t : numbers)
            System.out.print(" ,"+t);

        System.out.print("\nOperators entered are : "+operators.length);
        for(char t : operators)
            System.out.print(" ,"+t);
        System.out.println();

        while( hasAnyOperator(operators) ){

            while( hasOperator(operators,'/') ){

                int k = operatorIsAt(operators,'/');

                System.out.println("k: "+k+" d> "+numbers[k]+"/"+numbers[k+1]+"="
                        +(numbers[k] / numbers[k+1]) );

                numbers[k+1] = numbers[k] / numbers[k+1];

                operators=updateOperators(operators,k);
                numbers=updateNumbers(numbers,k);

                System.out.print("\nUpdated numbers are : "+numbers.length);
                for(double t : numbers)
                    System.out.print(" ,"+t);

                System.out.print("\nUpdated Operators are : "+operators.length);
                for(char t : operators)
                    System.out.print(" ,"+t);
                System.out.println();
            }

            while( hasOperator(operators,'*') ){

                int k = operatorIsAt(operators,'*');

                System.out.println("k: "+k+" m> "+numbers[k]+"*"+numbers[k+1]+"="
                        +(numbers[k] * numbers[k+1]) );

                numbers[k+1] = numbers[k] * numbers[k+1];

                operators=updateOperators(operators,k);
                numbers=updateNumbers(numbers,k);

                System.out.print("\nUpdated numbers are : "+numbers.length);
                for(double t : numbers)
                    System.out.print(" ,"+t);

                System.out.print("\nUpdated Operators are : "+operators.length);

                for(char t : operators)
                    System.out.print(" ,"+t);

                System.out.println();
            }

            while( hasOperator(operators,'-') ){

                int k = operatorIsAt(operators,'-');

                System.out.println("k: "+k+" s> "+numbers[k]+"-"+numbers[k+1]+"="
                        +(numbers[k] - numbers[k+1]) );

                numbers[k+1] = numbers[k] - numbers[k+1];

                operators=updateOperators(operators,k);
                numbers=updateNumbers(numbers,k);

                System.out.print("\nUpdated numbers are : "+numbers.length);
                for(double t : numbers)
                    System.out.print(" ,"+t);

                System.out.print("\nUpdated Operators are : "+operators.length);
                for(char t : operators)
                    System.out.print(" ,"+t);
                System.out.println();
            }


            while( hasOperator(operators,'+') ){

                int k = operatorIsAt(operators,'+');

                System.out.println("k: "+k+" a> "+numbers[k]+"+"+numbers[k+1]+"="
                        +(numbers[k] + numbers[k+1]) );


                numbers[k+1] = numbers[k] + numbers[k+1];

                operators=updateOperators(operators,k);
                numbers=updateNumbers(numbers,k);

                System.out.print("\nUpdated numbers are : "+numbers.length);
                for(double t : numbers)
                    System.out.print(" ,"+t);

                System.out.print("\nUpdated Operators are : "+operators.length);
                for(char t : operators)
                    System.out.print(" ,"+t);
                System.out.println();

            }




        }	 // end of while loop

        System.out.println("Final Ans : " + numbers[numbers.length-1] );
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        return (numbers[numbers.length-1]);


    }	// end of function "DMAS"

}
