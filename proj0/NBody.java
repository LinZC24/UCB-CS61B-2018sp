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
}
