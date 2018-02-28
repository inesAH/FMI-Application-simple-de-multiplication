package siani.javafmi;

import org.javafmi.framework.FmiSimulation;
import org.javafmi.framework.FmiSimulation.Status;

import static org.javafmi.framework.FmiContainer.Causality.input;
import static org.javafmi.framework.FmiContainer.Causality.output;
import static org.javafmi.framework.FmiContainer.Variability.discrete;

public class Slave extends FmiSimulation {

	private static final String ModelName = "Slave";
	private static final String Var1 = "Slave.var1";
	private static final String Var2 = "Slave.var2";
	private static final String ResultatVar = "Slave.resultat";

	public double var1 = 0;
	public double var2 = 0;
	public double resultat = 0;

	@Override
	public Model define() {
		return model(ModelName).canGetAndSetFMUstate(true)
				.add(variable(Var1).asReal().causality(input).variability(discrete).start(0))
				.add(variable(Var2).asReal().causality(input).variability(discrete).start(0))
				.add(variable(ResultatVar).asReal().causality(output).variability(discrete));

	}

	@Override
	public Status doStep(double stepSize) {
		resultat = var1 * var2 * stepSize;
		return Status.OK;
	}

	@Override
	public Status init() {
		logger().info("doing init");
		
		registerReal(Var1, () -> var1, value -> var1 = value);
		registerReal(Var2, () -> var2, value -> var2 = value);
		registerReal(ResultatVar, () -> resultat, value -> resultat = value);
		return Status.OK;
	}

	@Override
	public Status reset() {
		return Status.OK;
	}

	@Override
	public Status terminate() {
		return Status.OK;
	}

	public Status getState(String stateId) {
		logger().info("doing get state");
		return super.getState(stateId);
	}

}
