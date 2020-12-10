public class Planet {
    public static double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        double xx = Math.abs(p.xxPos-this.xxPos);
        double yy = Math.abs(p.yyPos-this.yyPos);
        double r = Math.sqrt(xx*xx+yy*yy);
        return r;
    }
    public double calcForceExertedBy(Planet p){
        double f = Planet.G * this.mass*p.mass/(this.calcDistance(p)*this.calcDistance(p));
        return f;
    }
    public double calcForceExertedByX(Planet p){
        double fx = this.calcForceExertedBy(p)*(p.xxPos-this.xxPos)/this.calcDistance(p);
        return fx;
    }
    public double calcForceExertedByY(Planet p){
        double fy = this.calcForceExertedBy(p)*(p.yyPos-this.yyPos)/this.calcDistance(p);
        return fy;
    }
    public double calcNetForceExertedByX(Planet[] p){
        double netfx=0;
        for (Planet planet:p){
            if (!planet.equals(this)){
                netfx+=this.calcForceExertedByX(planet);
            }
        }
        return  netfx;
    }
    public double calcNetForceExertedByY(Planet[] p){
        double netfy=0;
        for (Planet planet:p){
            if (!planet.equals(this)){
                netfy+=this.calcForceExertedByY(planet);
            }
        }
        return  netfy;
    }
    public void update(double dt,double fx, double fy){
        double ax = fx/this.mass;
        double ay = fy/this.mass;
        this.xxVel = this.xxVel+dt*ax;
        this.yyVel = this.yyVel+dt*ay;
        this.xxPos = this.xxPos+dt*this.xxVel;
        this.yyPos+=dt*this.yyVel;
    }
    public void draw(){

        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }


}
