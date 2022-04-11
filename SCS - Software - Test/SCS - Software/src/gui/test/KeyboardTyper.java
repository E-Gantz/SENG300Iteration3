<<<<<<< Updated upstream
package gui.test;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.Keyboard;
import org.lsmr.selfcheckout.devices.SupervisionStation;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.KeyboardObserver;

public class KeyboardTyper {
	public class keyboardEyeball implements KeyboardObserver{

		@Override
		public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(Keyboard k, char c) {
			typedString = typedString + c;
		}
	}
	
	public String typedString;
	public keyboardEyeball kEyeball;
	
	public void clearTypedString() {
		typedString = "";
	}

	public KeyboardTyper(SupervisionStation svs) {
		clearTypedString();
		svs.keyboard.attach(kEyeball);
	}
	
	public String getTypedString() {
		return typedString;
	}
	
}
=======
package gui.test;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.Keyboard;
import org.lsmr.selfcheckout.devices.SupervisionStation;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.KeyboardObserver;

public class KeyboardTyper {
	public class keyboardEyeball implements KeyboardObserver{

		@Override
		public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(Keyboard k, char c) {
			typedString = typedString + c;
		}
	}
	
	public String typedString;
	public keyboardEyeball kEyeball;
	
	public void clearTypedString() {
		typedString = "";
	}

	public KeyboardTyper(SupervisionStation svs) {
		clearTypedString();
		svs.keyboard.attach(kEyeball);
	}
	
	public String getTypedString() {
		return typedString;
	}
	
}
>>>>>>> Stashed changes
