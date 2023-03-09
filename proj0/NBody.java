public class NBody {
    public static double readRadius(String fileName) {
        In input = new In(fileName);
        input.readInt();
        return input.readDouble();
    }
    public static Planet[] readPlanets(String fileName) {
        In input = new In(fileName);
        int n = input.readInt();
        Planet[] result = new Planet[n];
        input.readDouble();
        for(int i = 0; i < n; i += 1) {
            double xxPos = input.readDouble();
            double yyPos = input.readDouble();
            double xxVel = input.readDouble();
            double yyVel = input.readDouble();
            double mass = input.readDouble();
            String name = input.readString();
            result[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, name);
        }
        return result;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.enableDoubleBuffering();

        String backGround = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        for(double time = 0; time < T; time += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i = 0; i < planets.length; i += 1) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < planets.length; i += 1) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, backGround);
            for(int i = 0; i < planets.length; i += 1) {
                planets[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.println(planets.length);
        StdOut.println(radius);
        for(int i = 0; i < planets.length; i += 1) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
