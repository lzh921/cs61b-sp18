

public class Planet {
    static final double G = 6.67e-11;
    public double xxPos; //its current x position
    public double yyPos; //its current y position
    public double xxVel; //Its current velocity in the x direction
    public double yyVel; //Its current velocity in the y direction
    public double mass; //Its mass
    public String imgFileName; //The name of the file that corresponds to the image that depicts the planet

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public Planet() {

    }

    public double calcDistance(Planet p) {
        return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) +(yyPos - p.yyPos)*(yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return G * mass * p.mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double distance = calcDistance(p);
        double force =  calcForceExertedBy(p);
        double dx = p.xxPos - xxPos;
        return force * dx / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double distance = calcDistance(p);
        double force =  calcForceExertedBy(p);
        double dy = p.yyPos - yyPos;
        return force * dy / distance;
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double net_force = 0;
        for (int i=0; i<p.length; i++){
            if (!p[i].equals(this)){
                net_force = net_force + calcForceExertedByX(p[i]);
            }
        }
        return net_force;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double net_force = 0;
        for (int i=0; i<p.length; i++){
            if (!p[i].equals(this)){
                net_force = net_force + calcForceExertedByY(p[i]);
            }
        }
        return net_force;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + dt * aX;
        yyVel = yyVel + dt * aY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, imgFileName);
    }
}
