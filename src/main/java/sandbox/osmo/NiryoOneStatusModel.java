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
	
		if (state == States.OFF) {

			led = Colors.RED;
			state = States.BOOTING;
		}
		else if (state == States.SHUT_DOWN) {

			led = Colors.OFF;
			state = States.OFF;
		}
		else {

			led = Colors.OFF;
			state = States.DAMAGED;
		}
	
		log("toggle_power");
	}

	@Guard("toggle_power")
	public boolean allowTogglePower() {

		return (state == States.OFF || state == States.SHUT_DOWN);
	}

	@TestStep("long_press_button")
	public void longPressButton() {
	
		if (state == States.ON) {

			state = States.SHUTTING_DOWN;
			led = Colors.VIOLET;
		}

		log("long_press_button");
	}

	@TestStep("signal_shutdown")
	public void signalShutdown() {
	
		if (state == States.ON) {
			
			state = States.SHUTTING_DOWN;
			led = Colors.VIOLET;
		}

		log("signal_shutdown");
	}

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

	@TestStep("connect_wifi")
	public void connectWifi() {

		if (state == States.ON) {

			mode = Modes.WIFI;
			led = Colors.GREEN;
		}

		log("connect_wifi");
	}

	@Guard("connect_wifi")
	public boolean allowConnectWifi() {

		return (state == States.ON);
	}

	@TestStep("disconnect_wifi")
	public void disconnectWifi() {

		if (state == States.ON) {

			mode = Modes.HOTSPOT;
			led = Colors.BLUE;
		}

		log("disconnect_wifi");
	}

	@Guard("disconnect_wifi")
	public boolean allowDisconnectWifi() {

		return (state == States.ON);
	}

	private void log(String testStep) {

		System.out.println(String.format("%s (state=%s; mode=%s; led=%s)",
			testStep, state, mode, led));
	}
}
