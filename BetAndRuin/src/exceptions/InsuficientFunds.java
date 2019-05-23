package exceptions;
public class InsuficientFunds extends Exception {
 private static final long serialVersionUID = 1L;
 
 public InsuficientFunds()
  {
    super();
  }
  /**This exception is triggered if the is not enough money in wallet
  *@param s String of the exception
  */
  public InsuficientFunds(String s)
  {
    super(s);
  }
}