

public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        double number_of_planets = in.readDouble();
        double universe_radius = in.readDouble();
        return universe_radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int number_of_planets = in.readInt();
        double universe_radius = in.readDouble();
        Planet[] planets = new Planet[number_of_planets];
        //System.out.println(number_of_planets);

        for (int i = 0; i < number_of_planets; i++) {
            planets[i] = new Planet();
            planets[i].xxPos = in.readDouble();
            planets[i].yyPos = in.readDouble();
            planets[i].xxVel = in.readDouble();
            planets[i].yyVel = in.readDouble();
            planets[i].mass = in.readDouble();
            planets[i].imgFileName = in.readString();
        }

        return planets;
    }

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        double t = 0;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = NBody.readPlanets(filename);
        double universe_radius = NBody.readRadius(filename);
        while (t <= T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.clear();
            StdDraw.setScale(-universe_radius, universe_radius);
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t = t + dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", universe_radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}
