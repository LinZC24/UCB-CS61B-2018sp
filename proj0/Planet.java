public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

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
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double distance;
        distance = Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) +
                            (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
        return distance;
    }

    public double calcForceExertedBy(Planet p) {
        double force;
        force = G * this.mass * p.mass / (calcDistance(p) * calcDistance(p));
        return  force;
    }

    public double calcForceExertedByX(Planet p) {
        double force;
        force = calcForceExertedBy(p) * ((this.xxPos - p.xxPos) / calcDistance(p));
        return force >= 0 ? force : force * (-1);
    }

    public double calcForceExertedByY(Planet p) {
        double force;
        force = calcForceExertedBy(p) * ((this.yyPos - p.yyPos) / calcDistance(p));
        return force >= 0 ? force : force * (-1);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double force = 0;
        for(int i = 0; i < planets.length; i += 1) {
            if(!this.equals(planets[i])) {
                force += calcForceExertedByX(planets[i]);
            }
        }
        return  force;
    }
    public double calcNetForceExertedByY(Planet[] planets) {
        double force = 0;
        for(int i = 0; i < planets.length; i += 1) {
            if(!this.equals(planets[i])) {
                force += calcForceExertedByY(planets[i]);
            }
        }
        return  force;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass, aY = fY / this.mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }
}
