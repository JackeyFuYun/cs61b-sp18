public class NBody{


/*目的是阅读第二个double数值
1，半径一般在第二行
2，调用in的read方法去阅读
**/
public static double readRadius(String s) {
	In in = new In(s);
	in.readInt();
	double radius = in.readDouble();
	return radius;

}


/*
double xxPos: Its current x position
double yyPos: Its current y position
double xxVel: Its current velocity in the x direction
double yyVel: Its current velocity in the y direction
double mass: Its mass
String imgFileName: The name of the file that corresponds to the image that depicts the planet (for example, jupiter.gif
返回5个星球的数组。包含上述信息

**/
public static Planet[]  readPlanets(String s){
    In in = new In(s);

    int n = in.readInt();
    in.readDouble();
   

    Planet[] allplanets = new Planet[n];

    for(int i = 0; i < n; i++){
    	double xP = in.readDouble();
    	double yP = in.readDouble();
    	double xV = in.readDouble();
    	double yV = in.readDouble();
    	double m  = in.readDouble();
    	String img = in.readString();
    	allplanets[i] = new Planet(xP, yP, xV, yV, m, img);
    }
     return  allplanets;

}

/*创建一个main函数，阅读几个参数，注意把string转化为double

调用readplanets 和 radius方法
*/
public static void main(String[] args) {
	double T = Double.valueOf(args[0]);
	double dt = Double.valueOf(args[1]);
	String filename = args[2];
	Planet allplanets[] = readPlanets(filename);
	double radius =readRadius(filename);
	String imageBackground = "./images/starfield.jpg";
	



//画出背景图。
	//确保流畅动画，避免闪烁
	StdDraw.enableDoubleBuffering();
	

	StdDraw.setScale(-radius, radius);

	//清除绘画窗口
	StdDraw.clear();

	

   /*1.创建一个时间变量，设为0。
   2.创建一个循环L：
     1.创建xForces和yForces的数组
     2.计算net x和net y，分别储存到xForces和yForces的数组中
     3.对每个星球调用update方法，更新每个星球的位置，速度和加速度
     4.画出背景图
     5.画出所有星球
     6.show offscreen buffer
     7.pause: 停止动画10 milliseconds
     8.增加时间变量 dt
     -----把所有数据都存储到这两个数列之后，再调用update方法。
     **/
       double time = 0.0;
       int n = allplanets.length;
        
        //创建一个double的容器用于储存x和y方向的力
     	double[] xForces = new double[n];
     	double[] yForces = new double[n];
     	 while(time < T){
     	for(int i = 0; i < n; i++){
     		double fx = allplanets[i].calcNetForceExertedByX(allplanets);
     		double fy = allplanets[i].calcNetForceExertedByY(allplanets);
            xForces[i] = fx;
            yForces[i] = fy;

     	}
       //update
     	for(int i = 0; i < n; i++){
     		allplanets[i].update(dt, xForces[i], yForces[i]);
     	}
    
    StdDraw.picture(0, 0, imageBackground);
	//x显示该图片
	

	//利用一个循环画出所有的星球
	
    for(Planet p : allplanets){
    	p.draw();
    }

    StdDraw.show();
    StdDraw.pause(10);	
    time += dt;


     }

     StdOut.printf("%d\n", n);
     StdOut.printf("%.2e\n", radius);
     for(int i = 0; i < n; i++){
     	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
     		allplanets[i].xxPos, allplanets[i].yyPos, allplanets[i].xxVel, allplanets[i].yyVel, allplanets[i].mass, allplanets[i].imgFileName);
     }









   


}



}