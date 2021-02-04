package sandbox.osmo;

import osmo.tester.annotation.AfterTest;
import osmo.tester.annotation.BeforeTest;
import osmo.tester.annotation.Guard;
import osmo.tester.annotation.TestStep;
import osmo.tester.annotation.Variable;

public class NiryoOneStatusModel {

	enum States { OFF, BOOTING, ON, SHUTTING_DOWN, SHUT_DOWN, DAMAGED }
	enum Modes { WIFI, HOTSPOT }
	enum Colors { OFF, RED, BLUE, GREEN, VIOLET }

	@Variable
	private States state;

	@Variable
	private Modes mode;

	@Variable
	private Colors led;

	@BeforeTest
	public void setup() {
		
		state = States.OFF;
		mode = Modes.HOTSPOT;
		led = Colors.OFF;

		log("reset");
	}
  
	@AfterTest
	public void teardown() {
	
		System.out.println("\n");
	}

	@TestStep("toggle_power")
	public void togglePower() {
	
		// TODO
	
		log("toggle_power");
	}

	/*
	@Guard("toggle_power")
	public boolean allowTogglePower() {

		return true; // TODO
	}
	*/

	@TestStep("long_press_button")
	public void longPressButton() {
	
		// TODO

		log("long_press_button");
	}

	@TestStep("signal_shutdown")
	public void signalShutdown() {
	
		// TODO

		log("signal_shutdown");
	}

	/*
	@TestStep("wait")
	public void testerWait() {

		if (state == States.BOOTING) {

			if (mode == Modes.HOTSPOT) {

				state = States.ON;
				led = Colors.BLUE;
			}
			else if (mode == Modes.WIFI) {

				state = States.ON;
				led = Colors.GREEN;
			}
		}
		else if (state == States.SHUTTING_DOWN) {

			state = States.SHUT_DOWN;
			led = Colors.RED;
		}
		
		log("wait");
	}
	*/

	// TODO: Can you think of more test steps? Add them.

	private void log(String testStep) {

		System.out.println(String.format("%s (state=%s; mode=%s; led=%s)",
			testStep, state, mode, led));
	}
}
