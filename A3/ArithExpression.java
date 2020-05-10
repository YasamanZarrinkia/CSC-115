/*
 * The shell of the class, to be completed as part of CSC115 Assignment 3 : Calculator.
 */

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.StringBuilder;
/*
 * NOTE TO STUDENT:
 * Fill in any of the parts that have the comment:
	 ******COMPLETE *****
 * Do not change method headers or code that has been supplied.
 * All methods must be properly commented.
 * Please delete all messages to you, incuding this one, before submitting.
 */

public class ArithExpression {

	public TokenList postfixTokens;
	public TokenList infixTokens;

	/**
	 * Sets up a legal standard Arithmetic expression.
	 * The only parentheses accepted are "(" and ")".
	 * @param word An arithmetic expression in standard infix order.
	 * 	An invalid expression is not expressly checked for, but will not be
	 * 	successfully evaluated, when the <b>evaluate</b> method is called.
	 * @throws InvalidExpressionException if the expression cannot be properly parsed,
	 *  	or converted to a postfix expression.
	 */
	public ArithExpression(String word) {
		if (Tools.isBalancedBy("()",word)) {
			tokenizeInfix(word);
			infixToPostfix();
		} else {
			throw new InvalidExpressionException("Parentheses unbalanced");
		}
	}

	/*
	 * A private helper method that tokenizes a string by separating out
	 * any arithmetic operators or parens from the rest of the string.
	 * It does no error checking.
	 * The method makes use of Java Pattern matching and Regular expressions to
	 * isolate the operators and parentheses.
	 * The operands are assumed to be the substrings delimited by the operators and parentheses.
	 * The result is captured in the infixToken list, where each token is
	 * an operator, a paren or a operand.
	 * @param express The string that is assumed to be an arithmetic expression.
	 */
	private void tokenizeInfix(String express) {
		infixTokens  = new TokenList(express.length());

		// regular expression that looks for any operators or parentheses.
		Pattern opParenPattern = Pattern.compile("[-+*/^()]");
		Matcher opMatcher = opParenPattern.matcher(express);

		String matchedBit, nonMatchedBit;
		int lastNonMatchIndex = 0;
		String lastMatch = "";

		// find all occurrences of a matched substring
		while (opMatcher.find()) {
			matchedBit = opMatcher.group();
			// get the substring between matches
			nonMatchedBit = express.substring(lastNonMatchIndex, opMatcher.start());
			nonMatchedBit = nonMatchedBit.trim(); //removes outside whitespace
			// The very first '-' or a '-' that follows another operator is considered a negative sign
			if (matchedBit.charAt(0) == '-') {
				if (opMatcher.start() == 0 ||
					!lastMatch.equals(")") && nonMatchedBit.equals("")) {
					continue;  // ignore this match
				}
			}
			// nonMatchedBit can be empty when an operator follows a ')'
			if (nonMatchedBit.length() != 0) {
				infixTokens.append(nonMatchedBit);
			}
			lastNonMatchIndex = opMatcher.end();
			infixTokens.append(matchedBit);
			lastMatch = matchedBit;
		}
		// parse the final substring after the last operator or paren:
		if (lastNonMatchIndex < express.length()) {
			nonMatchedBit = express.substring(lastNonMatchIndex,express.length());
			nonMatchedBit = nonMatchedBit.trim();
			infixTokens.append(nonMatchedBit);
		}
	}

