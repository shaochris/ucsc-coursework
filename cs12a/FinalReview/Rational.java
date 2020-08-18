// Rational.java
class Rational{
      int numerator; int denominator;  // Fields
      Rational(int n, int d){  // Constructor
            if(d==0) throw new RuntimeException("zero denominator");
            numerator = n; denominator = d;
      }
      Rational plus(Rational Q){
            return new Rational(this.numerator*Q.denominator+Q.numerator*this.denominator,this.denominator*Q.denominator);
      }
      Rational times(Rational Q){
            return new Rational(this.numerator*Q.numerator,this.denominator*Q.denominator);
      }
      public String toString(){
            String str = "";
            str = this.numerator + "/" + this.denominator;
            return str;
      }
      public boolean equals(Object x){
            Rational B = (Rational) x ;
            if(this.numerator == B.numerator && denominator == B.denominator){
                  return true;
            }else{
            return false;
      }
   } 
}// end of class Rational
