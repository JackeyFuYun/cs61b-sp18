public class Planet{ 
	

    private static final double G = 6.67e-11;

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	
	public Planet(double xP, double yP, double xV, double yV, double m, String img
	){
		 xxPos = xP;
		 yyPos = yP;
		 xxVel = xV;
		 yyVel = yV;
		 mass = m;
		 imgFileName = img;

	}
    
    public Planet(Planet p){
    	xxPos = p.xxPos;
    	yyPos = p.yyPos;
    	xxVel = p.xxVel;
    	yyVel = p.yyVel;
    	mass = p.mass;
    	imgFileName = p.imgFileName;
    }

  /*创建两个星球对象，然后计算距离
  
  1.创造一个星球的对象，并把各项数据传递到构造器中。默认是有一个星球对象的
  2.计算出dx和dy；
  3.r2 = dx2 +dy2;
  */
	public double calcDistance(Planet p){
		
		double dx = xxPos - p.xxPos;
		double dy = yyPos - p.yyPos;
		double dist = Math.sqrt(dx * dx + dy * dy);
		return dist;
	}

    
    /*直接套用公式的话，结果就出来。计算另一个星球对此星球的作用力
    1.用fiaal static来申明一个常量g
    2。学会使用科学记数法
    */

   public double calcForceExertedBy(Planet p){
    //调用方法计算出两个星球之间的距离
   	double r = calcDistance(p);
   double F = (G * mass * p.mass) / (r * r);
   return F;

   }

    /*计算x轴方向的作用力
    */
   public double calcForceExertedByX(Planet p){
       double F = calcForceExertedBy(p);
       double r = calcDistance(p);
       double dx = p.xxPos - xxPos;
       double Fx = F * dx / r;
       return Fx;

   }

 /*计算y轴方向的作用力
    */
   public double calcForceExertedByY(Planet p){
       double F = calcForceExertedBy(p);
       double r = calcDistance(p);
       double dy = p.yyPos - yyPos;
       double Fy = F * dy / r;
       return Fy;

   }

  /*计算net force x 和 net force y，
  1.分为两个方法
  初始化一个总值（求和必用），设为0；
  用一个boolean对两个星球是否一样进行判断。if continue的用法是跳出if判断。
  2.有一个数列来储存所有的planet
  对星球数组进行遍历
  3.注意星球不能对自己产生作用了，必须忽略。可以使用 samh.equals(samh)判断
  4，返回值
  */
   

   private Boolean equals(Planet p){
      if(xxPos == p.xxPos && yyPos == p.yyPos && xxVel == p.xxVel && yyVel == p.yyVel&& mass == p.mass && imgFileName == p.imgFileName){
         return true;
      }else {
      	return false;
      }

   }


   public double calcNetForceExertedByX(Planet[] allplanets){
       double fxNET = 0.0;
       for(Planet p: allplanets){
           if(equals(p)){
           	   continue;
             }else{
                  fxNET += calcForceExertedByX(p);

             }

       }
        return fxNET;


   }

   public double calcNetForceExertedByY(Planet[] allplanets){
       double fyNET = 0.0;
       for(Planet p: allplanets){
           if(equals(p)){
           	   continue;
             }else{
                  fyNET += calcForceExertedByY(p);

             }

       }
        return fyNET;


   }

/*创建一个update方法
1.update(dt, fx, fy);
2.axNET=fxNET/m
3.ayNET=fyNET/m;
4.NxV = xV + dt * axNET;
5.NyV = yV + dt * ayNET;
6.NxP = xP + dt * NxV;
7.NyP = yP + dt * NyV;
这个方法不需要返回任何值。
*/

public void update(double dt, double fX, double fY){
    double axNET = fX / this.mass;
    double ayNET = fY / this.mass;

    this.xxVel += dt * axNET;
    this.yyVel += dt * ayNET;
    this.xxPos += dt * this.xxVel;
    this.yyPos += dt * this.yyVel;

}

/*1.画一个星球，首先我需要知道这个星球图片文件的名字。
2.找到这个星球的x和y方向
3，然后按照这个x和y的数值开始画。
*/
public void draw( ){
   //public static String imagToDraw ="imgFileName";
   //StdDraw.clear();
 
   StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);

   



}






}