	/**
	 * Determines whether a single character string is an operator.
	 * The allowable operators are {+,-,*,/,^}.
	 * @param op The string in question.
	 * @return True if it is recognized as a an operator.
	 */
	public static boolean isOperator(String op) {
		switch(op) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "^":
				return true;
			default:
				return false;
		}
	}
	//specify operators precedenceLevel
    public static int precedenceLevel(String op) {
        switch (op) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
            case "^":
                return 2;   
        }
        return -1;
    }

	/*
	 * NOTE TO STUDENT for infixToPostfix below...:
	 * You do not need to check that the infixTokens data field is a legitimate infix
	 * expression at this time.
	 * If, during the process, something unexpected happens, then throw an Exception, but it
	 * is okay for the postfixTokens to contain an invalid postfix expression.
	 * It is only when processing the public method 'evaluate', that any errors must be
	 * acknowledged.
	 */

	 /**
	 * A private method that initializes the postfixTokens data field.
	 * It takes the information from the infixTokens data field.
	 * If, during the process, it is determined that the expression is invalid,
	 * an InvalidExpressionException is thrown.
 	 * Note that since the only method that calls this method is the constructor,
	 * the Exception is propogated through the constructor.
	 */
	public void infixToPostfix() {
		postfixTokens = new TokenList(infixTokens.size());
        StringStack newStack=new StringStack();
        for(int i=0;i<infixTokens.size();i++){
        	//System.out.println("Current token is: "+ infixTokens.get(i));
        	//System.out.println("is it (? " + infixTokens.get(i).equals("("));


        	//for operands
            if(!isOperator(infixTokens.get(i))  && !(infixTokens.get(i).equals("(")  || infixTokens.get(i).equals(")"))){
                postfixTokens.append(infixTokens.get(i));
                //System.out.println("reached not operator or bracket");
                //System.out.println("postfixTokens: " + postfixTokens);
            } 
            //for left bracet
            if(infixTokens.get(i).equals("(")){
                newStack.push(infixTokens.get(i));
                //System.out.println("reached left bracket");
            }
            //for operators 
            else if(isOperator(infixTokens.get(i))){
            	//System.out.println("reached operator");
            	//System.out.println("Peek: " + newStack.peek());
                while(!newStack.isEmpty() && newStack.peek()!="(" && precedenceLevel(infixTokens.get(i))<=precedenceLevel(newStack.peek()) ){
                    
                    postfixTokens.append(newStack.pop());
                    //System.out.println("postfixTokens: " + postfixTokens);
                    //System.out.println("appended newStack.pop()");
                    
                    
                }
                //System.out.println("Push!!!");
                newStack.push(infixTokens.get(i));
                //System.out.println("current stack peek" + newStack.peek());
            }
            //for right hand bracet
            else if(infixTokens.get(i).equals(")")){
                while(!newStack.peek().equals("(")){
                    postfixTokens.append(newStack.pop());  
                    //System.out.println("postfixTokens: " + postfixTokens); 
                    //System.out.println("Reached right bracket");
                }
                //popping left bracet
                newStack.pop();
            }
            
        }  // end of forloop
          while(!newStack.isEmpty()){
        postfixTokens.append(newStack.pop());
        //System.out.println("postfixTokens: " + postfixTokens);
    }       
    }//end infixTo PostFix

    

     
	

	public String getInfixExpression() {
	
		return infixTokens.toString();
	}



	public String getPostfixExpression() {
	
		return postfixTokens.toString() ;
	}



	//postfix calculator
	public double evaluate() throws InvalidExpressionException{

		StringStack eval=new StringStack();

		
		double result=0;
		for(int i=0;i<postfixTokens.size();i++){
			String token=postfixTokens.get(i);
			//checking for operator and alphabet
			if(!isOperator(token) && token.matches("[0-9]+")){
				
				eval.push(token);
				//System.out.println(eval.peek());
			}

			else if(token.equals("+")){
				//System.out.println("Adding Numbers");
				if(eval.isEmpty())
					throw new InvalidExpressionException("inavild expression");
				int x=Integer.parseInt(eval.pop());
				int y=Integer.parseInt(eval.pop());
				eval.push(String.valueOf(x+y));
			}
			else if(token.equals("-")){
				int x=Integer.parseInt(eval.pop());
				int y=Integer.parseInt(eval.pop());
				eval.push(String.valueOf(y-x));
			
			}
			else if(token.equals("*")){
				int x=Integer.parseInt(eval.pop());
				int y=Integer.parseInt(eval.pop());
				eval.push(String.valueOf(x*y));
			}
			else if(token.equals("/")){
				int x=Integer.parseInt(eval.pop());
				int y=Integer.parseInt(eval.pop());
				eval.push(String.valueOf(y/x));
			}
			else if(token.equals("^")){
				int x=Integer.parseInt(eval.pop());
				int y=Integer.parseInt(eval.pop());
				eval.push(String.valueOf((int)Math.pow(y,x)));
            }
            //throws invalidExpression
            else{
            	//System.out.println("Exception Thrown ");
            	throw new InvalidExpressionException("inavild expression");
            }

            
		    
		}

		
		//pop the result
		System.out.println(eval.peek());
		result=Double.parseDouble(eval.pop());
		return result;
          
	 	
	}

	public static void main(String[] args) {
	
		String Statement="+ +";
		System.out.println("IsOperator: " + isOperator("a"));
		//creating new object of ArithExpression
      	ArithExpression  testcase = new ArithExpression(Statement);
      	System.out.println("getInfixExpression: " +testcase.getInfixExpression());
      	System.out.println("getPostfixExpression: " +testcase.getPostfixExpression());
      	System.out.println("evaluate: " +testcase.evaluate());

	}

}

