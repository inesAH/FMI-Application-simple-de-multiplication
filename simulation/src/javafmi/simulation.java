package javafmi;

import org.javafmi.wrapper.Simulation;
import org.javafmi.wrapper.v1.Access;

public class simulation {

	int startTime = 0;
	int stopTime = 5;
	int stepSize = 1;


	public simulation() {
		Simulation simulation = new Simulation("fmu/Slave.fmu");
		simulation.init(startTime, stopTime);
		for (int i = startTime; i < stopTime; i ++) {
			simulation.write("Slave.var1").with(4.0);
			simulation.write("Slave.var2").with(5.0);
			
			simulation.doStep(i);
			double resultat= simulation.read("Slave.resultat").asDouble();
			System.out.println("4.0 x 5.0 x "+ i +" ="+resultat);
		}

		simulation.terminate();

	}

	public static void main(String[] args) {
		System.out.println("--- Master --- ");
		new simulation();

	}

}
