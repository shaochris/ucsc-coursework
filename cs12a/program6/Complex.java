/* Complex.java
*/

class Complex{

   
    private double re;
    private double im;

    
    public static final Complex ONE = Complex.valueOf(1,0);
    public static final Complex ZERO = Complex.valueOf(0,0);
    public static final Complex I = Complex.valueOf(0,1);


    Complex(double a, double b){
        this.re = a;
        this.im = b;
    }

    Complex(double a){
        this.re = a;
        this.im = 0;
    }

    Complex(String s){

        double[] C = parseComplex(s);
        this.re = C[0];
        this.im = C[1];
    }

    
    Complex copy(){
        re = this.re;
        im = this.im;
        return new Complex(re, im);
    }

    
    Complex add(Complex z){
        re = this.re + z.re;
        im = this.im + z.im;
        return new Complex(re, im);
    }

    
    Complex negate(){
        return new Complex(-re, -im);
    }

  
    Complex sub(Complex z){
        re = this.re - z.re;
        im = this.im - z.im;
        return new Complex(re, im);
    }

    
    Complex mult(Complex z){
        Complex a = this;
        double real = a.re * z.re - a.im * z.im;
        double imag = a.re * z.im + a.im * z.re;
        return new Complex(real, imag);
    }

    Complex recip(){
        if(this.equals(Complex.ZERO)) throw new ArithmeticException("Divison by Zero");
        double scale = re*re + im*im;
        return new Complex(re/scale, -im/scale);
    }

    
    Complex div(Complex z){
        Complex a = this;
        return a.mult(z.recip());
    }

    
    Complex conj(){
        return new Complex(re, -im);
    }

    
    double Re(){
        return re;
    }

   
    double Im(){
        return im;
    }

    
    double abs(){
        return Math.sqrt(re*re+im*im);
    }

    
    double arg(){
        return Math.atan2(im, re);
    }

   
    public String toString(){
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im <  0) return re + "-" + (-im) + "i";
        return re + "+" + im + "i";
    }

    
    public boolean equals(Object obj){
        Complex a = (Complex)obj;
        if((this.re == a.re) &&(this.im == a.im)) return true;
        else return false;
    }

   
    static Complex valueOf(double a, double b){
        return new Complex(a,b);
    }

    
    static Complex valueOf(double a){
        return new Complex(a);
    }

    
    static Complex valueOf(String s){
        return new Complex(s);
    }
    static double[] parseComplex(String str){
        double[] part = new double[2];
        String s = str.trim();
        String NUM = "(\\d+\\.\\d*|\\.?\\d+)";
        String SGN = "[+-]?";
        String OP =  "\\s*[+-]\\s*";
        String I =   "i";
        String OR =  "|";
        String REAL = SGN+NUM;
        String IMAG = SGN+NUM+"?"+I;
        String COMP = REAL+OR+
                IMAG+OR+
                REAL+OP+NUM+"?"+I;

        if( !s.matches(COMP) ){
            throw new NumberFormatException(
                    "Cannot parse input string \""+s+"\" as Complex");
        }
        s = s.replaceAll("\\s","");
        if( s.matches(REAL) ){
            part[0] = Double.parseDouble(s);
            part[1] = 0;
        }else if( s.matches(SGN+I) ){
            part[0] = 0;
            part[1] = Double.parseDouble( s.replace( I, "1.0" ) );
        }else if( s.matches(IMAG) ){
            part[0] = 0;
            part[1] = Double.parseDouble( s.replace( I , "" ) );
        }else if( s.matches(REAL+OP+I) ){
            part[0] = Double.parseDouble( s.replaceAll( "("+REAL+")"+OP+".+" , "$1" ) );
            part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+")"+I , "$1"+"1.0" ) );
        }else{   //  s.matches(REAL+OP+NUM+I)
            part[0] = Double.parseDouble( s.replaceAll( "("+REAL+").+"  , "$1" ) );
            part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+NUM+")"+I , "$1" ) );
        }
        return part;
    }
}
