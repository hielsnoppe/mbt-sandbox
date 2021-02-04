package sandbox.osmo;

import osmo.tester.OSMOConfiguration;
import osmo.tester.OSMOTester;
import osmo.tester.generator.endcondition.Length;
import osmo.tester.scenario.Scenario;

public class Main {

	public static void main(String[] args) {

		OSMOConfiguration config = new OSMOConfiguration();
		config.addModelObject(new NiryoOneStatusModel());

		config.setTestEndCondition(new Length(5));
		config.setSuiteEndCondition(new Length(10));

		Scenario scenario = new Scenario(false);
    	scenario.addStartup("toggle_power");
		config.setScenario(scenario);

		OSMOTester tester = new OSMOTester();
		tester.setConfig(config);
		tester.generate(42); // seed for random generator
	}
}