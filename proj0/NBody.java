public class NBody {
    public static double readRadius(String s){
        In in = new In(s);
        int Numofplaents = in.readInt();
        double radius =in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String s){
        In in= new In(s);
        int Numofplaents = in.readInt();
        double radius =in.readDouble();
        Planet[] planets = new Planet[5];
        for (int i = 0; i < 5; i++) {
            double xx =in.readDouble();
            double yy = in.readDouble();
            double xxvel = in.readDouble();
            double yyvel = in.readDouble();
            double mass = in.readDouble();
            String name = in.readString();
            planets[i] = new Planet(xx,yy,xxvel,yyvel,mass,name);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        StdDraw.enableDoubleBuffering();
        for (Planet planet : planets) {
            planet.draw();
        }
        double time = 0;
        while (time<=T){

            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (int j = 0; j < planets.length; j++) {
                planets[j].update(dt,xForces[j],yForces[j]);
                planets[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time+=dt;

        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}
